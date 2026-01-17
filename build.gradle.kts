import java.time.LocalDate
import java.time.temporal.WeekFields
import java.util.Locale

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    id("com.google.dagger.hilt.android") version "2.57.2" apply false
    id("com.google.devtools.ksp") version "2.2.20-2.0.4" apply false
    id("com.google.gms.google-services") version "4.4.4" apply false
    id("com.google.firebase.crashlytics") version "3.0.6" apply false
    id("com.google.firebase.firebase-perf") version "2.0.2" apply false
}

buildscript {

}

val globalVersion = "2"
val tdLibVersion = "1.8.55"

val now: LocalDate = LocalDate.now()
val year = now.year
val month = now.monthValue
val week = now.get(WeekFields.of(Locale.US).weekOfWeekBasedYear())
val beautifulBuildNumber = String.format("%02d%02d", year-2000, week)
val buildNumber = year*10000 + month*100 + week
val fullAppVersion = "$globalVersion.$tdLibVersion.$beautifulBuildNumber"

extra.apply {
    set("versionName", fullAppVersion)
    set("versionCode", buildNumber)
}