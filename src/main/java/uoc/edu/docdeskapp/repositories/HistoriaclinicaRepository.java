package uoc.edu.docdeskapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uoc.edu.docdeskapp.entity.HistoriaclinicaEntity;
import uoc.edu.docdeskapp.entity.PacienteEntity;

public interface HistoriaclinicaRepository extends JpaRepository<HistoriaclinicaEntity, Long> {

    HistoriaclinicaEntity findByPaciente(PacienteEntity paciente);
}
