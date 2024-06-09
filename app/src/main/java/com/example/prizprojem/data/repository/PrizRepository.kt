package com.example.prizprojem.data.repository

import com.example.prizprojem.data.network.PrizApi

class PrizRepository(
    private val api: PrizApi
) : SafeApiRequest() {

    suspend fun getRules() = safeApiRequest { api.getRules() }
}