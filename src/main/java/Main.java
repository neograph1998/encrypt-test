import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

public class Main {

	public static void main(String[] args) throws NoSuchAlgorithmException {

		//사용자정보
		final String ID = "user-01";
		final boolean IS_CORPORATE = true;
		final LocalDateTime GENERATE_TIME = LocalDateTime.now();

		String plainText = ID + "//" + IS_CORPORATE + "//" + GENERATE_TIME;
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
	}

}
