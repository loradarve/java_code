# FastAPI

## 一、uv安装
### uv安装，同时显示了安装位置，在C盘
```powershell
PS C:\Users\48179> powershell -ExecutionPolicy ByPass -c "irm https://astral.sh/uv/install.ps1 | iex"
downloading uv 0.11.13 (x86_64-pc-windows-msvc)
installing to C:\Users\48179\.local\bin
  uv.exe
  uvx.exe
  uvw.exe
everything's installed!

To add C:\Users\48179\.local\bin to your PATH, either restart your shell or run:

    set Path=C:\Users\48179\.local\bin;%Path%   (cmd)
    $env:Path = "C:\Users\48179\.local\bin;$env:Path"(powershell)
```
### 为uv安装全局python，所有项目全局共享python，只隔离包，利用.toml文件管理包路径，注意这区别于conda的每个项目重建环境
```
cpython-3.12.13-windows-x86_64-none                  AppData\Roaming\uv\python\cpython-3.12.13-windows-x86_64-none\python.exe
cpython-3.12.13-windows-x86_64-none                  .local\bin\python3.12.exe
cpython-3.12.13-windows-x86_64-none                  AppData\Roaming\uv\python\cpython-3.12-windows-x86_64-none\python.exe
```

## 二、FastAPI介绍
构建接口和网站的框架有FastAPI、Django、Flask。其中FastAPI是使用Python3.7+开发的，性能高，使用简单，并且支持异步，开发体验好，有可交互文档：
> https://127.0.0.1:8000/docs
> **FastAPI**
> GET /Root
> GET /async
> GET /sync
> 以上内容为可交互式文档的地址与界面，可以看到上面展示了三个接口

> PS:使用uv时，项目名称不要和库重名，否则会报错
```
uv init fastapi-for-dify
uv add fastapi uvicorn
uv run uvicorn main:app --reload
```
```toml
[project]
name = "fastapi-for-dify"
version = "0.1.0"
description = "Add your description here"
readme = "README.md"
requires-python = ">=3.12"
dependencies = [
    "fastapi>=0.136.1",
    "uvicorn>=0.46.0",
]
```
### FastAPI的路由结构解释：
1. 路由就是**URL地址**和**处理函数**的映射关系，当用户访问特定网址时，对应的服务器会执行对应的特定函数
2. 路由的参数结构：
   ```python
    @app.get("/")
    async def root():
        return {"message": "Hello World"}

    @app.get("/hello/{name}")
    async def say_hello(name: str):
        return {"message": f"Hello {name}"}
   ```
- 可以看到上面的路由结构，其构件为：`@app`FastAPI实例，`get`：请求方法，`/`：请求路径，`async def`：处理函数，`return`：返回结果
3. 路径参数`Path`
   路径参数通过`=`跟在类型注解之后，其括号内可以添加多种控制参数，如`min_length`：最小长度；`max_length`：最大长度；`description`：描述；`ge`： 大于等于；`le`：小于等于；`gt`：大于；`lt`：小于等等。
4. 查询参数，当声明的参数不属于路径参数时，就自动识别为查询参数。如下，skip和limit为查询参数。
    ```python
    @app.get("/news/news_list")
    async def news_list(skip: int, limit: int = 10):
        return {"skip": skip, "limit": limit}
    ```
    利用查询参数实现对网页数据的过滤、排序、分页等功能。例如：
    ```http
    http://127.0.0.1:8000/news/news_list?skip=0&limit=10
    ```

    **查询参数`Query`**
    功能与路径参数`Path`相同，但不能混用，关键在于路径参数必须出现在URL链接中，反之不必须。
5. 请求体：
    - 位置：HTTP请求体(body)中
    - 作用：创建、更新资源，携带大量数据，如JSON数据
    - 常见方法：POST, PUT
