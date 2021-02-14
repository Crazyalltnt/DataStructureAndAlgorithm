package sort;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author Neil
 * @version v1.0
 * @date 2021/2/6 21:46
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] array = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};

        System.out.println("排序前：");
        System.out.println(Arrays.toString(array));

        bubbleSort(array);

        System.out.println("排序后：");
        System.out.println(Arrays.toString(array));

    }

    /**
     * 选择排序实现函数
     *
     * @param array 要排序的数组
     */
    public static void bubbleSort(int[] array) {
        int temp;
        // 优化，标识变量，表示是否进行过交换
        boolean flag = false;

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    flag = true;
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }

            if (!flag) {
                break;
            } else {
                flag = false;
            }
        }
    }
}
