package com.simplon.projetweb.repository;

import com.simplon.projetweb.model.Projet;
import com.simplon.projetweb.model.Vote;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Repository
public class VoteProxy {
    private Long idVote;
    private Long idProjet;
    private Boolean value;
    private LocalDateTime voteLe;
    private String user;
    @Id
    private Long id;

    public VoteProxy() {
    }

    public VoteProxy(Long idVote, Long idProjet, Boolean value, LocalDateTime voteLe, String user) {
        this.idVote = idVote;
        this.idProjet = idProjet;
        this.value = value;
        this.voteLe = voteLe;
        this.user = user;
    }

    @Query("SELECT v FROM Vote v WHERE v.projet = :projet AND v.user = :user")
    public List<Vote> findByProjetAndUser(Projet projet, String user) {
        return null;
    }

    public void save(Vote vote) {

    }

    public void setIdVote(Long idVote) {
        this.idVote = idVote;
    }

    public Long getIVoted() {
        return idVote;
    }
}