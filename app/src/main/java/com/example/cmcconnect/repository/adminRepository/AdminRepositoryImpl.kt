package com.example.cmcconnect.repository.adminRepository

import com.example.cmcconnect.model.FiliereDto
import com.example.cmcconnect.model.GroupeDto
import com.example.cmcconnect.model.PoleDto
import com.example.cmcconnect.model.StudentDto
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AdminRepositoryImpl @Inject constructor(private val postgrest: Postgrest) : AdminRepository {
    override suspend fun getFilieresByPoleId(idPole: Int): List<FiliereDto> {
        return withContext(Dispatchers.IO) {
            val listFilieres = postgrest.from("filiere").select {
                filter {
                    eq("id_pole_fk", idPole)
                }
            }.decodeList<FiliereDto>()
            listFilieres
        }
    }

    override suspend fun getGroupsByFiliereId(idFiliere: Int): List<GroupeDto> {
        return withContext(Dispatchers.IO) {
            val listFilieres = postgrest.from("groupe").select {
                filter {
                    eq("id_filiere_fk", idFiliere)
                }
            }.decodeList<GroupeDto>()
            listFilieres

        }
    }

    override suspend fun getStudentsByGroupId(idGroup: Int): List<StudentDto> {
        return with(Dispatchers.IO){
            val lisStud = postgrest.from("student").select {
                filter {
                    eq("id_groupe_fk",idGroup)
                }
            }.decodeList<StudentDto>()
            lisStud
        }
    }

}