package com.testng_automatio.api.dao;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenCred {

    private String username;
    private String password;

}
