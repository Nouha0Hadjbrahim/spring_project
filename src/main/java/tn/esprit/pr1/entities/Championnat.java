package tn.esprit.pr1.entities;


import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Championnat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChampionnat;

    @Enumerated(EnumType.STRING)
    private Categorie categorie;

    private String libelleC;
    private Integer annee;

    // 1:1 bidirectional with DetailChampionnat
    // Owner = Championnat (foreign key in championnat table)
    @OneToOne
    @JoinColumn(name = "detail_championnat_id")
    private DetailChampionnat detail;

    // *:*: bidirectional with Course
    @ManyToMany
    @JoinTable(
            name = "championnat_course",
            joinColumns = @JoinColumn(name = "championnat_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses = new ArrayList<>();

    public Championnat() {
    }

    // Getters & Setters
    public Long getIdChampionnat() { return idChampionnat; }
    public void setIdChampionnat(Long idChampionnat) { this.idChampionnat = idChampionnat; }

    public Categorie getCategorie() { return categorie; }
    public void setCategorie(Categorie categorie) { this.categorie = categorie; }

    public String getLibelleC() { return libelleC; }
    public void setLibelleC(String libelleC) { this.libelleC = libelleC; }

    public Integer getAnnee() { return annee; }
    public void setAnnee(Integer annee) { this.annee = annee; }

    public DetailChampionnat getDetail() { return detail; }
    public void setDetail(DetailChampionnat detail) { this.detail = detail; }

    public List<Course> getCourses() { return courses; }
    public void setCourses(List<Course> courses) { this.courses = courses; }

    // Helper methods (recommended for bidirectional ManyToMany)
    public void addCourse(Course course) {
        courses.add(course);
        course.getChampionnats().add(this);
    }

    public void removeCourse(Course course) {
        courses.remove(course);
        course.getChampionnats().remove(this);
    }
}