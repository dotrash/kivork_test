package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.example.utils.DoubleConverter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MyCustomResponse {
    private Float latitude;
    private Float longitude;

    public Double getLatitude() {
        return DoubleConverter.convertToDouble(latitude);
    }

    public Double getLongitude() {
        return DoubleConverter.convertToDouble(longitude);
    }
}
