package com.example.myapplication


import android.app.Application
import dagger.hilt.android.testing.CustomTestApplication

@CustomTestApplication(MyApplication::class)
class MyTestApplication : Application()

