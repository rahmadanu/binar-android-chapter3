package com.binar.words.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Word(
    val letter: String = "",
    val word: String = ""
    ) : Parcelable