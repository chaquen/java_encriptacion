package contenido;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.apache.commons.codec.digest.DigestUtils;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;     

import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsonable;
import com.github.cliftonlabs.json_simple.JsonArray;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;


public class Contenido extends JFrame {

	private JPanel contentPane;
	private JTextField txtFldUsuario;
	private JTextField txtFldClave;
	private JLabel lblNewLabel;
	private JLabel lblPassword;
	private JButton btnGuardar;
	private JLabel lblNewLabel_1;
	private JCheckBox chckbxTodosHash;
	private JCheckBox checkMD5;
	private JCheckBox chckSha256;
	private JCheckBox chckSHA512;
	private JCheckBox chckSHA1;
	private JLabel lblMtodosAsimetricos;
	private JLabel lblMtodosSimetricos;
	private JCheckBox chckbxDes;
	private JCheckBox chckbxTodosSimetrico;
	private JCheckBox chckAES;
	private JLabel lblFuncionesHash;
	private JCheckBox chckTodosAsimetricos;
	private JCheckBox chckRSA;
	private JCheckBox chckbxDH;	
	protected Hashtable<String,String> lista_datos_encriptados = new Hashtable<String,String>();
	protected ArrayList<String> lista_algortimos = new ArrayList<String>();
	private CriptografiaAsimetrica cripto_asimetrica = new CriptografiaAsimetrica();
	private CriptografiaSimetrica cripto_simetrica = new CriptografiaSimetrica();
	private CriptografiaHash cripto_hash = new CriptografiaHash();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Contenido frame = new Contenido();					
				 
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Contenido() {
		
		lblNewLabel_1 = new JLabel("");
		chckbxTodosHash = new JCheckBox("TODOS");
			
		
		chckbxTodosSimetrico = new JCheckBox("TODOS");
		
		chckTodosAsimetricos = new JCheckBox("TODOS");
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 333);
		contentPane = new JPanel();
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{32, 156, 130, 156, 0};
		gbl_contentPane.rowHeights = new int[]{53, 27, 0, 0, 3, 0, 0, 0, 0, 0, 62, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		lblNewLabel = new JLabel("Usuario");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.SOUTH;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		lblPassword = new JLabel("Clave");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.SOUTH;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 0);
		gbc_lblPassword.gridx = 3;
		gbc_lblPassword.gridy = 0;
		contentPane.add(lblPassword, gbc_lblPassword);
		
		txtFldUsuario = new JTextField();
		txtFldUsuario.setSize(12,12);
		txtFldUsuario.setColumns(12);
		GridBagConstraints gbc_txtFldUsuario = new GridBagConstraints();
		gbc_txtFldUsuario.fill = GridBagConstraints.BOTH;
		gbc_txtFldUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_txtFldUsuario.gridx = 1;
		gbc_txtFldUsuario.gridy = 1;
		contentPane.add(txtFldUsuario, gbc_txtFldUsuario);
		
		txtFldClave = new JTextField();
		txtFldClave.setSize(12,12);
		txtFldClave.setColumns(10);
		GridBagConstraints gbc_txtFldClave = new GridBagConstraints();
		gbc_txtFldClave.fill = GridBagConstraints.BOTH;
		gbc_txtFldClave.insets = new Insets(0, 0, 5, 0);
		gbc_txtFldClave.gridx = 3;
		gbc_txtFldClave.gridy = 1;
		contentPane.add(txtFldClave, gbc_txtFldClave);
		
				
		chckbxTodosHash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			  seleccionar_algoritmo(arg0,"MD5");
		      seleccionar_algoritmo(arg0,"SHA1");
			  seleccionar_algoritmo(arg0,"SHA256");
			  seleccionar_algoritmo(arg0,"SHA512");	
			  
			  JCheckBox cbLog = (JCheckBox) arg0.getSource();
			  
