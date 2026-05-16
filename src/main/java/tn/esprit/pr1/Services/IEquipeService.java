package tn.esprit.pr1.Services;

import tn.esprit.pr1.entities.Equipe;

import java.util.List;

public interface IEquipeService {
    Equipe ajouterEquipe(Equipe equipe);
    List<Equipe> listEquipes();
    Equipe getEquipeById(Long id);
    Equipe updateEquipe(Equipe equipe);
    void deleteEquipe(Long id);
}
