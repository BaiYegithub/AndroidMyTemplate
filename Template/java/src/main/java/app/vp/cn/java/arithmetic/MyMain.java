package app.vp.cn.java.arithmetic;

/**
 * author : by
 * date: 2019/2/11 0011  下午 2:59.
 * describe
 */

public class MyMain {
    public static void main(String[] args){
        int[] a = new int[]{6,5,4,4,8};
        Solution solution = new Solution();
      //  solution.isMonotonic(a);

        Solution4 solution4 = new Solution4();
        int i = solution4.largestPerimeter(a);
        System.out.println(i);

        Node headNode = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(2);
        Node node5 = new Node(1);

        headNode.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        node4 = node4.next;
        boolean palindrome = solution4.isPalindrome(headNode);
        System.out.print("是不是回文字符串"+palindrome);


        if((1&1)!=0){
            System.out.print("(1&1)!=0");
        }
    }
}
