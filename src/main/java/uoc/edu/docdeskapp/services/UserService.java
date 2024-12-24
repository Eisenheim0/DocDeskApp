package uoc.edu.docdeskapp.services;

import uoc.edu.docdeskapp.dto.UserDto;
import uoc.edu.docdeskapp.entity.UsuarioEntity;

public interface UserService {

    UsuarioEntity findByUsername(String username);

    UsuarioEntity save(UserDto userDto);
}
