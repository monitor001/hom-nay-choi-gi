pluginManagement {
    repositories {
        google()
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

rootProject.name = "GauCon"

include(":app")
include(":core:common")
include(":core:designsystem")
include(":core:database")
include(":core:datastore")
include(":core:notifications")
include(":core:network")
include(":domain")
include(":content-seed")
include(":feature:onboarding")
include(":feature:today")
include(":feature:activity")
include(":feature:reminder")
