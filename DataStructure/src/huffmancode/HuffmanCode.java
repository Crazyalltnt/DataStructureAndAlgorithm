package huffmancode;

import java.util.*;

/**
 * 哈夫曼编码实现数据压缩
 *
 * @author Neil
 * @version v1.0
 * @date 2021/2/16 13:21
 */
public class HuffmanCode {
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();

        // 压缩前长度为 40
        System.out.println("contentBytes = " + Arrays.toString(contentBytes));
        System.out.println("压缩前长度为 " + contentBytes.length);

        byte[] huffmanCodeBytes = huffmanZip(contentBytes);
        System.out.println("压缩后的结果是 " + Arrays.toString(huffmanCodeBytes));
        System.out.println("长度为 " + huffmanCodeBytes.length);
        System.out.println("压缩率为 " + (100 - 100 * (float)huffmanCodeBytes.length / contentBytes.length) + "%");

        // 测试解码方法
        decode(huffmanCodes, huffmanCodeBytes);

        System.out.println("byte -1 是 " + Integer.toBinaryString(-1));
        System.out.println("byte -1 是 " + Integer.toBinaryString((byte)-1));

/*        // 分步测试压缩
        List<Node> nodes = getNodes(contentBytes);
        System.out.println("nodes = " + nodes);

        // 测试创建哈夫曼树
        System.out.println("哈夫曼树");
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        System.out.println("前序遍历");
        huffmanTreeRoot.preOrder();

        // 测试是否生成了对应的哈夫曼编码
        getCodes(huffmanTreeRoot);
        System.out.println("生成的哈夫曼编码表" + huffmanCodes);

        // 测试
        byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes);
        System.out.println("huffmanCodeBytes = " + Arrays.toString(huffmanCodeBytes));*/
    }

    /**
     * 字符串转换为对应的结点集合 NodeList
     *
     * @param bytes 待传入字节数组
     * @return 结点集合 List 形式 [Node[date=97 ,weight = 5], Node[date=32,weight = 9]......]
     */
    private static List<Node> getNodes(byte[] bytes) {
        ArrayList<Node> nodes = new ArrayList<>();

        // 统计每一个byte出现的次数 -> map[key,value]
        Map<Byte, Integer> counts = new HashMap<>(16);
        for (byte b : bytes) {
            counts.merge(b, 1, Integer::sum);
        }

        // 把每个键值对转成一个Node对象并加入nodes集合
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    /**
     * 创建哈夫曼树实现方法
     *
     * @param nodes 需要创建成哈夫曼树的结点集合
     * @return 创建号后的哈夫曼树的根结点
     */
    private static Node createHuffmanTree(List<Node> nodes) {

        while (nodes.size() > 1) {
            // 从小到大排序
            Collections.sort(nodes);

            // 取出根结点权值最小的两棵二叉树
            // 1. 取出权值最小的结点
            Node leftNode = nodes.get(0);
            // 2. 取出权值第二小的结点
            Node rightNode = nodes.get(1);
            // 3. 构建一棵新的二叉树
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
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
     * @param root 树的根结点
     */
    private static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("哈夫曼树为空");
        }
    }

    /**
     * 生成赫夫曼树对应的赫夫曼编码
     * 思路:
     * 1.将赫夫曼编码表存放为 Map<\Byte,String> 形式
     * 生成的赫夫曼编码表
     * {32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010,
     * 107=1111, 108=000, 111=0011}
     */
    static Map<Byte, String> huffmanCodes = new HashMap<>();

    /**
     * 获取哈夫曼树对应的哈夫曼编码并保存到编码集合
     *
     * @param root 哈夫曼树根结点
     */
    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }

        // 在生成赫夫曼编码表时，需要去拼接路径, 定义一个 StringBuilder 存储某个叶子结点的路径
        StringBuilder stringBuilder = new StringBuilder();

        // 处理root的左子树
        getCodes(root.left, "0", stringBuilder);
        // 处理root的右子树
        getCodes(root.right, "1", stringBuilder);

        return huffmanCodes;
    }

    /**
     * 获取传入结点的叶子节点对应的哈夫曼编码
     *
     * @param node          传入结点
     * @param code          路径，左子结点是0，右子结点是1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder tempStringBuilder = new StringBuilder(stringBuilder);
        // 将code加入到tempStringBuilder
        tempStringBuilder.append(code);

        if (node != null) {
            // 判断当前node是叶子结点还是非叶子结点
            if (node.data == null) {
                // 非叶子结点，递归处理
                // 向左递归
                getCodes(node.left, "0", tempStringBuilder);
                // 向右递归
                getCodes(node.right, "1", tempStringBuilder);
            } else {
                // 说明是一个叶子结点
                huffmanCodes.put(node.data, tempStringBuilder.toString());
            }
        }
    }

    /**
     * 将字符串对应的byte[]数组。通过生成的哈夫曼编码表，返回一个哈夫曼编码压缩后的byte[]
     * 举例:
     * String content = "i like like like java do you like a java";
     * =》 byte[] contentBytes = content.getBytes();
     * 哈夫曼编码处理后返回的是字符串
     * "1010100010111111110010001011111111001000101111111100100101001101110001110000011011
     * 101000111100101000101111111100110001001010011011100"
     * => 对应的 byte[] huffmanCodeBytes，即 8位对应一个 byte,放入到 huffmanCodeBytes
     * 以 huffmanCodeBytes[0] 为例
     * 00000000000000000000000010101000（168,32位）-补码-> 00000000000000000000000010101000（正数补码和反码一样）
     * -（转byte截断8位，最高位符号位）->10101000（反码形式） -原码-> 11011000 = -88
     * 所以 huffmanCodeBytes[0] = -88
     *
     * @param contentBytes        原始字符串对应的 byte[] 数组
     * @param huffmanCodes 生成的哈夫曼编码表
     * @return 哈夫曼编码处理后的 byte[]
     */
    private static byte[] zip(byte[] contentBytes, Map<Byte, String> huffmanCodes) {
        // 利用huffmanCodes将bytes转成哈夫曼编码对应的二进制字符串
        StringBuilder stringBuilder = new StringBuilder();
        int lengthOfByte = 8;

        // 遍历 bytes 数组
        for (byte b : contentBytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }

        // 测试
        // System.out.println("stringBuilder = " + stringBuilder);

        // 将stringBuilder转为 byte数组
        // 计算返回的byte数组huffmanCodeBytes的长度
        int len;
        if (stringBuilder.length() % lengthOfByte == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / lengthOfByte + 1;
        }
        // 创建存储压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += lengthOfByte) {
            String byteString;
            if (i + lengthOfByte > stringBuilder.length()) {
                byteString = stringBuilder.substring(i);
            } else {
                byteString = stringBuilder.substring(i, i + lengthOfByte);
            }
            System.out.println("byteString = " + byteString);
            huffmanCodeBytes[index] = (byte)Integer.parseInt(byteString, 2);
            index++;
        }
        return huffmanCodeBytes;
    }

    /**
     * 将哈夫曼编码压缩流程封装为压缩函数
     *
     * @param bytes 原始字符串对应的字节数组
     * @return 经过哈夫曼编码压缩后的字节数组
     */
    private static byte[] huffmanZip(byte[] bytes) {
        // 将内容字节数组转为Node集合
        List<Node> nodes = getNodes(bytes);
        // 创建huffman树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        // 生成对应的huffman编码表
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        // 根据编码表压缩字节数组

        return zip(bytes, huffmanCodes);
    }

    /*
     * 解压，将压缩后的字节数组还原为字符串
     * 思路
     * 1.将huffmanCodeBytes重新转成huffman编码对应的二进制字符串
     * 2.根据huffmanCodes将二进制字符串转成原来的字符串
     * */

    /**
     * 将huffmanCodeBytes重新转成huffman编码对应的二进制字符串
     *
     * @param flag 标志是否需要补高位，最后一个字节无需补高位
     * @param b    传入的byte
     * @return 该byte对应的二进制字符串（补码形式）
     */
    private static String byteToBitString(boolean flag, byte b) {
        // 使用整型变量保存b
        int temp = b;
        // 如果是正数还存在补高位
        if (flag) {
            // 按位或256，计算机内补码运算，以第一个 -88 为例
            // 00000000 00000000 00000001 00000000 | 11111111 11111111 11111111 10101000
            // => 11111111 11111111 11111111 10101000
            temp |= 256;
        }

        System.out.println("temp = " + temp);
        String bitString = Integer.toBinaryString(temp);
        System.out.println("bitString = " + bitString);

        if (flag) {
            return bitString.substring(bitString.length() - 8);
        } else {
            return bitString;
        }
    }

    /**
     * 解码
     *
     * @param huffmanCodes 哈夫曼编码表
     * @param huffmanCodeBytes 哈夫曼编码压缩得到的字节数组
     * @return 原来的字符串对应的字节数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanCodeBytes) {
        // 将huffmanCodeBytes重新转成huffman编码对应的二进制字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanCodeBytes.length; i++) {
            boolean flag = (i != huffmanCodeBytes.length - 1);
            stringBuilder.append(byteToBitString(flag, huffmanCodeBytes[i]));
        }
        System.out.println("二进制字符串：" + stringBuilder);
        return null;
    }
}

/**
 * 结点实现类
 */
class Node implements Comparable<Node> {
    /**
     * 存放数据（字符）本身，例如'a'=>97，' '=>32
     */
    Byte data;
    /**
     * 权值，表示字符出现的次数
     */
    int weight;
    /**
     * 指向左子结点
     */
    Node left;
    /**
     * 指向右子结点
     */
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
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

    @Override public int compareTo(Node o) {
        // 升序排序
        return this.weight - o.weight;
    }

    @Override public String toString() {
        return "Node{" + "data=" + data + ", weight=" + weight + '}';
    }
}
