package com.coink.features.account.ui.models

import android.os.Parcelable
import com.coink.features.account.data.models.DocumentTypeModel
import com.coink.features.account.data.models.GenderModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class AccountData(
    val pin: String,
    val email: String,
    val gender: GenderModel,
    val birthDate: String,
    val phoneNumber: String,
    val documentType: DocumentTypeModel,
    val documentNumber: String,
    val documentExpeditionDate: String,
): Parcelable