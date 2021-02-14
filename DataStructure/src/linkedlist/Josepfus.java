package linkedlist;

/**
 * 约瑟夫问题 单向环形列表解决
 * Josephu 问题为:设编号为 1，2，… n 的 n 个人围坐一圈，约定编号为 k（1<=k<=n）的人从 1 开始报数，数到 m 的那个人出列，
 * 它的下一位又从 1 开始报数，数到 m 的那个人又出列，依次类推，直到所有人出列为止，由此产生一个出队编号的序列。
 * @author Neil
 * @version v1.0
 * @date 2021/2/2 21:24
 */
public class Josepfus {
    public static void main(String[] args) {
        // 测试构建环形列表和遍历
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(10);
        circleSingleLinkedList.showBoy();

        // 测试约瑟夫出圈
        // 2->4->1->5->3
        circleSingleLinkedList.countBoy(1, 4, 10);
    }
}

/**
 * 单项环形列表实现类
 */
class CircleSingleLinkedList {
    /**
     * 创建一个first结点，当前无编号
     */
    private Boy first = null;

    /**
     * 添加指定数量孩子结点，构成环形链表
     * @param sum 待添加结点数量
     */
    public void addBoy(int sum) {
        // nums 做一个数据校验
        if (sum < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        // 辅助指针，帮助构建环形列表
        Boy currentBoy = null;
        // 创建环形列表
        for (int i = 1; i <= sum; i++) {
            // 根据编号，创建小孩结点
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                first.setNext(first);
                currentBoy = first;
            } else {
                currentBoy.setNext(boy);
                boy.setNext(first);
                currentBoy = boy;
            }
        }

    }

    /**
     * 遍历当前环形链表
     */
    public void showBoy() {
        if (first == null) {
            System.out.println("没有任何小孩");
            return;
        }

        Boy currentBoy = first;
        do {
            System.out.printf("小孩的编号为%d\n", currentBoy.getNo());
            currentBoy = currentBoy.getNext();
        } while (currentBoy != first);
    }

    /**
     * 根据用户输入计算小孩出圈顺序
     * @param startNo 从第几个小孩开始数数
     * @param countNumber 数几下
     * @param sum 最初有多少个小孩在圈中
     */
    public void countBoy(int startNo, int countNumber, int sum) {
        // 校验数据
        if (first == null || startNo < 1 || startNo > sum) {
            System.out.println("参数有误，请重新输入");
            return;
        }

        // 辅助指针，帮助孩子出圈
        Boy helper = first;
        while (helper.getNext() != first) {
            helper = helper.getNext();
        }

        // 小孩报数前，先让first和helper移动k-1次
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }

        // 小孩报数时，先让first和helper同时移动m-1次，然后出圈，直到圈中只有一个结点
        while (helper != first) {
            for (int i = 0; i < countNumber - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }

            // 此时first指向要出圈的结点
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号为%d\n", first.getNo());
    }
}

/**
 * 创建一个 Boy 类，表示一个结点
 */
class Boy {
    /**
     * 编号
     */
    private final int no;
    /**
     * 指向下一个结点，默认 null
     */
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}