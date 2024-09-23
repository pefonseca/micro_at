package user.service.domain.service;

import user.service.domain.model.dto.UserDTO;
import user.service.domain.model.dto.UserRequestDTO;

public interface UserService {

    UserDTO create(UserRequestDTO userRequestDTO);

}
