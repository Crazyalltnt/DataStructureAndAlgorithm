package sort;

import java.util.Arrays;

/**
 * 归并排序
 *
 * @author Neil
 * @version v1.0
 * @date 2021/2/7 18:56
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] array = {8, 9, 1, 2, 7, 2, 3, 5, 4, 6, 0, 8};
        int[] temp = new int[array.length];

        System.out.println("排序前：");
        System.out.println(Arrays.toString(array));

        mergeSort(array, 0, array.length - 1, temp);

        System.out.println("排序后：");
        System.out.println(Arrays.toString(array));
    }

    /**
     * 归并排序实现函数
     * 分治+合并
     *
     * @param array 待排序数组
     * @param left  左边索引
     * @param right 右边索引
     * @param temp  中间临时数组
     */
    private static void mergeSort(int[] array, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            // 向左递归进行分解
            mergeSort(array, left, mid, temp);
            // 向右递归进行分解
            mergeSort(array, mid + 1, right, temp);
            // 合并
            merge(array, left, mid, right, temp);

        }
    }

    /**
     * 合并函数
     *
     * @param array 待排序数组
     * @param left  左边有序序列初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  中间临时数组
     */
    private static void merge(int[] array, int left, int mid, int right, int[] temp) {
        int leftInitialIndex = left;
        int rightInitialIndex = mid + 1;
        int tempIndex = 0;

        // 先把左右两边的数据按照排序规则填充到 temp，直到有一边处理完成
        while (leftInitialIndex <= mid && rightInitialIndex <= right) {
            if (array[leftInitialIndex] <= array[rightInitialIndex]) {
                temp[tempIndex++] = array[leftInitialIndex++];
            } else {
                temp[tempIndex++] = array[rightInitialIndex++];

            }
        }

        // 把有剩余数据的一边的数据依次全部填充到 temp
        while (leftInitialIndex <= mid) {
            temp[tempIndex++] = array[leftInitialIndex++];
        }

        while (rightInitialIndex <= right) {
            temp[tempIndex++] = array[rightInitialIndex++];
        }

        // 将 temp 数组的元素拷贝到 array
        tempIndex = 0;
        while (left <= right) {
            array[left++] = temp[tempIndex++];
        }
    }
}
