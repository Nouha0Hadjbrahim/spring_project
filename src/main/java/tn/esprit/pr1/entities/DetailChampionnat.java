package tn.esprit.pr1.entities;

import jakarta.persistence.*;

@Entity
public class DetailChampionnat {

    @Id
    private String code;

    private String description;

    // getters & setters
}
