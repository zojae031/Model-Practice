package callback.repository

interface RepositoryCallback {
    //콜백
    fun getListCallback(callback: Callback)

    interface Callback {
        fun getList(data: String)
    }
}