public class java_001_printTriangle_logic_method {
    public static void main(String[] args) {
        /*
        打印平行梯形
            ******
           ********
          **********
        编写代码时先用#替代空格，方便只管查看，最后再替换回空格

        思路：1.外循环：行数，控制打印几行
             2.内循环：打印平行四边形以外的空位，控制打印几个#
             3.内循环：打印图形
        */

        // 外循环：控制图形的行数
        for (int i = 1; i <=3; i++) {
            // 内循环：控制打印# 
            for (int j = i; j <= 2; j++) {
                System.out.print(" ");
            }
            // 内循环：打印图形
            for (int j = 1; j <=4 + 2*i; j++) {
                System.out.print("*");
            }
            // 换行
            System.out.println();
        }
    }
}
