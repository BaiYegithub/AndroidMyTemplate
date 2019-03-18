package app.vp.cn.frame;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import app.vp.cn.common.base.BaseActivity;
import app.vp.cn.common.util.DateUtil;
import app.vp.cn.frame.adapter.QaDetailAdapter;
import app.vp.cn.frame.bean.QaBean;
import app.vp.cn.frame.service.TCPServerService;
import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    //聊天的列表
    @BindView(R.id.rcv_chat)
    RecyclerView rcvChat;
    //文本输入框
    @BindView(R.id.et_send)
    EditText etSend;
    //发送按钮
    @BindView(R.id.bt_send)
    Button btSend;

    private static final int MESSAGE_RECEIVE_NEW_MSG = 1;
    private static final int MESSAGE_SOCKET_CONNECTED = 2;
    private Socket mClientSocket;
    private PrintWriter mPrintWriter;

    private List<QaBean> qaBeanList = new ArrayList<>();

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MESSAGE_RECEIVE_NEW_MSG:
                    QaBean qaBean = (QaBean) msg.obj;
                    qaBeanList.add(qaBean);
                    qaDetailAdapter.setQaBeanList(qaBeanList);
                    rcvChat.scrollToPosition(qaBeanList.size()-1);
                    break;
                case MESSAGE_SOCKET_CONNECTED:
                    btSend.setEnabled(true);
                    break;
                default:
                    break;
            }
        }
    };
    private QaDetailAdapter qaDetailAdapter;

    @Override
    protected int initContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViewAndData() {
        rcvChat.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        qaDetailAdapter = new QaDetailAdapter(MainActivity.this);
        rcvChat.setAdapter(qaDetailAdapter);

        //启动服务器
        Intent intent = new Intent(this, TCPServerService.class);
        startService(intent);

        new Thread() {
            @Override
            public void run() {
                connectTCPServre();
            }
        }.start();
    }

    private void connectTCPServre() {
        Socket socket = null;
        while (socket == null) {
            try {
                socket = new Socket("localhost", 8688);
                mClientSocket = socket;
                mPrintWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                mHandler.sendEmptyMessage(MESSAGE_SOCKET_CONNECTED);//连接成功
                System.out.println("connect server success");
            } catch (IOException e) {
                SystemClock.sleep(1000);//失败重连，10秒内
                System.out.println("connect tcp server failed,retry...");
            }

        }

        try {
            //接收服务端的消息
            BufferedReader br = new BufferedReader(new InputStreamReader(mClientSocket.getInputStream()));
            while (!MainActivity.this.isFinishing()) {
                String msg = br.readLine();
                System.out.println("receive:" + msg);
                if (msg != null) {
                    String curDate = DateUtil.getCurDate("HH:mm:ss");
                    QaBean qaBean = new QaBean(1, curDate, msg, "", "服务器");
                    mHandler.obtainMessage(MESSAGE_RECEIVE_NEW_MSG, qaBean).sendToTarget();
                }
            }

            System.out.print("quit...");
            mPrintWriter.close();
            br.close();
            mClientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initHttp() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void destroyResources() {
        if(mClientSocket!=null){
            try {
                mClientSocket.shutdownInput();
                mClientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    @OnClick(R.id.bt_send)
    public void onViewClicked() {
        final String msg = etSend.getText().toString();
        if (!TextUtils.isEmpty(msg) && mPrintWriter != null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    mPrintWriter.println(msg);
                }
            }).start();

            QaBean qaBean = new QaBean(0, DateUtil.getCurDate("HH:mm:ss"), msg, "", "");
            qaBeanList.add(qaBean);
            qaDetailAdapter.setQaBeanList(qaBeanList);
            rcvChat.scrollToPosition(qaBeanList.size()-1);
            etSend.setText("");
        }
    }
}
