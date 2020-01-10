package app.vp.cn.java.bean;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by baiye
 * Date: 2019/9/17
 * Time: 16:28
 * Description:https://www.jianshu.com/p/dd34211f2565
 * 类型擦除
 其实在开篇的时候已经通过例子说明了，通过反射绕开泛型的定义，也说明了类中定义的泛型最终是以Object被jvm执行。所有的泛型在jvm中执行的时候，都是以Object对象存在的，加泛型只是为了一种代码的规范，避免了开发过程中再次强转。
 泛型信息只存在于代码编译阶段，在进入 JVM 之前，与泛型相关的信息会被擦除掉，专业术语叫做类型擦除。
 */
public class genericityTest {

    public static void main(String[] args){
        BaseBean<String> stringBaseBean = new BaseBean<>();
        stringBaseBean.setValue("中国");
        try {
            //获取属性上的泛型信息
            Field value = stringBaseBean.getClass().getDeclaredField("value");
            Class<?> type = value.getType();
            String name = type.getName();
            System.out.println("type" + name);

            //获取方法上的泛型信息
            Method getValue = stringBaseBean.getClass().getDeclaredMethod("getValue");
            Object invoke = getValue.invoke(stringBaseBean);
            String methodName = invoke.getClass().getName();
            System.out.println("methodName"+methodName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //多态的体现,用父类去定义一个子类，然后.getClass().getName()得到的是子类的名称
        Animal dog = new Dog();
        String name = dog.getClass().getName();
        System.out.println(name);
        //当把一个对象放进集合或者map 中，实际上放进去的是对象的地址，改变这个地址对应的值，集合里的对象也会跟着改变
        List<Dog> dogList = new ArrayList<>();
        Dog dog1 = new Dog();
        dog1.name = "拉布拉多";
        dogList.add(dog1);

        Map<String,Object> map = new HashMap<>();
        map.put("dog",dogList);

        List<Dog> dogList2 = (List<Dog>) map.get("dog");
        Dog dog2 = new Dog();
        dog2.name = "金毛犬";
        dogList2.add(dog2);

        List<Dog> dogList3 = (List<Dog>) map.get("dog");
        System.out.println("dogList3的size 是"+dogList3.size()+"第一个是"+dogList3.get(0).name+
        "第二个是"+dogList3.get(1).name);

    }
}
