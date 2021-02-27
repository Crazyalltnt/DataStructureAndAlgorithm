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
        char[] vertexes = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //克鲁斯卡尔算法的邻接矩阵
        int[][] matrix = {
            /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
            /*A*/ {   0,  12, Graph.NOT_ACCESSED, Graph.NOT_ACCESSED, Graph.NOT_ACCESSED,  16,  14},
            /*B*/ {  12,   0,  10, Graph.NOT_ACCESSED, Graph.NOT_ACCESSED,   7, Graph.NOT_ACCESSED},
            /*C*/ { Graph.NOT_ACCESSED,  10,   0,   3,   5,   6, Graph.NOT_ACCESSED},
            /*D*/ { Graph.NOT_ACCESSED, Graph.NOT_ACCESSED,   3,   0,   4, Graph.NOT_ACCESSED, Graph.NOT_ACCESSED},
            /*E*/ { Graph.NOT_ACCESSED, Graph.NOT_ACCESSED,   5,   4,   0,   2,   8},
            /*F*/ {  16,   7,   6, Graph.NOT_ACCESSED,   2,   0,   9},
            /*G*/ {  14, Graph.NOT_ACCESSED, Graph.NOT_ACCESSED, Graph.NOT_ACCESSED,   8,   9,   0}};

        //创建KruskalCase 对象实例
        Graph graph = new Graph(vertexes, matrix);
        //输出构建的
        graph.print();
        graph.kruskal();
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
    private final char[] vertexes;
    /**
     * 邻接矩阵
     */
    private int[][] matrix;
    public static final int NOT_ACCESSED = Integer.MAX_VALUE;

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

    /**
     * 打印邻接矩阵
     */
    public void print() {
        System.out.println("邻接矩阵为：\n");
        for (int i = 0; i < vertexes.length; i++) {
            for (int j = 0; j < vertexes.length; j++) {
                System.out.printf("%12d", matrix[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 通过邻接矩阵获取图中的边
     *
     * @return 边对象数组
     */
    private Edge[] getEdges() {
        int index = 0;
        Edge[] edges = new Edge[numberOfEdge];
        for (int i = 0; i < vertexes.length; i++) {
            for (int j = 0; j < vertexes.length; j++) {
                if (matrix[i][j] != NOT_ACCESSED) {
                    edges[index++] = new Edge(vertexes[i], vertexes[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 对边按权重进行排序
     *
     * @param edges 边的对象数组
     */
    private void sortEdge(Edge[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    Edge temp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 根据指定顶点值获取对应的索引值
     *
     * @param vertexData 指定顶点值
     * @return 顶点对应索引
     */
    private int getPosition(char vertexData) {
        for (int i = 0; i < vertexes.length; i++) {
            if (vertexes[i] == vertexData) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取索引为index的顶点的终点，用于判断两个顶点的终点是否相同
     *
     * @param ends  记录各个顶点的终点
     * @param index 顶点索引
     * @return 顶点对应终点的索引
     */
    private int getEnd(int[] ends, int index) {
        while (ends[index] != 0) {
            index = ends[index];
        }
        return index;
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