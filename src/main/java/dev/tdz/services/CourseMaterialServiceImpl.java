package dev.tdz.services;

import dev.tdz.aspects.ServiceLogging;
import dev.tdz.entities.CourseMaterial;
import dev.tdz.exceptions.NotFoundException;
import dev.tdz.repos.CourseMaterialRepo;
import dev.tdz.utils.AppUtil;
import org.apache.log4j.Logger;
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

    private static final Logger logger = Logger.getLogger(
            CourseMaterialServiceImpl.class.getName());

    @Autowired
    CourseMaterialRepo courseMaterialRepo;

    @ServiceLogging
    @Override
    public CourseMaterial createCourseMaterial(CourseMaterial courseMaterial) {
        try {
            return this.courseMaterialRepo.save(courseMaterial);
        } catch (Exception e) {
            AppUtil.logException(logger, e, "createCourseMaterial: Unable to create");
            throw e;
        }
    }

    @ServiceLogging
    @Override
    public CourseMaterial getCourseMaterialById(int id) {
        try {
            return this.courseMaterialRepo.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new NotFoundException("No such course material exists");
        } catch (Exception e) {
            AppUtil.logException(logger, e,
                    "getCourseMaterialById: Unable to get record with id=" + id);
            throw e;
        }
    }

    @ServiceLogging
    @Override
    public Set<CourseMaterial> getAllCourseMaterials() {
        try {
            return new HashSet<>(
                    (Collection<? extends CourseMaterial>) this.courseMaterialRepo.findAll());
        } catch (Exception e) {
            AppUtil.logException(logger, e, "getAllCourseMaterials: Unable to retrieve");
            throw e;
        }
    }

    @ServiceLogging
    @Override
    public CourseMaterial updateCourseMaterial(CourseMaterial courseMaterial) {
        try {
            return this.courseMaterialRepo.save(courseMaterial);
        } catch (Exception e) {
            AppUtil.logException(logger, e, "updateCourseMaterial: Unable to update");
            throw e;
        }
    }

    @ServiceLogging
    @Override
    public boolean deleteCourseMaterialById(int id) {
        try {
            this.getCourseMaterialById(id);
            return true;
        } catch (Exception e) {
            AppUtil.logException(logger, e,
                    "deleteCourseMaterialById: Unable to delete course material with id=" + id);
            throw e;
        }
    }

}
