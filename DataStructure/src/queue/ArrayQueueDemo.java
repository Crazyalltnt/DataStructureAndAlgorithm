package queue;

import java.util.Scanner;

/**
 * 数组实现队列
 *
 * @author Neil
 * @version v1.0
 * @date 2021/1/31 22:28
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        // 测试队列
        ArrayQueue arrayQueue = new ArrayQueue(3);
        // key接受用户输入
        char key;
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        // 输出一个菜单
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列头取出数据");
            System.out.println("h(head): 显示队列头数据");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                case 'a':
                    System.out.print("请输入一个数字：");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.println("取出的数据是：" + res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.println("队列头数据是" + res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

/**
 * 使用数组模拟队列-编写一个ArrayQueue类
 */
class ArrayQueue {
    /**
     * 表示数组的最大容量
     */
    private final int maxSize;
    /**
     * 队列头，指向队列第一个元素前一个位置
     */
    private int front;
    /**
     * 队列尾，指向队最后一个元素
     */
    private int rear;
    /**
     * 该数组用于存放数据，模拟队列
     */
    private final int[] arr;

    /**
     * 创建队列的构造器
     * @param maxSize 最大容量
     */
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1;
        rear = -1;
    }

    /**
     * 判断队列是否满
     * @return boolean 是否满
     */
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    /**
     * 判断队列是否为空
     * @return boolean
     */
    public boolean isEmpty() {

        return rear == front;
    }

    /**
     * 添加数据到队列
     * @param n 新添加的数据
     */
    public void addQueue(int n) {
        // 判断队列是否满
        if (isFull()) {
            System.out.println("队列满，不能加入数据");
            return;
        }
        rear++;
        arr[rear] = n;
    }

    /**
     * 从队列获取数据
     * @return int 从队列获取的数据
     */
    public int getQueue() {
        // 判断队列是否空
        if (isEmpty()) {
            // 通过抛出异常
            throw new RuntimeException("队列空，不能取数据");
        }
        front++;
        return arr[front];
    }

    /**
     * 显示队列的所有数据
     */
    public void showQueue() {
        // 遍历
        if (isEmpty()) {
            System.out.println("队列空，无数据");
            return;
        }

        for (int i = front + 1; i <= rear; i++) {
            System.out.printf("arr[%d] = %d\n", i, arr[i]);

        }
    }

    /**
     * 显示队列头数据，注意不是取出
     * @return int 队列第一个数据
     */
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("空队列，无数据");
        }
        return arr[front + 1];
    }
}