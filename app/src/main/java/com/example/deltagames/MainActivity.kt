package com.example.deltagames

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.deltagames.util.ContextProvider
import com.example.deltagames.view.navigation.AppNavigation
import com.example.deltagames.viewModel.HomeViewModel

class MainActivity : ComponentActivity(), ContextProvider {
    private val _viewModel: HomeViewModel by viewModels()
    override val context: Context
        get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation(viewModel = _viewModel, this)
        }
    }
}