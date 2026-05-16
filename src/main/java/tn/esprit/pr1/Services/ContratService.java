package tn.esprit.pr1.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.pr1.Repository.ContratRepository;
import tn.esprit.pr1.Repository.EquipeRepository;
import tn.esprit.pr1.Repository.SponsorRepository;
import tn.esprit.pr1.dto.ContratDto;
import tn.esprit.pr1.entities.Contrat;
import tn.esprit.pr1.entities.Equipe;
import tn.esprit.pr1.entities.Sponsor;

import java.util.List;

@Service
@AllArgsConstructor
public class ContratService implements IContratService {

    private final ContratRepository contratRepository;
    private final EquipeRepository equipeRepository;
    private final SponsorRepository sponsorRepository;

    @Override
    public Contrat addContrat(Contrat contrat) {
        return contratRepository.save(contrat);
    }

    @Override
    public List<Contrat> listContrats() {
        return contratRepository.findAll();
    }

    @Override
    public Contrat getContratById(Long id) {
        return contratRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteContrat(Long id) {
        contratRepository.deleteById(id);
    }

    @Override
    public ContratDto ajoutContratEtAffecterASponsorEtEquipe(Contrat contrat, String libelleEquipe, String nomSponsor, String pays) {
        Equipe equipe = equipeRepository.findByLibelle(libelleEquipe)
                .orElseThrow(() -> new RuntimeException("Equipe not found: " + libelleEquipe));
        Sponsor sponsor = sponsorRepository.findByNomAndPays(nomSponsor, pays)
                .orElseThrow(() -> new RuntimeException("Sponsor not found: " + nomSponsor));

        contrat.setEquipe(equipe);
        contrat.setSponsor(sponsor);
        contrat.setArchived(false);
        Contrat saved = contratRepository.save(contrat);

        return new ContratDto(
                saved.getIdContrat(),
                saved.getMontant(),
                saved.getAnnee(),
                equipe.getLibelle(),
                sponsor.getNom()
        );
    }
}
