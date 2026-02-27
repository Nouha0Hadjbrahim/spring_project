package tn.esprit.pr1.Services;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.pr1.Repository.ChampionnatRepository;
import tn.esprit.pr1.entities.Championnat;

@Service
@AllArgsConstructor
public class ChampionnatService implements IChampionnatService{
    ChampionnatRepository championnatRepository;

    @Override
    public Championnat ajouterChampionnat(Championnat championnat) {
        return championnatRepository.save(championnat);
    }
}
