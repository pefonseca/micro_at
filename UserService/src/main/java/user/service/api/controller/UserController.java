package user.service.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import user.service.domain.model.dto.UserDTO;
import user.service.domain.model.dto.UserRequestDTO;
import user.service.domain.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        var user = service.create(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
