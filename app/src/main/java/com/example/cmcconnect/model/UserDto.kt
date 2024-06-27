package com.example.cmcconnect.model

import kotlinx.serialization.Serializable

@Serializable
data class UserDto (
    val id: Int,
    val id_student_fk: Int?,
    val id_teacher_fk: Int?,
    val id_admin_fk: Int?
): java.io.Serializable
