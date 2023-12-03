package model;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.*;

public class Authenticator {

	public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        return generator.generateKeyPair();
    }

    public static String encryptText(String text, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] bytesOriginalText = text.getBytes();
        byte[] bytesCipheredText = cipher.doFinal(bytesOriginalText);
        return Base64.getEncoder().encodeToString(bytesCipheredText);
    }

    public static String decryptText(String cipheredText, PrivateKey privateKey) throws Exception {
        Cipher uncipher = Cipher.getInstance("RSA");
        uncipher.init(Cipher.DECRYPT_MODE, privateKey);

        byte[] bytesCipheredText = Base64.getDecoder().decode(cipheredText);
        byte[] bytesUncipheredText = uncipher.doFinal(bytesCipheredText);
        return new String(bytesUncipheredText);
    }
}
