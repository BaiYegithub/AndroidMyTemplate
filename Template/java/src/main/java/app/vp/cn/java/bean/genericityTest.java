package app.vp.cn.java.bean;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

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
    }
}
