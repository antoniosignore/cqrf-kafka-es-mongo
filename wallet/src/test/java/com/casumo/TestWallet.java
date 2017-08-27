package com.casumo;


import com.casumo.bet.events.entity.MoneyDeposit;
import com.casumo.bet.events.entity.MoneyWithdraw;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

public class TestWallet {


    @BeforeClass
    public static void setupURL() {
        RestAssured.baseURI = "http://localhost:8081";
//        RestAssured.basePath = "/api/yourapi/";
    }

    @Test
    public void getAllBalances() {
        Response response = given().when()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get("/wallets")
                .then().statusCode(200).extract().response();

        System.out.println("response.toString() = " + response.asString());
    }

    @Test
    public void getUserBalance() {
        Response response = given().when()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get("/wallets/pippo")
                .then().statusCode(200).extract().response();

        System.out.println("response.toString() = " + response.asString());
    }


    @Test
    public void addFunds() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        MoneyDeposit deposit = new MoneyDeposit();
        deposit.setUsername("pippo");
        deposit.setAmount(1000D);
        String s = mapper.writeValueAsString(deposit);

        Response response = given().when()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON).body(s)
                .post("/wallets/deposit")
                .then().statusCode(202).extract()
                .response();

        System.out.println("response.toString() = " + response.asString());
    }

    @Test
    public void withdrawFunds() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        MoneyWithdraw moneyWithdraw = new MoneyWithdraw();
        moneyWithdraw.setUsername("pippo");
        moneyWithdraw.setAmount(1000D);
        String s = mapper.writeValueAsString(moneyWithdraw);

        Response response = given().when()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON).body(s)
                .post("/wallets/withdraw")
                .then().statusCode(202).extract()
                .response();

        System.out.println("response.toString() = " + response.asString());
    }
}
