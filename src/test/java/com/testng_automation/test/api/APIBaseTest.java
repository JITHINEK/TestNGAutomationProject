package com.testng_automation.test.api;

import static org.hamcrest.Matchers.lessThan;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class APIBaseTest {

    @BeforeClass
    public void setUp() {

        System.out.println("Setting up RestAssured...");

        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();

        ResponseSpecification responseSpecification = new ResponseSpecBuilder()
                .expectResponseTime(lessThan(20000L))
                .build();

        RestAssured.requestSpecification = requestSpecification;
        RestAssured.responseSpecification = responseSpecification;

        System.out.println("Base URI set to: " + RestAssured.baseURI);
    }

}
