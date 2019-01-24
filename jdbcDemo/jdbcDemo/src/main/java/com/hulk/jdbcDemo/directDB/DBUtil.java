package com.hulk.jdbcDemo.directDB;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;

/**
 * @author ：lchao16
 * @ClassName ：${CLASS_NAME}
 * @date ：2019/1/22 15:15
 * @description：
 * @modified By：
 * @version: 1.0.0.1
 */
@Component
public class DBUtil {
    /**
     * 数据增加
     * @param tableName 表名
     * @param params 参数集合, key为字段名称, value为参数值
     * @return
     */
    public  static void callDbInsert(String tableName, Map<String,Object> params)throws Exception{
        boolean executeResult = false;
        String sql = "INSERT INTO " + tableName;
        String fieldName = " (";
        String fieldValue = "";
        StringBuffer values = new StringBuffer(" values(");
        for(Map.Entry<String,Object> entry : params.entrySet()){
            fieldName = fieldName + entry.getKey() + ",";
            String entryVal = String.valueOf(entry.getValue());
            if(entryVal.indexOf("to_date")!=-1||entryVal.indexOf("TO_DATE")!=-1){
                values.append(entry.getValue()+ ",") ;
            }else{
                values.append("'" + entry.getValue() + "',");
            }
        }
        fieldName = fieldName.substring(0,fieldName.length()-1) + ")";
        sql = sql + fieldName + values.substring(0,values.length()-1) + ")";
        if(sql.contains("$ID")){
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            sql = sql.replaceAll(Matcher.quoteReplacement("$ID"), uuid);
        }
        //sql过滤，跨数据库支持
        sql = SqlExecuteManage.formatSqlByDBTypy(sql);
      /*  {
            HikariDataSource tempdatas =  (HikariDataSource)DBPool.jdbcTemplate.getDataSource();
           System.out.println(tempdatas.getPassword());
            System.out.println(tempdatas.getUsername());
            System.out.println(tempdatas.getJdbcUrl());
        }*/
        DBPool.jdbcTemplate.execute(sql);

    }
}
