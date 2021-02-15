package tree;

import java.util.Arrays;
import java.util.Date;

/**
 * 堆排序
 *
 * @author Neil
 * @version v1.0
 * @date 2021/2/15 14:45
 */
public class HeapSort {
    public static final int BRANCH_OF_THE_TREE = 2;

    public static void main(String[] args) {
        //要求将数组进行升序排列
        // int[] array = {4, 6, 8, 5, 9};

        // 创建8000000个随机数组
        int numberOfArray = 8000000;
        int[] array = new int[numberOfArray];
        for (int i = 0; i < numberOfArray; i++) {
            array[i] = (int)(Math.random() * numberOfArray);
        }

        System.out.println("排序前");
        System.out.println("array = " + Arrays.toString(array));

        // 获取开始时间
        long startTime = System.currentTimeMillis();
        heapSort(array);
        //获取结束时间
        long endTime = System.currentTimeMillis();

        System.out.println("排序后");
        Date endDate = new Date();
        System.out.println("array = " + Arrays.toString(array));

        //输出程序运行时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
    }

    private static void heapSort(int[] array) {
        int temp;
        System.out.println("堆排序");

        // 将无序序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆
        for (int i = array.length / BRANCH_OF_THE_TREE - 1; i >= 0 ; i--) {
            adjustHeap(array, i, array.length);
        }

        for (int i = array.length - 1; i > 0; i--) {
            temp = array[i];
            array[i] = array[0];
            array[0] = temp;
            adjustHeap(array, 0, i);
        }
    }

    /**
     * 将以非叶子节点index为父节点的子树调整成一个大顶堆
     * 举例
     *     4
     *    / \
     *   6   8
     *  / \
     * 5   9
     * int array[]={4,6,8,5,9}; => index=1 => adjustHeap => {4,9,8,5,6}
     *     4
     *    / \
     *   9   8
     *  / \
     * 5   6
     * 再次 index=0 => adjustHeap => {9,6,8,5,4}
     *
     * @param array 待调整数组
     * @param index 非叶子结点的树的父节点索引
     * @param length 待调整元素个数
     */
    private static void adjustHeap(int[] array, int index, int length) {
        // 先取出当前元素的值，保存在临时变量
        int temp = array[index];
        // 开始调整
        // 1.i是index结点的左子结点
        for (int i = index * BRANCH_OF_THE_TREE + 1; i < length; i = i * BRANCH_OF_THE_TREE + 1) {
            // 说明左子结点的值小于右子结点的值
            if (i + 1 < length && array[i] < array[i + 1]) {
                // i指向右子结点
                i++;
            }
            // 如果子结点大于父结点
            if (array[i] > temp) {
                // 把较大的值赋给当前结点
                array[index] = array[i];
                // index指向i，继续循环比较
                index = i;
            } else {
                break;
            }
        }
        // for循环结束后，已经将i为父节点的树的最大值放在了顶部，将temp放在调整后的位置
        array[index] = temp;
    }
}
