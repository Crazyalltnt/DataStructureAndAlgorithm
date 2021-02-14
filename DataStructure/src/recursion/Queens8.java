package recursion;

/**
 * 递归-八皇后问题
 *
 * @author Neil
 * @version v1.0
 * @date 2021/2/6 17:25
 */
public class Queens8 {
    /**
     * 定义一个max表示共有多少个皇后
     */
    int max = 8;
    /**
     * 定义数组，保存皇后放置位置的结果，
     * locationOfQueensArray[i]=val表示第i+1个皇后放在第i+1行的第val+1列
     */
    int [] locationOfQueensArray= new int[max];

    static int count = 0;
    static int judgeCount = 0;

    public static void main(String[] args) {
        // 测试
        Queens8 queens8 = new Queens8();
        queens8.check(0);
        System.out.printf("一共有%d种解法\n", count);
        System.out.printf("一共判断冲突%d次", judgeCount);
    }

    /**
     * 放置第n个皇后
     *
     * @param n 第n个皇后
     */
    private void check(int n) {
        if (n == max) {
            print();
            return;
        }

        // 依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            // 先把当前皇后n放到该行第一列
            locationOfQueensArray[n] = i;
            // 判断当放置第n个皇后到i列时，是否冲突
            if (judge(n)) {
                // 不冲突时接着放n+1个皇后，开始递归
                check(n + 1);
            }
            // 如果冲突，就继续执行locationOfQueensArray[n]=i，即将第n个皇后放置在本行的后一个位置
        }
    }

    /**
     * 检测摆放当前第n个皇后时是否和之前的冲突，不冲突可以放
     *
     * @param n 第n个皇后
     * @return 是否和前面摆放的皇后冲突，冲突false不冲突true
     */
    private boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {
            /*
            * 说明
            * 1.第一个等式判断第n个皇后是否和前面的n-1个皇后在同一列
            * 2.第二个等式判断判断第n个皇后是否和第i个皇后在同一斜线
            * */
            if (locationOfQueensArray[i] == locationOfQueensArray[n] || Math.abs(n - i) == Math
                .abs(locationOfQueensArray[n] - locationOfQueensArray[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 打印皇后摆放的位置
     */
    private void print() {
        count++;
        for (int location : locationOfQueensArray) {
            System.out.print(location + " ");
        }
        System.out.println();
    }
}
