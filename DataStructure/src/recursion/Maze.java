package recursion;

/**
 * 递归-迷宫问题
 *
 * @author Neil
 * @version v1.0
 * @date 2021/2/6 16:01
 */
public class Maze {
    /**
     * 地图大小，行和列
     */
    private static final int ROW = 8;
    private static final int COL = 7;
    /**
     * 终点位置
     */
    private static final int FINAL_ROW = 6;
    private static final int FINAL_COL = 5;
    /**
     * 约定：当map[i][j]为0表示该点没有走过，为1表示墙，为2表示通路可以走，3表示该点已经走过，但是走不通
     */
    private static final int HAVE_NOT_ACCESS = 0;
    private static final int WALL = 1;
    private static final int ACCESS = 2;
    private static final int CAN_NOT_ACCESS = 3;

    public static void main(String[] args) {
        // 先创建一个二维数组模拟迷宫
        int[][] map = new int[ROW][COL];
        // 1表示墙，上下左右全部置为1
        for (int i = 0; i < COL; i++) {
            map[0][i] = WALL;
            map[ROW - 1][i] = WALL;
        }
        for (int i = 0; i < ROW; i++) {
            map[i][0] = WALL;
            map[i][COL - 1] = WALL;
        }
        // 设置挡板
        map[3][2] = WALL;
        map[3][3] = WALL;
        map[1][2] = WALL;
        map[2][2] = WALL;

        // 输出地图
        System.out.println("地图的情况：");
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();

        }

        // 使用递归回溯给小球找路
        setWay(map, 1, 1);

        // 输出新的地图并标识小球走过的递归
        System.out.println("小球走过后的地图的情况：");
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();

        }
    }



    /**
     * 使用递归给回溯给小球找路
     * 说明
     * 1.map表示迷宫地图
     * 2.i，j表示从地图出发的位置
     * 3.如果小球能找到map[6][5]位置，说明通路找到
     * 4.约定：当map[i][j]为0表示该点没有走过，为1表示墙，为2表示通路可以走，3表示该点已经走过，但是走不通
     * 5.走迷宫需要确定一个策略，下右上左，如果该点走不通，再回溯
     *
     * @param map 迷宫地图
     * @param i 从地图出发的行
     * @param j 从地图出发的列
     * @return 是否找到通路
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[FINAL_ROW][FINAL_COL] == ACCESS) {
            return true;
        } else {
            if (map[i][j] == HAVE_NOT_ACCESS) {
                // 按照策略下右上左走
                map[i][j] = ACCESS;
                if (setWay(map, i + 1, j)) {
                    return true;
                } else if (setWay(map, i, j + 1)) {
                    return true;
                } else if (setWay(map, i - 1, j)) {
                    return true;
                } else if (setWay(map, i, j - 1)) {
                    return true;
                } else {
                    // 说明该点死路，走不通
                    map[i][j] = CAN_NOT_ACCESS;
                    return false;
                }
            } else {
                return false;
            }
        }

    }
}
