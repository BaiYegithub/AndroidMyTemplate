package app.vp.cn.third.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import okhttp3.internal.http.HttpHeaders;
import retrofit2.Response;

public class FileUtils {
    public static final String SDPATH = Environment.getExternalStorageDirectory() + "/";

    public static File createSDfile(String fileName) throws IOException {
        File file = new File(SDPATH + fileName);
        if (!file.exists())
            file.createNewFile();
        return file;
    }

    public static File createSDdir(String dirName) {
        File dir = new File(SDPATH + dirName);
        if (!dir.exists())
            dir.mkdirs();
        return dir;
    }

    /**
     * 创建文件夹，传入的参数须带SDPATH
     *
     * @param dirName 全路径(包含SDPATH)
     */
    public static void createDirFullPath(String dirName) {
        File dir = new File(dirName);
        if (!dir.exists())
            dir.mkdirs();
    }

    /**
     * 把文件写入相应的目录下
     *
     * @param path
     * @param fileName
     * @param input
     * @param listener
     * @return
     */
    public static File write2SD(String path, String fileName, InputStream input,
                                FileWriteListener listener) {
        File file = null;
        OutputStream out = null;
        int len;
        try {
            createSDdir(path);
            file = createSDfile(path + fileName);
            out = new FileOutputStream(file);
            byte buffer[] = new byte[4 * 1024];
            while ((len = input.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }

            if (listener != null) {
                listener.onFileWriteSuccess();
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (listener != null) {
                listener.onFileWriteFail();
            }
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return file;
    }

    /**
     * 把字符串写入相应的目录下文件
     *
     * @param path
     * @param fileName
     * @param listener
     * @return
     */
    public static void writeStr2SD(String path, String fileName, String str,
                                   FileWriteListener listener) {
        OutputStream out = null;
        try {
            createSDdir(path);
            File file = createSDfile(path + fileName);
            out = new FileOutputStream(file);
            out.write(str.getBytes());

            if (listener != null) {
                listener.onFileWriteSuccess();
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (listener != null) {
                listener.onFileWriteFail();
            }
        } finally {
            try {
                if (out != null) out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Boolean isFileExist(String fileName) {
        File file = new File(SDPATH + fileName);
        return file.exists();
    }

    public static Boolean isDirExist(String dirName) {
        File dir = new File(SDPATH + dirName);
        return dir.exists();
    }

    public static boolean isSDcardExist() {
        String status = Environment.getExternalStorageState();
        if (!Environment.MEDIA_MOUNTED.equals(status))
            return false;
        else
            return true;
    }

    /**
     * 删除文件
     *
     * @param file
     */
    public static void deleteFile(File file) {
        if (file != null && file.exists()) {
            file.delete();
        }
    }

    /**
     * 删除文件
     *
     * @param path 路径(不包含SDPATH)
     */
    public static void deleteFile(String path) {
        File file = new File(SDPATH + path);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * 删除文件，传入的参数须带SDPATH
     *
     * @param path 全路径(包含SDPATH)
     */
    public static void deleteFileFullPath(String path) {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }

    public static abstract interface FileWriteListener {
        public abstract void onFileWriteFail();

        public abstract void onFileWriteSuccess();
    }



    //删除文件夹里的所以文件及其文件夹
    //param path 文件夹完整绝对路径
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);//再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }

    //删除文件夹
    //param folderPath 文件夹完整绝对路径

    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); //删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            myFilePath.delete(); //删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean fileCopy(String source, String des) {
        //复制目标文件
        File desFile = new File(des);
        if (desFile.exists())
            return true;
        //输入源文件 
        File sourceFile = new File(source);

        FileInputStream fr = null;
        FileOutputStream bw = null;
        try {
            fr = new FileInputStream(sourceFile);
            bw = new FileOutputStream(desFile);

            //buffer 
            byte[] b = new byte[512];
            while (fr.read(b) != -1) {
                bw.write(b);
            }
            bw.flush();
            return true;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block 
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block 
            e.printStackTrace();
        } finally {
            if (fr != null)
                try {
                    fr.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block 
                    e.printStackTrace();
                }
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block 
                    e.printStackTrace();
                }
            }
        }
        return false;
    }


//    public static String getFileNameByUrl(String url) {
////		try {
////			String suffixes="avi|mpeg|3gp|mp3|mp4|wav|jpeg|gif|jpg|png|apk|exe|pdf|rar|zip|docx|doc";
////			Pattern pat=Pattern.compile("[\\w]+[\\.]("+suffixes+")");//正则判断
////			Matcher mc=pat.matcher(url);//条件匹配
////			while(mc.find()){
////			            String substring = mc.group();//截取文件名后缀名
////			            return substring;
////			        }
////		} catch (Exception e) {
////			// TODO 自动生成的 catch 块
////			e.printStackTrace();
////		}
//        return "ad" + url.hashCode();
//    }


    /**
     * 将输入流写入到SD卡的文件中
     *
     * @param path     文件路径，不包含SDPATH和文件名
     * @param fileName 文件名
     * @param input    输入流，写入的内容
     */
    public static File write2SD(String path, String fileName, InputStream input) {
        return writeFile(SDPATH + path + fileName, input);
    }

    /**
     * 将输入流写入到文件中
     *
     * @param fileName 全路径文件名
     * @param input    输入流，写入的内容
     */
    public static File writeFile(String fileName, InputStream input) {
        File file = new File(fileName);
        writeFile(file, input);
        return file;
    }

    /**
     * 将输入流写入到文件中
     *
     * @param file  全路径文件名
     * @param input 输入流，写入的内容
     */
    public static File writeFile(File file, InputStream input) {
        OutputStream out = null;
        try {
            createDirs(file.getParent());
            if (!file.exists()) {
                file.createNewFile();
            }
            out = new FileOutputStream(file);

            byte buffer[] = new byte[4 * 1024];
            int len = -1;
            while ((len = input.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (Exception e) {

            }
        }

        return file;
    }

    public static File createDirs(String dirName) {
        File dir = new File(dirName);
        if (!dir.exists())
            dir.mkdirs();
        return dir;
    }

    public static File createSDdirs(String dirName) {
        return createDirs(SDPATH + dirName);
    }


    // 根缓存目录
    private static String cacheRootPath = "";

    /**
     * sd卡是否可用
     *
     * @return
     */
    public static boolean isSdCardAvailable() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    /**
     * 创建根缓存目录
     *
     * @return
     */
    @SuppressLint("NewApi")
    public static String createRootPath(Context context) {
        if (isSdCardAvailable()) {
            // /sdcard/Android/data/<application package>/cache
            cacheRootPath = context.getExternalCacheDir().getPath();
        } else {
            // /data/data/<application package>/cache
            cacheRootPath = context.getCacheDir().getPath();
        }
        return cacheRootPath;
    }

    /**
     * 创建文件夹
     *
     * @param dirPath
     * @return 创建失败返回""
     */
    private static String createDir(String dirPath) {
        try {
            File dir = new File(dirPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            return dir.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dirPath;
    }

    /**
     * 获取图片缓存目录
     *
     * @return 创建失败, 返回""
     */
    public static String getImageCachePath(Context context) {
        String path = createDir(createRootPath(context) + File.separator + "img"
                + File.separator);
        return path;
    }

    /**
     * 获取图片裁剪缓存目录
     *
     * @return 创建失败, 返回""
     */
    public static String getImageCropCachePath(Context context) {
        String path = createDir(createRootPath(context) + File.separator + "imgCrop"
                + File.separator);

        return path;
    }

    /**
     * 删除文件或者文件夹
     *
     * @param file
     */
    public static void deleteFileOrDirectory(File file) {
        try {
            if (file.isFile()) {
                file.delete();
                return;
            }
            if (file.isDirectory()) {
                File[] childFiles = file.listFiles();
                // 删除空文件夹
                if (childFiles == null || childFiles.length == 0) {
                    file.delete();
                    return;
                }
                // 递归删除文件夹下的子文件
                for (int i = 0; i < childFiles.length; i++) {
                    deleteFileOrDirectory(childFiles[i]);
                }
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将内容写入文件
     *
     * @param filePath eg:/mnt/sdcard/demo.txt
     * @param content  内容
     */
    public static void writeFileSdcard(String filePath, String content,
                                       boolean isAppend) {
        if (!TextUtils.isEmpty(content)) {
            FileOutputStream fout = null;
            try {
                fout = new FileOutputStream(filePath, isAppend);
                byte[] bytes = content.getBytes("UTF-8");
                fout.write(bytes);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (fout != null) {
                    try {
                        fout.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * @param fileName 全路径的文件名
     * @return
     */
    public static String readFileFromSdCard(String fileName) {
        try {
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String readline = "";
            StringBuffer sb = new StringBuffer();
            while ((readline = br.readLine()) != null) {
                System.out.println("readline:" + readline);
                sb.append(readline);
            }
            br.close();
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }






    public static String longToGMT(long lastModify) {
        Date d = new Date(lastModify);
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        return sdf.format(d);
    }

    public static long GMTToLong(String GMT) throws ParseException {
        if (GMT == null || "".equals(GMT)) {
            return new Date().getTime();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date date = sdf.parse(GMT);
        return date.getTime();
    }

    public static void close(Closeable closeable) throws IOException {
        if (closeable != null) {
            closeable.close();
        }
    }


    public static String lastModify(Response<?> response) {
        return response.headers().get("Last-Modified");
    }

    public static long contentLength(Response<?> response) {
        return HttpHeaders.contentLength(response.headers());
    }

    public static String transferEncoding(Response<?> response) {
        return response.headers().get("Transfer-Encoding");
    }

    public static boolean notSupportRange(Response<?> response) {
        return TextUtils.isEmpty(contentRange(response)) || contentLength(response) == -1;
    }

    public static boolean serverFileChanged(Response<Void> resp) {
        return resp.code() == 200;
    }

    public static boolean serverFileNotChange(Response<Void> resp) {
        return resp.code() == 206;
    }

    public static String formatSize(double size) {
        String hrSize;

        //double b = size;
        double k = size / 1024.0;
        double m = ((size / 1024.0) / 1024.0);
        double g = (((size / 1024.0) / 1024.0) / 1024.0);
        double t = ((((size / 1024.0) / 1024.0) / 1024.0) / 1024.0);

        DecimalFormat dec = new DecimalFormat("0.00");

        if (t > 1) {
            hrSize = dec.format(t).concat(" TB");
        } else if (g > 1) {
            hrSize = dec.format(g).concat(" GB");
        } else if (m > 1) {
            hrSize = dec.format(m).concat(" MB");
        } else { //if (k > 1)
            hrSize = dec.format(k).concat(" KB");
        }/* else {
            hrSize = dec.format(b).concat(" B");
        }*/
        return hrSize;
    }

    private static String contentRange(Response<?> response) {
        return response.headers().get("Content-Range");
    }

    /**
     * 获取文件后缀名
     * @return
     */
    private static String getExtension(String fileName) {
        String suffix = "";
        int idx = fileName.lastIndexOf(".");
        if (idx > 0) {
            suffix = fileName.substring(idx + 1);
        }
        return suffix;
    }

    /**
     * 获取文件file的MIME类型
     * @return
     */
    public static String getMimeType(String fileName) {
        String extension = getExtension(fileName);
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
    }
    /**
     * 用此方法必须实现
     <provider
     android:name="android.support.v4.content.FileProvider"
     android:authorities="${applicationId}.provider"
     android:exported="false"
     android:grantUriPermissions="true">
     <meta-data
     android:name="android.support.FILE_PROVIDER_PATHS"
     android:resource="@xml/provider_paths"/>
     </provider>
     *
     * 打开下载文件
     * @param context
     * @param fileName
     * @param file
     */
    /**
     * 截取Url最后一段作为文件保存名称
     * @param url url
     */
}