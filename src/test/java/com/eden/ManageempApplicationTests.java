package com.eden;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@SpringBootTest
class ManageempApplicationTests {

	@Test
	public void testGenJwt() {
		//System.out.println("token Generate");
		Keys.secretKeyFor(SignatureAlgorithm.HS256);
	}
}
