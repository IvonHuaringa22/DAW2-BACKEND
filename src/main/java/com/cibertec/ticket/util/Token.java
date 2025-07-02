package com.cibertec.ticket.util;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class Token {
	private final static String TOKEN_SECRETO = "A3xV9Jt7FkQp1WmBnLzYcTrUeGiK8Hd2";
	private final static Long TOKEN_DURACION = 1_500_000L;

	public static String crearToken(String nombre, String email, String rol) {
	    long expiracionTiempo = TOKEN_DURACION;
	    Date expiracionFecha = new Date(System.currentTimeMillis() + expiracionTiempo);

	    Map<String, Object> claims = new HashMap<>();
	    claims.put("nombre", nombre);
	    claims.put("rol", rol);

	    return Jwts.builder()
	            .setSubject(email)
	            .setExpiration(expiracionFecha)
	            .addClaims(claims)
	            .signWith(Keys.hmacShaKeyFor(TOKEN_SECRETO.getBytes()))
	            .compact();
    }

	public static UsernamePasswordAuthenticationToken getAuth(String token) {
	    try {
	        Claims claims = Jwts.parserBuilder()
	                .setSigningKey(TOKEN_SECRETO.getBytes())
	                .build()
	                .parseClaimsJws(token)
	                .getBody();

	        String correo = claims.getSubject();
	        String rol = claims.get("rol", String.class);

	        if (correo != null && rol != null) {
	            var authorities = List.of(new SimpleGrantedAuthority("ROLE_" + rol));
	            return new UsernamePasswordAuthenticationToken(correo, null, authorities);
	        }

	    } catch (JwtException e) {
	        System.out.println("Token inválido: " + e.getMessage());
	    }

	    return null;
	}
}
