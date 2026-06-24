package com.example.presentation

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.revolut.interview.R

class MainActivity : AppCompatActivity() {

    private val button: Button by lazy { findViewById(R.id.button) }

    private val input: EditText by lazy { findViewById(R.id.input) }

    private val viewModel: CryptoExchangeViewModel by viewModels { CryptoExchangeViewModel.ViewModelFactory }

    private val errorBottomSheet by lazy { BottomSheetDialog(this) }

    private val successBottomSheet by lazy { BottomSheetDialog(this) }

    private val loadingBottomSheet by lazy { BottomSheetDialog(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        successBottomSheet.setContentView(R.layout.dialog_success)
        errorBottomSheet.setContentView(R.layout.dialog_error)
        loadingBottomSheet.setContentView(R.layout.dialog_loading)
        loadingBottomSheet.setCancelable(false)

        input.doOnTextChanged { text, _, _, _ -> viewModel.onAmountChanged(text) }
    }
}
