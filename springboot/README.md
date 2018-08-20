## 1.1. 访问方式
JDBC，hibernate，mybatis，JPA
### 1.1.1 使用Spring Data JPA来访问MySQL
#### 1.1.1.1 JPA 特性

- Spring Data JPA 专注于使用JPA在关系数据库中存储数据。其最亮点的功能是能够在运行时从存储库接口自动创建存储库实现。
- 通过编写实现的类CustomerRepository，不必编写存储库接口的实现。Spring Data JPA在运行应用程序时动态创建实现。

#### 1.1.1.2 JPA 访问原理

- 1.创建entity类，目的是为了映射数据库表的数据结构
- 2.创建实体的repository类,继承CruRepository。CruRepository继承了几种处理entity类的方法，包括保存，删除，查找等。

> Spring Data JPA还允许您通过简单地声明其方法签名来定义其他查询方法。如在创建的Repoistory类中，使用findByLastName()显示。

#### 1.1.1.3 demo演示
[demo 演示]()

### 1.1.2 使用Spring JDBC访问MySQL
#### 1.1.2.1 特性
使用Spring构建的JdbcTemplate来访问存储在关系数据库中的数据。

#### 1.1.2.2 原理

- 1.创建customer对象
- 2.使用Spring提供的模板类JdbcTemplate，使用关系数据库和JDBC。
- 3.通过JdbcTemplate.execute 方法安装DDL(Data Definition Language)
- 4.通过JdbcTemplate.batchUpdate 实现插入等,该方法有两个参数，第一个参数是执行的sql语句字符串，第二个参数是数组参数的变量

> 或者执行JdbcTemplate.query查询操作
> ``` bash
> jdbcTemplate.query("SELECT id, first_name, last_name FROM customers WHERE first_name = ?", new Object[] { "Josh" }, (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))).forEach(customer -> log.info(customer.toString()));
> ```

#### 1.1.2.3 demo演示
[demo 演示]()


### 1.1.3 hibernate访问
### 1.1.4 mybatis访问
#### 1.1.4.1 特性
Mybatis使用SQL，也是最为直观的，绝大多数的使用Mysql的互联网公司使用Mybatis作为ORM框架。
#### 1.1.4.2 原理
- 1. 


### 1.1.5 对比

- 1.使用JDBC方式访问。SpringBoot使用的是JdbcTemplate，JdbcTemplate是Spring对jdbc的封装，但是要自己写sql语句，增加了灵活性和复杂性，不利于跨数据库。
- 2.使用hibernate方式访问。不用关系底层的数据库是哪个，在编程方面使用HQL语言代替SQL以对象为单位操作数据库。hiberate利用了缓存产生实现数据库的读取操作，提供了性能。
- 3.Mybatis使用SQL语句，比较直观。
#### 1.1.1.1 环境

- MySQL 版本5.6+
- JDK 版本1.8+
- Maven 版本3.2+
- IDE eclipse2018

## 缓存
Redis，memcach
## 分布式
技术特征，原理，JAVA开发连接方式