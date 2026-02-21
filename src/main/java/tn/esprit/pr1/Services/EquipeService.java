package tn.esprit.pr1.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.pr1.Repository.EquipeRepository;
import tn.esprit.pr1.entities.Equipe;

@Service
@AllArgsConstructor
public class EquipeService implements IEquipeService {
    EquipeRepository er;
    @Override
    public Equipe ajouterEquipe(Equipe equipe) {
        return er.save(equipe);
    }
}