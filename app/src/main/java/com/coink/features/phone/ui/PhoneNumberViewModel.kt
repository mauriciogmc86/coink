package com.coink.features.phone.ui

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class PhoneNumberViewModel @Inject constructor() : ViewModel(), DefaultLifecycleObserver {
    val phoneNumber = MutableLiveData("")
}