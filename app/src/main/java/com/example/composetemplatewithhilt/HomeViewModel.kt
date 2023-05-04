package com.example.composetemplatewithhilt

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composetemplatewithhilt.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val authRepository: AuthRepository
):ViewModel() {
    fun login(){
        viewModelScope.launch {
            authRepository.login("Shubham@htf.sa")
        }
    }
}