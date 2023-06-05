package com.simplon.projetweb.repository;

import com.simplon.projetweb.service.ProjetService;
import org.springframework.http.ResponseEntity;
import com.simplon.projetweb.CustomProperties;
import com.simplon.projetweb.model.Projet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class ProjetProxy {
    @Autowired
    private CustomProperties props;
    private ProjetService projetService;

    /**
     * Avoir tous les projets
     * @return Un iterable de tous les projets
     */
    public Iterable<Projet> getProjets() {
        String baseApiUrl = props.getApiUrl();
        String getProjetsUrl = baseApiUrl + "/index";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Projet>> response = restTemplate.exchange(
                getProjetsUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Projet>>() {
                }
        );
        log.debug("Get projets call " + response.getStatusCode().toString());
        return response.getBody();
    }
    public Projet getProjet(int idProjet) {
        // Implémentation de la logique pour récupérer un projet en fonction de son identifiant
        // Retourner le projet correspondant
        return projetService.getProjet(idProjet);
    }

    public void deleteProjet(int idProjet) {

    }

    public Projet createProjet(Projet projet) {
        return projet;
    }

    public Projet updateProjet(Projet projet) {
        return null;
    }
}
