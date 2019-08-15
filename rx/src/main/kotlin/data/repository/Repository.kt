package data.repository

import io.reactivex.Observable

interface Repository {
    fun getList(): Observable<String>
}