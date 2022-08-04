package com.coink.features.account.di

import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.navArgs
import com.coink.di.scope.FragmentScope
import com.coink.di.scope.ViewModelKey
import com.coink.features.account.data.AccountDetailRepository
import com.coink.features.account.data.api.AccountApi
import com.coink.features.account.ui.AccountDetailFormValidator
import com.coink.features.account.ui.AccountDetailFragment
import com.coink.features.account.ui.AccountDetailFragmentArgs
import com.coink.features.account.ui.AccountDetailViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import retrofit2.Retrofit

@Module
abstract class AccountDetailFragmentBuilder {

    @FragmentScope
    @ContributesAndroidInjector(modules = [AccountDetailProvider::class])
    abstract fun bindAccountDetailFragment(): AccountDetailFragment
}

@Module
object AccountDetailProvider {

    @Provides
    fun provideAccountApi(retrofit: Retrofit): AccountApi {
        return retrofit.create(AccountApi::class.java)
    }

    @Provides
    fun provideValidator(fragment: AccountDetailFragment): AccountDetailFormValidator {
        return AccountDetailFormValidator(fragment.resources)
    }

    @IntoMap
    @Provides
    @ViewModelKey(AccountDetailViewModel::class)
    fun provideViewModel(
        fragment: AccountDetailFragment,
        validator: AccountDetailFormValidator,
        repository: AccountDetailRepository
    ): ViewModel {
        val args: AccountDetailFragmentArgs by fragment.navArgs()
        return AccountDetailViewModel(args.phoneNumber, validator, repository)
    }
}