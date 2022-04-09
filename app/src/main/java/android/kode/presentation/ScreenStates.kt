package android.kode.presentation

/**
 *@author Moroz V.A. on 08.04.2022
 **/
sealed class ScreenStates{
    object Success: ScreenStates()
    object CriticalError: ScreenStates()
}
