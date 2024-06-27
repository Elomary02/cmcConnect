package com.example.cmcconnect.shared.User

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cmcconnect.model.UserDto
import com.example.cmcconnect.repository.sharedRepository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel() {
//    private val _userInfoLiveDate = MutableLiveData<UserDto>()
//    val userInfoLiveDate : LiveData<UserDto> = _userInfoLiveDate
//
//    fun getUserInfo(userEmail: String){
//        viewModelScope.launch {
//            val result = userRepository.getUserInfo(userEmail)
//            _userInfoLiveDate.postValue(result)
//        }
//    }
}