package com.examtracking.util;
import java.security.Key;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
public class JwtImpl {
	
	private static final Key secret = MacProvider.generateKey(SignatureAlgorithm.HS256);
    private static final byte[] secretBytes = secret.getEncoded();
    private static final String base64SecretBytes = Base64.getEncoder().encodeToString(secretBytes);
    
    /**
     * This Method Generates new Token
     * @param username
     * @return token
     */
    public static String generateToken(String username) {
    	
        String id = UUID.randomUUID().toString().replace("-", "");
        Date now = new Date();
        Date exp = new Date(System.currentTimeMillis() + (1000 * 30)); // 30 seconds

        String token = Jwts.builder()
            .setId(id)
            .setIssuedAt(now)
            //adding user data into token payload
            .setSubject(username)
            .setNotBefore(now)
            .setExpiration(exp)
            .signWith(SignatureAlgorithm.HS256, base64SecretBytes)
            .compact();

        return token;
    }
    
    /**
     * This Method Verify token provided by client
     * @param token
     * @return token Id
     */
    public static String verifyToken(String token) {
        Claims claims = Jwts.parser()
            .setSigningKey(base64SecretBytes)
            .parseClaimsJws(token).getBody();
//        System.out.println("----------------------------");
//        System.out.println("ID: " + claims.getId());
//        System.out.println("Subject: " + claims.getSubject());
//        System.out.println("Issuer: " + claims.getIssuer());
//        System.out.println("Expiration: " + claims.getExpiration());
		return claims.getId();
    }


    /**
     * This Method return JSON object in original format
     * @param Element
     * @return JSON
     */
    public JsonObject getOriginalElement(String Element) {
    	JsonParser parser = new JsonParser();
    	
    	//getting JSON object
		JsonElement jsonTree = parser.parse(Element);
		
		//converting into Original format
		JsonObject jsonObject = jsonTree.getAsJsonObject();
		return jsonObject;
    	
    }
}
