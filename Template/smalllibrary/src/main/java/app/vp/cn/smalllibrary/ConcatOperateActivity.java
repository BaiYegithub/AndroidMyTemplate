package app.vp.cn.smalllibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TimeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ConcatOperateActivity extends AppCompatActivity {
    public static final String TAG = "bai";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concat);
//        组合多个被观察者
//                该类型的操作符的作用 = 组合多个被观察者
//
//        concat（） / concatArray（）
//        作用
//        组合多个被观察者一起发送数据，合并后 按发送顺序串行执行
//        二者区别：组合被观察者的数量，即concat（）组合被观察者数量≤4个，而concatArray（）则可＞4个
// concat（）：组合多个被观察者（≤4个）一起发送数据
        // 注：串行执行,严格按照顺序发射数据
       /* Observable.concat(Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                        Thread.sleep(1000);
                        e.onNext(1);
                        Thread.sleep(1000);
                        e.onNext(2);
                        Thread.sleep(1000);
                        e.onNext(3);
                        e.onComplete();
                    }
                }).subscribeOn(Schedulers.io()),
                Observable.just(4,5,6),
                Observable.just(7,8,9),
                Observable.just(10,11,12))
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.i(TAG, "onNext: 接受到了事件"+value);
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

//        merge（） / mergeArray（）
//        作用
//        组合多个被观察者一起发送数据，合并后 按时间线并行执行
//        二者区别：组合被观察者的数量，即merge（）组合被观察者数量≤4个，而mergeArray（）则可＞4个
//        区别上述concat（）操作符：同样是组合多个被观察者一起发送数据，但concat（）操作符合并后是按发送顺序串行执行

           /* Observable.merge(
                    Observable.intervalRange(0,3,1,1, TimeUnit.SECONDS),// 从0开始发送、共发送3个数据、第1次事件延迟发送时间 = 1s、间隔时间 = 1s
                    Observable.intervalRange(2,3,1,1, TimeUnit.SECONDS))// 从2开始发送、共发送3个数据、第1次事件延迟发送时间 = 1s、间隔时间 = 1s
                    .subscribe(new Observer<Long>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Long value) {
                            Log.i(TAG, "onNext: 接受到了事件"+value);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.i(TAG, "onError: 对事件作出响应"+e);
                        }

                        @Override
                        public void onComplete() {
                            Log.i(TAG, "onComplete: 对事件作出响应");
                        }
                    });*/

//        concatArrayDelayError（） / mergeArrayDelayError（）
        //使用concat() 和 merge()操作符时，若其中一个被观察者发出onError事件，则会马上终止其他被观察者继续发送事件
        //若希望onError事件推迟到其他被观察者发送事件结束后才触发，即需调用concatDelayError()/mergeDelayError
        //没有使用concatDelayError（）的情况
     /*   Observable.concatArrayDelayError(
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                        e.onNext(1);
                        e.onNext(2);
                        e.onError(new NullPointerException()); // 发送Error事件，因为使用了concatDelayError，所以第2个Observable将会发送事件，等发送完毕后，再发送错误事件
                        e.onComplete();
                    }
                }), Observable.just(4, 5, 6))
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.i(TAG, "onNext: 接受到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: 错误提示" + e);
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete: 完成事件调用了");
                    }
                });*/
        //zip  合并多个事件 ,
