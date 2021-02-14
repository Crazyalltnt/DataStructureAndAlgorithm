package sort;

import java.util.Arrays;

/**
 * 选择排序
 *
 * @author Neil
 * @version v1.0
 * @date 2021/2/6 22:37
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] array = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};

        System.out.println("排序前：");
        System.out.println(Arrays.toString(array));

        selectSort(array);

        System.out.println("排序后：");
        System.out.println(Arrays.toString(array));
    }

    /**
     * 选择排序实现函数
     *
     * @param array 要排序的数组
     */
    public static void selectSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            int min = array[i];
            for (int j = i + 1; j < array.length; j++) {
                if (min > array[j]) {
                    min = array[j];
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                array[minIndex] = array[i];
                array[i] = min;
            }
        }
    }
}
