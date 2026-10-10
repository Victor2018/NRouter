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

rootProject.name = "NRouter"
include(":app")
include(":neuro_router_core")
include(":neuro_router_annotation")
include(":neuro_router_compiler")
include(":lib_common")
include(":module_home")
include(":module_live")
include(":module_login")
include(":module_me")
include(":module_message")
