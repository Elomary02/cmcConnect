package com.example.cmcconnect.repository.adminRepository

import com.example.cmcconnect.model.FiliereDto
import com.example.cmcconnect.model.GroupeDto
import com.example.cmcconnect.model.StudentDto

interface AdminRepository {
    suspend fun getFilieresByPoleId(idPole: Int):List<FiliereDto>
    suspend fun getGroupsByFiliereId(idFiliere: Int): List<GroupeDto>
    suspend fun getStudentsByGroupId(idGroup: Int): List<StudentDto>
}