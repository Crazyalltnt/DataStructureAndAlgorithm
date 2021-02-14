package hashtable;

import java.util.Scanner;

/**
 * 哈希表
 *
 * @author Neil
 * @version v1.0
 * @date 2021/2/10 15:24
 */
public class HashTableDemo {
    public static void main(String[] args) {
        // 创建哈希表
        HashTable hashTable = new HashTable(7);

        // 菜单
        String key;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add：添加雇员");
            System.out.println("list：显示雇员");
            System.out.println("find：查找雇员");
            System.out.println("exit：退出系统");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    // 创建雇员
                    Employee employee = new Employee(id, name);
                    hashTable.add(employee);
                    break;
                case "list":
                    hashTable.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTable.findEmployeeById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

/**
 * 创建 HashTable 管理多条链表
 */
class HashTable {
    /**
     * 链表数组
     */
    private final EmployeeLinkedList[] employeeLinkedLists;
    /**
     * 链表数量
     */
    private final int size;

    public HashTable(int size) {
        this.size = size;
        employeeLinkedLists = new EmployeeLinkedList[size];
        for (int i = 0; i < size; i++) {
            employeeLinkedLists[i] = new EmployeeLinkedList();
        }
    }

    /**
     * 添加雇员
     *
     * @param employee 待添加雇员
     */
    public void add(Employee employee) {
        // 根据员工id，得到该员工应当添加到哪条链表
        int employeeLinkedListNo = hashFunction(employee.id);
        // 将 employee 添加到对应链表中
        employeeLinkedLists[employeeLinkedListNo].add(employee);
    }

    /**
     * 变量哈希表
     */
    public void list() {
        for (int i = 0; i < size; i++) {
            employeeLinkedLists[i].list(i);
        }
    }

    public void findEmployeeById(int id) {
        // 使用散列函数确定到哪条链表查找
        int employeeLinkedListNo = hashFunction(id);
        Employee employee = employeeLinkedLists[employeeLinkedListNo].findEmployeeById(id);
        if (employee != null) {
            System.out.printf("在第%d条链表中找到雇员 id = %d\n", (employeeLinkedListNo + 1), id);
        } else {
            System.out.println("在哈希表中，没有找到该雇员~");
        }
    }

    /**
     * 编写散列函数，使用一个简单取模法
     *
     * @param id 员工id
     * @return 存放位置
     */
    private int hashFunction(int id) {
        return id % size;
    }
}

/**
 * 雇员实现类
 */
class Employee {
    public int id;
    public String name;
    /**
     * 默认为 null
     */
    public Employee next;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

/**
 * 雇员链表实现类
 */
class EmployeeLinkedList {
    /**
     * 头指针，指向第一个Employee
     */
    private Employee head;

    /**
     * 添加雇员到链表
     *
     * @param employee 雇员
     */
    public void add(Employee employee) {
        // 如果是添加第一个雇员
        if (head == null) {
            head = employee;
            return;
        }

        // 如果不是第一个雇员，则使用一个辅助的指针，帮助定位到最后
        Employee currentEmployee = head;
        while (currentEmployee.next != null) {
            currentEmployee = currentEmployee.next;
        }
        currentEmployee.next = employee;
    }

    /**
     * 遍历雇员的信息
     *
     * @param no 哈希表链表编号
     */
    public void list(int no) {
        if (head == null) {
            System.out.println("第 " + (no + 1) + " 链表为空");
            return;
        }
        System.out.print("第 " + (no + 1) + " 链表的信息为 ");
        Employee currentEmployee = head;
        while (true) {
            System.out.printf("=> id = %d name = %s\t", currentEmployee.id, currentEmployee.name);
            if (currentEmployee.next == null) {
                break;
            }
            currentEmployee = currentEmployee.next;
        }
        System.out.println();
    }

    /**
     * 根据id查找雇员
     *
     * @param id 雇员id
     * @return 待查找的雇员
     */
    public Employee findEmployeeById(int id) {
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        Employee currentEmployee = head;
        while (currentEmployee.id != id) {
            if (currentEmployee.next == null) {
                currentEmployee = null;
                break;
            }
            currentEmployee = currentEmployee.next;
        }
        return currentEmployee;
    }
}