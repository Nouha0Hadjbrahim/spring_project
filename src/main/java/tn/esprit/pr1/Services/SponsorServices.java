package tn.esprit.pr1.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.pr1.Repository.SponsorRepository;
import tn.esprit.pr1.Repository.ContratRepository;
import tn.esprit.pr1.entities.Sponsor;
import tn.esprit.pr1.entities.Contrat;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class SponsorServices implements ISponsorService{
    private SponsorRepository sp;
    private ContratRepository cr;
    @Override
    public Sponsor ajouterSponsor(Sponsor sponsor) {
        sponsor.setDateCreation(LocalDate.now());
        sponsor.setArchived(false);
        sponsor.setBloquerContrat(false);
        return sp.save(sponsor);
    }

    @Override
    public List<Sponsor> ajouterSponsors(List<Sponsor> sponsors) {
        sponsors.forEach(sponsor -> {
            sponsor.setDateCreation(LocalDate.now());
            sponsor.setArchived(false);
            sponsor.setBloquerContrat(false);
        });
        return sp.saveAll(sponsors);
    }

    @Override
    public Sponsor modifierSponsor(Sponsor sponsor) {
        sponsor.setDateDerniereModification(LocalDate.now());
        return sp.save(sponsor);
    }

    @Override
    public void supprimerSponsor(Long idSponsor) {
        sp.deleteById(idSponsor);
    }

    @Override
    public List<Sponsor> listSponsors() {
        return sp.findAll();
    }

    @Override
    public Sponsor recupererSponsor(Long idSponsor) {
        return sp.findById(idSponsor).orElse(null);
    }

    @Override
    public Boolean archiverSponsor(Long idSponsor) {
        Sponsor sponsor= sp.findById(idSponsor).orElse(null);
        if (sponsor != null) {
            sponsor.setArchived(true);
            sp.save(sponsor);
            return true;
        }
        return false;
    }

    @Override
    public Double calculerPourcentageBudgetAnnuelConsomme(Long idSponsor, String annee) {
        Sponsor sponsor = sp.findById(idSponsor).orElse(null);

        if (sponsor == null || sponsor.getBudgetAnnuel() == null || sponsor.getBudgetAnnuel() <= 0) {
            return 0.0;
        }

        // Get all contracts for this sponsor in the current year
        List<Contrat> contrats = cr.findAll();
        Double montantConsomme = contrats.stream()
            .filter(c -> c.getSponsor() != null &&
                        c.getSponsor().getIdSponsor().equals(idSponsor) &&
                        annee.equals(c.getAnnee()) &&
                        (c.getArchived() == null || !c.getArchived()))
            .mapToDouble(c -> c.getMontant() != null ? c.getMontant() : 0.0)
            .sum();

        Double pourcentage = (montantConsomme / sponsor.getBudgetAnnuel()) * 100;
        return pourcentage;
    }
}
