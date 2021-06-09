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
        final ExecutorService executor = Executors.newCachedThreadPool();
        final List<FutureTask<String>> futureTaskList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final int finalI = i;
            futureTaskList.add(new FutureTask<String>(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    Thread.sleep(finalI % 3 * 1000);
                    return "我是" + finalI;
                }
            }));
        }
        for (int i = 0; i < futureTaskList.size(); i++) {
            executor.execute(futureTaskList.get(i));
        }

        executor.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < futureTaskList.size(); i++) {
                    try {
                        String s = futureTaskList.get(i).get();
                        Log.i("bai", "run: >>>>>>> "+s);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}