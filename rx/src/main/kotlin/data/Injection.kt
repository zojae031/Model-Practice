package data

import data.datasource.remote.RemoteDataSource
import data.datasource.remote.RemoteDataSourceImpl

object Injection {
    fun getDataSource(): RemoteDataSource {
        return RemoteDataSourceImpl
    }
}