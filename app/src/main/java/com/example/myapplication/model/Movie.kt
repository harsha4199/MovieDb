package com.example.myapplication.model

data class Movie(val page: Int = 0,
                 val totalPages: Int = 0,
                 val results: List<ResultsItem>?,
                 val totalResults: Int = 0)