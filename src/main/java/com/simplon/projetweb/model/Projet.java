package com.simplon.projetweb.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Projet {

    private Long id;


    private String nomProjet;


    private String descriptionProjet;


    private LocalDateTime creeLe;


    private String creePar;
}
