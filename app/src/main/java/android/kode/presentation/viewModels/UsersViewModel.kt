package android.kode.presentation.viewModels

import android.app.ProgressDialog.show
import android.content.ContentValues
import android.content.Context
import android.kode.data.models.UsersModel
import android.kode.domain.repository.GetUsersResult
import android.kode.domain.useCase.UsersUseCase
import android.kode.presentation.HomeFragment
import android.kode.presentation.ScreenStates
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 *@author Moroz V.A. on 13.03.2022
 **/

class UsersViewModel(private val usersUseCase: UsersUseCase) : ViewModel() {

    private val _screenLoadingState: MutableStateFlow<ScreenStates> = MutableStateFlow(ScreenStates.Success)
    val screenLoadingState: StateFlow<ScreenStates>
        get() = _screenLoadingState.asStateFlow()

    fun log() {
        Log.d(ContentValues.TAG, "UsersViewModel")
    }

    fun getUsers() = viewModelScope.launch {

        Log.d(ContentValues.TAG, "UsersViewModel getUsers")

        when (usersUseCase.start()) {
            is GetUsersResult.Success -> {
                Log.d(ContentValues.TAG, "UsersViewModel GetUsersResult.Success")
            _screenLoadingState.emit(ScreenStates.Success)
            }
            is GetUsersResult.ConnectionError -> {
                Log.d(ContentValues.TAG, "UsersViewModel GetUsersResult.ConnectionError")
             _screenLoadingState.emit(ScreenStates.CriticalError)
            }
            is GetUsersResult.ServerError -> {
                Log.d(ContentValues.TAG, "UsersViewModel GetUsersResult.ServerError")
                _screenLoadingState.emit(ScreenStates.CriticalError)
            }
            is GetUsersResult.EnqueueError -> {
                Log.d(ContentValues.TAG, "UsersViewModel GetUsersResult.EnqueueError")
                _screenLoadingState.emit(ScreenStates.CriticalError)
            }
        }
    }

    val loadUsers = usersUseCase.loadUsers()

}