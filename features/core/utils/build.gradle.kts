import com.rockar.android.marvelapp.dependencies.ProjectDependencies

plugins {
    id("com.rockar.android.plugins.feature")
}

android {
    namespace = "com.rockar.utils"
}

dependencies {

    implementation(ProjectDependencies.coreKtx)
    implementation(ProjectDependencies.appCompat)
    implementation(ProjectDependencies.googleMaterial)
}
