package tn.esprit.pr1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContratDto {
    private Long idContrat;
    private Float montant;
    private String annee;
    private String libelleEquipe;
    private String nomSponsor;
}
