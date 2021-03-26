package dev.tdz.repos;

import dev.tdz.entities.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface CourseRepo extends CrudRepository<Course, Integer> {
}
