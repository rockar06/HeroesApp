import com.rockar.android.marvelapp.dependencies.NetworkDependencies
import com.rockar.android.marvelapp.dependencies.TestDependencies

plugins {
    id("com.rockar.android.plugins.feature")
}

android {
    namespace = "com.rockar.android.network"
}

dependencies {
    implementation(project(":network"))

    implementation(NetworkDependencies.retrofit)
    implementation(NetworkDependencies.moshiConverter)
    implementation(NetworkDependencies.okHttpClient)
    implementation(NetworkDependencies.logginInterceptor)
    testImplementation(TestDependencies.jUnit)
    testImplementation(TestDependencies.mockkLibrary)
    testImplementation(TestDependencies.truthLibrary)
}
