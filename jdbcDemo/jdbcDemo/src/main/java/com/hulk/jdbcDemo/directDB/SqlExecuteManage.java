package com.hulk.jdbcDemo.directDB;

/**
 * @author ：lchao16
 * @ClassName ：${CLASS_NAME}
 * @date ：2019/1/22 15:30
 * @description：
 * @modified By：
 * @version: 1.0.0.1
 */

public class SqlExecuteManage {
    /**
     * 数据库类型过滤，针对特有函数转换
     *
     * @param sql
     * @return
     */
    public static String formatSqlByDBTypy(String sql) {

        if(DBPool.dbType.equalsIgnoreCase("oracle")){//default dbtype

        }else if(DBPool.dbType.equalsIgnoreCase("mysql")){
            sql = sql.replaceAll("sysdate", "now()").replaceAll("nvl\\(", "ifnull(").replaceAll("substr\\(", "substring(").replaceAll("to_char\\(", "date_format(")
                    .replaceAll("yyyy-mm-dd", "%Y-%m-%d").replaceAll("hh24:mi:ss", "%H:%i:%S").replaceAll("days\\(", "(").replaceAll("timestampdiff\\(4,", "(");
        }else if(DBPool.dbType.equalsIgnoreCase("db2")){
            sql = sql.replaceAll(":term_id like concat\\(\\'%\\',concat\\(term_id,\\';%\\'\\)\\)", "locate(concat(term_id,';'),:term_id)>0")
                    .replaceAll(":group_Id like concat\\(\\'%\\',concat\\(group_Id,\\';%\\'\\)\\)", "locate(concat(group_Id,';'),:group_Id)>0").replaceAll("nvl\\(", "coalesce\\(")
                    .replaceAll("sysdate", "current_timestamp");
        }else if(DBPool.dbType.equalsIgnoreCase("sqlserver")){
            sql = sql.replaceAll("sysdate", "getdate()").replaceAll("nvl\\ \\(", "isnull(").replaceAll("substr\\(", "substring(").replaceAll("timestampdiff\\(4,", "(");;
        }

        return sql;
    }
}
