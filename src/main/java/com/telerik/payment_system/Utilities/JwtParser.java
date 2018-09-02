package com.telerik.payment_system.Utilities;

import com.telerik.payment_system.constants.Constants;
import com.telerik.payment_system.entities.User;
import com.telerik.payment_system.repositories.base.UserRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
@Component
public class JwtParser {

    private final UserRepository userRepository;



    public JwtParser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public String getUsernameFromToken(HttpServletRequest request) {

        String header = request.getHeader("Authorization");

        return Jwts.parser()
                .setSigningKey(Constants.SECRET.getBytes())
                .parseClaimsJws(header.replace("Bearer ", ""))
                .getBody()
                .getSubject();
    }

    public long getBankIdByUsernameFromToken(HttpServletRequest request) {
        String username = getUsernameFromToken(request);
        User user =(User)this.userRepository.getByUsername(username);
        long bankId = user.getId();
        return bankId;
    }


}
