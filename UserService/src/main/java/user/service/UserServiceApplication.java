package user.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import user.service.infra.feign.LocationFeignClient;
import user.service.infra.feign.WeatherFeignClient;

@SpringBootApplication
@EnableFeignClients(clients = {LocationFeignClient.class, WeatherFeignClient.class})
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

}
