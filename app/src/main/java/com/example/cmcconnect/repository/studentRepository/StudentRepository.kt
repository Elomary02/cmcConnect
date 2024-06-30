package com.example.cmcconnect.repository.studentRepository

import com.example.cmcconnect.model.RessourceDto

interface StudentRepository {

    suspend fun getRecentResourcesForStudentGroup(idStudent: Int): List<RessourceDto>?
}