package tn.esprit.pr1.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sponsor {

    @Id
    private Long idSponsor;

    private String nom;

    private String pays;

    private Float budgetAnnuel;

    private Boolean bloquerContrat;
    Boolean archived;
    LocalDate dateCreation;
    LocalDate dateDerniereModification;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy="sponsor")
    private List<Contrat> contrats;
}