- 示例：用户注册，定义user类，包含用户名+密码，定义post接口，结果如下：
```Schema
{
  "username": "admin",
  "password": "root"
}
```
以上为发送信息
```curl
curl -X 'POST' \
  'http://127.0.0.1:8000/register' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "username": "admin",
  "password": "root"
}'
```
**请求体参数**：
- 功能：创建更新资源，提供给服务器
- 导入方法：`from pydantic import BaseModel, Field`，然后语法与前面的参数类似
6. 请求与响应
- 导入方法：`from fastapi.response import HTMLResponse, FileResponse`
- 响应类型：默认为JSONResponse,其它有HTMLResponse, FileResponse
- 方法一：在装饰器中指定响应类型`response_class=HTMLResponse`
- 方法二：在函数中返回响应对象`response_class=FileResponse`
- 自定义响应数据格式，借助类 **`response_model=class`**
- 异常响应：`from fastapi import HTTPException`
   
## 三、FastAPI进阶
### 中间件
中间件：在请求处理之前和之后执行一段代码，比如记录请求日志、验证用户权限等等。
| 客户端 | 中间件 | 服务器 |
| - | - | - |
| 请求进入> | 传递请求> |  |
| | <返回响应 | <返回响应 |

**多个中间件的执行顺序：满足洋葱模型，按照pyehon代码编写顺序自下而上**
```python
@app.middleware("http")
async def middleware1(request, call_next):
    response = await call_next(request)
    return response
```
### 依赖注入
**对比中间件对所有请求增加共同代码逻辑，依赖注入是选中部分特定接口执行共同代码逻辑**
- 需要导入`from fastapi import Depends`
- 依赖：然后写一个不加装饰器的异步函数
- 注入：最后在需要的接口处使用`commins=Depends(common_func)`进行依赖注入
## 四、ORM(对象关系映射)工具
1. ORM(Object Relational Mapping)[对象关系映射]
2. ORM工具排行
    - SQLAlchemy ORM(ours leran orm)
    - Django ORM
    - Tortoise ORM
3. 安装：`uv add "sqlalchemy[asyncio]" aiomysql`

## 四、SQLAlchemy ORM
### 1、SQLAlchemy ORM建表
代码参考main_create_table.py
三个步骤：构建异步引擎；创建模型类；建表
### 2、异步会话工厂构建依赖注入
构建异步会话工厂，并注入依赖(即后续的增删改查操作均需要加载异步会话工厂基础上的数据库)
### 3、增删改查
1. 多种查询（主要）
    - `select`, `db.execute`, `scalars`等关键字，查询
    - 条件查询：`where`
    - 模糊条件查询
        ```python
        @app.get("/search")
        async def search(db: AsyncSession = Depends(get_database)):
            result = await db.execute(select(Book).where(Book.author.like("%曹"))) # 模糊查询like(%表示曹后面可以有任意字符数)
            result = await db.execute(select(Book).where(Book.author.like("_曹"))) # 模糊查询like(_表示曹后面只能有一个字符)
            # 可以有多个条件 用 &，| 等等，比如 & (Book.price > 100)
            id_list = [1, 2, 3]
            result = await db.execute(select(Book).where(Book.id.in_(id_list))) # 查询列表中有的id
            book = result.scalars().all()
            return book
        ``` 
    - 聚合查询
        ```python
            result = await db.execute(select(func.avg(Book.price)))
            count = result.scalar() # 单数的scalar() 获取标量，聚合查询的结果都是标量
            return count
        ```
            count：统计行数
            sum：求和
            max：最大值
            min：最小值
            avg：平均值
    - 分页查询
    ```python 
    @app.get("/book/get_books")
    async def get_book_list(page: int = 1, page_size: int = 10, db: AsyncSession = Depends(get_database)):
        skip = (page - 1) * page_size # 计算跳过条数 offset值 = (当前页-1)*每页条数
        # offset：跳过几条数据; limit：取几条数据;
        stmt = select(Book).offset(skip).limit(page_size)
        result = await db.execute(stmt)
        books = result.scalars().all()
        return books
    ```
2. 新增数据
   定义ORM对象，添加对象到数据库，提交
   用户输入--参数--请求体格式
   mysql> select * from books;
    ```bash
    +----+----------+--------+-------+------------+---------------------+---------------------+
    | id | bookname | author | price | publisher  | create_time         | update_time         |
    +----+----------+--------+-------+------------+---------------------+---------------------+
    |  1 | 红楼梦   | 曹雪芹 |   160 | 黑马出版社 | 2026-05-21 11:00:00 | 2026-05-21 11:00:00 |
    +----+----------+--------+-------+------------+---------------------+---------------------+
    1 row in set (0.01 sec)
    ```

