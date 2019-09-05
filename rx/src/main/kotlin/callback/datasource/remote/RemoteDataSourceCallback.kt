package callback.datasource.remote

interface RemoteDataSourceCallback {
    //콜백
    fun getListCallback(callback: Callback)

    interface Callback {
        fun getResult(data: String)
    }
}