package com.warehouse.batchjob.db;
import com.warehouse.batchjob.context.GlobalContext;
import com.warehouse.batchjob.context.PropName;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.sql.*;
public class DataSource {
    private String DATABASE_DRIVER;
    private String DATABASE_URL;
    private String DATABASE_USERNAME;
    private String DATABASE_PASSWORD;

    private ComboPooledDataSource dataSource;

    private DataSource() {
        DATABASE_DRIVER = GlobalContext.getInstance().getStringValue(PropName.Database.DRIVER);
        DATABASE_URL = GlobalContext.getInstance().getStringValue(PropName.Database.URL);
        DATABASE_USERNAME = GlobalContext.getInstance().getStringValue(PropName.Database.USERNAME);
        DATABASE_PASSWORD = GlobalContext.getInstance().getStringValue(PropName.Database.PASSWORD);


        dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass(DATABASE_DRIVER);
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
        dataSource.setJdbcUrl(DATABASE_URL);
        dataSource.setUser(DATABASE_USERNAME);
        dataSource.setPassword(DATABASE_PASSWORD);
        String testSql = "select 1";
        dataSource.setPreferredTestQuery(testSql);
    }

    private static DataSource instance = new DataSource();
    public static DataSource getInstance() {
        return instance;
    }

    public Connection getConnection()  {
        try{
            return dataSource.getConnection();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

}
