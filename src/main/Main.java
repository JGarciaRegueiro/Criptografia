package main;

import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import entities.User;
import model.Encrypter;
import model.UserDaoImpl;

public class Main {
	
	public static User user = new User();
	public static UserDaoImpl udao = new UserDaoImpl();
	
	public static SecretKey secretKey;
	public static Cipher cipher;
	private static Encrypter encrypter = new Encrypter();
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		int option = 0;
		boolean auth =false;
		boolean exit = false;
		String message = "";
		String encryptedMessage = "";
//		Authenticator.init();
		int counter = 0;
		
		try {
			KeyGenerator generator = KeyGenerator.getInstance("AES");
			secretKey = generator.generateKey();
			cipher = Cipher.getInstance("AES");
			udao.userDaoImpl();
			
			while(counter < 3 && !auth) {
				
				System.out.println("Introduce tu nombre de usuario:");
				user.setUser(scanner.nextLine());
				System.out.println("Introduce tu contraseña:");
				user.setPass(scanner.nextLine());
				counter++;
				
				if (udao.userExist(user)) {
					auth=true;
					while(!exit) {
						System.out.println("------------MENU------------");
						System.out.println("1. Encriptar");
						System.out.println("2. Desencriptar");
						System.out.println("3. Salir");
						option = scanner.nextInt();
						scanner.nextLine();
						
						switch(option) {
							case 1:
								System.out.println("Introduce la frase a encriptar:");
								message = scanner.nextLine();
								encryptedMessage = encrypter.encrypt(message, cipher, secretKey);
								System.out.println("Aquí la frase encriptada: " + encryptedMessage);
								break;
							case 2:
								if (!encryptedMessage.isEmpty()) {
									System.out.println("Aquí la frase desencriptada: " + encrypter.decrypt(encryptedMessage,cipher, secretKey));
								}else {
									System.out.println("No hay ninguna frase encriptada");
								}
								break;
							case 3:
								exit = true;
								break;
							default:
								System.out.println("Opción no válida");
								break;
						}
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR");
		}
		scanner.close();
		System.out.println("Fin de programa");
		
	}
}
