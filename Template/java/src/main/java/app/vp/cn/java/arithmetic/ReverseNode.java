package app.vp.cn.java.arithmetic;

/**
 * author : by
 * date: 2019/2/27 0027  下午 6:20.
 * describe  反转单链表
 */

public class ReverseNode {

    public Node reverseByLoop(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node preNode = null;//定义前驱节点
        Node nextNode = null;//定义后继节点

        while (head != null) {
            nextNode = head.next;

            head.next = preNode;
            preNode = head;
            head = nextNode;
        }
        return preNode;
    }
}
