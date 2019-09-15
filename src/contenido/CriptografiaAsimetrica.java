package contenido;

import java.security.SecureRandom;
import java.math.BigInteger;
import java.security.SecureRandom;

public class CriptografiaAsimetrica {
  
  public static String encriptar_rsa(String cadena) {
	  
	  try {
		  String texto_cifrado = RSA.encrypt(cadena, RSA.generateKeyPair().getPublic());
		  return texto_cifrado;  
	  }catch(Exception ex) {
		  System.out.println(ex.getMessage());
		  return "ERROR";
	  }
	  
  }
}
