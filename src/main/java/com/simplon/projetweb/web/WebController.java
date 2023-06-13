package com.simplon.projetweb.web;

import com.simplon.projetweb.model.Projet;
import com.simplon.projetweb.model.Vote;
import com.simplon.projetweb.repository.ProjetProxy;
import com.simplon.projetweb.repository.VoteProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@Data
public class WebController {

    private ProjetProxy mProjetProxy;
    private VoteProxy mVoteProxy;
    private Long idProjet;

    @Autowired
    public WebController(ProjetProxy pProjetProxy, VoteProxy pVoteProxy) {
        mProjetProxy = pProjetProxy;
        mVoteProxy = pVoteProxy;
    }

    @GetMapping(path = {"/", "/index", "/index.html"})
    public String index(@RequestParam(required = false, defaultValue = "0") Integer page, Model model) {
        model.addAttribute("nouveauProjet", new Projet());
        return "index";
    }

    @GetMapping(path = "/votes/{idProjet}")
    public String votes(@PathVariable Long idProjet, Model model) {
        this.idProjet = idProjet;
        Optional<Projet> projet = mProjetProxy.findByIdProjet(idProjet);
        model.addAttribute("projet", projet.get());

        List<Vote> votes = mVoteProxy.findByIdProjet(idProjet);
        model.addAttribute("votes", votes);
        return "votes";
    }

    @GetMapping(path = "/fragments/projets")
    public String fragmentProjets(@RequestParam(required = false, defaultValue = "0") Integer page, Model model) {
        fillModelWithPaginationAttributes(model, page);
        return "index :: all-projets";
    }

    @GetMapping(path = "/fragments/projets/{idProjet}")
    public String fragmentProjet(@PathVariable Long idProjet, Model model) {
        Optional<Projet> projet = mProjetProxy.findByIdProjet(idProjet);
        model.addAttribute("projet", projet.get());
        return "fragment-projet :: single-projet";
    }

    private void fillModelWithPaginationAttributes(Model model, int page) {
        Page<Projet> all = getProjets(page);


        model.addAttribute("projets", all.getContent());
        model.addAttribute("page", page);

    }

    private Page<Projet> getProjets(int page) {
        try {

            return (Page<Projet>) mProjetProxy.getProjets();
        } catch (NullPointerException e) {
            e.printStackTrace();
            return Page.empty();
        }
    }
}


