package dev.tdz.controllers;

import dev.tdz.aspects.Authorized;
import dev.tdz.aspects.Logging;
import dev.tdz.entities.Course;
import dev.tdz.entities.CourseMaterial;
import dev.tdz.services.CourseMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Component
@RestController
public class CourseMaterialController {
    @Autowired
    CourseMaterialService courseMaterialService;

    @Logging
    @GetMapping("/coursematerials")
    public Set<CourseMaterial> retrieveAllCourseMaterials() {
        Set<CourseMaterial> coursematerials =
                this.courseMaterialService.getAllCourseMaterials();
        return coursematerials;
    }

    @Logging
    @GetMapping("/coursematerials/{id}")
    public CourseMaterial getCourseMaterialById(@PathVariable int id) {
        CourseMaterial course = this.courseMaterialService.getCourseMaterialById(id);
        return course;
    }

    @Logging
    @Authorized
    @PostMapping("/coursematerials")
    public CourseMaterial createCourseMaterial(
            @RequestBody CourseMaterial courseMaterial
    ) {
        this.courseMaterialService.createCourseMaterial(courseMaterial);
        return courseMaterial;
    }

    @Logging
    @Authorized
    @PutMapping("/coursematerials/{id}")
    public CourseMaterial updateCourseMaterial(
            @PathVariable int id,
            @RequestBody CourseMaterial courseMaterial
    ) {
        courseMaterial.setId(id);
        this.courseMaterialService.updateCourseMaterial(courseMaterial);
        return courseMaterial;
    }

    @Logging
    @Authorized
    @DeleteMapping("/coursematerials/{id}")
    public Boolean deleteCourseMaterialById(@PathVariable int id) {
        Boolean result = this.courseMaterialService.deleteCourseMaterialById(id);
        return result;
    }
}
