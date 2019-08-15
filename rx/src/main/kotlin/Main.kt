import data.Injection
import data.repository.RepositoryImpl
import io.reactivex.disposables.CompositeDisposable

/**
 * Main 함수
 * object class Injection 에서 DataSource 를 생성하여 의존성주입을 해준다.
 * @see Injection
 * @author 조재영
 */

fun main() {
    val compositeDisposable = CompositeDisposable()
    val repository = RepositoryImpl.getInstance(Injection.getDataSource()) //Repository 생성

    repository
        .getList()
        .subscribe { println(it) }
        .also { compositeDisposable.add(it) }

    compositeDisposable.clear()
}




