package floyd;

import java.util.Arrays;

/**
 * 最短路径-弗洛伊德算法
 *
 * @author Neil
 * @version v1.0
 * @date 2021/3/1 22:17
 */
public class FloydAlgorithm {
    public static void main(String[] args) {
        // 测试看看图是否创建成功
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //创建邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[] {0, 5, 7, N, N, N, 2};
        matrix[1] = new int[] {5, 0, N, 9, N, N, 3};
        matrix[2] = new int[] {7, N, 0, N, 8, N, N};
        matrix[3] = new int[] {N, 9, N, 0, N, 4, N};
        matrix[4] = new int[] {N, N, 8, N, 0, 5, 4};
        matrix[5] = new int[] {N, N, N, 4, 5, 0, 6};
        matrix[6] = new int[] {2, 3, N, N, 4, 6, 0};

        //创建 Graph 对象
        Graph graph = new Graph(vertex.length, matrix, vertex);
        //调用弗洛伊德算法
        graph.floyd();
        graph.show();
    }
}

/**
 * 创建图
 */
class Graph {
    /**
     * 存放顶点的数组
     */
    private final char[] vertex;
    /**
     * 保存从各个顶点出发到其他顶点的距离，最后的结果也是保留在该数组
     */
    private final int[][] distance;
    /**
     * 前驱顶点数组
     */
    private final int[][] preVertex;

    /**
     * 构造器
     *
     * @param numberOfVertexes 顶点数量
     * @param matrix           邻接矩阵
     * @param vertex           顶点数组
     */
    public Graph(int numberOfVertexes, int[][] matrix, char[] vertex) {
        this.vertex = vertex;
        this.distance = matrix;
        this.preVertex = new int[numberOfVertexes][numberOfVertexes];
        // 初始化preVertex数组
        for (int i = 0; i < numberOfVertexes; i++) {
            Arrays.fill(preVertex[i], i);
        }
    }

    /**
     * 显示preVertex数组和distance数组
     */
    public void show() {
        for (int i = 0; i < distance.length; i++) {
            // 输出一行preVertex
            for (int j = 0; j < distance.length; j++) {
                System.out.print(vertex[preVertex[i][j]]);
            }
            System.out.println();
            // 输出distance数组
            for (int j = 0; j < distance.length; j++) {
                System.out.print("(" + vertex[i] + "到" + vertex[j] + "的最短路径是" + distance[i][j] + ")");
            }
            System.out.println();
        }
    }

    /**
     * 弗洛伊德算法
     */
    public void floyd() {
        int tempDistance;
        // 遍历中间顶点，i是中间顶点的索引
        for (int k = 0; k < distance.length; k++) {
            for (int i = 0; i < distance.length; i++) {
                for (int j = 0; j < distance.length; j++) {
                    tempDistance = distance[i][k] + distance[k][j];
                    if (tempDistance < distance[i][j]) {
                        distance[i][j] = tempDistance;
                        preVertex[i][j] = preVertex[k][j];
                    }
                }
            }
        }
    }
}