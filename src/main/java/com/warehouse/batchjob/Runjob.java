package com.warehouse.batchjob;
import org.apache.log4j.*;
import com.warehouse.batchjob.db.DataSource;
import com.warehouse.batchjob.control.DealTran;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import com.warehouse.batchjob.db.OperDb;

import javax.sql.RowSet;

public class RunJob {

    //private static Logger logger = Logger.getLogger(Runjob.class);
    public static void main(String args[]) {

        //PropertyConfigurator.configure("src/log4j.properties" );

        if (args.length == 2) {
            System.out.println(args[0]);
            System.out.println(args[1]);
        }
        List rs = OperDb.getInstance().GetResult("select * from columns_v2 where cd_id ='71846'");
        //rs.forEach(r -> System.out.println(r));
        for (Object r : rs){
            HashMap hm = (HashMap)r;
            //System.out.println(hm.get("DB_ID"));
            //System.out.println(hm.get("DESC"));
            //System.out.println(hm.get("DB_LOCATION_URI"));
            //System.out.println(hm.get("NAME"));
            System.out.print(hm.get("COMMENT"));
            System.out.println(hm.get("COLUMN_NAME"));
        }






        //logger.info("222");
        //logger.debug( " debug " );
        //logger.error( " error " );


    }
}
