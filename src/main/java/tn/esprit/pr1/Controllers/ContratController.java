package tn.esprit.pr1.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pr1.Services.IContratService;
import tn.esprit.pr1.dto.ContratDto;
import tn.esprit.pr1.entities.Contrat;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/contrat")
public class ContratController {

    IContratService contratService;

    @PostMapping("/add-contrat")
    public Contrat addContrat(@RequestBody Contrat contrat) {
        return contratService.addContrat(contrat);
    }

    @GetMapping("/retrieve-all")
    public List<Contrat> listContrats() {
        return contratService.listContrats();
    }

    @GetMapping("/retrieve/{id}")
    public Contrat getContratById(@PathVariable Long id) {
        return contratService.getContratById(id);
    }

    @DeleteMapping("/remove/{id}")
    public void deleteContrat(@PathVariable Long id) {
        contratService.deleteContrat(id);
    }

    @PostMapping("/add-affecter/{libelleEquipe}/{nomSponsor}/{pays}")
    public ContratDto ajoutContratEtAffecterASponsorEtEquipe(
            @RequestBody Contrat contrat,
            @PathVariable String libelleEquipe,
            @PathVariable String nomSponsor,
            @PathVariable String pays) {
        return contratService.ajoutContratEtAffecterASponsorEtEquipe(contrat, libelleEquipe, nomSponsor, pays);
    }
}
