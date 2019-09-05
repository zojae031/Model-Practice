import callback.repository.RepositoryCallback
import callback.repository.RepositoryCallbackImpl
import callback.repository.RepositoryHoImpl
import io.reactivex.disposables.CompositeDisposable
import rx.repository.RepositoryImpl

/**
 * Main 함수
 * object class Injection 에서 DataSource 를 생성하여 의존성주입을 해준다.
 * rxjava, Callback, 고차함수 세 가지 방법으로 데이터를 가져온다.
 * @see Injection
 * @author 조재영
 */

fun main() {
    println("rxjava")
    rxjava()
    println("callback")
    callback()
    println("HighOrderFunction")
    highOrderFunction()
}


fun callback() {
    RepositoryCallbackImpl.getInstance(Injection.getDataSourceCallback())
        .getListCallback(object : RepositoryCallback.Callback {
            override fun getList(data: String) {
                println(data)
            }
        })
}

fun highOrderFunction() {
    RepositoryHoImpl.getInstance(Injection.getDataSourceHo())
        .getListHighOrderFunction { data ->
            println(data)
        }
}

fun rxjava() {
    val compositeDisposable = CompositeDisposable()
    RepositoryImpl.getInstance(Injection.getDataSource()) //Repository 생성
        .getList()
        .subscribe { println(it) }
        .also { compositeDisposable.add(it) }

    compositeDisposable.clear()
}