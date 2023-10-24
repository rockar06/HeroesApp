import com.rockar.android.marvelapp.dependencies.ProjectDependencies

plugins {
    id("com.rockar.android.plugins.feature")
}

android {
    namespace = "com.rockar.shared.ui.components"
}

dependencies {

    //implementation(ProjectDependencies.hiltAndroid)
    //implementation(ProjectDependencies.hiltCompiler)
    implementation(ProjectDependencies.coreKtx)
    implementation(ProjectDependencies.appCompat)
    implementation(ProjectDependencies.googleMaterial)
    testImplementation(ProjectDependencies.jUnit)
    androidTestImplementation(ProjectDependencies.jUnitInstrumented)
    androidTestImplementation(ProjectDependencies.espressoCore)
}
