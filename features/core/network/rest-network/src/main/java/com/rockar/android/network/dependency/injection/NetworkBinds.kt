package com.rockar.android.network.dependency.injection

import com.rockar.android.network.api.Network
import com.rockar.android.network.implementation.RestApiNetwork
import com.rockar.android.network.interceptors.AuthenticationInterceptor
import com.rockar.android.network.service.MarvelService
import com.rockar.utils.coverage.ExcludeClassFromJacocoGeneratedReport
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor

@Module
@InstallIn(SingletonComponent::class)
@ExcludeClassFromJacocoGeneratedReport
abstract class NetworkBinds {

    @Binds
    abstract fun bindsNetworkComponent(impl: RestApiNetwork): Network<MarvelService>

    @Binds
    @AuthInterceptor
    abstract fun bindsAuthInterceptor(impl: AuthenticationInterceptor): Interceptor
}
