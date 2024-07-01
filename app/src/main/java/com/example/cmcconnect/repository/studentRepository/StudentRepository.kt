package com.example.cmcconnect.repository.studentRepository

import com.example.cmcconnect.model.CoursDto
import com.example.cmcconnect.model.ModuleDto
import com.example.cmcconnect.model.RessourceDto

interface StudentRepository {
    suspend fun getModulesByGroupId(idGroup : Int) : List<CoursDto>
    suspend fun getResourcesByModuleId(idModule: Int) : List<RessourceDto>

    suspend fun getRecentResourcesByModuleId(idModule: Int): List<RessourceDto>
    suspend fun getRecentModulesByGroupId(idGroup: Int): List<CoursDto>

}