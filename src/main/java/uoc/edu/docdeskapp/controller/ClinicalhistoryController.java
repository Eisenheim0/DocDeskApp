package uoc.edu.docdeskapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uoc.edu.docdeskapp.config.CustomUserDetails;
import uoc.edu.docdeskapp.dto.HistoriaclinicaDTO;
import uoc.edu.docdeskapp.entity.PacienteEntity;
import uoc.edu.docdeskapp.services.HistoriaclinicaService;
import uoc.edu.docdeskapp.services.PatientService;

@Controller
public class ClinicalhistoryController {

    private static final Logger logger = LoggerFactory.getLogger(ClinicalhistoryController.class);

    @Autowired
    PatientService patientService;

    @Autowired
    HistoriaclinicaService historiaclinicaService;

    @RequestMapping("/clinicalhistory")
    public String clinicalHistory(@RequestParam(required = false) Long idPaciente, Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.debug("|||||||||| " + ((CustomUserDetails) principal).getFullname());
        if (principal instanceof UserDetails) {
            String username = ((CustomUserDetails) principal).getFullname();
            model.addAttribute("username", username);
        } else {
            model.addAttribute("username", "Guest");
        }

        PacienteEntity pacienteEntity = null;
        HistoriaclinicaDTO historiaclinicaDTO = null;
        if (idPaciente != null) {
            logger.debug("|||||||||| id: " + idPaciente);
            pacienteEntity = patientService.findById(idPaciente);
            historiaclinicaDTO = historiaclinicaService.findByPaciente(pacienteEntity);
        }
        model.addAttribute("paciente", pacienteEntity);
        model.addAttribute("historiaClinica", historiaclinicaDTO);

        return "clinicalhistory";
    }

    @PostMapping("/clinicalhistory/save")
    public String save(HistoriaclinicaDTO historiaclinicaDTO, RedirectAttributes redirectAttributes) {
        try {
            historiaclinicaService.save(historiaclinicaDTO);
            redirectAttributes.addAttribute("successMessage", "Clinical history saved successfully.");
        } catch (Exception e) {
            redirectAttributes.addAttribute("errorMessage", "Error saving clinical history. " + e.getMessage());
        }
        return "redirect:/clinicalhistory?idPaciente=" + historiaclinicaDTO.getIdPaciente();
    }
}
