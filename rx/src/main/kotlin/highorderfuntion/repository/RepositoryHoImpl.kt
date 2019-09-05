package callback.repository

import callback.datasource.remote.RemoteDataSourceHo

class RepositoryHoImpl private constructor(private val dataSourceCallback: RemoteDataSourceHo) :
    RepositoryHo {


    //고차함수
    override fun getListHighOrderFunction(call: (String) -> Unit) {
        var index = 0
        dataSourceCallback.callbackHighOrderFunction { data ->
            data.split(">")[1].split("<")[0]
                .also {
                    index++
                    call("$index : $it")
                }
        }
    }


    companion object {
        private var INSTANCE: RepositoryHoImpl? = null
        fun getInstance(dataSource: RemoteDataSourceHo): RepositoryHoImpl {
            if (INSTANCE == null) {
                INSTANCE = RepositoryHoImpl(dataSource)
            }
            return INSTANCE!!
        }

    }
}