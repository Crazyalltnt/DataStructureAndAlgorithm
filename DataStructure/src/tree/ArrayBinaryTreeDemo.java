package tree;

/**
 * 数组存储二叉树
 *
 * @author Neil
 * @version v1.0
 * @date 2021/2/13 13:55
 */
public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(array);
        arrayBinaryTree.preOrder(); // 1245367
    }
}

/**
 * 数组顺序存储二叉树实现类
 */
class ArrayBinaryTree {
    /**
     * 存储数据结点的数组
     */
    private final int[] array;

    public ArrayBinaryTree(int[] array) {
        this.array = array;
    }


    public  void preOrder() {
        this.preOrder(0);
    }

    /**
     * 顺序存储二叉树的前序遍历
     *
     * @param index 数组下标
     */
    public void preOrder(int index) {
        // 如果数组为空，或者长度为 0
        if (array == null || array.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }

        // 输出当前这个元素
        assert array != null;
        System.out.println(array[index]);

        // 向左递归遍历
        int branchOfTheTree = 2;
        if ((index * branchOfTheTree + 1) < array.length) {
            preOrder(branchOfTheTree * index + 1);
        }

        // 向右递归遍历
        if ((index * branchOfTheTree + branchOfTheTree) < array.length) {
            preOrder(branchOfTheTree * index + branchOfTheTree);
        }
    }
}