public class java_002_printTriangle_direct_method {
    public static void main(String[] args) { 
        /*
        打印平行梯形
          ##* **** *##
          #** **** **#
          *** **** ***

          思路：1.外循环：行数，控制打印几行
               2.不考虑数学规律，分块打印局部形状
                    2.1.打印左上角# 
                    2.2.每行打印与行数相同数量的*
                    2.3.打印中间的三行四列矩形*
                    2.4.打印右边的*，每行数量与行数相同
                    2.5.结束，最右边#不打印
        */

        // 外循环：控制图形的行数
        for (int i = 1; i <=3; i++) { 
            // 2.1.打印左上角# 
            for (int j = i; j <= 2; j++) { 
                System.out.print(" ");
            }
            // 2.2.打印左三角
            for (int j = 1; j <= i; j++) { 
                System.out.print("*");
            }
            // 2.3.打印中间的三行四列矩形*
            for (int j = 1; j <= 4; j++) { 
                System.out.print("*");
            }
            // 2.4.打印右边的*，每行数量与行数相同
            for (int j = 1; j <= i; j++) { 
                System.out.print("*");
            }
            // 2.5.结束，最右边#不打印
            System.out.println();
        }
    }
}
