package com.warehouse.batchjob;
import org.apache.log4j.*;
import com.warehouse.batchjob.db.DataSource;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.*;
import  org.apache.commons.lang.StringUtils;
import com.warehouse.batchjob.db.OperDb;
import java.lang.ClassNotFoundException;

public class RunJob {
    private static Logger logger = Logger.getLogger(RunJob.class);

    public static void main(String args[]) {
        PropertyConfigurator.configure("src/log4j.properties" );

        if (args.length != 4) {
         logger.error("参数个数:" + args.length + " 参数列表:"+ StringUtils.join(args,(char)32) );
         System.exit(-1);
        }else{
            long start_dt = System.currentTimeMillis();
            String classname=args[0];
            String funcname=args[1];
            String bat_no=args[2];
            String etl_date=args[3];

            classname = "com.warehouse.batchjob.control." +classname;
            Class<?> class_instance=null;
            try{
                class_instance= Class.forName(classname);
                Method method = class_instance.getMethod(funcname,String.class,String.class);
                method.invoke(class_instance.newInstance(),bat_no,etl_date);
            }catch(Exception e){
                System.exit(-1);
                throw new RuntimeException(e);
            }
            long end_dt = System.currentTimeMillis();
            logger.info("作业运行时间:" +(end_dt - start_dt)/1000);

        }
        /*Connection conn = DataSource.getInstance().getConnection();
        List rs = OperDb.getInstance().GetResult(conn,"select 1 ");

        for (Object r : rs){
            HashMap hm = (HashMap)r;
            logger.info(hm.get("BAT_NO"));
            logger.info(hm.get("TGT_TAB_DESC"));
        }*/




    }
}
