package com.simplon.projetweb.service;

import com.simplon.projetweb.model.Vote;
import com.simplon.projetweb.repository.VoteProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class VoteService {
    private final VoteProxy voteProxy;

    @Autowired
    public VoteService(VoteProxy voteProxy) {
        this.voteProxy = voteProxy;
    }

    public void saveVote(Vote vote) {
        // Logique de validation, traitement ou préparation du vote
        if (vote.getIdProjet() == null || vote.getUser() == null) {
            throw new IllegalArgumentException("Le vote doit avoir un projet et un utilisateur spécifiés.");
        }

        // Vérifier si le vote existe déjà pour ce projet et cet utilisateur
        Vote existingVote = voteProxy.findByProjetAndUser(vote.getIdProjet(), vote.getUser());
        if (existingVote != null) {
            throw new IllegalArgumentException("Un vote existe déjà pour ce projet et cet utilisateur.");
        }
        voteProxy.saveVote(vote);
    }

    public Vote findByProjetAndUser(String projetId, String user) {
        return voteProxy.findByProjetAndUser(projetId, user);
    }

    public void deleteVote(Long voteIdVote) {
    }
}
