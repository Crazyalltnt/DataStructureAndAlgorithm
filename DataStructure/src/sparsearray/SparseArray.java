package sparsearray;

/**
 * 稀疏矩阵实现棋盘
 *
 * @author Neil
 * @version v1.0
 * @date 2021/1/31 20:16
 */
public class SparseArray {
    public static void main(String[] args) {
        // 创建一个原始的二维数组 11 * 11
        // 0 表示没有棋子，1 表示黑子，2 表示蓝子
        int[][] chessArray = new int[11][11];
        chessArray[1][2] = 1;
        chessArray[2][3] = 2;
        // 输出原始的二维数组
        System.out.println("原始二维数组");
        for (int[] row : chessArray) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        // 将二维数组转为稀疏数组
        // 1. 遍历二维数组，得到非零数据个数
        int sum = 0;
        for (int[] row : chessArray) {
            for (int data : row) {
                if (data != 0) {
                    sum++;
                }
            }
        }
        System.out.println("sum = " + sum);
        
        // 2. 创建对应的稀疏矩阵
        int[][] sparseArray = new int[sum + 1][3];
        // 稀疏矩阵赋值
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;
        // 遍历二维数组，将非零值存入
        // 用于记录第几个非零数
        int count = 0;
        for (int i = 0; i < chessArray.length; i++) {
            for (int j = 0; j < chessArray[i].length; j++) {
                if (chessArray[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessArray[i][j];
                }
            }
        }

        // 输出稀疏数组
        System.out.println("得到的稀疏数组为：");
        for (int[] row : sparseArray) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        // 将稀疏数组恢复成原始二维数组
        int[][] chessArray2 = new int[sparseArray[0][0]][sparseArray[0][1]];
        for (int i = 1; i < sparseArray.length; i++) {
            chessArray2[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }

        // 输出恢复的二维数组
        System.out.println("恢复的二维数组");
        for (int[] row : chessArray2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
}
