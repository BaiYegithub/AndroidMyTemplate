package app.vp.cn.java.javabasics;

import android.util.Log;

import java.util.HashSet;

/**
 * author : by
 * date: 2019/4/16 0016  下午 4:24.
 * describe
 */

public class Test {
    public static void methodBasic() {
        int lNum = 3;
        methodRunInner(lNum);
        Log.d("CopySample", "After methodRunInner, lNum=" + lNum);
    }

    private static void methodRunInner(int lNum) {
        lNum++;
        Log.d("CopySample", "methodRunInner, lNum=" + lNum);
    }

    public static void main(String[] args) {
        //  methodBasic();

        Student LiBai = new Student("1", "李白");
        Student libai = new Student("1", "李白");

        if(LiBai.equals(libai)){
            System.out.printf("main: 内容相同>>>");
        }else {
            System.out.printf("main: 内容不相同>>>");
        }

        //hashSet 存储比较唯一性的顺序是  先比较hashCode 是否相同    再比较equals  两者都相同，才会是同一个对象



        HashSet<Student> studentHashSet = new HashSet<>();
        studentHashSet.add(libai);
        studentHashSet.add(LiBai);

        System.out.printf("studentHashSet 的数量"+studentHashSet.size());



    }
}
