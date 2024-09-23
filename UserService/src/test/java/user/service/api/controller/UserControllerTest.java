package user.service.api.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import user.service.domain.model.dto.UserDTO;
import user.service.domain.model.dto.UserRequestDTO;
import user.service.domain.service.UserService;
import user.service.infra.feign.response.WeatherResponseDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserController controller;

    @Mock
    private UserService service;

    @Test
    void createUserTest() {
        UserRequestDTO requestDTO = new UserRequestDTO("12345678900", "São Paulo");
        UserDTO userDTO = new UserDTO(1L, "cpf", "cidade", new WeatherResponseDTO(10, "Frio"));

        when(service.create(any(UserRequestDTO.class))).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = controller.create(requestDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userDTO, response.getBody());
    }

}