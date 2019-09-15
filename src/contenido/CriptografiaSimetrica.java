package contenido;

public class CriptografiaSimetrica {
	public static byte[]  encriptar_triple_des(String cadena) {
	

        byte[] texto_cifrado;
		try {
			texto_cifrado = new TripleDES().encrypt(cadena);
			return texto_cifrado; // this is a byte array, you'll just see a reference to an array
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			
			return e.getMessage().getBytes(); // this is a byte array, you'll just see a reference to an array
		}
           
	}
	public static String encriptar_aes(String cadena) {
		
		final String llave_secreta = "encriptandoando";
	     
	     
	    String cadena_cifrada = AES.encrypt(cadena, llave_secreta);
	   
	     
	    
	   
		
		return cadena_cifrada;
	}
}
