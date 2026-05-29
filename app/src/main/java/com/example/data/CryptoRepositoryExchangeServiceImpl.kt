package com.example.data

import com.example.domain.CryptoExchangeRepository
import com.example.domain.Money
import com.revolut.interview.data.CryptoExchangeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CryptoExchangeRepositoryImpl(
    private val service: CryptoExchangeService
) : CryptoExchangeRepository {

    override suspend fun buyCrypto(amount: Money): Result<Unit> {
        // Senior fix: Жесткое переключение на фоновый поток,
        // чтобы ensureNotMainThread() не выкинул краш
        return withContext(Dispatchers.IO) {
            try {
                // Senior fix: Маппинг Domain Entity -> Data DTO
                val dto = MoneyDto(
                    amount = amount.amount,
                    currency = amount.currency
                )

                // Выполняем сетевой запрос
                service.exchange(dto)

                // Если метод не выкинул исключение, возвращаем успех
                Result.success(Unit)
            } catch (e: Exception) {
                // Senior fix: Перехватываем 50% ServerException
                // и заворачиваем в безопасный Result для ViewModel
                Result.failure(e)
            }
        }
    }
}