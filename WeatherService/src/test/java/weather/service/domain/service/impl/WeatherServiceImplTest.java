package weather.service.domain.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import weather.service.domain.model.dto.WeatherDTO;

import static org.junit.jupiter.api.Assertions.*;

class WeatherServiceImplTest {

    private WeatherServiceImpl weatherService;

    @BeforeEach
    void setUp() {
        weatherService = new WeatherServiceImpl();
    }

    @Test
    void findWeather() {
        String city = "São Paulo";

        WeatherDTO weatherDTO = weatherService.findWeather(city);
        assertNotNull(weatherDTO);
        assertTrue(weatherDTO.getTemperature() >= 15 && weatherDTO.getTemperature() <= 40);
        assertNotNull(weatherDTO.getCondition());
    }
}
