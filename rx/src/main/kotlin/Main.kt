import callback.repository.RepositoryCallback
import callback.repository.RepositoryCallbackImpl
import io.reactivex.disposables.CompositeDisposable
import rx.repository.RepositoryImpl

/**
 * Main 함수
 * object class Injection 에서 DataSource 를 생성하여 의존성주입을 해준다.
 * @see Injection
 * @author 조재영
 */

fun main() {
    println("rxjava")
    Rxjava()
    println("callback")
    Callback()

}
fun Rxjava(){
    val compositeDisposable = CompositeDisposable()
    val repository = RepositoryImpl.getInstance(Injection.getDataSource()) //Repository 생성

    repository
        .getList()
        .subscribe { println(it) }
        .also { compositeDisposable.add(it) }

    compositeDisposable.clear()
}

fun Callback(){
    val repository = RepositoryCallbackImpl.getInstance(Injection.getDataSourceCallback())
    repository
        .getList(object : RepositoryCallback.Callback{
            override fun getList(data: String) {
                println(data)
            }
        })
}



