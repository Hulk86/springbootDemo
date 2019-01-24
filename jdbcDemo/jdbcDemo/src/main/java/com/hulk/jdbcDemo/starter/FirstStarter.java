package com.hulk.jdbcDemo.starter;

import com.hulk.jdbcDemo.directDB.DBPool;
import com.hulk.jdbcDemo.directDB.DBUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：lchao16
 * @ClassName ：${CLASS_NAME}
 * @date ：2019/1/22 15:05
 * @description：
 * @modified By：
 * @version: 1.0.0.1
 */
@Component
public class FirstStarter implements CommandLineRunner {

    @Autowired
    @Qualifier(value = "dbType")
    private String dbType;


    @Autowired
    @Qualifier(value = "PrimaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;


/*
    @Autowired
    @Qualifier(value = "DruidJdbcTemplate")
    private JdbcTemplate jdbcTemplate2;

*/
    @Override
    public void run(String... args) {
        //初始化DBPool
        DBPool.dbType = this.dbType;
        //DBPool.jdbcTemplate = this.jdbcTemplate2;
        DBPool.jdbcTemplate = this.jdbcTemplate;
        //
        try {
            Map<String, Object> dataInfo = new HashMap<>();
            dataInfo.put("name", "tony3");
            dataInfo.put("age", "388");
            DBUtil.callDbInsert("USERINFO", dataInfo);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
    }
}
