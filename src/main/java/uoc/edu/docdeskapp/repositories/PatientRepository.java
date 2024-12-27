package uoc.edu.docdeskapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uoc.edu.docdeskapp.entity.PacienteEntity;

public interface PatientRepository extends JpaRepository<PacienteEntity, Long> {
}
