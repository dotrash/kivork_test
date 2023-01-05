package org.example.test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.example.MyCustomResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;

public class FreeGeoIpTest {

    private static final String BASE_URI = "http://api.ipstack.com";
    private static final String ACCESS_KEY = "access_key";
    private static final String ACCESS_KEY_VALUE = "430a6402182feac504456df4a82353ee";
    private static final double TOLERANCE = 0.01;
    private static final double EXPECTED_LATITUDE = 48.34;
    private static final double EXPECTED_LONGITUDE = 34.98;

    @Test
    public void geoIpTest() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();

        RequestSpecification requestSpecification = requestSpecBuilder
                .setBaseUri(BASE_URI)
                .addQueryParam(ACCESS_KEY, ACCESS_KEY_VALUE)
                .build();

        MyCustomResponse response = given(requestSpecification)
                .when()
                .get("/46.98.150.167")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().body()
                .extract()
                .response().as(MyCustomResponse.class);

        assertThat(response.getLatitude(), closeTo(EXPECTED_LATITUDE, TOLERANCE));
        assertThat(response.getLongitude(), closeTo(EXPECTED_LONGITUDE, TOLERANCE));
    }
}
