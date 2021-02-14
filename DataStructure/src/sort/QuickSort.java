package sort;

import java.util.Arrays;

/**
 * 快速排序
 *
 * @author Neil
 * @version v1.0
 * @date 2021/2/7 17:03
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] array = {8, 9, 1, 2, 7, 2, 3, 5, 4, 6, 0, 8};

        System.out.println("排序前：");
        System.out.println(Arrays.toString(array));

        quickSort(array, 0, array.length - 1);

        System.out.println("排序后：");
        System.out.println(Arrays.toString(array));
    }

    /**
     * 快速排序实现函数
     *
     * @param array 待排序数组
     * @param left  左指针
     * @param right 右指针
     */
    private static void quickSort(int[] array, int left, int right) {
        if (left < right) {
            int temp = array[left];
            int localLeft = left;
            int localRight = right;

            // while 循环的目的让比 temp 值小的放到左边，大的放在右边
            while (localLeft < localRight) {
                // 在 temp 的右边一直找，找到小于 temp 的值才退出
                while (localLeft < localRight && array[localRight] >= temp) {
                    localRight--;
                }
                array[localLeft] = array[localRight];

                // 在 temp 的左边一直找，找到大于 temp 的值才退出
                while (localLeft < localRight && array[localLeft] <= temp) {
                    localLeft++;
                }
                array[localRight] = array[localLeft];
            }

            array[localLeft] = temp;
            quickSort(array, left, localLeft - 1);
            quickSort(array, localLeft + 1, right);
        }
    }
}
