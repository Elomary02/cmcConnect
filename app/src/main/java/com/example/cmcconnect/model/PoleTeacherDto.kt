package com.example.cmcconnect.model

import kotlinx.serialization.Serializable

@Serializable
data class PoleTeacherDto (
    val id: Int,
    val id_pole_fk: PoleDto,
    val id_teacher_fk: TeacherDto
): java.io.Serializable
