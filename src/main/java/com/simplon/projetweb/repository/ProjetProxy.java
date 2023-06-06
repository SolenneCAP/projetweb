package com.simplon.projetweb.repository;

import com.simplon.projetweb.service.ProjetService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import com.simplon.projetweb.CustomProperties;
import com.simplon.projetweb.model.Projet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.lang.module.ModuleDescriptor;

@Slf4j
@Component
public class ProjetProxy {
    @Autowired
    private CustomProperties props;
    private ProjetService projetService;

    /**
     * Avoir tous les projets
     *
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

    /**
     * *Obtenier un Projet par l'id
     * @param idProjet - L'identifiant du Projet
     * @return Le projet qui correspond à l'idProjet
     */

    public Projet getProjet(long idProjet) {
        String baseApiUrl = props.getApiUrl();
        String getProjetUrl = baseApiUrl + "/projet" + idProjet;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Projet> response = restTemplate.exchange(
                getProjetUrl,
                HttpMethod.GET,
                null,
                Projet.class
        );

        log.debug("Get Projet call" + response.getStatusCode().toString());
        return response.getBody();
    }

    /**
     * Ajouter un nouveau Projet
     * @param e un nouveau projet sans identifiant
     *          @return Le projet rempli au complet avec l'identifiant
     */

    public Projet createProjet(Projet e) {
        String baseApiUrl = props.getApiUrl();
        String createProjetUrl = baseApiUrl + "/projet";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Projet> request = new HttpEntity<Projet>(e);
        ResponseEntity<Projet> response = restTemplate.exchange(
                createProjetUrl,
                HttpMethod.POST,
                request,
                Projet.class);

        log.debug("Create Projet call" + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
     * Mettre à jour un employé -en utilisant la méthode HTTP PUT
     * @param e Projet existant à mettre à jour
     */

public Projet updateProjet(Projet e) {
    String baseApiUrl = props.getApiUrl();
    String updateProjetUrl = baseApiUrl + "/projet/" + e.getIdProjet();

    RestTemplate restTemplate = new RestTemplate();
            HttpEntity<Projet> request = new HttpEntity<Projet>(e);
    ResponseEntity<Projet> response = restTemplate.exchange(
            updateProjetUrl,
            HttpMethod.PUT,
            request,
            Projet.class);

    log.debug("Update Projet call " + response.getStatusCode().toString());
    return response.getBody();
}

/**
 * Suppeimer un projet en utilisant la méthode d'échange de RestTemplate
 * au lieu de supprimer la méthode afin de consigner le code d'état de la réponse
 * @param e L'employé à supprimer
 **/

public void deleteProjet(Long idProjet) {
    String baseApiUrl = props.getApiUrl();
    String deleteProjetUrl = baseApiUrl + "/projet/" + idProjet;

    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<Void> response = restTemplate.exchange(
            deleteProjetUrl,
            HttpMethod.DELETE,
            null,
            Void.class);
    log.debug("Delete Projet call " + response.getStatusCode().toString());
}

}
