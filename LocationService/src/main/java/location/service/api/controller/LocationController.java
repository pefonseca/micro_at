package location.service.api.controller;

import jakarta.validation.Valid;
import location.service.domain.model.dto.LocationDTO;
import location.service.domain.model.dto.LocationRequestDTO;
import location.service.domain.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/location")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService service;

    @GetMapping("/{cpf}")
    public ResponseEntity<LocationDTO> findByUserId(@PathVariable(value = "cpf") String cpf) {
        var location = service.findByCpf(cpf);
        return Objects.isNull(location) ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(null) : ResponseEntity.ok(location);
    }

    @PostMapping
    public ResponseEntity<LocationDTO> create(@Valid @RequestBody LocationRequestDTO requestDTO) {
        var location = service.create(requestDTO);
        return Objects.isNull(location) ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(null) : ResponseEntity.status(HttpStatus.CREATED).body(location);
    }
}
