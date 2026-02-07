plugins {
    alias(libs.plugins.android.library)
}

kotlin {
    jvmToolchain(21)
    android {
        namespace = "org.drinkless.tdlib"
        compileSdk = 36
    }
}

dependencies {
    implementation(libs.androidx.annotation.jvm)
}
