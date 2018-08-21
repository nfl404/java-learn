## 1.0. java数据库访问方式
JDBC，hibernate，mybati
### 1.1. 使用Spring Data JPA来访问MySQL
#### 1.1.1 JPA 特性

- Spring Data JPA 专注于使用JPA在关系数据库中存储数据。其最亮点的功能是能够在运行时从存储库接口自动创建存储库实现。
- 通过编写实现的类CustomerRepository，不必编写存储库接口的实现。Spring Data JPA在运行应用程序时动态创建实现。

#### 1.1.2 JPA 访问原理

- 1.创建entity类，目的是为了映射数据库表的数据结构
- 2.创建实体的repository类,继承CruRepository。CruRepository继承了几种处理entity类的方法，包括保存，删除，查找等。

> Spring Data JPA还允许您通过简单地声明其方法签名来定义其他查询方法。如在创建的Repoistory类中，使用findByLastName()显示。

#### 1.1.3 demo演示
[demo 演示]()

### 1.2 使用Spring JDBC访问MySQL
#### 1.2.1 特性
使用Spring构建的JdbcTemplate来访问存储在关系数据库中的数据。

#### 1.2.2 原理

- 1.创建customer对象
- 2.使用Spring提供的模板类JdbcTemplate，使用关系数据库和JDBC。
- 3.通过JdbcTemplate.execute 方法安装DDL(Data Definition Language)
- 4.通过JdbcTemplate.batchUpdate 实现插入等,该方法有两个参数，第一个参数是执行的sql语句字符串，第二个参数是数组参数的变量

> 或者执行JdbcTemplate.query查询操作
> ``` bash
> jdbcTemplate.query("SELECT id, first_name, last_name FROM customers WHERE first_name = ?", new Object[] { "Josh" }, (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))).forEach(customer -> log.info(customer.toString()));
> ```

#### 1.2.3 demo演示
[demo 演示]()


### 1.3 hibernate访问
### 1.3.1 特性
- 1.对JDBC的代码进行了封装，使我们的编程更简便了，不用写SQL语句，提高了开发效率。
- 2.消除了代码的映射规则，也无需在管理数据库连接，全部被分离到了XML或注解里面去配置。
- 3.hibernate使用的是HQL语言，它支持方言配置，方便数据库移植。
- 4.一个会话中，不需要操作多个对象，只要操作Session即可，关闭资源也只要关闭一个Session即可，当然在Spring的集成使用下这些都会交由Spring来管理。
### 1.3.2 原理


### 1.4 mybatis访问
#### 1.4.1 特性
Mybatis使用SQL，也是最为直观的，绝大多数的使用Mysql的互联网公司使用Mybatis作为ORM框架。
#### 1.4.2 原理
- 1.


### 1.5 对比结论

- 1.使用JDBC方式访问。SpringBoot使用的是JdbcTemplate，JdbcTemplate是Spring对jdbc的封装，但是要自己写sql语句，增加了灵活性和复杂性，不利于跨数据库。
- 2.使用hibernate方式访问。不用关系底层的数据库是哪个，在编程方面使用HQL语言代替SQL以对象为单位操作数据库。hiberate利用了缓存产生实现数据库的读取操作，提供了性能。
- 3.Mybatis使用SQL语句，比较直观。
- 4.Mybatis和Hibernate的开发效率和运行效率。
#### 1.6 环境

- MySQL 版本5.6+
- JDK 版本1.8+
- Maven 版本3.2+
- IDE eclipse2018

## 2.0 缓存
Redis，memcach
## 3.0 分布式关系型数据库DRDS
技术特征，原理，JAVA开发连接方式
### 3.1 基本原理
#### 3.1.1 DRDS分库分表
DRDS在后端将数据量较大的数据表水平拆分到后端的每个RDS数据库中，这些拆分到RDS中的数据库被称为分库，分库中的表称为分表。

拆分后，每个分库负责每一份数据的读写操作，从而有效的分散了整体的访问压力。在系统扩容时，只需要水平正价分库的数量，并且迁移相关数据，就可以提高DRDS系统的总体容量。

[PS: DRDS 分库分表原理详细说明](https://help.aliyun.com/document_detail/29679.html?spm=a2c4g.11186623.6.556.eHQ1kR)
#### 3.1.2 DRDS SQL路由
在分库分表模式下，DRDS会根据拆分键（即拆分字段）以及SQL语义把SQL语句分发到底层的各个存储的分表进行执行。执行结束后，DRDS会将各个包上获取的数据合并，返回给用户。

[PS: DRDS SQL路由原理详细说明](https://help.aliyun.com/document_detail/29660.html?spm=a2c4g.11186623.6.557.BAttzc)
#### 3.1.3 DRDS 读写分离
在主实例的读请求较多、读压力比较大的时候，可以通过 DRDS 读写分离功能对读流量进行分流，减轻 RDS 主实例的读压力。

DRDS 的读写分离功能是对应用透明的设计。应用在不修改任何代码的情况下，只需要在 DRDS 控制台中调整读权重，即可将读流量按配置的比例在主 RDS 实例与多个 RDS 只读实例之间进行分流；写流量则全部到主实例，不做分流。

[PS: DRDS 读写分离原理详细说明](https://help.aliyun.com/document_detail/29681.html?spm=a2c4g.11186623.6.558.1L98kV)

### 3.1.4 DRDS 平滑扩容
当逻辑库对应的底层存储已经达到物理瓶颈，需要进行水平扩展，比如磁盘余量接近30%，那么可以通过平滑扩容来改善。

[ps: DRDS 平滑扩容原理详细说明](https://help.aliyun.com/document_detail/52132.html?spm=a2c4g.11186623.6.559.NYTJNL)

### 3.1.5 DRDS 分布式事务
DRDS 新版本从 5.2.6-1606682 开始提供原生的分布式事务支持。

区别传统的两阶段提交 (XA) 事务，DRDS 提供的原生分布式事务被称为 “柔性事务”。与 XA 事务相比，基于最终一致性原理的 “柔性事务” 能够更好的满足应用的高性能与高可用要求

[PS: DRDS分布式事务原理详细说明](https://help.aliyun.com/document_detail/71230.html?spm=a2c4g.11186623.6.560.YImfS1)

## 3.2 DRDS数据库操作
### 3.2.1 DRDS数据库创建
[DRDS数据库创建详细操作](https://help.aliyun.com/document_detail/50067.html?spm=a2c4g.11186623.6.550.ngxpmA)
### 3.2.2 DRDS数据库导入导出
[DRDS数据库导入导出详细操作](https://help.aliyun.com/document_detail/53106.html?spm=a2c4g.11186623.6.615.kxJgOr)

## 3.3 JAVA连接方式
[见 1.1 java数据库访问方式]()