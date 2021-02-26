package app.vp.cn.smalllibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class ChangeOperateActivity extends AppCompatActivity {
    public static final String TAG ="bai";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_operate);
        //map
//        对 被观察者发送的每1个事件都通过 指定的函数 处理，从而变换成另外一种事件
//        即， 将被观察者发送的事件转换为任意的类型事件。
        /*Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        }) .map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return "使用 Map变换操作符 将事件" + integer +"的参数从 整型"+integer + " 变换成 字符串类型" + integer ;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i(TAG, "accept: "+s);
            }
        });*/

        //FlatMap()
//        作用：将被观察者发送的事件序列进行 拆分 & 单独转换，再合并成一个新的事件序列，最后再进行发送
//                原理
//        应用场景
//                无序的将被观察者发送的整个事件序列进行变换
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {

                Log.i(TAG, "apply: 来来");
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("我是事件"+integer+"拆分后的子事件"+i);
                }
                //传入可迭代的参数，也就是实现了Iterable接口的参数
                return Observable.fromIterable(list);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i(TAG, "accept: "+s);
            }
        });
//        ConcatMap（）
//        作用：类似FlatMap（）操作符
//
//        与FlatMap（）的 区别在于：拆分 & 重新合并生成的事件序列 的顺序 = 被观察者旧序列生产的顺序
        /*Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).concatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("我是事件"+integer+"拆分后的子事件"+i);
                }
                //传入可迭代的参数，也就是实现了Iterable接口的参数
                return Observable.fromIterable(list);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i(TAG, "accept: "+s);
            }
        });*/

        //Buffer()
//        作用
//        定期从 被观察者（Obervable）需要发送的事件中 获取一定数量的事件 & 放到缓存区中，最终发送
        /*Observable.just(1,2,3,4,5)
                .buffer(3,1)
        //设置缓存区大小 & 步长
        // 缓存区大小 = 每次从被观察者中获取的事件数量
        // 步长 = 每次获取新事件的数量
        .subscribe(new Observer<List<Integer>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Integer> value) {
                Log.i(TAG, "onNext: 缓存区里的事件数量 = "+value.size());
                for (int i = 0; i < value.size(); i++) {
                    Log.i(TAG, "onNext: 事件是"+value.get(i));
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });*/

    }
}
