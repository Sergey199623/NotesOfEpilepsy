package com.belyakov.notesforepilepsy

import com.belyakov.notesforepilepsy.presentation.viewModel.SharedMainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object MainModules {

    fun create() = module {
        viewModel { SharedMainViewModel(get()) }
    }
}