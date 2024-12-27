package uoc.edu.docdeskapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uoc.edu.docdeskapp.entity.RolEntity;

public interface RoleRepository extends JpaRepository<RolEntity, Long> {

    RolEntity findByIdRol(Long idRol);
}
