package callback.datasource.remote

import util.NaverAccessUtil

object RemoteDataSourceCallbackImpl : RemoteDataSourceCallback, NaverAccessUtil() {

    //콜백
    override fun getListCallback(callback: RemoteDataSourceCallback.Callback) {
        for (buf in reader.lines()) {
            if (buf.contains("class=\"ah_k\"")) {
                callback.getResult(buf)
            }
        }
        this.closeStream()
    }

}