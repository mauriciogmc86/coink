package com.coink.features.account.data

import com.coink.features.account.data.api.AccountApi
import com.coink.features.account.data.models.DocumentTypeModel
import com.coink.features.account.data.models.GenderModel
import javax.inject.Inject

class AccountDetailRepository @Inject constructor(private val api: AccountApi) {

    suspend fun getGenders(): List<GenderModel> {
        return try {
            val response = api.getGenders().body()
            response ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getDocumentTypes(): List<DocumentTypeModel> {
        return try {
            val response = api.getDocumentTypes().body()
            response ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }
}