package model;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import entities.User;

public class UserDaoImpl {
	
	private List<User> userList = new ArrayList<>();
	private KeyPair keys;
	private PrivateKey privateKey;
	private PublicKey publicKey;
	
	public void userDaoImpl(){
		
		try {
			keys = Authenticator.generateKeyPair();
			privateKey = keys.getPrivate();
			publicKey = keys.getPublic();
			
			User user1 = new User("user1", Authenticator.encryptText(("pass1"), publicKey));
			User user2 = new User("user2", Authenticator.encryptText(("pass2"), publicKey));
			User user3 = new User("user3", Authenticator.encryptText(("pass3"), publicKey));
			
			userList.add(user1);
			userList.add(user2);
			userList.add(user3);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean userExist(User user) {
		for (User u : userList) {
	        try {
				if(u.getUser().equals(user.getUser()) && Authenticator.decryptText(u.getPass(), privateKey).equals(user.getPass())) {
				    return true;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    return false;
	}
}
