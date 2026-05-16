package tn.esprit.pr1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.pr1.dto.PiloteDto;
import tn.esprit.pr1.entities.Championnat;

import java.util.List;

@Repository
public interface ChampionnatRepository extends JpaRepository<Championnat, Long> {

    @Query("SELECT DISTINCT new tn.esprit.pr1.dto.PiloteDto(p.libelleP, p.nbPointsTotal, c.libelleC) " +
           "FROM Championnat c JOIN c.courses co JOIN co.positions pos JOIN pos.pilote p " +
           "WHERE c.annee > :annee AND pos.classement = 1")
    List<PiloteDto> findWinnersForChampionnatsAfterYear(@Param("annee") Integer annee);
}
