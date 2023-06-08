package com.simplon.projetweb.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;

@Entity
@Data
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProjet")
    private Long idProjet;

    @Column(name = "nomProjet")
    @Size(min = 5, max = 120)
    private String nomProjet;

    @Column(name = "descriptionProjet")
    private String descriptionProjet;

    //@Column(name = "creeLe")
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private LocalDateTime creeLe;

    @Future
    // @Column(nullable = false)
    @Column(name = "closLe")
    private LocalDateTime closLe;

    @NotNull
    @NotBlank
    @Column(name = "creePar")
    private String creePar;

    @OneToMany(mappedBy = "projet")
    @OrderBy("voteLe DESC")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
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
