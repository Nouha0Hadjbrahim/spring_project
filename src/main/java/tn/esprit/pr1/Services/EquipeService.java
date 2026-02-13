package tn.esprit.pr1.Services;

import tn.esprit.pr1.Repository.EquipeRepository;
import tn.esprit.pr1.entities.Equipe;

public class EquipeService implements IEquipeService {
    EquipeRepository er;
    @Override
    public Equipe ajouterEquipe(Equipe equipe) {
        return er.save(equipe);
    }
}