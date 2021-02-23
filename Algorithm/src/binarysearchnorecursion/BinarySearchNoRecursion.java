package binarysearchnorecursion;

/**
 * 非递归二分查找算法
 *
 * @author Neil
 * @version v1.0
 * @date 2021/2/23 17:31
 */
public class BinarySearchNoRecursion {
    public static void main(String[] args) {
        // 测试
        int[] array = {1, 3, 8, 10, 11, 67, 100};
        int index = binarySearch(array, 100);
        System.out.println("index = " + index);
    }

    /**
     * 非递归二分查找
     *
     * @param array  待查找数组
     * @param target 待查中目标值
     * @return 目标值对应的数组索引
     */
    public static int binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
