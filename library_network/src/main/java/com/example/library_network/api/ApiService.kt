package com.example.library_network.api

import retrofit2.http.GET

interface ApiService {

    @GET
    fun connectTest()

}