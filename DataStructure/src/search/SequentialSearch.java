package search;

/**
 * 顺序查找 / 线性查找
 *
 * @author Neil
 * @version v1.0
 * @date 2021/2/9 14:16
 */
public class SequentialSearch {
    public static void main(String[] args) {
        int[] array = {1, 9, 11, -1, 34, 89};
        int searchedValue = 11;
        int index = sequentialSearch(array, searchedValue);
        if (index == -1) {
            System.out.println("没有找到");
        } else {
            System.out.println("找到，下标为 " + index);
        }
    }

    /**
     * 顺序查找/线性查找实现函数
     *
     * @param array 待查找的数组
     * @param value 待查找的值
     * @return 待查找的值的下标，不存在返回-1
     */
    private static int sequentialSearch(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return  -1;
    }
}