			  if(cbLog.isSelected()) {
				  
				  checkMD5.setSelected(true);
				  chckSHA1.setSelected(true);
				  chckSha256.setSelected(true);
				  chckSHA512.setSelected(true);
				  /*algoritmos_asimetricos[0] = "MD5";
				  algoritmos_asimetricos[1] = "SHA1";
				  algoritmos_asimetricos[2] = "SHA256";
				  algoritmos_asimetricos[3] = "SHA512";*/
				   
				  
			  }else {
				  checkMD5.setSelected(false);
				  chckSHA1.setSelected(false);
				  chckSha256.setSelected(false);
				  chckSHA512.setSelected(false);
				  /*algoritmos_asimetricos[0] = "";
				  algoritmos_asimetricos[1] = "";
				  algoritmos_asimetricos[2] = "";
				  algoritmos_asimetricos[3] = "";*/
				  	
			  }
			}
		});
		
		lblFuncionesHash = new JLabel("Funciones Hash");
		GridBagConstraints gbc_lblFuncionesHash = new GridBagConstraints();
		gbc_lblFuncionesHash.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblFuncionesHash.anchor = GridBagConstraints.SOUTH;
		gbc_lblFuncionesHash.insets = new Insets(0, 0, 5, 5);
		gbc_lblFuncionesHash.gridx = 1;
		gbc_lblFuncionesHash.gridy = 2;
		contentPane.add(lblFuncionesHash, gbc_lblFuncionesHash);
		
		lblMtodosSimetricos = new JLabel("Métodos simetricos");
		GridBagConstraints gbc_lblMtodosSimetricos = new GridBagConstraints();
		gbc_lblMtodosSimetricos.insets = new Insets(0, 0, 5, 5);
		gbc_lblMtodosSimetricos.gridx = 2;
		gbc_lblMtodosSimetricos.gridy = 2;
		contentPane.add(lblMtodosSimetricos, gbc_lblMtodosSimetricos);
		
		lblMtodosAsimetricos = new JLabel("Métodos asimetricos");
		GridBagConstraints gbc_lblMtodosAsimetricos = new GridBagConstraints();
		gbc_lblMtodosAsimetricos.insets = new Insets(0, 0, 5, 0);
		gbc_lblMtodosAsimetricos.gridx = 3;
		gbc_lblMtodosAsimetricos.gridy = 2;
		contentPane.add(lblMtodosAsimetricos, gbc_lblMtodosAsimetricos);
		GridBagConstraints gbc_chckbxTodosHash = new GridBagConstraints();
		gbc_chckbxTodosHash.anchor = GridBagConstraints.SOUTHWEST;
		gbc_chckbxTodosHash.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxTodosHash.gridx = 1;
		gbc_chckbxTodosHash.gridy = 3;
		contentPane.add(chckbxTodosHash, gbc_chckbxTodosHash);
		
		
		chckbxTodosSimetrico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			  seleccionar_algoritmo(arg0,"DES");
			  seleccionar_algoritmo(arg0,"AES");		
			  JCheckBox cbLog = (JCheckBox) arg0.getSource();
			  if(cbLog.isSelected()) {
				  
				  chckbxDes.setSelected(true);
				  chckAES.setSelected(true);
				  
				  
			  }else {
				  chckbxDes.setSelected(false);
				  chckAES.setSelected(false);
			
				 
			  }
			}
		});
		GridBagConstraints gbc_chckbxTodosSimetrico = new GridBagConstraints();
		gbc_chckbxTodosSimetrico.anchor = GridBagConstraints.WEST;
		gbc_chckbxTodosSimetrico.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxTodosSimetrico.gridx = 2;
		gbc_chckbxTodosSimetrico.gridy = 3;
		contentPane.add(chckbxTodosSimetrico, gbc_chckbxTodosSimetrico);
		
		
		chckTodosAsimetricos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				  seleccionar_algoritmo(arg0,"RSA");
				  seleccionar_algoritmo(arg0,"DH");
				  JCheckBox cbLog = (JCheckBox) arg0.getSource();
				  if(cbLog.isSelected()) {
					  chckRSA.setSelected(true);
					  chckbxDH.setSelected(true);
					  
					  /*algoritmos_asimetricos[4] = "DES";
					  algoritmos_asimetricos[5] = "AES";*/
					  
					  
				  }else {
					  chckRSA.setSelected(false);
					  chckbxDH.setSelected(false);
					  /*algoritmos_asimetricos[4] = "";
					  algoritmos_asimetricos[5] = "";*/
					  
				  }
			}
		});
		GridBagConstraints gbc_chckTodosAsimetricos = new GridBagConstraints();
		gbc_chckTodosAsimetricos.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckTodosAsimetricos.insets = new Insets(0, 0, 5, 0);
		gbc_chckTodosAsimetricos.gridx = 3;
		gbc_chckTodosAsimetricos.gridy = 3;
		contentPane.add(chckTodosAsimetricos, gbc_chckTodosAsimetricos);
		checkMD5 = new JCheckBox("MD5");
		
		checkMD5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				seleccionar_algoritmo(arg0,"MD5");
				}
			});
		GridBagConstraints gbc_checkMD5 = new GridBagConstraints();
		gbc_checkMD5.anchor = GridBagConstraints.WEST;
		gbc_checkMD5.insets = new Insets(0, 0, 5, 5);
		gbc_checkMD5.gridx = 1;
		gbc_checkMD5.gridy = 4;
		contentPane.add(checkMD5, gbc_checkMD5);
		chckSHA1 = new JCheckBox("SHA1");
		
		
		chckSHA1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				seleccionar_algoritmo(arg0,"SHA1");
			}
		});
		chckbxDes = new JCheckBox("DES");
		
		chckbxDes.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			seleccionar_algoritmo(arg0,"DES");
			}
		});
		GridBagConstraints gbc_chckbxDes = new GridBagConstraints();
		gbc_chckbxDes.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxDes.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxDes.gridx = 2;
		gbc_chckbxDes.gridy = 4;
		contentPane.add(chckbxDes, gbc_chckbxDes);		
		chckRSA = new JCheckBox("RSA");
		
		chckRSA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				seleccionar_algoritmo(arg0,"RSA");
			}
		});
		GridBagConstraints gbc_chckRSA = new GridBagConstraints();
		gbc_chckRSA.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckRSA.insets = new Insets(0, 0, 5, 0);
		gbc_chckRSA.gridx = 3;
		gbc_chckRSA.gridy = 4;
		contentPane.add(chckRSA, gbc_chckRSA);
		GridBagConstraints gbc_chckSHA1 = new GridBagConstraints();
		gbc_chckSHA1.anchor = GridBagConstraints.WEST;
		gbc_chckSHA1.insets = new Insets(0, 0, 5, 5);
		gbc_chckSHA1.gridx = 1;
		gbc_chckSHA1.gridy = 5;
		contentPane.add(chckSHA1, gbc_chckSHA1);
		chckSha256 = new JCheckBox("SHA256");
		
		
		
		chckSha256.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				seleccionar_algoritmo(arg0,"SHA256");
			}
		});
		chckbxDH = new JCheckBox("Diffie-Hellman");
		
		
		chckbxDH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				seleccionar_algoritmo(arg0,"DH");
			}
		});
		chckAES = new JCheckBox("AES");
		
		
		chckAES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				seleccionar_algoritmo(arg0,"AES");
				}
			});
		GridBagConstraints gbc_chckAES = new GridBagConstraints();
		gbc_chckAES.anchor = GridBagConstraints.WEST;
		gbc_chckAES.insets = new Insets(0, 0, 5, 5);
		gbc_chckAES.gridx = 2;
		gbc_chckAES.gridy = 5;
		contentPane.add(chckAES, gbc_chckAES);
		GridBagConstraints gbc_chckbxDH = new GridBagConstraints();
		gbc_chckbxDH.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxDH.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxDH.gridx = 3;
		gbc_chckbxDH.gridy = 5;
		contentPane.add(chckbxDH, gbc_chckbxDH);
		GridBagConstraints gbc_chckSha256 = new GridBagConstraints();
		gbc_chckSha256.anchor = GridBagConstraints.WEST;
		gbc_chckSha256.insets = new Insets(0, 0, 5, 5);
		gbc_chckSha256.gridx = 1;
		gbc_chckSha256.gridy = 6;
		contentPane.add(chckSha256, gbc_chckSha256);
		
		
		btnGuardar = new JButton("Encriptar");
		/*EVENTO: para encrptar los datos enviados por el usuario */
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				String usuario=txtFldUsuario.getText();
				String clave=txtFldClave.getText();						
				if(!usuario.isEmpty() && !clave.isEmpty()) {
					Hashtable <String,String> lista = encriptar_datos(usuario.trim(),clave.trim(),lista_algortimos);
					lista.forEach((algortimo,cifrado) -> guardar_en_archivo(algortimo,cifrado));
					lista_algortimos.clear();
					lista.clear();
					lblNewLabel_1.setText("Datos cifrados.");
				}else {
					lblNewLabel_1.setText("Debes ingresar datos.");
				}
			}
		});
		chckSHA512 = new JCheckBox("SHA512");	
		
		chckSHA512.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {  
				   seleccionar_algoritmo(arg0,"SHA512");
				}
			});
		GridBagConstraints gbc_chckSHA512 = new GridBagConstraints();
		gbc_chckSHA512.anchor = GridBagConstraints.SOUTHWEST;
		gbc_chckSHA512.insets = new Insets(0, 0, 5, 5);
		gbc_chckSHA512.gridx = 1;
		gbc_chckSHA512.gridy = 7;
		contentPane.add(chckSHA512, gbc_chckSHA512);
		GridBagConstraints gbc_btnGuardar = new GridBagConstraints();
		gbc_btnGuardar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnGuardar.anchor = GridBagConstraints.NORTH;
		gbc_btnGuardar.insets = new Insets(0, 0, 5, 5);
		gbc_btnGuardar.gridx = 2;
		gbc_btnGuardar.gridy = 9;
		contentPane.add(btnGuardar, gbc_btnGuardar);
		
		
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 10;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
	}
	
	
	public void guardar_en_archivo(String algoritmo,String cifrado) {
		try
		{
			
			//Crear un objeto File se encarga de crear o abrir acceso a un archivo que se especifica en su constructor
			File archivo=new File("../Eje3Criptografia/criptos/texto.txt");
	
			//Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo
			FileWriter escribir=new FileWriter(archivo,true);

			//Escribimos en el archivo con el metodo write
			escribir.append(algoritmo+":"+cifrado+System.getProperty("line.separator"));

			//Cerramos la conexion
			escribir.close();
			System.out.println(algoritmo+":"+cifrado);
		}

		//Si existe un problema al escribir cae aqui
		catch(Exception e)
		{
			System.out.println("Error al escribir en archivo: "+e.getMessage());
		}
	} 
	
	
	
	
	public void seleccionar_algoritmo(ActionEvent arg,String algoritmo) {
		  JCheckBox checkbox = (JCheckBox) arg.getSource();
		  
		  int posicion=-1;
		  if(checkbox.isSelected()) {
			  lista_algortimos.add(algoritmo);
		  }else {
			  posicion =lista_algortimos.indexOf(algoritmo);
			  if(posicion>=0) {
				  lista_algortimos.remove(posicion);
			  }else {
				  System.out.println("NO se pudo eliminar el algoritmo.");
			  }
				  
			  
		  }
		  
	}
	
	
	public Hashtable<String,String> encriptar_datos(String usuario,String clave,ArrayList<String> algoritmos) {
		
		String cadena_a_encriptar=usuario+":"+clave;
		
		
		algoritmos.forEach((algoritmo) -> validar_algoritmo(algoritmo,cadena_a_encriptar));	
		
		
		return lista_datos_encriptados;
	}

	
	/*FUncion para validar algortimo seleccionado*/
	public void validar_algoritmo(String algoritmo,String cadena_a_encriptar) {
		
		switch(algoritmo) {
			case "MD5":				
				lista_datos_encriptados.put(algoritmo,CriptografiaHash.encriptar_md5(cadena_a_encriptar));
				break;
			case "SHA1":
				lista_datos_encriptados.put(algoritmo,CriptografiaHash.encriptar_sha1(cadena_a_encriptar));
				break;
			case "SHA256":
				lista_datos_encriptados.put(algoritmo,CriptografiaHash.encriptar_sha256(cadena_a_encriptar));
				break;
			case "SHA512":
				lista_datos_encriptados.put(algoritmo,CriptografiaHash.encriptar_sha512(cadena_a_encriptar));
				break;
			case "DES":
				lista_datos_encriptados.put(algoritmo,CriptografiaSimetrica.encriptar_triple_des(cadena_a_encriptar).toString());
				break;
			case "AES":				
				lista_datos_encriptados.put(algoritmo,CriptografiaSimetrica.encriptar_aes(cadena_a_encriptar).toString());
				break;
			case "RSA":
				lista_datos_encriptados.put(algoritmo,CriptografiaAsimetrica.encriptar_rsa(cadena_a_encriptar));
				break;
			case "DH":
				break;
			default:
				
				break;
		}
	}	
}
