package tn.esprit.pr1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pr1.entities.Championnat;

@Repository
public interface ChampionnatRepository extends JpaRepository<Championnat,Long> {

}
