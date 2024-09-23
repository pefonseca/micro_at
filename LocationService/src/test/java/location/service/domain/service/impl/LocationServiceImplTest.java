package location.service.domain.service.impl;

import location.service.domain.model.dto.LocationDTO;
import location.service.domain.model.dto.LocationRequestDTO;
import location.service.domain.model.entity.Location;
import location.service.infra.repository.LocationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationServiceImplTest {

    @InjectMocks
    private LocationServiceImpl locationService;

    @Mock
    private LocationRepository repository;

    @Test
    void createTest() {
        LocationRequestDTO requestDTO = LocationRequestDTO.builder().cpf("11133322200").city("Some City").build();
        Location location = Location.builder().id(1L).cpf(requestDTO.getCpf()).city(requestDTO.getCity()).build();
        when(repository.save(any(Location.class))).thenReturn(location);
        LocationDTO result = locationService.create(requestDTO);

        assertNotNull(result);
        assertEquals(requestDTO.getCpf(), result.getCpf());
        assertEquals(requestDTO.getCity(), result.getCity());
    }

    @Test
    void findByCpfTest() {
        String cpf = "11133322200";
        Location location = new Location();
        when(repository.findByCpf(anyString())).thenReturn(location);
        LocationDTO result = locationService.findByCpf(cpf);
        assertNotNull(result);
    }
}