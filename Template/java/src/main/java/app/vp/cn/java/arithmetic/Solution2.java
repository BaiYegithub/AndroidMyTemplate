package app.vp.cn.java.arithmetic;

import java.util.ArrayList;
import java.util.List;

/**
 * author : by
 * date: 2019/2/11 0011  下午 3:39.
 * describe
 * 如果二叉树每个节点都具有相同的值，那么该二叉树就是单值二叉树。
 * <p>
 * 只有给定的树是单值二叉树时，才返回 true；否则返回 false。
 */

public class Solution2 {
    List<Integer> vals;

    public boolean isUnivalTree(TreeNode root) {
        vals = new ArrayList<>();
        dfs(root);

        for(Integer v : vals){
            if(v!=vals.get(0)){
                return false;
            }
        }
        return true;
    }

    private void dfs(TreeNode root) {
        if (root != null) {
            vals.add(root.val);
            dfs(root.left);
            dfs(root.right);
        }
    }
}
