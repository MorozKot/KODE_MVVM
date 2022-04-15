package android.kode.presentation.viewModels

import android.content.ContentValues
import android.kode.presentation.GetUsersResult
import android.kode.domain.useCase.UsersUseCase
import android.kode.presentation.ScreenStates
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import kotlinx.coroutines.launch

/**
 *@author Moroz V.A. on 13.03.2022
 **/

class UsersViewModel(private val usersUseCase: UsersUseCase) : ViewModel() {

    protected val _screenLoadingState = MutableLiveData<ScreenStates>(ScreenStates.CriticalError)
    val screenLoadingState: LiveData<ScreenStates> = _screenLoadingState

    val getLocalUsers = usersUseCase.getLocalUsers()

    fun getUsers() = viewModelScope.launch {

        Log.d(ContentValues.TAG, "UsersViewModel getUsers")

        when (usersUseCase.startGetUsers()) {

            is GetUsersResult.Success -> {
                Log.d(ContentValues.TAG, "UsersViewModel GetUsersResult.Success")
            _screenLoadingState.postValue(ScreenStates.Success)
            }

            is GetUsersResult.ConnectionError -> {
                Log.d(ContentValues.TAG, "UsersViewModel GetUsersResult.ConnectionError")
             _screenLoadingState.postValue(ScreenStates.CriticalError)
            }

            is GetUsersResult.ServerError -> {
                Log.d(ContentValues.TAG, "UsersViewModel GetUsersResult.ServerError")
                _screenLoadingState.postValue(ScreenStates.CriticalError)
            }

            is GetUsersResult.EnqueueError -> {
                Log.d(ContentValues.TAG, "UsersViewModel GetUsersResult.EnqueueError")
                _screenLoadingState.postValue(ScreenStates.CriticalError)
            }
        }
    }
}