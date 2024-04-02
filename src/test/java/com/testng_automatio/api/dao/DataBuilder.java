package com.testng_automatio.api.dao;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import com.github.javafaker.Faker;

public class DataBuilder {

    public BookingData bookingDataBuilder() {

        Faker faker = new Faker();
        SimpleDateFormat formater = new SimpleDateFormat("YYYY-MM-dd");
        
        return BookingData.builder()
                .firstname(faker.name().firstName())
                .lastname(faker.name().lastName())
                .totalprice(faker.number().numberBetween(1, 2000))
                .depositpaid(true)
                .bookingdates(BookingDates.builder()
                        .checkin(formater.format(faker.date().past(20, TimeUnit.DAYS)))
                        .checkout(formater.format(faker.date().future(5, TimeUnit.DAYS)))
                        .build())
                .additionalneeds("Breadfast")
                .build();
    }

    public BookingData partialBookingDataBuilder() {
        Faker faker = new Faker();
        return BookingData.builder()
                .firstname(faker.name().firstName())
                .lastname(faker.name().lastName())
                .totalprice(faker.number().numberBetween(100, 5000))
                .build();
    }

    public TokenCred tokenCredBuilder() {

        return TokenCred.builder()
                .username("admin")
                .password("password123")
                .build();
    }

}

