package com.warehouse.batchjob.db;
import com.warehouse.batchjob.db.DataSource;
import java.util.*;

import java.sql.*;
public class OperDb {

    private OperDb(){}
    private static OperDb instance = new OperDb();
    public  static OperDb getInstance(){
        return instance;
    }
    //返回记录集
    public List<Map<String, Object>> GetResult(Connection conn,String strsql) {
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        try {
            PreparedStatement ps =  conn.prepareStatement(strsql);
            ResultSet rs = ps.executeQuery();

            ResultSetMetaData md = rs.getMetaData(); //获得结果集结构信息,元数据
            int columnCount = md.getColumnCount();   //获得列数
            while (rs.next()) {
                Map<String,Object> rowData = new HashMap<String,Object>();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i).toUpperCase(), rs.getObject(i));
                }
                list.add(rowData);

            }
            return list;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            try{
                conn.close();
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
    }
    //执行语句
    public void ExecuteSql(Connection conn,String strsql){
        try {
            //conn.setAutoCommit(false);
            PreparedStatement ps =  conn.prepareStatement(strsql);
            Boolean rs = ps.execute();
            //conn.commit();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    // 执行存过
    // String sql = "{call validateSelect('"+userName+"','"+passWord+"')}";
    public void ExecuteProcedure(Connection conn,String strsql,String etl_dt){
        try{
            CallableStatement  cstmt  =  conn.prepareCall(strsql);
            cstmt.setString(1,etl_dt);
            cstmt.registerOutParameter(2, Types.CHAR);
            cstmt.registerOutParameter(3, Types.CHAR);
            cstmt.executeQuery();

        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

    }


}
