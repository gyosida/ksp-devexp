plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "ksp-devexp"
include("before-ksp")
include("ksp-processors")
include("feature-flags-api")
