package com.zerodev.crudretrofit.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String
) : Parcelable

