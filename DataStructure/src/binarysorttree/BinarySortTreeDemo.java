package binarysorttree;

/**
 * 二叉排序树
 *
 * @author Neil
 * @version v1.0
 * @date 2021/2/19 14:40
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] array = {7, 3, 10, 12, 5, 1, 9};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i : array) {
            binarySortTree.add(new Node(i));
        }

        /*
        *        7
        *      /   \
        *     3    10
        *    / \   / \
        *   1   5 9   12
        *    \
        *     2
        * */

        System.out.println("中序遍历二叉排序树");
        binarySortTree.infixOrder();

        // 测试删除结点
        binarySortTree.deleteNode(1);
        System.out.println("删除结点后");
        binarySortTree.infixOrder();
    }
}

class BinarySortTree {
    private Node root;

    /**
     * 添加结点到二叉排序树
     *
     * @param node 待添加结点
     */
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("空树，无法遍历");
        }
    }

    /**
     * 查找指定节点
     *
     * @param value 待查找结点的值
     * @return 待查找结点
     */
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    /**
     * 查找待查找结点的父结点
     *
     * @param value 待查找结点的值
     * @return 待查找结点的父结点
     */
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    public void deleteNode(int value) {
        if (root == null) {
            return;
        }
        // 查找待删除结点
        Node targetNode = search(value);
        if (targetNode == null) {
            return;
        }
        // 只有一个根结点
        if (root.left == null && root.right == null) {
            root = null;
        }
        Node parentNode = searchParent(value);
        // 如果删除叶子结点
        if (targetNode.left == null && targetNode.right == null) {
            // 判断待删除结点是父结点的左子结点还是右子结点
            if (parentNode.left != null && parentNode.left.value == value) {
                // 左子结点
                parentNode.left = null;
            } else if (parentNode.right != null && parentNode.right.value == value) {
                // 右子结点
                parentNode.right = null;
            }
        } else if (targetNode.left != null && targetNode.right != null) {
            // 删除有两棵子树的结点
        } else {
            // 删除只有一棵子树的结点
            // 结点有左子树
            if (targetNode.left != null) {
                if (parentNode.left.value == value) {
                    // target是parent的左子节点
                    parentNode.left = targetNode.left;
                } else {
                    // target是parent的右子结点
                    parentNode.right = targetNode.left;
                }
            } else {
                // 结点有右子树
                // target是parent的左子节点
                if (parentNode.left.value == value) {
                    parentNode.left = targetNode.right;
                } else {
                    // target是parent的右子结点
                    parentNode.right = targetNode.right;
                }
            }
        }
    }
}

/**
 * 结点类
 */
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override public String toString() {
        return "Node{" + "value=" + value + '}';
    }

    /**
     * 添加结点
     *
     * @param node 待添加结点
     */
    public void add(Node node) {
        if (node == null) {
            return;
        }

        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }

        System.out.println(this);

        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    /**
     * 查找指定值的结点
     *
     * @param value 待查找的结点的值
     * @return 待查找的结点
     */
    public Node search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value) {
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    /**
     * 查找指定结点的父结点
     *
     * @param value 待查找的结点
     * @return 待查找结点的父结点
     */
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);
            } else {
                return null;
            }
        }
    }
}