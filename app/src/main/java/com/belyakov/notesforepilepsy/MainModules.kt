package com.belyakov.notesforepilepsy

import com.belyakov.notesforepilepsy.data.ReminderRepository
import com.belyakov.notesforepilepsy.data.ReminderRepositoryImpl
import com.belyakov.notesforepilepsy.presentation.viewModel.NotificationViewModel
import com.belyakov.notesforepilepsy.presentation.viewModel.ProfileViewModel
import com.belyakov.notesforepilepsy.presentation.viewModel.SharedMainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object MainModules {

    fun create() = module {
        viewModel { SharedMainViewModel(get()) }
        viewModel { ProfileViewModel(get()) }
        viewModel { NotificationViewModel(get()) }

        single<ReminderRepository> { ReminderRepositoryImpl(get()) }
    }
}