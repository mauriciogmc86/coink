package com.coink.features.end.di

import com.coink.di.scope.FragmentScope
import com.coink.features.end.ui.EndFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class EndFragmentBuilder {

    @FragmentScope
    @ContributesAndroidInjector()
    abstract fun bindEndFragment(): EndFragment
}