package callback.datasource.remote

interface RemoteDataSourceCallback {
    fun getList(callback: Callback)
    /**
     * 보일러 플레이트 콜백 인터페이스
     * Java 를 사용할때 겪었던 문제이기 때문에 SAM, 고차함수 사용하지 않음
     */
    interface Callback{
        fun getResult(data:String)
    }
}