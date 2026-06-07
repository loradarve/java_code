# LangChain学习笔记
## 1. 提示词工程
### 1.1 基本思想：
1. Zero-shot思想：训练阶段不存在与测试阶段完全相同的类别，但是模型可以使用训练过的知识来推广到测试集中的新类别上。
   对于提示词：就是要提供模型没见过的内容的描述
2. Few-shot思想：大量样本训练后的模型，通过少量样本微调，即可用于测试集。
   对于提示词：就是要提供少量示例
3. 技巧：详细的描述、设定模型角色、使用分隔符把需要区别对待的内容框起来、分步骤说明、提供例子
### 1.2 JSON格式
1. JSON 基础概念
全称：**JavaScript Object Notation**，轻量级跨程序数据交换格式，JSON 本质是带固定格式的字符串，用于不同程序之间传输数据。
三种数据格式对比（Text/CSV/JSON）
    | 格式 | 结构化特点 | 信息提取 | 额外特性 | 缺点 |
    |----|----|----|----|----|
    | 纯 Text 文本 | 非结构化 | 提取困难 | 无规范 Schema 源数据 | 数据杂乱无固定字段 |
    |CSV 逗号分隔文本 | 结构化 | 提取方便 | 无内置字段 Schema | 字段含义无标注，数据解析易出错 |
    |JSON 格式文本 | 强结构化 | 提取极方便 | 每条数据自带键名 (Schema 字段)| 每条数据重复字段名，存储空间占用更大 |
```json
{"name":"周杰伦","age":11,"gender":"男"} //JSON只认双引号
```
2. Python json 模块两大核心方法
- json.dumps(对象, ensure_ascii=False)
**功能**：Python字典/列表 → JSON字符串
**关键参数**：ensure_ascii=False：关闭 ASCII 转义，保证中文正常原样显示（不加中文会变成\uXXXX编码）
**返回值**：标准 JSON 字符串
- json.loads(json字符串)
**功能**：JSON字符串 → Python原生字典/列表
**返回值**：Python dict 或 list
### 1.3 代码结构
```python
client = OpenAI(api_key=os.getenv("OPENAI_API_KEY"))
question = []
examples_data = {}
messages = []
for key, value in examples_data.items():
   for t in value:
      messages.append(
         {"role": "user", "content": t}
      )
for q in question:
   response = client.chat(messages=messages + [{"role": "user", "content": q}])
```

## 2. LangChain
### 2.1 LangChain 介绍:
核心理念是提供LLM通用接口，本质是一个python第三方库，与OpenAI一样
- Prompts : 优化提示词
- Models : 调用各类模型
- History : 管理会话历史
- Indexes : 管理和分析各类文档
- Chains : 构建功能的执行链条
- Agents : 构建智能体
- `pip install langchain`;`uv add langchain langchain-community langchain-ollama dashscope chromadb`;
### 2.2 RAG：Retrieval Augmented Generation
- LLM: **知识滞后**，**知识缺乏**，**幻觉问题**，**安全问题** ---> RAG：知识检索增强可以解决
- 流程：
  在线流程：用户提问(A\G)   用户提问--->检索环节--->Prompt融合--->大模型回答
  离线流程：知识库预处理(R)   文档/私有知识加载--->分割--->向量化--->向量数据库-------->上一行的检索环节

