pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Mategram"
include(":mategram")
include(":mategram:mategram")
include(":mategram:presentation")
include(":mategram:domain")
include(":mategram:data")
include(":mategram:tdlib")
include(":mategram:utils")
include(":mategram:jni")
include(":mategram:player")
