package com.simplon.projetweb.web;


import com.simplon.projetweb.model.Projet;
import com.simplon.projetweb.repository.ProjetProxy;
import com.simplon.projetweb.repository.VoteProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//@RequestMapping("/WEB-INF/views/")

@Controller
@Data
public class WebController {
    public static final int DEFAULT_PAGE_COUNT = 10;

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
        fillModelWithPaginationAttributes(model, page);
        model.addAttribute("nouveauProjet", new Projet());
        return "index";
    }

    @GetMapping(path = "/fragments/projets")
    public String fragmentProjets(@RequestParam(required = false, defaultValue = "0") Integer page, Model model) {
        fillModelWithPaginationAttributes(model, page);
        return "index :: all-projets";
    }

    @GetMapping(path = "/fragments/projets/{idProjet}")
    public String fragmentProjet(@PathVariable Long idProjet, Model model) {
        Optional<Projet> projet = mProjetProxy.findByIdProjet(idProjet);
        model.addAttribute("projet",
                projet.get());
        return "fragment-projet :: single-projet";
    }

    private void fillModelWithPaginationAttributes(Model model, int page) {
        long count = mProjetProxy.count();
        long pageCount = (count + DEFAULT_PAGE_COUNT - 1) / DEFAULT_PAGE_COUNT;
        List<Projet> all = getProjets(page);
        model.addAttribute("projets", all);
        model.addAttribute("page", page);
        model.addAttribute("pageCount", pageCount);
    }

    private List<Projet> getProjets(int page) {
        try {
            PageRequest pageable = PageRequest.of(page, DEFAULT_PAGE_COUNT, Sort.by("closLe").ascending());
            Page<Projet> all = mProjetProxy.findAll(pageable);

            if (all != null) {
                List<Projet> openProjets = all.filter(projet -> projet.getClosLe().isAfter(LocalDateTime.now()))
                        .stream().collect(Collectors.toList());
                List<Projet> closedProjets = all.filter(projet -> projet.getClosLe().isBefore(LocalDateTime.now()))
                        .stream().toList();
                openProjets.addAll(closedProjets);
                return openProjets;
            } else {
                // Traitement lorsque 'all' est null (ex: pas de projets trouvés)
                return Collections.emptyList(); // Retourner une liste vide
            }
        } catch (NullPointerException e) {
            // Gérer l'exception NullPointerException
            e.printStackTrace(); // Afficher la trace de l'exception (facultatif)
            return Collections.emptyList(); // Retourner une liste vide en cas d'exception
        }
    }

}

