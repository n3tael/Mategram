cmake_minimum_required(VERSION 3.22.1)
project("lottie_player")
add_link_options("-Wl,-z,max-page-size=16384")
find_library(log-lib log)
find_library(android-lib android)
find_library(jnigraphics-lib jnigraphics)
add_subdirectory(rlottie)
add_library(
        mategram-native
        SHARED
        rlottie.cpp)
target_link_libraries(
        mategram-native
        PRIVATE
        rlottie
        ${log-lib}
        ${android-lib}
        ${jnigraphics-lib})