package sort;

import java.util.Arrays;

/**
 * 希尔排序
 * 也是一种插入排序，是简单插入排序的改进版本，更加高效，也称为缩小增量排序
 *
 * @author Neil
 * @version v1.0
 * @date 2021/2/7 14:51
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] array = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};

        System.out.println("排序前：");
        System.out.println(Arrays.toString(array));

        // shellSortWithExchange(array);
        shellSortWithShift(array);

        System.out.println("排序后：");
        System.out.println(Arrays.toString(array));
    }

    private static void shellSortWithShift(int[] array) {
        int temp;
        int tempIndex;
        int gapFactor = 2;

        // 增量 gap，并逐步缩小增量
        for (int gap = array.length / gapFactor; gap > 0; gap /= gapFactor) {
            // 从第 gap 个元素，逐个对其所在的组进行直接插入排序
            for (int i = gap; i < array.length; i++) {
                tempIndex = i;
                temp = array[tempIndex];
                if (array[tempIndex] < array[tempIndex - gap]) {
                    while (tempIndex - gap >= 0 && temp < array[tempIndex - gap]) {
                        // 移动
                        array[tempIndex] = array[tempIndex - gap];
                        tempIndex -= gap;
                    }

                    // 当退出 while 后，就给 temp 找到插入的位置
                    array[tempIndex] = temp;
                }
            }
        }
    }

    /**
     * 希尔排序实现类
     * 希尔排序时对有序序列在插入时采用交换法
     *
     * @param array 待排序数组
     */
    private static void shellSortWithExchange(int[] array) {
        int temp;
        int gapFactor = 2;

        for (int gap = array.length / gapFactor; gap > 0; gap /= gapFactor) {
            for (int i = gap; i < array.length; i++) {
                // 遍历各组中所有的元素，步长gap
                for (int j = i - gap; j >= 0; j -= gap) {
                    // 如果当前元素大于加上步长后的那个元素，交换
                    if (array[j] > array[j + gap]) {
                        temp = array[j];
                        array[j] = array[j + gap];
                        array[j + gap] = temp;
                    }
                }
            }
        }
    }
}
