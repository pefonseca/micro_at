package weather.service.domain.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import weather.service.domain.model.dto.WeatherDTO;
import weather.service.domain.service.WeatherService;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private static final Map<String, WeatherDTO> weatherData = new HashMap<>();
    private static final Random random = new Random();

    private static final String[] conditions = {
            "Ensolarado", "Parcialmente nublado", "Chuvoso", "Tempestade",
            "Neblina", "Frio", "Quente", "Ventos fortes", "Garoa", "Céu limpo"
    };

    static {
        String[] capitals = {
                "Rio Branco", "Maceió", "Macapá", "Manaus", "Salvador", "Fortaleza",
                "Brasília", "Vitória", "Goiânia", "São Luís", "Cuiabá", "Campo Grande",
                "Belo Horizonte", "Belém", "João Pessoa", "Curitiba", "Recife",
                "Teresina", "Rio de Janeiro", "Natal", "Porto Alegre", "Boa Vista",
                "Florianópolis", "São Paulo", "Aracaju", "Palmas"
        };

        for (String capital : capitals) {
            int temperature = random.nextInt(26) + 15;

            String condition = conditions[random.nextInt(conditions.length)];

            weatherData.put(capital, new WeatherDTO(temperature, condition));
        }
    }


    @Override
    public WeatherDTO findWeather(String city) {
        return weatherData.get(city);
    }
}
