import callback.datasource.remote.RemoteDataSourceCallback
import callback.datasource.remote.RemoteDataSourceCallbackImpl
import rx.datasource.remote.RemoteDataSource
import rx.datasource.remote.RemoteDataSourceImpl

object Injection {
    private val remoteDataSource = RemoteDataSourceImpl
    private val remoteDataSourceCallback = RemoteDataSourceCallbackImpl

    fun getDataSource(): RemoteDataSource = remoteDataSource

    fun getDataSourceCallback(): RemoteDataSourceCallback = remoteDataSourceCallback


}