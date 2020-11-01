package com.khanh.conection;

import com.khanh.dao.BaseJDBCClient;
import com.khanh.dao.BaseJDBCClientImpl;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class DatabaseConnection {
    public static BaseJDBCClient getConnection() {
        JsonObject dbConfig = new JsonObject();
        dbConfig.put("jdbcUrl", "jdbc:mysql://localhost:3306/?user=root");
        dbConfig.put("provider_class", "io.vertx.ext.jdbc.spi.impl.HikariCPDataSourceProvider");
        dbConfig.put("maximumPoolSize", 2);
        dbConfig.put("driver_class", "oracle.jdbc.pool.OracleDataSource");
        dbConfig.put("username", "root");
        dbConfig.put("password", "1234567x@X");
        dbConfig.put("poolName", "be.u.ora");
       return  new BaseJDBCClientImpl(Vertx.vertx(), dbConfig, "world");
    }
}
