package location.service.api.controller;

import location.service.domain.model.dto.LocationDTO;
import location.service.domain.model.dto.LocationRequestDTO;
import location.service.domain.service.LocationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationControllerTest {

    @InjectMocks
    private LocationController controller;
    @Mock
    private LocationService service;

    @Test
    void findByUserIdTest() {
        when(service.findByCpf(anyString())).thenReturn(LocationDTO.builder().build());
        assertNotNull(controller.findByUserId("11133322200"));
    }

    @Test
    void createTest() {
        when(service.create(any())).thenReturn(LocationDTO.builder().build());
        assertNotNull(controller.create(LocationRequestDTO.builder().build()));
    }

}