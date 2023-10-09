package com.onlinetours.base.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.onlinetours.data.api.Api
import com.onlinetours.data.datasource.local.LocalDataSource
import com.onlinetours.data.datasource.remoute.RemoteDataSource
import com.onlinetours.data.db.dao.CityDao
import com.onlinetours.data.db.dao.CountryDao
import com.onlinetours.data.db.manager.DatabaseManager
import com.onlinetours.data.db.manager.PreferenceManager
import com.onlinetours.domain.utils.ConstantUtils
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun providesRoomDatabase(context: Context): DatabaseManager {
        return Room.databaseBuilder(
            context,
            DatabaseManager::class.java,
            "ONLINE_TOURS"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideCityDao(databaseManager: DatabaseManager): CityDao =
        databaseManager.cityDao()

    @Provides
    @Singleton
    fun provideCountryDao(databaseManager: DatabaseManager): CountryDao =
        databaseManager.countryDao()

    @Provides
    fun localDataSource(
        cityDao: CityDao,
        countryDao: CountryDao
    ): LocalDataSource =
        LocalDataSource(cityDao, countryDao)

    @Provides
    fun remouteDataSource(context: Context, api: Api): RemoteDataSource =
        RemoteDataSource(context, api)

    @Provides
    fun provideUserApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    @Provides
    @Singleton
    fun provideCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    private val logInterceptor: HttpLoggingInterceptor
        get() {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            return logging
        }

    // init {
    //        val locale = ConfigurationCompat.getLocales(mContext.resources.configuration).get(0)
    //        val localeString = locale.country
    //        val isGMU = mContext.packageName == "com.growmeup.android"
    //        val isGMUString = isGMU.toString()
    //        Timber.d(localeString)
    //        this.okBuilder.addInterceptor { chain ->
    //            val original = chain.request()
    //            val request = original.newBuilder()
    //                .method(original.method, original.body)
    //                .header("language", localeString)
    //                .header("isGMU", isGMUString)
    //                .build()
    //            chain.proceed(request)
    //        }
    //        this.okBuilder.connectTimeout(TIMEOUT_CONNECT.toLong(), TimeUnit.SECONDS)
    ////        this.okBuilder.addInterceptor(ChuckInterceptor(mContext))
    //        this.okBuilder.readTimeout(TIMEOUT_READ.toLong(), TimeUnit.SECONDS)
    //        this.okBuilder.cache(cache())
    //        this.okBuilder.addInterceptor(logInterceptor)
    //    }
    @Provides
    fun provideHttpClient(cache: Cache): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClientBuilder = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                    .method(original.method, original.body)
                    .build()
                chain.proceed(request)
            }
            .cache(cache)
            .addInterceptor(logInterceptor)

        return okHttpClientBuilder.build()
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }

    @Provides
    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {

        return Retrofit.Builder()
            .baseUrl(ConstantUtils.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(factory))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()

    }

    @Singleton
    @Provides
    fun providesPref(context: Context): PreferenceManager {
        return PreferenceManager(context)
    }
}