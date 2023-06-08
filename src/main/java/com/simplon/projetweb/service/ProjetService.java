package com.simplon.projetweb.service;

import com.simplon.projetweb.model.Projet;
import com.simplon.projetweb.repository.ProjetProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class ProjetService {
    @Autowired
    private ProjetProxy projetProxy;

    public Projet getProjet(final long idProjet) {
        return projetProxy.getProjet(idProjet);
    }

    public Iterable<Projet> getProjets() {
        return projetProxy.getProjets();
    }

    public void deleteProjet(final long idProjet) {
        projetProxy.deleteProjet(idProjet);
    }

    public Projet saveProjet(Projet projet) {
        Projet result;
        // Règle de gestion pour mettre le nom du projet en majuscules
        projet.setNomProjet(projet.getNomProjet().toUpperCase());

        if (projet.getIdProjet() == null) {
            // si l'id est nul, alors c'est un nouveau projet
            result = projetProxy.createProjet(projet);
        } else {
            result = projetProxy.updateProjet(projet);
        }
        return result;
    }


}
