package com.belyakov.notesforepilepsy

import com.belyakov.notesforepilepsy.data.ReminderRepository
import com.belyakov.notesforepilepsy.data.ReminderRepositoryImpl
import com.belyakov.notesforepilepsy.presentation.viewModel.NotificationViewModel
import com.belyakov.notesforepilepsy.presentation.viewModel.ProfileViewModel
import com.belyakov.notesforepilepsy.presentation.viewModel.SharedMainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object MainModules {

    fun create(databaseUrl: String) = module {
        viewModel { SharedMainViewModel(get()) }
        viewModel { ProfileViewModel(databaseUrl) }
        viewModel { NotificationViewModel(get()) }

        single<ReminderRepository> { ReminderRepositoryImpl(get()) }
    }
}