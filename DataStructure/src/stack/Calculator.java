package stack;

/**
 * 栈实现计算器
 * 使用栈完成表达式的计算思路
 * 1.通过一个index值（索引）,来遍历我们的表达式
 * 2.如果我们发现是一个数字,就直接入数栈
 * 3.如果发现扫描到是一个符号,就分如下情况
 *  3.1如果发现当前的符号栈为空,就直接入栈
 *  3.2如果符号栈有操作符,就进行比较,如果当前的操作符的优先级小于或者等于的操作符,就需要从数栈中pop出两个数,再从符号栈中pop出一个符号，
 *     进行运算，将得到的结果入数栈，然后将当前的操作符入符号栈，如果当前的操作符的优先级大于栈中的操作符,就直接入符号栈
 * 4.当表达式扫描完毕,就顺序的从数栈和符号栈中pop出相应的数和符号,并运行.
 * 5.最后在数栈只有一个数字,就是表达式的结果
 *
 * @author Neil
 * @version v1.0
 * @date 2021/2/3 16:08
 */
public class Calculator {
    public static void main(String[] args) {
        // 创建表达式
        String expression = "7*2*2-5+1-5+3-4";
        // 创建两个栈，数栈和符号栈
        CalculatorArrayStack numberStack = new CalculatorArrayStack(10);
        CalculatorArrayStack operatorStack = new CalculatorArrayStack(10);
        // 定义相关变量
        int index = 0;
        int number1;
        int number2;
        int operator;
        int result;
        // 保存每次扫描得到的字符
        char expressionChar;
        // 用于拼接多位数
        StringBuilder keepNumber = new StringBuilder();

        // 开始扫描表达式
        do {
            // 依次得到expression的每一个字符
            expressionChar = expression.substring(index, index + 1).charAt(0);
            if (operatorStack.isOperator(expressionChar)) {
                if (!operatorStack.isEmpty()) {
                    /*
                     * 符号栈有操作符，和当前比较
                     * 当前操作符优先级小于或等于栈中操作符，就从数栈pop两个数
                     * 从符号栈pop一个数进行运算，得到的结果入数栈
                     * */
                    if (operatorStack.priority(expressionChar) <= operatorStack.priority(operatorStack.peek())) {
                        number1 = numberStack.pop();
                        number2 = numberStack.pop();
                        operator = operatorStack.pop();
                        result = numberStack.cal(number1, number2, operator);
                        // 运算结果入数栈
                        numberStack.push(result);
                    }
                }
                operatorStack.push(expressionChar);
            } else {
                /*
                 * 如果是数，直接入数栈
                 * 1.当处理多位数时，不能发现是一个数就立即入栈，因为可能是多位数
                 * 2.在处理数时需要向expression表达式的index后再看一位,如果是数就进行扫描，如果是符号才入栈
                 * 3.因此我们需要定义一个变量字符串，用于拼接处理多位数
                 * */
                keepNumber.append(expressionChar);

                // 如果expressionChar是expression的最后一位，就直接入栈
                if (index == expression.length() - 1) {
                    numberStack.push(Integer.parseInt(keepNumber.toString()));
                } else {
                    if (operatorStack.isOperator(expression.substring(index + 1, index + 2).charAt(0))) {
                        numberStack.push(Integer.parseInt(keepNumber.toString()));
                        keepNumber.delete(0, keepNumber.length());
                    }
                }
            }
            index++;
        } while (index < expression.length());
        // 当表达式扫描完，就顺序出栈数字和符号并运算
        while (!operatorStack.isEmpty()) {
            number1 = numberStack.pop();
            number2 = numberStack.pop();
            operator = operatorStack.pop();
            result = numberStack.cal(number1, number2, operator);
            numberStack.push(result);
        }
        // 将数栈的最后pop出，就是结果
        result = numberStack.pop();
        System.out.printf("表达式 %s = %d", expression, result);
    }
}

class CalculatorArrayStack {
    /**
     * 加减乘除常量
     */
    public final char ADD = '+';
    public final char SUBTRACT = '-';
    public final char MULTIPLY = '*';
    public final char DIVIDE = '/';

    /**
     * 栈的大小
     */
    private final int maxSize;
    /**
     * 数组模拟栈，数据存放在该数组中
     */
    private final int[] stack;
    /**
     * top表示栈顶，初始化为-1
     */
    private int top = -1;

    /**
     * 构造器
     * @param maxSize 栈的大小
     */
    public CalculatorArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    /**
     * 返回栈顶的值但不pop
     * @return 栈顶的值
     */
    public int peek() {
        return stack[top];
    }

    /**
     * 判断栈是否满
     * @return 栈是否满
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 判断栈是否空
     * @return 栈是否空
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 入栈-push
     * @param value 待入栈数据
     */
    public void push(int value) {
        // 判断栈是否满
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    /**
     * 出栈-pop
     * @return 出栈的数据
     */
    public int pop() {
        // 判断栈是否空
        if (isEmpty()) {
            throw new RuntimeException("栈空，无数据");
        }
        int value = stack[top];
        top--;
        return value;
    }

    /**
     * 遍历显示栈
     */
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空");
            return;
        }
        // 从栈顶开始显示数据
        for (int i = top; i >=0 ; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    /**
     * 返回运算符优先级，假设目前只有加减乘除四则运算
     * @param operator 运算符
     * @return 运算符优先级
     */
    public int priority(int operator) {
        if (operator == MULTIPLY || operator == DIVIDE) {
            return 1;
        } else if (operator == ADD || operator == SUBTRACT) {
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * 判断是否运算符
     * @param val 运算符
     * @return 是否运算符
     */
    public boolean isOperator(char val) {
        return val == ADD || val == SUBTRACT || val == MULTIPLY || val == DIVIDE;
    }

    public int cal(int number1, int number2, int operator) {
        // 存放计算结果
        int result = 0;
        switch (operator) {
            case '+':
                result = number1 + number2;
                break;
            case '-':
                result = number2 - number1;
                break;
            case '*':
                result = number1 * number2;
                break;
            case '/':
                result = number2 / number1;
                break;
            default:
                break;
        }
        return result;
    }
}