package com.casumo.bet;

import com.casumo.bet.events.entity.BetPlaced;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.kafka.test.rule.KafkaEmbedded;

import java.util.UUID;

import static com.jayway.restassured.RestAssured.given;

public class TestBet {

    @ClassRule
    public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, "topic");

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
    public void postBet() {
        BetPlaced order = new BetPlaced();
        order.setId(UUID.randomUUID());

        Response response = given().when()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(order)
                .post("/bets")
                .then().statusCode(202).extract().response();

        System.out.println("response.toString() = " + response.asString());
    }


}
