package app.vp.cn.java.reference;

import java.lang.ref.WeakReference;

import app.vp.cn.java.bean.User;

public class WeakReferenceTest {
 
	public static void main(String[] args) {
 
		/*创建User对象*/
		User user = new User();
		/*设置username*/
		user.setUname("廖泽民");
		
		/*把对象放在弱引用中*/
		WeakReference<User> weak = new WeakReference<User>(user);
		
		/*把user对象置空，然后再从若引用中取值*/
		user = null;
 
		int i = 0;
//		User user1 = weak.get();
		/*weak.get()表示从引用中取得对象*/
		while (weak.get() != null) {
 
			System.out.println(String.format("从弱引用中取值: %s, count: %d", weak.get().getUname(), ++i));
//			User user1 = weak.get();
			getUser(weak);
			if (i % 10 == 0) {
				System.gc();
				System.out.println("内存回收方法被调用");
			}
 
			try {
				Thread.sleep(500);
			} catch (Exception e) {
 
			}
		}

//		System.out.println(String.format("原来get 的值是%s",user1.getUname()));

	/*	System.gc();
		try {
			Thread.sleep(500);
		} catch (Exception e) {

		}

		User user2 = weak.get();
		int po = 0;
		while (user2!=null){
			System.out.println(String.format("原来get 的值是%s",user1.getUname()));
			System.out.println(String.format("后来get 的值是%s",user2.getUname()));
			po++;
			try {
				Thread.sleep(500);
			} catch (Exception e) {

			}
		}*/
		System.out.println("对象已经被JVM回收");
 
	}

	public static void getUser(WeakReference<User> weakReference){
		User user = weakReference.get();
		String uname = user.getUname();
	}
 
}