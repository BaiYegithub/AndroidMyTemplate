package app.vp.cn.mixed;

import android.app.Activity;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by baiye
 * Date: 2020/10/30
 * Time: 11:10
 * Description:
 */
public class Utils {
    public static String UTF_8 = "UTF-8";
    public static String GB2312 = "gb2312";
    public static byte[] getReadModeBytes(Activity mActivity, String urlString, String charsetName) {
        String res = "";
        String cssString = "";
        String utlString = httGetByteArrayOutputStream(urlString, charsetName);
        if (TextUtils.isEmpty(urlString))
            return null;
        StringBuilder contents = new StringBuilder();
        InputStreamReader reader = null;
        try {

            reader = new InputStreamReader(mActivity.getResources().openRawResource(R.raw.browser_product_mode));

            BufferedReader br = new BufferedReader(reader);

            String line;
            while ((line = br.readLine()) != null) {
                contents.append(line);
            }

            cssString = "<style type=\"text/css\">" + contents.toString().trim().replace("\n", "") + "</style>";
            int headEnd = utlString.indexOf("</head>");
            if (headEnd > 0) {
                res = utlString.substring(0, headEnd) + cssString + utlString.substring(headEnd, utlString.length());
            } else {
                res = "<head>" + cssString + "</head>" + utlString;
            }
            br.close();
            reader.close();
            return res.getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public static String getReadModeStr(Activity mActivity, String urlString, String charsetName) {
        String res = "";
        String cssString = "";
        String utlString = httGetByteArrayOutputStream(urlString, charsetName);
        if (TextUtils.isEmpty(urlString))
            return null;
        StringBuilder contents = new StringBuilder();
        InputStreamReader reader = null;
        try {

            reader = new InputStreamReader(mActivity.getResources().openRawResource(R.raw.browser_product_mode));

            BufferedReader br = new BufferedReader(reader);

            String line;
            while ((line = br.readLine()) != null) {
                contents.append(line);
            }

            cssString = "<style type=\"text/css\">" + contents.toString().trim().replace("\n", "") + "</style>";
            int headEnd = utlString.indexOf("</head>");
            if (headEnd > 0) {
                res = utlString.substring(0, headEnd) + cssString + utlString.substring(headEnd, utlString.length());
            } else {
                res = "<head>" + cssString + "</head>" + utlString;
            }
            br.close();
            reader.close();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 通过ByteArrayOutputStream 获取网络数据
     *
     * @param urlString
     * @param charsetName
     * @return
     */
    public static String httGetByteArrayOutputStream(String urlString, String charsetName) {
        String utlString = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(5000);
            InputStream in = connection.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            in.close();
            utlString = baos.toString(charsetName);
            baos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return utlString;
    }
}
