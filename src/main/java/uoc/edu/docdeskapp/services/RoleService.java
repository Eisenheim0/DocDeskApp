package uoc.edu.docdeskapp.services;

import uoc.edu.docdeskapp.entity.RolEntity;

import java.util.List;

public interface RoleService {

    List<RolEntity> findAll();

    RolEntity findByIdRol(Long idRol);
}
