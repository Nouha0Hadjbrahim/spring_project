package tn.esprit.pr1.entities;


import jakarta.persistence.*;

@Entity
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPosition;

    private Integer classement;
    private Integer nbPoints;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pilote_id")
    private Pilote pilote;

    public Position() {
    }

    public Long getIdPosition() { return idPosition; }
    public void setIdPosition(Long idPosition) { this.idPosition = idPosition; }

    public Integer getClassement() { return classement; }
    public void setClassement(Integer classement) { this.classement = classement; }

    public Integer getNbPoints() { return nbPoints; }
    public void setNbPoints(Integer nbPoints) { this.nbPoints = nbPoints; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    public Pilote getPilote() { return pilote; }
    public void setPilote(Pilote pilote) { this.pilote = pilote; }
}
