package linkedlist;

/**
 * 双向链表实现
 *
 * @author Neil
 * @version v1.0
 * @date 2021/2/2 20:01
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        // 测试
        System.out.println("双向链表的测试：");

        // 创建结点
        DoubleHeroNode hero1 = new DoubleHeroNode(1, "宋江", "及时雨");
        DoubleHeroNode hero2 = new DoubleHeroNode(2, "卢俊义", "玉麒麟");
        DoubleHeroNode hero3 = new DoubleHeroNode(3, "吴用", "智多星");
        DoubleHeroNode hero4 = new DoubleHeroNode(4, "林冲", "豹子头");

        // 创建双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        doubleLinkedList.list();

        // 修改
        DoubleHeroNode newDoubleHeroNode = new DoubleHeroNode(4, "小林冲", "小豹子头");
        doubleLinkedList.update(newDoubleHeroNode);
        System.out.println("修改后的：");
        doubleLinkedList.list();

        // 删除
        doubleLinkedList.del(3);
        System.out.println("删除后的：");
        doubleLinkedList.list();
    }
}

/**
 * 双向链表类
 */
class DoubleLinkedList {
    private final DoubleHeroNode head = new DoubleHeroNode(0, "", "");

    /**
     * 返回头结点* @return 头结点*/
    public DoubleHeroNode getHead() {
        return head;
    }

    /**
     * 遍历显示链表
     */
    public void list() {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        DoubleHeroNode temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    /**
     * 添加结点到双向链表最后
     * @param doubleHeroNode 待添加结点*/
    public void add(DoubleHeroNode doubleHeroNode) {
        DoubleHeroNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = doubleHeroNode;
        doubleHeroNode.pre = temp;
    }

    /**
     * 修改结点内容
     * @param doubleHeroNode 新结点*/
    public void update(DoubleHeroNode doubleHeroNode) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }

        DoubleHeroNode temp = head.next;
        boolean flag = false;

        while (temp != null) {
            if (temp.no == doubleHeroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            temp.name = doubleHeroNode.name;
            temp.nickName = doubleHeroNode.nickName;
        } else {
            System.out.printf("没有找到编号%d的结点，不能修改", doubleHeroNode.no);
        }
    }

    /**
     * 删除指定编号结点* @param no 待删除结点编号
     */
    public void del(int no) {
        if (head.next == null) {
            System.out.println("空链表，无法删除");
        }
        DoubleHeroNode temp = head.next;
        boolean flag = false;
        while (temp != null) {
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            temp.pre.next = temp.next;
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("要删除的%d号结点不存在", no);
        }
    }
}

/**
 * 双向链表结点*/
class DoubleHeroNode {
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
     * 指向下一个结点*/
    public DoubleHeroNode next;
    /**
     * 指向上一个结点*/
    public DoubleHeroNode pre;

    /**
     * 构造器
     * @param no 序号
     * @param name 姓名
     * @param nickName 绰号
     */
    public DoubleHeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    /**
     * @return 字符串
     */
    @Override
    public String toString() {
        return "DoubleHeroNode{" + "no=" + no + ", name='" + name + '\'' + ", nickName='" + nickName + '\'' + '}';
    }
}

