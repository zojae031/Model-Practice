package data.datasource.remote

import io.reactivex.*
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Cancellable
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

object RemoteDataSourceImpl : RemoteDataSource {
    private val url = URL("https://www.naver.com/")
    private val connector = url.openConnection()
    private val reader = BufferedReader(InputStreamReader(connector.getInputStream()))

    override fun getList(): Single<String> {
        return Single.create {
            reader.readLine()
        }
    }
}