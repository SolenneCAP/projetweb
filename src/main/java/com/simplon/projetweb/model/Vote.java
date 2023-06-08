package com.simplon.projetweb.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVote;

    @ManyToOne
    @JoinColumn(name = "idProjet")
    private Projet projet;

    public void setProjet(Projet projet) {

    }
}
