package com.simplon.projetweb.controller;

import com.simplon.projetweb.model.Projet;
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
    @Autowired
    private ProjetService projetService;

    @GetMapping("/")
    public String index(Model model) {
        Iterable<Projet> listProjet = projetService.getProjets();
        model.addAttribute("projets", listProjet);
        return "index";
    }

    @GetMapping("/createProjet")
    public String createProjet(Model model) {
        Projet projet = new Projet();
        model.addAttribute("projet", projet);
        return "formNouveauProjet";
    }

    @GetMapping("/updateProjet/{idProjet}")
    public String updateProjet(@PathVariable("idProjet") final int idProjet, Model model) {
        Projet projet = projetService.getProjet(idProjet);
        model.addAttribute("projet", projet);
        return "formNouveauProjet";
    }

    @GetMapping("/deleteProjet/{idProjet}")
    public ModelAndView deleteProjet(@PathVariable("idProjet") final int idProjet) {
        projetService.deleteProjet(idProjet);
        return new ModelAndView("redirect:/");
    }

    @PostMapping("/saveProjet")
    public ModelAndView saveProjet(@ModelAttribute Projet projet) {
        if (projet.getIdProjet() != null) {
            // Si l'ID du projet n'est pas nul, cela signifie qu'il s'agit d'une mise à jour d'un projet existant

            // Utiliser la méthode updateProjet de ProjetService pour mettre à jour le projet
            Projet updatedProjet = projetService.updateProjet(projet);

            // Créer un ModelAndView pour afficher le projet mis à jour
            ModelAndView modelAndView = new ModelAndView("projet-details");
            modelAndView.addObject("projet", updatedProjet);
            return modelAndView;
        } else {
            // Si l'ID du projet est nul, cela signifie qu'il s'agit de la création d'un nouveau projet

            // Utiliser la méthode createProjet de ProjetService pour créer le projet
            Projet createdProjet = projetService.createProjet(projet);

            // Rediriger vers la page de liste des projets après la création
            return new ModelAndView("redirect:/");
        }
    }
}
