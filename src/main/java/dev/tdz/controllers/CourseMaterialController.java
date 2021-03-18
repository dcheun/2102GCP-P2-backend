package dev.tdz.controllers;

import dev.tdz.entities.Course;
import dev.tdz.entities.CourseMaterial;
import dev.tdz.services.CourseMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Component
@RestController
public class CourseMaterialController {
    @Autowired
    CourseMaterialService courseMaterialService;

    @GetMapping("/coursematerials")
    public Set<CourseMaterial> retrieveAllCourseMaterials() {
        Set<CourseMaterial> coursematerials = this.courseMaterialService.getAllCourseMaterials();
        return coursematerials;
    }

    @GetMapping("/coursematerials/{id}")
    public CourseMaterial getCourseMaterialById(@PathVariable int id) {
        CourseMaterial course = this.courseMaterialService.getCourseMaterialById(id);
        return course;
    }

    @PostMapping("/coursematerials")
    public CourseMaterial createCourseMaterial(@RequestBody CourseMaterial courseMaterial) {
        this.courseMaterialService.createCourseMaterial(courseMaterial);
        return courseMaterial;
    }

    @PutMapping("/coursematerials/{id}")
    public CourseMaterial updateCourseMaterial(@PathVariable int id, @RequestBody CourseMaterial courseMaterial) {
        courseMaterial.setId(id);
        this.courseMaterialService.updateCourseMaterial(courseMaterial);
        return courseMaterial;
    }

    @DeleteMapping("/coursematerials/{id}")
    public Boolean deleteCourseMaterialById(@PathVariable int id) {
        Boolean result = this.courseMaterialService.deleteCourseMaterialById(id);
        return result;
    }
}
