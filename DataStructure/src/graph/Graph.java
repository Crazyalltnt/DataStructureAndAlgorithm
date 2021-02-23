package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * 图
 *
 * @author Neil
 * @version v1.0
 * @date 2021/2/22 20:38
 */
public class Graph {
    /**
     * 存储顶点集合
     */
    private final ArrayList<String> vertexList;
    /**
     * 存储图的边邻接矩阵
     */
    private int[][] edges;
    /**
     * 边的数量
     */
    private int numberOfEdges;
    /**
     * 访问标记，已访问为true
     */
    private boolean[] accessed;

    public static void main(String[] args) {
        // 测试图创建
        // String[] vertexesArray = {"A", "B", "C", "D", "E"};
        String[] vertexesArray = {"1", "2", "3", "4", "5", "6", "7", "8"};
        int numberOfVertexes = vertexesArray.length;
        Graph graph = new Graph(numberOfVertexes);

        // 添加顶点
        for (String vertex : vertexesArray) {
            graph.insertVertex(vertex);
        }

        // 添加边
        // A-B A-C B-C B-D B-E
        // graph.insertEdge(0, 1, 1);
        // graph.insertEdge(0, 2, 1);
        // graph.insertEdge(1, 2, 1);
        // graph.insertEdge(1, 3, 1);
        // graph.insertEdge(1, 4, 1);

        //更新边的关系
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);

        // 显示
        graph.showGraphMatrix();

        // 测试深度优先遍历 1->2->4->8->5->3->6->7
        // graph.dfs();

        // 测试广度优先遍历 1->2->3->4->5->6->7->8
        graph.bfs();

    }

    /**
     * 构造器
     *
     * @param numberOfVertexes 定点数量
     */
    public Graph(int numberOfVertexes) {
        edges = new int[numberOfVertexes][numberOfVertexes];
        vertexList = new ArrayList<>();
        accessed = new boolean[numberOfVertexes];
    }

    /**
     * 获取顶点数量
     *
     * @return 顶点数量
     */
    public int getNumberOfVertex() {
        return vertexList.size();
    }

    /**
     * 获取边的数量
     *
     * @return 边的数量
     */
    public int getNumberOfEdges() {
        return numberOfEdges;
    }

    /**
     * 获取指定索引顶点对应的数据
     *
     * @param i 索引
     * @return 顶点对应数据
     */
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    public int getWeight(int vertex1, int vertex2) {
        return edges[vertex1][vertex2];
    }

    public void showGraphMatrix() {
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }

    /**
     * @param vertex 插入结点
     */
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 图插入边
     *
     * @param vertex1 边的一个顶点
     * @param vertex2 边的另一个顶点
     * @param weight  边的权重
     */
    public void insertEdge(int vertex1, int vertex2, int weight) {
        edges[vertex1][vertex2] = weight;
        edges[vertex2][vertex1] = weight;
        numberOfEdges++;
    }

    /**
     * 获取第一个邻接结点的下标
     *
     * @param index 初始结点
     * @return 第一个邻接结点下标
     */
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 根据前一个邻接结点下标获取下一个邻接结点下标
     *
     * @param vertex1 当前结点
     * @param vertex2 当前结点的邻接结点
     * @return 邻接结点下标
     */
    public int getNextNeighbor(int vertex1, int vertex2) {
        for (int i = vertex2 + 1; i < vertexList.size(); i++) {
            if (edges[vertex1][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 深度优先遍历
     *
     * @param accessed    访问标记
     * @param vertexIndex 当前结点下标
     */
    private void dfs(boolean[] accessed, int vertexIndex) {
        System.out.print(getValueByIndex(vertexIndex) + "->");
        accessed[vertexIndex] = true;
        // 查找结点i的第一个邻接结点
        int neighborVertexIndex = getFirstNeighbor(vertexIndex);
        while (neighborVertexIndex != -1) {
            // 有第一个邻接结点
            if (!accessed[neighborVertexIndex]) {
                dfs(accessed, neighborVertexIndex);
            }
            // 如果已经被访问，查找下一个邻接结点
            neighborVertexIndex = getNextNeighbor(vertexIndex, neighborVertexIndex);
        }
    }

    /**
     * 深度优先遍历所有节点
     */
    public void dfs() {
        for (int i = 0; i < getNumberOfVertex(); i++) {
            if (!accessed[i]) {
                dfs(accessed, i);
            }
        }
    }

    /**
     * 广度优先遍历
     *
     * @param accessed    访问标记
     * @param vertexIndex 当前结点下标
     */
    private void bfs(boolean[] accessed, int vertexIndex) {
        // 头节点下标
        int headVertexIndex;
        // 邻接结点
        int neighborVertexIndex;
        // 队列记录访问顺序
        LinkedList<Integer> queue = new LinkedList<>();
        // 访问结点
        System.out.print(getValueByIndex(vertexIndex) + "->");
        accessed[vertexIndex] = true;
        queue.addLast(vertexIndex);

        while (!queue.isEmpty()) {
            headVertexIndex = queue.removeFirst();
            neighborVertexIndex = getFirstNeighbor(headVertexIndex);
            while (neighborVertexIndex != -1) {
                if (!accessed[neighborVertexIndex]) {
                    System.out.print(getValueByIndex(neighborVertexIndex) + "->");
                    accessed[neighborVertexIndex] = true;
                    queue.addLast(neighborVertexIndex);
                }
                neighborVertexIndex = getNextNeighbor(headVertexIndex, neighborVertexIndex);
            }
        }
    }

    /**
     * 广度优先遍历所有节点
     */
    public void bfs() {
        for (int i = 0; i < getNumberOfVertex(); i++) {
            if (!accessed[i]) {
                bfs(accessed, i);
            }
        }
    }
}
