  package com.process.util;


import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JWTUtil {
	@Value("${app.secret}")
	private String secret;
	
	public  boolean ValiDateToken(String token,String userName) {
		String Uname=getUserName(token);
		return (userName.equals(Uname)&&  isTokenExp(token));
		
	}
	
	
	public boolean isTokenExp(String token) {
		Date expdate=getExpDate(token);
		return expdate.before(new Date(System.currentTimeMillis()));
	}
	
	public String getUserName(String token) {
		return getClaims(token).getSubject();
		
	}
	
	public Date getExpDate(String token) {
		return getClaims(token).getExpiration();
	}
	
	public Claims getClaims(String token) {
		return Jwts.parser()
				.setSigningKey(secret.getBytes())
				.parseClaimsJws(token)
				.getBody();
		
	}

	
	public String generateToken(String subject) {
		return Jwts.builder()
				.setSubject(subject)
				.setIssuer("Prashant")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+TimeUnit.MINUTES.toMillis(15)))
				.signWith(SignatureAlgorithm.HS256, secret.getBytes())
				.compact();
		 
						
	}
}
