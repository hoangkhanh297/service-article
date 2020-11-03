package com.khanh;

import com.khanh.dao.MySqlClientImpl;
import com.khanh.entities.City;
import com.khanh.utils.JSONUtils;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;

public class Main {

    public static void main(String[] args) {
/*        System.setProperty("log4j.configurationFile", "C:\\Users\\Khanh Hoang\\service-article\\service-dispatcher\\src\\main\\log4j2.xml");

        Vertx vertx = Vertx.vertx();
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);
        server.requestHandler(router).listen(8080);*/
/*        router.route("/service").handler(request -> {
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
        });*/

        String SQL = "SELECT * FROM world.city where ID = ? OR ID = ?";
        System.out.println("Start HTTP server OK");
        new MySqlClientImpl().queryWithParams(SQL, new JsonArray().add(1).add(2), City.class, done->{
            done.toString();
        });

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
