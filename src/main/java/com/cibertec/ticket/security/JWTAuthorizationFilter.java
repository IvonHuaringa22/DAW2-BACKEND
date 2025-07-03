package com.cibertec.ticket.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cibertec.ticket.util.Token;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		 
		String bearerToken = request.getHeader("Authorization");
		
		if( bearerToken != null && bearerToken.startsWith("Bearer ") ) {
			String token = bearerToken.replace("Bearer ", "");
			UsernamePasswordAuthenticationToken userPAT = Token.getAuth(token);
			if (userPAT != null) {
		        SecurityContextHolder.getContext().setAuthentication(userPAT);
		    } else {
		        System.out.println("❌ Token no autorizado o no contiene credenciales válidas");
		    }
		}
		filterChain.doFilter(request, response);
		
	}
}
