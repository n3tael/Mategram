package com.xxcactussell.jni

class NativeLib {

    /**
     * A native method that is implemented by the 'jni' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'jni' library on application startup.
        init {
            System.loadLibrary("jni")
        }
    }
}