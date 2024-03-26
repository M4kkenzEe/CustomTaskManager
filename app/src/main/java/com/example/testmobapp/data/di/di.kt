package com.example.testmobapp.data.di

import com.example.testmobapp.data.repository.TaskRepositoryImpl
import com.example.testmobapp.data.room.TaskDatabase
import com.example.testmobapp.domain.interactor.TaskInteractor
import com.example.testmobapp.domain.repository.TaskRepository
import com.example.testmobapp.presentation.viewmodel.TableViewModel
import com.example.testmobapp.presentation.viewmodel.TimerViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { TaskDatabase.getDatabase(androidContext()).taskDao() }

    single<TaskRepository> { TaskRepositoryImpl(taskDao = get()) }
    factory { TaskInteractor(taskRepository = get()) }

    viewModel { TableViewModel(taskInteractor = get()) }

    viewModel { TimerViewModel() }


//    single<UserUtilsRepository> {
//        UserUtilsRepositoryImpl(
//            jwtService = get(),
//            userInteractor = get()
//        )
//    }
//    factory { UserUtilsInteractor(utilsRepository = get()) }
//
//    single<UserRepository> { UserRepositoryImpl(userDao = get()) }
//    factory { UserInteractor(userRepository = get()) }
//
//    viewModel { MainViewModel(context = androidContext(), userInteractor = get()) }
}