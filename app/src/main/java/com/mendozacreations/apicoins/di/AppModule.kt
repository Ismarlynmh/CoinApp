package com.mendozacreations.apicoins.di

import com.mendozacreations.apicoins.data.remote.CoinAPI
import com.mendozacreations.apicoins.data.remote.repository.CoinRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideCoinApi(moshi: Moshi): CoinAPI {
        return Retrofit.Builder()
            .baseUrl("http://ismarlynamh.somee.com")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(CoinAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(coinAPI: CoinAPI): CoinRepository {
        return CoinRepository(coinAPI)
    }
}