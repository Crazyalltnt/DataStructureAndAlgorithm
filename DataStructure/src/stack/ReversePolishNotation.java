package stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰表达式计算器
 * 输入逆波兰表达式计算结果
 *
 * @author Neil
 * @version v1.0
 * @date 2021/2/3 22:05
 */
public class ReversePolishNotation {
    public static void main(String[] args) {
        /*
         * 给定逆波兰表达式
         * (30+4)*5-6 => 30 4 + 5 * 6 - => 164
         * 4*5-8+60+8/2 => 4 5 * 8 - 60 + 8 2 / + => 76
         * 测试
         * 为了方便，逆波兰表达式数字和符号用空格隔开
         * */
        // String reversePolishNotation = "30 4 + 5 * 6 -";
        String reversePolishNotation = "4 5 * 8 - 60 + 8 2 / +";

        /*
         * 思路
         * 1.先将逆波兰表达式放到ArrayList中
         * 2.将ArrayList传给一个方法，遍历ArrayList配合栈完成计算
         * */
        List<String> list = getListString(reversePolishNotation);
        System.out.println("reversePolishNotation list = " + list);
        int result = calculate(list);
        System.out.println("计算的结果 = " + result);
    }

    /**
     * 将一个逆波兰表达式放入ArrayList中
     *
     * @param reversePolishNotation 逆波兰表达式
     * @return 存储逆波兰表达式的列表
     */
    private static List<String> getListString(String reversePolishNotation) {
        // 分割逆波兰表达式
        String[] split = reversePolishNotation.split(" ");
        return new ArrayList<>(Arrays.asList(split));
    }

    /**
     * 计算逆波兰表达式
     * 
     * @param list 存储逆波兰表达式的ArrayList
     * @return 计算结果
     */
    private static int calculate(List<String> list) {
        // 创建栈
        Stack<String> stack = new Stack<>();
        for (String item : list) {
            // 使用正则表达式取出数，匹配多位数
            if (item.matches("\\d+")) {
                stack.push(item);
            } else {
                int number2 = Integer.parseInt(stack.pop());
                int number1 = Integer.parseInt(stack.pop());
                int result;
                switch (item) {
                    case "+":
                        result = number1 + number2;
                        break;
                    case "-":
                        result = number1 - number2;
                        break;
                    case "*":
                        result = number1 * number2;
                        break;
                    case "/":
                        result = number1 / number2;
                        break;
                    default:
                        throw new RuntimeException("运算符有误");
                }

                // result入栈
                stack.push("" + result);
            }
        }
        // 最后留在stack中的数据是运算结果
        return Integer.parseInt(stack.pop());
    }
}
