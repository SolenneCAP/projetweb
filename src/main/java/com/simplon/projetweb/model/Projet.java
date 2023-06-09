package com.simplon.projetweb.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;


@Data
public class Projet {

    private Long idProjet;

    private String nomProjet;


    private String descriptionProjet;


    private LocalDateTime creeLe;


    private LocalDateTime closLe;

    private String creePar;

    private Collection<Vote> votes;


    public Projet() {
        super();
        this.creeLe = LocalDateTime.now();
    }

    public Projet(
            Long pIdProjet,
            String pNomProjet,
            String pDescriptionProjet,
            String pCreePar,
            LocalDateTime pCreeLe,
            LocalDateTime pClosLe) {
        super();
        setIdProjet(pIdProjet);
        setNomProjet(pNomProjet);
        setDescriptionProjet(pDescriptionProjet);
        setCreeLe(pCreeLe);
        setClosLe(pClosLe);
        setCreePar(pCreePar);
    }

    public Long getIdProjet() {
        return idProjet;
    }

    public void setIdProjet(Long pIdProjet) {
        this.idProjet = pIdProjet;
    }

    public String getNomProjet() {
        return nomProjet;
    }

    public void setNomProjet(String pNomProjet) {
        this.nomProjet = pNomProjet;
    }

    public String getDescriptionProjet() {
        return descriptionProjet;
    }

    public void setDescriptionProjet(String pDescriptionProjet) {
        this.descriptionProjet = pDescriptionProjet;
    }

    public LocalDateTime getCreeLe() {
        return creeLe;
    }

    public void setCreeLe(LocalDateTime pCreeLe) {
        creeLe = pCreeLe;
        if (creeLe != null) {
            creeLe = creeLe.truncatedTo(ChronoUnit.SECONDS);
        }
    }

    public LocalDateTime getClosLe() {
        return closLe;
    }

    public void setClosLe(LocalDateTime pClosLe) {
        closLe = pClosLe;
        if (closLe != null) {
            closLe = closLe.truncatedTo(ChronoUnit.SECONDS);
        }
    }

    public String getCreePar() {
        return creePar;
    }

    public void setCreePar(String pCreePar) {
        this.creePar = pCreePar;
    }

    public Collection<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Collection<Vote> pVotes) {
        votes = pVotes;
    }


}
