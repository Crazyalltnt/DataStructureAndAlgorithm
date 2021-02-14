package sort;

import java.util.Arrays;

/**
 * 插入排序
 *
 * @author Neil
 * @version v1.0
 * @date 2021/2/7 11:16
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] array = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};

        System.out.println("排序前：");
        System.out.println(Arrays.toString(array));

        insertSort(array);

        System.out.println("排序后：");
        System.out.println(Arrays.toString(array));
    }

    /**
     * 插入排序实现函数
     *
     * @param array 待排序数组
     */
    public static void insertSort(int[] array) {
        int insertValue;
        int insertIndex;

        // 使用for循环来把代码简化
        for (int i = 1; i < array.length; i++) {
            insertValue = array[i];
            insertIndex = i - 1;

            /*
             * 给 insertValue 找到插入的位置
             * 说明
             * 1.insertIndex >= 0 保证在给 insertValue 找插入位置时不越界
             * 2.insertValue < array[insertIndex] 待插入的数，还没找到插入的位置
             * 3.此时需要将 array[insertIndex] 后移
             * */
            while (insertIndex >= 0 && insertValue < array[insertIndex]) {
                array[insertIndex + 1] = array[insertIndex];
                insertIndex--;
            }

            // 退出 while 循环表示找到插入位置
            if (insertIndex + 1 != i) {
                array[insertIndex + 1] = insertValue;
            }
        }
    }
}
