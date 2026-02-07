plugins {
    alias(libs.plugins.android.library)
}

kotlin {
    jvmToolchain(21)
    android {
        namespace = "com.xxcactussell.mategram.utils"
        compileSdk = 36
        defaultConfig {
            minSdk = 31
        }
    }
}
dependencies {
    implementation(libs.androidx.compose.runtime)
}
