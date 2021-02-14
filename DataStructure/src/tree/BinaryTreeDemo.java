package tree;

/**
 * 二叉树
 *
 * @author Neil
 * @version v1.0
 * @date 2021/2/10 20:54
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();

        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node1 = new HeroNode(2, "吴用");
        HeroNode node2 = new HeroNode(3, "卢俊义");
        HeroNode node3 = new HeroNode(4, "林冲");
        HeroNode node4 = new HeroNode(5, "关胜");

        /*
        *   1
        *  / \
        * 2   3
        *    / \
        *   5   4
        * */

        root.setLeft(node1);
        root.setRight(node2);
        node2.setRight(node3);
        node2.setLeft(node4);
        binaryTree.setRoot(root);

        // 测试
        // 1 2 3 5 4
        System.out.println("前序遍历");
        binaryTree.preOrder();

        // 2 1 5 3 4
        System.out.println("中序遍历");
        binaryTree.infixOrder();

        // 2 5 4 3 1
        System.out.println("后序遍历");
        binaryTree.postOrder();

        //前序遍历
        //前序遍历的次数 ：4
        System.out.println("前序遍历方式~~~");
        HeroNode resultNode1 = binaryTree.preOrderSearch(5);
        if (resultNode1 != null) {
         System.out.printf("找到了，信息为 no=%d name=%s\n", resultNode1.getNo(), resultNode1.getName());
        } else {
         System.out.printf("没有找到 no = %d 的英雄\n", 5);
        }

        //中序遍历查找
        //中序遍历3次
        System.out.println("中序遍历方式~~~");
        HeroNode resultNode2 = binaryTree.infixOrderSearch(5);
        if (resultNode2 != null) {
         System.out.printf("找到了，信息为 no=%d name=%s\n", resultNode2.getNo(), resultNode2.getName());
        } else {
         System.out.printf("没有找到 no = %d 的英雄\n", 5);
        }

        //后序遍历查找
        //后序遍历查找的次数  2次
        System.out.println("后序遍历方式~~~");
        HeroNode resultNode3 = binaryTree.postOrderSearch(5);
        if (resultNode3 != null) {
         System.out.printf("找到了，信息为 no=%d name=%s\n", resultNode3.getNo(), resultNode3.getName());
        } else {
         System.out.printf("没有找到 no = %d 的英雄\n", 5);
        }

        //测试一把删除结点

        System.out.println("删除前,前序遍历");
        binaryTree.preOrder(); //  1,2,3,5,4
        // binaryTree.deleteNode(5);
        binaryTree.deleteNode(3);
        System.out.println("删除后，前序遍历");
        binaryTree.preOrder(); // 1,2,3,4
    }
}

class BinaryTree {
    /**
     * 根结点*/
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
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
    public HeroNode preOrderSearch(int no) {
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
    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }

    /**
     * 删除结点*
     * @param no 待删除结点编号
     */
    public void deleteNode(int no) {
        if (root != null) {
            if (root.getNo() == no) {
                root = null;
            } else {
                root.deleteNode(no);
            }
        } else {
            System.out.println("空树，不能删除");
        }
    }
}

/**
 * 英雄结点实现类
 */
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
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

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override public String toString() {
        return "HeroNode{" + "no=" + no + ", name='" + name + '\'' + '}';
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
    public HeroNode preOrderSearch(int no) {
        System.out.println("进入前序遍历查找");

        // 比较当前结点是不是
        if (this.no == no) {
            return this;
        }

        // 1.则判断当前结点的左子结点是否为空，如果不为空，则递归前序查找
        // 2.如果左递归前序查找，找到结点，则返回
        HeroNode resultNode = null;
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
    public HeroNode infixOrderSearch(int no) {
        HeroNode resultNode = null;

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
    public HeroNode postOrderSearch(int no) {
        HeroNode resultNode = null;

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
}