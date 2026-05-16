package tn.esprit.pr1.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pr1.Services.ISponsorService;
import tn.esprit.pr1.entities.Sponsor;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/sponsor")
@Tag(name = "Sponsor", description = "Gestion des sponsors")
public class SponsorController {

    ISponsorService sponsorService;

    @Operation(summary = "Ajouter un sponsor", description = "Crée un nouveau sponsor avec initialisation automatique des champs booléens et date")
    @ApiResponse(responseCode = "200", description = "Sponsor ajouté avec succès")
    @PostMapping("/add-sponsor")
    public Sponsor ajouterSponsor(@RequestBody Sponsor sponsor) {
        return sponsorService.ajouterSponsor(sponsor);
    }

    @Operation(summary = "Ajouter une liste de sponsors", description = "Crée plusieurs sponsors en une seule requête")
    @PostMapping("/add-sponsors")
    public List<Sponsor> ajouterSponsors(@RequestBody List<Sponsor> sponsors) {
        return sponsorService.ajouterSponsors(sponsors);
    }

    @Operation(summary = "Modifier un sponsor", description = "Met à jour les informations d'un sponsor existant")
    @PutMapping("/update-sponsor")
    public Sponsor modifierSponsor(@RequestBody Sponsor sponsor) {
        return sponsorService.modifierSponsor(sponsor);
    }

    @Operation(summary = "Supprimer un sponsor", description = "Supprime un sponsor par son identifiant")
    @DeleteMapping("/remove-sponsor/{sponsor-id}")
    public void supprimerSponsor(
            @Parameter(description = "Identifiant du sponsor à supprimer") @PathVariable("sponsor-id") Long idSponsor) {
        sponsorService.supprimerSponsor(idSponsor);
    }

    @Operation(summary = "Lister tous les sponsors")
    @GetMapping("/retrieve-all-sponsors")
    public List<Sponsor> listSponsors() {
        return sponsorService.listSponsors();
    }

    @Operation(summary = "Récupérer un sponsor par ID")
    @GetMapping("/retrieve-sponsor/{sponsor-id}")
    public Sponsor recupererSponsor(
            @Parameter(description = "Identifiant du sponsor") @PathVariable("sponsor-id") Long idSponsor) {
        return sponsorService.recupererSponsor(idSponsor);
    }

    @Operation(summary = "Archiver un sponsor", description = "Passe le champ archived à true pour le sponsor indiqué")
    @PutMapping("/archive-sponsor/{sponsor-id}")
    public Boolean archiverSponsor(
            @Parameter(description = "Identifiant du sponsor à archiver") @PathVariable("sponsor-id") Long idSponsor) {
        return sponsorService.archiverSponsor(idSponsor);
    }
}
