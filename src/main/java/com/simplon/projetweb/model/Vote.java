package com.simplon.projetweb.model;

import lombok.Data;

import java.time.LocalDateTime;
@Data

public class Vote {

    private Long idVote;

    private Projet projet;

    private Boolean valueVote;

    private LocalDateTime voteLe;

    private String user;

    private String idProjet;

    public String getIdProjet() {
        return idProjet;
    }
}
