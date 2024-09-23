package weather.service.domain.service;

import weather.service.domain.model.dto.WeatherDTO;

public interface WeatherService {

    WeatherDTO findWeather(String city);

}
