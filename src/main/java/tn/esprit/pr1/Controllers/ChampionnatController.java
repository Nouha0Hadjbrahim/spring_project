package tn.esprit.pr1.Controllers;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.pr1.Services.IChampionnatService;
import tn.esprit.pr1.entities.Championnat;

@RestController
@AllArgsConstructor
@RequestMapping("/championnat")
public class ChampionnatController {
    IChampionnatService championnatService;
    @PostMapping("/add-championnat")
    public Championnat ajouterChampionnat(@RequestBody Championnat championnat) {
        return championnatService.ajouterChampionnat(championnat);
    }
}
