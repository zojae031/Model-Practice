package data.repository

import data.datasource.remote.RemoteDataSource
import io.reactivex.Observable

/**
 * dataSource에서 리스트를 받아와 태그를 제거하여 반환한다.
 * Singleton으로 구성되어있으며 DataSource를 외부에서 주입받아 사용한다.
 * < Local은 존재하지 않는다. >
 */
class RepositoryImpl private constructor(private val dataSource: RemoteDataSource) : Repository {

    override fun getRemoteList(): Observable<String> = dataSource.getList().map { it.split(">")[1].split("<")[0] }


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