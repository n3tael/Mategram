# BuildRlottie.cmake
# Custom, local build of rlottie (uses source tree inside third_party/rlottie)
# This file intentionally does not change anything inside the rlottie submodule.

set(RLOTTIE_DIR "${THIRDPARTY_DIR}/rlottie")
message(STATUS "Using custom BuildRlottie.cmake for ${RLOTTIE_DIR}")

# Collect C/C++ sources from rlottie/src (exclude tests/examples and wasm)
file(GLOB_RECURSE RLOTTIE_SRCS
    "${RLOTTIE_DIR}/src/*.cpp"
    "${RLOTTIE_DIR}/src/*.c"
    "${RLOTTIE_DIR}/src/*.cc"
    "${RLOTTIE_DIR}/src/*.cxx"
)

# Exclude directories that are not relevant for Android build:
# - wasm (requires emscripten)
# - test, example, tools (build-time/test helpers)
# - pixman (not needed when NEON is disabled)
set(RLOTTIE_SRCS_FILTERED "")
foreach(_src ${RLOTTIE_SRCS})
    # Normalize path for matching (CMake usually returns forward slashes)
    string(REPLACE "\\" "/" _src_norm "${_src}")
    string(REGEX MATCH "/(wasm|test|example|tools|pixman)/" _is_excluded "${_src_norm}")
    if(NOT _is_excluded)
        list(APPEND RLOTTIE_SRCS_FILTERED "${_src}")
    endif()
endforeach()
set(RLOTTIE_SRCS ${RLOTTIE_SRCS_FILTERED})

# Create static library target 'rlottie' so top-level CMake can link to it.
add_library(rlottie STATIC ${RLOTTIE_SRCS})
set_target_properties(rlottie PROPERTIES DEFINE_SYMBOL RLOTTIE_BUILD)

# For armeabi-v7a: use VFPv3-D16 instead of NEON
# This disables __ARM_NEON__ macro and forces use of portable C implementations
if (DEFINED ANDROID_ABI AND "${ANDROID_ABI}" STREQUAL "armeabi-v7a")
    message(STATUS "armeabi-v7a: Using VFPv3-D16 (no NEON) - portable C fallback")
    
    # Replace any -mfpu=neon with -mfpu=vfpv3-d16
    string(REPLACE "-mfpu=neon" "-mfpu=vfpv3-d16" CMAKE_C_FLAGS "${CMAKE_C_FLAGS}")
    string(REPLACE "-mfpu=neon" "-mfpu=vfpv3-d16" CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS}")
    string(REPLACE "-mfpu=neon" "-mfpu=vfpv3-d16" CMAKE_ASM_FLAGS "${CMAKE_ASM_FLAGS}")
    
    # Apply these flags to rlottie target as well
    target_compile_options(rlottie PRIVATE -mfpu=vfpv3-d16)
    
else()
    message(STATUS "${ANDROID_ABI}: NEON/SIMD optimizations enabled")
endif()

# Public include directory (API headers)
target_include_directories(rlottie
    PUBLIC $<BUILD_INTERFACE:${RLOTTIE_DIR}/inc>
    PRIVATE
        "${RLOTTIE_DIR}/vs2019"
        "${RLOTTIE_DIR}/src"
        "${RLOTTIE_DIR}/src/vector"
        "${RLOTTIE_DIR}/src/vector/freetype"
)

# Compiler flags similar to rlottie original CMake
target_compile_features(rlottie PUBLIC cxx_std_14)
if (NOT MSVC)
    target_compile_options(rlottie
        PRIVATE
            -std=c++14
            -fno-exceptions
            -fno-unwind-tables
            -fno-asynchronous-unwind-tables
            -fno-rtti
            -Wall
            -fvisibility=hidden
            -Wnon-virtual-dtor
            -Woverloaded-virtual
            -Wno-unused-parameter
    )
else()
    target_compile_options(rlottie PRIVATE /std:c++14 /EHs-c- /GR- /W3)
endif()

# Threads dependency
set(CMAKE_THREAD_PREFER_PTHREAD TRUE)
find_package(Threads)
target_link_libraries(rlottie PUBLIC ${CMAKE_THREAD_LIBS_INIT})

# For non-Windows / non-Apple builds, keep --no-undefined to match original behavior
if (NOT APPLE AND NOT MSVC)
    target_link_libraries(rlottie PRIVATE "-Wl,--no-undefined")
endif()

# Optionally set version to avoid surprises (not strictly required)
set_target_properties(rlottie PROPERTIES
    VERSION 0.2
    SOVERSION 0
)