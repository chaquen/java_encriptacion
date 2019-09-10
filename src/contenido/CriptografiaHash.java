package contenido;

import org.apache.commons.codec.digest.DigestUtils;

public class CriptografiaHash {

	public static String encriptar_md5(String dato_a_encriptar) {
		String dato_encriptado =DigestUtils.md5Hex(dato_a_encriptar);
		return dato_encriptado;
	}
	public static String encriptar_sha1(String dato_a_encriptar) {
		String dato_encriptado =DigestUtils.sha1Hex(dato_a_encriptar);
		return dato_encriptado;
	}
	public static String encriptar_sha256(String dato_a_encriptar) {
		String dato_encriptado =DigestUtils.sha256Hex(dato_a_encriptar);
		return dato_encriptado;
		
	}
	public static String encriptar_sha512(String dato_a_encriptar) {
		String dato_encriptado =DigestUtils.sha512Hex(dato_a_encriptar);
		return dato_encriptado;
	}
}
