package android.kode.presentation.di

import android.kode.data.dataSource.UsersApiDataSource
import android.kode.data.dataSource.UsersDataSource
import android.kode.data.dataSourceImpl.UsersApiDataSourceImpl
import android.kode.data.dataSourceImpl.UsersDataSourceImpl
import android.kode.data.localDB.LocalDB
import android.kode.data.repositiry.UsersRepository
import android.kode.domain.repository.UsersCall
import android.kode.domain.useCase.UsersUseCase
import android.kode.presentation.viewModels.UsersViewModel
import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 *@author Moroz V.A. on 13.03.2022
 **/

val usersModule = module{

    single {
        Room.databaseBuilder(androidContext(), LocalDB::class.java,
            "localDB").build()
    }

    single { get<LocalDB>().usersDao }


    single<UsersDataSource> {
        UsersDataSourceImpl(
            get()
        )
    }

    single<UsersApiDataSource> {
        UsersApiDataSourceImpl(
            get()
        )
    }

    single<UsersCall> { UsersRepository(get(),get()) }

    single { UsersUseCase(get()) }

    viewModel { UsersViewModel(get()) }

}