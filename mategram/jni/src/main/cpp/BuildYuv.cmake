# yuv

set(YUV_DIR "${THIRDPARTY_DIR}/libyuv")

# Collect all source files from the source directory
file(GLOB YUV_SOURCES "${YUV_DIR}/source/*.cc")

add_library(yuv STATIC ${YUV_SOURCES})

if(${ANDROID_ABI} STREQUAL "armeabi-v7a")
    target_compile_definitions(yuv PRIVATE
            LIBYUV_NEON
    )
elseif(${ANDROID_ABI} STREQUAL "arm64-v8a")
    target_compile_options(yuv PRIVATE -march=armv8.2-a+i8mm+sve+sve2+dotprod+sme)
endif()

target_include_directories(yuv PUBLIC
        "${YUV_DIR}/include"
)

target_compile_options(yuv PRIVATE
        -fexceptions
        -fno-unwind-tables -fno-asynchronous-unwind-tables
        -Wnon-virtual-dtor -Woverloaded-virtual
        -Wno-unused-parameter
)