package com.warehouse.batchjob;
import org.apache.log4j.*;
import com.warehouse.batchjob.db.DataSource;
import com.warehouse.batchjob.control.DealTran;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import  org.apache.commons.lang.StringUtils;
import com.warehouse.batchjob.db.OperDb;



public class RunJob {

    private static Logger logger = Logger.getLogger(RunJob.class);
    public static void main(String args[]) {

        PropertyConfigurator.configure("src/log4j.properties" );

        if (args.length != 2) {

         logger.debug("传入参数个数:" + args.length + " 输入参数列表:"+ StringUtils.join(args,(char)32) );
         System.exit(-1);
        }
        List rs = OperDb.getInstance().GetResult("select * from etldb.tran_conf_def where bat_no='300010001'");
        //rs.forEach(r -> System.out.println(r));
        for (Object r : rs){
            HashMap hm = (HashMap)r;
            logger.info(hm.get("BAT_NO"));
            logger.info(hm.get("TGT_TAB_DESC"));
        }






        //logger.info("222");
        //logger.debug( " debug " );
        //logger.error( " error " );


    }
}
