rootProject.name = "revanced-patches-template"

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/revanced/registry")
            credentials {
                username = providers.gradleProperty("gpr.user").orNull ?: System.getenv("GPR_ACTOR")
                password = providers.gradleProperty("gpr.key").orNull ?: System.getenv("GPR_KEY")
            }
        }
    }
}

plugins {
    id("app.revanced.patches") version "1.0.0-dev.5"
}
