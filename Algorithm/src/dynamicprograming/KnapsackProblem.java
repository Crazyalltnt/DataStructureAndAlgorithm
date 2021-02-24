package dynamicprograming;

/**
 * 01背包问题
 *
 * @author Neil
 * @version v1.0
 * @date 2021/2/24 17:36
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        int[] weights = {1, 4, 3};
        int[] values = {1500, 3000, 2000};
        int capacity = 4;
        int maxVal = knapsackProblem(capacity, weights, values);
        System.out.println("最大价值是 " + maxVal);
    }

    /**
     * 背包问题解答
     *
     * @param capacity 背包容量
     * @param weights  商品大小数组
     * @param values   商品价值数组
     * @return 最大价值
     */
    public static int knapsackProblem(int capacity, int[] weights, int[] values) {
        int numberOfGoods = values.length;
        int[][] dp = new int[numberOfGoods + 1][capacity + 1];

        for (int i = 1; i <= numberOfGoods; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (j < weights[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i - 1]] + values[i - 1]);
                }
            }
        }
        return dp[numberOfGoods][capacity];
    }
}
