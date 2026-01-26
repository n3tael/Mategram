cmake_minimum_required(VERSION 3.22.1)

project("jni")


include("${CMAKE_HOME_DIRECTORY}/cmake/ReadVariables.cmake")
include("${CMAKE_HOME_DIRECTORY}/cmake/Prefix.cmake")
include("${CMAKE_HOME_DIRECTORY}/cmake/Join.cmake")


set(THIRDPARTY_DIR "${CMAKE_HOME_DIRECTORY}/third_party")
set(STUB_DIR "${CMAKE_HOME_DIRECTORY}/stub")
set(MATEGRAM_ROOT_DIR "${CMAKE_HOME_DIRECTORY}/../../../../..")
set(UTILS_DIR "${THIRDPARTY_DIR}/jni-utils")

set(ZSTD_LIB_DIR ${THIRDPARTY_DIR}/zstd/lib)

set(ENABLE_TG_CALLS no)
set(USE_WEBP yes)

file(GLOB ZSTD_SOURCES
        "${ZSTD_LIB_DIR}/common/*.c"
        "${ZSTD_LIB_DIR}/compress/*.c"
        "${ZSTD_LIB_DIR}/decompress/*.c"
        "${ZSTD_LIB_DIR}/zstd.h"
)

add_library(zstd STATIC ${ZSTD_SOURCES})
target_compile_definitions(zstd PUBLIC ZSTD_DISABLE_ASM)
target_include_directories(zstd PUBLIC ${ZSTD_LIB_DIR})


if (${ANDROID_ABI} STREQUAL "x86_64" OR ${ANDROID_ABI} STREQUAL "arm64-v8a")
    set(FFMPEG_ABI ${ANDROID_ABI})
elseif(${ANDROID_ABI} STREQUAL "x86")
    set(FFMPEG_ABI "i686")
elseif(${ANDROID_ABI} STREQUAL "armeabi-v7a")
    set(FFMPEG_ABI "armv7-a")
else()
    message(FATAL_ERROR "Unknown abi: ${ANDROID_ABI}")
endif()
set(FFMPEG_DIR "${THIRDPARTY_DIR}/ffmpeg/build/android/${ANDROID_ABI}/install/")
set(FFMPEG_LIBS
        swresample
        avformat
        swscale
        avcodec
        avfilter
        avutil
)

set(VPX_DIR "${THIRDPARTY_DIR}/libvpx/build/${FFMPEG_ABI}")
set(VPX_LIB_PATH "${VPX_DIR}/lib/libvpx.a")


if (${ANDROID_ABI} STREQUAL "armeabi-v7a" OR ${ANDROID_ABI} STREQUAL "arm64-v8a")
    set(IS_ARM_FAMILY yes)
else()
    set(IS_ARM_FAMILY no)
endif()

message(STATUS "Building native library... \
        CMAKE_BUILD_TYPE: ${CMAKE_BUILD_TYPE} \
        IS_ARM_FAMILY: ${IS_ARM_FAMILY}")


set(CMAKE_SKIP_RPATH ON)
set(CMAKE_BUILD_WITH_INSTALL_RPATH ON)

if (${IS_ARM_FAMILY})
    enable_language(ASM)
else()
    enable_language(ASM_NASM)
endif()
set(ORIGINAL_CMAKE_ASM_FLAGS "${CMAKE_ASM_FLAGS}")


set(EXCLUDE_LIBS
        libjni-utils.a
        libyuv.a
        "${VPX_LIB_PATH}"
        liblz4.a
)

if (${USE_WEBP})
    list(APPEND EXCLUDE_LIBS
            libwebpdecoder_static.a
    )
endif()

foreach(ffmpeg_lib ${FFMPEG_LIBS})
    list(APPEND EXCLUDE_LIBS
            "${FFMPEG_DIR}/lib/lib${ffmpeg_lib}.so"
    )
endforeach()

Join(EXCLUDE_LIBS "${EXCLUDE_LIBS}" ",")

list(APPEND ADD_LINKER_FLAGS
        -Wl,--exclude-libs,${EXCLUDE_LIBS}
)

Join(ADD_LINKER_FLAGS "${ADD_LINKER_FLAGS}" " ")

