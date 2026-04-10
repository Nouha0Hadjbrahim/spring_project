package tn.esprit.pr1.Services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.pr1.Repository.SponsorRepository;
import tn.esprit.pr1.entities.Sponsor;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SponsorSchedulerService {

    private final SponsorRepository sponsorRepository;
    private final SponsorServices sponsorServices;

    /**
     * Section 5.2: Service that executes every Monday at 9:00 AM
     * Displays the percentage of annual budget consumed by each sponsor
     * for contracts of the current year
     */
    @Scheduled(cron = "0 0 9 * * 1") // Every Monday at 9:00 AM (second minute hour day month day-of-week)
    public void afficherPourcentageBudgetConsommeParSponsor() {
        log.info("=== Starting sponsor budget consumption report (Monday 9:00 AM) ===");

        try {
            String currentYear = String.valueOf(LocalDate.now().getYear());

            List<Sponsor> sponsors = sponsorRepository.findAll();

            if (sponsors.isEmpty()) {
                log.info("No sponsors found in the system");
                return;
            }

            for (Sponsor sponsor : sponsors) {
                if (sponsor.getBudgetAnnuel() == null || sponsor.getBudgetAnnuel() <= 0) {
                    log.warn("Sponsor {} has no annual budget set", sponsor.getNom());
                    continue;
                }

                // Calculate budget consumed
                Double pourcentageConsomme = sponsorServices.calculerPourcentageBudgetAnnuelConsomme(
                    sponsor.getIdSponsor(), currentYear);

                log.info("======= Budget Report for Sponsor: {} =======", sponsor.getNom());
                log.info("Annual Budget: {} | Percentage Consumed: {}%",
                    sponsor.getBudgetAnnuel(), String.format("%.2f", pourcentageConsomme));

                // Apply business rules based on percentage
                if (pourcentageConsomme > 70 && pourcentageConsomme < 100) {
                    sponsor.setBloquerContrat(false);
                    log.warn("⚠️  ATTENTION BUDGET PRESQUE CONSOMMÉ : {:.2f}% !",
                        pourcentageConsomme);
                } else if (pourcentageConsomme >= 100) {
                    sponsor.setBloquerContrat(true);
                    sponsorRepository.save(sponsor);
                    log.error("🚫 BUDGET DÉPASSÉ!! VOUS NE POUVEZ PLUS FAIRE DE CONTRATS");
                } else {
                    sponsor.setBloquerContrat(false);
                    log.info("✓ Budget is under control");
                }

                sponsorRepository.save(sponsor);
            }

            log.info("=== Sponsor budget consumption report completed ===");

        } catch (Exception e) {
            log.error("Error occurred while processing sponsor budgets", e);
        }
    }

}
