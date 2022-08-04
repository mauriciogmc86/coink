package com.coink.features.account.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DocumentTypeModel(
    @SerializedName("id") val id: Int,
    @SerializedName("notation") val notation: String,
    @SerializedName("description") val description: String,
): Parcelable
