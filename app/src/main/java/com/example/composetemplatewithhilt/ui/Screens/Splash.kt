package com.example.composetemplatewithhilt.ui.Screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composetemplatewithhilt.HomeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

class Splash() {
    @Composable
    fun SplashScreen(homeViewModel: HomeViewModel= hiltViewModel()){

    }
}