package model;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class Encrypter {
	
	static Cipher cipher;
	static SecretKey secretKey;
	
	public String encrypt(String message, Cipher cipher, SecretKey secretKey) throws Exception {
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] bytesOriginalMessage = message.getBytes();
		byte[] bytesCipheredMessage = cipher.doFinal(bytesOriginalMessage);
		String cipheredMessage = new String(Base64.getEncoder().encodeToString(bytesCipheredMessage));
		
        return cipheredMessage;
    }

    public String decrypt(String encryptedMessage, Cipher cipher, SecretKey secretKey) throws Exception {
    	cipher.init(Cipher.DECRYPT_MODE, secretKey);
    	byte[] bytesEncryptedMessage = Base64.getDecoder().decode(encryptedMessage.getBytes());
    	byte[] bytesUncipheredMessage = cipher.doFinal(bytesEncryptedMessage);
		String uncipheredMessage = new String (bytesUncipheredMessage);
        
        return uncipheredMessage;
    }
	
}