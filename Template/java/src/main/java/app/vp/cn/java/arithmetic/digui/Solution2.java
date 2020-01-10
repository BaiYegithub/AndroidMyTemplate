package app.vp.cn.java.arithmetic.digui;

import app.vp.cn.java.arithmetic.TreeNode;

/**
 * Created by baiye
 * Date: 2019/9/27
 * Time: 15:22
 * Description:
 * 给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。 这条路径可以经过也可以不经过根节点。
 * <p>
 * 注意：两个节点之间的路径长度由它们之间的边数表示。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * <p>
 * 5
 * / \
 * 4   5
 * / \   \
 * 1   1   5
 * 输出:
 * <p>
 * 2
 * 示例 2:
 * <p>
 * 输入:
 * <p>
 * 1
 * / \
 * 4   5
 * / \   \
 * 4   4   5
 * 输出:
 * <p>
 * 2
 * 注意: 给定的二叉树不超过10000个结点。 树的高度不超过1000。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-univalue-path
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution2 {
    int ans;

    public int longestUnivaluePath(TreeNode root) {
        ans = 0;
        countHelper(root);
        return ans;
    }

    private int countHelper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = countHelper(root.left);
        int right = countHelper(root.right);
        int nextLeft = 0;
        int nextRight = 0;
        if (root.left != null && root.left.val == root.val) {
            nextLeft = left + 1;
        }
        if (root.right != null && root.right.val == root.val) {
            nextRight = right + 1;
        }

        ans = Math.max(ans, nextLeft + nextRight);
        return Math.max(nextLeft,nextRight);
    }
}
