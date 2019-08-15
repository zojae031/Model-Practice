# Model-Practice

Practice modelling with rxjava



## Main 함수 구성

```kotlin
fun main() {
    val compositeDisposable = CompositeDisposable()
    val repository = RepositoryImpl.getInstance(Injection.getDataSource()) //Repository 생성

    repository
        .getList()
        .subscribe { println(it) }
        .also { compositeDisposable.add(it) }

    compositeDisposable.clear()
}
```

- Repository를 통하여 데이터를 가져와 보여주는 부분
- Disposable 생명주기를 관리하는 부분



## Injection

```kotlin
object Injection {
    fun getDataSource(): RemoteDataSource {
        return RemoteDataSourceImpl
    }
}
```

- 의존성 주입을 위해 Injection 내에서 RemoteDataSource를 생성하여 넘겨준다.



## RepositoryImpl

```kotlin
class RepositoryImpl private constructor(private val dataSource: RemoteDataSource) : Repository {
    private var index = 0

    override fun getList(): Observable<String> = dataSource
        .getList()
        .map { it.split(">")[1].split("<")[0] }
        .map { string ->
            index++
            return@map "$index : $string"   
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
object RemoteDataSourceImpl : RemoteDataSource {
    private val url = URL("https://www.naver.com/")
    private val connector = url.openConnection()
    private val reader = BufferedReader(InputStreamReader(connector.getInputStream()))
    override fun getList(): Observable<String> {
        return Observable.create {
            for (buf in reader.lines()) {
                if (buf.contains("class=\"ah_k\"")) {
                    it.onNext(buf)
                }
            }
            reader.close()
        }
    }
}
```

- RemoteDataSource Interface 구현체 부분
- www.naver.com으로 connection을 설정하여 html 태그를 가져온다.
- ah_k class 태그를 골라 Observable 의 onNext를 호출해준다.