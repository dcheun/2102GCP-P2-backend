package dev.tdz.OnlineEducation.auth;

import com.auth0.jwt.interfaces.DecodedJWT;
import dev.tdz.entities.AppUser;
import dev.tdz.services.AppUserService;
import dev.tdz.utils.JwtUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class JwtTests {
    private static AppUser instructor;
    private static AppUser student;

    @Mock
    private static AppUserService appUserService;

    @BeforeEach
    void setUp() {
        instructor = new AppUser(
                1,
                "Rubeus",
                "Hagrid",
                "rhagrid@hogwarts.edu",
                "hagrid123",
                1
        );
        student = new AppUser(
                2,
                "Ron",
                "Weasley",
                "rweasley@hogwarts.edu",
                "weasley123",
                2
        );
    }

    @Test
    void create_jwt_instructor() {
        Mockito.when(appUserService.getAppUserById(1)).thenReturn(instructor);
        AppUser instructor = appUserService.getAppUserById(1);
        String jwt = JwtUtil.generateJwtToken(instructor.getId(), instructor.getUserRoleId());
        Assertions.assertNotNull(jwt);
    }

    @Test
    void create_jwt_student() {
        Mockito.when(appUserService.getAppUserById(2)).thenReturn(student);
        AppUser student = appUserService.getAppUserById(2);
        String jwt = JwtUtil.generateJwtToken(student.getId(), student.getUserRoleId());
        Assertions.assertNotNull(jwt);
    }

    @Test
    void decode_jwt() {
        Mockito.when(appUserService.getAppUserById(1)).thenReturn(instructor);
        AppUser instructor = appUserService.getAppUserById(1);
        String encJwt = JwtUtil.generateJwtToken(instructor.getId(), instructor.getUserRoleId());
        DecodedJWT jwt = JwtUtil.verifyToken(encJwt);
        int userId = jwt.getClaim("id").asInt();
        int roleId = jwt.getClaim("userRoleId").asInt();
        Assertions.assertEquals(1, userId);
        Assertions.assertEquals(1, roleId);
    }
}
