package app.vp.cn.smalllibrary;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class BaseRxJavaActivity extends AppCompatActivity {

    public static final String TAG ="bai";
    private Disposable mDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        findViewById(R.id.bt_toast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BaseRxJavaActivity.this,"吐司了",Toast.LENGTH_LONG).show();
            }
        });
        //RxJava 是什么，  异步  RxJava 好在哪 简洁
//        Android 创造的 AsyncTask 和Handler ，其实都是为了让异步代码更加简洁。RxJava 的优势也是简洁，但它的简洁的与众不同之处在于，随着程序逻辑变得越来越复杂，它依然能够保持简洁。


        //创建被观察者 Observable 对象
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.i("bai", "当前的thread是:"+Thread.currentThread().getName());
//                Thread.sleep(15000);
                e.onNext(1);
//                Thread.sleep(15000);
                e.onNext(2);
//                Thread.sleep(15000);
                e.onNext(3);
                e.onComplete();
            }
        });
//        创建观察者
        //方式1：采用observer接口
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "onSubscribe: 开始采用subscribe连接");
                mDisposable = d;
            }

            @Override
            public void onNext(Integer value) {
                Log.i(TAG, "onNext: 对Next事件作出响应"+value);
                if(value==2){
                    mDisposable.dispose();
                    Log.i(TAG, "onNext: 切断链接");
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: 对error事件作出响应");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: 对complete事件作出响应");
            }
        };
        //方式2：采用subscriber类
        // 说明：Subscriber类 = RxJava 内置的一个实现了 Observer 的抽象类，对 Observer 接口进行了扩展
        /*Subscriber<Integer> subscriber = new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                Log.i(TAG, "onSubscribe: 开始采用subscribe连接");

            }

            @Override
            public void onNext(Integer integer) {
                Log.i(TAG, "onNext: 对Next事件作出响应"+integer);

            }

            @Override
            public void onError(Throwable t) {
                Log.i(TAG, "onError: 对error事件作出响应");

            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: 对complete事件作出响应");

            }
        };*/

        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
//       RxJava 2.x 提供了多个函数式接口 ，用于实现简便式的观察者模式。 以 Consumer为例：实现简便式的观察者模式
        Observable.just("hello").subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {

            }
        });
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                e.onNext(100);
                e.onNext(200);
            }
        }, BackpressureStrategy.ERROR)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        //必须设置的否则接收不到
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.i(TAG, "onNext: Flowable接收到了integer"+integer);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