3. 更新数据
    查-请求体数据覆盖修改-提交数据库
4. 删除数据
    查-删除delete-提交数据库

## 五、FastAPI 企业级模块化项目架构
### 1、项目结构
app/
|- config # 配置文件
|- crud # 增删改查
|- models # 数据库ORM类模型
|- schemas 
|- routers # 路由
|- utils # 工具类
main.py
test_main.http
### 2、模块化路由
1. app/routers/news.py
```python
from fastapi import APIRouter
router = APIRouter(prefix="/api/news", tags=["news"]) # 前缀、分组
@router.get("/categories")
async def get_categories():
    return {"message": "获取新闻分类成功"}
```

2. app/main.py
```python
from fastapi import FastAPI
from app.routers import news
app = FastAPI()
app.include_router(news.router) # 挂载路由
```
### 3、数据库与ORM配置
自己建表或者找资源 把database.sql文件导入数据库
将之前写过的ORM对象化代码放在app/config/db_config.py中
代码内容为：安装sqlalchemy[asyncio]\aiomysql  创建异步引擎、创建模型类、建表

### 4、ORM对象类
app/models/news.py写ORM对象类：Base基类(创建时间、更新时间)、Category类（表名 id\name\description等字段信息）
在app/curd/news.py中导入ORM对象类，并定义增删改查方法

### 5、跨域问题 - 添加中间件
CORS中间件：跨域资源共享（协议+域名+端口都一样浏览器安全机制才允许通过）
由于前端的Vue运行在5377端口，后端的FastAPI运行在8000端口，所以需要添加跨域中间件

app/main.py中添加跨域中间件
```python
from fastapi.middleware.cors import CORSMiddleware
# 允许的来源（可以是域名列表）
origins = [
    "http://localhost:8080",
]
# 添加跨域中间件
app.add_middleware(CORSMiddleware, allow_origins=origins, allow_credentials=True, allow_methods=["*"], allow_headers=["*"])
# 允许的来源、允许的cookie、允许的请求方法、允许的请求头
```

**三个问题记录**
1. 模型类中，如何为字段创建索引？
    # 创建索引，提升查询速度
    ```python
        __table_args__ = ( # 表级索引
            Index('fk_news_category_idx', "Category.id"), # 外键索引
            Index('idx_publish_time', "publish_time") # 索引
        )
    ```
2. ORM如何将查询的数据库进行排序
`select().order_by(Book.id.desc())` 其中`desc()`为降序，`asc()`为升序
1. 使用ORM修改数据的方法是什么？
`update().where().values()` # where()为条件，values()为修改的字段及值
```python
async def increase_news_view(db: AsyncSession, news_id: int):
    stmt = update(News).where(News.id == news_id).values(view_count=News.view_count + 1) # 修改字段及值
    result = await db.execute(stmt)
    await db.commit()
    return result.rowcount > 0
```

### 6. 用户注册模块：路由-模型类-add(方法)-依赖注入调用
返回内容包含token: 用于验证用户是否登录
1. 路由：app/routers/users.py
```python
@router.post("/register")
async def register(user: UserRequest, db: AsyncSession = Depends(get_database)):
    return {
        "code": 200,
        "message": "注册成功",
        "data": {
            "token": "用户访问令牌",
            "userInfo": {
                "id": ,
                "username": "用户名",
                "bio": "用户简介",
                "avatar": "用户头像"
            }
        }
    }
```
2. 模型类：app/schemas/users.py
```python
class UserRequest:
    username: str = Field(..., min_length=4, max_length=20)
    password: str = Field(..., min_length=6, max_length=20)
```
3. main.py挂载路由
4. 路由router/users.py在函数中添加如下逻辑代码：
   请求---检验用户是否已经存在于用户表---若存在抛出异常，不存在创建用户(密码加密+添加用户信息)---uuid生成用户Token---响应结果
   - 在models/users.py中添加User类
   ```python
   class User(Base):
    __tablename__ = "users"
    id = Column(Integer, primary_key=True, index=True)
    username = Column(String(20), unique=True, index=True)
    pass
    ```
    - 在crud/users.py中添加get_user_by_name函数，检查用户名是否已存在:`query = select(User).where(User.username == username)`
    - 在crud/users.py中添加create_user函数，要包含密码加密于创建用户
