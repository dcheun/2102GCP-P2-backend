package dev.tdz.services;


import dev.tdz.entities.CourseMaterial;

import java.util.Set;

public interface CourseMaterialService {

    CourseMaterial createCourseMaterial(CourseMaterial courseMaterial);

    CourseMaterial getCourseMaterialById(int id);
    Set<CourseMaterial> getAllCourseMaterials();

    CourseMaterial updateCourseMaterial(CourseMaterial course);

    boolean deleteCourseMaterialById(int id);
}
