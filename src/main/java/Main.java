import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Base64;

public class Main {

	public static void main(String[] args) throws NoSuchAlgorithmException, JsonProcessingException {

		//사용자정보
		final String ID = "user-01";
		final String NAME = "테스트";
		final boolean IS_CORPORATE = true;
		final LocalDateTime GENERATE_TIME = LocalDateTime.now();

		UserInfo userInfo = new UserInfo(ID, NAME, IS_CORPORATE, GENERATE_TIME);

		ObjectMapper objectMapper = new ObjectMapper();
		String plainText = objectMapper.registerModule(new JavaTimeModule()).writeValueAsString(userInfo);
		System.out.println(plainText);

		//여성가족재단서버

		//SEED256 암호화
		String seedEncrypt = Seed256Util.encrypt(plainText);
		System.out.println(seedEncrypt);

		//RSA 암호화
		RsaKeyPair rsaKeyPair = RsaUtil.createKeypairAsString();
		String rsaEncrypt = RsaUtil.encrypt(seedEncrypt, "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsLHTJiWpyZ5tJMJZEB5yJZy+hdcVIsWMJtL4xmWbVo8O9FNInjtNbkB7JcjEjPTKgk2nc7ElXi98ILATrItd65LsJBbCDHZZpFi4D369ORnTcF2Mc3JcT0tO5h3spbydxfvBCE7wcmouZMKuwHuDUev/WQlJgO6p5weAopcAeKMTAbrnIOz/gC6/jHHfVMp1mRJDr2PMZON4fOoisyYCwzfPDxr2Zrah6EBvkPjwywiB8yCaTTAWCAUklcPhRvLs+T46DNteSuw8B8qXqANuqLwJB37aN+y3KaBf2WkTNvMQe2L5J2NWpwBgx67qjLqqEezLM9m3WC2pd+8y1DFWAQIDAQAB");

		String encodingRsaEncrypt = Base64.getUrlEncoder().encodeToString(rsaEncrypt.getBytes());
		System.out.println("encodingRsaEncrypt: " + encodingRsaEncrypt);

		//네오 서버

		String decodingRsaEncrypt = new String(Base64.getUrlDecoder().decode(encodingRsaEncrypt));
		System.out.println("decodingRsaEncrypt: " + decodingRsaEncrypt);

		//RSA 복호화
		String rsaDecrypt = RsaUtil.decrypt(rsaEncrypt, "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCwsdMmJanJnm0kwlkQHnIlnL6F1xUixYwm0vjGZZtWjw70U0ieO01uQHslyMSM9MqCTadzsSVeL3wgsBOsi13rkuwkFsIMdlmkWLgPfr05GdNwXYxzclxPS07mHeylvJ3F+8EITvByai5kwq7Ae4NR6/9ZCUmA7qnnB4CilwB4oxMBuucg7P+ALr+Mcd9UynWZEkOvY8xk43h86iKzJgLDN88PGvZmtqHoQG+Q+PDLCIHzIJpNMBYIBSSVw+FG8uz5PjoM215K7DwHypeoA26ovAkHfto37LcpoF/ZaRM28xB7YvknY1anAGDHruqMuqoR7Msz2bdYLal37zLUMVYBAgMBAAECggEBAIHHmtcCAttbyb6eINx+AEkv/6A+gf+wKWi6vI68QoQpP0qlgUj3NcAtDEaMWBFQGw3qC3wmONQc/IblzQ2Gr80/dMJtFMoig2T65PY8mK9AENUPYQHKlZKINcM/Z3m0OA4bA4UetezToI/RjplCHwok1MCI2bwhkBlooUrKrkXsBVm5VA+Pm9DGwZYTohogFkPi4q7wnlO8KB/1d8GnGS1Yov8PbZjqRYTcJzbE9ZK5poMQcU8fD0Vlrm5bThHoQm6hCDZpO6B/lvYhzNox5o0bGRT7iYrzGi85VnE0HrQuyHCLCQoWT3cQjFbN8zT9btqRAuPwaYbulPNQJzA04AECgYEA9jRrKEz1+86y0Q/KOVmQT23bIWJP1ZtaKa79Xwh2BRaibyV2Ua+RQdScarpvTnAi3hSrb7UZeQ3Oi7WFDPPeUaQKYS1zwiiQ2vGLj7favyDLOW5FkmHOFIjFTKn40TUzBWXPSwNno7kODo1nAYe1KllbNFZ58Cno8WhqDKuaX+ECgYEAt7lzEgsgsRESUc5xfHfgKXdd5q8r+XyvUtMLDwVGNMpQRycGOC2uIHmSgsJOWCgEGLfU1FAZLKyq+6WFxdTFG4e6mM0aKkWGhh04QBPngER/DWAEZ1Qd15X83nUHw9shtGD+H0k26jX6Ia+cZWipPQ0byuo0m3JiWl0uo7XSOiECgYBL4sxEl7QZ8RPcrGlAVALO+KVaHNtOk5uuzWj7Kbh2yQs4Buv5MYwsJkQwAVbdbmLQ4L4WqFM5pK/NEB36aBBWSaGJYmETrz02vsytd5XM42bAROTcwZira5ykjJ9MnIElYMQL8vi21gsnIR5mslV8wB4Ee8MJ62EJR42oahwjAQKBgB5DKsQfFxxzbmwRMDpLWJ4b7liwI/MtaYegAaNQwBaz+HMv52NbiuIIhGyLavI0bTBRH3p1UyA7tNJMmnNSr8DwI3lF13h9CUKQD5LmdGx5W+AtH6yRe3TmXMebyVKmhq+6hkFLAwOjLndcAMvnaHEI9rn+x/mO5spxrNwEeANhAoGAXEPPiL5GHIbcWvQBoXtZS7yaKVY92dA/LXCcU23GFBLPfGcwBtm0KhFBK7KNoP9r27iPcof7syNi4DrwmIQpnh7myhCqkCFLHFTOQSDt+L02zZT9H8/zO6BGo4aULXJCAMue7ezGaYyV/2/kC2Y9pvQ7qr9o05y/xxBureGnplM=");
		System.out.println(rsaDecrypt);

		//SEED256 복호화
		String seedDecrypt = Seed256Util.decrypt(rsaDecrypt);
		System.out.println(seedDecrypt);

		assert seedDecrypt != null;
		System.out.println(seedDecrypt.equals(plainText));

		UserInfo userInfoDecrypt = objectMapper.registerModule(new JavaTimeModule()).readValue(seedDecrypt, UserInfo.class);
		System.out.println(userInfoDecrypt.toString());
	}

}
