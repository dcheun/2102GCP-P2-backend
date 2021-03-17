package dev.tdz.repos;

import dev.tdz.entities.Rating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface RatingRepo extends CrudRepository<Rating,Integer> {

}
