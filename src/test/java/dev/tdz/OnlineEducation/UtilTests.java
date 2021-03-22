package dev.tdz.OnlineEducation;

import com.auth0.jwt.interfaces.DecodedJWT;
import dev.tdz.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UtilTests {

    @Test
    public void generate_jwt(){
        String jwt = JwtUtil.generateJwtToken(1,1);
        System.out.println(jwt);
    }

    @Test
    public void verify_jwt(){
        String token = JwtUtil.generateJwtToken(1,2);
        DecodedJWT jwt = JwtUtil.verifyToken(token);
        int id = jwt.getClaim("id").asInt();
        int roleId = jwt.getClaim("userRoleId").asInt();
        System.out.println(token);
        System.out.println("id: "+id+" roleId: "+roleId);
    }
}
