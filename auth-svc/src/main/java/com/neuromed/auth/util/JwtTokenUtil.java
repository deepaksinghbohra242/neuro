package com.neuromed.auth.util;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;

@Component
public class JwtTokenUtil {

	@Value("${jwt.secret}")
	private String secret;

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("username", userDetails.getUsername());
		claims.put("authorities", userDetails.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(",")));
		claims.put("roles", userDetails.getAuthorities().stream()
				.map(auth -> auth.getAuthority().replace("ROLE_", ""))
				.collect(Collectors.toList()));
		SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
		return Jwts.builder()
				.issuer("Neuromed Clinic")
				.subject(userDetails.getUsername())
				.claims(claims)
				.issuedAt(new Date())
				.expiration(new Date((new Date()).getTime() + 30000000))
				.signWith(secretKey)
				.compact();
	}



	public String getUsernameFromToken(String jwtToken) {
		return getClaimFromToken(jwtToken, Claims::getSubject);
	}
	
	private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
		final Claims claims = Jwts.parser().verifyWith(secretKey)
				.build().parseSignedClaims(token).getPayload();
		return claimsResolver.apply(claims);
	}


	public boolean validateToken(String jwtToken, UserDetails userDetails) {
		final String username = getUsernameFromToken(jwtToken);
		return username.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken);
		
	}

	private boolean isTokenExpired(String jwtToken) {
		final Date expiration = getExpirationDateFromToken(jwtToken);
		return expiration.before(new Date());
	}

	private Date getExpirationDateFromToken(String jwtToken) {
		return getClaimFromToken(jwtToken, Claims::getExpiration);
	}
}
























