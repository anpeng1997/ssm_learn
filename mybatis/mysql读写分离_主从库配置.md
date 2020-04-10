# mysql的主从库配置

[同一台电脑windows环境下的mysql的主从配置](https://www.cnblogs.com/healthinfo/p/10375322.html)

[mysql主从同步异常原因及恢复](https://blog.csdn.net/GX_1_11_real/article/details/80658527)

1. 主库的my.ini的配置文件

    ```ini
    [mysqld]
    port=3306

    character-set-server=utf8

    skip-grant-tables

    #给数据库服务的唯一标识，必须唯一
    server-id=3306
    #开启二进制文件，后面的设置的master-bin就是二进制文件名字的前缀
    log-bin=master-bin
    #开启二进制文件的索引
    log-bin-index=master-bin.index
    ```

2. 从库的my.ini的配置

    ```ini
    [mysqld]

    port=3307

    character-set-server=utf8

    skip-grant-tables

    server-id=3307

    relay-log=slave-relay-bin

    relay-log-index=slave-relay-bin.index

    #都不设置就是所有的数据库都会同步
    #replicate-do-db=master #需要同步的数据库
    #replicate-ignore-db=mysql #需要排除同步的数据库
    ```

3. 在从库中执行sql配置语句

    ```sql
    change master to master_host='127.0.0.1',
    master_user='root',
    master_password='root',
    master_port=3306,
    master_log_file='master-bin.000001',
    #master_log_pos指定请求同步Master的bin-log的哪一行数据之后的内容；
    master_log_pos=0;
    ```

4. 常用的SQL命令

    ```sql
        #主库
        show master status;

        reset master;

        show variables like 'server_id';

        #从库

        #开启从库
        start slave;

        #显示从库的状态   \G
        show slave status;

        #停止从库同步
        stop slave;

        #设置跳过一步错误
        set global sql_slave_skip_counter =1;

        #清除slave上的同步位置，删除所有旧的同步日志，使用新的日志重新开始.(使用前先停止slave服务）
        reset slave;
    ```
