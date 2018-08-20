## mysql操作例子
> 关闭springuser用户在localhost上对db_example数据库的所有权限操作。
``` bash
revoke all on db_example.* from 'springuser'@'localhost';  
```

> 开启springuser用户在localhost上对db_example数据库的CURD权限操作。
``` bash
grant select,insert,delete,update on db_example.* to 'springuser'@'localhost';
```

> 