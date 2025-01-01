package uoc.edu.docdeskapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uoc.edu.docdeskapp.entity.RolEntity;
import uoc.edu.docdeskapp.repositories.RoleRepository;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<RolEntity> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public RolEntity findByIdRol(Long idRol) {
        return roleRepository.findByIdRol(idRol);
    }
}
