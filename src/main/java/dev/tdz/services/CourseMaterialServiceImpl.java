package dev.tdz.services;

import dev.tdz.entities.CourseMaterial;
import dev.tdz.repos.CourseMaterialRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;


@Component
@Service
public class CourseMaterialServiceImpl implements CourseMaterialService {

    @Autowired
    CourseMaterialRepo courseMaterialRepo;


    @Override
    public CourseMaterial createCourseMaterial(CourseMaterial courseMaterial) {
            this.courseMaterialRepo.save(courseMaterial);
        return  courseMaterial;
    }

    @Override
    public CourseMaterial getCourseMaterialById(int id) {
        CourseMaterial courseMaterial = this.courseMaterialRepo.findById(id).get();
        return courseMaterial;
    }

    @Override
    public Set<CourseMaterial> getAllCourseMaterials() {
        Set<CourseMaterial> courseMaterials = new HashSet<CourseMaterial>((Collection<? extends CourseMaterial>) this.courseMaterialRepo.findAll());
        return courseMaterials;
    }

    @Override
    public CourseMaterial updateCourseMaterial(CourseMaterial course) {
        return this.courseMaterialRepo.save(course);
    }

    @Override
    public boolean deleteCourseMaterialById(int id) {
        this.courseMaterialRepo.deleteById(id);
        try {
            this.getCourseMaterialById(id);
            return false;
        } catch(NoSuchElementException e){
            return true;
        }

    }
}
