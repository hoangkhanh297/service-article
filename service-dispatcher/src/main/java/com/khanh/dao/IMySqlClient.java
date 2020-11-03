package com.khanh.dao;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;

import java.util.List;

public interface IMySqlClient<T> {
     void queryWithParams(String SQL, JsonArray params, final Class<T> type, final Handler<List<T>> whenDone);
}
