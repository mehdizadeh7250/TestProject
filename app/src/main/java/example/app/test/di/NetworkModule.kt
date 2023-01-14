package example.app.test.di

import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import example.app.test.BuildConfig
import example.app.test.R
import example.app.test.data.repository.GetRemoteRestaurantListRepositoryImpl
import example.app.test.data.source.remote.HeaderInterceptor
import example.app.test.data.source.remote.services.ApiServices
import example.app.test.domain.repository.ErrorHandler
import example.app.test.domain.repository.GetRemoteRestaurantListRepository
import example.app.test.utils.flowConverter.FlowCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideHeaderInterceptor() = HeaderInterceptor()

    @Provides
    @Singleton
    fun provideOkHttpClient(headerInterceptor: HeaderInterceptor): OkHttpClient {
        val client = OkHttpClient.Builder()
            .readTimeout(40, TimeUnit.SECONDS)
            .writeTimeout(40, TimeUnit.SECONDS)
            .connectTimeout(40, TimeUnit.SECONDS)
            .addInterceptor(headerInterceptor)
        if (BuildConfig.DEBUG) {
            client.addNetworkInterceptor(StethoInterceptor())
            val logger =
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
            client.addInterceptor(logger)
        }
        return client.build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        @ApplicationContext context: Context,
        client: OkHttpClient,
        moshi: Moshi
    ): Retrofit =
        Retrofit.Builder()
            .addCallAdapterFactory(FlowCallAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .baseUrl(context.getString(R.string.core_url)).build()

    @Provides
    fun provideApiServices(retrofit: Retrofit): ApiServices =
        retrofit.create(ApiServices::class.java)

    @Provides
    fun provideRemoteRestaurantList(
        apiServices: ApiServices,
        errorHandler: ErrorHandler
    ): GetRemoteRestaurantListRepository = GetRemoteRestaurantListRepositoryImpl(apiServices, errorHandler)
}
