package com.casumo.orders;

import com.casumo.bet.events.entity.BetInfo;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import java.util.UUID;

import static com.jayway.restassured.RestAssured.given;

public class TestClient {


    @BeforeClass
    public static void setupURL() {
        // here we setup the default URL and API base path to use throughout the tests
        RestAssured.baseURI = "http://localhost:8080";
//        RestAssured.basePath = "/api/yourapi/";
    }

    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        int timeout = 5000;
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(timeout)
                .setConnectionRequestTimeout(timeout)
                .setSocketTimeout(timeout)
                .build();
        CloseableHttpClient client = HttpClientBuilder
                .create()
                .setDefaultRequestConfig(config)
                .build();
        return new HttpComponentsClientHttpRequestFactory(client);
    }



    @Test
    public void postOrder() {
        BetInfo order = new BetInfo();
        order.setId(UUID.randomUUID());
        Response response = given().when()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(order)
                .post("/orders")
                .then().statusCode(202).extract().response();

        System.out.println("response.toString() = " + response.asString());
    }


}
