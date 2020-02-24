package app.vp.cn.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 公历格式转农历格式
 */
public class CalendarUtils {
    /**
     * 用来表示1900年到2099年间农历年份的相关信息，共24位bit的16进制表示，其中：
     * 1. 前4位表示该年闰哪个月；
     * 2. 5-17位表示农历年份13个月的大小月分布，0表示小，1表示大；
     * 3. 最后7位表示农历年首（正月初一）对应的公历日期。
     * 以2014年的数据0x955ABF为例说明：
     * 1001 0101 0101 1010 1011 1111
     * 闰九月  农历正月初一对应公历1月31号
     */
    private static final int LUNAR_INFO[] = {
            0x84B6BF,/*1900*/
            0x04AE53, 0x0A5748, 0x5526BD, 0x0D2650, 0x0D9544, 0x46AAB9, 0x056A4D, 0x09AD42, 0x24AEB6, 0x04AE4A,/*1901-1910*/
            0x6A4DBE, 0x0A4D52, 0x0D2546, 0x5D52BA, 0x0B544E, 0x0D6A43, 0x296D37, 0x095B4B, 0x749BC1, 0x049754,/*1911-1920*/
            0x0A4B48, 0x5B25BC, 0x06A550, 0x06D445, 0x4ADAB8, 0x02B64D, 0x095742, 0x2497B7, 0x04974A, 0x664B3E,/*1921-1930*/
            0x0D4A51, 0x0EA546, 0x56D4BA, 0x05AD4E, 0x02B644, 0x393738, 0x092E4B, 0x7C96BF, 0x0C9553, 0x0D4A48,/*1931-1940*/
            0x6DA53B, 0x0B554F, 0x056A45, 0x4AADB9, 0x025D4D, 0x092D42, 0x2C95B6, 0x0A954A, 0x7B4ABD, 0x06CA51,/*1941-1950*/
            0x0B5546, 0x555ABB, 0x04DA4E, 0x0A5B43, 0x352BB8, 0x052B4C, 0x8A953F, 0x0E9552, 0x06AA48, 0x6AD53C,/*1951-1960*/
            0x0AB54F, 0x04B645, 0x4A5739, 0x0A574D, 0x052642, 0x3E9335, 0x0D9549, 0x75AABE, 0x056A51, 0x096D46,/*1961-1970*/
            0x54AEBB, 0x04AD4F, 0x0A4D43, 0x4D26B7, 0x0D254B, 0x8D52BF, 0x0B5452, 0x0B6A47, 0x696D3C, 0x095B50,/*1971-1980*/
            0x049B45, 0x4A4BB9, 0x0A4B4D, 0xAB25C2, 0x06A554, 0x06D449, 0x6ADA3D, 0x0AB651, 0x095746, 0x5497BB,/*1981-1990*/
            0x04974F, 0x064B44, 0x36A537, 0x0EA54A, 0x86B2BF, 0x05AC53, 0x0AB647, 0x5936BC, 0x092E50, 0x0C9645,/*1991-2000*/
            0x4D4AB8, 0x0D4A4C, 0x0DA541, 0x25AAB6, 0x056A49, 0x7AADBD, 0x025D52, 0x092D47, 0x5C95BA, 0x0A954E,/*2001-2010*/
            0x0B4A43, 0x4B5537, 0x0AD54A, 0x955ABF, 0x04BA53, 0x0A5B48, 0x652BBC, 0x052B50, 0x0A9345, 0x474AB9,/*2011-2020*/
            0x06AA4C, 0x0AD541, 0x24DAB6, 0x04B64A, 0x6a573D, 0x0A4E51, 0x0D2646, 0x5E933A, 0x0D534D, 0x05AA43,/*2021-2030*/
            0x36B537, 0x096D4B, 0xB4AEBF, 0x04AD53, 0x0A4D48, 0x6D25BC, 0x0D254F, 0x0D5244, 0x5DAA38, 0x0B5A4C,/*2031-2040*/
            0x056D41, 0x24ADB6, 0x049B4A, 0x7A4BBE, 0x0A4B51, 0x0AA546, 0x5B52BA, 0x06D24E, 0x0ADA42, 0x355B37,/*2041-2050*/
            0x09374B, 0x8497C1, 0x049753, 0x064B48, 0x66A53C, 0x0EA54F, 0x06AA44, 0x4AB638, 0x0AAE4C, 0x092E42,/*2051-2060*/
            0x3C9735, 0x0C9649, 0x7D4ABD, 0x0D4A51, 0x0DA545, 0x55AABA, 0x056A4E, 0x0A6D43, 0x452EB7, 0x052D4B,/*2061-2070*/
            0x8A95BF, 0x0A9553, 0x0B4A47, 0x6B553B, 0x0AD54F, 0x055A45, 0x4A5D38, 0x0A5B4C, 0x052B42, 0x3A93B6,/*2071-2080*/
            0x069349, 0x7729BD, 0x06AA51, 0x0AD546, 0x54DABA, 0x04B64E, 0x0A5743, 0x452738, 0x0D264A, 0x8E933E,/*2081-2090*/
            0x0D5252, 0x0DAA47, 0x66B53B, 0x056D4F, 0x04AE45, 0x4A4EB9, 0x0A4D4C, 0x0D1541, 0x2D92B5          /*2091-2099*/
    };
    private static int[] lunar_month_days = {1887, 0x1694, 0x16aa, 0x4ad5, 0xab6, 0xc4b7, 0x4ae, 0xa56, 0xb52a, 0x1d2a,
            0xd54, 0x75aa, 0x156a, 0x1096d, 0x95c, 0x14ae, 0xaa4d, 0x1a4c, 0x1b2a, 0x8d55, 0xad4, 0x135a, 0x495d, 0x95c,
            0xd49b, 0x149a, 0x1a4a, 0xbaa5, 0x16a8, 0x1ad4, 0x52da, 0x12b6, 0xe937, 0x92e, 0x1496, 0xb64b, 0xd4a, 0xda8,
            0x95b5, 0x56c, 0x12ae, 0x492f, 0x92e, 0xcc96, 0x1a94, 0x1d4a, 0xada9, 0xb5a, 0x56c, 0x726e, 0x125c, 0xf92d,
            0x192a, 0x1a94, 0xdb4a, 0x16aa, 0xad4, 0x955b, 0x4ba, 0x125a, 0x592b, 0x152a, 0xf695, 0xd94, 0x16aa, 0xaab5,
            0x9b4, 0x14b6, 0x6a57, 0xa56, 0x1152a, 0x1d2a, 0xd54, 0xd5aa, 0x156a, 0x96c, 0x94ae, 0x14ae, 0xa4c, 0x7d26,
            0x1b2a, 0xeb55, 0xad4, 0x12da, 0xa95d, 0x95a, 0x149a, 0x9a4d, 0x1a4a, 0x11aa5, 0x16a8, 0x16d4, 0xd2da,
            0x12b6, 0x936, 0x9497, 0x1496, 0x1564b, 0xd4a, 0xda8, 0xd5b4, 0x156c, 0x12ae, 0xa92f, 0x92e, 0xc96, 0x6d4a,
            0x1d4a, 0x10d65, 0xb58, 0x156c, 0xb26d, 0x125c, 0x192c, 0x9a95, 0x1a94, 0x1b4a, 0x4b55, 0xad4, 0xf55b,
            0x4ba, 0x125a, 0xb92b, 0x152a, 0x1694, 0x96aa, 0x15aa, 0x12ab5, 0x974, 0x14b6, 0xca57, 0xa56, 0x1526,
            0x8e95, 0xd54, 0x15aa, 0x49b5, 0x96c, 0xd4ae, 0x149c, 0x1a4c, 0xbd26, 0x1aa6, 0xb54, 0x6d6a, 0x12da,
            0x1695d, 0x95a, 0x149a, 0xda4b, 0x1a4a, 0x1aa4, 0xbb54, 0x16b4, 0xada, 0x495b, 0x936, 0xf497, 0x1496,
            0x154a, 0xb6a5, 0xda4, 0x15b4, 0x6ab6, 0x126e, 0x1092f, 0x92e, 0xc96, 0xcd4a, 0x1d4a, 0xd64, 0x956c, 0x155c,
            0x125c, 0x792e, 0x192c, 0xfa95, 0x1a94, 0x1b4a, 0xab55, 0xad4, 0x14da, 0x8a5d, 0xa5a, 0x1152b, 0x152a,
            0x1694, 0xd6aa, 0x15aa, 0xab4, 0x94ba, 0x14b6, 0xa56, 0x7527, 0xd26, 0xee53, 0xd54, 0x15aa, 0xa9b5, 0x96c,
            0x14ae, 0x8a4e, 0x1a4c, 0x11d26, 0x1aa4, 0x1b54, 0xcd6a, 0xada, 0x95c, 0x949d, 0x149a, 0x1a2a, 0x5b25,
            0x1aa4, 0xfb52, 0x16b4, 0xaba, 0xa95b, 0x936, 0x1496, 0x9a4b, 0x154a, 0x136a5, 0xda4, 0x15ac};
    private static int[] solar_1_1 = {
            1887, 0xec04c, 0xec23f, 0xec435, 0xec649, 0xec83e, 0xeca51, 0xecc46, 0xece3a,
            0xed04d, 0xed242, 0xed436, 0xed64a, 0xed83f, 0xeda53, 0xedc48, 0xede3d, 0xee050, 0xee244, 0xee439, 0xee64d,
            0xee842, 0xeea36, 0xeec4a, 0xeee3e, 0xef052, 0xef246, 0xef43a, 0xef64e, 0xef843, 0xefa37, 0xefc4b, 0xefe41,
            0xf0054, 0xf0248, 0xf043c, 0xf0650, 0xf0845, 0xf0a38, 0xf0c4d, 0xf0e42, 0xf1037, 0xf124a, 0xf143e, 0xf1651,
            0xf1846, 0xf1a3a, 0xf1c4e, 0xf1e44, 0xf2038, 0xf224b, 0xf243f, 0xf2653, 0xf2848, 0xf2a3b, 0xf2c4f, 0xf2e45,
            0xf3039, 0xf324d, 0xf3442, 0xf3636, 0xf384a, 0xf3a3d, 0xf3c51, 0xf3e46, 0xf403b, 0xf424e, 0xf4443, 0xf4638,
            0xf484c, 0xf4a3f, 0xf4c52, 0xf4e48, 0xf503c, 0xf524f, 0xf5445, 0xf5639, 0xf584d, 0xf5a42, 0xf5c35, 0xf5e49,
            0xf603e, 0xf6251, 0xf6446, 0xf663b, 0xf684f, 0xf6a43, 0xf6c37, 0xf6e4b, 0xf703f, 0xf7252, 0xf7447, 0xf763c,
            0xf7850, 0xf7a45, 0xf7c39, 0xf7e4d, 0xf8042, 0xf8254, 0xf8449, 0xf863d, 0xf8851, 0xf8a46, 0xf8c3b, 0xf8e4f,
            0xf9044, 0xf9237, 0xf944a, 0xf963f, 0xf9853, 0xf9a47, 0xf9c3c, 0xf9e50, 0xfa045, 0xfa238, 0xfa44c, 0xfa641,
            0xfa836, 0xfaa49, 0xfac3d, 0xfae52, 0xfb047, 0xfb23a, 0xfb44e, 0xfb643, 0xfb837, 0xfba4a, 0xfbc3f, 0xfbe53,
            0xfc048, 0xfc23c, 0xfc450, 0xfc645, 0xfc839, 0xfca4c, 0xfcc41, 0xfce36, 0xfd04a, 0xfd23d, 0xfd451, 0xfd646,
            0xfd83a, 0xfda4d, 0xfdc43, 0xfde37, 0xfe04b, 0xfe23f, 0xfe453, 0xfe648, 0xfe83c, 0xfea4f, 0xfec44, 0xfee38,
            0xff04c, 0xff241, 0xff436, 0xff64a, 0xff83e, 0xffa51, 0xffc46, 0xffe3a, 0x10004e, 0x100242, 0x100437,
            0x10064b, 0x100841, 0x100a53, 0x100c48, 0x100e3c, 0x10104f, 0x101244, 0x101438, 0x10164c, 0x101842,
            0x101a35, 0x101c49, 0x101e3d, 0x102051, 0x102245, 0x10243a, 0x10264e, 0x102843, 0x102a37, 0x102c4b,
            0x102e3f, 0x103053, 0x103247, 0x10343b, 0x10364f, 0x103845, 0x103a38, 0x103c4c, 0x103e42, 0x104036,
            0x104249, 0x10443d, 0x104651, 0x104846, 0x104a3a, 0x104c4e, 0x104e43, 0x105038, 0x10524a, 0x10543e,
            0x105652, 0x105847, 0x105a3b, 0x105c4f, 0x105e45, 0x106039, 0x10624c, 0x106441, 0x106635, 0x106849,
            0x106a3d, 0x106c51, 0x106e47, 0x10703c, 0x10724f, 0x107444, 0x107638, 0x10784c, 0x107a3f, 0x107c53,
            0x107e48};
    final private static long[] lunarInfo = new long[]{0x04bd8, 0x04ae0,
            0x0a570, 0x054d5, 0x0d260, 0x0d950, 0x16554, 0x056a0, 0x09ad0,
            0x055d2, 0x04ae0, 0x0a5b6, 0x0a4d0, 0x0d250, 0x1d255, 0x0b540,
            0x0d6a0, 0x0ada2, 0x095b0, 0x14977, 0x04970, 0x0a4b0, 0x0b4b5,
            0x06a50, 0x06d40, 0x1ab54, 0x02b60, 0x09570, 0x052f2, 0x04970,
            0x06566, 0x0d4a0, 0x0ea50, 0x06e95, 0x05ad0, 0x02b60, 0x186e3,
            0x092e0, 0x1c8d7, 0x0c950, 0x0d4a0, 0x1d8a6, 0x0b550, 0x056a0,
            0x1a5b4, 0x025d0, 0x092d0, 0x0d2b2, 0x0a950, 0x0b557, 0x06ca0,
            0x0b550, 0x15355, 0x04da0, 0x0a5d0, 0x14573, 0x052d0, 0x0a9a8,
            0x0e950, 0x06aa0, 0x0aea6, 0x0ab50, 0x04b60, 0x0aae4, 0x0a570,
            0x05260, 0x0f263, 0x0d950, 0x05b57, 0x056a0, 0x096d0, 0x04dd5,
            0x04ad0, 0x0a4d0, 0x0d4d4, 0x0d250, 0x0d558, 0x0b540, 0x0b5a0,
            0x195a6, 0x095b0, 0x049b0, 0x0a974, 0x0a4b0, 0x0b27a, 0x06a50,
            0x06d40, 0x0af46, 0x0ab60, 0x09570, 0x04af5, 0x04970, 0x064b0,
            0x074a3, 0x0ea50, 0x06b58, 0x055c0, 0x0ab60, 0x096d5, 0x092e0,
            0x0c960, 0x0d954, 0x0d4a0, 0x0da50, 0x07552, 0x056a0, 0x0abb7,
            0x025d0, 0x092d0, 0x0cab5, 0x0a950, 0x0b4a0, 0x0baa4, 0x0ad50,
            0x055d9, 0x04ba0, 0x0a5b0, 0x15176, 0x052b0, 0x0a930, 0x07954,
            0x06aa0, 0x0ad50, 0x05b52, 0x04b60, 0x0a6e6, 0x0a4e0, 0x0d260,
            0x0ea65, 0x0d530, 0x05aa0, 0x076a3, 0x096d0, 0x04bd7, 0x04ad0,
            0x0a4d0, 0x1d0b6, 0x0d250, 0x0d520, 0x0dd45, 0x0b5a0, 0x056d0,
            0x055b2, 0x049b0, 0x0a577, 0x0a4b0, 0x0aa50, 0x1b255, 0x06d20,
            0x0ada0};
    /**
     * 选择年份
     */
    private static char[][] sectionalTermYear = {
            {13, 49, 85, 117, 149, 185, 201, 250, 250},
            {13, 45, 81, 117, 149, 185, 201, 250, 250},
            {13, 48, 84, 112, 148, 184, 200, 201, 250},
            {13, 45, 76, 108, 140, 172, 200, 201, 250},
            {13, 44, 72, 104, 132, 168, 200, 201, 250},
            {5, 33, 68, 96, 124, 152, 188, 200, 201},
            {29, 57, 85, 120, 148, 176, 200, 201, 250},
            {13, 48, 76, 104, 132, 168, 196, 200, 201},
            {25, 60, 88, 120, 148, 184, 200, 201, 250},
            {16, 44, 76, 108, 144, 172, 200, 201, 250},
            {28, 60, 92, 124, 160, 192, 200, 201, 250},
            {17, 53, 85, 124, 156, 188, 200, 201, 250}};
    /**
     * 基本天数集合
     */
    private static char[][] principleTermMap = {
            {21, 21, 21, 21, 21, 20, 21, 21, 21, 20, 20, 21, 21, 20, 20, 20,
                    20, 20, 20, 20, 20, 19, 20, 20, 20, 19, 19, 20},
            {20, 19, 19, 20, 20, 19, 19, 19, 19, 19, 19, 19, 19, 18, 19, 19,
                    19, 18, 18, 19, 19, 18, 18, 18, 18, 18, 18, 18},
            {21, 21, 21, 22, 21, 21, 21, 21, 20, 21, 21, 21, 20, 20, 21, 21,
                    20, 20, 20, 21, 20, 20, 20, 20, 19, 20, 20, 20, 20},
            {20, 21, 21, 21, 20, 20, 21, 21, 20, 20, 20, 21, 20, 20, 20, 20,
                    19, 20, 20, 20, 19, 19, 20, 20, 19, 19, 19, 20, 20},
            {21, 22, 22, 22, 21, 21, 22, 22, 21, 21, 21, 22, 21, 21, 21, 21,
                    20, 21, 21, 21, 20, 20, 21, 21, 20, 20, 20, 21, 21},
            {22, 22, 22, 22, 21, 22, 22, 22, 21, 21, 22, 22, 21, 21, 21, 22,
                    21, 21, 21, 21, 20, 21, 21, 21, 20, 20, 21, 21, 21},
            {23, 23, 24, 24, 23, 23, 23, 24, 23, 23, 23, 23, 22, 23, 23, 23,
                    22, 22, 23, 23, 22, 22, 22, 23, 22, 22, 22, 22, 23},
            {23, 24, 24, 24, 23, 23, 24, 24, 23, 23, 23, 24, 23, 23, 23, 23,
                    22, 23, 23, 23, 22, 22, 23, 23, 22, 22, 22, 23, 23},
            {23, 24, 24, 24, 23, 23, 24, 24, 23, 23, 23, 24, 23, 23, 23, 23,
                    22, 23, 23, 23, 22, 22, 23, 23, 22, 22, 22, 23, 23},
            {24, 24, 24, 24, 23, 24, 24, 24, 23, 23, 24, 24, 23, 23, 23, 24,
                    23, 23, 23, 23, 22, 23, 23, 23, 22, 22, 23, 23, 23},
            {23, 23, 23, 23, 22, 23, 23, 23, 22, 22, 23, 23, 22, 22, 22, 23,
                    22, 22, 22, 22, 21, 22, 22, 22, 21, 21, 22, 22, 22},
            {22, 22, 23, 23, 22, 22, 22, 23, 22, 22, 22, 22, 21, 22, 22, 22,
                    21, 21, 22, 22, 21, 21, 21, 22, 21, 21, 21, 21, 22}};
    /**
     * 年集合
     */
    private static char[][] principleTermYear = {
            {13, 45, 81, 113, 149, 185, 201},
            {21, 57, 93, 125, 161, 193, 201},
            {21, 56, 88, 120, 152, 188, 200, 201},
            {21, 49, 81, 116, 144, 176, 200, 201},
            {17, 49, 77, 112, 140, 168, 200, 201},
            {28, 60, 88, 116, 148, 180, 200, 201},
            {25, 53, 84, 112, 144, 172, 200, 201},
            {29, 57, 89, 120, 148, 180, 200, 201},
            {17, 45, 73, 108, 140, 168, 200, 201},
            {28, 60, 92, 124, 160, 192, 200, 201},
            {16, 44, 80, 112, 148, 180, 200, 201},
            {17, 53, 88, 120, 156, 188, 200, 201}};
    private static char[][] sectionalTermMap = {
            {7, 6, 6, 6, 6, 6, 6, 6, 6, 5, 6, 6, 6, 5, 5, 6, 6, 5, 5, 5, 5, 5,
                    5, 5, 5, 4, 5, 5},
            {5, 4, 5, 5, 5, 4, 4, 5, 5, 4, 4, 4, 4, 4, 4, 4, 4, 3, 4, 4, 4, 3,
                    3, 4, 4, 3, 3, 3},
            {6, 6, 6, 7, 6, 6, 6, 6, 5, 6, 6, 6, 5, 5, 6, 6, 5, 5, 5, 6, 5, 5,
                    5, 5, 4, 5, 5, 5, 5},
            {5, 5, 6, 6, 5, 5, 5, 6, 5, 5, 5, 5, 4, 5, 5, 5, 4, 4, 5, 5, 4, 4,
                    4, 5, 4, 4, 4, 4, 5},
            {6, 6, 6, 7, 6, 6, 6, 6, 5, 6, 6, 6, 5, 5, 6, 6, 5, 5, 5, 6, 5, 5,
                    5, 5, 4, 5, 5, 5, 5},
            {6, 6, 7, 7, 6, 6, 6, 7, 6, 6, 6, 6, 5, 6, 6, 6, 5, 5, 6, 6, 5, 5,
                    5, 6, 5, 5, 5, 5, 4, 5, 5, 5, 5},
            {7, 8, 8, 8, 7, 7, 8, 8, 7, 7, 7, 8, 7, 7, 7, 7, 6, 7, 7, 7, 6, 6,
                    7, 7, 6, 6, 6, 7, 7},
            {8, 8, 8, 9, 8, 8, 8, 8, 7, 8, 8, 8, 7, 7, 8, 8, 7, 7, 7, 8, 7, 7,
                    7, 7, 6, 7, 7, 7, 6, 6, 7, 7, 7},
            {8, 8, 8, 9, 8, 8, 8, 8, 7, 8, 8, 8, 7, 7, 8, 8, 7, 7, 7, 8, 7, 7,
                    7, 7, 6, 7, 7, 7, 7},
            {9, 9, 9, 9, 8, 9, 9, 9, 8, 8, 9, 9, 8, 8, 8, 9, 8, 8, 8, 8, 7, 8,
                    8, 8, 7, 7, 8, 8, 8},
            {8, 8, 8, 8, 7, 8, 8, 8, 7, 7, 8, 8, 7, 7, 7, 8, 7, 7, 7, 7, 6, 7,
                    7, 7, 6, 6, 7, 7, 7},
            {7, 8, 8, 8, 7, 7, 8, 8, 7, 7, 7, 8, 7, 7, 7, 7, 6, 7, 7, 7, 6, 6,
                    7, 7, 6, 6, 6, 7, 7}};
    /* solar terms information */
    private static final int[] SOLAR_TERM_INFO = {
            0, 21208, 42467, 63836, 85337, 107014, 128867, 150921,
            173149, 195551, 218072, 240693, 263343, 285989, 308563, 331033,
            353350, 375494, 397447, 419210, 440795, 462224, 483532, 504758
    };
    final private static int[] year20 = new int[]{1, 4, 1, 2, 1, 2, 1, 1, 2,
            1, 2, 1};
    final private static int[] year19 = new int[]{0, 3, 0, 1, 0, 1, 0, 0, 1,
            0, 1, 0};
    final private static int[] year2000 = new int[]{0, 3, 1, 2, 1, 2, 1, 1,
            2, 1, 2, 1};
    public final static String[] nStr1 = new String[]{"", "正", "二", "三", "四",
            "五", "六", "七", "八", "九", "十", "冬", "腊"};
    private final static String[] Gan = new String[]{"甲", "乙", "丙", "丁", "戊",
            "己", "庚", "辛", "壬", "癸"};
    private final static String[] Zhi = new String[]{"子", "丑", "寅", "卯", "辰",
            "巳", "午", "未", "申", "酉", "戌", "亥"};
    private final static String[] Animals = new String[]{"鼠", "牛", "虎", "兔",
            "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪"};
    private static final String[] Orientation = {"南", "东", "北", "西"};
    /**
     * 用于保存24节气
     */
    private static String[] principleTermNames = {"大寒", "雨水", "春分", "谷雨",
            "小满", "夏至", "大暑", "处暑", "秋分", "霜降", "小雪", "冬至"};
    /**
     * 用于保存24节气
     */
    private static String[] sectionalTermNames = {"小寒", "立春", "惊蛰", "清明",
            "立夏", "芒种", "小暑", "立秋", "白露", "寒露", "立冬", "大雪"};


