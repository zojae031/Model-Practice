package data.datasource.remote

import io.reactivex.Observable

interface RemoteDataSource {
    fun getList(): Observable<String>

}