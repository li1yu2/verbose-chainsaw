package com.eden;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@SpringBootTest
class ManageempApplicationTests {

	@Test
	public void testGenJwt() {
		//System.out.println("token Generate");
		SecretKey signKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
		//验证->signKey+有效时间+用户名=令牌
		System.out.println(signKey.toString());
		int expire=3600000;
		
		Map<String,Object> claims=new HashMap<>();
		claims.put("id", 1);
		claims.put("name", "tom");
		
		String jwt = Jwts.builder()
		.addClaims(claims)
		.signWith(signKey,SignatureAlgorithm.HS256)
		.setExpiration(new Date(System.currentTimeMillis()+expire))
		.compact();
		System.out.println(jwt);
		
		Map<String,Object>tokenResolt=new HashMap<>();
		tokenResolt = Jwts.parser().setSigningKey(signKey).parseClaimsJws(jwt).getBody();
		System.out.println(tokenResolt);
	}
}
