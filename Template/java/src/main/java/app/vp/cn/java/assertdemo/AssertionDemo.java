package app.vp.cn.java.assertdemo;

/**
 * author : by
 * date: 2019/2/20 0020  下午 1:41.
 * describe  断言  也就是所谓的assertion，是jdk1.4后加入的新功能。
 * 它主要使用在代码开发和测试时期，用于对某些关键数据的判断，如果这个关键数据不是你程序所预期的数据，程序就提出警告或退出。
 * 当软件正式发布后，可以取消断言部分的代码。java中使用assert作为断言的一个关键字，这就可以看出java对断言还是很重视的，因为如果不是很重要的话，直接开发个类就可以了，没必要新定义一个关键字。
 */

public class AssertionDemo {

    static int i = 5;

    public static void main(String[] args) {
        assert i==6;
        System.out.println("如果断言正常，我就被打印");
    }

}
