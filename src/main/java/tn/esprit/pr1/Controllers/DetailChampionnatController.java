package tn.esprit.pr1.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pr1.Services.IDetailChampionnatService;
import tn.esprit.pr1.entities.DetailChampionnat;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/detailchampionnat")
public class DetailChampionnatController {

    IDetailChampionnatService detailChampionnatService;

    @PostMapping("/add")
    public DetailChampionnat ajouterDetailChampionnat(@RequestBody DetailChampionnat dc) {
        return detailChampionnatService.ajouterDetailChampionnat(dc);
    }

    @GetMapping("/retrieve-all")
    public List<DetailChampionnat> listDetailChampionnats() {
        return detailChampionnatService.listDetailChampionnats();
    }

    @GetMapping("/retrieve/{id}")
    public DetailChampionnat getDetailChampionnatById(@PathVariable Long id) {
        return detailChampionnatService.getDetailChampionnatById(id);
    }

    @PutMapping("/update")
    public DetailChampionnat updateDetailChampionnat(@RequestBody DetailChampionnat dc) {
        return detailChampionnatService.updateDetailChampionnat(dc);
    }

    @DeleteMapping("/remove/{id}")
    public void deleteDetailChampionnat(@PathVariable Long id) {
        detailChampionnatService.deleteDetailChampionnat(id);
    }
}
