package android.kode.domain.repository

import android.kode.data.models.UsersModel

sealed class GetUsersResult(open val error: String? = null) {
    data class Success(override val error: String? = null) : GetUsersResult(error = error)
    data class ConnectionError(override val error: String? = null) : GetUsersResult(error = error)
    data class ServerError(override val error: String? = null) : GetUsersResult(error = error)
    data class EnqueueError(override val error: String? = null) : GetUsersResult(error = error)
}