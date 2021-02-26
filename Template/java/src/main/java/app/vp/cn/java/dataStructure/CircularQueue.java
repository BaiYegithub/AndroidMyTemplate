package app.vp.cn.java.dataStructure;

/**
 * Created by baiye
 * Date: 2021/2/5
 * Time: 16:24
 * Description: 环状数组队列
 */
class CircularQueue {
    private String[] items;
    private int n = 0;
    private int head = 0;
    private int end = 0;

    public CircularQueue(int n) {
        this.n = n;
        items = new String[n];
    }

    //入队
    public boolean enqueue(String str) {
        if ((end + 1) % n == head) {//队列满了
            return false;
        }
        items[end] = str;
        end = (end + 1) % n;
        return true;
    }
    //出队
    public String dequeue(){
        if(head == end){
            return null;
        }else {
            String item = items[head];
            head = (head + 1) % n;
            return item;
        }
    }
}









