# HSY寝室管理系统

### 技术栈
```
JavaFx2     
Mybatis3
```

### 开发环境
``` 
Java 1.8   
Idea-2018.3
```

### 项目结构
```
MVC（模型Model-视图View-控制器Controller）
```

![image](resources/images/tree.png)

### 效果图与功能介绍
1、登陆
- 初次登陆必须修改密码
- 初始管理员：用户名admin，密码123
![image](resources/images/login.png)

2、修改密码
- 健壮性：单击确认按钮后会检查数据库用户名是否存在、原始密码是否正确、新密码和确认密码是否一致、新密码是否超过6位

![image](resources/images/resetPWD.png)

3、系统主界面
* 寝室入住/退房页面
    * 操作逻辑：点击左侧选择一个房间，该房间详情会显示在右上角table中，点击右上角table可以选中床位，并且根据当前床位的状态（有人或无人）来选择性屏蔽右下角tab
    * 可以根据房间号模糊搜索房间，例如，输入2可以显示出所有房间号中含有2的房间：102, 201等等
    * 健壮性：选中床位自动屏蔽入住或退房中某一个tab，选中存在不合法信息的床位或者房间则会屏蔽入住退房两个tab；入住操作只能根据完整学号查询并且填充，不可编辑。退房操作在选中床位后自动填充，不可编辑。
    * 顶部显示欢迎信息， 显示当前时间， 有修改当前用户密码和退出按钮
    
![image](resources/images/tab1.png)

* 学生信息查询页面
    * 学生支持多条件查询
    * 寝室条件则必须输入完整寝室号，会在右侧列表显示入住该寝室的成员详细信息

![image](resources/images/tab2.png)

* 更多功能页面
    * 备份数据库：需提供用户名、密码、数据库名称、路径、文件名等信息
    * 导出Excel报表：自动导出当前所有学生的详细报表
    
![image](resources/images/backupDB.png)

![image](resources/images/exportExcel.png)



### 1.0版本完工了...

- 代码统计

![image](resources/images/statistic.png)

* 一些感悟
    * 遵循MVC设计模式（Model–view–controller），加上JavaFX原生支持，确实很大程度上实现了分层和解耦，出了Bug改起来很方便，甚至可以在写完了整个项目之后再改界面还不够满意的地方。
    * 开发流程： 确定需求 -> 写数据库访问接口 -> 写业务逻辑 -> 写页面（FXML+controller）， 调起之前写的数据库访问方法很爽。
    * 使用Mybatis框架，原理和JDBC大同小异，但是支持注解（快速实现基础增删改查） + XML（复杂多条件查询等），功能更加强大
    * 工具类：
        * 单例模式实现SqlSessionFactoryUtil，工厂模式创建sqlSession。