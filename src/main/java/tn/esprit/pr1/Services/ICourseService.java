package tn.esprit.pr1.Services;

import tn.esprit.pr1.entities.Course;

import java.util.List;

public interface ICourseService {
    Course ajouterCourse(Course course);
    List<Course> listCourses();
    Course getCourseById(Long id);
    Course updateCourse(Course course);
    void deleteCourse(Long id);
}
