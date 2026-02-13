package tn.esprit.pr1.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.pr1.entities.Pilote;
import tn.esprit.pr1.Repository.PiloteRepository;

@Service
@RequiredArgsConstructor
public class PiloteService implements IPiloteService {

    PiloteRepository pr;

    @Override
    public String addPilote(Pilote pilote) {
        pr.save(pilote);
        return "Pilote added successfully ";
    }
}
