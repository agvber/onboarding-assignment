pluginManagement {
    includeBuild("build-logic")
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
gradle.startParameter.excludedTaskNames.apply {
    add(":build-logic:convention:testClasses")
}

rootProject.name = "onboarding"
include(":app")
include(":core:domain")
include(":core:designsystem")
include(":core:data")
include(":feature:signup")
include(":feature:login")
include(":core:database")
include(":core:common")
