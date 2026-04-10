package tn.esprit.pr1.Services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.pr1.Repository.ContratRepository;
import tn.esprit.pr1.Repository.EquipeRepository;
import tn.esprit.pr1.entities.Contrat;
import tn.esprit.pr1.entities.Equipe;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContratSchedulerService {

    private final ContratRepository contratRepository;
    private final EquipeRepository equipeRepository;

    /**
     * Section 5.1: Service that executes every 30 seconds
     * Archives expired contracts (contracts from previous years)
     * and displays active contracts by team
     */
    @Scheduled(fixedDelay = 30000) // 30 seconds
    public void archiverContratsExpireesEtAffichageContratsActifsParEquipe() {
        log.info("=== Starting contract archival and active contracts display ===");

        try {
            String currentYear = String.valueOf(LocalDate.now().getYear());

            // Archive contracts from previous years
            List<Contrat> tousLesContrats = contratRepository.findAll();
            int nombreArchives = 0;

            for (Contrat contrat : tousLesContrats) {
                if (contrat.getAnnee() != null &&
                    Integer.parseInt(contrat.getAnnee()) < Integer.parseInt(currentYear)) {
                    if (contrat.getArchived() == null || !contrat.getArchived()) {
                        contrat.setArchived(true);
                        contratRepository.save(contrat);
                        nombreArchives++;
                        log.info("Archived contract ID: {} (Year: {})",
                            contrat.getIdContrat(), contrat.getAnnee());
                    }
                }
            }

            log.info("Total archived contracts: {}", nombreArchives);

            // Get all active contracts (not archived) from current year
            List<Contrat> contratsActifs = contratRepository.findAll()
                .stream()
                .filter(c -> (c.getArchived() == null || !c.getArchived()) &&
                           currentYear.equals(c.getAnnee()))
                .collect(Collectors.toList());

            log.info("Found {} active contracts for year {}", contratsActifs.size(), currentYear);

            // Display active contracts grouped by team
            if (!contratsActifs.isEmpty()) {
                List<Equipe> equipes = equipeRepository.findAll();

                for (Equipe equipe : equipes) {
                    List<Contrat> contratsEquipe = contratsActifs
                        .stream()
                        .filter(c -> c.getEquipe() != null && c.getEquipe().getIdEquipe().equals(equipe.getIdEquipe()))
                        .collect(Collectors.toList());

                    if (!contratsEquipe.isEmpty()) {
                        log.info("======= Active Contracts for Team: {} (ID: {}) =======",
                            equipe.getLibelle(), equipe.getIdEquipe());

                        for (Contrat contrat : contratsEquipe) {
                            log.info("  Contract ID: {} | Sponsor: {} | Amount: {} | Year: {} | Status: ACTIVE",
                                contrat.getIdContrat(),
                                contrat.getSponsor() != null ? contrat.getSponsor().getNom() : "N/A",
                                contrat.getMontant(),
                                contrat.getAnnee());
                        }
                    }
                }
            }

            log.info("=== Contract archival and display completed successfully ===");

        } catch (Exception e) {
            log.error("Error occurred while processing contracts", e);
        }
    }
}
