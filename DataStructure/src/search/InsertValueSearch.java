package search;

import java.util.ArrayList;
import java.util.List;

/**
 * 插值排序
 *
 * @author Neil
 * @version v1.0
 * @date 2021/2/9 18:19
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] array = {1, 8, 10, 10, 89, 1000, 1000, 1000, 1234};
        int searchedValue = 10;
        List<Integer> resultIndexList = insertValueSearch(array, 0, array.length - 1, searchedValue);
        if (resultIndexList.size() == 0) {
            System.out.println("没有找到");
        } else {
            System.out.println("找到，下标为 " + resultIndexList);
        }
    }

    /**
     * 插值查找实现函数，要求数组是有序的
     *
     * @param array 待查找数组
     * @param left 左侧索引
     * @param right 右侧索引
     * @param searchedValue 待查找的值
     * @return 待查找的值的下标，没找到返回-1
     */
    private static List<Integer> insertValueSearch(int[] array, int left, int right, int searchedValue) {
        if (left > right || searchedValue < array[0] || searchedValue > array[array.length - 1]) {
            return new ArrayList<>();
        }

        int mid = left + (right - left) * (searchedValue - array[left]) / (array[right] - array[left]);
        int midValue = array[mid];
        if (searchedValue > midValue) {
            return insertValueSearch(array, mid + 1, right, searchedValue);
        } else if (searchedValue < midValue) {
            return insertValueSearch(array, left, mid - 1, searchedValue);
        } else {
            List<Integer> resultIndexList = new ArrayList<>();

            int temp = mid - 1;
            while (temp >=0 && array[temp] == searchedValue) {
                resultIndexList.add(temp);
                temp--;
            }
            resultIndexList.add(mid);

            temp = mid + 1;
            while (temp <= array.length - 1 && array[temp] == searchedValue) {
                resultIndexList.add(temp);
                temp++;
            }

            return resultIndexList;
        }
    }

}
