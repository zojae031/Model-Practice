package callback.repository

import callback.datasource.remote.RemoteDataSourceCallback

class RepositoryCallbackImpl private constructor(private val dataSourceCallback: RemoteDataSourceCallback) :
    RepositoryCallback {

    override fun getListCallback(callback: RepositoryCallback.Callback) {
        var index = 0
        dataSourceCallback
            .getListCallback(object : RemoteDataSourceCallback.Callback {
                override fun getResult(data: String) {
                    data.split(">")[1].split("<")[0]
                        .also {
                            index++
                            callback.getList("$index : $it")
                        }
                }
            })
    }


    companion object {
        private var INSTANCE: RepositoryCallbackImpl? = null
        fun getInstance(dataSource: RemoteDataSourceCallback): RepositoryCallbackImpl {
            if (INSTANCE == null) {
                INSTANCE = RepositoryCallbackImpl(dataSource)
            }
            return INSTANCE!!
        }

    }
}