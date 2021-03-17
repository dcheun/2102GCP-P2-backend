package dev.tdz.repos;

import dev.tdz.entities.CourseMaterial;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface CourseMaterialRepo extends CrudRepository<CourseMaterial,Integer> {


}
