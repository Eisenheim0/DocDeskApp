package uoc.edu.docdeskapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import uoc.edu.docdeskapp.dto.UserDto;
import uoc.edu.docdeskapp.entity.RolEntity;
import uoc.edu.docdeskapp.entity.UsuarioEntity;
import uoc.edu.docdeskapp.repositories.UserRepository;

public class UserServiceImpl implements UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UsuarioEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UsuarioEntity save(UserDto userDto) {
        RolEntity rolEntity = new RolEntity();
        UsuarioEntity usuarioEntity = new UsuarioEntity(userDto.getUsername(), userDto.getNombre(), userDto.getApellido(), userDto.getEmail(), userDto.getPassword(), rolEntity);
        return userRepository.save(usuarioEntity);
    }
}
