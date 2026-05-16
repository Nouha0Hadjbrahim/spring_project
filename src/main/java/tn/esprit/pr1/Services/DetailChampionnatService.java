package tn.esprit.pr1.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.pr1.Repository.DetailChampionnatRepository;
import tn.esprit.pr1.entities.DetailChampionnat;

import java.util.List;

@Service
@AllArgsConstructor
public class DetailChampionnatService implements IDetailChampionnatService {

    DetailChampionnatRepository detailChampionnatRepository;

    @Override
    public DetailChampionnat ajouterDetailChampionnat(DetailChampionnat dc) {
        return detailChampionnatRepository.save(dc);
    }

    @Override
    public List<DetailChampionnat> listDetailChampionnats() {
        return detailChampionnatRepository.findAll();
    }

    @Override
    public DetailChampionnat getDetailChampionnatById(Long id) {
        return detailChampionnatRepository.findById(id).orElse(null);
    }

    @Override
    public DetailChampionnat updateDetailChampionnat(DetailChampionnat dc) {
        return detailChampionnatRepository.save(dc);
    }

    @Override
    public void deleteDetailChampionnat(Long id) {
        detailChampionnatRepository.deleteById(id);
    }
}
