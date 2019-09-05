package util

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

open class NaverAccessUtil {
    private val url = URL(naver)
    private val connector = url.openConnection()
    protected val reader = BufferedReader(InputStreamReader(connector.getInputStream()))

     companion object{
         private const val naver = "https://www.naver.com/"
     }
}