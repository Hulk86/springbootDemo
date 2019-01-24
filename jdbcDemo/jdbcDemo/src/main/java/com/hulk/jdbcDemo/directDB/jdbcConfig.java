package com.hulk.jdbcDemo.directDB;

//import com.alibaba.druid.pool.DruidDataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

//import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author ：lchao16
 * @ClassName ：${CLASS_NAME}
 * @date ：2019/1/22 11:49
 * @description：
 * @modified By：
 * @version: 1.0.0.1
 */
@Configuration
@PropertySource("classpath:application.properties")
//@ConfigurationProperties(prefix = "spring.datasource")
public class jdbcConfig {
    /**
     * dbTypeArray indicate the db types be supported;
     */
   /* @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String  password;
    //private String driverclassname;
    @Value("${spring.datasource.driver-class-name}")
    private String driveClassName;
*/
    @Value("${spring.datasource.driver-class-name}")
    private String driveClassName;

    static private String[] dbTypeArray = {"oracle", "db2", "sqlserver", "mysql"};



    @Bean("dataSource")
    @Qualifier("dataSource")
   // @ConfigurationProperties(prefix = "spring.datasource.hikari")
    //@Primary
    public DataSource primaryDataSource() {
       DataSource theDATA =  DataSourceBuilder.create().build();

    /*      DataSource theDATA = new HikariDataSource();
      ((HikariDataSource) theDATA).setJdbcUrl(url);
        ((HikariDataSource) theDATA).setUsername(username);
        ((HikariDataSource) theDATA).setPassword(password);
        ((HikariDataSource) theDATA).setDriverClassName(driveClassName);
       //((HikariDataSource) theDATA).setDataSource();
        try{
        theDATA.getConnection();

            }catch (Exception e)
        {
            System.out.println(e);
        }*/
        return  theDATA;
        //DruidDataSource theDATA = new DruidDataSource();

        //return theDATA;
    }

    @Bean("PrimaryJdbcTemplate")
    public JdbcTemplate primaryJdbcTemplate(@Qualifier("dataSource")
                                                    DataSource datasource) {
        return new JdbcTemplate(datasource);
    }

    @Bean("dbType")
    public String getDbType() throws Exception {
        if (driveClassName == null || driveClassName.isEmpty()) {
            throw new Exception("driveClassName must be configured at application.properties explicitly!");
        }
        for (String dbType : dbTypeArray) {
            if (driveClassName.toLowerCase().contains(dbType)) {
                return dbType;
            }
        }
        return null;
    }

}
