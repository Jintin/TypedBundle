import java.net.URI

pluginManagement {
    repositories {
        google()
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenLocal()
        mavenCentral()
//        maven {
//            url = URI("https://s01.oss.sonatype.org/service/local/repositories/iogithubjintin-1040/content/")
//        }
    }
}

rootProject.name = "TypedBundle"
include(":app")
include(":lib")
include(":processor")
project(":lib").name = "typed-bundle"
