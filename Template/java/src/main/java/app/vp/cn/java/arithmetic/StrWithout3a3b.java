package app.vp.cn.java.arithmetic;

/**
 * author : by
 * date: 2019/2/22 0022  下午 6:01.
 * describe  给定两个整数 A 和 B，返回任意字符串 S，要求满足：
 * <p>
 * S 的长度为 A + B，且正好包含 A 个 'a' 字母与 B 个 'b' 字母；
 * 子串 'aaa' 没有出现在 S 中；
 * 子串 'bbb' 没有出现在 S 中。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：A = 1, B = 2
 * 输出："abb"
 * 解释："abb", "bab" 和 "bba" 都是正确答案。
 * 示例 2：
 * <p>
 * 输入：A = 4, B = 1
 * 输出："aabaa"
 */

public class StrWithout3a3b {

    private String s;

    public String strWithout3a3b(int A, int B) {

        if (A >= B) {
            StringBuffer strOut = new StringBuffer();
            int aCount = 0;
            int bSpace = 0;
            for (int i = 0; i < A; i++) {
                strOut.append("a");
                aCount++;
                if (aCount >= 2) {
                    strOut.append("cd");
                    i--;
                    aCount = 0;
                    bSpace++;
                }
            }

            s = strOut.toString();
            s.replace("c", "b");
            int afterB = B - bSpace;

            for (int i = 0; i < afterB; i++) {
                s.replaceFirst("d", "b");
            }

            s.replace("d", "");

        } else {
            StringBuffer strOut = new StringBuffer();
            int aCount = 0;
            int bSpace = 0;

            for (int i = 0; i < B; i++) {
                strOut.append("b");
                aCount++;
                if (aCount >= 2) {
                    strOut.append("cd");
                    i--;
                    aCount = 0;
                    bSpace++;
                }
            }

            s = strOut.toString();
            s.replace("c", "a");
            int afterB = A - bSpace;

            for (int i = 0; i < afterB; i++) {
                s.replaceFirst("d", "a");
            }
            s.replace("d", "");
        }


        return s;
    }
}
