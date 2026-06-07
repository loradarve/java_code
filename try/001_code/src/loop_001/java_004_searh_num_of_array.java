import java.util.Scanner;

public class java_004_searh_num_of_array {
    public static void main(String[] args) {
        /* 
            需求：键盘录入一个整数，判断该整数是否在数组当中
            输出要求：
                如果查找数字存在，打印索引
                如果数组中要查找的数字出现多次，只要返回第一次出现的索引即可
                如果查找数字不存在，提示：“该数据不存在”
            
            细节1：判断数字在数组中不存在的条件是：遍历数组都没有找到，在循环外给出提示(满足条件时)
            细节2：判断遍历数组都找不到数字的方法有：flag(标记)、count(统计)
        */

        // 1. 定义数组
        int[] arr = { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 };

        // 2. 键盘录入数据
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要查找的整数:");
        int number = sc.nextInt();

        // 3. 遍历数组
        boolean flag = false; // 创建一个标记，默认为false
        for (int i = 0; i < arr.length; i++) {
            if (number == arr[i]) {
                System.out.println("该数据在数组中的索引为：" + i);
                flag = true;
                break;
            }
        }

        if (!flag) { // 使用逻辑非，确保if中括号中不为空
            System.out.println("该数据不存在");
        }

        sc.close();
    }
}
