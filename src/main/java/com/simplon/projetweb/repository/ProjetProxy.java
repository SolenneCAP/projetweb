package com.simplon.projetweb.repository;

import com.simplon.projetweb.CustomProperties;
import com.simplon.projetweb.model.Projet;
import com.simplon.projetweb.service.ProjetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Component

public class ProjetProxy {

    private final CustomProperties props = new CustomProperties();
    private ProjetService projetService;

    /**
     * Avoir tous les projets
     *
     * @return Un iterable de tous les projets
     */
    public Iterable<Projet> getProjets() {
        String baseApiUrl = props.getApiUrl();
        String getProjetsUrl = baseApiUrl + "/projets";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Projet[]> response = restTemplate.exchange(
                getProjetsUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Projet[]>() {
                }
        );
        log.debug("Get projets call " + response.getStatusCode());
        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }

    /**
     * *Obtenir un Projet par l'id
     *
     * @param idProjet - L'identifiant du Projet
     * @return Le projet qui correspond à l'idProjet
     */

    public Projet getProjet(long idProjet) {
        String baseApiUrl = props.getApiUrl();
        String getProjetUrl = baseApiUrl + "/projet/" + idProjet;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Projet> response = restTemplate.exchange(
                getProjetUrl,
                HttpMethod.GET,
                null,
                Projet.class
        );

        log.debug("Get Projet call " + response.getStatusCode());
        return response.getBody();
    }

    /**
     * Ajouter un nouveau Projet
     *
     * @param e un nouveau projet sans identifiant
     * @return Le projet rempli au complet avec l'identifiant
     */

    public Projet createProjet(Projet e) {
        String baseApiUrl = props.getApiUrl();
        String createProjetUrl = baseApiUrl + "/projet";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Projet> request = new HttpEntity<>(e);
        ResponseEntity<Projet> response = restTemplate.exchange(
                createProjetUrl,
                HttpMethod.POST,
                request,
                Projet.class);

        log.debug("Create Projet call " + response.getStatusCode());

        return response.getBody();
    }

    /**
     * Mettre à jour un projet -en utilisant la méthode HTTP PUT
     *
     * @param e Projet existant à mettre à jour
     */

    public Projet updateProjet(Projet e) {
        String baseApiUrl = props.getApiUrl();
        String updateProjetUrl = baseApiUrl + "/projet/" + e.getIdProjet();

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Projet> request = new HttpEntity<>(e);
        ResponseEntity<Projet> response = restTemplate.exchange(
                updateProjetUrl,
                HttpMethod.PUT,
                request,
                Projet.class);

        log.debug("Update Projet call " + response.getStatusCode());
        return response.getBody();
    }

    /**
     * Supprimer un projet en utilisant la méthode d'échange de RestTemplate
     * au lieu de supprimer la méthode afin de consigner le code d'état de la réponse
     * @param e Le projet à supprimer
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
        log.debug("Delete Projet call " + response.getStatusCode());
    }


    public Optional<Projet> findByIdProjet(Long idProjet) {
        return Optional.empty();
    }

    public Page<Projet> findAll(Pageable pageable) {
        return null;
    }

    public long count() {
        return 0;
    }
}
