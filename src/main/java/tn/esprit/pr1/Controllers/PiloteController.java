package tn.esprit.pr1.Controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.pr1.Services.IPiloteService;

@RestController
@AllArgsConstructor
@RequestMapping("/pilote")
public class PiloteController {
    IPiloteService piloteService;

}
