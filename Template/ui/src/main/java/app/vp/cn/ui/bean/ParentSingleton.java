package app.vp.cn.ui.bean;

public class ParentSingleton{

public static int value = 100;

public ParentSingleton(){
    System.out.println("ParentSingleton new instance");
}

static {
    System.out.println("ParentSingleton static block");
}

{
    System.out.println("ParentSingleton  block ！！！ ");
}

}