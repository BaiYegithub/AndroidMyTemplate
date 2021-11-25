package app.vp.cn.system;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class ThreadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        ExecutorService excutor = Executors.newCachedThreadPool();
        final List<FutureTask<String>> futureTasks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final int finalI = i;
            futureTasks.add(new FutureTask<String>(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    Thread.sleep( finalI % 4*1000);
                    return "我是" + finalI;
                }
            }));
        }
        for (int i = 0; i < futureTasks.size(); i++) {
            excutor.submit(futureTasks.get(i));
        }
        excutor.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < futureTasks.size(); i++) {
                    try {
                        Log.i("bai", "run: >>>"+futureTasks.get(i).get());
                        Log.i("bai", "run: 当前线程名称是"+Thread.currentThread().getName());
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


    }
}