plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "org.drinkless.tdlib"
    compileSdk = 36
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    implementation(libs.androidx.annotation.jvm)
}
