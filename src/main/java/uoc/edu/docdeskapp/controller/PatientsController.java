package uoc.edu.docdeskapp.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uoc.edu.docdeskapp.config.CustomUserDetails;
import uoc.edu.docdeskapp.dto.PatientDTO;
import uoc.edu.docdeskapp.entity.PacienteEntity;
import uoc.edu.docdeskapp.services.PatientService;

import java.util.List;

@Controller
public class PatientsController {

    private static final Logger logger = LoggerFactory.getLogger(PatientsController.class);

    @Autowired
    private PatientService patientService;

    @RequestMapping("/patients")
    public String patients(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.debug("|||||||||| " + ((CustomUserDetails) principal).getFullname());
        if (principal instanceof UserDetails) {
            String username = ((CustomUserDetails) principal).getFullname();
            model.addAttribute("username", username);
        } else {
            model.addAttribute("username", "Guest");
        }

        List<PacienteEntity> patients = patientService.findAll();
        model.addAttribute("patients", patients);

        return "patients";
    }

    @RequestMapping("/patients/delete/{id}")
    public String deletePatient(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            patientService.deletePatientById(id);
            redirectAttributes.addAttribute("successMessage", "Patient deleted successfully.");
        } catch (Exception e) {
            redirectAttributes.addAttribute("errorMessage", "Error deleting patient.");
        }
        return "redirect:/patients";
    }

    @RequestMapping("/patients/create")
    public String createPatient(@Valid PatientDTO patientDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> logger.debug(error.toString()));
            redirectAttributes.addAttribute("errorMessage", "Error creating patient. Errors in fields.");
            return "redirect:/patients";
        }

        try {
            patientService.save(patientDTO);
            redirectAttributes.addAttribute("successMessage", "Patient created successfully.");
        } catch (Exception e) {
            redirectAttributes.addAttribute("errorMessage", "Error creating patient. " + e.getMessage());
        }

        return "redirect:/patients";
    }
}