//        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                e.onNext(1);
//                Log.i(TAG, "subscribe: 被观察者发送了事件1");
//                Thread.sleep(1000);
//                e.onNext(2);
//                Log.i(TAG, "subscribe: 被观察者发送了事件2");
//                Thread.sleep(1000);
//                e.onNext(3);
//                Log.i(TAG, "subscribe: 被观察者发送了事件3");
//                Thread.sleep(1000);
//                e.onComplete();
//            }
//        }).subscribeOn(Schedulers.io());
//
//        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
//                Log.i(TAG, "被观察者2发送了事件A");
//                emitter.onNext("A");
//                Thread.sleep(1000);
//
//                Log.i(TAG, "被观察者2发送了事件B");
//                emitter.onNext("B");
//                Thread.sleep(1000);
//
//                Log.i(TAG, "被观察者2发送了事件C");
//                emitter.onNext("C");
//                Thread.sleep(1000);
//
//                Log.i(TAG, "被观察者2发送了事件D");
//                emitter.onNext("D");
//                Thread.sleep(1000);
//
//                emitter.onComplete();
//            }
//        }).subscribeOn(Schedulers.newThread());// 设置被观察者2在工作线程2中工作
//
//        //使用zip变换操作符进行事件合并  严格按照顺序进行对位合并
//        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
//            @Override
//            public String apply(Integer integer, String s) throws Exception {
//                return integer + s;
//            }
//        }).subscribe(new Observer<String>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                Log.i(TAG, "onSubscribe: ");
//            }
//
//            @Override
//            public void onNext(String value) {
//                Log.i(TAG, "onNext: value是"+value);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.i(TAG, "onError: error是"+e);
//            }
//
//            @Override
//            public void onComplete() {
//                Log.i(TAG, "onComplete: ");
//            }
//        });

//        combineLatest（）
//        作用
//        当两个Observables中的任何一个发送了数据后，将先发送了数据的Observables 的最新（最后）一个数据 与 另外一个Observable发送的每个数据结合，最终基于该函数的结果发送数据
//        与Zip（）的区别：Zip（） = 按个数合并，即1对1合并；CombineLatest（） = 按时间合并，即在同一个时间点上合并

        Observable.combineLatest(
                Observable.just(1L, 2L, 3L),//第一个发送事件的Observable
                Observable.intervalRange(0, 3, 1, 1, TimeUnit.SECONDS),
                new BiFunction<Long, Long, Long>() {
                    @Override
                    public Long apply(Long aLong, Long aLong2) throws Exception {
                        Log.i(TAG, "apply: 合并前aLong " + aLong + " 合并后 aLong2是" + aLong2);
                        return aLong + aLong2;
                    }
                }
        ).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Log.i(TAG, "合并的结果是： " + aLong);
            }
        });

        //reduce
//        作用
//        把被观察者需要发送的事件聚合成1个事件 & 发送
//        聚合的逻辑根据需求撰写，但本质都是前2个数据聚合，然后与后1个数据继续进行聚合，依次类推
        /*Observable.just(1,2,3,4)
                .reduce(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer s1, Integer s2) throws Exception {
                        Log.i(TAG, "本次计算的数据是： "+s1 +" 乘 "+ s2);
                        return s1*s2;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.i(TAG, "最终计算的结果是： "+integer);
            }
        });*/

        //collect() 作用
        //将被观察者Observable发送的数据事件收集到一个数据结构里
       /* Observable.just(1, 2, 3, 4, 5, 6)
                .collect(new Callable<ArrayList<Integer>>() {
                    @Override
                    public ArrayList<Integer> call() throws Exception {
                        return new ArrayList<>();
                    }
                }, new BiConsumer<ArrayList<Integer>, Integer>() {
                    @Override
                    public void accept(ArrayList<Integer> integers, Integer integer) throws Exception {
                        integers.add(integer);
                    }
                }).subscribe(new Consumer<ArrayList<Integer>>() {
            @Override
            public void accept(ArrayList<Integer> integers) throws Exception {
                Log.i(TAG, "accept: 本次发送的数据是" + integers);
            }
        });*/

//        startWith（） / startWithArray（）
//         在一个被观察者发送事件前，追加发送一些数据
        // 注：追加数据顺序 = 后调用先追加
      /*  Observable.just(4,5,6)
                .startWith(0)
                .startWithArray(1,2,3)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.i(TAG, "onNext: 接受到了事件"+value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        Observable.just(4,5,6)
                .startWith(Observable.just(1,2,3))
                .subscribe(new Observer<Integer>() {
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
      /*  Observable.just(1,2,3)
                .count()
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.i(TAG, "accept: 发送的事件数量是"+aLong);
                    }
                });*/
    }
}
