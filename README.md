# Model-Practice

Practice modelling with rxjava

``` 내부에 Callback, 고차함수, Rxjava를 사용한 코드들이 모두 들어있다.```
[Callback](https://github.com/zojae031/Model-Practice/tree/master/rx/src/main/kotlin/callback)
[고차함수](https://github.com/zojae031/Model-Practice/tree/master/rx/src/main/kotlin/highorderfuntion)
[rxjava](https://github.com/zojae031/Model-Practice/tree/master/rx/src/main/kotlin/rx)

## Main 함수 구성

```kotlin
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
```



## Injection

```kotlin
object Injection {
    private val remoteDataSource = RemoteDataSourceImpl
    private val remoteDataSourceCallback = RemoteDataSourceCallbackImpl
    private val remoteDataSourceHo = RemoteDataSourceHoImpl

    fun getDataSource(): RemoteDataSource = remoteDataSource

    fun getDataSourceCallback(): RemoteDataSourceCallback = remoteDataSourceCallback

    fun getDataSourceHo(): RemoteDataSourceHo = remoteDataSourceHo
}
```

- 의존성 주입을 위해 Injection 내에서 RemoteDataSource를 생성하여 넘겨준다.
- LocalDataSource는 구현하지 않았다.



## RepositoryImpl

```kotlin
/**
 * dataSource에서 리스트를 받아와 태그를 제거하여 반환한다.
 * Singleton으로 구성되어있으며 DataSource를 외부에서 주입받아 사용한다.
 * < Local은 존재하지 않는다. >
 */
class RepositoryImpl private constructor(private val dataSource: RemoteDataSource) :
    Repository {
    private var index = 0

    /**
     * 문제점1 : 학습곡선이 가파르다
     * 결과 -> 비동기처리와 콜백지옥 문제 모두 해결할 수 있다.
     */
    override fun getList(): Observable<String> = dataSource
        .getList()
        .map { it.split(">")[1].split("<")[0] }
        .map {
            index++
            return@map "$index : $it"
        }

    companion object {
        private var INSTANCE: RepositoryImpl? = null
        fun getInstance(dataSource: RemoteDataSource): RepositoryImpl {
            if (INSTANCE == null) {
                INSTANCE = RepositoryImpl(dataSource)
            }
            return INSTANCE!!
        }

    }
}
```

- Repository Interface 구현체 부분
- DataSource 에서 받아온 정보를 가공하여 리턴해준다.
- Local과 분리는 하지 않았기 때문에 Remote에 관한 정보만 받아온다.



## RemoteDataSourceImpl

```kotlin
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
```

- RemoteDataSource Interface 구현체 부분
- www.naver.com으로 connection을 설정하여 html 태그를 가져온다.
- ah_k class 태그를 골라 Observable 의 onNext를 호출해준다.
