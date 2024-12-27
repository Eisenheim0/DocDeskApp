package uoc.edu.docdeskapp.services;

import uoc.edu.docdeskapp.dto.PatientDTO;
import uoc.edu.docdeskapp.entity.PacienteEntity;

import java.util.List;

public interface PatientService {

    List<PacienteEntity> findAll();

    void deletePatientById(Long id);

    PacienteEntity save(PatientDTO patientDTO);

    PacienteEntity findById(Long id);
}
