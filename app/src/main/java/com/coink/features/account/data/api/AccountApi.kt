package com.coink.features.account.data.api

import com.coink.features.account.data.models.DocumentTypeModel
import com.coink.features.account.data.models.GenderModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AccountApi {

    @GET("signup/documentTypes")
    suspend fun getDocumentTypes(
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<List<DocumentTypeModel>>

    @GET("signup/genders")
    suspend fun getGenders(
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<List<GenderModel>>
}

private const val API_KEY = "030106"
