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
 * boolean操作符
 */
public class BooleanOperaterActivity extends AppCompatActivity {
    public static final String TAG = "bai";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boolean_operater);
        //all()
//        作用：判断发送的每项数据是否都满足 设置的函数条件
//        若满足，返回 true；否则，返回 false
        /*Observable.just(1,2,3,4,5,6)
                .all(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer<10;
                    }
                }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                Log.i(TAG, "accept: 是否符合条件"+aBoolean);
            }
        });*/

        //contains()
      /*  Observable.just(1,2,3,4,5)
                .contains(6)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        Log.i(TAG, "accept: 是否包含所要的数据5"+aBoolean);
                    }
                });*/
      //isEmpty()

        //takeWhile（）
        //作用：判断发送的每项数据是否满足 设置函数条件
        //若发送的数据满足该条件，则发送该项数据；否则不发送
       /* Observable.interval(1, TimeUnit.SECONDS)
                .takeWhile(new Predicate<Long>() {
                    @Override
                    public boolean test(Long aLong) throws Exception {
                        return aLong > 3;
                    }
                }).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long value) {
                Log.i(TAG, "onNext: 接收了事件"+value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });*/

//        skipWhile（）
//        作用：判断发送的每项数据是否满足 设置函数条件
//        直到该判断条件 = false时，才开始发送Observable的数据
//        开始时符合条件不会发送，直到不符合条件才发送，以后无论是否符合条件都发送
        /*Observable.interval(1,TimeUnit.SECONDS)
                .skipWhile(new Predicate<Long>() {
                    @Override
                    public boolean test(Long aLong) throws Exception {
                        return aLong < 5;
                    }
                }).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Log.i(TAG, "accept: skipWhile接收到的事件是"+aLong);
            }
        });*/
    }
}
