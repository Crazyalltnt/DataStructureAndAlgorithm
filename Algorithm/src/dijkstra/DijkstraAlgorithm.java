package dijkstra;

import java.util.Arrays;

/**
 * 最短路径-迪杰斯特拉算法
 *
 * @author Neil
 * @version v1.0
 * @date 2021/2/28 20:36
 */
public class DijkstraAlgorithm {
    public static void main(String[] args) {

    }
}

class Graph {
    /**
     * 顶点数组
     */
    private char[] vertex;
    /**
     * 邻接矩阵
     */
    private int[][] matrix;
    /**
     * 已访问顶点集合
     */
    private VisitedVertex visitedVertex;

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    /**
     * 显示结果
     */
    public void showDijkstra() {
        visitedVertex.show();
    }

    /**
     * 显示图的邻接矩阵
     */
    public void showGraph() {
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 迪杰斯特拉算法实现
     *
     * @param index 初始顶点索引
     */
    public void dijkstra(int index) {
        visitedVertex = new VisitedVertex(vertex.length, index);
        // 更新index顶点到周围顶点的距离和前驱顶点
        update(index);
        for (int i = 0; i < vertex.length; i++) {
            // 选择并返回新的访问顶点
            index = visitedVertex.updateVisited();
            // 更新index顶点到周围顶点的距离和前驱顶点
            update(index);
        }
    }

    /**
     * 更新当前顶点到周围顶点的距离和周围顶点的前驱顶点
     *
     * @param index 当前顶点索引
     */
    public void update(int index) {
        // 出发顶点到index顶点的距离
        int distance = 0;
        for (int i = 0; i < matrix[index].length; i++) {
            distance = visitedVertex.getDistance(index) + matrix[index][i];
            if (!visitedVertex.visited(i) && distance < visitedVertex.getDistance(i)) {
                visitedVertex.updatePreVertex(i, index);
                visitedVertex.updateDistance(i, distance);
            }
        }
    }
}

/**
 * 已访问顶点集合
 */
class VisitedVertex {
    /**
     * 记录顶点是否访问过，1表示已访问，0表示未访问
     */
    public int[] visited;
    /**
     * 已访问顶点的前一个顶点索引值
     */
    public int[] preVisited;
    /**
     * 记录出发顶点到其他所有顶点的当前最短距离，动态更新完成就是最短距离
     */
    public int[] distance;

    /**
     * @param numberOfVertexes 顶点数量
     * @param index            初始顶点索引
     */
    public VisitedVertex(int numberOfVertexes, int index) {
        this.visited = new int[numberOfVertexes];
        this.preVisited = new int[numberOfVertexes];
        this.distance = new int[numberOfVertexes];

        // 初始化distance数组
        Arrays.fill(distance, Integer.MAX_VALUE);
        // 设置出发顶点被访问过
        this.visited[index] = 1;
        // 设置出发顶点的访问距离为0
        this.distance[index] = 0;
    }

    /**
     * 判断指定索引顶点是否被访问过
     *
     * @param index 顶点索引
     * @return 顶点已访问返回true，未访问返回false
     */
    public boolean visited(int index) {
        return visited[index] == 1;
    }

    /**
     * 更新出发顶点到当前顶点的距离
     *
     * @param index       当前顶点索引
     * @param newDistance 新的最短距离
     */
    public void updateDistance(int index, int newDistance) {
        distance[index] = newDistance;
    }

    /**
     * 更新当前顶点的前驱顶点
     *
     * @param currentVertex 当前顶点
     * @param preVertex     当前顶点的前驱顶点
     */
    public void updatePreVertex(int currentVertex, int preVertex) {
        preVisited[currentVertex] = preVertex;
    }

    /**
     * 获取初始顶点到当前顶点的距离
     *
     * @param index 当前顶点索引
     * @return 初始顶点到当前顶点的距离
     */
    public int getDistance(int index) {
        return distance[index];
    }

    /**
     * 返回本次迭代时新的访问顶点
     *
     * @return 新的访问顶点
     */
    public int updateVisited() {
        int minDistance = Integer.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < visited.length; i++) {
            if (visited[index] == 0 && distance[i] < minDistance) {
                minDistance = distance[i];
                index = i;
            }
        }
        // 标记index顶点被访问过
        visited[index] = 1;
        return index;
    }

    public void show() {
        System.out.println("======================================");
        // 输出visited
        for (int i : visited) {
            System.out.print(i + " ");
        }
        System.out.println();
        // 输出visited
        for (int i : preVisited) {
            System.out.print(i + " ");
        }
        System.out.println();
        char[] vertex = {'A','B','C', 'D','E','F','G'};
        int count = 0;
        for (int i : distance) {
            if (i != Integer.MAX_VALUE) {
                System.out.print(vertex[count] + "(" + i + ")");
            } else {
                System.out.println("N");
            }
            count++;
        }
        System.out.println();
    }
}