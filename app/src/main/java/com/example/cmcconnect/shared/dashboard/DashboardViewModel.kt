package com.example.cmcconnect.shared.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cmcconnect.model.EventDto
import com.example.cmcconnect.model.RessourceDto
import com.example.cmcconnect.repository.sharedRepository.EventRepository
import com.example.cmcconnect.repository.studentRepository.StudentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val eventRepository: EventRepository, private val studentRepository: StudentRepository): ViewModel() {
    private val _recentEventsLiveData = MutableLiveData<List<EventDto>?>()
    val recentEventsLiveData : LiveData<List<EventDto>?> = _recentEventsLiveData

    private val _studentResources = MutableLiveData<List<RessourceDto>?>()
    val studentResources: LiveData<List<RessourceDto>?> = _studentResources

    fun getRecentEvents() {
        viewModelScope.launch {
            val recentEvents = eventRepository.getRecentEvents()
            _recentEventsLiveData.postValue(recentEvents)
        }
    }

    fun getStudentResources(idStudent: Int) {
        viewModelScope.launch {
            val studentResources = studentRepository.getRecentResourcesForStudentGroup(idStudent)
            _studentResources.postValue(studentResources)
        }
    }
}