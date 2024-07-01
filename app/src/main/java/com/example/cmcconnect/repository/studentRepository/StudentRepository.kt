package com.example.cmcconnect.repository.studentRepository

import com.example.cmcconnect.model.AdminDto
import com.example.cmcconnect.model.JustifToSend
import com.example.cmcconnect.model.RequestToSend
import com.example.cmcconnect.model.RequestTypeDto
import com.example.cmcconnect.model.RessourceDto
import com.example.cmcconnect.model.TeacherDto

interface StudentRepository {
    suspend fun getRecentResourcesForStudentGroup(idStudent: Int): List<RessourceDto>?
    suspend fun loadRequests(): List<RequestTypeDto>
    suspend fun loadTeachersForStudent(idStudent: Int): List<TeacherDto>
    suspend fun getStudentAdmin(idStudent: Int): AdminDto
    suspend fun sendRequest(request: RequestToSend): Boolean
    suspend fun sendJustif(justif: JustifToSend): Boolean
}