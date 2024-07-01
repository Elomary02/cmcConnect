package com.example.cmcconnect.repository.studentRepository

import com.example.cmcconnect.model.CoursDto
import com.example.cmcconnect.model.ModuleDto
import com.example.cmcconnect.model.RessourceDto
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.postgrest.query.Order
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class StudentRepositoryImp @Inject constructor(private val postgrest: Postgrest) :
    StudentRepository {
    override suspend fun getModulesByGroupId(idGroup: Int): List<CoursDto> {
        return withContext(Dispatchers.IO) {
            val lisModuleRes =
                postgrest.from("cours")
                    .select(columns = Columns.list("id,groupe(id),module(id,name)")) {
                        filter {
                            eq("id_groupe_fk", idGroup)
                        }
                    }.decodeList<CoursDto>()
            lisModuleRes
        }
    }

    override suspend fun getResourcesByModuleId(idModule: Int): List<RessourceDto> {
        return withContext(Dispatchers.IO) {
            val listRes = postgrest.from("ressource").select {
                filter {
                    eq("id_module_fk", idModule)
                }
            }.decodeList<RessourceDto>()
            listRes
        }
    }

    override suspend fun getRecentResourcesByModuleId(idModule: Int): List<RessourceDto> {
        return withContext(Dispatchers.IO) {
            val listRes = postgrest.from("ressource").select {
                filter {
                    eq("id_module_fk", idModule)
                }
                limit(1)
                order("id", order = Order.DESCENDING)
            }.decodeList<RessourceDto>()
            listRes
        }
    }

    override suspend fun getRecentModulesByGroupId(idGroup: Int): List<CoursDto> {
        return withContext(Dispatchers.IO) {
            val lisModuleRes =
                postgrest.from("cours")
                    .select(columns = Columns.list("id,groupe(id),module(id,name)")) {
                        filter {
                            eq("id_groupe_fk", idGroup)
                        }
                        limit(4)
                        order("id", order = Order.DESCENDING)
                    }.decodeList<CoursDto>()
            lisModuleRes
        }
    }



}