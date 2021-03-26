package dev.tdz.repos;

import dev.tdz.entities.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface UserRoleRepo extends CrudRepository<UserRole, Integer> {
}
