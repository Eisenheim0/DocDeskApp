package uoc.edu.docdeskapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uoc.edu.docdeskapp.entity.ConsultaEntity;
import uoc.edu.docdeskapp.entity.PacienteEntity;

import java.util.List;

public interface ConsultRepository extends JpaRepository<ConsultaEntity, Long> {
    List<ConsultaEntity> findByPacienteOrderByFechaCreacionDesc(PacienteEntity pacienteEntity);
}
