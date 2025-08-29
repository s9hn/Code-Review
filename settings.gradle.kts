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

rootProject.name = "Search Library"
include(":app")
include(
    ":core:designsystem",
    ":core:router",
    ":core:network",
    ":core:database"
)
include(
    ":feature:main",
    ":feature:books-ui",
    ":feature:books-search",
    ":feature:books-favorite",
    ":feature:books-detail",
)
include(
    ":data:search",
    ":data:favorite",
)
include(
    ":domain:book",
    ":domain:search",
    ":domain:favorite",
)
