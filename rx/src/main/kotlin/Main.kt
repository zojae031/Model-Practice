import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableOnSubscribe
import io.reactivex.disposables.CompositeDisposable


fun main() {
    val compositeDisposable = CompositeDisposable()

    Flowable.create(FlowableOnSubscribe<String> {
        for (i in 1..1000) {
            it.onNext("${Thread.currentThread().name} + ${System.currentTimeMillis()} : $i")
        }
    }, BackpressureStrategy.BUFFER)
        .subscribe {
            println(it)
        }.also {
            compositeDisposable.add(it)
        }
}




