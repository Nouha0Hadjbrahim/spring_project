package tn.esprit.pr1.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.pr1.entities.Pilote;
import tn.esprit.pr1.Repository.PiloteRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PiloteService implements IPiloteService {

    private final PiloteRepository pr;

    @Override
    public String addPilote(Pilote pilote) {
        pr.save(pilote);
        return "Pilote added successfully ";
    }

    @Override
    public void updatePilotRankings() {
        List<Pilote> pilotes = pr.findAll();

        // Sort pilots by total points in descending order
        pilotes.sort((p1, p2) -> {
            int points1 = p1.getNbPointsTotal() != null ? p1.getNbPointsTotal() : 0;
            int points2 = p2.getNbPointsTotal() != null ? p2.getNbPointsTotal() : 0;
            return Integer.compare(points2, points1);
        });

        // Assign ranking based on sorted position
        for (int i = 0; i < pilotes.size(); i++) {
            Pilote pilote = pilotes.get(i);
            pilote.setClassementGeneral(i + 1);
            pr.save(pilote);
        }
    }
}
