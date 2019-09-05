package callback.datasource.remote

interface RemoteDataSourceCb {
    //콜백
    fun getListCallback(callback: Callback)

    interface Callback {
        fun getResult(data: String)
    }
}