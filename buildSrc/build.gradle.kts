plugins {
    id("org.jetbrains.kotlin.jvm") version "1.9.0"
    id("java-gradle-plugin")
    `kotlin-dsl`
}

sourceSets.main {
    java.srcDirs("src/main/kotlin")
}

dependencies {
    implementation("com.android.tools.build:gradle:8.1.1")
    implementation(kotlin("gradle-plugin:1.9.0"))
}

gradlePlugin {
    plugins {
        create("androidLibraryConfiguration") {
            id = "com.rockar.android.plugins.feature"
            implementationClass = "com.rockar.android.marvelapp.plugins.MarvelFeaturePlugin"
        }
        create("validateChangesPlugin") {
            id = "com.rockar.android.plugins.verify"
            implementationClass = "com.rockar.android.marvelapp.plugins.VerifyChangesPlugin"
        }
        create("checkAndFormatKotlinCodePlugin") {
            id = "com.rockar.android.plugins.format"
            implementationClass = "com.rockar.android.marvelapp.plugins.FormatCodePlugin"
        }
        create("checkCodeCoveragePlugin") {
            id = "com.rockar.android.plugins.coverage"
            implementationClass = "com.rockar.android.marvelapp.plugins.CodeCoveragePlugin"
        }
    }
}
