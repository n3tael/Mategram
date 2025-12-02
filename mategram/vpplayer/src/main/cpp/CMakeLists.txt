cmake_minimum_required(VERSION 3.22.1)
project("vpplayer")

add_link_options("-Wl,-z,max-page-size=16384")

find_library(log-lib log)
find_library(android-lib android)

set(LIBVPX_ROOT "${CMAKE_CURRENT_SOURCE_DIR}/libvpx-prebuilt")
set(LIBVPX_INCLUDE "${LIBVPX_ROOT}/include")

set(LIBWEBM_ROOT "${CMAKE_CURRENT_SOURCE_DIR}/libwebm-prebuilt")
set(LIBWEBM_INCLUDE "${LIBWEBM_ROOT}/include")

set(LIBYUV_ROOT "${CMAKE_CURRENT_SOURCE_DIR}/libyuv-prebuilt")
set(LIBYUV_INCLUDE "${LIBYUV_ROOT}/include")

set(LIBFFMPEG_ROOT "${CMAKE_CURRENT_SOURCE_DIR}/libffmpeg-prebuilt")
set(LIBFFMPEG_INCLUDE "${LIBFFMPEG_ROOT}/include")

include_directories(${LIBVPX_INCLUDE} ${LIBWEBM_INCLUDE} ${LIBYUV_INCLUDE} ${LIBFFMPEG_INCLUDE})

set(libvpx_path "${LIBVPX_ROOT}/${ANDROID_ABI}/lib/libvpx.a")
add_library(libvpx STATIC IMPORTED)
set_target_properties(libvpx PROPERTIES IMPORTED_LOCATION ${libvpx_path})

set(libwebm_path "${LIBWEBM_ROOT}/${ANDROID_ABI}/libwebm.a")
add_library(webm STATIC IMPORTED)
set_target_properties(webm PROPERTIES IMPORTED_LOCATION ${libwebm_path})

set(libyuv_path "${LIBYUV_ROOT}/${ANDROID_ABI}/libyuv.a")
add_library(yuv STATIC IMPORTED)
set_target_properties(yuv PROPERTIES IMPORTED_LOCATION ${libyuv_path})

set(libavformat_path "${LIBFFMPEG_ROOT}/${ANDROID_ABI}/lib/libavformat.a")
add_library(avformat STATIC IMPORTED)
set_target_properties(avformat PROPERTIES IMPORTED_LOCATION ${libavformat_path})

set(libavcodec_path "${LIBFFMPEG_ROOT}/${ANDROID_ABI}/lib/libavcodec.a")
add_library(avcodec STATIC IMPORTED)
set_target_properties(avcodec PROPERTIES IMPORTED_LOCATION ${libavcodec_path})

set(libavutil_path "${LIBFFMPEG_ROOT}/${ANDROID_ABI}/lib/libavutil.a")
add_library(avutil STATIC IMPORTED)
set_target_properties(avutil PROPERTIES IMPORTED_LOCATION ${libavutil_path})

set(libavfilter_path "${LIBFFMPEG_ROOT}/${ANDROID_ABI}/lib/libavfilter.a")
add_library(avfilter STATIC IMPORTED)
set_target_properties(avfilter PROPERTIES IMPORTED_LOCATION ${libavfilter_path})

set(libavdevice_path "${LIBFFMPEG_ROOT}/${ANDROID_ABI}/lib/libavdevice.a")
add_library(avdevice STATIC IMPORTED)
set_target_properties(avdevice PROPERTIES IMPORTED_LOCATION ${libavdevice_path})

set(libswscale_path "${LIBFFMPEG_ROOT}/${ANDROID_ABI}/lib/libswscale.a")
add_library(swscale STATIC IMPORTED)
set_target_properties(swscale PROPERTIES IMPORTED_LOCATION ${libswscale_path})

set(libswresample_path "${LIBFFMPEG_ROOT}/${ANDROID_ABI}/lib/libswresample.a")
add_library(swresample STATIC IMPORTED)
set_target_properties(swresample PROPERTIES IMPORTED_LOCATION ${libswresample_path})

add_library(vpplayer SHARED vpplayer.cpp)

target_link_libraries(vpplayer PRIVATE android ${log-lib} z m atomic)

target_link_options(vpplayer PRIVATE "-Wl,--start-group")
target_link_libraries(vpplayer PRIVATE
        libvpx
        webm
        yuv
        avformat
        avcodec
        avutil
        avfilter
        avdevice
        swscale
        swresample
        jnigraphics
)
target_link_options(vpplayer PRIVATE "-Wl,--end-group")