package app.vp.cn.smalllibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * 过滤操作符
 */
public class FilterActivity extends AppCompatActivity {
    public static final String TAG = "bai";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
//        1.filter 过滤操作符
        /*Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onNext(4);
                e.onNext(5);
                e.onNext(1);
                e.onNext(2);
                e.onNext(6);
                e.onNext(7);
            }
        }).filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
                // 根据test()的返回值 对被观察者发送的事件进行过滤 & 筛选
                // a. 返回true，则继续发送
                // b. 返回false，则不发送（即过滤）
                return integer>3;
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer value) {
                Log.i(TAG, "过滤后得到的事件是："+ value  );
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });*/

//        ofType（）
//        作用
//        过滤 特定数据类型的数据
     /*   Observable.just(1, "Carson", 3, "Ho", 5)
                .ofType(Integer.class) // 筛选出 整型数据
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept( Integer integer) throws Exception {
                        Log.i(TAG,"获取到的整型事件元素是： "+ integer);
                    }
                });*/
//        skip（） / skipLast（）
//        作用
//                跳过某个事件

      /*  Observable.just(1,2,3,4,5)
                .skip(1)//跳过正序的前1项
                .skipLast(2)//跳过正序的后2项
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "accept: 获取到的整型事件是"+integer);
                    }
                });*/
//        distinct（） / distinctUntilChanged（）
//        作用
//        过滤事件序列中重复的事件 / 连续重复的事件

        /*Observable.just(1,2,3,1,2)
                .distinct()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "accept: 不重复的整型事件"+integer);
                    }
                });*/

        //过滤事件序列中 连续重复的事件
        /*Observable.just(1,2,3,1,2,3,3,4,4)
                .distinctUntilChanged()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "accept:不连续重复的事件是 "+integer);
                    }
                });*/

//        根据 指定事件数量 过滤事件
//                需求场景
//        通过设置指定的事件数量，仅发送特定数量的事件
//
//                对应操作符类型
//        take（） & takeLast（）
//
//        对应操作符使用
//
//        take（）
//        作用
//                指定观察者最多能接收到的事件数量
        /*Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onNext(4);
                e.onNext(5);
            }
        }).take(2)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe: 开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.i(TAG, "onNext: 接收到的事件是"+value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: "+e);
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete: ");
                    }
                });*/

       /* Observable.just(1,2,3,4,5)
                .takeLast(3)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe: 开始采用subscribe连接 ");
                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.i(TAG, "onNext: 过滤后得到的事件是"+value);
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

     /*  Observable.create(new ObservableOnSubscribe<Integer>() {
           @Override
           public void subscribe(ObservableEmitter<Integer> e) throws Exception {
               //隔段时间发送事件
               e.onNext(1);
               Thread.sleep(500);
               e.onNext(2);
               Thread.sleep(400);
               e.onNext(3);
               Thread.sleep(300);
               e.onNext(4);
               Thread.sleep(300);
               e.onNext(5);
               Thread.sleep(300);
               e.onNext(6);
               Thread.sleep(400);
               e.onNext(7);
               Thread.sleep(300);
               e.onComplete();
           }
       }).throttleLast(1, TimeUnit.SECONDS)
               .subscribe(new Observer<Integer>() {
                   @Override
                   public void onSubscribe(Disposable d) {
                       Log.i(TAG, "onSubscribe: 开始采用subscribe连接 ");
                   }

                   @Override
                   public void onNext(Integer value) {
                       Log.i(TAG, "onNext: 过滤后得到的事件是"+value);
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
//        firstElement（） / lastElement（）
//        作用
//        仅选取第1个元素 / 最后一个元素
        Observable.just(1,2,3,4,5)
                .firstElement()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "accept: 获取到的第一个事件是"+integer);
                    }
                });
    }
}
