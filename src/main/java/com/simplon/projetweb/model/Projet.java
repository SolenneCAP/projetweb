package com.simplon.projetweb.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Projet {

    private Long idProjet;


    private String nomProjet;


    private String descriptionProjet;


    private Long creeLe;


    private String creePar;


    private Date closLe;


}
