package com.warehouse.batchjob.control;
import com.warehouse.batchjob.db.OperDb;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import java.sql.*;
import java.util.*;
import com.warehouse.batchjob.context.GlobalContext;
import com.warehouse.batchjob.db.DataSource;
public class DealTran {
    private static Logger logger = Logger.getLogger(DealTran.class);
    public DealTran() {
        PropertyConfigurator.configure("src/log4j.properties" );
    }

    public void ExecuteTran(String bat_no,String etl_dt){
        Connection conn = DataSource.getInstance().getConnection();
        List rs = OperDb.getInstance().GetResult(conn,"select * from tran_con_def where bat_no='"+bat_no+"' order by seq_no");
        for (Object r : rs){
            HashMap hm = (HashMap)r;
            String valid_flag = (String)hm.get("VALID_FLAG");
            String tran_mode = (String)hm.get("TRAN_MODE");
            // 判断有效性
            if ( valid_flag.equals("1")) {
                if (tran_mode.equals("U")){
                    Connection conn2 = DataSource.getInstance().getConnection((String) hm.get("DB_DRIVER"),(String) hm.get("DB_URL"),(String) hm.get("DB_USERNAME"),(String) hm.get("DB_PASSWORD"));
                    String tran_sql = (String)hm.get("TRAN_SQL");
                    String del_sql = (String)hm.get("DEL_SQL");
                    tran_sql = tran_sql.replace("#ETL_DATE#",GlobalContext.getInstance().getFormatDay(etl_dt));
                    del_sql=del_sql.replace("#ETL_DATE#",GlobalContext.getInstance().getFormatDay(etl_dt));
                    OperDb.getInstance().ExecuteSql(conn2,del_sql);
                    OperDb.getInstance().ExecuteSql(conn2,tran_sql);
                }

               }else{
                logger.debug(bat_no + "当前状态无效，标志：" + valid_flag);
            }

            }

        }

    }








