package callback.datasource.remote

import util.NaverAccessUtil

object RemoteDataSourceCallbackImpl : RemoteDataSourceCallback, NaverAccessUtil() {

    override fun getList(callback: RemoteDataSourceCallback.Callback) {
        for (buf in reader.lines()) {
            if (buf.contains("class=\"ah_k\"")) {
                callback.getResult(buf)
            }
        }
        reader.close()
    }

}