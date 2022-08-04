package com.coink.di.component

import android.app.Application
import com.coink.CoinkApp
import com.coink.di.modules.ActivityBuilder
import com.coink.di.modules.FactoryModule
import com.coink.di.modules.NetworkModule
import com.coink.features.MainActivity
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        FactoryModule::class,
        NetworkModule::class,
        ActivityBuilder::class,
        AndroidSupportInjectionModule::class
    ]
)

interface AppComponent : AndroidInjector<CoinkApp> {

    fun inject(app: MainActivity)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application): Builder
    }
}
