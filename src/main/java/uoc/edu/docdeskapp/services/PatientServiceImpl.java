package uoc.edu.docdeskapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uoc.edu.docdeskapp.dto.PatientDTO;
import uoc.edu.docdeskapp.entity.PacienteEntity;
import uoc.edu.docdeskapp.repositories.PatientRepository;

import java.sql.Date;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    PatientRepository patientRepository;

    @Override
    public List<PacienteEntity> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public void deletePatientById(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new RuntimeException("Patient not found with ID: " + id);
        }
        patientRepository.deleteById(id);
    }

    @Override
    public PacienteEntity save(PatientDTO patientDTO) {
        return patientRepository.save(new PacienteEntity(patientDTO.getNombre(), patientDTO.getApellido(), new Date(patientDTO.getFechaNacimiento().getTime()), patientDTO.getDireccion(), patientDTO.getTelefono()));
    }

    public PacienteEntity findById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with ID: " + id));
    }
}
