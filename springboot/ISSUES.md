## 1.1 MySQL警告：
> 问题描述
``` bash 
 WARN: Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.
 
```
> 分析原因：

是Mysql数据库的SSL连接问题，提示警告不建议使用没有带服务器身份验证的SSL连接，是在MYSQL5.5.45+, 5.6.26+ and 5.7.6+版本中才有的这个问题。解决办法在警告中已经说明了：

> 解决方案：

- 1.在数据库连接的url中添加useSSL=false;
- 2.url中添加useSSL=true，并且提供服务器的验证证书。

## 1.2. MySQL错误
> 问题描述
``` bash 
Failed to auto-configure a DataSource
```

> 分析原因：

未能自动配置数据源

> 解决方案：

- 方法一：

 在Application后添加(exclude={DataSourceAutoConfiguration.class})

 ```
 @Application(exclude={DataSourceAutoConfiguration.class})
```

- 方法二：

1.添加如下依赖

``` bash
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <!-- 移除嵌入式tomcat插件 不移除会启动报错 -->
    <exclusions>
        <exclusion>
             <groupId>org.springframework.boot</groupId>
                 <artifactId>spring-boot-starter-tomcat</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```
2.必须要加

``` bash
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
</dependency>
```

## 1.3 
> 问题描述
``` 
mybatis向mysql数据库中插入数据报错Field 'id' doesn't have a default value
```

> 分析原因

主键id不能是默认值，应设置自增

> 解决方案

修改数据库表，将id字段设置自增。
