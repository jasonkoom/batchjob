package com.warehouse.batchjob;
import org.apache.log4j.*;

public class Runjob {

    private static Logger logger = Logger.getLogger(Runjob.class);
    public static void main(String args[]) {

         PropertyConfigurator.configure("src/log4j.properties" );

        if (args.length == 2) {
            System.out.println(args[0]);
            System.out.println(args[1]);
        }
        logger.info("222");
        //logger.debug( " debug " );
        logger.error( " error " );


    }
}
