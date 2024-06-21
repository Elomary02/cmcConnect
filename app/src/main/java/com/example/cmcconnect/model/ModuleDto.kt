package com.example.cmcconnect.model

import kotlinx.serialization.Serializable

@Serializable
data class ModuleDto (
    val id: Int,
    val nam: String
): java.io.Serializable
