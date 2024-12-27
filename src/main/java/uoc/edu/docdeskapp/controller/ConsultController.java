package uoc.edu.docdeskapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uoc.edu.docdeskapp.entity.PacienteEntity;
import uoc.edu.docdeskapp.services.PatientService;

@Controller
public class ConsultController {

    private static final Logger logger = LoggerFactory.getLogger(ConsultController.class);

    @Autowired
    PatientService patientService;

    @RequestMapping("/consult")
    public String consultIdPaciente(@RequestParam(required = false) Long idPaciente, Model model) {
        PacienteEntity pacienteEntity = null;

        if (idPaciente != null) {
            logger.debug("|||||||||| id: " + idPaciente);
            pacienteEntity = patientService.findById(idPaciente);
        }

        model.addAttribute("paciente", pacienteEntity);

        return "consult";
    }
}
