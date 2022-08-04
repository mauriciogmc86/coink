package com.coink.features.account.ui

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coink.features.account.data.AccountDetailRepository
import com.coink.features.account.data.models.DocumentTypeModel
import com.coink.features.account.data.models.GenderModel
import com.coink.features.account.ui.models.AccountData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AccountDetailViewModel constructor(
    val phoneNumber: String,
    val validator: AccountDetailFormValidator,
    private val repository: AccountDetailRepository
) : ViewModel(), DefaultLifecycleObserver {

    private var genderJob: Job? = null
    private var documentTypesJob: Job? = null

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _success = MutableLiveData<AccountData?>(null)
    val success: LiveData<AccountData?> = _success

    private val _genders = MutableLiveData<List<GenderModel>>()
    val genders: LiveData<List<GenderModel>> = _genders

    private val _documentTypes = MutableLiveData<List<DocumentTypeModel>>()
    val documentTypes: LiveData<List<DocumentTypeModel>> = _documentTypes

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        _loading.value = true
        gettingGenders()
        gettingDocumentTypes()
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        _success.value = null
    }

    override fun onCleared() {
        super.onCleared()
        genderJob?.cancel()
        genderJob = null
        documentTypesJob?.cancel()
        documentTypesJob = null
    }

    fun onConfirm() {
        val isValid = validator.isFormValid()
        if (isValid) {
            _success.value = AccountData(
                pin = validator.securityPin.safeValue(),
                email = validator.emailAddress.safeValue(),
                gender = genders.value!!.first { it.name == validator.gender.safeValue() },
                birthDate = validator.birthDate.safeValue(),
                phoneNumber = phoneNumber,
                documentType = documentTypes.value!!.first { it.description == validator.documentType.safeValue() },
                documentNumber = validator.documentNumber.safeValue(),
                documentExpeditionDate = validator.expeditionDate.safeValue()
            )
        }
    }

    private fun gettingGenders() {
        genderJob = CoroutineScope(Dispatchers.IO).launch {
            val items = repository.getGenders()
            withContext(Dispatchers.Main) {
                if (items.isNotEmpty()) {
                    _genders.value = items
                }
                if (_documentTypes.value?.isNotEmpty() == true) {
                    _loading.value = false
                }
            }
        }
    }

    private fun gettingDocumentTypes() {
        documentTypesJob = CoroutineScope(Dispatchers.IO).launch {
            val items = repository.getDocumentTypes()
            withContext(Dispatchers.Main) {
                if (items.isNotEmpty()) {
                    _documentTypes.value = items
                }
                if (_genders.value?.isNotEmpty() == true) {
                    _loading.value = false
                }
            }
        }
    }

}