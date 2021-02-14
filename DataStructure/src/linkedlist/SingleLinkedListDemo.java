package linkedlist;

import java.util.Stack;

/**
 * 单链表，以水浒108好汉为例
 *
 * @author Neil
 * @version v1.0
 * @date 2021/2/1 22:17
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        // 先创建结点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        // 创建一个单链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();

        // 测试添加结点// singleLinkedList.add(hero4);
        // singleLinkedList.add(hero2);
        // singleLinkedList.add(hero3);
        // singleLinkedList.add(hero1);

        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.list();

        // // 测试反转链表
        // System.out.println("反转链表：");
        // reverseList(singleLinkedList.getHead());
        // singleLinkedList.list();

        // 测试
        System.out.println("反向打印：");
        reversePrint(singleLinkedList.getHead());

        // // 测试修改结点// HeroNode newHeroNode = new HeroNode(2, "小卢俊义", "小玉麒麟");
        // singleLinkedList.update(newHeroNode);
        // System.out.println("修改后的：");
        // singleLinkedList.list();
        //
        // // 测试删除结点// singleLinkedList.del(3);
        // System.out.println("删除后的：");
        // singleLinkedList.list();
        //
        // // 测试结点统计
        // System.out.println("有效的结点个数=" + getLength(singleLinkedList.getHead()));
        //
        // // 测试获取倒数第k个结点// HeroNode res = findLastIndexNode(singleLinkedList.getHead(), 4);
        // System.out.println("res = " + res);
    }

    /**
     * 获取单链表除头结点外的结点个数
     *
     * @param head 链表头结点
     * @return 链表结点个数
     */
    public static int getLength(HeroNode head) {
        // 判断空结点
        if (head == null) {
            return 0;
        }

        int length = 0;
        HeroNode temp = head.next;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

    /**
     * 查找倒数第index个结点
     *
     * @param head 链表头结点
     * @param index 倒数索引
     * @return 查找的结点
     */
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        if (head.next == null) {
            return null;
        }

        int size = getLength(head);
        if (index <= 0 || index > size) {
            return null;
        }
        HeroNode temp = head.next;
        for (int i = 0; i < size - index; i++) {
            temp = temp.next;
        }

        return temp;
    }

    /**
     * 反转单链表
     *
     * @param head 链表头结点
     */
    public static void reverseList(HeroNode head) {
        if (head.next == null || head.next.next == null) {
            return;
        }
        HeroNode current = head.next;
        HeroNode next;
        HeroNode reverseHead = new HeroNode(0, "", "");

        // 遍历原来的链表，每遍历一个结点，就去除并放在心得链表reversedHead的最前端
        while (current != null) {
            next = current.next;
            current.next = reverseHead.next;
            reverseHead.next = current;
            current = next;
        }
        head.next = reverseHead.next;
    }

    /**
     * 反序打印单链表
     * 可以利用栈，将结点压入栈中，利用栈先进后出的特点
     *
     * @param head 链表头结点
     */
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            System.out.println("空链表");
            return;
        }

        // 使用栈来反向打印
        Stack<HeroNode> stack = new Stack<>();
        HeroNode temp = head.next;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }

        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }

    }
}

/**
 * 单链表类，用于管理梁山好汉
 */
class SingleLinkedList {
    /**
     * 初始化头结点，头结点不动，不存放具体的数据
     */
    private final HeroNode head = new HeroNode(0, "", "");

    /**
     * 不按顺序添加结点到单链表
     * 找到当前链表的最后结点，将最后结点的next指向新的结点* @param heroNode 待添加结点
     */
    public void add(HeroNode heroNode) {
        // head 不能动，因为我们需要一个辅助遍历 temp
        HeroNode temp = head;
        // 遍历链表，找到最后
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    /**
     * 按顺序添加结点到单链表
     *
     * @param heroNode 待添加结点
     */
    public void addByOrder(HeroNode heroNode) {
        // head 不能动，因此我们需要一个辅助遍历 temp
        HeroNode temp = head;
        // 标识添加的编号是否存在
        boolean flag = false;

        while (temp.next != null && temp.next.no <= heroNode.no) {
            if (temp.next.no == heroNode.no) {
                // 说明编号存在
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.printf("待添加结点的编号%d已存在\n", heroNode.no);
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    /**
     * 修改结点信息
     *
     * @param newHeroNode 新的结点信息
     */
    public void update(HeroNode newHeroNode) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 根据no找到需要修改的结点// 定义一个辅助变量
        HeroNode temp = head.next;
        // 表示是否找到结点
        boolean flag = false;
        while (temp != null) {
            if (temp.no == newHeroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        } else {
            System.out.printf("没有找到编号%d的结点，不能修改\n", newHeroNode.no);
        }
    }

    /**
     * 删除结点* @param no 待删除结点的编号
     */
    public void del(int no) {
        HeroNode temp = head;
        // 标志是否找到待删除的结点的前一个结点
        boolean flag = false;
        while (temp.next != null) {
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("要删除的%d号结点不存在", no);
        }
    }

    /**
     * 显示链表，遍历
     */
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    public HeroNode getHead() {
        return head;
    }
}

/**
 * 定义英雄结点HeroNode，每个HeroNode对象就是一个结点
 */
class HeroNode {
    /**
     * 序号
     */
    public int no;
    /**
     * 姓名
     */
    public String name;
    /**
     * 绰号
     */
    public String nickName;
    /**
     * 指向下一个结点
     */
    public HeroNode next;

    /**
     * 构造函数
     *
     * @param no       序号
     * @param name     姓名
     * @param nickName 绰号
     */
    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    /**
     * 重写 toString()
     *
     * @return String
     */
    @Override public String toString() {
        return "HeroNode{" + "no=" + no + ", name='" + name + '\'' + ", nickName='" + nickName + '\'' + '}';
    }
}
