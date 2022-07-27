package com.example.myintentapp.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class person(
    val name: String?,
    val age: Int?,
    val email: String?,
    val city: String?
): Parcelable