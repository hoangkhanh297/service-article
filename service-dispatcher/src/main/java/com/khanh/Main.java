package com.khanh;

import com.khanh.conection.DatabaseConnection;
import com.khanh.entities.City;
import com.khanh.entities.Product;
import com.khanh.utils.JSONUtils;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final Logger log = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);
        server.requestHandler(router).listen(8080);
        router.route("/service").handler(request -> {
            System.out.println("New request");
            List<Product> products = new ArrayList<>();
            for (int i = 1; i < 100; i++) {
                Product product = new Product();
                product.setId(i);
                product.setAmount(i * 10000);
                product.setName("Ao " + i);
                product.setPicture("https://icons.iconarchive.com/icons/artdesigner/gentle-romantic/512/rose-icon.png");
                products.add(product);
            }
            sendResponse(request.response(), products, 200);
        });
        String SQL = "SELECT * FROM world.city";
        DatabaseConnection.getConnection().doQuery(SQL, City.class, done->{
            log.warn("Result : {}", done.get(0));
        });
        System.out.println("Start HTTP server OK");
    }

    private static void sendSuccessResponse(HttpServerResponse response, Object object) {
        sendResponse(response, object, 200);
    }

    private static void sendResponse(HttpServerResponse response, Object object, int statusCode) {
        response.setChunked(true)
                .putHeader("content-type", "application/json; charset=utf-8")
                .setStatusCode(statusCode)
                .write(JSONUtils.objToString(object), "UTF-8")
                .end();
    }
}
