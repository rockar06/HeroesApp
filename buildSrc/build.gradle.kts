plugins {
    `kotlin-dsl`
    kotlin("plugin.serialization") version "1.9.0"
    kotlin("jvm") version "1.9.0"
    id("java-gradle-plugin")
}

sourceSets.main {
    java.srcDirs("src/main/kotlin")
}

dependencies {
    implementation("com.android.tools.build:gradle:8.1.1")
    implementation(kotlin("gradle-plugin:1.9.0"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
}

gradlePlugin {
    plugins {
        create("androidLibraryConfiguration") {
            id = "com.android.plugins.feature"
            implementationClass = "com.android.marvelapp.plugins.MarvelFeaturePlugin"
        }
        create("validateChangesPlugin") {
            id = "com.android.plugins.verify"
            implementationClass = "com.android.marvelapp.plugins.VerifyChangesPlugin"
        }
        create("checkAndFormatKotlinCodePlugin") {
            id = "com.android.plugins.format"
            implementationClass = "com.android.marvelapp.plugins.FormatCodePlugin"
        }
        create("checkCodeCoveragePlugin") {
            id = "com.android.plugins.coverage"
            implementationClass = "com.android.marvelapp.plugins.CodeCoveragePlugin"
        }
    }
}
