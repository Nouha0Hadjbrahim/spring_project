package tn.esprit.pr1.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pr1.Services.IChampionnatService;
import tn.esprit.pr1.dto.PiloteDto;
import tn.esprit.pr1.entities.Championnat;
import tn.esprit.pr1.entities.DetailChampionnat;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/championnat")
@Tag(name = "Championnat", description = "Gestion des championnats et affectations")
public class ChampionnatController {

    IChampionnatService championnatService;

    @Operation(summary = "Ajouter un championnat simple")
    @PostMapping("/add-championnat")
    public Championnat ajouterChampionnat(@RequestBody Championnat championnat) {
        return championnatService.ajouterChampionnat(championnat);
    }

    @Operation(summary = "Ajouter un championnat avec ses courses associées (cascade)")
    @PostMapping("/add-with-courses")
    public Championnat addChampionnatAndAssociatedCourses(@RequestBody Championnat championnat) {
        return championnatService.addChampionnatAndAssociatedCourses(championnat);
    }

    @Operation(summary = "Ajouter un détail et l'affecter à un championnat existant")
    @PostMapping("/add-detail/{idChampionnat}")
    public DetailChampionnat ajouterEtAffecterDetail(
            @RequestBody DetailChampionnat dc,
            @Parameter(description = "ID du championnat") @PathVariable Long idChampionnat) {
        return championnatService.ajouterEtaffecterDetailChampionnatAChampionnat(dc, idChampionnat);
    }

    @Operation(summary = "Affecter une course existante à un championnat existant")
    @PutMapping("/affecter-course/{idCourse}/{idChampionnat}")
    public String affecterCourseAChampionnat(
            @PathVariable Long idCourse,
            @PathVariable Long idChampionnat) {
        return championnatService.affecterCourseAChampionnat(idCourse, idChampionnat);
    }

    @Operation(summary = "Lister tous les championnats")
    @GetMapping("/retrieve-all")
    public List<Championnat> listChampionnats() {
        return championnatService.listChampionnats();
    }

    @Operation(summary = "Récupérer un championnat par ID")
    @GetMapping("/retrieve/{id}")
    public Championnat getChampionnatById(@PathVariable Long id) {
        return championnatService.getChampionnatById(id);
    }

    @Operation(summary = "Modifier un championnat")
    @PutMapping("/update")
    public Championnat updateChampionnat(@RequestBody Championnat championnat) {
        return championnatService.updateChampionnat(championnat);
    }

    @Operation(summary = "Supprimer un championnat")
    @DeleteMapping("/remove/{id}")
    public void deleteChampionnat(@PathVariable Long id) {
        championnatService.deleteChampionnat(id);
    }

    @Operation(summary = "Lister les gagnants des championnats après une année donnée (DTO)")
    @GetMapping("/winners/{annee}")
    public List<PiloteDto> listeWinners(
            @Parameter(description = "Année de référence (strictement supérieure)") @PathVariable Integer annee) {
        return championnatService.listeWinners(annee);
    }
}
