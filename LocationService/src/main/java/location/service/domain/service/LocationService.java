package location.service.domain.service;

import location.service.domain.model.dto.LocationDTO;
import location.service.domain.model.dto.LocationRequestDTO;

public interface LocationService {

    LocationDTO create(LocationRequestDTO requestDTO);
    LocationDTO findByCpf(String cpf);

}
