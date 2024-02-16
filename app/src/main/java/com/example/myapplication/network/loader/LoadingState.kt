package com.example.myapplication.network.loader

sealed class LoadingState {
    object Loading : LoadingState()
    object Loaded : LoadingState()
}
