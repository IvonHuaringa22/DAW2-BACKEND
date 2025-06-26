package com.cibertec.ticket.util;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class Token {
	private final static String TOKEN_SECRETO = "A3xV9Jt7FkQp1WmBnLzYcTrUeGiK8Hd2";
	private final static Long TOKEN_DURACION = 1_500_000L;

    public static String crearToken(String username, String email) {
		long expiracionTiempo = TOKEN_DURACION;
		Date expiracionFecha = new Date(System.currentTimeMillis() + expiracionTiempo);
		
		Map<String, Object> map = new HashMap<>();
		map.put("nombre", username);
    	
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expiracionFecha)
				.addClaims(map)
				.signWith(Keys.hmacShaKeyFor(TOKEN_SECRETO.getBytes()))
				.compact();
    }

    public static UsernamePasswordAuthenticationToken getAuth (String token) {
    	
    	try {
			Claims claims = Jwts.parserBuilder()
					.setSigningKey(TOKEN_SECRETO.getBytes())
					.build()
					.parseClaimsJws(token)
					.getBody();
			
			String email = claims.getSubject();
    	
			return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
		} catch (JwtException e) {
			return null;
		}
    }
}
