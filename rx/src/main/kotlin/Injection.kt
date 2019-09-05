import callback.datasource.remote.RemoteDataSourceCallback
import callback.datasource.remote.RemoteDataSourceCallbackImpl
import callback.datasource.remote.RemoteDataSourceHo
import callback.datasource.remote.RemoteDataSourceHoImpl
import rx.datasource.remote.RemoteDataSource
import rx.datasource.remote.RemoteDataSourceImpl

object Injection {
    private val remoteDataSource = RemoteDataSourceImpl
    private val remoteDataSourceCallback = RemoteDataSourceCallbackImpl
    private val remoteDataSourceHo = RemoteDataSourceHoImpl

    fun getDataSource(): RemoteDataSource = remoteDataSource

    fun getDataSourceCallback(): RemoteDataSourceCallback = remoteDataSourceCallback

    fun getDataSourceHo(): RemoteDataSourceHo = remoteDataSourceHo
}