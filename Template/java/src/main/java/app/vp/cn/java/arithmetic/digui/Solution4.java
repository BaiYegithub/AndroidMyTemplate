package app.vp.cn.java.arithmetic.digui;

import app.vp.cn.java.arithmetic.TreeNode;
import io.reactivex.internal.operators.flowable.FlowableInternalHelper;

/**
 * Created by baiye
 * Date: 2019/9/28
 * Time: 14:37
 * Description:给定一个二叉搜索树的根结点 root, 返回树中任意两节点的差的最小值。
 * <p>
 * 示例：
 * <p>
 * 输入: root = [4,2,6,1,3,null,null]
 * 输出: 1
 * 解释:
 * 注意，root是树结点对象(TreeNode object)，而不是数组。
 * <p>
 * 给定的树 [4,2,6,1,3,null,null] 可表示为下图:
 * <p>
 * 4
 * /   \
 * 2      6
 * / \
 * 1   3
 * <p>
 * 最小的差值是 1, 它是节点1和节点2的差值, 也是节点3和节点2的差值,也是节点4和节点3的差值
 * 注意：
 * <p>
 * 二叉树的大小范围在 2 到 100。
 * 二叉树总是有效的，每个节点的值都是整数，且不重复。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题关键:中序遍历二叉搜索树得到的是有序节点
 */
public class Solution4 {

    int min = Integer.MAX_VALUE;
    TreeNode pre;
    public int minDiffInBST(TreeNode root) {

        NodeHelper(root);
        return min;
    }

    private void NodeHelper(TreeNode root) {
        if (root == null) {
            return;
        }
        NodeHelper(root.left);
        if(pre!=null){
            min  = Math.min(min,root.val-pre.val);
        }
        pre = root;
        NodeHelper(root.right);

    }
}
