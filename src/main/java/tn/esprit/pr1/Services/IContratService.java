package tn.esprit.pr1.Services;

import tn.esprit.pr1.dto.ContratDto;
import tn.esprit.pr1.entities.Contrat;

import java.util.List;

public interface IContratService {
    Contrat addContrat(Contrat contrat);
    List<Contrat> listContrats();
    Contrat getContratById(Long id);
    void deleteContrat(Long id);
    ContratDto ajoutContratEtAffecterASponsorEtEquipe(Contrat contrat, String libelleEquipe, String nomSponsor, String pays);
}
