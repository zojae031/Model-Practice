import callback.datasource.remote.RemoteDataSourceCb
import callback.datasource.remote.RemoteDataSourceCbImpl
import callback.datasource.remote.RemoteDataSourceHo
import callback.datasource.remote.RemoteDataSourceHoImpl
import rx.datasource.remote.RemoteDataSource
import rx.datasource.remote.RemoteDataSourceImpl

object Injection {
    private val remoteDataSource = RemoteDataSourceImpl
    private val remoteDataSourceCallback = RemoteDataSourceCbImpl
    private val remoteDataSourceHo = RemoteDataSourceHoImpl

    fun getDataSource(): RemoteDataSource = remoteDataSource

    fun getDataSourceCallback(): RemoteDataSourceCb = remoteDataSourceCallback

    fun getDataSourceHo(): RemoteDataSourceHo = remoteDataSourceHo
}