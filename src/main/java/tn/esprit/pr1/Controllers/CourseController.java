package tn.esprit.pr1.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pr1.Services.ICourseService;
import tn.esprit.pr1.entities.Course;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/course")
public class CourseController {

    ICourseService courseService;

    @PostMapping("/add-course")
    public Course ajouterCourse(@RequestBody Course course) {
        return courseService.ajouterCourse(course);
    }

    @GetMapping("/retrieve-all")
    public List<Course> listCourses() {
        return courseService.listCourses();
    }

    @GetMapping("/retrieve/{id}")
    public Course getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    @PutMapping("/update")
    public Course updateCourse(@RequestBody Course course) {
        return courseService.updateCourse(course);
    }

    @DeleteMapping("/remove/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }
}
