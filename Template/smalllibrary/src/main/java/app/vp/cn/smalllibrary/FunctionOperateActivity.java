package app.vp.cn.smalllibrary;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * 功能操作符
 */
public class FunctionOperateActivity extends AppCompatActivity {
    public static final String TAG = "bai";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function_operate);
        //subscribe 订阅
        //延迟操作，delay()
        //在事件的生命周期中操作
        //do()
      /*  Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onError(new Throwable("发生错误了"));
            }
        })
                // 1. 当Observable每发送1次数据事件就会调用1次
                .doOnEach(new Consumer<Notification<Integer>>() {
                    @Override
                    public void accept(Notification<Integer> integerNotification) throws Exception {
                        Log.d(TAG, "doOnEach: " + integerNotification.getValue());
                    }
                })
                // 2. 执行Next事件前调用
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "doOnNext: " + integer);
                    }
                })
                // 3. 执行Next事件后调用
                .doAfterNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "doAfterNext: " + integer);
                    }
                })
                // 4. Observable正常发送事件完毕后调用
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.e(TAG, "doOnComplete: ");
                    }
                })
                // 5. Observable发送错误事件时调用
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG, "doOnError: " + throwable.getMessage());
                    }
                })
                // 6. 观察者订阅时调用
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        Log.e(TAG, "doOnSubscribe: ");
                    }
                })
                // 7. Observable发送事件完毕后调用，无论正常发送完毕 / 异常终止
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.e(TAG, "doAfterTerminate: ");
                    }
                })
                // 8. 最后执行
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.e(TAG, "doFinally: ");
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "接收到了事件"+ value  );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });*/


        //错误处理
        //需求场景
        //发送事件过程中，遇到错误时的处理机制 onErrorReturn（）
        /*Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onError(new Throwable("发生错误了"));
            }
        }).onErrorReturn(new Function<Throwable, Integer>() {
            @Override
            public Integer apply(Throwable throwable) throws Exception {
                Log.i(TAG, "apply: 接受到了错误" + throwable);
                return 666;
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer value) {
                Log.i(TAG, "onNext: 接收到了事件" + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: 对事件作出了响应" + e);
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: ");
            }
        });*/

//        onErrorResumeNext（）
//        作用
//        遇到错误时，发送1个新的Observable
//        注：
//        onErrorResumeNext（）拦截的错误 = Throwable；若需拦截Exception请用onExceptionResumeNext（）
//        若onErrorResumeNext（）拦截的错误 = Exception，则会将错误传递给观察者的onError方法
      /*  Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onError(new Throwable("发生错误了"));
            }
        }).onErrorResumeNext(new Function<Throwable, ObservableSource<? extends Integer>>() {
            @Override
            public ObservableSource<? extends Integer> apply(Throwable throwable) throws Exception {
                Log.i(TAG, "apply: 在onErrorReturn处理了错误" + throwable.toString());
                //发送一个新的被观察者
                return Observable.just(11,22);
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer value) {
                Log.i(TAG, "onNext: 接受到了事件"+value);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: 对error事件作出响应");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: 对complete事件作出响应");
            }
        });*/
//        onExceptionResumeNext（）
//        作用
//        遇到错误时，发送1个新的Observable
//        注：
//
//        onExceptionResumeNext（）拦截的错误 = Exception；若需拦截Throwable请用onErrorResumeNext（）
//        若onExceptionResumeNext（）拦截的错误 = Throwable，则会将错误传递给观察者的onError方法
      /*  Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onError(new Exception("发生错误了"));
            }
        }).onExceptionResumeNext(new Observable<Integer>() {
            @Override
            protected void subscribeActual(Observer<? super Integer> observer) {
                observer.onNext(11);
                observer.onNext(22);
                observer.onComplete();
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                
            }

            @Override
            public void onNext(Integer value) {
                Log.i(TAG, "onNext: 接受到了事件"+value);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: 对error事件作出响应"+e);
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: 对complete事件作出响应");
            }
        });*/

        //retry()
//        作用
//        重试，即当出现错误时，让被观察者（Observable）重新发射数据
//        接收到 onError（）时，重新订阅 & 发送事件
//        Throwable 和 Exception都可拦截

    /*    <-- 1. retry（） -->
// 作用：出现错误时，让被观察者重新发送数据
// 注：若一直错误，则一直重新发送

<-- 2. retry（long time） -->
// 作用：出现错误时，让被观察者重新发送数据（具备重试次数限制
// 参数 = 重试次数

<-- 3. retry（Predicate predicate） -->
// 作用：出现错误后，判断是否需要重新发送数据（若需要重新发送& 持续遇到错误，则持续重试）
// 参数 = 判断逻辑

<--  4. retry（new BiPredicate<Integer, Throwable>） -->
// 作用：出现错误后，判断是否需要重新发送数据（若需要重新发送 & 持续遇到错误，则持续重试
// 参数 =  判断逻辑（传入当前重试次数 & 异常错误信息）

<-- 5. retry（long time,Predicate predicate） -->
// 作用：出现错误后，判断是否需要重新发送数据（具备重试次数限制
// 参数 = 设置重试次数 & 判断逻辑
*/

//    1.retry()
        // 作用：出现错误时，让被观察者重新发送数据
// 注：若一直错误，则一直重新发送
       /* Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onError(new Exception("发生错误了"));
                e.onNext(3);
            }
        }).retry(3)// 遇到错误时，让被观察者重新发射数据（若一直错误，则一直重新发送 会导致死机
        .subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer value) {
                Log.i(TAG, "onNext: 接收到了事件"+value);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: 对error事件作出响应"+e);
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: ");
            }
        });*/

       //repeat()
//        作用
//        无条件地、重复发送 被观察者事件
//        具备重载方法，可设置重复创建次数
        Observable.just(1,2,3,4)
                .repeat(3)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.i(TAG, "onNext: 接收到的事件是"+value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
