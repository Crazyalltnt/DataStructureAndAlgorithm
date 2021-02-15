package huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 哈夫曼树
 *
 * @author Neil
 * @version v1.0
 * @date 2021/2/15 18:59
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] array = {13, 7, 8, 3, 29, 6, 1};
        Node root = createHuffmanTree(array);
        preOrder(root);
    }

    /**
     * 创建哈夫曼树实现方法
     * 
     * @param array 需要创建成哈夫曼树的数组
     * @return 创建号后的哈夫曼树的根结点
     */
    private static Node createHuffmanTree(int[] array) {
        // 将array的每个元素构成一个Node，放入ArrayList
        List<Node> nodes = new ArrayList<>();
        for (int value : array) {
            nodes.add(new Node(value));
        }

        while (nodes.size() > 1) {
            // 从小到大排序
            Collections.sort(nodes);
            System.out.println("nodes = " + nodes);

            // 取出根结点权值最小的两棵二叉树
            // 1. 取出权值最小的结点
            Node leftNode = nodes.get(0);
            // 2. 取出权值第二小的结点
            Node rightNode = nodes.get(1);
            // 3. 构建一棵新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            // 4. 从ArrayList删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            // 5. 将parent加入到nodes
            nodes.add(parent);
        }

        // 返回哈夫曼树的root结点
        return nodes.get(0);
    }

    /**
     * 前序遍历
     * 
     * @param root 根结点
     */
    private static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("空树，无法遍历~");
        }
    }
}

/**
 * 创建结点类
 * 为了让Node对象支持排序，实现Comparable接口
 */
class Node implements Comparable<Node> {
    /**
     * 结点权值
     */
    int value;
    /**
     * 指向左子结点
     */
    Node left;
    /**
     * 指向右子结点
     */
    Node right;

    public Node(int value) {
        this.value = value;
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        System.out.println(this);

        if (this.left != null) {
            this.left.preOrder();
        }

        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override public String toString() {
        return "Node{" + "value=" + value + '}';
    }

    @Override public int compareTo(Node o) {
        // 从小到大排序
        return this.value - o.value;
    }
}