5. 加密密码方法
   - 安装passlib ：`pip install "passlib[bcrypt]==1.7.4"`官方推荐稳定版本
   - 在utils文件夹之间security.py中添加加密方法，方便封装多接口调用
        `pwd_context = CryptContext(schemes=["bcrypt"], deprecated="auto")` 创建密码上下文
        `def get_hash_password(password: str):`
        `return pwd_context.hash(password)`
   - 回到路由router/users.py中，添加加密密码方法：`hashed_password = security.get_password_hash(user.password)`
6. 访问令牌：
   Token:服务器发给客户端的一段字符串，作为登录证明
   作用：每次客户端请求服务端接口时，都需要携带访问令牌，服务端会验证令牌，并返回结果，用于“自我身份证明”
   位置在请求头中：Authorization: Bearer <token> 鉴权
7. 封装通用成功响应格式
    - 抽取响应结果
    utils/response.py
    ```python
    from fastapi.responses import JSONResponse
    def success_reponse(message: str = "success", data = None)
        content = {"code": 200, "message": message, "data": data}
        return JSONResponse(content=jsonable(content))
    ``` 
    - 定义数据类型
    schemas/users.py 三个类，互相嵌套
    ```python
    class UserInfoBase(BaseModel):
        pass # 一些基础信息

    class UserInfoResponse(UserInfoBase):
        id: int
        username: str

    class UserAuthResponse(UserInfoResponse):
        token: str
        userInfo: UserInfoResponse = Field(..., alias="userInfo") # 取别名
        # 模型配置类
        moddel_config = ConfigDict(populate_by_name=True, from_attributes=True)
    ``` 
    - 调用函数
    schemas/users.py
    `response = UserAuthResponse(token=token, userInfo=UserInfoResponse.model_dump(user))`
    `return success_reponse(message = "注册成功", data=response)`
8. 全局异常处理器(Global Exception Handler)
- 在 utils/exceptions.py 添加统一的大量异常处理函数
- 在 utils/exception_handler.py 添加全局异常处理函数
```python
def register_exception_handler(app: FastAPI):
    # 注册全局异常处理：子类在前，父类在后；具体在前，抽象在后
    app.add_exception_handler(类型，方法名) # 自己上面写的，多行，导包
    # 异常处理函数共4个，业务、数据完整性约束、数据库、兜底
```
- main.py 进行注册：`register_exception_handler(app)`
9. 用户登录等；添加收藏、取消添加收藏、获取收藏列表、清空收藏列表；添加历史浏览记录、取消添加历史浏览记录、获取历史浏览记录、清空历史浏览记录
- ORM联表查询写法：`select(查询的主体模型类，字段重命名).join(联表模型类， 联表的条件)`
- 唯一约束：`UniqueConstraint(字段1,字段2,name="唯一约束名称")`
10. redis缓存(Cache)：安装、配置redis，封装redis功能方便调用，设计缓存策略
- config/cache_conf.py
```python
async def get_cache(key: str) -> Any:
    try:
        data = await redis_client.get(key)
        if data:
            return json.loads(data)  # 反序列化为 Python 对象
        return None
    except Exception as e:
        print(f"获取 JSON 缓存失败: {e}")
        return None
```
- 旁路缓存策略：
    + 读操作：先查缓存 → 命中直接返回；未命中则查数据库，再把数据写回缓存。
    + 写操作：先更新数据库 → 再删除或更新缓存（避免脏数据）。
    + 应用主动管理缓存，缓存与数据库的一致性由业务代码维护。
```python
async def get_categories(db: AsyncSession, skip: int=0, limit: int=100):
    # 1. 尝试从缓存获取
    cached_categories = await get_cached_categories()
    if cached_categories:
        return cached_categories
    
    # 2. 如果缓存没有，则从数据库中查询
    stmt = select(Category).offset(skip).limit(limit)
    result = await db.execute(stmt)
    categories = result.scalars().all()
    
    # 3. 缓存新闻分类列表
    if categories:
        categories = jsonable_encoder(categories)
        await cache_categories(categories)
    
    return categories
```