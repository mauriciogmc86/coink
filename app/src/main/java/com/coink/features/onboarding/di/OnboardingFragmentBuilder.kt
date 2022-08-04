package com.coink.features.onboarding.di

import com.coink.di.scope.FragmentScope
import com.coink.features.onboarding.ui.OnboardingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class OnboardingFragmentBuilder {

    @FragmentScope
    @ContributesAndroidInjector()
    abstract fun bindOnboardingFragment(): OnboardingFragment
}