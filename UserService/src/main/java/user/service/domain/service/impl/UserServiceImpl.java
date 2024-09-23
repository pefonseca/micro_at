package user.service.domain.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import user.service.domain.model.dto.UserDTO;
import user.service.domain.model.dto.UserRequestDTO;
import user.service.domain.service.UserService;
import user.service.infra.feign.LocationFeignClient;
import user.service.infra.feign.WeatherFeignClient;
import user.service.infra.feign.request.LocationRequestDTO;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final LocationFeignClient locationFeignClient;
    private final WeatherFeignClient weatherFeignClient;

    @Override
    public UserDTO create(UserRequestDTO userRequestDTO) {

        var weather = weatherFeignClient.findWeatherByCity(userRequestDTO.getCity()).getBody();

        var location = locationFeignClient.create(LocationRequestDTO.builder()
                                                                    .cpf(userRequestDTO.getCpf())
                                                                    .city(userRequestDTO.getCity())
                                                                    .build()).getBody();

        return UserDTO.builder()
                      .id(Objects.requireNonNull(location).getId())
                      .cpf(userRequestDTO.getCpf())
                      .weather(weather)
                      .city(Objects.requireNonNull(location).getCity())
                      .build();
    }
}
