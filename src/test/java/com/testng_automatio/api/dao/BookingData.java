package com.testng_automatio.api.dao;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingData {

    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private BookingDates bookingdates;
    private String additionalneeds;

}
