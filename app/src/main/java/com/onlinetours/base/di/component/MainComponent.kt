package com.onlinetours.base.di.component

import android.app.Application
import android.content.Context
import com.onlinetours.base.di.module.DataModule
import com.onlinetours.base.di.module.DomainModule
import com.onlinetours.base.di.module.NavigationModule
import com.onlinetours.ui.MainActivity
import com.onlinetours.ui.search.SearchFragment
import com.onlinetours.ui.search.SearchViewModel
import com.onlinetours.ui.search.dailog.SheetListCityFromDialog
import com.onlinetours.ui.search.dailog.SheetListCityToDialog
import com.onlinetours.ui.tours.ListTourFragment
import com.onlinetours.ui.tours.ListToursViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DataModule::class,
        DomainModule::class,
        NavigationModule::class
    ]
)
interface MainComponent {


    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun context(context: Context): Builder
        fun build(): MainComponent
    }

    fun inject(activity: MainActivity)

    fun inject(fragment: SearchFragment)

    fun inject(viewModel: SearchViewModel)

    fun inject(fragment: SheetListCityFromDialog)

    fun inject(fragment: SheetListCityToDialog)

    fun inject(fragment: ListTourFragment)

    fun inject(fragment: ListToursViewModel)


}