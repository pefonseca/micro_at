package location.service.domain.service.impl;

import location.service.domain.model.dto.LocationDTO;
import location.service.domain.model.dto.LocationRequestDTO;
import location.service.domain.service.LocationService;
import location.service.infra.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository repository;

    @Override
    public LocationDTO create(LocationRequestDTO requestDTO) {
        return repository.save(requestDTO.toEntity()).toDTO();
    }

    @Override
    public LocationDTO findByCpf(String cpf) {
        return repository.findByCpf(cpf).toDTO();
    }
}
