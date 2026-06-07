# Postman
## 一、基本软件界面内容介绍
1. 请求头：**headers**
    - 作用：修饰正文body
    - 常见用途：表示发送的数据格式、期望返回的数据格式、传递认证令牌
2.  正文：请求体**body**
    - 作用：发送数据正文
3.  请求前脚本：**pre-request script**
    - 作用：在发送请求之前执行脚本
    - 常见用途：准备数据、动态设置变量
4. 测试脚本：**test script**
    - 作用：在接收到响应之后执行脚本
    - 常见用途：验证响应数据、检查响应状态码
5. 设置：**settings**
    - 作用：设置Postman运行环境、代理、SSL验证等
6. postman软件特点：界面美观、满足HTTP接口测试需求、支持代码
            但扩展性较差
7. 接口测试工具
- 接口功能测试（Postman, Jmeter, Charles, Fiddler）
- 接口自动化测试（Python+requests, SoapUI, RobotFramework, Jmeter, Postman）
- 接口性能测试（Jmeter）

+ Jmeter 工具的优点：能做接口、接口自动化、接口性能测试，缺点测试用例和测试结果的管理不方便。
+ Fiddler 工具：能够抓包，并且能够模拟 HTTP 请求，可以网络限速，进行弱网测试。（App 测试会有课程和实例）
+ Postman 工具：界面比较好看，管理用例和管理运行结果比较方便。
工作时首先应该要接口文档，如果没有就需要通过抓包自行整理接口文档
<details>
    <summary>点击展开查看分装好的测试接口信息</summary>

    ```yaml
    name: 访问百度
    steps:
        - request:
            method: GET
            url: https://www.baidu.com
            params: [value1, value2]
            data: [value3, value4]
        - response: 
            status_code: 200
            headers: content-type: text/html
            text: "**baidu**"
        - extract: # 提取数据
            code:[status_code, (.*)]
        - validate: # 验证数据
            code: 200 
 
    ```

</details>

## 二、Postman 测试步骤
1. 在Collections下创建项目，项目下创建接口，接口下创建用例 
2. ... 新建请求(Add Request)
3. 填入信息后：冒烟测试 Send
    排列组合，把正确和各种可能的数据出错和为空的情况都测试一遍，与预期结果对比，形成测试报告
4. 添加断言辅助测试
    在tests中写断言代码，断言代码从postman右侧选择断言模板
5. 接口关联：某个接口产生的Token，需要关联到下一个接口的请求头中
6. 批量运行用例：点击|>三角形按钮，集体运行创建的所有接口
7. 还是三角形按钮，点击后可以在data上添加测试用例，比如一个test.csv文件，里面有10条测试用例，(账号密码均正确，分别缺失和错误等)，添加后迭代次数iterations: 10，观察结果

## 三、添加断言
<details>
    <summary>点击展开查看详细内容</summary>

断言是程序进行调试时，用于判断脚本是否运行成功的依据
有断言，脚本的结果就是 pass or fail
没有断言，脚本就没有结果
(1) Postman 断言方式一：Postman 自带断言，点击即可自动生成断言脚本
- 检查状态码为 200：Status code:Code is 200
- 检查响应中包含字符串：Response body:Contains string
- 检查 JSON 中某个字段的值：Response body:JSON value check
- 检查响应 body 等于指定字符串：Response body:is equal to a string
- 检查是否包含 Content-Type 响应头：Response headers:Content-Type header check
- 检查请求耗时时间小于 200ms：Response time is less then 200ms
了解即可：
1. POST 请求的状态响应码是否是某个值：Status code:Successful POST request
2. 检查 Code name 包含指定字符串：Status code:Code name has string（就是返回的 OK 字符串）
**接口断言方式（要求背诵）**
- 检查响应体中是否包含字符串 xxx
- 检查响应头中是否包含键值对 xxx:xxx
- 检查 JSON 格式的响应体，是否包含键值对 {xxx:xxx}
- 检查响应状态码是否为 200、302 等
- 检查响应时间是否在 200ms 以内

</details>
