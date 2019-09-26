package app.vp.cn.java.arithmetic;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by baiye
 * Date: 2019/9/18
 * Time: 17:15
 * Description:  Byte Dance 面试题 遍历一个view
 * https://mp.weixin.qq.com/s/WAxWJMuVgXAL9tHAiWyxqg
 * 面试题：
 * <p>
 * 给定一个 RootView，打印其内 View Tree 的每个 View。
 * 在 Android 下，UI 的布局结构，对标到数据结构中，本质就是一个由 View 和 ViewGroup 组成的多叉树结构。其中 View 只能作为叶子节点，而 ViewGroup 是可以存在子节点的。
 * <p>
 * getChildCount()：获取其子 View 的个数。
 * <p>
 * getChildAt(int)：获取对应索引的子 View。
 * <p>
 * 对于 View，无需过多处理，直接打印输出即可。而 ViewGroup，除了打印自身的这个节点之外，还需要打印其子节点。
 */
public class TraverseView {

    /*
        1.第一种解题思路，递归 当一个大问题，可以被拆分成多个小问题，并且分解后的小问题，和大问题相比，只是数据规模不同，求解思路完全一致的问题，非常适合递归来实现。
        缺点: 可能会爆栈，会报 StackOverflowError
    */
    @SuppressLint("NewApi")
    public static void recursionPrint(View root) {
        System.out.print(root);
        Log.i("myView", "recursionPrint: 我的view 是"+root +"   " +root.getTransitionName());
        if (root instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) root).getChildCount(); i++) {
                View child = ((ViewGroup) root).getChildAt(i);
                recursionPrint(child);
            }
        }
    }

    /*广度优先遍历 广度优先遍历是一层一层地处理数据，非常适合用先入先出的队列来实现*/
    @SuppressLint("NewApi")
    public static void breadthFirst(View root) {
        LinkedList<View> viewDeque = new LinkedList<>();
        View view = root;
        viewDeque.push(view);
        while (!viewDeque.isEmpty()) {
            //.poll 的含义是找到并删除表头，
            view = viewDeque.poll();
            Log.i("myView", "recursionPrint: 我的view 是"+view +"   " +view.getTransitionName());
            if (view instanceof ViewGroup) {
                for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                    View child = ((ViewGroup) view).getChildAt(i);
                    viewDeque.addLast(child);
                }
            }
        }
    }

    /**
     * 深度优先遍历
     *
     * @param root
     */
    @SuppressLint("NewApi")
    public static void depthFirst(View root) {
        LinkedList<View> viewDeque = new LinkedList();
        View view = root;
        viewDeque.push(view);
        while (!viewDeque.isEmpty()) {
            view = viewDeque.pop();
            System.out.print(view);
            Log.i("myView", "recursionPrint: 我的view 是"+view +"   " +view.getTransitionName());
            if (view instanceof ViewGroup) {
                for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                    View child = ((ViewGroup) view).getChildAt(i);
                    viewDeque.push(child);
                }
            }
        }
    }


}
