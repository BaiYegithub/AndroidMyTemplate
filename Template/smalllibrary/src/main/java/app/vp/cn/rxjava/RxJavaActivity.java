package app.vp.cn.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import app.vp.cn.smalllibrary.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RxJavaActivity extends AppCompatActivity {
    private static final String TAG = "bai";

    private Disposable disposable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java2);
//        被观察者（Observable）：产生事件
       /* Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        });
//        观察者(Observer):接收事件，并给出响应动作
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "onSubscribe: 连接上了，开始接收");
            }

            @Override
            public void onNext(Integer value) {
                Log.i(TAG, "onNext: 接收被观察者发送来的事件" + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError:" + e);
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: ");
            }
        };
        observable.subscribe(observer);*/

       new Thread(new Runnable() {
           @Override
           public void run() {
               Observable.create(new ObservableOnSubscribe<Integer>() {
                   @Override
                   public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                       Log.i(TAG, "subscribe: 被观察者当前的线程是"+Thread.currentThread().getName());
                       Thread.sleep(5000);
                       e.onNext(1);
                       Thread.sleep(5000);
                       e.onNext(2);
                       Thread.sleep(5000);
                       e.onNext(3);
                       e.onComplete();
                   }
               }).subscribeOn(Schedulers.io())
                       .subscribeOn(Schedulers.io())
                       .observeOn(AndroidSchedulers.mainThread())
                       .subscribe(new Observer<Integer>() {
                           @Override
                           public void onSubscribe(Disposable d) {
                               Log.i(TAG, "onSubscribe: 连接上了，开始接收");
                               Log.i(TAG, "观察者当前subscribe: 当前的线程是"+Thread.currentThread().getName());
                               disposable = d;

                           }

                           @Override
                           public void onNext(Integer value) {
                               Log.i(TAG, "onNext: 接收被观察者发送来的事件" + value);
                               Log.i(TAG, "观察者当前subscribe: 当前的线程是>>>>>"+Thread.currentThread().getName());
                                if(value==2){
                                    disposable.dispose();
                                }
                           }

                           @Override
                           public void onError(Throwable e) {
                               Log.i(TAG, "onError:" + e);
                           }

                           @Override
                           public void onComplete() {
                               Log.i(TAG, "onComplete: ");
                           }
                       });
           }
       }).start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(!disposable.isDisposed()){
            disposable.dispose();
        }
    }
}
