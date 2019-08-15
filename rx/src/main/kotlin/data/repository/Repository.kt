package data.repository

import io.reactivex.Single

interface Repository {
    fun getRemoteList() : Single<String>
}