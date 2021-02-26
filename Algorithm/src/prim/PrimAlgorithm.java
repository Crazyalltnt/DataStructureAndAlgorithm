package prim;

import java.util.Arrays;

/**
 * 普里姆算法
 *
 * @author Neil
 * @version v1.0
 * @date 2021/2/26 14:23
 */
public class PrimAlgorithm {
    public static void main(String[] args) {
        //测试看看图是否创建
        char[] data = new char[]{'A','B','C','D','E','F','G'};
        int numberOfVertexes = data.length;
        //邻接矩阵的关系使用二维数组表示,10000这个大数，表示两个点不联通
        int [][]weight=new int[][]{
            {10000, 5, 7, 10000, 10000, 10000, 2},
            {5, 10000, 10000, 9, 10000, 10000, 3},
            {7, 10000, 10000, 10000, 8,10000, 10000},
            {10000, 9, 10000, 10000, 10000, 4, 10000},
            {10000, 10000, 8, 10000, 10000, 5, 4},
            {10000, 10000,10000, 4, 5, 10000, 6},
            {2, 3, 10000, 10000, 4, 6, 10000},};

        //创建MGraph对象
        Graph graph = new Graph(numberOfVertexes);
        //创建一个MinTree对象
        MinTree minTree = new MinTree();
        minTree.createGraph(graph, numberOfVertexes, data, weight);
        //输出
        minTree.showGraph(graph);
        //测试普利姆算法
        minTree.prim(graph, 1);
    }
}

/**
 * 创建最小生成树
 */
class MinTree {
    /**
     * 创建图的邻接矩阵
     *
     * @param graph            图对象
     * @param numberOfVertexes 图的顶点个数
     * @param data             图的顶点的值
     * @param weights          图的邻接矩阵
     */
    public void createGraph(Graph graph, int numberOfVertexes, char[] data, int[][] weights) {
        for (int i = 0; i < numberOfVertexes; i++) {
            graph.data[i] = data[i];
            System.arraycopy(weights[i], 0, graph.weights[i], 0, numberOfVertexes);
        }
    }

    /**
     * 显示图的邻接矩阵
     *
     * @param graph 图对象
     */
    public void showGraph(Graph graph) {
        for (int[] link : graph.weights) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * prim算法生成最小生成树
     *
     * @param graph       图对象
     * @param vertexIndex 最小生成树的起始顶点索引
     */
    public void prim(Graph graph, int vertexIndex) {
        // 标记已访问顶点
        int[] visited = new int[graph.numberOfVertexes];
        for (int i = 0; i < graph.numberOfVertexes; i++) {
            visited[i] = 0;
        }

        visited[vertexIndex] = 1;
        // 记录两个顶点下标
        int startVertexIndex = -1;
        int endVertexIndex = -1;
        int minWeight = 10000;

        // 边的数量是顶点数减一
        for (int i = 1; i < graph.numberOfVertexes; i++) {
            // 确定每一次生成的子图和哪个结点的距离最近
            for (int j = 0; j < graph.numberOfVertexes; j++) {
                for (int k = 0; k < graph.numberOfVertexes; k++) {
                    if (visited[j] == 1 && visited[k] == 0 && graph.weights[j][k] < minWeight) {
                        // 替换minWeight，寻找已访问结点集合与未访问结点集合之间的权值最小的边
                        minWeight = graph.weights[j][k];
                        startVertexIndex = j;
                        endVertexIndex = k;
                    }
                }
            }
            System.out.println("边 <" + graph.data[startVertexIndex] + "," + graph.data[endVertexIndex] + "> 权值：" + minWeight);
            visited[endVertexIndex] = 1;
            // minWeight重新设为最大值
            minWeight = 10000;
        }
    }
}

/**
 * 图的实现类
 */
class Graph {
    /**
     * 图的顶点数
     */
    int numberOfVertexes;
    /**
     * 存放顶点的值
     */
    char[] data;
    /**
     * 存放边的邻接矩阵
     */
    int[][] weights;

    public Graph(int numberOfVertexes) {
        this.numberOfVertexes = numberOfVertexes;
        data = new char[numberOfVertexes];
        weights = new int[numberOfVertexes][numberOfVertexes];
    }
}
