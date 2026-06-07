public class java_008_two_sum_search {
    public static void main(String[] args) {
        /*
            需求：给定一个整数数组和一个整数目标值target，返回两个数在数组中的索引，使得两个数之和等于目标值
            要求：只要输出第一队满足要求的情况
            举例：
                输入：nums = [2,7,11,15], target = 9    输出：[0,1]
                输入：nums = [3,2,4], target = 6    输出：[1,2]

            思路：通过布尔值确定是否找到第一组符合条件的数对，两层循环遍历
        */

        // 1. 定义数组和目标值
        int[] nums = {2, 4, 5, 7, 11, 15};
        int target = 9;

        // 2. 两层循环查找符合求和多余目标值的数对
        // 设置布尔值控制退出循环条件
        boolean flag = false;
        for (int i=0; i<nums.length; i++) {
            for (int j=i+1; j<nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    System.out.println("找到的第一对满足两数之和等于目标值：{target} 的索引为" + i + ", " + j);
                    flag = true;
                    break; // 退出内层循环
                }
            }
            if (flag) {
                break; // 退出外层循环
            }
        }
        if (!flag) {
            System.out.println("没有找到满足两数之和等于目标值的数对");
        }
    }
}

