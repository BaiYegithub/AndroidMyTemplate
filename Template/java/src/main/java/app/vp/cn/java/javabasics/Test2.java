package app.vp.cn.java.javabasics;

/**
 * Created by baiye
 * Date: 2020/4/9
 * Time: 17:56
 * Description:
 */
public class Test2 {

    private static Student student;
    public static void main(String[] args) {
        int a = 123;
        change(a);
        System.out.println(a);

        String str = "啦啦啦";
        change(str);
        System.out.println(str);

        Student stuXiao = new Student("1", "小明");
        change(stuXiao);
        System.out.println(stuXiao.getName());

        Integer integer = new Integer(2);
        changeInteger(integer);
        System.out.println(integer);
    }

    private static void changeInteger(Integer integer) {
        integer = 5;
    }

    private static void change(Student stuXiao) {
        student = stuXiao;
        student.setName("小红");
//        stuXiao.setName("小红");
        System.out.println(student.getName());
    }

    private static void change(String str) {
        str = new String("wwerrwe");
    }

    private static void change(int a) {
        a = 4;
    }
}
