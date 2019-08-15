package data.repository

import io.reactivex.Observable

interface Repository {
    fun getRemoteList(): Observable<String>
}