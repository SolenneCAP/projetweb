package com.simplon.projetweb.controller;

import com.simplon.projetweb.model.Projet;
import com.simplon.projetweb.model.Vote;
import com.simplon.projetweb.service.ProjetService;
import com.simplon.projetweb.service.VoteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class VoteController {
    private final VoteService voteService;
    private final ProjetService projetService;

    @Autowired
    public VoteController(VoteService voteService, ProjetService projetService) {
        this.voteService = voteService;
        this.projetService = projetService;
    }

    @GetMapping("/votes/{projetId}")
    public String votesByProjet(@PathVariable Long projetId, Model model) {
        Optional<Projet> projetOptional = Optional.ofNullable(projetService.getProjet(projetId));
        if (projetOptional.isPresent()) {
            Projet projet = projetOptional.get();
            model.addAttribute("projet", projet);
            model.addAttribute("votes", projet.getVotes());
            return "votes";
        } else {
            throw new EntityNotFoundException("Le projet avec l'ID " + projetId + " n'existe pas.");
        }
    }

    @GetMapping("/votes/{projetId}/createVote")
    public String createVote(@PathVariable Long projetId, Model model) {
        Optional<Projet> projetOptional = Optional.ofNullable(projetService.getProjet(projetId));
        if (projetOptional.isPresent()) {
            Projet projet = projetOptional.get();
            Vote vote = new Vote();
            model.addAttribute("projet", projet);
            model.addAttribute("vote", vote);
            return "createVote";
        } else {
            throw new EntityNotFoundException("Le projet avec l'ID " + projetId + " n'existe pas.");
        }
    }

    @PostMapping("/votes/{projetId}/saveVote")
    public String saveVote(@PathVariable Long projetId, @ModelAttribute Vote vote) {
        Optional<Projet> projetOptional = Optional.ofNullable(projetService.getProjet(projetId));
        if (projetOptional.isPresent()) {
            Projet projet = projetOptional.get();
            vote.setProjet(projet);
            voteService.saveVote(vote);
            return "redirect:/votes/" + projetId;
        } else {
            throw new EntityNotFoundException("Le projet avec l'ID " + projetId + " n'existe pas.");
        }
    }

    @GetMapping("/votes/{projetId}/deleteVote/{voteId}")
    public String deleteVote(@PathVariable Long projetId, @PathVariable Long voteIdVote) {
        voteService.deleteVote(voteIdVote);
        return "redirect:/votes/" + projetId;
    }
}
