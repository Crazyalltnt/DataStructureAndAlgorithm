package kmp;

/**
 * 暴力匹配字符串
 *
 * @author Neil
 * @version v1.0
 * @date 2021/2/25 14:18
 */
public class ViolenceMatch {
    public static void main(String[] args) {

    }

    /**
     * 暴力匹配字符串
     *
     * @param string1 长字符串
     * @param string2 待匹配字串
     * @return 匹配到的子串索引
     */
    public static int violenceMatch(String string1, String string2) {
        char[] s1 = string1.toCharArray();
        char[] s2 = string2.toCharArray();

        int s1Length = s1.length;
        int s2Length = s2.length;

        int i = 0;
        int j = 0;

        while (i < s1Length && j < s2Length) {
            if (s1[i] == s2[j]) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }

        if (j == s2Length) {
            return i - j;
        } else {
            return -1;
        }
    }
}
