package horse;

import java.awt.*;
import java.util.ArrayList;

/**
 * 马踏棋盘-骑士周游问题
 *
 * @author Neil
 * @version v1.0
 * @date 2021/3/2 16:42
 */
public class HorseChessboard {
    /**
     * 棋盘的列数
     */
    private static int xLabel;
    /**
     * 棋盘的行数
     */
    private static int yLabel;
    /**
     * 记录棋盘的各个位置是否被访问
     */
    private static boolean[] visited;
    /**
     * 表示是否完成游戏
     */
    private static boolean finished;

    public static void main(String[] args) {
        System.out.println("骑士周游算法，开始运行~~");
        //测试骑士周游算法是否正确
        xLabel = 8;
        yLabel = 8;
        //创建棋盘
        int[][] chessboard = new int[xLabel][yLabel];
        visited = new boolean[xLabel * yLabel];//初始值都是false
        //测试一下耗时
        long start = System.currentTimeMillis();
        traversalChessboard(chessboard, 0, 0, 1);
        long end = System.currentTimeMillis();
        System.out.println("共耗时: " + (end - start) + " 毫秒");

        //输出棋盘的最后情况
        for (int[] rows : chessboard) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 实现骑士周游问题解决算法
     *
     * @param chessboard 棋盘
     * @param row        当前行数（0开始）
     * @param column     当前列数（0开始）
     * @param step       当前是第几步（1开始）
     */
    public static void traversalChessboard(int[][] chessboard, int row, int column, int step) {
        chessboard[row][column] = step;
        // 标记该位置已访问
        visited[row * xLabel + column] = true;
        // 获取当前位置可以走的下一个位置的集合
        ArrayList<Point> points = next(new Point(column, row));
        // 对points排序，point对象的下一步的位置数目进行升序
        sort(points);
        while (!points.isEmpty()) {
            // 取出一个可以走的位置
            Point point = points.remove(0);
            // 判断该点是否已经访问过
            if (!visited[point.y * xLabel + point.x]) {
                traversalChessboard(chessboard, point.y, point.x, step + 1);
            }
        }
        // 判断是否完成任务，没有完成棋盘重置为0
        if (step < xLabel * yLabel && !finished) {
            chessboard[row][column] = 0;
            visited[row * xLabel + column] = false;
        } else {
            finished = true;
        }

    }

    /**
     * 根据当前位置（point对象），计算马还能走哪些位置，并放入到一个集合中，最多八个位置如下
     * 6  7
     * 5      0
     * #
     * 4      1
     * 3   2
     *
     * @param currentPoint 当前位置
     * @return 可走的位置集合
     */
    private static ArrayList<Point> next(Point currentPoint) {
        ArrayList<Point> pointArrayList = new ArrayList<>();
        Point nextPoint = new Point();
        // 判断马可以走位置5
        if ((nextPoint.x = currentPoint.x - 2) >= 0 && (nextPoint.y = currentPoint.y - 1) >= 0) {
            pointArrayList.add(new Point(nextPoint));
        }
        // 判断马可以走位置6
        if ((nextPoint.x = currentPoint.x - 1) >= 0 && (nextPoint.y = currentPoint.y - 2) >= 0) {
            pointArrayList.add(new Point(nextPoint));
        }
        // 判断马可以走位置7
        if ((nextPoint.x = currentPoint.x + 1) < xLabel && (nextPoint.y = currentPoint.y - 2) >= 0) {
            pointArrayList.add(new Point(nextPoint));
        }
        // 判断马可以走位置0
        if ((nextPoint.x = currentPoint.x + 2) < xLabel && (nextPoint.y = currentPoint.y - 1) >= 0) {
            pointArrayList.add(new Point(nextPoint));
        }
        // 判断马可以走位置1
        if ((nextPoint.x = currentPoint.x + 2) < xLabel && (nextPoint.y = currentPoint.y + 1) < yLabel) {
            pointArrayList.add(new Point(nextPoint));
        }
        // 判断马可以走位置2
        if ((nextPoint.x = currentPoint.x + 1) < xLabel && (nextPoint.y = currentPoint.y + 2) < yLabel) {
            pointArrayList.add(new Point(nextPoint));
        }
        // 判断马可以走位置3
        if ((nextPoint.x = currentPoint.x - 1) >= 0 && (nextPoint.y = currentPoint.y + 2) < yLabel) {
            pointArrayList.add(new Point(nextPoint));
        }
        // 判断马可以走位置4
        if ((nextPoint.x = currentPoint.x - 2) >= 0 && (nextPoint.y = currentPoint.y + 1) < yLabel) {
            pointArrayList.add(new Point(nextPoint));
        }
        return pointArrayList;
    }

    /**
     * 对points数组根据每个对象下一个位置的选择数量进行升序排序以优化减少回溯
     *
     * @param points 当前位置
     */
    public static void sort(ArrayList<Point> points) {
        points.sort((o1, o2) -> {
            // 获取到o1的下一步的所有位置个数
            int count1 = next(o1).size();
            // 获取到o2的下一步的所有位置大的个数
            int count2 = next(o2).size();
            return Integer.compare(count1, count2);
        });
    }
}