    /**
     * 支持转换的最小农历年份
     */
    private static final int MIN_YEAR = 1900;
    private static GregorianCalendar mUTCCalendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));

    /**
     * 传回农历 y年的总天数
     *
     * @param y
     * @return
     */
    final private static int lYearDays(int y) {
        int i, sum = 348;
        for (i = 0x8000; i > 0x8; i >>= 1) {
            if ((lunarInfo[y - 1900] & i) != 0)
                sum += 1;
        }
        return (sum + leapDays(y));
    }

    /**
     * 传回农历 y年闰月的天数
     *
     * @param y
     * @return
     */
    final private static int leapDays(int y) {
        if (leapMonth(y) != 0) {
            if ((lunarInfo[y - 1900] & 0x10000) != 0)
                return 30;
            else
                return 29;
        } else
            return 0;
    }

    /**
     * 传回农历 y年闰哪个月 1-12 , 没闰传回 0
     *
     * @param y
     * @return
     */
    final private static int leapMonth(int y) {
        return (int) (lunarInfo[y - 1900] & 0xf);
    }

    /**
     * 传回农历 y年m月的总天数
     *
     * @param y
     * @param m
     * @return
     */
    final private static int monthDays(int y, int m) {
        if ((lunarInfo[y - 1900] & (0x10000 >> m)) == 0)
            return 29;
        else
            return 30;
    }

    /**
     * 传回农历 y年的生肖
     *
     * @param y
     * @return
     */
    final public static String animalsYear(int y) {
        return Animals[(y - 4) % 12];
    }

    /**
     * 传入 月日的offset 传回干支,0=甲子
     *
     * @param num
     * @return
     */
    final private static String cyclicalm(int num) {
        return (Gan[num % 10] + Zhi[num % 12]);
    }


    public static String getCyclicalMonth(int month) {
        return cyclicalm(month) + "月";
    }

    public static String getCyclicalDay(int day) {
        return cyclicalm(day) + "日";
    }

    /**
     * 传入 offset 传回干支, 0=甲子
     *
     * @param y
     * @return
     */
    final public static String cyclical(int y) {
        int num = y - 1900 + 36;
        return (cyclicalm(num));
    }

    /**
     * 传出农历.year0 .month1 .day2 .yearCyl3 .monCyl4 .dayCyl5 .isLeap6
     *
     * @param y
     * @param m
     * @return
     */
    final private long[] Lunar(int y, int m) {
        long[] nongDate = new long[7];
        int i = 0, temp = 0, leap = 0;
        Date baseDate = new GregorianCalendar(1900 + 1900, 1, 31).getTime();
        Date objDate = new GregorianCalendar(y + 1900, m, 1).getTime();
        long offset = (objDate.getTime() - baseDate.getTime()) / 86400000L;
        if (y < 2000)
            offset += year19[m - 1];
        if (y > 2000)
            offset += year20[m - 1];
        if (y == 2000)
            offset += year2000[m - 1];
        nongDate[5] = offset + 40;
        nongDate[4] = 14;
        for (i = 1900; i < 2050 && offset > 0; i++) {
            temp = lYearDays(i);
            offset -= temp;
            nongDate[4] += 12;
        }
        if (offset < 0) {
            offset += temp;
            i--;
            nongDate[4] -= 12;
        }
        nongDate[0] = i;
        nongDate[3] = i - 1864;
        leap = leapMonth(i); // 闰哪个月
        nongDate[6] = 0;
        for (i = 1; i < 13 && offset > 0; i++) {
            // 闰月
            if (leap > 0 && i == (leap + 1) && nongDate[6] == 0) {
                --i;
                nongDate[6] = 1;
                temp = leapDays((int) nongDate[0]);
            } else {
                temp = monthDays((int) nongDate[0], i);
            }
            // 解除闰月
            if (nongDate[6] == 1 && i == (leap + 1))
                nongDate[6] = 0;
            offset -= temp;
            if (nongDate[6] == 0)
                nongDate[4]++;
        }
        if (offset == 0 && leap > 0 && i == leap + 1) {
            if (nongDate[6] == 1) {
                nongDate[6] = 0;
            } else {
                nongDate[6] = 1;
                --i;
                --nongDate[4];
            }
        }
        if (offset < 0) {
            offset += temp;
            --i;
            --nongDate[4];
        }
        nongDate[1] = i;
        nongDate[2] = offset + 1;
        return nongDate;
    }

    /**
     * 传出y年m月d日对应的农历.year0 .month1 .day2 .yearCyl3 .monCyl4 .dayCyl5 .isLeap6
     *
     * @param y
     * @param m
     * @param d
     * @return
     */
    final public static long[] calElement(int y, int m, int d) {
        long[] nongDate = new long[7];
        int i = 0, temp = 0, leap = 0;
        Date baseDate = new GregorianCalendar(0 + 1900, 0, 31).getTime();
        Date objDate = new GregorianCalendar(y, m - 1, d).getTime();
        long offset = (objDate.getTime() - baseDate.getTime()) / 86400000L;
        nongDate[5] = offset + 40;
        nongDate[4] = 14;
        for (i = 1900; i < 2050 && offset > 0; i++) {
            temp = lYearDays(i);
            offset -= temp;
            nongDate[4] += 12;
        }
        if (offset < 0) {
            offset += temp;
            i--;
            nongDate[4] -= 12;
        }
        nongDate[0] = i;
        nongDate[3] = i - 1864;
        leap = leapMonth(i); // 闰哪个月
        nongDate[6] = 0;
        for (i = 1; i < 13 && offset > 0; i++) {
            // 闰月
            if (leap > 0 && i == (leap + 1) && nongDate[6] == 0) {
                --i;
                nongDate[6] = 1;
                temp = leapDays((int) nongDate[0]);
            } else {
                temp = monthDays((int) nongDate[0], i);
            }
            // 解除闰月
            if (nongDate[6] == 1 && i == (leap + 1))
                nongDate[6] = 0;
            offset -= temp;
            if (nongDate[6] == 0)
                nongDate[4]++;
        }
        if (offset == 0 && leap > 0 && i == leap + 1) {
            if (nongDate[6] == 1) {
                nongDate[6] = 0;
            } else {
                nongDate[6] = 1;
                --i;
                --nongDate[4];
            }
        }
        if (offset < 0) {
            offset += temp;
            --i;
            --nongDate[4];
        }
        nongDate[1] = i;
        nongDate[2] = offset + 1;
        return nongDate;
    }

    public final static String getChinaDate(int day) {
        String a = "";
        if (day == 10)
            return "初十";
        if (day == 20)
            return "二十";
        if (day == 30)
            return "三十";
        int two = (int) ((day) / 10);
        if (two == 0)
            a = "初";
        if (two == 1)
            a = "十";
        if (two == 2)
            a = "廿";
        if (two == 3)
            a = "三";
        int one = (int) (day % 10);
        switch (one) {
            case 1:
                a += "一";
                break;
            case 2:
                a += "二";
                break;
            case 3:
                a += "三";
                break;
            case 4:
                a += "四";
                break;
            case 5:
                a += "五";
                break;
            case 6:
                a += "六";
                break;
            case 7:
                a += "七";
                break;
            case 8:
                a += "八";
                break;
            case 9:
                a += "九";
                break;
        }
        return a;
    }

    public static String today() {
        try {
            Calendar today = Calendar.getInstance(Locale.SIMPLIFIED_CHINESE);
            int year = today.get(Calendar.YEAR);
            int month = today.get(Calendar.MONTH) + 1;
            int date = today.get(Calendar.DATE);
            long[] l = calElement(year, month, date);
            StringBuffer sToday = new StringBuffer();
            sToday.append(sdf.format(today.getTime()));
            sToday.append(" 农历");
            sToday.append(cyclical(year));
            sToday.append('(');
            sToday.append(animalsYear(year));
            sToday.append(")年");
            sToday.append(nStr1[(int) l[1]]);
            sToday.append("月");
            sToday.append(getChinaDate((int) (l[2])));
            return sToday.toString();
        } catch (Exception e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return "";
    }

    public static String oneDay(int year, int month, int day) {
        try {
            Calendar today = Calendar.getInstance(Locale.SIMPLIFIED_CHINESE);
            long[] l = calElement(year, month, day);
            StringBuffer sToday = new StringBuffer();
            //       sToday.append(sdf.format(today.getTime()));
            //       sToday.append(" 农历");
            //       sToday.append(cyclical(year));
            //       sToday.append('(');
            //       sToday.append(AnimalsYear(year));
            //       sToday.append(")年");
            sToday.append(nStr1[(int) l[1]]);
            sToday.append("月");
            sToday.append(getChinaDate((int) (l[2])));
            return sToday.toString();
        } catch (Exception e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 传回农历year年month月的总天数，总共有13个月包括闰月
     * 0-199的下标
     *
     * @param year  将要计算的年份
     * @param month 将要计算的月份
     * @return 传回农历 year年month月的总天数
     */
    public static int daysInLunarMonth(int year, int month) {
        if ((LUNAR_INFO[year - MIN_YEAR] & (0x100000 >> month)) == 0)
            return 29;
        else
            return 30;
    }

    /**
     * 用于获取中国的传统节日
     *
     * @param month 农历的月 1-12
     * @param day   农历日
     * @return 中国传统节日
     */
    public static String getLunarHoliday(int year, int month, int day) {
        String message = "";
        if (month == 1 && day == 1) {
            message = "春节";
        } else if (month == 1 && day == 15) {
            message = "元宵节";
        } else if (month == 5 && day == 5) {
            message = "端午节";
        } else if (month == 7 && day == 7) {
            message = "七夕节";
        } else if (month == 8 && day == 15) {
            message = "中秋节";
        } else if (month == 9 && day == 9) {
            message = "重阳节";
        } else if (month == 12 && day == 8) {
            message = "腊八节";
        } else {
            if (month == 12) {
                if ((((daysInLunarMonth(year, month) == 29) && day == 29))
                        || ((((daysInLunarMonth(year, month) == 30) && day == 30)))) {
                    message = "除夕";
                }
            }
        }
        return message;
    }


    // 判断是否为闰年
    public static boolean isLeapYear(int year) {
        if (year % 100 == 0 && year % 400 == 0) {
            return true;
        } else if (year % 100 != 0 && year % 4 == 0) {
            return true;
        }
        return false;
    }

    /**
     * 通过年份和月份 得到当月的日子
     *
     * @param year
     * @param month 1-12
     * @return
     */
    public static int getMonthDays(int year, int month) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                if (isLeapYear(year)) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                return -1;
        }
    }


    /**
     * 公历转农历
     */
    public static Lunar solarToLunar(Solar solar) {
        Lunar lunar = new Lunar();
        int index = solar.solarYear - solar_1_1[0];
        int data = (solar.solarYear << 9) | (solar.solarMonth << 5) | (solar.solarDay);
        int solar11 = 0;
        if (solar_1_1[index] > data) {
            index--;
        }
        solar11 = solar_1_1[index];
        int y = getBitInt(solar11, 12, 9);
        int m = getBitInt(solar11, 4, 5);
        int d = getBitInt(solar11, 5, 0);
        long offset = solarToInt(solar.solarYear, solar.solarMonth, solar.solarDay) - solarToInt(y, m, d);

        int days = lunar_month_days[index];
        int leap = getBitInt(days, 4, 13);

        int lunarY = index + solar_1_1[0];
        int lunarM = 1;
        int lunarD = 1;
        offset += 1;
        for (int i = 0; i < 13; i++) {
            int dm = getBitInt(days, 1, 12 - i) == 1 ? 30 : 29;
            if (offset > dm) {
                lunarM++;
                offset -= dm;
            } else {
                break;
            }
        }
        lunarD = (int) (offset);
        lunar.lunarYear = lunarY;
        lunar.lunarMonth = lunarM;
        lunar.isLeap = false;
        if (leap != 0 && lunarM > leap) {
            lunar.lunarMonth = lunarM - 1;
            if (lunarM == leap + 1) {
                lunar.isLeap = true;
            }
        }
        lunar.lunarDay = lunarD;
        return lunar;
    }

    private static int getBitInt(int data, int length, int shift) {
        return (data & (((1 << length) - 1) << shift)) >> shift;
    }

    private static long solarToInt(int y, int m, int d) {
        m = (m + 9) % 12;
        y = y - m / 10;
        return 365 * y + y / 4 - y / 100 + y / 400 + (m * 306 + 5) / 10 + (d - 1);
    }

    /**
     * 阳历
     */
    public static class Solar {
        int solarDay;
        int solarMonth;
        int solarYear;

        public Solar(int solarYear, int solarMonth, int solarDay) {
            this.solarYear = solarYear;
            this.solarMonth = solarMonth;
            this.solarDay = solarDay;
        }
    }

    /**
     * 阴历
     */
    public static class Lunar {
        public boolean isLeap;
        public int lunarDay;
        public int lunarMonth;
        public int lunarYear;
    }

    public static class Cyclical {
        public int cyclicalYear;
        public int cyclicalMonth;
        public int cyclicalDay;

        public void getCyclicalData(int year, int month, int day) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);
            long timeInMillis = calendar.getTime().getTime();
            calendar= Calendar.getInstance();
            calendar.setTimeInMillis(timeInMillis);
            int mSolarYear = calendar.get(Calendar.YEAR);
            int mSolarMonth = calendar.get(Calendar.MONTH);
            int mSolarDay = calendar.get(Calendar.DAY_OF_MONTH);

		/* year in Heavenly Stems and Earthly Branches */
            int term = getSolarTermDay(mSolarYear, 2);
            if (mSolarMonth < 1 || (mSolarMonth == 1 && mSolarDay < term)) {
                cyclicalYear = (mSolarYear - 1900 + 36 - 1) % 60;
            } else {
                cyclicalYear = (mSolarYear - 1900 + 36) % 60;
            }

		/* month in Heavenly Stems and Earthly Branches */
            int firstNode = getSolarTermDay(mSolarYear, mSolarMonth * 2);
            if (mSolarDay < firstNode) {
                cyclicalMonth = ((mSolarYear - 1900) * 12 + mSolarMonth + 12) % 60;
            } else {
                cyclicalMonth = ((mSolarYear - 1900) * 12 + mSolarMonth + 13) % 60;
            }

            cyclicalDay = (int) (getUTC(mSolarYear, mSolarMonth,
                    mSolarDay, 0, 0, 0) / 86400000 + 25567 + 10) % 60;
        }
    }

    private static int getSolarTermDay(int solarYear, int index) {
        long millisec = (long) 31556925974.7 * (solarYear - 1900) +
                SOLAR_TERM_INFO[index] * 60000L;
        return getUTCDay(new Date(millisec + getUTC(1900, 0, 6, 2, 5, 0)));
    }

    private static int getUTCDay(Date date) {
        GregorianCalendar mUTCCalendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        mUTCCalendar.setTimeInMillis(date.getTime());
        return mUTCCalendar.get(Calendar.DAY_OF_MONTH);
    }

    private static long getUTC(int year, int month, int day, int hour, int min, int sec) {
        GregorianCalendar mUTCCalendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        mUTCCalendar.set(year, month, day, hour, min, sec);
        return mUTCCalendar.getTimeInMillis();
    }

    private static SimpleDateFormat sdf = new SimpleDateFormat(
            "yyyy年M月d日 EEEEE");

