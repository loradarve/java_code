public class java_007_slow_and_fast_hands {
    /*
        快慢指针:
            慢指针：存入数组的位置
            快指针：找不重复的值
            相等：舍弃快指针位置的数据
            不相等：把快指针位置的数据存入慢指针位置
    */
    public static void main(String[] args) { 
        // 给定一个有序递增数组，要求去除重复元素并打印
        int[] arr = { 1, 1, 2, 3, 3, 5, 5, 7, 7, 8, 9, 9, 10, 10, 11, 11, 12};

        // 1. 定义快慢指针
        int slow = 0; // 初始位置指向0
        int fast = 1;

        // 2. 利用循环不断地移动快慢指针，找不重复的元素
        while (fast < arr.length) { 
            // 判断快慢指针位置上的数据是否相等
            if (arr[slow] != arr[fast]) { 
                // 不相等，把fast位置的数据存入slow位置
                slow++; // 慢指针位置后移
                arr[slow] = arr[fast]; // 赋值
            }
            // 没有触发慢指针赋值操作时, 移动快指针
            fast++;
        }

        // 3. 输出结果
        for (int i = 0; i <= slow; i++) { 
            System.out.print(arr[i] + " ");
        }
    }
}
