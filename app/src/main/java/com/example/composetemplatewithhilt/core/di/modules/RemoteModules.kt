package com.example.composetemplatewithhilt.core.di.modules

import com.example.composetemplatewithhilt.BuildConfig
import com.example.composetemplatewithhilt.core.di.Qualifiers.AuthInterceptorOkHttpClient
import com.example.composetemplatewithhilt.data.dataStores.AuthDataStore
import com.example.composetemplatewithhilt.remote.NetworkInterceptor
import com.example.composetemplatewithhilt.remote.RetrofitServiceFactory
import com.example.composetemplatewithhilt.remote.dataStoreImpl.AuthDataStoreImpl
import com.example.composetemplatewithhilt.remote.services.AuthServices
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit


@Module
@InstallIn(SingletonComponent::class)
object RemoteModules {

    @AuthInterceptorOkHttpClient
    @Provides
    fun provideAuthInterceptorOkHttpClient(networkInterceptor: NetworkInterceptor) :OkHttpClient{
        val okHttpClient=OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS).addInterceptor(networkInterceptor)

        if (BuildConfig.DEBUG)
            okHttpClient.addInterceptor(HttpLoggingInterceptor().apply { this.level = HttpLoggingInterceptor.Level.BODY })

        return okHttpClient.build()
    }

    @Provides
    fun provideRetrofitFactory(@AuthInterceptorOkHttpClient okHttpClient: OkHttpClient):RetrofitServiceFactory{
        return RetrofitServiceFactory(okHttpClient)
    }



}
@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModels{

    companion object{
        @Provides
        fun provideAuthService(
            retrofitServiceFactory: RetrofitServiceFactory
        ):AuthServices{
            return retrofitServiceFactory.prepareService(AuthServices::class.java)
        }
    }

    @Binds
    abstract fun bindAuthRemoteDataSource(authRemoteDataStoreImpl: AuthDataStoreImpl): AuthDataStore
}