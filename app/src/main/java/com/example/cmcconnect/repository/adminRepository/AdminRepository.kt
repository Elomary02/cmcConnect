package com.example.cmcconnect.repository.adminRepository

import com.example.cmcconnect.model.FiliereDto
import com.example.cmcconnect.model.GroupeDto
import com.example.cmcconnect.model.JustifWithStudent
import com.example.cmcconnect.model.RequestWithStudent
import com.example.cmcconnect.model.StudentDto
import com.example.cmcconnect.model.StudentRequestForAdminReplyToPost

interface AdminRepository {
    suspend fun getFilieresByPoleId(idPole: Int):List<FiliereDto>
    suspend fun getGroupsByFiliereId(idFiliere: Int): List<GroupeDto>
    suspend fun getStudentsByGroupId(idGroup: Int): List<StudentDto>
    suspend fun loadJustifsForAdmin(idAdmin: Int): List<JustifWithStudent>
    suspend fun loadStudentRequestsForAdmin(idAdmin: Int): List<RequestWithStudent>
    suspend fun adminReplyToStudent(reply: StudentRequestForAdminReplyToPost): Boolean
}