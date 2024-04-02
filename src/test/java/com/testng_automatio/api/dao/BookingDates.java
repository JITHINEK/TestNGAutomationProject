package com.testng_automatio.api.dao;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingDates {

    private String checkin;
    private String checkout;
}
