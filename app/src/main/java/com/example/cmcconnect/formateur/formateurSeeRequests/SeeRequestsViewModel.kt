package com.example.cmcconnect.formateur.formateurSeeRequests

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cmcconnect.model.RequestDto
import com.example.cmcconnect.model.RequestWithStudent
import com.example.cmcconnect.model.StudentDto
import com.example.cmcconnect.repository.teacherRepository.TeacherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeeRequestsViewModel @Inject constructor(private val teacherRepository: TeacherRepository) : ViewModel() {
    private val _requestsForTeacherLiveData = MutableLiveData<List<RequestWithStudent>>()
    val requestsForTeacherLiveData: LiveData<List<RequestWithStudent>> get() = _requestsForTeacherLiveData

    fun loadRequestsForTeacher(idTeacher: Int) {
        viewModelScope.launch {
            val result = teacherRepository.loadStudentRequestsForTeacher(idTeacher)
            _requestsForTeacherLiveData.postValue(result)
        }
    }
}


