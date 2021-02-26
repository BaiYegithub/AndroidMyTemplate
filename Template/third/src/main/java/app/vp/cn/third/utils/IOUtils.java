package app.vp.cn.third.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 输入输出工具类
 */
public class IOUtils {
	/**
	 * 关闭输入流
	 */
	public static void closeInputStream(InputStream inputStream) {
		try {
			if (inputStream != null) {
				inputStream.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭输出流
	 */
	public static void closeOutputStream(OutputStream outputStream) {
		try {
			if (outputStream != null) {
				outputStream.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
