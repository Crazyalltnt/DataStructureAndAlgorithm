package sort;

import java.util.Arrays;

/**
 * 基数排序
 *
 * @author Neil
 * @version v1.0
 * @date 2021/2/8 18:28
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] array = {53, 3, 542, 748, 14, 214, 542, 53};

        System.out.println("排序前：");
        System.out.println(Arrays.toString(array));

        radixSort(array);

        System.out.println("排序后：");
        System.out.println(Arrays.toString(array));
    }

    public static void radixSort(int[] array) {
        int numberOfBuckets = 10;
        // 1.得到数组中最大的数的位数
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        // 得到的最大数是几位数
        int maxLength = (max + "").length();

        /*
        * 定义一个二维数组，表示诗歌桶，每个桶是一个一维数组
        * 说明
        * 1.二维数组包含10个一维数组
        * 2.为了放置在放入数的时候数据溢出，则每个一维数组大小为 array.length
        * 3.基数排序是使用空间换时间的经典算法
        * */
        int[][] bucket = new int[numberOfBuckets][array.length];

        // 为了记录每个桶中放了多少个数据，定义一个一维数组，对应索引的值表示对应桶的数据个数
        int[] bucketElementCounts = new int[numberOfBuckets];

        for (int i = 0, n = 1; i < maxLength; i++, n *= numberOfBuckets) {
            // 从个位开始对每一位处理
            for (int element : array) {
                // 取出每个元素对应位的值
                int digitOfElement = element / n % 10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = element;
                bucketElementCounts[digitOfElement]++;
            }

            // 遍历桶，按照这个桶的顺序依次取出数据，放入原来的数组
            int index = 0;
            for (int j = 0; j < bucketElementCounts.length; j++) {
                if (bucketElementCounts[j] != 0) {
                    for (int k = 0; k < bucketElementCounts[j]; k++) {
                        array[index++] = bucket[j][k];
                    }
                }
                bucketElementCounts[j] = 0;
            }
        }
    }
}
