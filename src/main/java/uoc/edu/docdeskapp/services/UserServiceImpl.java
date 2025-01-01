package uoc.edu.docdeskapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uoc.edu.docdeskapp.dto.UserDto;
import uoc.edu.docdeskapp.entity.RolEntity;
import uoc.edu.docdeskapp.entity.UsuarioEntity;
import uoc.edu.docdeskapp.repositories.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Override
    public UsuarioEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UsuarioEntity save(UserDto userDto) {
        RolEntity rolEntity = roleService.findByIdRol((long) userDto.getIdRol());
        UsuarioEntity usuarioEntity = new UsuarioEntity(userDto.getUsername(), userDto.getNombre(), userDto.getApellido(), userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()), rolEntity);
        return userRepository.save(usuarioEntity);
    }

    @Override
    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public List<UsuarioEntity> findAll() {
        return userRepository.findAll();
    }
}
