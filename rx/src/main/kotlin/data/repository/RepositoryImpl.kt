package data.repository

import data.datasource.remote.RemoteDataSource
import io.reactivex.Single

class RepositoryImpl private constructor(private val dataSource: RemoteDataSource) : Repository {

    override fun getRemoteList(): Single<String> {
        return dataSource.getList()
    }

    companion object {
        private var INSTANCE: RepositoryImpl? = null
        fun getInstance(dataSource: RemoteDataSource): RepositoryImpl {
            if (INSTANCE == null) {
                INSTANCE = RepositoryImpl(dataSource)
            }
            return INSTANCE!!
        }

    }
}