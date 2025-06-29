package com.cibertec.ticket.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cibertec.ticket.model.Auth;
import com.cibertec.ticket.model.Usuario;
import com.cibertec.ticket.serviceImplement.UserDetailImplement;
import com.cibertec.ticket.util.Token;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        Auth authCredentials = new Auth();

        try {
            authCredentials = new ObjectMapper().readValue(request.getReader(), Auth.class);
        } catch (IOException e) {
            throw new RuntimeException("Error al leer las credenciales desde el request", e);
        }

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                authCredentials.getCorreo(),
                authCredentials.getContrasenia(),
                Collections.emptyList()
        );

        return getAuthenticationManager().authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

    	UserDetails userDetails = (UserDetails) authResult.getPrincipal();

        String nombre = userDetails.getUsername();
        String correo = userDetails.getUsername();
        String rol = userDetails.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "");

        String token = Token.crearToken(nombre, correo, rol);

        response.addHeader("Authorization", "Bearer " + token);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"token\": \"" + token + "\"}");
        response.getWriter().flush();
    }
}