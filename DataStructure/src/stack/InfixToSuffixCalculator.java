package stack;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 完整逆波兰表达式（后缀表达式）计算器
 * 输入中缀表达式计算结果
 *
 * @author Neil
 * @version v2.0
 * @date 2021/2/3 22:05
 */
public class InfixToSuffixCalculator {
    public static void main(String[] args) {
        /*
         * 给定逆波兰表达式
         * (30+4)*5-6 => 30 4 + 5 * 6 - => 164
         * 4*5-8+60+8/2 => 4 5 * 8 - 60 + 8 2 / + => 76
         * 测试
         * 为了方便，逆波兰表达式数字和符号用空格隔开
         * */
        // String reversePolishNotation = "30 4 + 5 * 6 -";
        // String reversePolishNotation = "4 5 * 8 - 60 + 8 2 / +";

        /*
        * 将一个中缀表达式转换成后缀表达式
        * 说明
        * 1.1+((2+3)*4)-5 => 1 2 3 + 4 * + 5 -
        * 2.直接操作string不方便,转为对应的list
        * 3.将中缀表达式对应的list=>后缀表达式list
        * */
        // String infixExpression = "1+((2+3)*4)-5";
        String infixExpression = "(((4*5)-8)+60)+(8/2)";
        List<String> infixExpressionList = toInfixExpressionList(infixExpression);
        System.out.println("中缀表达式对应的 List = " + infixExpressionList);
        List<String> suffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println("后缀表达式对应的 List = " + suffixExpressionList);
        System.out.printf("expression = %d", calculate(suffixExpressionList));

        /*
         * 计算逆波兰表达式思路
         * 1.先将逆波兰表达式放到ArrayList中
         * 2.将ArrayList传给一个方法，遍历ArrayList配合栈完成计算
         * */
        /*
        List<String> list = getListString(reversePolishNotation);
        System.out.println("reversePolishNotation list = " + list);
        int result = calculate(list);
        System.out.println("计算的结果 = " + result);
        */



    }

    /**
     * 将中缀表达式列表转换为后缀表达式列表
     *
     * @param infixExpressionList  中缀表达式列表
     * @return 后缀表达式列表
     */
    private static List<String> parseSuffixExpressionList(List<String> infixExpressionList) {
        // 定义符号栈
        Stack<String> stack = new Stack<>();
        // 存储中间结果的栈，因为不用pop且要逆序输出，用list更方便
        List<String> list = new ArrayList<>();

        for (String item : infixExpressionList) {
            if (item.matches("\\d+")) {
                list.add(item);
            } else if ("(".equals(item)) {
                stack.push(item);
            } else if (")".equals(item)) {
                while (!"(".equals(stack.peek())) {
                    list.add(stack.pop());
                }
                stack.pop();
            } else {
                // 当item优先级小于等于stack栈顶运算符，stack栈顶弹出并加入list，循环比较
                while(stack.size() != 0 && Operation.getValue(stack.peek()) >= Operation.getValue(item)) {
                    list.add(stack.pop());
                }
                stack.push(item);
            }
        }

        // stack 中剩余运算符依次弹出加入 list
        while (stack.size() !=0) {
            list.add(stack.pop());
        }

        return list;
    }

    /**
     * 将中缀表达式转换为列表形式
     *
     * @param infixExpression 中缀表达式
     * @return 中缀表达式列表
     */
    private static List<String> toInfixExpressionList(String infixExpression) {
        List<String> list = new ArrayList<>();
        // 指针，用于遍历中缀表达式字符串
        int i = 0;
        // 用于多位数拼接
        StringBuilder str = new StringBuilder();
        char expressionChar;

        do {
            if ((expressionChar = infixExpression.charAt(i)) < 48 || (expressionChar = infixExpression.charAt(i)) > 57) {
                list.add("" + expressionChar);
                i++;
            } else {
                str.delete(0, str.length());
                while (i < infixExpression.length() && infixExpression.charAt(i) >= 48 && (expressionChar = infixExpression.charAt(i)) <= 57) {
                    str.append(expressionChar);
                    i++;
                }
                list.add(str.toString());
            }
        } while (i < infixExpression.length());

        return list;
    }

    // /**
    //  * 将一个逆波兰表达式放入ArrayList中
    //  *
    //  * @param reversePolishNotation 逆波兰表达式
    //  * @return 存储逆波兰表达式的列表
    //  */
    // private static List<String> getListString(String reversePolishNotation) {
    //     // 分割逆波兰表达式
    //     String[] split = reversePolishNotation.split(" ");
    //     return new ArrayList<String>(Arrays.asList(split));
    // }

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

/**
 * 可以返回运算符优先级的实现类
 */
class Operation {
    private static final int ADD = 1;
    private static final int SUB = 1;
    private static final int MUL = 2;
    private static final int DIV = 2;

    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                break;
        }
        return result;
    }
}
