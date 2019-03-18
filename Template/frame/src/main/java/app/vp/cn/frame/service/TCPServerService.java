package app.vp.cn.frame.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * author : by
 * date: 2019/3/16 0016  上午 11:47.
 * describe 建立一个TCP 的Service 充当服务器的角色
 */

public class TCPServerService extends Service {

    private boolean mIsServiceDestroyed = false;
    private String[] mDefinedMessages = new String[]{
            "你好啊，哈哈", "请问你叫什么名字啊", "今天北京天气不错啊，shy", "你知道吗，我是可以和多个人同事聊天的哦", "给你讲个笑话吧，据说爱笑的人运气不会太差，不知道真假"
    };

    @Override
    public void onCreate() {
        super.onCreate();
        //开子线程建立tcp 连接
        new Thread(new TcpServer()).start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        mIsServiceDestroyed = true;
        super.onDestroy();
    }

    private class TcpServer implements Runnable {
        @SuppressWarnings("resource")
        @Override
        public void run() {
            ServerSocket serverSocket = null;

            try {
                serverSocket = new ServerSocket(8688);

            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            while (!mIsServiceDestroyed) {
                try {
                    //接收客户端请求
                    final Socket client = serverSocket.accept();
                    System.out.println("accept");
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                responseClient(client);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void responseClient(Socket client) throws IOException {
        //用于接收客户端消息
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        //用于向客户端发送消息
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
        out.println("欢迎来到聊天室!");
        while (!mIsServiceDestroyed) {
            String str = in.readLine();
            System.out.println("msg from client :" + str);
            if (str == null) {
                //客户端断开连接
                break;
            }
            int i = new Random().nextInt(mDefinedMessages.length);
            String msg = mDefinedMessages[i];
            //必须使用println 否则发送不了消息
            out.println(msg);
            System.out.println("send :" + msg);
        }
        System.out.println("client quit.");
        //关闭资源
        out.close();
        in.close();
        client.close();
    }
}
