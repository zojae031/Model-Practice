package callback.repository

interface RepositoryCallback {
    fun getList(callback : Callback)

    interface Callback {
        fun getList(data: String)
    }
}