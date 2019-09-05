package callback.repository

import callback.datasource.remote.RemoteDataSourceCb

class RepositoryCbImpl private constructor(private val dataSourceCallback: RemoteDataSourceCb) :
    RepositoryCb {

    /**
     * 문제점1 : 콜백지옥이 형성된다.
     * 문제점2 : 가독성이 저하된다.
     * 해결방안 -> 고차함수
     * @see RepositoryHoImpl
     */

    override fun getList(callback: RepositoryCb.Callback) {//콜백1
        var index = 0
        dataSourceCallback
            .getList(object : RemoteDataSourceCb.Callback {
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
        private var INSTANCE: RepositoryCbImpl? = null
        fun getInstance(dataSource: RemoteDataSourceCb): RepositoryCbImpl {
            if (INSTANCE == null) {
                INSTANCE = RepositoryCbImpl(dataSource)
            }
            return INSTANCE!!
        }

    }
}