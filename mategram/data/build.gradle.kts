import java.util.Properties

val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localProperties.load(localPropertiesFile.inputStream())
}

plugins {
    alias(libs.plugins.android.library)
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
}

kotlin {
    jvmToolchain(21)

    android {
        namespace = "com.xxcactussell.mategram.data"
        compileSdk = 36
        android.buildFeatures.buildConfig = true
        defaultConfig {
            minSdk = 31
            val apiId: String = localProperties.getProperty("TELEGRAM_API_ID")?: "0"
            val apiHash: String = localProperties.getProperty("TELEGRAM_API_HASH")?: "YOUR_API_HASH"
            val myVersionName = rootProject.extra["versionName"] as String
            buildConfigField("int", "TELEGRAM_API_ID", apiId)
            buildConfigField("String", "TELEGRAM_API_HASH", apiHash)
            buildConfigField("String", "APP_VERSION_STRING", "\"$myVersionName\"")
        }
    }
}

dependencies {
    implementation(project(":mategram:tdlib"))
    implementation(project(":mategram:utils"))
    implementation(project(":mategram:domain"))
    implementation(project(":mategram:player"))
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.androidx.core.ktx)
    implementation(libs.hilt.android)
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.androidx.media3.common.ktx)
    ksp(libs.hilt.compiler)
    ksp(libs.hilt.android.compiler)
    ksp(libs.androidx.hilt.compiler)
}