import java.util.Properties

val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localProperties.load(localPropertiesFile.inputStream())
}

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.xxcactussell.mategram.data"
    compileSdk = 36
    android.buildFeatures.buildConfig = true
    defaultConfig {
        minSdk = 31
        val apiId: String = localProperties.getProperty("TELEGRAM_API_ID")?: "0"
        val apiHash: String = localProperties.getProperty("TELEGRAM_API_HASH")?: "YOUR_API_HASH"
        buildConfigField("int", "TELEGRAM_API_ID", apiId)
        buildConfigField("String", "TELEGRAM_API_HASH", apiHash)
    }
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    implementation(project(":mategram:tdlib"))
    implementation(project(":mategram:utils"))
    implementation(project(":mategram:domain"))
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.androidx.core.ktx)
    implementation(libs.hilt.android)
    implementation(libs.androidx.work.runtime.ktx)
    ksp(libs.hilt.compiler)
    ksp(libs.hilt.android.compiler)
    ksp(libs.androidx.hilt.compiler)
}