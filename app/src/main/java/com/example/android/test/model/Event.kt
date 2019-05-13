package com.example.android.test.model

data class Event(
        val `public`: Boolean,
        val actor: Actor,
        val created_at: String,
        val id: String,
        val org: Org,
        val payload: Payload,
        val repo: Repo,
        val type: String
)