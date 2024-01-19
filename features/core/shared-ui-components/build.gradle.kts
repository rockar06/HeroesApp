import com.rockar.android.marvelapp.dependencies.ProjectDependencies
import com.rockar.android.marvelapp.dependencies.TestDependencies

plugins {
    id("com.rockar.android.plugins.feature")
}

android {
    namespace = "com.rockar.shared.ui.components"
}

dependencies {

    implementation(ProjectDependencies.coreKtx)
    implementation(ProjectDependencies.appCompat)
    implementation(ProjectDependencies.googleMaterial)
    testImplementation(TestDependencies.jUnit)
    androidTestImplementation(TestDependencies.jUnitInstrumented)
    androidTestImplementation(TestDependencies.espressoCore)
}
