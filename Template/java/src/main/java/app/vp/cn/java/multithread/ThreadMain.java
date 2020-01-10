package app.vp.cn.java.multithread;

/**
 * Created by baiye
 * Date: 2019/9/25
 * Time: 15:28
 * Description:
 */
public class ThreadMain {



    public static void main(String args[]){

       Foo foo = new Foo();
        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                System.out.print("two");
            }
        };
         Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.print("one");
            }
        };

        Runnable runnable3 = new Runnable() {
            @Override
            public void run() {
                System.out.print("three");
            }
        };

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);
        Thread thread3 = new Thread(runnable3);

        try {
            foo.first(thread1);
            foo.second(thread2);
            foo.third(thread3);

            thread1.start();
            thread2.start();
            thread3.start();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
