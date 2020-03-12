package app.vp.cn.ui.utils;

import java.util.Locale;

/**
 * @author zhuchongkun
 * @package com.lu.utils
 * @fileName LanguageUtils
 * @describe 语言方法整合
 */

public class LanguageUtils {
    /**
     * 获取当前默认语言
     *
     * @return
     */
    public static String getLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * 获取当前国家或地区
     *
     * @return
     */
    public static String getCountryName() {
        Locale loc = Locale.getDefault();
        return loc.getCountry();
    }

    /**
     * zh zh-cn cn 	简体中文
     * zh-hk hk 	繁体中文
     * en 	英文
     * de 	德语
     * es 	西班牙语
     * fr 	法语
     * it 	意大利语
     * jp 	日语             ja
     * kr 	韩语			 ko
     * ru 	俄语
     * in 	印度语
     * th 	泰语			 tr
     *
     * @return
     */
    public static String getHeWeatherLang() {
        Locale loc = Locale.getDefault();
        String lang = "en";
        if (loc != null) {
            lang = loc.getLanguage();
            if (lang.equalsIgnoreCase("ja")) {
                lang = "jp";
            } else if (lang.equalsIgnoreCase("ko")) {
                lang = "kr";
            } else if (lang.equalsIgnoreCase("tr")) {
                lang = "th";
            } else if (lang.equalsIgnoreCase("zh") || lang.equalsIgnoreCase("de") || lang.equalsIgnoreCase("es") ||
                    lang.equalsIgnoreCase("fr") || lang.equalsIgnoreCase("it") || lang.equalsIgnoreCase("ru") ||
                    lang.equalsIgnoreCase("in")) {
                return lang;
            }
        }
        return lang;

    }

    /**
     * 判断是否是大陆用户
     *
     * @return
     */
    public static boolean isChinaMainlandUser() {
        Locale loc = Locale.getDefault();
        return loc != null && loc.getLanguage().equals("zh") && loc.getCountry().equals("CN");
    }

    /**
     * 判断是否是台湾用户
     *
     * @return
     */
    public static boolean isChinaTWUser() {
        Locale loc = Locale.getDefault();
//		return loc != null && loc.getLanguage().equals("zh") && (loc.getCountry().equals("TW") || loc.getCountry().equals("HK"));
        return loc != null && loc.getLanguage().equals("zh") && (!loc.getCountry().equals("CN"));
    }

    /**
     * 判断是否是汉语用户
     *
     * @return
     */
    public static boolean isChinaContainsTWUser() {
        Locale loc = Locale.getDefault();
        return loc != null && loc.getLanguage().equals("zh");
    }

    /**
     * 判断是否是英语用户
     *
     * @return
     */
    public static boolean isEnglishUser() {
        Locale loc = Locale.getDefault();
        return loc != null && loc.getLanguage().equals("en");
    }

    /**
     * 判断英语国家是否是美国
     *
     * @return
     */
    public static boolean isUSAUser() {
        Locale loc = Locale.getDefault();
        return loc != null && loc.getLanguage().equals("en") && loc.getCountry().equals("US");
    }
    /**
     * 判断是否是日本
     *
     * @return
     */
    public static boolean isJPUser() {
        Locale loc = Locale.getDefault();
        return loc != null && loc.getLanguage().equals("ja");
    }

}
