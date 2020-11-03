package com.khanh.dao;

import com.khanh.utils.JSONUtils;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.impl.ArrayTuple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySqlClientImpl<T> implements IMySqlClient<T> {
    private static MySQLConnectOptions connectOptions;
    public static MySQLPool getConnection(){
        if (connectOptions == null) {
            connectOptions = new MySQLConnectOptions();
        }
        connectOptions.setPort(3306)
                .setHost("localhost")
                .setDatabase("world")
                .setUser("root")
                .setPassword("12345678x@X");
        PoolOptions poolOptions = new PoolOptions()
                .setMaxSize(5);
        return  MySQLPool.pool(connectOptions, poolOptions);
    }


    @Override
    public void queryWithParams(String SQL, JsonArray params, Class<T> type, Handler<List<T>> whenDone) {
        ArrayTuple tuple = new ArrayTuple(params.size());
        for (Object o : params) {
            tuple.addValue(o);
        }
        List<T> out = new ArrayList<>();
        getConnection().preparedQuery(SQL).execute(tuple, rowSetAsyncResult -> {
            RowSet<Row> rows = rowSetAsyncResult.result();
            for (Row row : rows) {
                Map<String, String> result = new HashMap<>();
                for (int i = 0; i < row.size(); i++) {
                    result.put(row.getColumnName(i), row.getValue(row.getColumnName(i)).toString());
                }
                out.add((T) JSONUtils.mapToObj(result, type));
            }
            whenDone.handle(out);
        });
    }
}
