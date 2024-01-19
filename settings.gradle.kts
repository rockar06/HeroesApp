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

rootProject.name = "MarvelApp"
include(":app-phone")
project(":app-phone").projectDir = file("./apps/app-phone")
include(":shared-ui-components")
project(":shared-ui-components").projectDir = file("./features/core/shared-ui-components")
include(":utils")
project(":utils").projectDir = file("./features/core/utils")
