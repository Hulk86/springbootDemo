package com.hulk.jdbcDemo.directDB;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author ：lchao16
 * @ClassName ：${CLASS_NAME}
 * @date ：2019/1/22 11:22
 * @description： 使用springframework 的 jdbc 直连数据库
 * @modified By：
 * @version: 1.0.0.1
 */

@Component
public class DBPool {

    public static JdbcTemplate jdbcTemplate;
    public static String dbType;

}
