import java.util.Scanner; 

public class java_003_array_base_knowleages {
    public static void main(String[] args) {
        /*
            数组的动态初始化
                需求：键盘录入5个整数，存入数组当中，并进行遍历
            动态初始化的格式
                数据类型[] 数组名 = new 数据类型[数组的长度];
        */

        // 键盘录入5个整数，存入数组当中，并进行遍历
        // 1. 创建数组
        int[] arr = new int[5];

        // 2. 键盘录入数据
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < arr.length; i++) {
            System.out.println("请输入第" + (i + 1) + "个数据:");
            int num = sc.nextInt();
            // 把接收到的数值赋给数组
            arr[i] = num;
        }

        // 3. 遍历数组
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i != arr.length - 1) {
                System.out.print(", ");
            }
        }    
        
        System.out.println(); // 最后换行
        sc.close();
    }
}
