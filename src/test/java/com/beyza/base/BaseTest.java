package com.beyza.base;

import com.beyza.config.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.filter.log.LogDetail;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    @BeforeEach
    void setup() {
        RestAssured.baseURI = ConfigReader.get("base.url");

        RestAssured.config = RestAssured.config()
                .logConfig(LogConfig.logConfig()
                        .enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL));
    }
}