package com.example.domain

interface CryptoExchangeRepository {
    suspend fun buyCrypto(amount: Money): Result<Unit>
}