package cn.curleyg.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA加密
 * 非对称加密，有公钥和私钥之分，公钥用于数据加密，私钥用于数据解密。加密结果可逆
 * 公钥一般提供给外部进行使用，私钥需要放置在服务器端保证安全性。
 * 特点：加密安全性很高，但是加密速度较慢
 *
 */
public class RSAUtil {

	private static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCvZKAoICHBTcFuEC37cHnz+CSROqBsgpQTIv5QktxL9SYn2MXkQ+H7MWEy2hQUzfct7+4grZH/n4cJeeMt8nOoUYfxdHLFRvl8T2QF0eQlUBX7dI94nWSxifItAdhN2pIaptFFYShpBKGlOlpQQQALJDFb3GvJKrJlnJsCowF0HwIDAQAB";

	private static final String PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAK9koCggIcFNwW4QLftwefP4JJE6oGyClBMi/lCS3Ev1JifYxeRD4fsxYTLaFBTN9y3v7iCtkf+fhwl54y3yc6hRh/F0csVG+XxPZAXR5CVQFft0j3idZLGJ8i0B2E3akhqm0UVhKGkEoaU6WlBBAAskMVvca8kqsmWcmwKjAXQfAgMBAAECgYB08wDDoKFBVop6zDkM5nO61KP1XoUntW3s0LGIolRYJY+alDVnvkfJiaSJFta7BJcjv08l5PZr30BYdjigI4EwBLjqhzCgWSOZuSQag+nLGHxML8HFThiFRx43xZoqCU3NNcUQNMpg/Ejo8S4STgJyO4Yta6CZwrJLjxGbFgDRiQJBAPxtZLECgS739vrJC+/y5MKZOsbRuM9dWsDGXKwpnjReZC0unDboLKjPVAINQ3fmlzjxgXf50ifIDtnXT5aTS4sCQQCx4B49rRDRXIwXqbYtuRbIG8znj/1yOBVYaJOIa831BRt62LEQFaLGU0YVNb4yULBb172PJVkrrUPcJDaal9w9AkEApm9OY7OeD6TpEIO8vjHtiS1U5sBi1T7dVpuzgMRUDn1qGaxQzZcZ0xgYcN3j7Ut5y87YqY1rdlVfDVz8mfB2bwJBAJLE1q8ValcWAhJUWpoHqyCar8wyJ3JjjFlSkMl0CpEuEaqfuhoHgDQc6ir+GV3y7lIaBbNjodxEL0YqK1QMEhECQBVv32HQ0qNdMj8jPZ9WQPFHca572nKKvcxXXYEo48Fh6qh92iD+XLx5zix4sRhDRj8BdHQ0xhNhAbWRu7zAX7E=";

	public static void main(String[] args) throws Exception{
		//加密
		String str = RSAUtil.encrypt("123456");
		System.out.println(str);
		//解密
		String str2=RSAUtil.decrypt(str);
		System.out.println(str2);
	}

	public static String getPublicKeyStr(){
		return PUBLIC_KEY;
	}

	public static RSAPublicKey getPublicKey() throws Exception {
		byte[] decoded = Base64.decodeBase64(PUBLIC_KEY);
		return (RSAPublicKey) KeyFactory.getInstance("RSA")
				.generatePublic(new X509EncodedKeySpec(decoded));
	}

	public static RSAPrivateKey getPrivateKey() throws Exception {
		byte[] decoded = Base64.decodeBase64(PRIVATE_KEY);
		return (RSAPrivateKey) KeyFactory.getInstance("RSA")
				.generatePrivate(new PKCS8EncodedKeySpec(decoded));
	}
	
	public static RSAKey generateKeyPair() throws NoSuchAlgorithmException {
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
		keyPairGen.initialize(1024, new SecureRandom());
		KeyPair keyPair = keyPairGen.generateKeyPair();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
		String privateKeyString = new String(Base64.encodeBase64(privateKey.getEncoded()));
		return new RSAKey(privateKey, privateKeyString, publicKey, publicKeyString);
	}

	public static String encrypt(String source) throws Exception {
		byte[] decoded = Base64.decodeBase64(PUBLIC_KEY);
		RSAPublicKey rsaPublicKey = (RSAPublicKey) KeyFactory.getInstance("RSA")
				.generatePublic(new X509EncodedKeySpec(decoded));
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(1, rsaPublicKey);
		return Base64.encodeBase64String(cipher.doFinal(source.getBytes(StandardCharsets.UTF_8)));
	}

	public static Cipher getCipher() throws Exception {
		byte[] decoded = Base64.decodeBase64(PRIVATE_KEY);
		RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) KeyFactory.getInstance("RSA")
				.generatePrivate(new PKCS8EncodedKeySpec(decoded));
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(2, rsaPrivateKey);
		return cipher;
	}

	public static String decrypt(String text) throws Exception {
		Cipher cipher = getCipher();
		byte[] inputByte = Base64.decodeBase64(text.getBytes(StandardCharsets.UTF_8));
		return new String(cipher.doFinal(inputByte));
	}
	
	public static class RSAKey {
		  private RSAPrivateKey privateKey;
		  private String privateKeyString;
		  private RSAPublicKey publicKey;
		  public String publicKeyString;

		  public RSAKey(RSAPrivateKey privateKey, String privateKeyString, RSAPublicKey publicKey, String publicKeyString) {
		    this.privateKey = privateKey;
		    this.privateKeyString = privateKeyString;
		    this.publicKey = publicKey;
		    this.publicKeyString = publicKeyString;
		  }

		  public RSAPrivateKey getPrivateKey() {
		    return this.privateKey;
		  }

		  public void setPrivateKey(RSAPrivateKey privateKey) {
		    this.privateKey = privateKey;
		  }

		  public String getPrivateKeyString() {
		    return this.privateKeyString;
		  }

		  public void setPrivateKeyString(String privateKeyString) {
		    this.privateKeyString = privateKeyString;
		  }

		  public RSAPublicKey getPublicKey() {
		    return this.publicKey;
		  }

		  public void setPublicKey(RSAPublicKey publicKey) {
		    this.publicKey = publicKey;
		  }

		  public String getPublicKeyString() {
		    return this.publicKeyString;
		  }

		  public void setPublicKeyString(String publicKeyString) {
		    this.publicKeyString = publicKeyString;
		  }
		}
}