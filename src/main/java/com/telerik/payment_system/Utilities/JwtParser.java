package com.telerik.payment_system.Utilities;

import com.telerik.payment_system.constants.Constants;
import io.jsonwebtoken.Jwts;

import javax.servlet.http.HttpServletRequest;

public class JwtParser {


    public String getUsernameFromToken(HttpServletRequest request) {

        String header = request.getHeader("Authorization");

        return Jwts.parser()
                .setSigningKey(Constants.SECRET.getBytes())
                .parseClaimsJws(header.replace("Bearer ", ""))
                .getBody()
                .getSubject();
    }




}
