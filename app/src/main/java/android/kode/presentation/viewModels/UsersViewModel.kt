package android.kode.presentation.viewModels

import android.content.Context
import android.kode.domain.useCase.UsersUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 *@author Moroz V.A. on 13.03.2022
 **/

class UsersViewModel (private val usersUseCase: UsersUseCase): ViewModel() {

    val loadCoffee = usersUseCase.loadUsers()


    fun migration(context: Context) = viewModelScope.launch {
        usersUseCase.startMigration(context)

    }

}