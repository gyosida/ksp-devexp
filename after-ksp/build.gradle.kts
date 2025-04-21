plugins {
    kotlin("jvm")
    id("com.google.devtools.ksp") version "2.1.20-1.0.32"
}

group = "com.yosida.talks"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":feature-flags-api"))
//    implementation(project(":ksp-processors"))
    ksp(project(":ksp-processors"))
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
