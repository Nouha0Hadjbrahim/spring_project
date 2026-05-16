package tn.esprit.pr1.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.pr1.Repository.ChampionnatRepository;
import tn.esprit.pr1.Repository.CourseRepository;
import tn.esprit.pr1.Repository.DetailChampionnatRepository;
import tn.esprit.pr1.dto.PiloteDto;
import tn.esprit.pr1.entities.Championnat;
import tn.esprit.pr1.entities.Course;
import tn.esprit.pr1.entities.DetailChampionnat;

import java.util.List;

@Service
@AllArgsConstructor
public class ChampionnatService implements IChampionnatService {

    ChampionnatRepository championnatRepository;
    CourseRepository courseRepository;
    DetailChampionnatRepository detailChampionnatRepository;

    @Override
    public Championnat ajouterChampionnat(Championnat championnat) {
        return championnatRepository.save(championnat);
    }

    @Override
    public Championnat addChampionnatAndAssociatedCourses(Championnat championnat) {
        return championnatRepository.save(championnat);
    }

    @Override
    public DetailChampionnat ajouterEtaffecterDetailChampionnatAChampionnat(DetailChampionnat dc, Long idChampionnat) {
        DetailChampionnat savedDc = detailChampionnatRepository.save(dc);
        Championnat championnat = championnatRepository.findById(idChampionnat)
                .orElseThrow(() -> new RuntimeException("Championnat not found: " + idChampionnat));
        championnat.setDetail(savedDc);
        championnatRepository.save(championnat);
        return savedDc;
    }

    @Override
    public String affecterCourseAChampionnat(Long idCourse, Long idChampionnat) {
        Championnat championnat = championnatRepository.findById(idChampionnat)
                .orElseThrow(() -> new RuntimeException("Championnat not found: " + idChampionnat));
        Course course = courseRepository.findById(idCourse)
                .orElseThrow(() -> new RuntimeException("Course not found: " + idCourse));
        championnat.getCourses().add(course);
        course.getChampionnats().add(championnat);
        championnatRepository.save(championnat);
        return "Course " + idCourse + " affectée au championnat " + idChampionnat;
    }

    @Override
    public List<Championnat> listChampionnats() {
        return championnatRepository.findAll();
    }

    @Override
    public Championnat getChampionnatById(Long id) {
        return championnatRepository.findById(id).orElse(null);
    }

    @Override
    public Championnat updateChampionnat(Championnat championnat) {
        return championnatRepository.save(championnat);
    }

    @Override
    public void deleteChampionnat(Long id) {
        championnatRepository.deleteById(id);
    }

    @Override
    public List<PiloteDto> listeWinners(Integer annee) {
        return championnatRepository.findWinnersForChampionnatsAfterYear(annee);
    }
}
