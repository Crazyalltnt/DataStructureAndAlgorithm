package kmp;

import java.util.Arrays;

/**
 * kmp算法实现
 *
 * @author Neil
 * @version v1.0
 * @date 2021/2/25 15:41
 */
public class KMPAlgorithm {
    public static void main(String[] args) {
        String string1 = "BBC ABCDAB ABCDABCDABDE";
        String string2 = "ABCDABD";

        int[] next = kmpNext(string2);
        System.out.println("next = " + Arrays.toString(next));

        int index = kmpSearch(string1, string2, next);
        System.out.println("index = " + index);

    }

    /**
     * kmp搜索算法
     *
     * @param string1 源字符串
     * @param string2 待匹配字串
     * @param next 子串对应的部分匹配表
     * @return 第一个匹配的位置，没有匹配返回-1
     */
    public static int kmpSearch(String string1, String string2, int[] next) {
        for (int i = 0, j = 0; i < string1.length(); i++) {
            while (j > 0 && string1.charAt(i) != string2.charAt(j)) {
                j = next[j - 1];
            }
            if (string1.charAt(i) == string2.charAt(j)) {
                j++;
            }
            if (j == string2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    /**
     * 获取kmp部分匹配值数组
     *
     * @param destination 待匹配子串
     * @return 部分匹配值数组
     */
    public static int[] kmpNext(String destination) {
        // 创建一个next数组保存部分匹配值
        int[] next = new int[destination.length()];
        // 字符串长度为1时部分匹配值为0
        next[0] = 0;
        for (int i = 1, j = 0; i < destination.length(); i++) {
            // 字符不相同时
            while (j > 0 && destination.charAt(i) != destination.charAt(j)) {
                j = next[j - 1];
            }

            // 字符相同时部分匹配值+1
            if (destination.charAt(i) == destination.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
