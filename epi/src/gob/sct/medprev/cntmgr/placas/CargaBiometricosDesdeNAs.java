/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.sct.medprev.cntmgr.placas;

/**
 *
 * @author AG SA L
 */
import com.micper.excepciones.DAOException;
import com.micper.ingsw.TParametro;
import com.micper.util.ZipDecompressor;
import com.micper.util.ZipCompress;

import gob.sct.medprev.cntmgr.CM_GetContent;
import gob.sct.medprev.dao.TDLICFFH;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.util.Properties;
import java.util.Vector;
import java.util.zip.ZipOutputStream;
import java.util.zip.ZipEntry;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class CargaBiometricosDesdeNAs {
	public ZipDecompressor DES = new ZipDecompressor();
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

	public CargaBiometricosDesdeNAs() {
		cargarConfiguraciones();

		this.keys = new String[5];
		this.keys[0] = "userid";
		this.keys[1] = "password";
		this.keys[2] = "entity";
		this.keys[3] = "mimeType";
		this.keys[4] = "lintiCveDocumen";

		this.values = new String[5];
		// this.values[0] = prop.getProperty("userid");
		this.values[0] = "db2cmuca";
		// this.values[1] = prop.getProperty("password");
		this.values[1] = "ucacmv82";
		// this.values[2] = prop.getProperty("entity");
		this.values[2] = "eLicDoc";
		this.values[3] = prop.getProperty("mimeType");
		this.values[4] = prop.getProperty("lintiCveDocumen");
		// String[] values =
		// {"db2cmuca","ucacmv82","eLicDoc","1","true",inodoctofoto};

		this.operators = new String[5];
		this.operators[0] = prop.getProperty("OPuserid");
		this.operators[1] = prop.getProperty("OPpassword");
		this.operators[2] = prop.getProperty("OPentity");
		this.operators[3] = prop.getProperty("OPmimeType");
		this.operators[4] = prop.getProperty("OPlintiCveDocumen");
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

	public byte[] obterneByteArchivos(File fichero) throws IOException {
		System.out.println("obterneByteArchivos");
		FileInputStream fos = new FileInputStream(fichero);
		byte[] zipped = new byte[(int) fichero.length()];
		fos.read(zipped);
		fos.close();
		return zipped;
	}

	public byte[] obterneByteArchivos2(File file) throws IOException {
		byte[] buffer = new byte[(int) file.length()];
		InputStream ios = null;
		try {
			ios = new FileInputStream(file);
			if (ios.read(buffer) == -1) {
				throw new IOException(
						"EOF reached while trying to read the whole file");
			}
		} finally {
			try {
				if (ios != null)
					ios.close();
			} catch (IOException e) {
			}
		}
		return buffer;
	}

	public String CargaBiometricosDesdeNas(String inicio, String fin) {
		System.out.println("metodo CargaBiometricosDesdeNas");
		String msg = "";
		// TDEXPServArchCM consulta = new TDEXPServArchCM();
		TDLICFFH consulta = new TDLICFFH();
		ZipCompress compres = new ZipCompress();
		@SuppressWarnings("rawtypes")
		Vector datos = new Vector();
		String[] archivoInodocto;
		String archivo = "";
		String inodocto = "";
		boolean resultado = false;
		String NoExisten = VParametros.getPropEspecifica("RutaNAS2")
				+ "NoExisten.txt";
		String NoCargados = VParametros.getPropEspecifica("RutaNAS2")
				+ "NoCargados.txt";
		String NoBorrado = VParametros.getPropEspecifica("RutaNAS2")
				+ "NoBorrado.txt";
		String ruta = VParametros.getPropEspecifica("RutaNAS2")
				+ "logarchivos.txt";
		String sFichero2 = "";
		String sFichero = "";
		try {
			datos = consulta.InodoctoDeFotoFirmaHuella(inicio, fin);
			for (int i = 0; i < datos.size(); i++) {
				System.out.print("Dato = " + datos.elementAt(i) + "\n");
				archivoInodocto = datos.elementAt(i).toString().split("/");
				System.out.println("size = " + archivoInodocto.length);
				archivo = archivoInodocto[0];
				inodocto = archivoInodocto[1];
				boolean JPG = false;
				if (archivo.charAt(0) == 'f') {
					System.out.println("foto");
					JPG = true;
				} else {
					System.out.println("no es foto");
					JPG = false;
				}
				System.out.println("archivo = " + archivo);
				System.out.println("inodocto = " + inodocto);
				// //////////////////////////////////BUSCANDO ARCHIVO EN LA
				// NAS/////////////////////////////////
				sFichero = ""
						+ VParametros.getPropEspecifica("RutaNAS2").toString()
						+ "" + archivo + "";
				File fichero = new File(sFichero);
				if (!fichero.exists()) {
					this.escribirEnArchivo(ruta, archivo + "/" + inodocto
							+ ";no existe en la NAS;");
					this.escribirEnArchivo(NoExisten, archivo
							+ ";no existe en la NAS;");
					System.out.println("no existe");
				} else {
					System.out.println("existe");
					this.escribirEnArchivo(ruta, archivo + "/" + inodocto
							+ ";Cargando al Content;");
					// ////////////////////// Guardando en el content
					// ///////////////////////////
					System.out.println("Guardara en content;");
					if (JPG) {
						System.out.println("JPG");
						this.subirArchivoNAS(Integer.parseInt(inodocto),
								this.obterneByteArchivos2(fichero));
					} else {
						System.out.println("BMP");
						List<String> messages = Arrays.asList("inodocto");
						// this.subirArchivoNAS(Integer.parseInt(inodocto),
						// this.obterneByteArchivos2(fichero));
						this.subirArchivoNAS(
								Integer.parseInt(inodocto),
								this.zipBytes(inodocto,
										this.obterneByteArchivos2(fichero)));
					}
					this.escribirEnArchivo(ruta, archivo
							+ ";Guardado en content;");
					System.out.println("Guardado en content;");
					// //////////////////////Guradando imagen en la nas
					// /////////////////////////
					if (JPG) {
						System.out.println("bajando a la nas v2");
						// this.bajaArchivoNASCompruebaJPG(inodocto,archivo);
						this.getImg(inodocto, archivo);
					} else {
						// this.bajaArchivoNASCompruebaBMP(inodocto,archivo);
						this.getHuella(inodocto, archivo);
					}
					this.escribirEnArchivo(ruta, archivo
							+ ";Descargando a la NAS version2;");
					System.out.println("Descargando a la NAS version2");
					// //////////////////////Comprobando la imagen existe y que
					// es mayor a 1
					// kilobyte//////////////////////////////////////////////
					sFichero2 = ""
							+ VParametros.getPropEspecifica("RutaNAS2")
									.toString() + "v2-" + archivo + "";
					File fichero2 = new File(sFichero2);
					if (!fichero2.exists()) {
						System.out
								.println("no existe la version 2 del archivo");
						this.escribirEnArchivo(ruta, archivo
								+ ";no se cargo correctamente;");
						this.escribirEnArchivo(NoCargados, archivo
								+ ";no se cargo correctamente;");
						resultado = false;
					} else {
						System.out.println("existe la version 2");
						if (fichero2.length() >= 1024) {
							// /La Imagen fue guardada y descargada
							// correctamente
							System.out.println("Archivo mayor a 1 kilobyte");
							this.escribirEnArchivo(ruta, archivo
									+ ";se cargo correctamente;");
							if (fichero.delete()) {
								System.out
										.println("El fichero ha sido borrado satisfactoriamente");
								this.escribirEnArchivo(ruta, archivo
										+ ";se borro satisfactoriamente;");
							} else {
								System.out
										.println("El fichero no puede ser borrado");
								this.escribirEnArchivo(ruta, archivo
										+ ";se borro satisfactoriamente;");
								this.escribirEnArchivo(NoBorrado, archivo
										+ ";se borro satisfactoriamente;");
							}
							resultado = true;
						} else {
							resultado = false;
							this.escribirEnArchivo(ruta, archivo
									+ ";no se cargo correctamente;");
							this.escribirEnArchivo(NoCargados, archivo
									+ ";no se cargo correctamente;");
							System.out.println("Archivo menor a 1 kilobyte");
							fichero.delete();
						}
					}
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}

		return msg;
	}

	// Metodo que inserta un documento con una clave explicita
	public int subirArchivoNAS(int iCveArchivo, byte[] btArchivo) {
		System.out.println("subirArchivoNAS");
		int resultado = 0;// Todo BIEN
		// String[] values =
		// {"db2cmuca","ucacmv82","eLicDoc","1","true",inodoctofoto};
		this.values[4] = "" + iCveArchivo;
		System.out.println("l-" + this.values[2]);
		CM_ImportBioMetricos cmImport = new CM_ImportBioMetricos();

		try {
			// ////////////////////// Guardando en el content
			// ///////////////////////////
			if (cmImport.connect(keys, values, btArchivo).compareTo("0") == 0) {
				resultado = 0;
			} else {
				resultado = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public static byte[] zipBytes(String filename, byte[] input)
			throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(baos);
		ZipEntry entry = new ZipEntry(filename + ".bmp");
		entry.setSize(input.length);
		zos.putNextEntry(entry);
		zos.write(input);
		zos.closeEntry();
		zos.close();
		return baos.toByteArray();
	}

	// public boolean escribirEnArchivo(String linea_a_guardar, String
	// nombre_archivo) {
	public boolean escribirEnArchivo(String ruta, String cadena) {
		File archivo = new File(ruta);
		try {
			FileWriter escribirArchivo = new FileWriter(archivo, true);
			BufferedWriter buffer = new BufferedWriter(escribirArchivo);
			buffer.write(cadena);
			buffer.newLine();
			buffer.close();
		} catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
		return true;
	}

	public boolean bajaArchivoNASCompruebaJPG(String cFolioGestor,
			String NombreArchivo) {
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
			btArchivo = cmImport.connect(keys2, values, operators2);

			OutputStream bos = new FileOutputStream(
					VParametros.getPropEspecifica("RutaNAS2") + "v2-"
							+ NombreArchivo + "");
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

	public boolean bajaArchivoNASCompruebaBMP(String cFolioGestor,
			String NombreArchivo) {
		System.out.println("bajaArchivoNASCompruebaBMP");
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
					vParametros.getPropEspecifica("CM_Base").toString().trim(),
					"1", "true", null };
			System.out.println(vParametros.getPropEspecifica("CM_Usuario")
					.toString()
					+ ","
					+ vParametros.getPropEspecifica("CM_Password").toString()
					+ ","
					+ vParametros.getPropEspecifica("CM_Base").toString()
							.trim() + "," + "1,true," + cFolioGestor);
			values[5] = cFolioGestor;
			btArchivo = cmImport.connect(keys2, values, operators2);
			System.out.println("bajaArchivoNASCompruebaBMP length = "
					+ btArchivo.length);
			if (btArchivo == null || btArchivo.length == 0) {
				ZipDecompressor DES = new ZipDecompressor();
				btArchivo = DES.decompress(btArchivo, ".bmp");
			}
			OutputStream bos = new FileOutputStream(
					VParametros.getPropEspecifica("RutaNAS2") + "bv2-"
							+ NombreArchivo + "");
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

	// /SE OBTIENE LA HUELLA
	public boolean getHuella(String inodoctohuella, String icveexpediente)
			throws DAOException {
		System.out.println("getHuella");
		String[] keys = { "userid", "password", "entity", "maxResults",
				"queryOP", "lintiCveDocumen" };
		String[] operators = { "", "", "", "", "", "=" };
		byte[] btArchivo = null;
		String[] values = { "db2cmuca", "ucacmv82", "eLicDoc", "1", "true",
				inodoctohuella };
		System.out
				.println("db2cmuca,ucacmv82,eLicDoc,1,true," + inodoctohuella);
		CM_GetContent cmImport = new CM_GetContent();
		try {
			btArchivo = DES.decompress(
					cmImport.connect(keys, values, operators), ".bmp");
			if (btArchivo != null) {
				System.out.println("el bmp no es nulo = " + btArchivo.length);
				OutputStream bos = new FileOutputStream(
						VParametros.getPropEspecifica("RutaNAS2") + "v2-"
								+ icveexpediente + "");
				bos.write(btArchivo);
				bos.close();
				bos = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	// /SE OBTIENE LA FOTO
	public boolean getImg(String inodoctofoto, String icveexpediente)
			throws DAOException {
		String[] keys = { "userid", "password", "entity", "maxResults",
				"queryOP", "lintiCveDocumen" };
		String[] operators = { "", "", "", "", "", "=" };
		byte[] btArchivo = null;
		String[] values = { "db2cmuca", "ucacmv82", "eLicDoc", "1", "true",
				inodoctofoto };
		CM_GetContent cmImport = new CM_GetContent();
		try {
			btArchivo = cmImport.connect(keys, values, operators);
			OutputStream bos = new FileOutputStream(
					VParametros.getPropEspecifica("RutaNAS2") + "v2-"
							+ icveexpediente + "");
			bos.write(btArchivo);
			bos.close();
			bos = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

}
