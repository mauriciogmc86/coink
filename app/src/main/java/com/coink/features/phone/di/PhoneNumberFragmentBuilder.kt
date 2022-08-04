package com.coink.features.phone.di

import androidx.lifecycle.ViewModel
import com.coink.di.scope.FragmentScope
import com.coink.di.scope.ViewModelKey
import com.coink.features.phone.ui.PhoneNumberFragment
import com.coink.features.phone.ui.PhoneNumberViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class PhoneNumberFragmentBuilder {

    @FragmentScope
    @ContributesAndroidInjector(modules = [PhoneNumberProvider::class])
    abstract fun bindPhoneNumberFragment(): PhoneNumberFragment
}

@Module
class PhoneNumberProvider {
    @IntoMap
    @Provides
    @ViewModelKey(PhoneNumberViewModel::class)
    fun provideViewModel(): ViewModel {
        return PhoneNumberViewModel()
    }
}