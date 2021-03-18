package dev.tdz.repos;

import dev.tdz.entities.AppUser;
import org.hibernate.Session;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface AppUserRepo extends CrudRepository<AppUser,Integer> {

//    @Query(value = "SELECT pw = crypt(?2, pw) FROM app_user WHERE id = ?1", nativeQuery = true)
//    Boolean authenticate(Integer userId, String pw);

    // @see: Reference: https://www.baeldung.com/spring-data-jpa-query#2-native-3
    @Query(value = "SELECT * FROM app_user WHERE email = :email AND pw = crypt(:pw, pw)", nativeQuery = true)
    AppUser authenticate(@Param("email") String email, @Param("pw") String pw);


}
