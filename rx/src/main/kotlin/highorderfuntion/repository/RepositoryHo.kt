package callback.repository

interface RepositoryHo {
    fun getListHighOrderFunction(call: (String) -> Unit)
}