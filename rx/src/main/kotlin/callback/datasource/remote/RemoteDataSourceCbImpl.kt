package callback.datasource.remote

import util.NaverAccessUtil

object RemoteDataSourceCbImpl : RemoteDataSourceCb, NaverAccessUtil() {

    //콜백
    override fun getListCallback(callback: RemoteDataSourceCb.Callback) {
        for (buf in reader.lines()) {
            if (buf.contains("class=\"ah_k\"")) {
                callback.getResult(buf)
            }
        }
        this.closeStream()
    }

}