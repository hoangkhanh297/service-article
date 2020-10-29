package com;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import khanh.base.BaseHandler;

public class DispatcherHandlerBase {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        HttpServer server = vertx.createHttpServer();
        server.requestHandler(request -> {
            JsonObject response = new JsonObject();
            response.put("a",  BaseHandler.converter("HOANG VAN KHANH"));
            sendSuccessResponse(request.response(), response);
        });
        server.listen(8081);
        System.out.println("Start HTTP server OK");
    }

    private static void sendSuccessResponse(HttpServerResponse response, Object object) {
        sendResponse(response, object, 200);
    }

    private static void sendResponse(HttpServerResponse response, Object object, int statusCode) {
        response.setChunked(true)
                .putHeader("content-type", "application/json; charset=utf-8")
                .setStatusCode(statusCode)
                .write(object.toString(), "UTF-8");
        response.end();
    }
}
