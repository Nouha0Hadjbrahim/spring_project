package tn.esprit.pr1.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.pr1.Repository.EquipeRepository;
import tn.esprit.pr1.Repository.PiloteRepository;
import tn.esprit.pr1.entities.Equipe;
import tn.esprit.pr1.entities.Pilote;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PiloteService implements IPiloteService {

    private final PiloteRepository pr;
    private final EquipeRepository equipeRepository;

    @Override
    public String addPilote(Pilote pilote) {
        pr.save(pilote);
        return "Pilote added successfully ";
    }

    @Override
    public void updatePilotRankings() {
        List<Pilote> pilotes = pr.findAll();
        pilotes.sort((p1, p2) -> {
            int points1 = p1.getNbPointsTotal() != null ? p1.getNbPointsTotal() : 0;
            int points2 = p2.getNbPointsTotal() != null ? p2.getNbPointsTotal() : 0;
            return Integer.compare(points2, points1);
        });
        for (int i = 0; i < pilotes.size(); i++) {
            Pilote pilote = pilotes.get(i);
            pilote.setClassementGeneral(i + 1);
            pr.save(pilote);
        }
    }

    @Override
    public List<Pilote> listPilotes() {
        return pr.findAll();
    }

    @Override
    public Pilote getPiloteById(Long id) {
        return pr.findById(id).orElse(null);
    }

    @Override
    public Pilote updatePilote(Pilote pilote) {
        return pr.save(pilote);
    }

    @Override
    public void deletePilote(Long id) {
        pr.deleteById(id);
    }

    @Override
    public Pilote affecterPiloteAEquipe(String libelleP, String libelleEq) {
        Pilote pilote = pr.findAll().stream()
                .filter(p -> libelleP.equals(p.getLibelleP()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Pilote not found: " + libelleP));
        Equipe equipe = equipeRepository.findByLibelle(libelleEq)
                .orElseThrow(() -> new RuntimeException("Equipe not found: " + libelleEq));
        pilote.setEquipe(equipe);
        return pr.save(pilote);
    }
}
