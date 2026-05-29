package com.example.data

import com.example.domain.Money

interface IdempotencyKeyCache {
    fun put(idempotencyKey: String, amount: Money)
    fun get(idempotencyKey: String): Money?
}

class IdempotencyKeyCacheImpl : IdempotencyKeyCache {

    var cache = mutableListOf<Pair<String, Money>>()

    override fun put(idempotencyKey: String, amount: Money) {
        cache.removeAll { it.first == idempotencyKey }
        cache.add(Pair(first = idempotencyKey, second = amount))
    }

    override fun get(idempotencyKey: String): Money? {
        return cache.find { it.first == idempotencyKey }?.second
    }
}