package callback.repository

import callback.datasource.remote.RemoteDataSourceCallback

class RepositoryCallbackImpl private constructor(private val dataSourceCallback: RemoteDataSourceCallback) :
    RepositoryCallback {

    /**
     * 문제점1 : 콜백지옥이 형성된다.
     * 문제점2 : 가독성이 저하된다.
     * 해결방안 -> 고차함수
     * @see RepositoryHoImpl
     */

    override fun getListCallback(callback: RepositoryCallback.Callback) {//콜백1
        var index = 0
        dataSourceCallback
            .getListCallback(object : RemoteDataSourceCallback.Callback {
                //콜백2
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