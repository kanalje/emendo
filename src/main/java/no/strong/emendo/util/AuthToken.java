package no.strong.emendo.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class AuthToken {

	//
	private static final byte[] SECRET = "".getBytes();

	// 
	private static final long VALIDITY_LENGTH = 1000 * 60;
	
	private static Gson gson = new Gson();
	
	public static String genereateAuthToken(String id, String email) {
		String expiration = Long.toString(System.currentTimeMillis() + VALIDITY_LENGTH);
		String sign = sign(id + email + expiration);		
		Map<String, String> token = new HashMap<String, String>();
		token.put("expiration", expiration);
		token.put("id", id);
		token.put("email", email);
		token.put("sign", sign);
		return gson.toJson(token);	  
	}
	
	public static boolean verifyToken(String token) {
		Map<String, String> mappedToken = gson.fromJson(token, new TypeToken<Map<String, String>>() {}.getType());
		if (mappedToken.get("sign").equals(sign(mappedToken.get("email")+mappedToken.get("expiration")))) {
			return true;
		}
		return false;
	}
	

	private static String sign(String data) {
		Mac mac = null;
		try {
			mac = Mac.getInstance("HmacSHA256");
		} catch (NoSuchAlgorithmException e) {}
		SecretKeySpec secret = new SecretKeySpec(SECRET, mac.getAlgorithm());
		try {
			mac.init(secret);
		} catch (InvalidKeyException e) {}
		byte[] digest = mac.doFinal(data.getBytes());
		return Hex.encodeHexString(digest);
	}
}
