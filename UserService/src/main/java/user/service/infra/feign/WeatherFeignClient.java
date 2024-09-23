package user.service.infra.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import user.service.infra.feign.response.WeatherResponseDTO;

@Component
@FeignClient(name = "weather-service", url = "http://localhost:8080")
public interface WeatherFeignClient {

    @GetMapping(value = "/api/v1/weather", produces = "application/json")
    ResponseEntity<WeatherResponseDTO> findWeatherByCity(@RequestParam(value = "city") String city);

}
