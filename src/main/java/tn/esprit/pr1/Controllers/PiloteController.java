package tn.esprit.pr1.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pr1.Services.IPiloteService;
import tn.esprit.pr1.entities.Pilote;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/pilote")
public class PiloteController {

    IPiloteService piloteService;

    @PostMapping("/add-pilote")
    public String addPilote(@RequestBody Pilote pilote) {
        return piloteService.addPilote(pilote);
    }

    @GetMapping("/retrieve-all")
    public List<Pilote> listPilotes() {
        return piloteService.listPilotes();
    }

    @GetMapping("/retrieve/{id}")
    public Pilote getPiloteById(@PathVariable Long id) {
        return piloteService.getPiloteById(id);
    }

    @PutMapping("/update")
    public Pilote updatePilote(@RequestBody Pilote pilote) {
        return piloteService.updatePilote(pilote);
    }

    @DeleteMapping("/remove/{id}")
    public void deletePilote(@PathVariable Long id) {
        piloteService.deletePilote(id);
    }

    @PutMapping("/affecter-equipe/{libelleP}/{libelleEq}")
    public Pilote affecterPiloteAEquipe(@PathVariable String libelleP, @PathVariable String libelleEq) {
        return piloteService.affecterPiloteAEquipe(libelleP, libelleEq);
    }
}