set(CMAKE_SHARED_LINKER_FLAGS "${CMAKE_SHARED_LINKER_FLAGS} ${ADD_LINKER_FLAGS}")


add_subdirectory(
        "${UTILS_DIR}"
)

include("${CMAKE_HOME_DIRECTORY}/BuildYuv.cmake")

add_library(vpx STATIC IMPORTED)
set_target_properties(vpx PROPERTIES IMPORTED_LOCATION "${VPX_LIB_PATH}")
target_include_directories(vpx INTERFACE "${VPX_DIR}/include")

include("${CMAKE_HOME_DIRECTORY}/BuildRlottie.cmake")

include("${CMAKE_HOME_DIRECTORY}/BuildLz4.cmake")

if (${USE_WEBP})
    include("${CMAKE_HOME_DIRECTORY}/BuildWebp.cmake")
endif()


foreach(FFMPEG_LIB IN LISTS FFMPEG_LIBS)
    add_library(${FFMPEG_LIB} STATIC IMPORTED)
    set_target_properties(${FFMPEG_LIB} PROPERTIES IMPORTED_LOCATION "${FFMPEG_DIR}/lib/lib${FFMPEG_LIB}.so")
    target_include_directories(${FFMPEG_LIB} INTERFACE "${FFMPEG_DIR}/include")
endforeach()


set(NATIVE_LIB "mgjni")
add_library(${NATIVE_LIB} SHARED
        log.cpp
        "${THIRDPARTY_DIR}/androidx-media/ffmpeg_jni.cc"
        "${THIRDPARTY_DIR}/androidx-media/vpx_jni.cc"
        jni.cpp
        bridge.cpp
)

target_include_directories(${NATIVE_LIB} PRIVATE
        "${THIRDPARTY_DIR}"
        .
)

target_compile_definitions(${NATIVE_LIB} PUBLIC
        SOCKLEN_T=socklen_t
        LOCALE_NOT_USED

        DISABLE_IMPORTGL
        BSD=1
        AVOID_TABLES
        ANDROID_TILE_BASED_DECODE
        ANDROID_ARMV6_IDCT
        __STDC_CONSTANT_MACROS

        TDLIB_TDAPI_CLASS_PATH="org/drinkless/tdlib/TdApi"
)
if(CMAKE_BUILD_TYPE STREQUAL "Release")
    target_compile_options(${NATIVE_LIB} PUBLIC
            -O3 -flto
            -Wall -Wno-deprecated-declarations
            -Wno-macro-redefined -Wno-unused-variable
            -fno-math-errno -fno-strict-aliasing -funroll-loops
            -fassociative-math -freciprocal-math -fno-signed-zeros
            -fno-rtti
            -fvisibility=hidden
            -march=native
            -ftree-vectorize -fvectorize
    )
    target_link_options(${NATIVE_LIB} PRIVATE -O3 -flto)
else()
    target_compile_options(${NATIVE_LIB} PUBLIC
            -O2
            -Wall -Wno-deprecated-declarations
            -Wno-macro-redefined -Wno-unused-variable
            -fno-math-errno -fno-strict-aliasing -funroll-loops
            -fassociative-math
            -fno-rtti
            -fvisibility=hidden
    )
endif()

target_link_libraries(${NATIVE_LIB}
        jni-utils
        yuv
        vpx
        rlottie
        zstd
        lz4
)

if (${USE_WEBP})
    target_link_libraries(${NATIVE_LIB} webpdecoder_static)
else()
    target_compile_definitions(${NATIVE_LIB} PRIVATE NO_WEBP)
endif()
foreach(ffmpeg_lib ${FFMPEG_LIBS})
    target_link_libraries(${NATIVE_LIB} "${ffmpeg_lib}")
endforeach()

target_link_libraries(${NATIVE_LIB}
        jnigraphics
        log
        GLESv2
        EGL
        android
        cpufeatures
)

if (${ANDROID_ABI} STREQUAL "arm64-v8a" OR ${ANDROID_ABI} STREQUAL "x86_64")
    target_link_options(${NATIVE_LIB} PRIVATE
            "-Wl,-z,max-page-size=16384"
    )
endif()

include(AndroidNdkModules)
android_ndk_import_module_cpufeatures()