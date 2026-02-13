package tn.esprit.pr1.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Equipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idEquipe;

    String libelle;
    Integer nbPointsTotal;
    Integer classementGeneral;

    // 1:* bidirectional with Pilote
    @OneToMany(mappedBy = "equipe")
    List<Pilote> pilotes = new ArrayList<>();

    // 1:* bidirectional with Contrat
    @OneToMany(mappedBy = "equipe")
    List<Contrat> contrats = new ArrayList<>();

}