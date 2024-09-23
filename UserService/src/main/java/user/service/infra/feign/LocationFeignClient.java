package user.service.infra.feign;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import user.service.infra.feign.request.LocationRequestDTO;
import user.service.infra.feign.response.LocationResponseDTO;

@Component
@FeignClient(name = "location-service", url = "http://localhost:8081")
public interface LocationFeignClient {

    @PostMapping(value = "/api/v1/location", produces = "application/json")
    ResponseEntity<LocationResponseDTO> create(@Valid @RequestBody LocationRequestDTO requestDTO);

}
