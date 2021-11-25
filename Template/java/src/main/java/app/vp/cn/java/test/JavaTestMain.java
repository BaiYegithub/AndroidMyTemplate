package app.vp.cn.java.test;

import java.util.ArrayList;
import java.util.List;

import app.vp.cn.java.bean.Student;

/**
 * Created by baiye
 * Date: 2021/11/17
 * Time: 14:06
 * Description:
 */
public class JavaTestMain {

    public static void main(String[] args) {

        List<Student> students = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            students.add(new Student("小明" + i, i));
        }

        Student studentXiaoHong = new Student("小红", 7);

        for (int i = 0; i < students.size(); i++) {
            System.out.print("替换之前list的是" + students.get(i).getName());
        }

        Student studentXiaoMing = students.get(0);
        studentXiaoMing = studentXiaoHong;

        for (int i = 0; i < students.size(); i++) {
            System.out.print("替换之后list的是" + students.get(i).getName());
        }
        System.out.print("小明是"+studentXiaoMing.getName());

    }
}
