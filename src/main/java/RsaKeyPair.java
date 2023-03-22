public class RsaKeyPair {

	final private String PUBLIC_KEY;

	final private String PRIVATE_KEY;

	public RsaKeyPair(String publicKey, String privateKey) {
		PUBLIC_KEY = publicKey;
		PRIVATE_KEY = privateKey;
	}

	public String getPUBLIC_KEY() {
		return PUBLIC_KEY;
	}

	public String getPRIVATE_KEY() {
		return PRIVATE_KEY;
	}
}
