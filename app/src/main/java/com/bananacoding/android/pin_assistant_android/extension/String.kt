package com.bananacoding.android.pin_assistant_android.extension

import android.util.Patterns

fun String.Companion.empty() = ""
fun String.Companion.space() = " "

fun String.isValidEmail(): Boolean = this.isNotEmpty() &&
        Patterns.EMAIL_ADDRESS.matcher(this).matches()