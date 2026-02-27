package tn.esprit.pr1.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pr1.Services.ISponsorService;
import tn.esprit.pr1.entities.Sponsor;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/sponsor")
public class SponsorController {

    ISponsorService sponsorService;

    // POST http://localhost:8089/championnat/sponsor/add-sponsor
    @PostMapping("/add-sponsor")
    public Sponsor ajouterSponsor(@RequestBody Sponsor sponsor) {
        return sponsorService.ajouterSponsor(sponsor);
    }

    // POST http://localhost:8089/championnat/sponsor/add-sponsors
    @PostMapping("/add-sponsors")
    public List<Sponsor> ajouterSponsors(@RequestBody List<Sponsor> sponsors) {
        return sponsorService.ajouterSponsors(sponsors);
    }

    // PUT http://localhost:8089/championnat/sponsor/update-sponsor
    @PutMapping("/update-sponsor")
    public Sponsor modifierSponsor(@RequestBody Sponsor sponsor) {
        return sponsorService.modifierSponsor(sponsor);
    }

    // DELETE http://localhost:8089/championnat/sponsor/remove-sponsor/1
    @DeleteMapping("/remove-sponsor/{sponsor-id}")
    public void supprimerSponsor(@PathVariable("sponsor-id") Long idSponsor) {
        sponsorService.supprimerSponsor(idSponsor);
    }

    // GET http://localhost:8089/championnat/sponsor/retrieve-all-sponsors
    @GetMapping("/retrieve-all-sponsors")
    public List<Sponsor> listSponsors() {
        return sponsorService.listSponsors();
    }

    // GET http://localhost:8089/championnat/sponsor/retrieve-sponsor/1
    @GetMapping("/retrieve-sponsor/{sponsor-id}")
    public Sponsor recupererSponsor(@PathVariable("sponsor-id") Long idSponsor) {
        return sponsorService.recupererSponsor(idSponsor);
    }

    // PUT http://localhost:8089/championnat/sponsor/archive-sponsor/1
    @PutMapping("/archive-sponsor/{sponsor-id}")
    public Boolean archiverSponsor(@PathVariable("sponsor-id") Long idSponsor) {
        return sponsorService.archiverSponsor(idSponsor);
    }
}