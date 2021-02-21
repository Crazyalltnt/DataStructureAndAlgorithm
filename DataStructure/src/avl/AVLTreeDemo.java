package avl;

/**
 * 平衡二叉树
 *
 * @author Neil
 * @version v1.0
 * @date 2021/2/21 11:50
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
        // int[] array = {4, 3, 6, 5, 7, 8};
        int[] array = {10, 12, 8, 9, 7, 6};
        // 创建一个AVLTree对象
        AVLTree avlTree = new AVLTree();
        for (int j : array) {
            avlTree.add(new Node(j));
        }

        System.out.println("中序遍历");
        avlTree.infixOrder();

        /*
         *        4                    10
         *      /   \                /    \
         *     3     6              8     12
         *          / \            / \
         *         5   7          7   9
         *              \        /
         *               8      6
         * */
        System.out.println("平衡处理：");
        System.out.println("树的高度=" + avlTree.getRoot().height());
        System.out.println("左子树的高度=" + avlTree.getRoot().leftHeight());
        System.out.println("右子树的高度=" + avlTree.getRoot().rightHeight());
        System.out.println("当前的根结点是" + avlTree.getRoot());
    }
}

class AVLTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

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

    /**
     * 删除结点
     *
     * @param value 待删除结点
     */
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
            targetNode.value = delRightTreeMin(targetNode.right);
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

    /**
     * 查找以node为根结点的二叉排序树的最小结点的值，删除并返回
     *
     * @param node 传入的结点，当作根结点
     * @return 以node为根结点的二叉排序树的最小结点的值
     */
    public int delRightTreeMin(Node node) {
        Node target = node;
        // 循环查找左子结点，找到最小值
        while (target.left != null) {
            target = target.left;
        }
        // 删除最小结点
        deleteNode(target.value);
        return target.value;
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
     * @return 返回左子树的高度
     */
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    /**
     * @return 返回右子树的高度
     */
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    /**
     * 获取指定节点的高度
     *
     * @return 节点的高度
     */
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    /**
     * 左旋转
     */
    public void leftRotate() {
        // 以当前结点的值创建新结点
        Node newNode = new Node(value);
        // 把新结点的左子树设置成当前结点的左子树
        newNode.left = left;
        // 把新的结点的右子树设置成当前结点的右子树的左子树
        newNode.right = right.left;
        // 把当前结点的值换成右子结点的值
        value = right.value;
        // 把当前结点的右子树设置成当前结点的右子树的右子树
        right = right.right;
        // 把当前结点的左子结点设置成新的结点
        left = newNode;
    }

    /**
     * 右旋转
     */
    public void rightRotate() {
        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
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

        // 添加完结点后转为avl树
        if (rightHeight() - leftHeight() > 1) {
            leftRotate();
        }

        if (leftHeight() - rightHeight() > 1) {
            rightRotate();
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