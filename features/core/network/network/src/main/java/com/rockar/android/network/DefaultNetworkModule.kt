package com.rockar.android.network

import com.rockar.android.network.api.Network
import dagger.BindsOptionalOf
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DefaultNetworkModule {

    @BindsOptionalOf
    abstract fun optionalImpl(): Network<Unit>
}
