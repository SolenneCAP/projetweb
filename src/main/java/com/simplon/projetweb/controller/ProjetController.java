package com.simplon.projetweb.controller;

import com.simplon.projetweb.model.Projet;
import com.simplon.projetweb.service.ProjetService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Data
@Controller
@RequestMapping("/")
public class ProjetController {

    private ProjetService projetService;

    @Autowired
    public ProjetController(ProjetService projetService) {
        this.projetService = projetService;
    }


    @GetMapping("/saveProjet")
    public String createProjet(Model model) {
        Projet e = new Projet();
        model.addAttribute("projet", e);
        return "index";
    }

    @GetMapping("/updateProjet/{id}")
    public String showUpdateProjetForm(@PathVariable("id") Long id, Model model) {
        Projet projet = projetService.getProjet(id);
        model.addAttribute("projet", projet);
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String deleteProjetById(@PathVariable("id") Long id) {
        projetService.deleteProjet(id);
        return "redirect:/";
    }

    @PostMapping("/saveProjet")
    public String saveProjet(@ModelAttribute("projet") Projet projet) {
        // Règle de gestion pour mettre le nom du projet en majuscules
        projet.setNomProjet(projet.getNomProjet().toUpperCase());

        projetService.saveProjet(projet);

        // Redirection vers la page d'accueil
        return ("redirect:/");
    }

}
