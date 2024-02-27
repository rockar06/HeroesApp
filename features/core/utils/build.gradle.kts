import com.rockar.android.marvelapp.dependencies.GradleConfigVersions
import com.rockar.android.marvelapp.dependencies.ProjectDependencies

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.rockar.utils"
    compileSdk = GradleConfigVersions.compileSdk

    defaultConfig {
        minSdk = GradleConfigVersions.minSdk

        testInstrumentationRunner = ProjectDependencies.testInstrumentationRunner
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {

    implementation(ProjectDependencies.coreKtx)
    implementation(ProjectDependencies.appCompat)
    implementation(ProjectDependencies.googleMaterial)
}
