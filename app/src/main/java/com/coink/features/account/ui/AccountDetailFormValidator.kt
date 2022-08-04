package com.coink.features.account.ui

import android.content.res.Resources
import android.util.Patterns
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.coink.R

class AccountDetailFormValidator constructor(private val resource: Resources) {

    val emailAddress = MutableLiveData("")
    val emailAddressError = MutableLiveData("")
    val confirmEmailAddress = MutableLiveData("")
    val confirmEmailAddressError = MutableLiveData("")

    val birthDate = MutableLiveData("")
    val birthDateError = MutableLiveData("")
    val expeditionDate = MutableLiveData("")
    val expeditionDateError = MutableLiveData("")

    val gender = MutableLiveData("")
    val genderError = MutableLiveData("")
    val documentType = MutableLiveData("")
    val documentTypeError = MutableLiveData("")
    val documentNumber = MutableLiveData("")
    val documentNumberError = MutableLiveData("")

    val securityPin = MutableLiveData("")
    val securityPinError = MutableLiveData("")
    val confirmSecurityPin = MutableLiveData("")
    val confirmSecurityPinError = MutableLiveData("")

    val valid = MediatorLiveData<Boolean>().apply {
        addSource(documentType) {
            documentTypeError.value = getEmptyError(it)
            value = isFormValid()
        }
        addSource(documentNumber) {
            documentNumberError.value = getEmptyError(it)
            value = isFormValid()
        }
        addSource(expeditionDate) {
            expeditionDateError.value = getEmptyError(it)
            value = isFormValid()
        }
        addSource(birthDate) {
            birthDateError.value = getEmptyError(it)
            value = isFormValid()
        }
        addSource(gender) {
            genderError.value = getEmptyError(it)
            value = isFormValid()
        }
        addSource(emailAddress) {
            emailErrorHandler(it)
            value = isFormValid()
        }
        addSource(confirmEmailAddress) {
            confirmEmailErrorHandler(it)
            value = isFormValid()
        }
        addSource(securityPin) {
            pinErrorHandler(it)
            value = isFormValid()
        }
        addSource(confirmSecurityPin) {
            confirmPinErrorHandler(it)
            value = isFormValid()
        }
    }

    fun isFormValid(): Boolean {
        return documentTypeError.safeValue().isEmpty() &&
                documentNumberError.safeValue().isEmpty() &&
                expeditionDateError.safeValue().isEmpty() && birthDateError.safeValue().isEmpty() &&
                genderError.safeValue().isEmpty() && emailAddressError.safeValue().isEmpty() &&
                confirmEmailAddressError.safeValue().isEmpty() &&
                securityPinError.safeValue().isEmpty() &&
                confirmSecurityPinError.safeValue().isEmpty()
    }

    private fun emailErrorHandler(email: String) {
        val validation = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        if (!validation) {
            emailAddressError.value = resource.getString(R.string.account_detail_screen_email_error)
        } else {
            emailAddressError.value = ""
        }
    }

    private fun confirmEmailErrorHandler(email: String) {
        val validation = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        confirmEmailAddressError.value = when {
            !validation -> resource.getString(R.string.account_detail_screen_email_error)
            email != emailAddress.safeValue() -> resource.getString(R.string.account_detail_screen_email_confirm_error)
            else -> ""
        }
    }

    private fun pinErrorHandler(value: String) {
        securityPinError.value = if (value.length < MINIMUM_LENGTH) {
            resource.getString(R.string.account_detail_screen_pin_error)
        } else {
            ""
        }
    }

    private fun confirmPinErrorHandler(value: String) {
        confirmSecurityPinError.value = when {
            value.length < MINIMUM_LENGTH -> resource.getString(R.string.account_detail_screen_pin_error)
            value != securityPin.safeValue() -> resource.getString(R.string.account_detail_screen_pin_confirm_error)
            else -> ""
        }
    }

    private fun getEmptyError(value: String): String {
        return if (value.isEmpty()) {
            resource.getString(R.string.account_detail_screen_empty_error)
        } else {
            ""
        }
    }

    companion object {
        private const val MINIMUM_LENGTH = 4
    }
}

fun MutableLiveData<String>.safeValue(): String = this.value.orEmpty()
