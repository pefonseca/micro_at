package weather.service.app.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import weather.service.domain.model.dto.WeatherDTO;
import weather.service.domain.service.WeatherService;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService service;

    @GetMapping
    public ResponseEntity<WeatherDTO> findWeatherByCity(@RequestParam(value = "city") String city) {
        var weather = service.findWeather(city);
        return Objects.isNull(weather) ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(null) : ResponseEntity.ok(weather);
    }
}
