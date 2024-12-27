package uoc.edu.docdeskapp.services;

import uoc.edu.docdeskapp.dto.UserDto;
import uoc.edu.docdeskapp.entity.UsuarioEntity;

import java.util.List;

public interface UserService {

    UsuarioEntity findByUsername(String username);

    UsuarioEntity save(UserDto userDto);

    void deleteUserById(Long id);

    List<UsuarioEntity> findAll();
}
