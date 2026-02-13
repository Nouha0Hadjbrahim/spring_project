package tn.esprit.pr1.entities;


import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCourse;

    private String emplacement;
    private LocalDate dateCourse;

    // Inverse side of ManyToMany with Championnat
    @ManyToMany(mappedBy = "courses")
    private List<Championnat> championnats = new ArrayList<>();

    // 1:* bidirectional with Position
    @OneToMany(mappedBy = "course")
    private List<Position> positions = new ArrayList<>();

    public Course() {
    }

    public Long getIdCourse() { return idCourse; }
    public void setIdCourse(Long idCourse) { this.idCourse = idCourse; }

    public String getEmplacement() { return emplacement; }
    public void setEmplacement(String emplacement) { this.emplacement = emplacement; }

    public LocalDate getDateCourse() { return dateCourse; }
    public void setDateCourse(LocalDate dateCourse) { this.dateCourse = dateCourse; }

    public List<Championnat> getChampionnats() { return championnats; }
    public void setChampionnats(List<Championnat> championnats) { this.championnats = championnats; }

    public List<Position> getPositions() { return positions; }
    public void setPositions(List<Position> positions) { this.positions = positions; }

    // Helper methods for bidirectional 1:* with Position
    public void addPosition(Position position) {
        positions.add(position);
        position.setCourse(this);
    }

    public void removePosition(Position position) {
        positions.remove(position);
        position.setCourse(null);
    }
}
