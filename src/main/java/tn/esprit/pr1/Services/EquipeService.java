package tn.esprit.pr1.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.pr1.Repository.EquipeRepository;
import tn.esprit.pr1.entities.Equipe;

import java.util.List;

@Service
@AllArgsConstructor
public class EquipeService implements IEquipeService {

    EquipeRepository er;

    @Override
    public Equipe ajouterEquipe(Equipe equipe) {
        return er.save(equipe);
    }

    @Override
    public List<Equipe> listEquipes() {
        return er.findAll();
    }

    @Override
    public Equipe getEquipeById(Long id) {
        return er.findById(id).orElse(null);
    }

    @Override
    public Equipe updateEquipe(Equipe equipe) {
        return er.save(equipe);
    }

    @Override
    public void deleteEquipe(Long id) {
        er.deleteById(id);
    }
}
