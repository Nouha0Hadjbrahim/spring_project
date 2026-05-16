package tn.esprit.pr1.Services;

import tn.esprit.pr1.dto.PiloteDto;
import tn.esprit.pr1.entities.Championnat;
import tn.esprit.pr1.entities.DetailChampionnat;

import java.util.List;

public interface IChampionnatService {
    Championnat ajouterChampionnat(Championnat championnat);
    Championnat addChampionnatAndAssociatedCourses(Championnat championnat);
    DetailChampionnat ajouterEtaffecterDetailChampionnatAChampionnat(DetailChampionnat dc, Long idChampionnat);
    String affecterCourseAChampionnat(Long idCourse, Long idChampionnat);
    List<Championnat> listChampionnats();
    Championnat getChampionnatById(Long id);
    Championnat updateChampionnat(Championnat championnat);
    void deleteChampionnat(Long id);
    List<PiloteDto> listeWinners(Integer annee);
}
