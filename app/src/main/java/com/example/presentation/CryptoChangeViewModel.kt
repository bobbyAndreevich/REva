package com.example.presentation

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.Currency
import com.example.domain.Money
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class CryptoExchangeViewModel() : ViewModel() {

    private val amountState = MutableStateFlow(value = Money(amount = 0, Currency.BTC))

    fun onAmountChanged(text: CharSequence?) {
        amountState.update {
            if (text.isNullOrEmpty() || !text.isDigitsOnly()) {
                Money(amount = 0L, Currency.BTC)
            } else {
                Money(amount = text.toString().toLong(), Currency.BTC)
            }
        }
    }

    object ViewModelFactory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return CryptoExchangeViewModel() as T
        }
    }
}