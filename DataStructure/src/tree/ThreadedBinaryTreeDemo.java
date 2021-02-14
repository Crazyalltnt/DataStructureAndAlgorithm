package tree;

/**
 * 线索二叉树
 *
 * @author Neil
 * @version v1.0
 * @date 2021/2/14 15:05
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {

        /*
         *     1
         *    / \
         *   3   6
         *  / \   \
         * 8  10  14
         * */

        //测试一把中序线索二叉树的功能
        HeroNode2 root = new HeroNode2(1, "tom");
        HeroNode2 node2 = new HeroNode2(3, "jack");
        HeroNode2 node3 = new HeroNode2(6, "smith");
        HeroNode2 node4 = new HeroNode2(8, "mary");
        HeroNode2 node5 = new HeroNode2(10, "king");
        HeroNode2 node6 = new HeroNode2(14, "dim");

        //二叉树，后面我们要递归创建, 现在简单处理使用手动创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //测试中序线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();

        //测试: 以10号节点测试
        HeroNode2 leftNode = node5.getLeft();
        HeroNode2 rightNode = node5.getRight();
        System.out.println("10号结点的前驱结点是 ="  + leftNode); //3
        System.out.println("10号结点的后继结点是="  + rightNode); //1

        //当线索化二叉树后，能在使用原来的遍历方法
        //threadedBinaryTree.infixOrder();
        System.out.println("使用线索化的方式遍历 线索化二叉树");
        threadedBinaryTree.threadedList(); // 8, 3, 10, 1, 14, 6
    }
}

class ThreadedBinaryTree {
    private HeroNode2 root;

    private HeroNode2 pre = null;

    public void setRoot(HeroNode2 root) {
        this.root = root;
    }

    /**
     * 线索化二叉树
     */
    public void threadedNodes() {
        this.threadedNodes(root);
    }

    /**
     * 中序线索化二叉树
     *
     * @param node 当前需要线索化的结点
     */
    public void threadedNodes(HeroNode2 node) {
        if (node == null) {
            return;
        }

        // 先线索化左子树
        threadedNodes(node.getLeft());

        // 线索化当前结点
        // 处理当前结点的前驱结点
        if (node.getLeft() == null) {
            // 让当前结点的左指针指向前驱结点
            node.setLeft(pre);
            // 修改当前结点的左指针的类型，指向前驱结点
            node.setLeftType(1);
        }

        // 处理后继结点
        if (pre != null && pre.getRight() == null) {
            // 让前驱结点的右指针指向当前结点
            pre.setRight(node);
            // 修改前驱结点的右指针类型
            pre.setRightType(1);
        }

        // 每处理一个结点后，让当前结点是下一个结点的前驱结点
        pre = node;

        // 线索化右子树
        threadedNodes(node.getRight());
    }

    /**
     * 遍历线索化二叉树的方法
     */
    public void threadedList() {
        // 定义一个变量，存储当前遍历的结点，从root开始
        HeroNode2 node = root;
        while (node != null) {
            // 循环找到left=1的结点时，第一个找到8节点
            // 后面随着遍历而变化，因为leftType=1时说明该结点是按照线索化处理后的有效结点
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }

            // 打印当前这个结点
            System.out.println(node);

            // 如果当前结点的右指针指向的是后继节点，就一直输出
            while (node.getRightType() == 1) {
                // 获取到当前结点的后继节点
                node = node.getRight();
                System.out.println(node);
            }
            // 替换这个遍历的结点
            node = node.getRight();
        }
    }

    /**
     * 删除结点
     *
     * @param no 待删除结点编号
     */
    public void deleteNode(int no) {
        if (root != null) {
            // 如果只有一个root结点，这里立即判断root是不是就是要删除的结点
            if (root.getNo() == no) {
                root = null;
            } else {
                //递归删除
                root.deleteNode(no);
            }
        } else {
            System.out.println("空树，不能删除~");
        }
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    /**
     * 前序遍历查找
     *
     * @param no 待查找结点编号
     * @return 待查找结点*/
    public HeroNode2 preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    /**
     * 中序遍历查找
     *
     * @param no 待查找结点编号
     * @return 待查找结点*/
    public HeroNode2 infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    public HeroNode2 postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }
}

class HeroNode2 {
    private int no;
    private String name;
    private HeroNode2 left;
    private HeroNode2 right;

    /**
     * 值为0表示指向左子树，值为1表示前驱结点
     */
    private int leftType;
    /**
     * 值为0表示指向右子树，值为1表示后驱结点
     */
    private int rightType;

    public HeroNode2(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode2 getLeft() {
        return left;
    }

    public void setLeft(HeroNode2 left) {
        this.left = left;
    }

    public HeroNode2 getRight() {
        return right;
    }

    public void setRight(HeroNode2 right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "HeroNode2{" + "no=" + no + ", name='" + name + '\'' + '}';
    }

    /**
     * 删除结点*
     * @param no 待删除结点的编号
     */
    public void deleteNode(int no) {
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }

        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }

        if (this.left != null) {
            this.left.deleteNode(no);
        }

        if (this.right != null) {
            this.right.deleteNode(no);
        }
    }

    /**
     * 前序遍历的方法
     */
    public void preOrder() {
        // 输出父结点
        System.out.println(this);

        // 递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }

        // 递归向右子树遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }


    /**
     * 中序遍历
     */
    public void infixOrder() {
        // 递归向左子树中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }

        // 输出父结点System.out.println(this);

        // 递归向右子树中序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }

        if (this.right != null) {
            this.right.postOrder();
        }

        System.out.println(this);
    }

    /**
     * 前序遍历查找
     *
     * @param no 待查找结点编号
     * @return 待查找结点*/
    public HeroNode2 preOrderSearch(int no) {
        System.out.println("进入前序遍历查找");

        // 比较当前结点是不是
        if (this.no == no) {
            return this;
        }

        // 1.则判断当前结点的左子结点是否为空，如果不为空，则递归前序查找
        // 2.如果左递归前序查找，找到结点，则返回
        HeroNode2 resultNode = null;
        if (this.left != null) {
            resultNode = this.left.preOrderSearch(no);
        }
        if (resultNode != null) {
            return resultNode;
        }

        // 1.左递归前序查找，找到结点，则返回，否则继续判断
        // 2.当前的结点的右子结点是否为空，如果不为空，则继续向右递归前序查找
        if (this.right != null) {
            resultNode = this.right.preOrderSearch(no);
        }

        return resultNode;
    }

    /**
     * 中序遍历查找
     *
     * @param no 待查找结点编号
     * @return 待查找结点*/
    public HeroNode2 infixOrderSearch(int no) {
        HeroNode2 resultNode = null;

        if (this.left != null) {
            resultNode = this.left.infixOrderSearch(no);
        }
        if (resultNode != null) {
            return resultNode;
        }

        System.out.println("进入中序遍历查找");

        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            resultNode = this.right.infixOrderSearch(no);
        }

        return resultNode;
    }

    /**
     * 后序遍历查找
     *
     * @param no 待查找结点编号
     * @return 带擦轰炸待查找结点*/
    public HeroNode2 postOrderSearch(int no) {
        HeroNode2 resultNode = null;

        if (this.left != null) {
            resultNode = this.left.postOrderSearch(no);
        }
        if (resultNode != null) {
            return resultNode;
        }

        if (this.right != null) {
            resultNode = this.right.postOrderSearch(no);
        }
        if (resultNode != null) {
            return resultNode;
        }

        System.out.println("进入后序遍历查找");
        if (this.no == no) {

            return this;
        }

        return null;
    }
}