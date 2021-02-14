package search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找
 * 针对有序列表
 *
 * @author Neil
 * @version v1.0
 * @date 2021/2/9 14:31
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] array = {1, 8, 10, 89, 1000, 1000, 1234};
        int searchedValue = 1000;
        List<Integer> resultIndexList = binarySearch(array, 0, array.length - 1, searchedValue);
        if (resultIndexList.size() == 0) {
            System.out.println("没有找到");
        } else {
            System.out.println("找到，下标为 " + resultIndexList);
        }
    }

    /**
     * 二分查找实现函数 可以查找到所有相同值
     * 思路分析
     * 1.在找到mid索引值时，不要马上返回
     * 2.向mid索引值的左边扫描，将所有满足 searchedValue 的元素下标加入到集合
     * 3.向mid索引值的右边扫描，将所有满足 searchedValue 的元素下标加入到集合
     * 4.返回 ArrayList
     *
     * @param array 待查找数组
     * @param left 左侧索引
     * @param right 右侧索引
     * @param searchedValue 待查找的值
     * @return 待查找数的下标集合
     */
    private static List<Integer> binarySearch(int[] array, int left, int right, int searchedValue) {
        if (left > right) {
            return new ArrayList<>();
        }

        int mid = (left + right) / 2;
        int midValue = array[mid];
        if (searchedValue > midValue) {
            return binarySearch(array, mid + 1, right, searchedValue);
        } else if (searchedValue < midValue) {
            return binarySearch(array, left, mid - 1, searchedValue);
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
