package weather.service.app.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import weather.service.domain.model.dto.WeatherDTO;
import weather.service.domain.service.WeatherService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class WeatherControllerTest {

    @InjectMocks
    private WeatherController controller;

    @Mock
    private WeatherService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findWeatherByCity() {
        WeatherDTO weatherDTO = WeatherDTO.builder().build();
        when(service.findWeather(anyString())).thenReturn(weatherDTO);
        ResponseEntity<WeatherDTO> response = controller.findWeatherByCity("Manaus");

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(weatherDTO, response.getBody());
    }

    @Test
    void findWeatherByCity_null() {
        when(service.findWeather(anyString())).thenReturn(null);
        ResponseEntity<WeatherDTO> response = controller.findWeatherByCity("Manaus");
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

}