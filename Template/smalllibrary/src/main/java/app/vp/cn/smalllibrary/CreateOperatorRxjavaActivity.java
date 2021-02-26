package app.vp.cn.smalllibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CreateOperatorRxjavaActivity extends AppCompatActivity {

    public static final String TAG = "bai";
    private TextView tvOpe;
    private Integer i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operator_rxjava);
        tvOpe = findViewById(R.id.tv_acOpe);
        //1.基本创建，create()
       /* Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {

            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer value) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });*/

        //2.快速创建 &发送事件
//        just()  最多只能是10个
        //fromArray()  可发送10个以上参数  需要传递数组  若直接传递一个list集合进去，否则会直接把list当做一个数据元素发送
              /*  Integer[] aa= new Integer[]{1,2,3,4};
                Observable.just(1,"aa",true)
                        .subscribeOn(Schedulers.io()) //处理操作在io 子线程，内部维护一个线程池
                        .observeOn(AndroidSchedulers.mainThread()) //回调在主线程
                        .subscribe(new Observer<Object>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                Log.i(TAG, "开始采用subscribe连接");
                            }
                            // 默认最先调用复写的 onSubscribe（）

                            @Override
                            public void onNext(Object value) {
                                Log.i(TAG, "接收到了事件" + value);
                                tvOpe.setText(value+"");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i(TAG, "对Error事件作出响应");
                            }

                            @Override
                            public void onComplete() {
                                Log.i(TAG, "对Complete事件作出响应");
                            }
                        });*/
      /*  Observable.just(1, 2, 3, 4)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "开始采用subscribe连接");
                    }
                    // 默认最先调用复写的 onSubscribe（）

                    @Override
                    public void onNext(Integer value) {
                        Log.i(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "对Complete事件作出响应");
                    }
                });*/

        //fromIterable（）
        //        快速创建1个被观察者对象（Observable）
        //        发送事件的特点：直接发送 传入的集合List数据

        //延迟创建
//        defer()
        //第一次对i赋值
       /* i = 10;

        // 2. 通过defer 定义被观察者对象
        // 注：此时被观察者对象还没创建
        Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
            @Override
            public ObservableSource<? extends Integer> call() throws Exception {
                return Observable.just(i);
            }
        });

        i = 15;

        observable.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer value) {
                Log.i(TAG, "onNext: 接收到的整数是"+value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });*/

        //timer
        // 作用:快速创建1个被观察者对象（Observable）
        //发送事件的特点：延迟指定时间后，发送1个数值0（Long类型）
        /*Observable.timer(2, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe: 开始采用subscribe");
                    }

                    @Override
                    public void onNext(Long value) {
                        Log.i(TAG, "onNext: timer 接收到了事件"+value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });*/

//        interval（）
//        作用
//        快速创建1个被观察者对象（Observable）
//        发送事件的特点：每隔指定时间 就发送 事件
        // 参数说明：
        // 参数1 = 第1次延迟时间；
        // 参数2 = 间隔时间数字；
        // 参数3 = 时间单位；
       /* Observable.interval(3,1,TimeUnit.SECONDS).
                subscribe(
                new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe: interval开始链接");
                    }

                    @Override
                    public void onNext(Long value) {
                        Log.i(TAG, "onNext:interval 接收到了事件"+value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }
        );*/
//        intervalRange（）
//        作用
//        快速创建1个被观察者对象（Observable）
//        发送事件的特点：每隔指定时间 就发送 事件，可指定发送的数据的数量
//        a. 发送的事件序列 = 从0开始、无限递增1的的整数序列
//        b. 作用类似于interval（），但可指定发送的数据的数量
        // 参数说明：
        // 参数1 = 事件序列起始点；
        // 参数2 = 事件数量；
        // 参数3 = 第1次事件延迟发送时间；
        // 参数4 = 间隔时间数字；
        // 参数5 = 时间单位

         Observable.intervalRange(0, 5, 0, 1, TimeUnit.SECONDS)
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        tvOpe.setText((5 - aLong) + "");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
//                        tomain
                    }
                });

    }
}
