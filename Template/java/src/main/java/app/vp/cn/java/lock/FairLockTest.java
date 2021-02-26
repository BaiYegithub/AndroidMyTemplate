package app.vp.cn.java.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by baiye
 * Date: 2020/11/4
 * Time: 15:04
 * Description:
 */
public class FairLockTest implements Runnable {
    private int num = 0;
    //创建公平锁
    private static ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        while (num < 20) {
            lock.lock();
            try {
                num++;
                System.out.println("当前线程名称是" + Thread.currentThread().getName() + "读取的数字是" + num);
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        FairLockTest fairLockTest = new FairLockTest();
        Thread th1 = new Thread(fairLockTest);
        Thread th2 = new Thread(fairLockTest);
        Thread th3 = new Thread(fairLockTest);
        th1.start();
        th2.start();
        th3.start();
    }
}
