package callback.repository

interface RepositoryCb {
    //콜백
    fun getList(callback: Callback)

    interface Callback {
        fun getList(data: String)
    }
}