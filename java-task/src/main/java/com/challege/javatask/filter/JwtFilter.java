package com.challege.javatask.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        ServletOutputStream pw = httpServletResponse.getOutputStream();
        // expects the token to come from header
        String authHeader = httpServletRequest.getHeader("Authorization");
        String theMethod = httpServletRequest.getMethod();
        //Do Nothing on GET methods
        if(HttpMethod.GET.matches(theMethod)) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        //Check JWT Validity For Other Than Get Methods
        else {
            if (authHeader == null || !authHeader.startsWith("Bearer")) {
                //If token is not coming than set the response status as UNAUTHORIZED
                httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                pw.println("Missing or invalid Token ");
                pw.close();
            } else {//extract token from the header
                String jwtToken = authHeader.substring(7);//Bearer => 6+1 since token begins with Bearer
                //token validation
                Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(jwtToken).getBody();
                System.out.println("Claims" + claims);
                httpServletRequest.setAttribute("username", claims.get("user_name"));
                httpServletRequest.setAttribute("userId", claims.get("user_id"));
                // pass the claims in the request
                filterChain.doFilter(servletRequest, servletResponse); //some more filters , controller}

            }

        }

    }
}
