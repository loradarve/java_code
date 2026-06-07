# Python + MySQL + Linux + Docker

## 一、MySQL软件发展
1. 1995年，企业AB：MySQL发布
2. 2008年，SUN 公司收购了MySQL AB
3. 2010年，Oracle 收购了SUN
4. MySQL是目前最流行的关系型数据库管理系统(RDBMS)[Relational Database Management System]

## 二、SQL语句详解
*概述*：SQL(Structured Query Language)[结构化查询语言]，主要是实现 用户(程序员) 和数据库 之间交互使用

*分类*：
1. DDL(Data Definition Language)[数据定义语言]：主要是操作 数据库，数据表，字段，进行*增删改查*(CURD)
   - 涉及到的关键字：*create*,*alter*,*drop*,*show*
2. DML(Data Manipulation Language)[数据操作语言]：主要是操作 表数据，进行*增删改*(CDU) ->统称为*更新语句*
   - 涉及到的关键字：*insert*,*update*,*delete*
3. DQL(Data Query Language)[数据查询语言]：主要是操作 表数据，进行*查询操作(R)*
   - 涉及到的关键字：*select*,*from*,*where*
4. DCL(Data Control Language)[数据控制语言]：主要是创建用户，设置权限，隔离级别等。

通用语法：
1. SQL语句可以写一行，也可以多行，最后用一个分号结束
2. SQL语句不区分大小写，方便阅读起见，统一使用小写

## 三、SQL语句基础示例
<details>
    <summary>SQL语句基础示例</summary>

   ```sql
-- ===================== MySQL数据库常用命令合集 =====================
-- 1.登录MySQL
mysql -u root -p
-- 2.查看所有数据库
show databases;
-- 3.创建数据库
create database db_test;
-- 4.创建数据库并指定编码
create database db_test default character set utf8mb4;
-- 5.使用指定数据库
use db_test;
-- 6.查看当前所在数据库
select database();
-- 7.删除数据库
drop database db_test;

-- ===================== 数据表操作命令 =====================
-- 1.查看当前库所有数据表
show tables;
-- 2.创建数据表
create table user(
id int primary key auto_increment,
username varchar(20) not null,
password varchar(30) not null,
age int default 0,
create_time datetime default now()
)engine=InnoDB default charset=utf8mb4;
-- 3.查看表结构
desc user;
-- 4.查看建表语句
show create table user;
-- 5.修改表名
alter table user rename to users;
-- 6.删除数据表
drop table users;

-- ===================== 表字段修改命令 =====================
-- 1.添加字段
alter table users add email varchar(50);
-- 2.修改字段类型
alter table users modify email varchar(60);
-- 3.修改字段名称+类型
alter table users change email tel varchar(20);
-- 4.删除字段
alter table users drop tel;

-- ===================== 数据增删改查基础 =====================
-- 1.添加数据
insert into users(username,password,age) values('zhangsan','123456',20);
-- 2.查询全部数据
select * from users;
-- 3.条件查询
select * from users where id=1;
-- 4.修改数据
update users set age=22 where id=1;
-- 5.删除单条数据
delete from users where id=1;
-- 6.清空整张表数据
truncate table users;

-- ===================== 其他常用命令 =====================
-- 退出数据库
exit;
quit;
-- 刷新权限
flush privileges;

-- 查看表内容
select * from books;
   ```

</details>

1. 主键：自己表里人的身份证号，独一无二，确定你是谁
2. 外键：自己表里人的所属单位编号，指向另一张表的主键，确定归属关系

## 四、MySQL安装
1. google搜索MySQL下载地址，下载安装包，解压到指定目录[在页面最下面找到mysql comunity server, 点进去后，选择版本号，安装zip压缩包，跳过登录，直接下载，最后解压到D:\mysql\]
2. 配置环境变量[在系统变量中添加MYSQL_HOME=D:\mysql\mysql-8.4.9-winx64, Path=%MYSQL_HOME%\bin;]
3. cmd中`mysqld --initialize-insecure `初始化mysql,出现对应data文件夹即成功
4. `mysqld -install`注册服务【要以管理员身份运行cmd，否则权限不够】
5. `net start mysql`启动服务
      ```cmd
      net start mysql
      MySQL 服务正在启动 .
      MySQL 服务已经启动成功。
      ```
6. 修改密码：`mysqladmin -u root password 429537`
7. 登录：`mysql -u root -p`
8. 停止服务：`net stop mysql`

## 五、MySQL基础库信息
1. information_schema （信息库）
   - 保存所有数据库、表、字段、权限的信息
   - 你建库、建表，信息都存在这里
   - 只读，不能删、不能改
   - 理解：MySQL 的 “户口本 / 档案库”
2. mysql （核心权限库）
   - 保存用户、密码、权限
   - 你改密码、创建用户、授权都在这里
   - 最重要的系统库
   - 理解：MySQL 的 “用户账户库”
3. performance_schema （性能监控库）
   - 记录 MySQL 运行性能、SQL 执行效率
   - 用于监控、调优
   - 理解：MySQL 的 “体检报告库”
4. sys （系统诊断库）
   - 给 DBA 排查问题用的
   - 封装了性能监控数据
   - 日常开发基本不用
   - 理解：MySQL 的 “高级诊断库”
5. Schema /`ski:么/ 提要，纲要

## 六、FastAPI ORM框架
1. 建表：执行了main_create_table.py文件，生成了数据库表
```powershell
mysql> show tables;
+--------------------------+
| Tables_in_fastapi_dbtest |
+--------------------------+
| books                    |
+--------------------------+
1 row in set (0.00 sec)

mysql> desc books;
+-------------+--------------+------+-----+---------+----------------+
| Field       | Type         | Null | Key | Default | Extra          |
+-------------+--------------+------+-----+---------+----------------+
| id          | int          | NO   | PRI | NULL    | auto_increment |
| bookname    | varchar(255) | NO   |     | NULL    |                |
| author      | varchar(255) | NO   |     | NULL    |                |
| price       | float        | NO   |     | NULL    |                |
| publisher   | varchar(255) | NO   |     | NULL    |                |
| create_time | datetime     | NO   |     | NULL    |                |
| update_time | datetime     | NO   |     | NULL    |                |
+-------------+--------------+------+-----+---------+----------------+
7 rows in set (0.01 sec)
```