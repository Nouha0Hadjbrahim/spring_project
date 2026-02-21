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

    ISponsorService sponsorService; // ← injection auto via @AllArgsConstructor


}
