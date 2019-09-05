package callback.repository

interface RepositoryCb {
    //콜백
    fun getListCallback(callback: Callback)

    interface Callback {
        fun getList(data: String)
    }
}