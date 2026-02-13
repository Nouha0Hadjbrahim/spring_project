package tn.esprit.pr1.Repository;
import org. springframework. data. jpa.repository. JpaRepository;
import org.springframework.data.repository .PagingAndSortingRepository;
import tn.esprit.pr1.entities.Sponsor;
public interface SponsorRepository extends JpaRepository<Sponsor,Long> {
}
