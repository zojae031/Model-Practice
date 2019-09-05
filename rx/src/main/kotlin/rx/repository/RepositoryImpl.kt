package rx.repository

import io.reactivex.Observable
import rx.datasource.remote.RemoteDataSource

/**
 * dataSource에서 리스트를 받아와 태그를 제거하여 반환한다.
 * Singleton으로 구성되어있으며 DataSource를 외부에서 주입받아 사용한다.
 * < Local은 존재하지 않는다. >
 */
class RepositoryImpl private constructor(private val dataSource: RemoteDataSource) :
    Repository {

    /**
     * 문제점1 : 학습곡선이 가파르다
     * 결과 -> 비동기처리와 콜백지옥 문제 모두 해결할 수 있다.
     */
    override fun getList(): Observable<String> {
        var index = 0
        return dataSource
            .getList()
            .map { it.split(">")[1].split("<")[0] }
            .map {
                index++
                return@map "$index : $it"
            }
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