package uoc.edu.docdeskapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uoc.edu.docdeskapp.dto.UserDto;
import uoc.edu.docdeskapp.entity.UsuarioEntity;

public interface UserRepository extends JpaRepository<UsuarioEntity, Long> {

    UsuarioEntity findByUsername(String username);

    //UsuarioEntity save(UsuarioEntity usuarioEntity);
}
