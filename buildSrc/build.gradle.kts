plugins {
    id("org.jetbrains.kotlin.jvm") version "1.9.10"
    id("java-gradle-plugin")
    id("org.gradle.kotlin.kotlin-dsl") version "4.0.6"
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

sourceSets.main {
    java.srcDirs("src/main/kotlin")
}

dependencies {
    implementation("com.android.tools.build:gradle:8.1.1")
    implementation(kotlin("gradle-plugin:1.9.10"))
}

gradlePlugin {
    plugins {
        create("androidLibraryConfiguration") {
            id = "com.rockar.android.plugins.feature"
            implementationClass = "com.rockar.android.marvelapp.plugins.MarvelFeaturePlugin"
        }
    }
}
