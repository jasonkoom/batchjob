package com.warehouse.batchjob.context;

public interface PropName {
    public String DEBUGGER = "debugger";

    public interface Kafka {
        public String URL = "kafka.url";
        public String TOPIC = "kafka.topic";
        public String MAX_UNCOMMITTED_OFFSETS="kafka.max_uncommitted_offsets";
    }

    public interface Topology {
        public String NAME = "topology.name";
        public String WORKER_NUM = "topology.worker_num";
        public String MAX_SPOUT_PENDING = "topology.max_spout_pending";
    }

    public interface Database {
        public String TYPE = "database.type";
        public String DRIVER = "database.driver";
        public String URL= "database.url";
        public String USERNAME = "database.username";
        public String PASSWORD = "database.password";
    }

    public interface ErrorDatabase {
        public String TYPE = "err.database.type";
        public String DRIVER = "err.database.driver";
        public String URL= "err.database.url";
        public String USERNAME = "err.database.username";
        public String PASSWORD = "err.database.password";
        public String ERROR_LOG_TABLE = "err.log.table";
    }

}
