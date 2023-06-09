package com.simplon.projetweb.repository;

import com.simplon.projetweb.CustomProperties;
import com.simplon.projetweb.model.Vote;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Component
@Data

public class VoteProxy {
    private CustomProperties props = new CustomProperties();

    private RestTemplate restTemplate;


    @Autowired
    public VoteProxy(CustomProperties props,
                     RestTemplate restTemplate) {
        this.props = props;
        this.restTemplate = restTemplate;

    }

    public void saveVote(Vote vote) {
        String baseApiUrl = props.getApiUrl();
        String saveVoteUrl = baseApiUrl + "/vote";

        HttpEntity<Vote> request = new HttpEntity<>(vote);
        ResponseEntity<Vote> response = restTemplate.exchange(
                saveVoteUrl,
                HttpMethod.POST,
                request,
                Vote.class);

        log.debug("Save Vote call: " + response.getStatusCode());

        response.getBody();
    }

    public Vote findByProjetAndUser(String idProjet, String user) {
        String baseApiUrl = props.getApiUrl();
        String findByProjetAndUserUrl = baseApiUrl + "/vote?projetId=" + idProjet + "&user=" + user;

        ResponseEntity<Vote> response = restTemplate.exchange(
                findByProjetAndUserUrl,
                HttpMethod.GET,
                null,
                Vote.class);

        log.debug("Find Vote call: " + response.getStatusCode());

        return response.getBody();
    }


}
