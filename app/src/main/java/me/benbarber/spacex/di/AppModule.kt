package me.benbarber.spacex.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import me.benbarber.spacex.data.api.SpacexApiService
import me.benbarber.spacex.data.repository.ILaunchesRepository
import me.benbarber.spacex.data.repository.LaunchesRepository
import me.benbarber.spacex.data.source.IRemoteDataSource
import me.benbarber.spacex.data.source.RemoteDataSource
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideOkHttpClient() = OkHttpClient.Builder().build()

    @Provides
    fun provideJson(): Json = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    @Provides
    fun provideJsonConverter(json: Json): Converter.Factory {
        return json.asConverterFactory("application/json".toMediaType())
    }

    @Provides
    fun provideRetrofitClient(
        okHttpClient: OkHttpClient,
        jsonConverter: Converter.Factory,
    ) = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(jsonConverter)
        .baseUrl("https://api.spacexdata.com/")
        .build()

    @Provides
    fun provideSpacexApiService(
        retrofit: Retrofit
    ): SpacexApiService = retrofit.create()

    @Provides
    fun RemoteDataSource.bindRemoteDataSource() : IRemoteDataSource = this

    @Provides
    fun LaunchesRepository.bindLaunchesRepository() : ILaunchesRepository = this
}