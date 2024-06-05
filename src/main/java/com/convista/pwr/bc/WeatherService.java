package com.convista.pwr.bc;

import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    public int readCurrentTemperature() {
        return (int) (Math.random() * 20) + 16;
    }
}