/**
 * 农历日历工具使用演示
 *
 */
//public static void main(String[] args) {
//   System.out.println(today());
//   System.out.println(oneDay(1989, 9, 10));
//}


    /**
     * 用于获取24节气的值
     *
     * @param solarYear
     * @param solarMonth 正常显示的月份 1-12
     * @param solarDay
     * @return
     */
    public static String getSolartermsMsg(int solarYear, int solarMonth, int solarDay) {
        String str = "";
        if (solarDay == getSectionalTerm(solarYear, solarMonth)) {
            str = sectionalTermNames[solarMonth - 1];
        } else if (solarDay == getPrincipleTerm(solarYear, solarMonth)) {
            str = " " + principleTermNames[solarMonth - 1];
        }
        return str;
    }

    /**
     * @param y
     * @param m
     * @return
     */
    public static int sectionalTerm(int y, int m) {
        if (y < 1901 || y > 2100)
            return 0;
        int index = 0;
        int ry = y - 1901 + 1;
        while (ry >= sectionalTermYear[m - 1][index])
            index++;
        int term = sectionalTermMap[m - 1][4 * index + ry % 4];
        if ((ry == 121) && (m == 4))
            term = 5;
        if ((ry == 132) && (m == 4))
            term = 5;
        if ((ry == 194) && (m == 6))
            term = 6;
        return term;
    }

    /**
     * @param y
     * @param m
     * @return
     */
    private static int principleTerm(int y, int m) {
        if (y < 1901 || y > 2100)
            return 0;
        int index = 0;
        int ry = y - 1901 + 1;
        while (ry >= principleTermYear[m - 1][index])
            index++;
        int term = principleTermMap[m - 1][4 * index + ry % 4];
        if ((ry == 171) && (m == 3))
            term = 21;
        if ((ry == 181) && (m == 5))
            term = 21;
        return term;
    }

    private static int getSectionalTerm(int solarYear, int solarMonth) {
        if (solarYear < 1901 || solarYear > 2100)
            return 1;
        return sectionalTerm(solarYear, solarMonth);
    }

    private static int getPrincipleTerm(int solarYear, int solarMonth) {
        if (solarYear < 1901 || solarYear > 2100)
            return 1;
        return principleTerm(solarYear, solarMonth);
    }


    /**
     * @param year
     * @param month 1-12根据国历获取假期
     * @param day
     * @return
     */
    public static String getHolidayFromSolar(int year, int month, int day) {
        String message = "";
        if (month == 1 && day == 1) {
            message = "元旦";
        } else if (year >= 1997 && month == 2 && day == 2) {//国际
            message = "世界湿地日";
        } else if (month == 2 && day == 14) {
            message = "情人节";
        } else if (year >= 2000 && month == 3 && day == 3) {//国际
            message = "爱耳日";
        } else if (year >= 1963 && month == 3 && day == 5) {//中国
            message = "学雷锋纪念日 青年自愿者服务日";//
        } else if (year >= 1917 && month == 3 && day == 8) {//国际
            message = "妇女节";
        } else if (year >= 2008 && month == 3 && day == 9) {//中国发起
            message = "保护母亲河日";
        } else if (year >= 1979 && month == 3 && day == 12) {
            message = "植树节";
        } else if (year >= 2004 && month == 3 && day == 14) {//中国发起
            message = "国际警察节";
        } else if (year >= 1983 && month == 3 && day == 15) {
            message = "国际消费者权益日";
        } else if (year >= 1977 && month == 3 && day == 21) {//世界
            message = "消除种族歧视日 森林日 睡眠日 儿歌日";
        } else if (year >= 1993 && month == 3 && day == 22) {
            message = "世界水日";
        } else if (year >= 1960 && month == 3 && day == 23) {
            message = "世界气象日";
        } else if (year >= 1995 && month == 3 && day == 24) {
            message = "世界防治结核病日 ";
        } else if (year >= 1961 && month == 3 && day == 27) {
            message = "世界戏剧日";
        } else if (month == 4) {
            if (day == 1) {
                message = "愚人节";
            } else if (day >= 4 && day <= 6) {//中国
                if (year <= 1999) {
                    int compare = (int) (((year - 1900) * 0.2422 + 5.59) - ((year - 1900) / 4));
                    if (compare == day) {
                        message = "清明节";
                    }
                } else {
                    int compare = (int) (((year - 2000) * 0.2422 + 4.81) - ((year - 2000) / 4));
                    if (compare == day) {
                        message = "清明节";
                    }
                }
            }
        }/*else if (month == 3 && day == 16) {//西方春风月圆第一个周日
            message = "复活节";
        }*/ else if (year >= 1948 && month == 4 && day == 7) {
            message = "世界卫生日";
        } else if (year >= 1970 && month == 4 && day == 22) {//
            message = "世界地球日";
        } else if (year >= 1995 && month == 4 && day == 23) {
            message = "世界读书日";
        } else if (year >= 2001 && month == 4 && day == 26) {//
            message = "世界知识产权日";//秘书节
        } else if (year >= 2001 && month == 4 && day == 28) {
            message = "世界安全生产与健康日";
        } else if (month == 5 && day == 1) {
            message = "劳动节";
        } else if (year >= 1949 && month == 5 && day == 4) {
            message = "青年节";
        } else if (year >= 1912 && month == 5 && day == 12) {
            message = "护士节";
        } else if (year >= 1987 && month == 5 && day == 31) {
            message = "世界无烟日";
        }/* else if (month == 4 && day == 15) {//5月的第二个星期日
            message = "母亲节";
        } */ else if (year >= 1949 && month == 6 && day == 1) {
            message = "儿童节";
        } else if (year >= 1972 && month == 6 && day == 5) {//世界
            message = "环境保护日";
        } else if (year >= 1996 && month == 6 && day == 6) {//中国
            message = "爱眼日";
        }/* else if (month == 5 && day == 18) {//6月第三个星期日
            message = "父亲节";
        }*/ else if (year >= 1938 && month == 7 && day == 1) {//中国
            message = "建党节";
        } else if (year >= 1945 && month == 7 && day == 7) {//中国日民坑日战争纪念日
            message = "七七事变纪念日";
        } else if (year >= 1949 && month == 8 && day == 1) {//中国
            message = "建军节";
        } else if (year >= 1985 && month == 9 && day == 10) {//中国
            message = "教师节";
        } else if (year >= 1989 && month == 9 && day == 20) {
            message = "爱牙日";
        } else if (year >= 1949 && month == 10 && day == 1) {//中国
            message = "国庆节";
        } else if (year >= 1997 && month == 10 && day == 4) {
            message = "世界动物日";
        } else if (year >= 1924 && month == 11 && day == 31) {//西方
            message = "万圣节";//世界勤俭日
        } else if (year >= 1970 && month == 11 && day == 11) {//中国
            message = "光棍节";
        }/* else if (month == 10 && day == 23) {//11月第四个星期四
            message = "感恩节";
        }*/ else if (year >= 1935 && month == 12 && day == 9) {//中国
            message = "一二·九运动纪念日";
        } else if (year >= 1948 && month == 12 && day == 10) {
            message = "世界人权日";
        } else if (year >= 2014 && month == 12 && day == 13) {//中国
            message = "南京大屠杀纪念日";
        } else if (month == 12 && day == 24) {//西方
            message = "平安夜";
        } else if (month == 12 && day == 25) {//西方
            message = "圣诞节";
        }
        return message;
    }


    public static String getConflictEvilSpirit(int lunarDay) {
        int heavenlyIndex = lunarDay % 10;
        int earthlyIndex = lunarDay % 12;
        int conflictHeavenlyIndex;
        int conflictEarthlyIndex;

        if (heavenlyIndex < 6) {
            conflictHeavenlyIndex = heavenlyIndex + 4;
        } else {
            conflictHeavenlyIndex = heavenlyIndex - 6;
        }

        if (earthlyIndex < 6) {
            conflictEarthlyIndex = earthlyIndex + 6;
        } else {
            conflictEarthlyIndex = earthlyIndex - 6;
        }

        StringBuilder builder = new StringBuilder();
        builder.append("冲");
        builder.append(Animals[conflictEarthlyIndex]);
        builder.append("(" + Gan[conflictHeavenlyIndex]);
        builder.append(Zhi[conflictEarthlyIndex] + ")");
        builder.append("煞");
        builder.append(Orientation[earthlyIndex % 4]);
        return builder.toString();
    }
}
