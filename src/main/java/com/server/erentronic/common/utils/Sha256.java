package com.server.erentronic.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.stereotype.Component;

@Component
public class Sha256 {

	public String encrypt(String value) {
		StringBuilder sb = new StringBuilder();

		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(value.getBytes());
			byte byteData[] = md.digest();

			for (byte tmpStrByte : byteData) {
				String tmpEncTxt = Integer.toString((tmpStrByte & 0xff) + 0x100, 16).substring(1);

				sb.append(tmpEncTxt);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}
}
