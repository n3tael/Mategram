plugins {
    alias(libs.plugins.android.library)
}

kotlin {
    jvmToolchain(21)
    android {
        namespace = "com.xxcactussell.mategram.domain"
        compileSdk = 36
        defaultConfig {
            minSdk = 31
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.hilt.core)
}