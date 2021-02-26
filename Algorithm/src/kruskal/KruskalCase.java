package kruskal;

import java.util.Arrays;

/**
 * 克鲁斯卡尔算法
 *
 * @author Neil
 * @version v1.0
 * @date 2021/2/26 17:40
 */
public class KruskalCase {
    public static void main(String[] args) {

    }
}

/**
 * 图的实现类
 */
class Graph {
    /**
     * 边的个数
     */
    private int numberOfEdge;
    /**
     * 顶点数组
     */
    private char[] vertexes;
    /**
     * 邻接矩阵
     */
    private int[][] matrix;
    private final int NOT_ACCESSED = Integer.MAX_VALUE;

    /**
     * 构造器
     *
     * @param vertexes 顶点数组
     * @param matrix   邻接矩阵
     */
    public Graph(char[] vertexes, int[][] matrix) {
        // 初始化顶点数和边数
        int vertexesLength = vertexes.length;
        // 初始化顶点
        this.vertexes = new char[vertexesLength];
        System.arraycopy(vertexes, 0, this.vertexes, 0, vertexesLength);
        // 初始化边，并统计条数
        this.matrix = new int[vertexesLength][vertexesLength];
        for (int i = 0; i < vertexesLength; i++) {
            for (int j = 0; j < vertexesLength; j++) {
                this.matrix[i][j] = matrix[i][j];
                if (this.matrix[i][j] != NOT_ACCESSED) {
                    numberOfEdge++;
                }
            }
        }
    }

    /**
     * 克鲁斯卡尔算法实现
     */
    public void kruskal() {
        // 最后结果数组的索引
        int index = 0;
        // 保存最小生成树中每个顶点的终点
        int[] ends = new int[numberOfEdge];
        // 创建结果数组，保存最小生成树
        Edge[] result = new Edge[numberOfEdge];
        // 获取图中所有边的集合
        Edge[] edges = getEdges();
        System.out.println("图的边的集合=" + Arrays.toString(edges) + " 共" + edges.length);

        // 按照边的权值大小进行排序
        sortEdge(edges);
        // 遍历edges数组，将边添加到最小生成树中时，判断准备加入的边是否形成回路，没有就加入
        for (int i = 0; i < numberOfEdge; i++) {
            // 获取第i条边的第1个顶点（起点）
            int p1 = getPosition(edges[i].start);
            // 获取第i条边的第2个顶点
            int p2 = getPosition(edges[i].end);
            // 获取p1这个顶点在已有最小生成树的中的终点
            int m = getEnd(ends, p1);
            // 获取p2这个顶点在已有最小生成树中的终点
            int n = getEnd(ends, p2);
            // 判断是否构成回路
            if (m != n) {
                // 没有构成回路
                // 设置m在已有最小生成树中的终点
                ends[m] = n;
                result[index++] = edges[i];
            }
        }
        // 统计并打印最小生成树
        System.out.println("最小生成树为");
        for (int i = 0; i < index; i++) {
            System.out.println(result[i]);
        }
    }

    private int getEnd(int[] ends, int p1) {
    }

    private int getPosition(char start) {
    }

    private void sortEdge(Edge[] edges) {
    }

    private Edge[] getEdges() {
    }
}

/**
 * 边的实现类
 */
class Edge {
    /**
     * 边的起点
     */
    char start;
    /**
     * 边的终点
     */
    char end;
    /**
     * 边的权值
     */
    int weight;

    public Edge(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override public String toString() {
        return "Edge[<" + start + "," + end + ">=" + weight + ']';
    }
}