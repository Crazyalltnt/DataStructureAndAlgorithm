package stack;

import java.util.Scanner;

/**
 * 数组模拟栈
 *
 * @author Neil
 * @version v1.0
 * @date 2021/2/3 13:56
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        // 测试ArrayStack
        // 创建ArrayStack对象
        ArrayStack stack = new ArrayStack(4);
        String key;
        // 控制是否退出菜单
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);

        while (loop) {
            System.out.println("show：显示栈");
            System.out.println("exit：退出程序");
            System.out.println("push：入栈");
            System.out.println("pop：出栈");
            System.out.println("请输入你的选择：");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数字");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("出栈的数据时%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

/**
 * 数组模拟栈实现类
 */
class ArrayStack {
    /**
     * 栈的大小
     */
    private final int maxSize;
    /**
     * 数组模拟栈，数据存放在该数组中
     */
    private final int[] stack;
    /**
     * top表示栈顶，初始化为-1
     */
    private int top = -1;

    /**
     * 构造器
     * @param maxSize 栈的大小
     */
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    /**
     * 判断栈是否满
     * @return 栈是否满
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 判断栈是否空
     * @return 栈是否空
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 入栈-push
     * @param value 待入栈数据
     */
    public void push(int value) {
        // 判断栈是否满
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    /**
     * 出栈-pop
     * @return 出栈的数据
     */
    public int pop() {
        // 判断栈是否空
        if (isEmpty()) {
            throw new RuntimeException("栈空，无数据");
        }
        int value = stack[top];
        top--;
        return value;
    }

    /**
     * 遍历显示栈
     */
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空");
            return;
        }
        // 从栈顶开始显示数据
        for (int i = top; i >=0 ; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }
}
