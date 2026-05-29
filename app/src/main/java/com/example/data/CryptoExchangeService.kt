package com.revolut.interview.data

import android.os.Looper
import android.os.NetworkOnMainThreadException
import com.example.data.MoneyDto
import kotlinx.coroutines.delay
import kotlin.math.absoluteValue
import kotlin.random.Random

// Исключение, которое мы будем перехватывать
class ServerException(val code: Int) : Exception("Server error with code: $code")

interface CryptoExchangeService {
    suspend fun exchange(amount: MoneyDto)
}

class RemoteCryptoExchangeServiceImpl : CryptoExchangeService {
    private val random = Random(seed = System.currentTimeMillis())

    override suspend fun exchange(amount: MoneyDto) {
        simulateNetworkDelayCoroutines()
        return performNetworkCall()
    }

    private fun performNetworkCall() {
        ensureNotMainThread()
        if (random.nextBoolean()) {
            throw ServerException(code = (random.nextInt() % 10000).absoluteValue)
        }
    }

    private suspend fun simulateNetworkDelayCoroutines() {
        delay(timeMillis = random.nextInt(from = 0, until = DELAY).toLong())
    }

    private fun ensureNotMainThread() {
        if (Thread.currentThread() == Looper.getMainLooper().thread) {
            throw NetworkOnMainThreadException()
        }
    }

    companion object {
        const val DELAY = 3000
    }
}