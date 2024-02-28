import com.android.marvelapp.dependencies.ProjectDependencies

plugins {
    id("com.android.plugins.feature")
}

android {
    namespace = "com.android.shared.ui.components"
}

dependencies {

    implementation(ProjectDependencies.coreKtx)
    implementation(ProjectDependencies.appCompat)
    implementation(ProjectDependencies.googleMaterial)
    testImplementation(ProjectDependencies.jUnit)
    androidTestImplementation(ProjectDependencies.jUnitInstrumented)
    androidTestImplementation(ProjectDependencies.espressoCore)
}
