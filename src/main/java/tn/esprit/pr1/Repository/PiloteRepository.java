package tn.esprit.pr1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pr1.entities.Pilote;

@Repository
public interface PiloteRepository extends JpaRepository<Pilote, Long> {
}