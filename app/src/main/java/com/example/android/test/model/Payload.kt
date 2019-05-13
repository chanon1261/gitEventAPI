package com.example.android.test.model

data class Payload(
        val description: Any,
        val master_branch: String,
        val pusher_type: String,
        val ref: String,
        val ref_type: String
)