package tn.esprit.pr1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PiloteDto {
    private String libelleP;
    private Integer nbPointsTotal;
    private String libelleC;
}
