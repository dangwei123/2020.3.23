设计实现双端队列。
你的实现需要支持以下操作：

MyCircularDeque(k)：构造函数,双端队列的大小为k。
insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true。
insertLast()：将一个元素添加到双端队列尾部。如果操作成功返回 true。
deleteFront()：从双端队列头部删除一个元素。 如果操作成功返回 true。
deleteLast()：从双端队列尾部删除一个元素。如果操作成功返回 true。
getFront()：从双端队列头部获得一个元素。如果双端队列为空，返回 -1。
getRear()：获得双端队列的最后一个元素。 如果双端队列为空，返回 -1。
isEmpty()：检查双端队列是否为空。
isFull()：检查双端队列是否满了

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/design-circular-deque
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class MyCircularDeque {
    private int[] arr;
    private int front;
    private int rear;
    private int len;
    private int size;
    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
        arr=new int[k];
        len=k;
    }
    
    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if(size==len) return false;
        if(size==0){
            arr[0]=value;
            front=0;
            rear=0;
        }else{
            front=(front-1+len)%len;
            arr[front]=value;
        }
        size++;
        return true;
    }
    
    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if(size==len) return false;
        if(size==0){
            arr[0]=value;
            front=0;
            rear=0;
        }else{
            rear=(rear+1)%len;
            arr[rear]=value;
        }
        size++;
        return true;
    }
    
    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if(size==0) return false;
        front=(front+1)%len;
        size--;
        if(size==0){
            rear=0;
            front=0;
        }
        return true;
    }
    
    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if(size==0) return false;
        rear=(rear-1+len)%len;
        size--;
        if(size==0){
            rear=0;
            front=0;
        }
        return true;
    }
    
    /** Get the front item from the deque. */
    public int getFront() {
        return size==0?-1:arr[front];
    }
    
    /** Get the last item from the deque. */
    public int getRear() {
        return size==0?-1:arr[rear];
    }
    
    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return 0==size;
    }
    
    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return size==len;
    }
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */
 
 有家动物收容所只收留猫和狗，但有特殊的收养规则，收养人有两种收养方式，第一种为直接收养所有动物中最早进入收容所的，第二种为选择收养的动物类型（猫或狗），并收养该种动物中最早进入收容所的。

       给定一个操作序列int[][2] ope(C++中为vector<vector<int>>)代表所有事件。若第一个元素为1，则代表有动物进入收容所，第二个元素为动物的编号，正数代表狗，负数代表猫；若第一个元素为2，则代表有人收养动物，第二个元素若为0，则采取第一种收养方式，若为1，则指定收养狗，若为-1则指定收养猫。请按顺序返回收养的序列。若出现不合法的操作，即没有可以符合领养要求的动物，则将这次领养操作忽略。
	   import java.util.*;

public class CatDogAsylum {
    public ArrayList<Integer> asylum(int[][] ope) {
        // write code here
        ArrayList<Integer> res=new ArrayList<>();
        ArrayList<Integer> animal=new ArrayList<>();
        ArrayList<Integer> name=new ArrayList<>();
        for(int i=0;i<ope.length;i++){
            if(ope[i][0]==1){
                if(ope[i][1]>0){
                    animal.add(1);
                }else if(ope[i][1]<0){
                    animal.add(-1);
                }
                name.add(ope[i][1]);
            }else if(ope[i][0]==2){
                if(ope[i][1]==0){
                    animal.remove(0);
                    int r=name.remove(0);
                    res.add(r);
                }else if(ope[i][1]==1){
                    int index=animal.indexOf(1);
                    if(index>-1){
                        animal.remove(index);
                        res.add(name.remove(index));
                    }
                }else if(ope[i][1]==-1){
                    int index=animal.indexOf(-1);
                    if(index>-1){
                        animal.remove(index);
                        res.add(name.remove(index));
                    }
                }
            }
        }
        return res;
    }
}