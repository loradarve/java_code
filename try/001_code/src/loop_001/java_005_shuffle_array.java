import java.util.Random;

public class java_005_shuffle_array {
    public static void main(String[] args) { 
        /*
            需求：已知数组元素为1-10，打乱顺序打印出来

            思路： 1. 0索引的数据，与随机位置的数据交换位置
                  2. 遍历重复上面的过程

            细节1： 如何交换两个位置上的数据 temp(临时存储)
            细节2： 如何获取随机数 Random
        */

        // 1. 定义数组
        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        // 创建Random对象
        Random r = new Random();

        // 2. 遍历数组
        for (int i = 0; i < arr.length; i++) { 

            // 获取随机数
            int randomIndex = r.nextInt(arr.length);
            
            // 用临时存储temp，实现数据随机交换位置
            int temp = arr[i];
            arr[i] = arr[randomIndex];
            arr[randomIndex] = temp;
        }

        // 3. 遍历数组在同一行输出结果
        for (int i = 0; i < arr.length; i++) { 
            System.out.print(arr[i]);
            if (i != arr.length - 1) {
                System.out.print(", ");
            }
        }
    }
}
