package tn.esprit.pr1.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContrat;

    private Float montant;
    private String annee;
    private Boolean archived;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipe_id")
    @JsonIgnore   // ← évite la boucle infinie JSON
    private Equipe equipe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sponsor_id")
    @JsonIgnore   // ← évite la boucle infinie JSON
    private Sponsor sponsor;
}