package tn.esprit.pr1.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.pr1.Repository.CourseRepository;
import tn.esprit.pr1.entities.Course;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseService implements ICourseService {

    CourseRepository courseRepository;

    @Override
    public Course ajouterCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public List<Course> listCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public Course updateCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
