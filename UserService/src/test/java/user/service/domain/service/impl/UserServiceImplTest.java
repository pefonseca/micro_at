package user.service.domain.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import user.service.domain.model.dto.UserDTO;
import user.service.domain.model.dto.UserRequestDTO;
import user.service.infra.feign.LocationFeignClient;
import user.service.infra.feign.WeatherFeignClient;
import user.service.infra.feign.response.LocationResponseDTO;
import user.service.infra.feign.response.WeatherResponseDTO;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private LocationFeignClient locationFeignClient;

    @Mock
    private WeatherFeignClient weatherFeignClient;

    @Test
    void createUserTest() {
        UserRequestDTO requestDTO = new UserRequestDTO("11133322200", "São Paulo");
        WeatherResponseDTO weatherResponse = new WeatherResponseDTO(10, "Sunny");
        LocationResponseDTO locationResponse = new LocationResponseDTO(1L, "11122233300","São Paulo");

        when(weatherFeignClient.findWeatherByCity(any())).thenReturn(ResponseEntity.ok(weatherResponse));
        when(locationFeignClient.create(any())).thenReturn(ResponseEntity.ok(locationResponse));

        UserDTO result = userService.create(requestDTO);

        assertNotNull(result);
        assertEquals("11133322200", result.getCpf());
        assertEquals("São Paulo", result.getCity());
        assertEquals("Sunny", result.getWeather().getCondition());
        assertEquals(10, result.getWeather().getTemperature());
        assertEquals(Long.valueOf(1), result.getId());
    }
}