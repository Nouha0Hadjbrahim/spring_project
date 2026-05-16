package tn.esprit.pr1.Services;

import tn.esprit.pr1.entities.DetailChampionnat;

import java.util.List;

public interface IDetailChampionnatService {
    DetailChampionnat ajouterDetailChampionnat(DetailChampionnat dc);
    List<DetailChampionnat> listDetailChampionnats();
    DetailChampionnat getDetailChampionnatById(Long id);
    DetailChampionnat updateDetailChampionnat(DetailChampionnat dc);
    void deleteDetailChampionnat(Long id);
}
