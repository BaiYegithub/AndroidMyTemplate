package app.vp.cn.mixed;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.ByteArrayInputStream;

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
        String userAgentString = wbSettings.getUserAgentString();
        Log.i("bai", "onCreate: userAgent是"+userAgentString);
        //最少写到这里，去加载一个url 就可以显示出来了
        //开启定位
        wbSettings.setGeolocationEnabled(true);

        //是否支持多窗口
        wbSettings.setSupportMultipleWindows(false);
        wb.loadUrl("https://so.toutiao.com/search?keyword=%E6%94%B6%E8%B4%B9%E7%AB%9910%E7%A7%92KO%E4%B8%A4%E7%94%B7%E5%AD%90%20%E5%BD%93%E4%BA%8B%E4%BA%BA%E9%81%93%E6%AD%89&pd=synthesis&traffic_source=WA1113&original_source=1&source=client");
//        wb.loadData(" <!DOCTYPE html>\n" +
//                "<html>\n" +
//                "<head>\n" +
//                "<meta charset=\"UTF-8\">\n" +
//                "<meta name=\"applicable-device\" content=\"mobile\">\n" +
//                "<title>钓场_钓场大全_全国钓场信息 - 钓鱼之家</title>\n" +
//                "<meta name=\"keywords\" content=\"钓场，钓点\" />\n" +
//                "<meta name=\"description\" content=\"钓鱼之家钓场频道搜集了全国各地的钓场信息大全，有免费钓场，也有收费钓场，将您身边的钓场一网打尽。\" />\n" +
//                "<meta name=\"applicable-device\" content=\"mobile\" />\n" +
//                "<meta name=\"apple-mobile-web-app-capable\" content=\"yes\" />\n" +
//                "<meta name=\"apple-touch-fullscreen\" content=\"yes\" />\n" +
//                "<meta name=\"format-detection\" content=\"telephone=no,email=no\" />\n" +
//                "<meta name=\"viewport\" content=\"width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no\" />\n" +
//                "<link rel=\"icon\" type=\"image/x-icon\" href=\"http://m.diaoyu.com/favicon.ico\" />\n" +
//                "<link rel=\"shortcut icon\" type=\"image/x-icon\" href=\"http://m.diaoyu.com/favicon.ico\" />\n" +
//                "<link rel=\"canonical\" href=\"http://m.diaoyu.com/diaochang/\" />\n" +
//                "<link type=\"text/css\" href=\"http://static.diaoyu.com/diaoyucms/m/v201905/css/??common.css,menu.css?version=0.177\" rel=\"stylesheet\" />\n" +
//                "<script type=\"text/javascript\">var getCityUrl ='http://m.diaoyu.com/ajax/common/getCityInfo';</script>\n" +
//                "<style type=\"text/css\">html,div,span,body,link,textarea,select,input,option,optgroup button,tr,nav,aside,applet,object,tr,h1,h2,h3,h4,h5,h6,blockquote,pre,abbr,acronym,address,big,cite,code,del,dfn,em,font,ins,kbd,q,p,s,samp,small,strike,strong,sub,sup,tt,var,b,u,i,center,dl,dt,dd,ol,ul,li,fieldset,form,label,legend,table,caption,tbody,tfoot,thead,th,td,main{\tbackground-color: rgba(211,228,211,0.8) !important;\tcolor: #323a24 !important;\tborder-color: #ffffffff !important;\t/*border-color: #323a24 !important;*/\t/* text-shadow: 0 0 0 #000; 去掉文本的阴影效果*/ } select{    background-color: rgba(211,228,211,0.8) !important; \tcolor: #323a24 !important; \tborder-color: #555555 !important; }</style></head>\n" +
//                "<body> <header>\n" +
//                "<a class=\"back\" href=\"http://m.diaoyu.com/\"><span>返回</span></a>\n" +
//                "<span class=\"title\"><h1>选择城市</h1></span>\n" +
//                "<a class=\"menu\" href=\"http://m.diaoyu.com/daohang/\">菜单</a>\n" +
//                "</header>\n" +
//                "<dl class=\"city-hot\">\n" +
//                "<script src=\"http://m.diaoyu.com/ajax/diaochang/indexcityblock.js\" type=\"text/javascript\"></script>\n" +
//                "<dt>热门城市</dt>\n" +
//                "<dd>\n" +
//                "<ul>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/beijing/\">北京</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/tianjin/\">天津</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/shanghai/\">上海</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/guangzhou/\">广州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/shenzhen/\">深圳</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/hangzhou/\">杭州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/nanjing/\">南京</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/suzhou/\">苏州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/yangzhou/\">扬州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/zhengzhou/\">郑州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/zibo/\">淄博</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/chengdu/\">成都</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/hefei/\">合肥</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/wuhan/\">武汉</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/chongqing/\">重庆</a></li>\n" +
//                "</ul>\n" +
//                "</dd>\n" +
//                "</dl>\n" +
//                "<dl class=\"city-all\">\n" +
//                "<dt><a href=\"javascript:void(0);\" name=\"a\">A</a></dt>\n" +
//                "<dd>\n" +
//                "<ul>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/alashan/\" title=\"阿拉善钓场\">阿拉善</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/anshan/\" title=\"鞍山钓场\">鞍山</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/anqing/\" title=\"安庆钓场\">安庆</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/anyang/\" title=\"安阳钓场\">安阳</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/aba/\" title=\"阿坝钓场\">阿坝</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/anshun/\" title=\"安顺钓场\">安顺</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/ali/\" title=\"阿里钓场\">阿里</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/ankang/\" title=\"安康钓场\">安康</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/akesu/\" title=\"阿克苏钓场\">阿克苏</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/aletai/\" title=\"阿勒泰钓场\">阿勒泰</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/alaer/\" title=\"阿拉尔钓场\">阿拉尔</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/aomen/\" title=\"澳门钓场\">澳门</a></li>\n" +
//                "</ul>\n" +
//                "</dd>\n" +
//                "<dt><a href=\"javascript:void(0);\" name=\"b\">B</a></dt>\n" +
//                "<dd>\n" +
//                "<ul>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/beijing/\" title=\"北京钓场\">北京</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/baoding/\" title=\"保定钓场\">保定</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/baotou/\" title=\"包头钓场\">包头</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/bayannaoer/\" title=\"巴彦淖尔钓场\">巴彦淖尔</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/benxi/\" title=\"本溪钓场\">本溪</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/baishan/\" title=\"白山钓场\">白山</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/baicheng/\" title=\"白城钓场\">白城</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/bangbu/\" title=\"蚌埠钓场\">蚌埠</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/bozhou/\" title=\"亳州钓场\">亳州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/binzhou/\" title=\"滨州钓场\">滨州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/beihai/\" title=\"北海钓场\">北海</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/baise/\" title=\"百色钓场\">百色</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/bazhong/\" title=\"巴中钓场\">巴中</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/bijie/\" title=\"毕节钓场\">毕节</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/baoshan/\" title=\"保山钓场\">保山</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/baoji/\" title=\"宝鸡钓场\">宝鸡</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/baiyin/\" title=\"白银钓场\">白银</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/boertala/\" title=\"博尔塔拉钓场\">博尔塔拉</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/bayinguoleng/\" title=\"巴音郭楞钓场\">巴音郭楞</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/baisha/\" title=\"白沙钓场\">白沙</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/baoting/\" title=\"保亭钓场\">保亭</a></li>\n" +
//                "</ul>\n" +
//                "</dd>\n" +
//                "<dt><a href=\"javascript:void(0);\" name=\"c\">C</a></dt>\n" +
//                "<dd>\n" +
//                "<ul>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/chengde/\" title=\"承德钓场\">承德</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/cangzhou/\" title=\"沧州钓场\">沧州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/changzhi/\" title=\"长治钓场\">长治</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/chifeng/\" title=\"赤峰钓场\">赤峰</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/chaoyang/\" title=\"朝阳钓场\">朝阳</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/changchun/\" title=\"长春钓场\">长春</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/changzhou/\" title=\"常州钓场\">常州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/chuzhou/\" title=\"滁州钓场\">滁州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/chaohu/\" title=\"巢湖钓场\">巢湖</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/chizhou/\" title=\"池州钓场\">池州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/changsha/\" title=\"长沙钓场\">长沙</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/changde/\" title=\"常德钓场\">常德</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/chenzhou/\" title=\"郴州钓场\">郴州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/chaozhou/\" title=\"潮州钓场\">潮州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/chongzuo/\" title=\"崇左钓场\">崇左</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/chongqing/\" title=\"重庆钓场\">重庆</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/chengdu/\" title=\"成都钓场\">成都</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/chuxiong/\" title=\"楚雄钓场\">楚雄</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/changdu/\" title=\"昌都钓场\">昌都</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/changji/\" title=\"昌吉钓场\">昌吉</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/chengmai/\" title=\"澄迈钓场\">澄迈</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/changjiang/\" title=\"昌江钓场\">昌江</a></li>\n" +
//                "</ul>\n" +
//                "</dd>\n" +
//                "<dt><a href=\"javascript:void(0);\" name=\"d\">D</a></dt>\n" +
//                "<dd>\n" +
//                "<ul>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/datong/\" title=\"大同钓场\">大同</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/dalian/\" title=\"大连钓场\">大连</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/dandong/\" title=\"丹东钓场\">丹东</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/daqing/\" title=\"大庆钓场\">大庆</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/daxinganling/\" title=\"大兴安岭钓场\">大兴安岭</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/dongying/\" title=\"东营钓场\">东营</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/dezhou/\" title=\"德州钓场\">德州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/dongguan/\" title=\"东莞钓场\">东莞</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/deyang/\" title=\"德阳钓场\">德阳</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/dazhou/\" title=\"达州钓场\">达州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/dali/\" title=\"大理钓场\">大理</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/dehong/\" title=\"德宏钓场\">德宏</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/diqing/\" title=\"迪庆钓场\">迪庆</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/dingxi/\" title=\"定西钓场\">定西</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/danzhou/\" title=\"儋州钓场\">儋州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/dongfang/\" title=\"东方钓场\">东方</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/dingan/\" title=\"定安钓场\">定安</a></li>\n" +
//                "</ul>\n" +
//                "</dd>\n" +
//                "<dt><a href=\"javascript:void(0);\" name=\"e\">E</a></dt>\n" +
//                "<dd>\n" +
//                "<ul>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/eerduosi/\" title=\"鄂尔多斯钓场\">鄂尔多斯</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/ezhou/\" title=\"鄂州钓场\">鄂州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/enshi/\" title=\"恩施钓场\">恩施</a></li>\n" +
//                "</ul>\n" +
//                "</dd>\n" +
//                "<dt><a href=\"javascript:void(0);\" name=\"f\">F</a></dt>\n" +
//                "<dd>\n" +
//                "<ul>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/fushun/\" title=\"抚顺钓场\">抚顺</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/fuxin/\" title=\"阜新钓场\">阜新</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/fuyang/\" title=\"阜阳钓场\">阜阳</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/fuzhou/\" title=\"福州钓场\">福州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/fuzhou1/\" title=\"抚州钓场\">抚州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/foshan/\" title=\"佛山钓场\">佛山</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/fangchenggang/\" title=\"防城港钓场\">防城港</a></li>\n" +
//                "</ul>\n" +
//                "</dd>\n" +
//                "<dt><a href=\"javascript:void(0);\" name=\"g\">G</a></dt>\n" +
//                "<dd>\n" +
//                "<ul>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/ganzhou/\" title=\"赣州钓场\">赣州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/guangzhou/\" title=\"广州钓场\">广州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/guilin/\" title=\"桂林钓场\">桂林</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/guigang/\" title=\"贵港钓场\">贵港</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/guangyuan/\" title=\"广元钓场\">广元</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/guangan/\" title=\"广安钓场\">广安</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/ganzi/\" title=\"甘孜钓场\">甘孜</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/guiyang/\" title=\"贵阳钓场\">贵阳</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/gannan/\" title=\"甘南钓场\">甘南</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/guoluo/\" title=\"果洛钓场\">果洛</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/guyuan/\" title=\"固原钓场\">固原</a></li>\n" +
//                "</ul>\n" +
//                "</dd>\n" +
//                "<dt><a href=\"javascript:void(0);\" name=\"h\">H</a></dt>\n" +
//                "<dd>\n" +
//                "<ul>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/handan/\" title=\"邯郸钓场\">邯郸</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/hengshui/\" title=\"衡水钓场\">衡水</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/huhehaote/\" title=\"呼和浩特钓场\">呼和浩特</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/hulunbeier/\" title=\"呼伦贝尔钓场\">呼伦贝尔</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/huludao/\" title=\"葫芦岛钓场\">葫芦岛</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/haerbin/\" title=\"哈尔滨钓场\">哈尔滨</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/hegang/\" title=\"鹤岗钓场\">鹤岗</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/heihe/\" title=\"黑河钓场\">黑河</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/huaian/\" title=\"淮安钓场\">淮安</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/hangzhou/\" title=\"杭州钓场\">杭州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/huzhou/\" title=\"湖州钓场\">湖州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/hefei/\" title=\"合肥钓场\">合肥</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/huainan/\" title=\"淮南钓场\">淮南</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/huaibei/\" title=\"淮北钓场\">淮北</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/huangshan/\" title=\"黄山钓场\">黄山</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/heze/\" title=\"菏泽钓场\">菏泽</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/hebi/\" title=\"鹤壁钓场\">鹤壁</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/huangshi/\" title=\"黄石钓场\">黄石</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/huanggang/\" title=\"黄冈钓场\">黄冈</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/hengyang/\" title=\"衡阳钓场\">衡阳</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/huaihua/\" title=\"怀化钓场\">怀化</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/huizhou/\" title=\"惠州钓场\">惠州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/heyuan/\" title=\"河源钓场\">河源</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/hezhou/\" title=\"贺州钓场\">贺州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/hechi/\" title=\"河池钓场\">河池</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/haikou/\" title=\"海口钓场\">海口</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/honghe/\" title=\"红河钓场\">红河</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/hanzhong/\" title=\"汉中钓场\">汉中</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/haidong/\" title=\"海东钓场\">海东</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/haibeicangzu/\" title=\"海北藏族钓场\">海北藏族</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/huangnan/\" title=\"黄南钓场\">黄南</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/hainancangzu/\" title=\"海南藏族钓场\">海南藏族</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/haixi/\" title=\"海西钓场\">海西</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/hami/\" title=\"哈密钓场\">哈密</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/hetian/\" title=\"和田钓场\">和田</a></li>\n" +
//                "</ul>\n" +
//                "</dd>\n" +
//                "<dt><a href=\"javascript:void(0);\" name=\"j\">J</a></dt>\n" +
//                "<dd>\n" +
//                "<ul>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/jincheng/\" title=\"晋城钓场\">晋城</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/jinzhong/\" title=\"晋中钓场\">晋中</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/jinzhou/\" title=\"锦州钓场\">锦州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/jilin/\" title=\"吉林钓场\">吉林</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/jixi/\" title=\"鸡西钓场\">鸡西</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/jiamusi/\" title=\"佳木斯钓场\">佳木斯</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/jiaxing/\" title=\"嘉兴钓场\">嘉兴</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/jinhua/\" title=\"金华钓场\">金华</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/jingdezhen/\" title=\"景德镇钓场\">景德镇</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/jiujiang/\" title=\"九江钓场\">九江</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/jian/\" title=\"吉安钓场\">吉安</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/jinan/\" title=\"济南钓场\">济南</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/jining/\" title=\"济宁钓场\">济宁</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/jiaozuo/\" title=\"焦作钓场\">焦作</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/jingmen/\" title=\"荆门钓场\">荆门</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/jingzhou/\" title=\"荆州钓场\">荆州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/jiangmen/\" title=\"江门钓场\">江门</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/jieyang/\" title=\"揭阳钓场\">揭阳</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/jiayuguan/\" title=\"嘉峪关钓场\">嘉峪关</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/jinchang/\" title=\"金昌钓场\">金昌</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/jiuquan/\" title=\"酒泉钓场\">酒泉</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/jiyuan/\" title=\"济源钓场\">济源</a></li>\n" +
//                "</ul>\n" +
//                "</dd>\n" +
//                "<dt><a href=\"javascript:void(0);\" name=\"k\">K</a></dt>\n" +
//                "<dd>\n" +
//                "<ul>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/kaifeng/\" title=\"开封钓场\">开封</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/kunming/\" title=\"昆明钓场\">昆明</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/kelamayi/\" title=\"克拉玛依钓场\">克拉玛依</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/kezilesu/\" title=\"克孜勒苏钓场\">克孜勒苏</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/kashi/\" title=\"喀什钓场\">喀什</a></li>\n" +
//                "</ul>\n" +
//                "</dd>\n" +
//                "<dt><a href=\"javascript:void(0);\" name=\"l\">L</a></dt>\n" +
//                "<dd>\n" +
//                "<ul>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/langfang/\" title=\"廊坊钓场\">廊坊</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/linfen/\" title=\"临汾钓场\">临汾</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/lvliang/\" title=\"吕梁钓场\">吕梁</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/liaoyang/\" title=\"辽阳钓场\">辽阳</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/liaoyuan/\" title=\"辽源钓场\">辽源</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/lianyungang/\" title=\"连云港钓场\">连云港</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/lishui/\" title=\"丽水钓场\">丽水</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/liuan/\" title=\"六安钓场\">六安</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/longyan/\" title=\"龙岩钓场\">龙岩</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/laiwu/\" title=\"莱芜钓场\">莱芜</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/linyi/\" title=\"临沂钓场\">临沂</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/liaocheng/\" title=\"聊城钓场\">聊城</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/luoyang/\" title=\"洛阳钓场\">洛阳</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/luohe/\" title=\"漯河钓场\">漯河</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/loudi/\" title=\"娄底钓场\">娄底</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/liuzhou/\" title=\"柳州钓场\">柳州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/laibin/\" title=\"来宾钓场\">来宾</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/luzhou/\" title=\"泸州钓场\">泸州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/leshan/\" title=\"乐山钓场\">乐山</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/liangshan/\" title=\"凉山钓场\">凉山</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/liupanshui/\" title=\"六盘水钓场\">六盘水</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/lijiang/\" title=\"丽江钓场\">丽江</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/lincang/\" title=\"临沧钓场\">临沧</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/lasa/\" title=\"拉萨钓场\">拉萨</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/linzhi/\" title=\"林芝钓场\">林芝</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/lanzhou/\" title=\"兰州钓场\">兰州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/longnan/\" title=\"陇南钓场\">陇南</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/linxia/\" title=\"临夏钓场\">临夏</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/lingao/\" title=\"临高钓场\">临高</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/ledong/\" title=\"乐东钓场\">乐东</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/lingshui/\" title=\"陵水钓场\">陵水</a></li>\n" +
//                "</ul>\n" +
//                "</dd>\n" +
//                "<dt><a href=\"javascript:void(0);\" name=\"m\">M</a></dt>\n" +
//                "<dd>\n" +
//                "<ul>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/mudanjiang/\" title=\"牡丹江钓场\">牡丹江</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/maanshan/\" title=\"马鞍山钓场\">马鞍山</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/maoming/\" title=\"茂名钓场\">茂名</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/meizhou/\" title=\"梅州钓场\">梅州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/mianyang/\" title=\"绵阳钓场\">绵阳</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/meishan/\" title=\"眉山钓场\">眉山</a></li>\n" +
//                "</ul>\n" +
//                "</dd>\n" +
//                "<dt><a href=\"javascript:void(0);\" name=\"n\">N</a></dt>\n" +
//                "<dd>\n" +
//                "<ul>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/nanjing/\" title=\"南京钓场\">南京</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/nantong/\" title=\"南通钓场\">南通</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/ningbo/\" title=\"宁波钓场\">宁波</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/nanping/\" title=\"南平钓场\">南平</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/ningde/\" title=\"宁德钓场\">宁德</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/nanchang/\" title=\"南昌钓场\">南昌</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/nanyang/\" title=\"南阳钓场\">南阳</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/nanning/\" title=\"南宁钓场\">南宁</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/neijiang/\" title=\"内江钓场\">内江</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/nanchong/\" title=\"南充钓场\">南充</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/nujiang/\" title=\"怒江钓场\">怒江</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/naqu/\" title=\"那曲钓场\">那曲</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/nanshaqundao/\" title=\"南沙群岛钓场\">南沙群岛</a></li>\n" +
//                "</ul>\n" +
//                "</dd>\n" +
//                "<dt><a href=\"javascript:void(0);\" name=\"p\">P</a></dt>\n" +
//                "<dd>\n" +
//                "<ul>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/panjin/\" title=\"盘锦钓场\">盘锦</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/putian/\" title=\"莆田钓场\">莆田</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/pingxiang/\" title=\"萍乡钓场\">萍乡</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/pingdingshan/\" title=\"平顶山钓场\">平顶山</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/puyang/\" title=\"濮阳钓场\">濮阳</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/panzhihua/\" title=\"攀枝花钓场\">攀枝花</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/puer/\" title=\"普洱钓场\">普洱</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/pingliang/\" title=\"平凉钓场\">平凉</a></li>\n" +
//                "</ul>\n" +
//                "</dd>\n" +
//                "<dt><a href=\"javascript:void(0);\" name=\"q\">Q</a></dt>\n" +
//                "<dd>\n" +
//                "<ul>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/qinhuangdao/\" title=\"秦皇岛钓场\">秦皇岛</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/qiqihaer/\" title=\"齐齐哈尔钓场\">齐齐哈尔</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/qitaihe/\" title=\"七台河钓场\">七台河</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/quzhou/\" title=\"衢州钓场\">衢州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/quanzhou/\" title=\"泉州钓场\">泉州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/qingdao/\" title=\"青岛钓场\">青岛</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/qingyuan/\" title=\"清远钓场\">清远</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/qinzhou/\" title=\"钦州钓场\">钦州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/qianxinan/\" title=\"黔西南钓场\">黔西南</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/qiandongnan/\" title=\"黔东南钓场\">黔东南</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/qiannan/\" title=\"黔南钓场\">黔南</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/qujing/\" title=\"曲靖钓场\">曲靖</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/qingyang/\" title=\"庆阳钓场\">庆阳</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/qianjiang/\" title=\"潜江钓场\">潜江</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/qionghai/\" title=\"琼海钓场\">琼海</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/qiongzhong/\" title=\"琼中钓场\">琼中</a></li>\n" +
//                "</ul>\n" +
//                "</dd>\n" +
//                "<dt><a href=\"javascript:void(0);\" name=\"r\">R</a></dt>\n" +
//                "<dd>\n" +
//                "<ul>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/rizhao/\" title=\"日照钓场\">日照</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/rikaze/\" title=\"日喀则钓场\">日喀则</a></li>\n" +
//                "</ul>\n" +
//                "</dd>\n" +
//                "<dt><a href=\"javascript:void(0);\" name=\"s\">S</a></dt>\n" +
//                "<dd>\n" +
//                "<ul>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/shijiazhuang/\" title=\"石家庄钓场\">石家庄</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/shuozhou/\" title=\"朔州钓场\">朔州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/shenyang/\" title=\"沈阳钓场\">沈阳</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/siping/\" title=\"四平钓场\">四平</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/songyuan/\" title=\"松原钓场\">松原</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/shuangyashan/\" title=\"双鸭山钓场\">双鸭山</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/suihua/\" title=\"绥化钓场\">绥化</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/shanghai/\" title=\"上海钓场\">上海</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/suzhou/\" title=\"苏州钓场\">苏州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/suqian/\" title=\"宿迁钓场\">宿迁</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/shaoxing/\" title=\"绍兴钓场\">绍兴</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/suzhou1/\" title=\"宿州钓场\">宿州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/sanming/\" title=\"三明钓场\">三明</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/shangrao/\" title=\"上饶钓场\">上饶</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/sanmenxia/\" title=\"三门峡钓场\">三门峡</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/shangqiu/\" title=\"商丘钓场\">商丘</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/shiyan/\" title=\"十堰钓场\">十堰</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/suizhou/\" title=\"随州钓场\">随州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/shaoyang/\" title=\"邵阳钓场\">邵阳</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/shaoguan/\" title=\"韶关钓场\">韶关</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/shenzhen/\" title=\"深圳钓场\">深圳</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/shantou/\" title=\"汕头钓场\">汕头</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/shanwei/\" title=\"汕尾钓场\">汕尾</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/sanya/\" title=\"三亚钓场\">三亚</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/suining/\" title=\"遂宁钓场\">遂宁</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/shannan/\" title=\"山南钓场\">山南</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/shangluo/\" title=\"商洛钓场\">商洛</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/shizuishan/\" title=\"石嘴山钓场\">石嘴山</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/shennongjia/\" title=\"神农架钓场\">神农架</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/shihezi/\" title=\"石河子钓场\">石河子</a></li>\n" +
//                "</ul>\n" +
//                "</dd>\n" +
//                "<dt><a href=\"javascript:void(0);\" name=\"t\">T</a></dt>\n" +
//                "<dd>\n" +
//                "<ul>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/tangshan/\" title=\"唐山钓场\">唐山</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/taiyuan/\" title=\"太原钓场\">太原</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/tongliao/\" title=\"通辽钓场\">通辽</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/tieling/\" title=\"铁岭钓场\">铁岭</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/tonghua/\" title=\"通化钓场\">通化</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/taizhou1/\" title=\"泰州钓场\">泰州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/taizhou/\" title=\"台州钓场\">台州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/tongling/\" title=\"铜陵钓场\">铜陵</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/taian/\" title=\"泰安钓场\">泰安</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/tongren/\" title=\"铜仁钓场\">铜仁</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/tongchuan/\" title=\"铜川钓场\">铜川</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/tianshui/\" title=\"天水钓场\">天水</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/tulufan/\" title=\"吐鲁番钓场\">吐鲁番</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/tacheng/\" title=\"塔城钓场\">塔城</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/tianjin/\" title=\"天津钓场\">天津</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/tianmen/\" title=\"天门钓场\">天门</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/tunchang/\" title=\"屯昌钓场\">屯昌</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/tumushuke/\" title=\"图木舒克钓场\">图木舒克</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/taibei/\" title=\"台北钓场\">台北</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/taizhong/\" title=\"台中钓场\">台中</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/tainan/\" title=\"台南钓场\">台南</a></li>\n" +
//                "</ul>\n" +
//                "</dd>\n" +
//                "<dt><a href=\"javascript:void(0);\" name=\"w\">W</a></dt>\n" +
//                "<dd>\n" +
//                "<ul>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/wuhai/\" title=\"乌海钓场\">乌海</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/wulanchabu/\" title=\"乌兰察布钓场\">乌兰察布</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/wuxi/\" title=\"无锡钓场\">无锡</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/wenzhou/\" title=\"温州钓场\">温州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/wuhu/\" title=\"芜湖钓场\">芜湖</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/weifang/\" title=\"潍坊钓场\">潍坊</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/weihai/\" title=\"威海钓场\">威海</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/wuhan/\" title=\"武汉钓场\">武汉</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/wuzhou/\" title=\"梧州钓场\">梧州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/wenshan/\" title=\"文山钓场\">文山</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/weinan/\" title=\"渭南钓场\">渭南</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/wuwei/\" title=\"武威钓场\">武威</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/wuzhong/\" title=\"吴忠钓场\">吴忠</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/wulumuqi/\" title=\"乌鲁木齐钓场\">乌鲁木齐</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/wuzhishan/\" title=\"五指山钓场\">五指山</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/wenchang/\" title=\"文昌钓场\">文昌</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/wanning/\" title=\"万宁钓场\">万宁</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/wujiaqu/\" title=\"五家渠钓场\">五家渠</a></li>\n" +
//                "</ul>\n" +
//                "</dd>\n" +
//                "<dt><a href=\"javascript:void(0);\" name=\"x\">X</a></dt>\n" +
//                "<dd>\n" +
//                "<ul>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/xingtai/\" title=\"邢台钓场\">邢台</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/xinzhou/\" title=\"忻州钓场\">忻州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/xinganmeng/\" title=\"兴安盟钓场\">兴安盟</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/xilinguole/\" title=\"锡林郭勒钓场\">锡林郭勒</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/xuzhou/\" title=\"徐州钓场\">徐州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/xuancheng/\" title=\"宣城钓场\">宣城</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/xiamen/\" title=\"厦门钓场\">厦门</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/xinyu/\" title=\"新余钓场\">新余</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/xinxiang/\" title=\"新乡钓场\">新乡</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/xuchang/\" title=\"许昌钓场\">许昌</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/xinyang/\" title=\"信阳钓场\">信阳</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/xiangyang/\" title=\"襄阳钓场\">襄阳</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/xiaogan/\" title=\"孝感钓场\">孝感</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/xianning/\" title=\"咸宁钓场\">咸宁</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/xiangtan/\" title=\"湘潭钓场\">湘潭</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/xiangxi/\" title=\"湘西钓场\">湘西</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/xishuangbanna/\" title=\"西双版纳钓场\">西双版纳</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/xian/\" title=\"西安钓场\">西安</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/xianyang/\" title=\"咸阳钓场\">咸阳</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/xining/\" title=\"西宁钓场\">西宁</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/xiantao/\" title=\"仙桃钓场\">仙桃</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/xishaqundao/\" title=\"西沙群岛钓场\">西沙群岛</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/xianggang/\" title=\"香港钓场\">香港</a></li>\n" +
//                "</ul>\n" +
//                "</dd>\n" +
//                "<dt><a href=\"javascript:void(0);\" name=\"y\">Y</a></dt>\n" +
//                "<dd>\n" +
//                "<ul>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/yangquan/\" title=\"阳泉钓场\">阳泉</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/yuncheng/\" title=\"运城钓场\">运城</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/yingkou/\" title=\"营口钓场\">营口</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/yanbian/\" title=\"延边钓场\">延边</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/yichun1/\" title=\"伊春钓场\">伊春</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/yancheng/\" title=\"盐城钓场\">盐城</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/yangzhou/\" title=\"扬州钓场\">扬州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/yingtan/\" title=\"鹰潭钓场\">鹰潭</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/yichun/\" title=\"宜春钓场\">宜春</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/yantai/\" title=\"烟台钓场\">烟台</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/yichang/\" title=\"宜昌钓场\">宜昌</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/yueyang/\" title=\"岳阳钓场\">岳阳</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/yiyang/\" title=\"益阳钓场\">益阳</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/yongzhou/\" title=\"永州钓场\">永州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/yangjiang/\" title=\"阳江钓场\">阳江</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/yunfu/\" title=\"云浮钓场\">云浮</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/yulin/\" title=\"玉林钓场\">玉林</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/yibin/\" title=\"宜宾钓场\">宜宾</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/yaan/\" title=\"雅安钓场\">雅安</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/yuxi/\" title=\"玉溪钓场\">玉溪</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/yanan/\" title=\"延安钓场\">延安</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/yulin1/\" title=\"榆林钓场\">榆林</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/yushu/\" title=\"玉树钓场\">玉树</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/yinchuan/\" title=\"银川钓场\">银川</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/yili/\" title=\"伊犁钓场\">伊犁</a></li>\n" +
//                "</ul>\n" +
//                "</dd>\n" +
//                "<dt><a href=\"javascript:void(0);\" name=\"z\">Z</a></dt>\n" +
//                "<dd>\n" +
//                "<ul>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/zhangjiakou/\" title=\"张家口钓场\">张家口</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/zhenjiang/\" title=\"镇江钓场\">镇江</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/zhoushan/\" title=\"舟山钓场\">舟山</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/zhangzhou/\" title=\"漳州钓场\">漳州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/zibo/\" title=\"淄博钓场\">淄博</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/zaozhuang/\" title=\"枣庄钓场\">枣庄</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/zhengzhou/\" title=\"郑州钓场\">郑州</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/zhoukou/\" title=\"周口钓场\">周口</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/zhumadian/\" title=\"驻马店钓场\">驻马店</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/zhuzhou/\" title=\"株洲钓场\">株洲</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/zhangjiajie/\" title=\"张家界钓场\">张家界</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/zhuhai/\" title=\"珠海钓场\">珠海</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/zhanjiang/\" title=\"湛江钓场\">湛江</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/zhaoqing/\" title=\"肇庆钓场\">肇庆</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/zhongshan/\" title=\"中山钓场\">中山</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/zigong/\" title=\"自贡钓场\">自贡</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/ziyang/\" title=\"资阳钓场\">资阳</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/zunyi/\" title=\"遵义钓场\">遵义</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/zhaotong/\" title=\"昭通钓场\">昭通</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/zhangye/\" title=\"张掖钓场\">张掖</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/zhongwei/\" title=\"中卫钓场\">中卫</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/diaochang/zhongshaqundao/\" title=\"中沙群岛钓场\">中沙群岛</a></li>\n" +
//                "</ul>\n" +
//                "</dd>\n" +
//                "</dl>\n" +
//                "<ul class=\"letter\">\n" +
//                "<li><a href=\"#a\">A</a></li>\n" +
//                "<li><a href=\"#b\">B</a></li>\n" +
//                "<li><a href=\"#c\">C</a></li>\n" +
//                "<li><a href=\"#d\">D</a></li>\n" +
//                "<li><a href=\"#e\">E</a></li>\n" +
//                "<li><a href=\"#f\">F</a></li>\n" +
//                "<li><a href=\"#g\">G</a></li>\n" +
//                "<li><a href=\"#h\">H</a></li>\n" +
//                "<li><a href=\"#j\">J</a></li>\n" +
//                "<li><a href=\"#k\">K</a></li>\n" +
//                "<li><a href=\"#l\">L</a></li>\n" +
//                "<li><a href=\"#m\">M</a></li>\n" +
//                "<li><a href=\"#n\">N</a></li>\n" +
//                "<li><a href=\"#p\">P</a></li>\n" +
//                "<li><a href=\"#q\">Q</a></li>\n" +
//                "<li><a href=\"#r\">R</a></li>\n" +
//                "<li><a href=\"#s\">S</a></li>\n" +
//                "<li><a href=\"#t\">T</a></li>\n" +
//                "<li><a href=\"#w\">W</a></li>\n" +
//                "<li><a href=\"#x\">X</a></li>\n" +
//                "<li><a href=\"#y\">Y</a></li>\n" +
//                "<li><a href=\"#z\">Z</a></li>\n" +
//                "</ul>\n" +
//                "<div class=\"app-load\">\n" +
//                "<a class=\"dyr-apdl-url\" href=\"javascript:void(0);\">打开钓鱼人APP&nbsp;&nbsp;查看更多内容</a>\n" +
//                "</div>\n" +
//                "<footer>\n" +
//                "<ul>\n" +
//                "<li><a href=\"http://m.diaoyu.com/daohang/\">网站导航</a></li>\n" +
//                "<li><a href=\"http://m.diaoyu.com/site/feedback.htm\">反馈意见</a></li>\n" +
//                "<li><a href=\"javascript:void(0);\" class=\"backTop\">返回顶部</a></li>\n" +
//                "</ul>\n" +
//                "<span>Copyright © 2009-2020 钓鱼之家 版权所有</span>\n" +
//                "</footer>\n" +
//                "<div class=\"fixed-app\">\n" +
//                "<a class=\"dyr-apdl-url\" href=\"javascript:void(0);\">APP内打开</a>\n" +
//                "</div>\n" +
//                "<script type=\"text/javascript\" src=\"http://static.diaoyu.com/diaoyucms/m/v201905/js/??common.js?version=0.177\"></script>\n" +
//                "<div style=\"display:none\">\n" +
//                "<script>var _hmt =_hmt ||[];(function() {var hm =document.createElement(\"script\");hm.src =\"//hm.baidu.com/hm.js?cd069e91d9d6a19b9b95bf6a43ec1d51\";var s =document.getElementsByTagName(\"script\")[0];s.parentNode.insertBefore(hm,s);})();</script>\n" +
//                "</div>\n" +
//                "</body>\n" +
//                "</html>","text/html",Utils.UTF_8);
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

        /*@Nullable
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {

            return super.shouldInterceptRequest(view, Utils.getReadModeStr(MainActivity.this,url,Utils.UTF_8));
        }*/
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Nullable
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
            byte[] readModeBytes = Utils.getReadModeBytes(MainActivity.this, request.getUrl().toString(), Utils.UTF_8);
            if (readModeBytes != null)
                return new WebResourceResponse("text/html", Utils.UTF_8, new ByteArrayInputStream(readModeBytes));
            return super.shouldInterceptRequest(view, request);
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
