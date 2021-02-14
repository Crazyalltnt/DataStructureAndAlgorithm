package search;

import java.util.Arrays;

/**
 * 斐波那契查找 / 黄金分割查找
 *
 * @author Neil
 * @version v1.0
 * @date 2021/2/9 18:37
 */
public class FibonacciSearch {
    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] array = {1, 8, 10, 10, 89, 1000, 1000, 1000, 1234};
        int searchedValue = 1234;
        int index = fibonacciSearch(array, searchedValue);
        if (index == -1) {
            System.out.println("没有找到");
        } else {
            System.out.println("找到，下标为 " + index);
        }
    }

    private static int fibonacciSearch(int[] array, int searchedValue) {
        int low = 0;
        int high = array.length - 1;
        // 斐波那契分割数值的下标
        int fibonacciDivideIndex = 0;
        int mid;
        int[] fibonacci = getFibonacciSequence();
        // 获取斐波那契分割数值的下标
        while (high > fibonacci[fibonacciDivideIndex] - 1) {
            fibonacciDivideIndex++;
        }

        // fibonacci[fibonacciDivideIndex] 值可能大于array长度，因此我们需要使用Arrays类，构造一个新的数组，并指向temp[]
        // 不足的部分会使用 0 填充
        int[] temp = Arrays.copyOf(array, fibonacci[fibonacciDivideIndex]);
        // 实际上需要使用 array 数组最后的数填充 temp
        // 例如 temp={1,8,10,89,1000,1234,0,0} =》 {1,8,10,89,1000,1234,1234,1234}
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = array[high];
        }

        // 使用while循环处理，找到我们的数 searchedValue
        while (low <= high) {
            mid = low + fibonacci[fibonacciDivideIndex - 1] - 1;
            if (searchedValue < temp[mid]) {
                high = mid - 1;
                fibonacciDivideIndex--;
            } else if (searchedValue > temp[mid]) {
                low = mid + 1;
                fibonacciDivideIndex -= 2;
            } else {
                return Math.min(mid, high);
            }
        }
        return -1;
    }

    /**
     * 获取斐波那契数列
     *
     * @return 斐波那契数列
     */
    private static int[] getFibonacciSequence() {
        int[] fibonacci = new int[maxSize];
        fibonacci[0] = 1;
        fibonacci[1] = 1;

        for (int i = 2; i < maxSize; i++) {
            fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
        }
        return fibonacci;
    }
}
