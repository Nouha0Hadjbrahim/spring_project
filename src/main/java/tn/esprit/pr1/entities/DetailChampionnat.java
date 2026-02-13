package tn.esprit.pr1.entities;

import jakarta.persistence.*;

@Entity
public class DetailChampionnat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String description;

    // Inverse side of 1:1
    @OneToOne(mappedBy = "detail")
    private Championnat championnat;

    public DetailChampionnat() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Championnat getChampionnat() { return championnat; }
    public void setChampionnat(Championnat championnat) { this.championnat = championnat; }
}
