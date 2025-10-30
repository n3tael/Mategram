plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.xxcactussell.mategram.utils"
    compileSdk = 36
    defaultConfig {
        minSdk = 31
    }
}

kotlin {
    jvmToolchain(21)
}
dependencies {
    implementation(libs.androidx.compose.runtime)
}
