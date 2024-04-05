package com.example.deltagames

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.deltagames.view.navigation.AppNavigation
import com.example.deltagames.viewModel.HomeViewModel

class MainActivity : ComponentActivity() {
    private val _viewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation(viewModel = _viewModel)
        }
    }
}