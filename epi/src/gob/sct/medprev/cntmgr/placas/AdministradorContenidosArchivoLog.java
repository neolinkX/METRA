/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.sct.medprev.cntmgr.placas;

/**
 *
 * @author AG SA L
 */
import com.micper.ingsw.TParametro;
import com.micper.util.ZipDecompressor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.Vector;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class AdministradorContenidosArchivoLog {

	private TParametro VParametros = new TParametro("7");

	private Properties prop = null;
	private final String paquetePropiedades = "/gob/sct/medprev/cntmgr/placas/contentManager.properties";

	public Properties getProp() {
		return prop;
	}

	public void setProp(Properties prop) {
		this.prop = prop;
	}

	public String[] getKeys() {
		return keys;
	}

	public void setKeys(String[] keys) {
		this.keys = keys;
	}

	public String[] getValues() {
		return values;
	}

	public void setValues(String[] values) {
		this.values = values;
	}

	public String[] getOperators() {
		return operators;
	}

	public void setOperators(String[] operators) {
		this.operators = operators;
	}

	private String[] keys = null;
	private String[] values = null;
	private String[] operators = null;

	private BufferedReader reader;

	private BufferedWriter writer;

	public AdministradorContenidosArchivoLog() {
		cargarConfiguraciones();

		this.keys = new String[5];
		this.keys[0] = "userid";
		this.keys[1] = "password";
		this.keys[2] = "entity";
		this.keys[3] = "mimeType";
		this.keys[4] = "lintiCveDocumen";

		this.values = new String[5];
		this.values[0] = prop.getProperty("userid");
		this.values[1] = prop.getProperty("password");
		this.values[2] = prop.getProperty("entity");
		this.values[3] = prop.getProperty("mimeType");
		this.values[4] = prop.getProperty("lintiCveDocumen");

		this.operators = new String[5];
		this.operators[0] = prop.getProperty("OPuserid");
		this.operators[1] = prop.getProperty("OPpassword");
		this.operators[2] = prop.getProperty("OPentity");
		this.operators[3] = prop.getProperty("OPmimeType");
		this.operators[4] = prop.getProperty("OPlintiCveDocumen");
	}

	// Metodo que incerta un documento con la clave por default que es -1
	public int subirArchivo(byte[] btArchivo) {
		int resultado = 0;// Todo BIEN

		CM_Import cmImport = new CM_Import();
		try {
			if (cmImport.connect(keys, values, btArchivo).compareTo("0") == 0) {
				resultado = 0;
			} else {
				resultado = 1;
			}
		} catch (Exception ex) {
			resultado = 0;
		}

		return resultado;
	}

	public void cargarConfiguraciones() {
		prop = new Properties();
		try {
			prop.load(this.getClass().getResource(paquetePropiedades)
					.openStream());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	// /////////// Leer lienas registradas de archivos fallidos en el log//////
	public boolean LeerLineasDeArchivo() {
		boolean regresa = false;
		Vector<String> datos = new Vector<String>();
		try {
			String ruta = VParametros.getPropEspecifica("RutaNASSM")
					+ "logarchivos.txt";
			// Validar que el archivo Existe
			File fichero = new File(ruta);
			if (!fichero.exists()) {
				// System.out.println("no existe");
			} else {
				// System.out.println("existe");
				// Abrimos el archivo
				FileInputStream fstream = new FileInputStream(ruta);
				// Creamos el objeto de entrada
				DataInputStream entrada = new DataInputStream(fstream);
				// Creamos el Buffer de Lectura
				BufferedReader buffer = new BufferedReader(
						new InputStreamReader(entrada));
				String strLinea = "";
				String strLinea2 = "";
				boolean borrarlinea = false;
				// Leer el archivo linea por linea
				while ((strLinea = buffer.readLine()) != null) {
					strLinea2 = strLinea;
					// Obtener clave de documento
					StringTokenizer tokens = new StringTokenizer(strLinea, ";");
					strLinea = tokens.nextToken();
					// Imprimimos la línea por pantalla
					System.out.println("Subir archivo=" + strLinea);
					// Llamando metodo que carga archivos de log a nas
					borrarlinea = this.CargaDeServiciosDesdeLog(strLinea);
					//System.out.println("Borrar linea=" + borrarlinea);
					if (borrarlinea) {
						//System.out.println("Borrar lineas");
						datos.addElement(strLinea2);
					}
				}
				// Cerramos el archivo
				entrada.close();
				// /Borramos Lineas
				if (datos.size() > 0) {
					for (int i = 0; i < datos.size(); i++) {
						//System.out.print(datos.elementAt(i) + "\t");
						this.BorrarLineaDeArchivo2(ruta, datos.elementAt(i)
								.toString());
					}
				}

			}
		} catch (Exception e) { // Catch de excepciones
			System.err.println("Ocurrio un error: " + e.getMessage());
		}
		return regresa;
	}

	// ///////////Cargar Servicios desde el Log /////////////////////
	public boolean CargaDeServiciosDesdeLog(String icveDocumento) {
		//System.out.println("metodo CargaDeServiciosDesdeLog");
		boolean msg = false;
		try {
			// //////////////////////////////////BUSCANDO ARCHIVO EN LA
			// NAS/////////////////////////////////
			String sFichero = ""
					+ VParametros.getPropEspecifica("RutaNASSM").toString()
					+ "" + icveDocumento + ".jpg";
			File fichero = new File(sFichero);
			if (!fichero.exists()) {
				//System.out.println("no existe archivo original en la NAS, se borrara linea");
				msg = true;
			} else {
				//System.out.println("existe archivo original en la NAS, subir archivo");
				msg = this.subirArchivoDesdeLog(
						Integer.parseInt(icveDocumento),
						this.obterneByteArchivos(fichero));
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return msg;
	}

	// Metodo que inserta un documento con una clave explicita
	public boolean subirArchivoDesdeLog(int iCveArchivo, byte[] btArchivo) {
		boolean resultado = false;// Todo BIEN
		this.values[4] = "" + iCveArchivo;

		try {
			// ////////////////////// Guardando en el content
			// ///////////////////////////
			this.subirArchivo(btArchivo);
			// //////////////////////Guradando imagen en la nas
			// /////////////////////////
			this.bajaArchivoNASCompruebaLog(Integer.toString(iCveArchivo));
			// //////////////////////Comprobando la imagen existe y que es mayor
			// a 1 kilobyte//////////////////////////////////////////////
			String sFichero = ""
					+ VParametros.getPropEspecifica("RutaNASSM").toString()
					+ "" + iCveArchivo + "-2.jpg";
			File fichero = new File(sFichero);
			if (!fichero.exists()) {
				//System.out.println("no existe la version -2 del archivo");
				resultado = false;
			} else {
				//System.out.println("existe");
				if (fichero.length() >= 1024) {
					// /La Imagen fue guardada y descargada correctamente
					//System.out.println("Archivo mayor a 1 kilobyte");
					resultado = true;
				} else {
					resultado = false;
					//System.out.println("Archivo menor a 1 kilobyte");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			//System.out.println("subirArchivoDesdeLog" + e);
		}
		return resultado;
	}

	public boolean bajaArchivoNASCompruebaLog(String cFolioGestor) {
		// System.out.println(cFolioGestor);
		byte[] btArchivo = null;
		boolean cargado = false;
		CM_GetContent cmImport = new CM_GetContent();
		try {
			String[] keys2 = { "userid", "password", "entity", "maxResults",
					"queryOP", "lintiCveDocumen" };
			String[] operators2 = { "", "", "", "", "", "=" };
			TParametro vParametros = new TParametro("7");
			String[] values = {
					vParametros.getPropEspecifica("CM_Usuario").toString(),
					vParametros.getPropEspecifica("CM_Password").toString(),
					vParametros.getPropEspecifica("CM_Base_Varios").toString()
							.trim(), "1", "true", null };

			values[5] = cFolioGestor;
			btArchivo = cmImport.connectMedPrevDoc(keys2, values, operators2);
			if (btArchivo == null || btArchivo.length == 0) {
				ZipDecompressor DES = new ZipDecompressor();
				btArchivo = DES.decompress(btArchivo, ".jpg");
			}
			OutputStream bos = new FileOutputStream(
					VParametros.getPropEspecifica("RutaNASSM") + ""
							+ cFolioGestor + "-2.jpg");
			bos.write(btArchivo);
			bos.close();
			bos = null;
			cargado = true;
		} catch (Exception e) {
			e.printStackTrace();
			btArchivo = null;
		}
		return cargado;
	}

	public boolean BorrarLineaDeArchivo(String Archivo, String lineToRemove) {
		File inputFile = new File(Archivo);
		File tempFile = new File(VParametros.getPropEspecifica("RutaNASSM")
				+ "myTempFile.txt");
		boolean successful = false;
		try {
			reader = new BufferedReader(new FileReader(inputFile));
			writer = new BufferedWriter(new FileWriter(tempFile));
			String currentLine;
			while ((currentLine = reader.readLine()) != null) {
				// trim newline when comparing with lineToRemove
				String trimmedLine = currentLine.trim();
				if (trimmedLine.equals(lineToRemove))
					continue;
				writer.write(currentLine + "\n");
			}

			reader.close();
			writer.close();
			successful = tempFile.renameTo(inputFile);

		} catch (IOException e) {
			System.out.println(e);
		}
		return successful;
	}

	public boolean BorrarLineaDeArchivo2(String Archivo, String lineToRemove) {
		// Create original file and new temp file
		File inputFile = new File(Archivo);
		File tempFile = new File(VParametros.getPropEspecifica("RutaNASSM")
				+ "myTempFile.txt");
		boolean successful = false;
		boolean stringFound = false;
		boolean deleteFile = false;

		try {
			BufferedReader reader = new BufferedReader(
					new FileReader(inputFile));
			PrintWriter writer = new PrintWriter(new FileWriter(tempFile));
			String currentLine;
			String userInput;

			//System.out.println("Enter the item you want to delete.");
			//System.out.println("Remember if your item had spaces, use "	+ "underscores:");
			// userInput = kboard.next();
			// lineToRemove = userInput;

			while ((currentLine = reader.readLine()) != null) {
				// trim newline when comparing with lineToRemove
				String trimmedLine = currentLine.trim();
				if (trimmedLine.equalsIgnoreCase(lineToRemove)) {
					stringFound = true;
					continue;
				}
				writer.println(currentLine);
			}

			if (stringFound == true) {
				reader.close();
				writer.close();
				deleteFile = inputFile.delete();
				successful = tempFile.renameTo(inputFile);
				if (!successful) {
					//System.out.println("Sorry we couldn't complete your "+ "request due to an unexpected error");
				} else {
					//System.out.println(lineToRemove + " was deleted "+ "of your list!");
				}
			} else {
				//System.out.println("Sorry this item was not found.");
			}

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return successful;
	}

	public byte[] obterneByteArchivos(File fichero) throws IOException {
		FileInputStream fos = new FileInputStream(fichero);
		byte[] zipped = new byte[(int) fichero.length()];
		fos.read(zipped);
		fos.close();
		return zipped;
	}

}
