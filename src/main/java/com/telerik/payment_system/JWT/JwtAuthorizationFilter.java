package com.telerik.payment_system.JWT;

import com.telerik.payment_system.constants.Constants;
import com.telerik.payment_system.services.base.AdminService;
import io.jsonwebtoken.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private final AdminService adminService;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, AdminService adminService) {
        super(authenticationManager);
        this.adminService = adminService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException,
            IOException {
        try {

            UsernamePasswordAuthenticationToken authenticationToken =
                    getAuthentication(request);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException |
                SignatureException | IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token expired");
        }
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = null;
        Cookie cookie = WebUtils.getCookie(request, Constants.COOKIE_BEARER);
        if (cookie != null) {
            token = cookie.getValue();
        }

        if (token != null) {
            String user = Jwts.parser()
                    .setSigningKey(Constants.SECRET.getBytes())
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        this.adminService.loadUserByUsername(user).getAuthorities()
                );
            }
        }

        return null;
    }

}
