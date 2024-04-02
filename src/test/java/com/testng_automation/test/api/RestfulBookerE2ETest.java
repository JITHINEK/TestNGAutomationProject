package com.testng_automation.test.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.testng_automatio.api.dao.BookingData;
import com.testng_automatio.api.dao.DataBuilder;
import com.testng_automatio.api.dao.TokenCred;



public class RestfulBookerE2ETest extends APIBaseTest {

        private BookingData newBooking;
        private BookingData updateBooking;
        private BookingData partialBooking;
        private TokenCred tokenCred;

        private String token;
        private int bookingId;

        @BeforeTest
        public void testSetup() {

                DataBuilder builder = new DataBuilder();
                newBooking = builder.bookingDataBuilder();
                updateBooking = builder.bookingDataBuilder();
                partialBooking = builder.partialBookingDataBuilder();
                tokenCred = builder.tokenCredBuilder();

        }

        @Test(description = "Restful Booker API: Generate token with valid credentials.")
        public void generateToken() {

                System.out.println(tokenCred);
                token = given()
                                .body(tokenCred)
                                .when()
                                .post("/auth")
                                .then()
                                .statusCode(200)
                                .assertThat()
                                .body("token", notNullValue())
                                .extract().path("token");
        }

        @Test(description = "Restful Booker API: create booking test", enabled = true)
        public void createBookingTest() {

                bookingId = given()
                                .body(newBooking)
                                .when()
                                .post("/booking")
                                .then()
                                .statusCode(200)
                                .and()
                                .assertThat()
                                .body("bookingid", notNullValue(),
                                                "booking.firstname", equalTo(newBooking.getFirstname()),
                                                "booking.lastname", equalTo(newBooking.getLastname()),
                                                "booking.totalprice", equalTo(newBooking.getTotalprice()),
                                                "booking.depositpaid", equalTo(newBooking.isDepositpaid()),
                                                "booking.bookingdates.checkin",
                                                equalTo(newBooking.getBookingdates().getCheckin()),
                                                "booking.bookingdates.checkout",
                                                equalTo(newBooking.getBookingdates().getCheckout()),
                                                "booking.additionalneeds", equalTo(newBooking.getAdditionalneeds()))
                                .extract()
                                .path("bookingid");
        }

        @Test(description = "Restful Booker API: update booking test", dependsOnMethods = { "createBookingTest",
                        "generateToken" })
        public void updateBookingTest() {
                System.out.println(updateBooking);
                given()
                                .body(updateBooking)
                                .header("Cookie", "token=" + token)
                                .when()
                                .put("/booking/" + bookingId)
                                .then()
                                .statusCode(200)
                                .log().all()
                                .and()
                                .assertThat()
                                .body("firstname", equalTo(updateBooking.getFirstname()),
                                                "lastname", equalTo(updateBooking.getLastname()),
                                                "totalprice", equalTo(updateBooking.getTotalprice()));

        }

        
}

