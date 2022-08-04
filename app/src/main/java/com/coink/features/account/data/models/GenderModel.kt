package com.coink.features.account.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenderModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
): Parcelable
