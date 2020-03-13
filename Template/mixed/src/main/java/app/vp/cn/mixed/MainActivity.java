package app.vp.cn.mixed;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

      @BindView(R.id.wb)
      WebView wb;
    @BindView(R.id.bt)
    Button bt;

    @BindView(R.id.ll)
    LinearLayout linearLayout;
//    private WebView wb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //直接 new WebView 并传入 application context 代替在 XML 里面声明以防止 activity 引用被滥用，能解决90+%的 WebView 内存泄漏。
        //但是这种方法好像不能打开webView 的二级界面，所以慎重使用
//        wb = new WebView(getApplicationContext());
//        linearLayout.addView(wb);
        WebSettings wbSettings = wb.getSettings();
        wbSettings.setJavaScriptEnabled(true);
        //最少写到这里，去加载一个url 就可以显示出来了
        //开启定位
        wbSettings.setGeolocationEnabled(true);

        //是否支持多窗口
        wbSettings.setSupportMultipleWindows(false);
        wb.loadUrl("https://cpu.baidu.com/1022/cddee177/i/detail/60666953476/video?from=list&scid=48303");

        //清除当前webview 访问的历史记录
        // wb.clearHistory();
        //查找下一个匹配的字符串
        // wb.findNext(true);

        //保存网页（.html）到指定文件  提前新建文件
        wb.saveWebArchive(getExternalCacheDir().getAbsolutePath());

        wb.setWebViewClient(new MyWebViewClient());

        wb.setWebChromeClient(new MyWebChromeClient());

        //与js 进行交互  在网页中通过 android.say 调用  可以添加多个，并可以移除
        wb.addJavascriptInterface(new JSONOBJECT(), "android");
        //移除调用
        wb.removeJavascriptInterface("android");


    }

    public class JSONOBJECT {
        @JavascriptInterface
        public void say(String words) {
            // TODO: 2019/3/26 0026  
        }
    }

    @OnClick(R.id.bt)
    public void onViewClicked() {
        //下翻一页
        wb.pageDown(true);
        // 上翻一页，即向上滚动WebView高度的一半
        // wb.pageUp(true);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (wb != null) {
            wb.setWebViewClient(null);
            wb.setWebChromeClient(null);
            wb.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            wb.clearHistory();

            ((ViewGroup) wb.getParent()).removeView(wb);
            wb.destroy();
            wb = null;
        }
    }

    private class MyWebViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return true;
        }
    }

    private class MyWebChromeClient extends WebChromeClient{
        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            super.onShowCustomView(view, callback);
            Log.i("bai", "onShowCustomView: ");
        }
    }
}
