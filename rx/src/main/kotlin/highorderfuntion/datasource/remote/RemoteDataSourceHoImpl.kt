package callback.datasource.remote

import util.NaverAccessUtil

object RemoteDataSourceHoImpl : RemoteDataSourceHo, NaverAccessUtil() {

    //고차함수
    override fun callbackHighOrderFunction(call: (String) -> String) {
        for (buf in reader.lines()) {
            if (buf.contains("class=\"ah_k\"")) {
                call(buf)
            }
        }
        this.closeStream()
    }

}