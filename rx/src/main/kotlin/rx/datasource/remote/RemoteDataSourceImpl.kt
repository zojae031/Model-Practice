package rx.datasource.remote

import io.reactivex.Observable
import util.NaverAccessUtil

/**
 * 네이버 url 로 connection 을 요청하여 ah_k 클래스 태그들을 가져오는 Datasource
 * Observable 을 통해 onNext를 호출해서 repository 로 넘겨준다.
 * @see data.repository.RepositoryImpl
 */
object RemoteDataSourceImpl : RemoteDataSource, NaverAccessUtil() {
    override fun getList(): Observable<String> {
        return Observable.create {
            for (buf in reader.lines()) {
                if (buf.contains("class=\"ah_k\"")) {
                    it.onNext(buf)
                }
            }
            this.closeStream()
        }
    }
}