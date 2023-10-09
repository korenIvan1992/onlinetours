package com.onlinetours.base.di.module

import android.content.Context
import com.onlinetours.data.datasource.local.LocalDataSource
import com.onlinetours.data.datasource.remoute.RemoteDataSource
import com.onlinetours.data.mapper.local.ModelMapperLocal
import com.onlinetours.data.mapper.service.ModelMapperService
import com.onlinetours.data.repository.MyRepositoryImpl
import com.onlinetours.domain.usecases.MyUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
 class DomainModule {


    @Provides
    @Singleton
    fun provideMapperService() = ModelMapperService()


    @Provides
    @Singleton
    fun provideMapperLocal() = ModelMapperLocal()

    @Provides
    @Singleton
    fun myUseCase(myRepository: MyRepositoryImpl) = MyUseCase(myRepository = myRepository)

    @Provides
    @Singleton
    fun provideImyRepository(
        context: Context,
        mapperService: ModelMapperService,
        mapperLocal: ModelMapperLocal,
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ) =
        MyRepositoryImpl(
            context,
            mapperService,
            mapperLocal,
            localDataSource,
            remoteDataSource
        )
}


