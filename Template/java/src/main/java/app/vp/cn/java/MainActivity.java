package app.vp.cn.java;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.lang.ref.WeakReference;

import app.vp.cn.java.arithmetic.TraverseView;
import app.vp.cn.java.bean.Dog;
import app.vp.cn.java.bean.User;
import app.vp.cn.java.reference.SetUser;
import app.vp.cn.java.reference.SetUserReference;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    private WeakReference<Dog> stringWeakReference;
    private Dog dog1;
    private Dog dog;
    private Button btMain;
    private SetUser setUser;
    private WeakReference<User> weak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout rlv = findViewById(R.id.rlv);
        btMain = findViewById(R.id.bt_main);
        Button btGc = findViewById(R.id.bt_gc);
//        TraverseView.breadthFirst(rlv);

/*
        dog = new Dog();
        dog.name = "金毛";

        stringWeakReference = new WeakReference<Dog>(dog);
        dog = null;
        dog1 = stringWeakReference.get();
      *//*  while (true) {
            if (!TextUtils.isEmpty(s)) {*//*

         *//*        }
        }*//*
        btMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.i("bai", "onClick: dog是"+ dog);
                Log.i("bai", "onCreate:dog1是 " + dog1);

                Log.i("bai", "onClick:stringWeakReference.get()是 "+stringWeakReference.get());
            }
        });

        btGc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.gc();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.i("bai", "onClick: gc");
            }
        });

        WeakReference<String> sr = new WeakReference<>(new String("hello world " ));

// before gc -> hello world

        System.out.println("before gc -> " + sr.get());

// 通知JVM的gc进行垃圾回收

        System.gc();

// after gc -> null

        System.out.println("after gc -> " + sr.get());*/


        /*创建User对象*/
        User user = new User();
        /*设置username*/
        user.setUname("廖泽民");

        /*把对象放在弱引用中*/
        weak = new WeakReference<User>(user);

        /*把user对象置空，然后再从若引用中取值*/
        user = null;

        int i = 0;
        User user1 = new User();
        /*weak.get()表示从引用中取得对象*/
//        weak.get()是空的,user1不是空的

        while (weak.get() != null) {

            System.out.println(String.format("从弱引用中取值: %s, count: %d", weak.get().getUname(), ++i));
             new SetUser(weak.get());
            new SetUserReference(weak);
//            User user2 = weak.get();
            if (i % 10 == 0) {
                System.gc();//好像没用，得手动点击profiler里面的垃圾回收按钮
                System.out.println("内存回收方法被调用");
            }
            /* user1 = weak.get();*/

            try {
                Thread.sleep(500);
            } catch (Exception e) {

            }
        }
        System.out.println("对象已经被JVM回收");

        btMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                setUser.logUser();
            }
        });
    }


}
