package data.datasource.remote

import io.reactivex.Single

interface RemoteDataSource {
    fun getList(): Single<String>

}