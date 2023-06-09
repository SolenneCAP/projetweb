package com.simplon.projetweb.controller;

import com.simplon.projetweb.model.Projet;
import com.simplon.projetweb.repository.ProjetProxy;
import com.simplon.projetweb.service.ProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProjetController {
    private ProjetProxy mRepository;
    @Autowired
    private ProjetService projetService;

    private ProjetProxy projetProxy;


    @GetMapping({"/createProjet", "/nxprojet", "/nxprojet.html"})
    public String createProjet(Model model) {
        Projet e = new Projet();
        model.addAttribute("projet", e);
        return "nxprojet";
    }

    @GetMapping({"/updateProjet/{idProjet}", "/majprojet{idProjet}", "/majprojet{idProjet}.html"})
    public String updateProjet(@PathVariable("idProjet") final Long idProjet, Model model) {
        Projet e = projetService.getProjet(idProjet);
        model.addAttribute("projet", e);
        return "majprojet";
    }

    @GetMapping("/deleteProjet/{idProjet}")
    public ModelAndView deleteProjet(@PathVariable("idProjet") final Long idProjet) {
        projetService.deleteProjet(idProjet);
        return new ModelAndView("redirect:/");
    }

    @PostMapping("/saveProjet")
    public String saveProjet(@ModelAttribute Projet projet) {
        Projet result;

        // Règle de gestion pour mettre le nom du projet en majuscules
        projet.setNomProjet(projet.getNomProjet().toUpperCase());

        result = projetService.saveProjet(projet);

        // Redirection vers la page d'accueil
        return "redirect:/";
    }

}
