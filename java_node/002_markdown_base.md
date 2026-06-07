# 关于Markdown语法的笔记(一级标题)：
1. [文本与标题](#一文本与标题二级标题)
2. [列表](#二列表)
3. [代码块](#三代码块)
4. [引用文本](#四引用文本)
5. [超链接](#五超链接)
6. [插入图片](#六插入图片)
7. [插入表格](#七插入表格)
8. [html扩展](#八利用html语法来实现更好的markdown效果)
9. [数学公式](#九数学公式)

## 一、文本与标题(二级标题)：：
### 换行(三级标题)：文本之间加两个空格再换行才是换行
我是第一段文本。  
我是第二段文本。
### 加粗、斜体(效果可嵌套共用)(三级标题)：
**加粗一段文本(前后加上两个星号)**  
*斜体效果*  
***同时施加加粗和斜体效果***
### 分割线(三级标题)：三个单独的星号：
***
### 划去效果(三级标题)：
~~前后各加两个上浮波浪线~~

***
## 二、列表：：
### 无序列表：前面打一个星号，空格分开
* first line
* second line
* third line
### 有序列表：前面打数字.  ；然后空格隔开
1. list_one
2. list_two
### 嵌套列表以及退出列表(两个换行):
1. 今日待办事项
    * 学习java视频
    * 学习Markdown视频
        * 学习基础语法
        * 关于列表的语法知识
2. 今日待购物品
    * 存活所需摄入的最低能量所需

退出列表后(顺便记个笔记)：打勾渲染需要插件：Markdown All in One  
然后顺便安装了一个美化插件：Markdown Preview Enhanced
### 可打勾的列表清单
* [x] 第一章第一节
* [ ] 第一章第二节

***
## 三、代码块
### 3个反引号包围的区域为代码块，且可声明对应的语言
```java
    import java.util.Scanner;
    public class CompareNumbers {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            System.out.print("请输入第一个数字：");
            int a = sc.nextInt();
            System.out.print("请输入第二个数字：");
            int b = sc.nextInt();
            int max = a > b ? a : b;
            System.out.println("较大的数是：" + max);
        }
    }
```
### 文本中可以局部显示函数格式，展示函数`equals()`，这样就变成了代码块

***
## 四、引用文本
### 左边打一个大于号即可变为引用文本块 (可随意与其它格式嵌套)
> Java 是跨平台、面向对象、编译加解释型的高级编程语言，由Sun公司推出，现在是企业开发主流语言。 
> * 跨平台一次编写，到处运行：只要有 JVM（Java 虚拟机）
> * 面向对象：封装、继承、多态，适合开发大型、复杂项目。
> * 安全稳定：有内存自动回收（GC 垃圾回收），不用手动管理内存，不容易内存泄漏。
> * 语法严谨：强制类型定义，代码规范性强，适合团队协作。

***
## 五、超链接
### 格式：[链接文本]（网址）/ 还可以以变量的形式来写(必须换行两次)
点击购买[xxxxxxxxxx][link1]，更多信息请前往[我们的官网](http://www.baidu.com)查看[^1]

[link1]: http://www.baidu.com
[^1]: 这里是脚注1，安装插件Markdown Footnote才能实现

***
## 六、插入图片
### 插入图片的格式：![图片描述]（图片地址）  
还可以嵌套跳转链接 【 ![图片描述]（图片地址）】（跳转网站链接）  

***
## 七、插入表格
### 规则：第一行是|分割的表头；第二行为分割线；后续可添加任意行内容
| a | b | c |
| - | - | - |
| c | d | 
| e | f |
* 左对齐    <span style = "color:red;"><b> :- </b></span>
* 居中      <span style = "color:green;"><b> :-: </b></span>
* 右对齐    <span style = "color:red;"><b> -: </b></span>

| 姓名                                    | 学号|成绩|
| :-------------------------------------- | -: | :-:|
| 小明                                    | 001 | A |
| <span style = "color:red;">小红</span>  | 002 | B |


*** 
## 八、利用html语法来实现更好的Markdown效果

<details>
    <summary>点击展开查看详细内容</summary>

1. 适合放冗长知识点、代码、附录，默认折叠，可点开：
    <details>
    <summary>点击展开查看详细内容</summary>

    这里放文字、表格、代码、图片都可以

    </details>
2. 漂亮的分割线
<hr style="height:3px;background-color:#ccc;border:none;">

3.  图片居中
        <div align="center">
        <img src="https://www.baidu.com/img/flexible/logo/pc/result.png" width=200px height=150px>
        </div>
4. 美化表格
        <table border="2" cellpadding="5" cellspacing="0" width="100%">
        <tr>
            <td><b>项目</b></td>
            <td><b>说明</b></td>
        </tr>
        <tr>
            <td>Java</td>
            <td>跨平台面向对象语言</td>
        </tr>
        </table>

5. 彩色文字<br>
        <span style="color:red;">红色重点文字</span><br>
        <span style="color:blue;">蓝色注释文字</span><br>
        <span style="color:green;">绿色完成标记</span><br>
6. 上下标<br>
        文本内容<sup>上标</sup> :  x<sup>2</sup> <br>
        文本内容<sub>下标</sub> :  x<sub>2</sub>

    </details>
<br>
<hr style="height:3px;background-color:#ccc;border:none;">

## 九、数学公式
### 公式测试
#### ctrl + ,打开设置，搜索markdown preview math enabled，把<span style="color:red;">"markdown.preview.math.enabled": true</span>直接复制到JSON 配置中

行内：$E=mc^2$、$\alpha \beta \gamma$、$\sum_{i=1}^n x_i$

块级：
$$
\frac{\partial f}{\partial x} = 2x + y
$$

上下标：$A^B_C$、$v_{max}$、$e^{i\pi}+1=0$
       
<details>
    <summary>点击展开查看公式字典</summary>

上下标<br>
$x^2$<br>
$x_1$<br>
$a^{m+n}$<br>
$x_{i,j}$<br>
$A^B_C$<br>
分式<br>
$\frac{1}{2}$<br>
$\dfrac{1}{2}$<br>
$\dfrac{x+y}{2a}$<br>
根号<br>
$\sqrt{x}$<br>
$\sqrt{a+b}$<br>
$\sqrt[3]{8}$<br>
$\sqrt[n]{x}$<br>
求和、连乘<br>
$\sum_{i=1}^n$<br>
$\sum_{i=1}^{\infty}$<br>
$\prod_{i=1}^n$<br>
积分<br>
$\int f(x)dx$<br>
$\int_{0}^{1} x^2 dx$<br>
$\iint$<br>
$\iiint$<br>
$\oint$<br>
极限<br>
$\lim_{x\to 0}$<br>
$\lim_{x\to\infty}$<br>
三角函数、对数<br>
$\sin x$<br>
$\cos x$<br>
$\tan x$<br>
$\sin^2 x$<br>
$\log x$<br>
$\ln x$<br>
常用符号
$\pm$<br>
$\times$<br>
$\div$<br>
$\ge$<br>
$\le$<br>
$\ne$<br>
$\infty$<br>
$\to$<br>
$\forall$<br>
$\exists$<br>
小写希腊字母<br>
$\alpha$<br>
$\beta$<br>
$\gamma$<br>
$\delta$<br>
$\epsilon$<br>
$\theta$<br>
$\lambda$<br>
$\mu$<br>
$\pi$<br>
$\sigma$<br>
$\omega$<br>
大写希腊字母<br>
$\Gamma$<br>
$\Delta$<br>
$\Theta$<br>
$\Lambda$<br>
$\Pi$<br>
$\Sigma$<br>
$\Omega$<br>

</details>
