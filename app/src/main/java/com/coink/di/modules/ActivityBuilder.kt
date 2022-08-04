package com.coink.di.modules

import com.coink.di.scope.ActivityScope
import com.coink.features.MainActivity
import com.coink.features.account.di.AccountDetailFragmentBuilder
import com.coink.features.end.di.EndFragmentBuilder
import com.coink.features.onboarding.di.OnboardingFragmentBuilder
import com.coink.features.phone.di.PhoneNumberFragmentBuilder
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(
        modules = [
            EndFragmentBuilder::class,
            OnboardingFragmentBuilder::class,
            PhoneNumberFragmentBuilder::class,
            AccountDetailFragmentBuilder::class,
        ]
    )
    @ActivityScope
    abstract fun bindMainActivity(): MainActivity
}