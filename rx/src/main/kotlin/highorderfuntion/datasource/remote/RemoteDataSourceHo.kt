package callback.datasource.remote

interface RemoteDataSourceHo {
    //고차함수
    fun callbackHighOrderFunction(call : (String)->String)

}