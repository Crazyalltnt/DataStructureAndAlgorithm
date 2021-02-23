package dac;

/**
 * 分治算法解决汉诺塔问题
 *
 * @author Neil
 * @version v1.0
 * @date 2021/2/23 18:19
 */
public class HanoiTower {
    public static void main(String[] args) {
        // 测试汉诺塔
        hanoiTower(3, 'A', 'B', 'C');
    }

    /**
     * 完成汉诺塔任务
     *
     * @param number 圆盘数量
     * @param a 柱子a
     * @param b 柱子b
     * @param c 柱子c
     */
    public static void hanoiTower(int number, char a, char b, char c) {
        if (number == 1) {
            System.out.println("第1个盘从 " + a + "->" + c);
        } else {
            // n≥2时将柱子上的盘看作两个盘-最下面的一个盘和上面的所有盘
            // 先把最上面的所有盘从a->b，移动过程使用到c
            hanoiTower(number - 1, a, c, b);
            // 把最下面的盘a->c
            System.out.println("第" + number + "个盘从 " + a + "->" + c);
            // b柱子所有盘从b->c
            hanoiTower(number - 1, b, a, c);
        }
    }
}
