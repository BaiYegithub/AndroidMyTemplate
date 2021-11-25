package app.vp.cn.java.arithmetic.search;

import app.vp.cn.java.arithmetic.TreeNode;

/**
 * Created by baiye
 * Date: 2021/6/15
 * Time: 13:42
 * Description:二叉查找树的查找操作
 */
public class BinarySearchTree {
    private TreeNode tree;

    public TreeNode find(int data) {
        TreeNode p = tree;
        while (p != null) {
            if (data > p.val) {
                p = p.right;
            } else if (data < p.val) {
                p = p.left;
            } else {
                return p;
            }
        }
        return null;
    }

    public void insertData(int data) {
        if (tree == null) {
            tree = new TreeNode(data);
            return;
        }

        TreeNode p = tree;
        while (p != null) {
            if (data > p.val) {
                if (p.right == null) {
                    p.right = new TreeNode(data);
                    return;
                } else {
                    p = p.right;
                }
            } else if (data < p.val) {
                if (p.left == null) {
                    p.left = new TreeNode(data);
                    return;
                } else {
                    p = p.left;
                }
            }
        }
    }
}
