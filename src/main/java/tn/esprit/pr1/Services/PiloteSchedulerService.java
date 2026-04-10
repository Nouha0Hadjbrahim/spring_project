package tn.esprit.pr1.Services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.pr1.Repository.PiloteRepository;
import tn.esprit.pr1.entities.Pilote;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PiloteSchedulerService {

    private final PiloteRepository piloteRepository;

    /**
     * Service that triggers every December 31st at 11:15 AM
     * Updates the general ranking of each pilot for the current year
     * based on total points collected during the year
     */
    @Scheduled(cron = "0 15 11 31 12 *")
    public void updatePilotRankingYearly() {
        log.info("Starting annual pilot ranking update...");

        try {
            // Get all pilots sorted by total points in descending order
            List<Pilote> pilotes = piloteRepository.findAll();

            // Sort pilots by total points (descending)
            pilotes.sort((p1, p2) -> {
                int points1 = p1.getNbPointsTotal() != null ? p1.getNbPointsTotal() : 0;
                int points2 = p2.getNbPointsTotal() != null ? p2.getNbPointsTotal() : 0;
                return Integer.compare(points2, points1);
            });

            // Assign ranking based on sorted position
            for (int i = 0; i < pilotes.size(); i++) {
                Pilote pilote = pilotes.get(i);
                pilote.setClassementGeneral(i + 1); // Ranking starts from 1
                piloteRepository.save(pilote);
                log.info("Updated ranking for pilot: {} with ranking: {}",
                    pilote.getLibelleP(), pilote.getClassementGeneral());
            }

            log.info("Annual pilot ranking update completed successfully");
        } catch (Exception e) {
            log.error("Error occurred while updating pilot rankings", e);
        }
    }
}
