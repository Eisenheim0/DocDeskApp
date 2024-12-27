package uoc.edu.docdeskapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uoc.edu.docdeskapp.dto.UserDto;
import uoc.edu.docdeskapp.entity.UsuarioEntity;

import java.util.List;

public interface UserRepository extends JpaRepository<UsuarioEntity, Long> {

    UsuarioEntity findByUsername(String username);
}
