plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly("com.android.tools.lint:lint-api:31.2.2")
}

tasks.jar {
    manifest {
        attributes("Lint-Registry-v2" to "com.android.rules.detector.registry.CustomIssueRegistry")
    }
}
