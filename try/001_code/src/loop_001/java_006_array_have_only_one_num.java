import java.util.Random;

public class java_006_array_have_only_one_num {
    public static void main(String[] args) { 
        /*
            需求：获取10个1-100之间的随机数并存入到数组之中，要求保证数组中的每一个数字都是唯一的
            思路：遍历已经填入的随机数，如果存在就继续生成下一个随机数，不存在就存入当前索引位置
            细节：循环的i++条件应该放在判断遍历数据中不存在生成的随机数这里，否则会出现跳过索引，数组中默认填0的bug
        */

        // 1. 创建数组
        int[] arr = new int[10];

        // 2.生成随机数
        Random r = new Random();

        // // 3. 遍历填入不重复的随机数
        // for (int i = 0; i < arr.length;) { 
        //     int randomNum = r.nextInt(101);
        //     boolean flag = true;
        //     for (int j = 0; j < i; j++) { 
        //         if (arr[j] == randomNum) {
        //             flag = false;
        //             break;
        //         }
        //     }
        //     if (flag) {
        //         arr[i] = randomNum; 
        //         // 只有在生成的随机数不重复时才将i++，否则会出现跳过索引，数组中默认填0的bug
        //         i++;
        //     }
        // }

        // (3).替换3的功能，使用方法完成数组遍历保证生成的数字不重复，注意替换时将原本的3全部注释掉，然后把(3)和main以外的方法解开注释
        // 该方法实际替代了3中的内循环功能
        for(int i = 0; i < arr.length;) {    
            int randomNum = r.nextInt(101);
            boolean flag = contains(arr, randomNum);
            if (!flag) {
                arr[i] = randomNum;
                i++;
            }
        }
        // 4. 遍历输出在同一行, 这里也可以封装为方法
        for (int i = 0; i < arr.length; i++) { 
            System.out.print(arr[i]);
            if (i != arr.length - 1) {
                System.out.print(", ");
            }
        }
    }
    // 在main之外定义一个方法
    // 定义一个方法，判断随机数randomNum是否在数组arr中
    public static boolean contains(int[] arr, int randomNum) { 
        for (int i = 0; i < arr.length; i++) { 
            if (arr[i] == randomNum) {
                // 如果遇到一个符合条件的数，则返回true
                return true;
            }
        }
        // 循环结束，没有找到符合条件的数，则返回false
        return false;
    }
}
