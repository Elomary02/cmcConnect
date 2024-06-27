package com.example.cmcconnect.repository.sharedRepository

import com.example.cmcconnect.model.EventDto
import com.example.cmcconnect.model.UserDto

interface EventRepository {
    suspend fun getEvents(): EventDto
}