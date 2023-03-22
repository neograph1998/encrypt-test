import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class Main {

	public static void main(String[] args) throws NoSuchAlgorithmException, JsonProcessingException {

		//사용자정보
		final String ID = "user-01";
		final boolean IS_CORPORATE = true;
		final LocalDateTime GENERATE_TIME = LocalDateTime.now();

		UserInfo userInfo = new UserInfo(ID, IS_CORPORATE, GENERATE_TIME);

		ObjectMapper objectMapper = new ObjectMapper();
		String plainText = objectMapper.registerModule(new JavaTimeModule()).writeValueAsString(userInfo);
		System.out.println(plainText);

		//여성가족재단서버

		//SEED256 암호화
		String seedEncrypt = Seed256Util.encrypt(plainText);
		System.out.println(seedEncrypt);

		//RSA 암호화
		RsaKeyPair rsaKeyPair = RsaUtil.createKeypairAsString();
		String rsaEncrypt = RsaUtil.encrypt(seedEncrypt, rsaKeyPair.getPUBLIC_KEY());

		//네오 서버

		//RSA 복호화
		String rsaDecrypt = RsaUtil.decrypt(rsaEncrypt, rsaKeyPair.getPRIVATE_KEY());
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
