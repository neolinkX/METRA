/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.sct.medprev.cntmgr.placas;

/**
 *
 * @author Ivan Santiago Mï¿½ndez 
 * Modificada por AG SA
 */
import com.micper.ingsw.TParametro;
import com.micper.util.ZipDecompressor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;
import gob.sct.medprev.dao.TDEXPServArchCM;

import java.io.FileNotFoundException;

public class AdministradorContenidosServicios {

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

	public AdministradorContenidosServicios() {
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

	// Metodo que inserta un documento con una clave explicita
	public int subirArchivo(int iCveArchivo, byte[] btArchivo) {
		int resultado = 0;// Todo BIEN
		this.values[4] = "" + iCveArchivo;

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

	// Metodo que inserta un documento con una clave explicita
	public int subirArchivoNAS(int iCveArchivo, byte[] btArchivo) {
		int resultado = 0;// Todo BIEN
		this.values[4] = "" + iCveArchivo;

		CM_Import cmImport = new CM_Import();

		try {
			// ////////////////////// Guardando en el content
			// ///////////////////////////
			if (cmImport.connect(keys, values, btArchivo).compareTo("0") == 0) {
				resultado = 0;
			} else {
				resultado = 1;
			}
			// ////////////////////// Comprueba no es nulo y escribe en el log
			// ////////////////////////////////////
			boolean valida = false;
			valida = this.bajarArchivoCompruebaExiste(iCveArchivo);
			String folio = Integer.toString(iCveArchivo);
			String ruta = VParametros.getPropEspecifica("RutaNASServicios")
					+ "logarchivos.txt";
			if (valida) {
				// this.escribirEnArchivo(ruta,folio+" cargado correctamente");
				//System.out.println(folio + " cargado correctamente");
			} else {
				this.escribirEnArchivo(ruta, folio
						+ ";no se cargo correctamente;");
				//System.out.println(folio + " no se cargo correctamente");
			}
			// //////////////////////Guradando imagen en la nas
			// /////////////////////////
			// System.out.println("archivo Destino servicio:" + "/nas/" + "/" +
			// iCveArchivo + ".jpg");
			OutputStream bos = new FileOutputStream(
					VParametros.getPropEspecifica("RutaNASServicios") + ""
							+ iCveArchivo + ".jpg");
			bos.write(btArchivo);
			bos.close();
			bos = null;
			// ////////////////////////////////////////////////////////////////////
		} catch (Exception e) {
			e.printStackTrace();
			resultado = 1;
		}
		return resultado;
	}

	// Metodo que inserta un documento 31 DE MAYO 2016 AG SA
		public int subirArchivoNASV2(int iCveArchivo, byte[] btArchivo) {
			int resultado = 0;// Todo BIEN
			this.values[4] = "" + iCveArchivo;
			CM_Import cmImport = new CM_Import();
			try {
				//////////////////////// Guardando en la NAS
				OutputStream bos = new FileOutputStream(
						VParametros.getPropEspecifica("RutaNASServicios") + ""
								+ iCveArchivo + ".jpg");
				bos.write(btArchivo);
				bos.close();
				bos = null;
				/*bos = new FileOutputStream(
						VParametros.getPropEspecifica("RutaNASServicios") + ""
								+ iCveArchivo + "-2.jpg");
				bos.write(btArchivo);
				bos.close();
				bos = null;*/
				//////////////////////// Guardando en el content
				/*if (cmImport.connect(keys, values, btArchivo).compareTo("0") == 0) {
					resultado = 0;
				} else {
					resultado = 1;
				}*/
				// ////////////////////// Comprueba no es nulo y se cargo correctamente
				/*boolean valida = false;
				valida = this.CompruebaCargaExitosa(iCveArchivo);
				if(valida){
					resultado = 0;
				}else{
					resultado = 1;
				}
				String folio = Integer.toString(iCveArchivo);*/
				// ////////////////////////////////////////////////////////////////////
			} catch (Exception e) {
				e.printStackTrace();
				resultado = 1;
			}
			return resultado;
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

	public boolean bajarArchivoCompruebaExiste(int cFolioGestor) {
		 System.out.println("bajarArchivoCompruebaExiste " +cFolioGestor);
		boolean msg = false;
		byte[] btArchivo = null;
		String sFichero = ""
				+ VParametros.getPropEspecifica("RutaNASServicios").toString() + ""
				+ cFolioGestor + ".jpg";
		File fichero = new File(sFichero);
		if (!fichero.exists()) {
			// dLICDownFoto.getImg(Integer.toString(inodoctos[0]),
			// ClaveExpediente, conn);
			// System.out.println("no existe");
			CM_GetContent cmImport = new CM_GetContent();
			try {
				String[] keys2 = { "userid", "password", "entity",
						"maxResults", "queryOP", "lintiCveDocumen" };
				String[] operators2 = { "", "", "", "", "", "=" };
				TParametro vParametros = new TParametro("7");
				String[] values = {
						vParametros.getPropEspecifica("CM_Usuario").toString(),
						vParametros.getPropEspecifica("CM_Password").toString(),
						vParametros.getPropEspecifica("CM_Base_Varios")
								.toString().trim(), "1", "true", null };
				values[5] = cFolioGestor + "";
				btArchivo = cmImport.connectMedPrevDoc(keys2, values,
						operators2);
				if (btArchivo == null || btArchivo.length == 0) {
					ZipDecompressor DES = new ZipDecompressor();
					btArchivo = DES.decompress(btArchivo, ".jpg");
				}

				if (btArchivo == null) {
					// System.out.println("El archivo no se guardó en el content manager");
					msg = false;
				} else {
					// System.out.println("con datos");
					msg = true;
				}

				/*
				 * OutputStream bos = new
				 * FileOutputStream(VParametros.getPropEspecifica
				 * ("RutaNASServicios")+"" + cFolioGestor + ".jpg");
				 * bos.write(btArchivo); bos.close(); bos = null;
				 */
			} catch (Exception e) {
				e.printStackTrace();
				btArchivo = null;
			}

		}
		return msg;
	}

	public boolean CompruebaCargaExitosa(int cFolioGestor) throws Exception {
		 //System.out.println("bajarArchivoCompruebaExiste " +cFolioGestor);
		boolean msg = false;
		byte[] btArchivo = null;
		String sFichero = ""+ VParametros.getPropEspecifica("RutaNASServicios").toString() + ""+ cFolioGestor + ".jpg";
		File fichero = new File(sFichero);
		int resultado = 0;
		if (fichero.exists()) {
			if (fichero.length()>10) {
				for(int i = 0; i < 3 ; i ++){ ///Este ciclo se repetira 3 ocasiones en caso de que el archivo no se descargue correctamente del Content
					//System.out.println("Este ciclo se repetira 3 ocasiones en caso de que el archivo no se descargue correctamente del Content");
					////// Descarga archivo del content //////
						CM_GetContent cmImport = new CM_GetContent();
						String[] keys2 = { "userid", "password", "entity",
								"maxResults", "queryOP", "lintiCveDocumen" };
						String[] operators2 = { "", "", "", "", "", "=" };
						TParametro vParametros = new TParametro("7");
						String[] values = {
								vParametros.getPropEspecifica("CM_Usuario").toString(),
								vParametros.getPropEspecifica("CM_Password").toString(),
								vParametros.getPropEspecifica("CM_Base_Varios")
										.toString().trim(), "1", "true", null };
						values[5] = cFolioGestor + "";
						try {
							btArchivo = cmImport.connectMedPrevDoc(keys2, values,operators2);
						} catch (Exception e) {
							e.printStackTrace();
							btArchivo = null;
						}
						//System.out.println("Descarga archivo del content ");
					////// Elimina si existe una archivo de comprobacion //////
						String sFichero2 = ""+ VParametros.getPropEspecifica("RutaNASServicios").toString() + ""+ cFolioGestor + "CE.jpg";
						File fichero2 = new File(sFichero2);
						if (fichero2.exists()) {
							fichero2.delete();
						}
						//System.out.println("Elimina si existe una archivo de comprobacion ");
					////// Guarda archivo en la NAS //////
						OutputStream bos = new FileOutputStream(VParametros.getPropEspecifica ("RutaNASServicios")+"" + cFolioGestor + "CE.jpg");
						bos.write(btArchivo); 
						bos.close(); 
						bos = null;
						//System.out.println("Guarda archivo en la NAS");
					////// Verifica que el archivo sea mayor a 0 bytes //////
						if (fichero2.exists()) {
							//System.out.println("Existe fichero original");
							if(fichero2.length()>10){
								msg = true;
								i=5;
								//System.out.println("Comprueba que el archivo es mayor a 0 bytes");
								//System.out.println("Archivo Original = "+fichero.length()+" bytes");
								//System.out.println("Archivo descargado = "+fichero2.length()+" bytes");
							}else{
								////// Carga nuevamente el documento
								CM_Import cmImport2 = new CM_Import();
								this.values[4] = "" + cFolioGestor;
								cmImport2.connect(keys, values, this.obterneByteArchivos(fichero));
								//System.out.println("Carga nuevamente el documento");
							}
						}
						////Elimina el ultimo archivo descargado a la NAS generado para la Comprobacion
						fichero2.delete();
				}
			}else{
				fichero.delete();
			}
		}
		//System.out.println("Supero el ciclo con resultado = "+ msg);
		return msg;
	}

	public int subirArchivo(int iCveArchivo, byte[] btArchivo, String cTipoDocto) {
		int resultado = 0;// Todo BIEN
		String[] keys2 = new String[6];
		keys2[0] = this.keys[0];
		keys2[1] = this.keys[1];
		keys2[2] = this.keys[2];
		keys2[3] = this.keys[3];
		keys2[4] = this.keys[4];
		keys2[5] = "Tipo de Doc";

		String[] values2 = new String[6];
		values2[0] = this.values[0];
		values2[1] = this.values[1];
		values2[2] = this.values[2];
		values2[3] = this.values[3];
		values2[4] = "" + iCveArchivo;
		values2[5] = cTipoDocto;
		this.values[4] = "" + iCveArchivo;

		CM_Import cmImport = new CM_Import();
		try {
			if (cmImport.connect(keys2, values2, btArchivo).compareTo("0") == 0) {
				resultado = 0;
			} else {
				resultado = 1;
			}
		} catch (Exception ex) {
			resultado = 0;
		}

		return resultado;
	}

	public byte[] bajarArchivo(String cFolioGestor) {
		byte[] btArchivo = null;
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
		} catch (Exception e) {
			e.printStackTrace();
			btArchivo = null;
		}
		return btArchivo;
	}

	public byte[] bajarArchivoNAS(String cFolioGestor) throws IOException {
	    //System.out.println(cFolioGestor);
		byte[] btArchivo = null;
		String sFichero = ""
				+ VParametros.getPropEspecifica("RutaNASServicios").toString() + ""
				+ cFolioGestor + ".jpg";
		File fichero = new File(sFichero);
		
		///Elimina el archivo si es igual a 0 bytes
		if(fichero.length() < 10){
			fichero.delete();
		}
		if (!fichero.exists()) {
			// dLICDownFoto.getImg(Integer.toString(inodoctos[0]),
			// ClaveExpediente, conn);
			//System.out.println("no existe");
			CM_GetContent cmImport = new CM_GetContent();
			try {
				String[] keys2 = { "userid", "password", "entity",
						"maxResults", "queryOP", "lintiCveDocumen" };
				String[] operators2 = { "", "", "", "", "", "=" };
				TParametro vParametros = new TParametro("7");
				String[] values = {
						vParametros.getPropEspecifica("CM_Usuario").toString(),
						vParametros.getPropEspecifica("CM_Password").toString(),
						vParametros.getPropEspecifica("CM_Base_Varios")
								.toString().trim(), "1", "true", null };
				values[5] = cFolioGestor;
				btArchivo = cmImport.connectMedPrevDoc(keys2, values,
						operators2);
				if (btArchivo == null || btArchivo.length == 0) {
					ZipDecompressor DES = new ZipDecompressor();
					btArchivo = DES.decompress(btArchivo, ".jpg");
				}
				OutputStream bos = new FileOutputStream(
						VParametros.getPropEspecifica("RutaNASServicios") + ""
								+ cFolioGestor + ".jpg");
				//System.out.println("Ruta Nas ="	+ VParametros.getPropEspecifica("RutaNASServicios") + ""+ cFolioGestor + ".jpg");
				bos.write(btArchivo);
				bos.close();
				bos = null;
			} catch (Exception e) {
				e.printStackTrace();
				btArchivo = null;
			}
			if(fichero.length() < 10){
				fichero.delete();
			}

		} else {
			//System.out.println("existe");
		}
		//System.out.println("el archivo a sido guardado");
		long tamanoArch = fichero.length();
		btArchivo = new byte[(int) tamanoArch];
		try {
			//System.out.println("try--");
			// Nos creamos esta variable para poder leer el archivo.
			FileInputStream docu = new FileInputStream(fichero);
			// Leemos los bytes del archivo y a la vez se van insertando en el
			// array de bytes creado.
			int numBytes = docu.read(btArchivo);
			//System.out.println("El archivo tiene " + numBytes + " de bytes.");
			docu.close(); // Muy importante cerrar tras la lectura.
			// System.out.println("cerrando conexion--");
		} catch (FileNotFoundException e) {
			System.out.println("No se ha encontrado el archivo-1.");
		} catch (IOException e) {
			System.out.println("No se ha podido leer el archivo.");
		}
		return btArchivo;
	}

	public byte[] bajarArchivoCompruebaExiste(String cFolioGestor) {
		// System.out.println(cFolioGestor);
		byte[] btArchivo = null;
		String sFichero = ""
				+ VParametros.getPropEspecifica("RutaNASServicios").toString() + ""
				+ cFolioGestor + ".jpg";
		File fichero = new File(sFichero);
		if (!fichero.exists()) {
			// dLICDownFoto.getImg(Integer.toString(inodoctos[0]),
			// ClaveExpediente, conn);
			// System.out.println("no existe");
			CM_GetContent cmImport = new CM_GetContent();
			try {
				String[] keys2 = { "userid", "password", "entity",
						"maxResults", "queryOP", "lintiCveDocumen" };
				String[] operators2 = { "", "", "", "", "", "=" };
				TParametro vParametros = new TParametro("7");
				String[] values = {
						vParametros.getPropEspecifica("CM_Usuario").toString(),
						vParametros.getPropEspecifica("CM_Password").toString(),
						vParametros.getPropEspecifica("CM_Base_Varios")
								.toString().trim(), "1", "true", null };

				values[5] = cFolioGestor;
				btArchivo = cmImport.connectMedPrevDoc(keys2, values,
						operators2);
				if (btArchivo == null || btArchivo.length == 0) {
					ZipDecompressor DES = new ZipDecompressor();
					btArchivo = DES.decompress(btArchivo, ".jpg");
				}
				OutputStream bos = new FileOutputStream(
						VParametros.getPropEspecifica("RutaNASServicios") + ""
								+ cFolioGestor + ".jpg");
				bos.write(btArchivo);
				bos.close();
				bos = null;
			} catch (Exception e) {
				e.printStackTrace();
				btArchivo = null;
			}

		} else {
			//System.out.println("existe");
		}
		// System.out.println("el archivo a sido guardado");
		long tamanoArch = fichero.length();
		btArchivo = new byte[(int) tamanoArch];
		try {
			// System.out.println("try--");
			// Nos creamos esta variable para poder leer el archivo.
			FileInputStream docu = new FileInputStream(fichero);
			// System.out.println("El archivo tiene " + numBytes +
			// " de bytes.");
			docu.close(); // Muy importante cerrar tras la lectura.
			// System.out.println("cerrando conexion--");
		} catch (FileNotFoundException e) {
			System.out.println("No se ha encontrado el archivo-2.");
		} catch (IOException e) {
			System.out.println("No se ha podido leer el archivo.");
		}

		return btArchivo;
	}


	
	
	public byte[] bajarArchivoFotoBiometrica(String cFolioGestor) {
		byte[] btArchivo = null;
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

			values[5] = cFolioGestor;
			btArchivo = cmImport.connecteLicDoc(keys2, values, operators2);
			if (btArchivo == null || btArchivo.length == 0) {
				ZipDecompressor DES = new ZipDecompressor();
				btArchivo = DES.decompress(btArchivo, ".jpg");
			}
		} catch (Exception e) {
			e.printStackTrace();
			btArchivo = null;
		}
		return btArchivo;
	}

	public byte[] bajarArchivoFoto(String cFolioGestor) {
		//System.out.println("AdministradorContenidos - bajarArchivoFoto-"+ cFolioGestor);
		byte[] btArchivo = null;
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

			values[5] = cFolioGestor;
			btArchivo = cmImport.connecteLicDoc(keys2, values, operators2);
			if (btArchivo == null || btArchivo.length == 0) {
				ZipDecompressor DES = new ZipDecompressor();
				btArchivo = DES.decompress(btArchivo, ".jpg");
			}
		} catch (Exception e) {
			e.printStackTrace();
			btArchivo = null;
		}
		return btArchivo;
	}

	public byte[] bajarArchivoHuellaBMP(String cFolioGestor) {
		// System.out.print("Solicita HUELLa");;
		byte[] btHuella = buscarHuellaNAS(cFolioGestor);
		// System.out.print("busco en la NAS");
		if (btHuella == null) {// si en la NAS no existe
			// System.out.print("La bajara del content");
			btHuella = bajarArchivoHuellaBMPContentDirecto(cFolioGestor);
			// System.out.print("la bajo con "+btHuella.length +" de tamano");
		}

		return btHuella;
	}

	public byte[] bajarArchivoHuellaPacienteBMP(String cFolioGestor) {
		 //System.out.println("Solicita HUELLa bajarArchivoHuellaPacienteBMP");;
		//byte[] btHuella = buscarHuellaNASPacientesConIcveDocto(cFolioGestor);
		byte[] btHuella = null;
		// System.out.print("busco en la NAS");
		if (btHuella == null) {// si en la NAS no existe
		 //System.out.println("La bajara del content");
			btHuella = bajarArchivoHuellaPacienteBMPContentDirecto(cFolioGestor);
		 //System.out.println("la bajo con "+btHuella.length +" de tamano");
		}

		return btHuella;
	}

	public byte[] bajarArchivoHuellaBMPContentDirecto(String cFolioGestor) {
		byte[] btHuella = null;
		byte[] btArchivo = null;

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

			values[5] = cFolioGestor;
			TParametro VParametros = new TParametro("7");
			btArchivo = cmImport.connecteLicDoc(keys2, values, operators2);
			if (btArchivo != null) {
				ZipDecompressor DES = new ZipDecompressor();
				btHuella = DES.decompress(btArchivo, ".bmp");
				if (btHuella != null) {// Si tiene la huella la escribe en la
										// NAS
					OutputStream bos = new FileOutputStream(
							VParametros.getPropEspecifica("RutaNASMedicos")
									+ "h-" + cFolioGestor + ".bmp");
					bos.write(btHuella);
					bos.close();
					bos = null;
					// System.out.print("HUELLA GUARDADA EN LA NAS");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			btArchivo = null;
		}
		return btHuella;
	}

	public byte[] bajarArchivoHuellaPacienteBMPContentDirecto(
			String cFolioGestor) {
		byte[] btHuella = null;
		byte[] btArchivo = null;

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

			values[5] = cFolioGestor;
			TParametro VParametros = new TParametro("7");
			btArchivo = cmImport.connecteLicDoc(keys2, values, operators2);
			//System.out.println("btArchivo = "+btArchivo.length);
			if (btArchivo != null) {
				ZipDecompressor DES = new ZipDecompressor();
				btHuella = DES.decompress(btArchivo, ".bmp");
				//System.out.println(btHuella.length);
				if (btHuella != null) {// Si tiene la huella la escribe en la
										// NAS
					OutputStream bos = new FileOutputStream(
							VParametros.getPropEspecifica("RutaNAS2")
									+ "hdocto-" + cFolioGestor + ".bmp");
					bos.write(btHuella);
					bos.close();
					bos = null;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			//btArchivo = null;
		}
		return btHuella;
	}

	public byte[] buscarHuellaNAS(String cFolioGestor) {
		TParametro VParametros2 = new TParametro("7");
		String sFichero = ""
				+ VParametros2.getPropEspecifica("RutaNASMedicos").toString()
				+ "h-" + cFolioGestor + ".bmp";
		File fichero = new File(sFichero);
		byte[] btHuella = null;
		if (!fichero.exists()) {
			btHuella = null;
		} else {
			try {
				FileInputStream fos = new FileInputStream(fichero);
				byte[] fileinBytes = new byte[(int) fichero.length()];
				fos.read(fileinBytes);
				fos.close();
				btHuella = fileinBytes;
			} catch (Exception er) {
				er.printStackTrace();
			}
		}
		return btHuella;
	}

	public byte[] buscarHuellaNASPacientesConExpediente(String cFolioGestor) {
		TParametro VParametros2 = new TParametro("7");
		String sFichero = ""
				+ VParametros2.getPropEspecifica("RutaNAS2").toString() + "h-"
				+ cFolioGestor + ".bmp";
		File fichero = new File(sFichero);
		byte[] btHuella = null;
		if (!fichero.exists()) {
			btHuella = null;
		} else {
			try {
				FileInputStream fos = new FileInputStream(fichero);
				byte[] fileinBytes = new byte[(int) fichero.length()];
				fos.read(fileinBytes);
				fos.close();
				btHuella = fileinBytes;
			} catch (Exception er) {
				er.printStackTrace();
			}
		}
		return btHuella;
	}

	public byte[] buscarHuellaNASPacientesConIcveDocto(String cFolioGestor) {
		TParametro VParametros2 = new TParametro("7");
		String sFichero = ""
				+ VParametros2.getPropEspecifica("RutaNAS2").toString()
				+ "hdocto-" + cFolioGestor + ".bmp";
		File fichero = new File(sFichero);
		byte[] btHuella = null;
		if (!fichero.exists()) {
			btHuella = null;
		} else {
			try {
				FileInputStream fos = new FileInputStream(fichero);
				byte[] fileinBytes = new byte[(int) fichero.length()];
				fos.read(fileinBytes);
				fos.close();
				btHuella = fileinBytes;
			} catch (Exception er) {
				er.printStackTrace();
			}
		}
		return btHuella;
	}

	public byte[] bajarArchivoFirma(String cFolioGestor) {
		byte[] btArchivo = null;
		byte[] btFirma = null;
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

			values[5] = cFolioGestor;
			btArchivo = cmImport.connecteLicDoc(keys2, values, operators2);
			if (btArchivo != null) {
				ZipDecompressor DES = new ZipDecompressor();
				btFirma = DES.decompress(btArchivo, ".bmp");
				/* dictamen.setFirma(btFirma); */
				if (btFirma == null || btFirma.length == 0) {
					btFirma = DES.decompress(btArchivo, ".jpg");
				}
				btArchivo = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			btFirma = null;
		}
		return btFirma;
	}

	public byte[] bajarArchivoHuella(String cFolioGestor) {
		byte[] btArchivo = null;
		byte[] btHuella = null;
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

			values[5] = cFolioGestor;
			btArchivo = cmImport.connecteLicDoc(keys2, values, operators2);
			if (btArchivo != null) {
				ZipDecompressor DES = new ZipDecompressor();
				btHuella = DES.decompress(btArchivo, ".bmp");
				if (btHuella == null || btHuella.length == 0) {
					btHuella = DES.decompress(btArchivo, ".jpg");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			btHuella = null;
		}
		return btHuella;
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

	public String CargaDeServiciosDesdeNas(String inicio, String fin) {
		System.out.println("metodo CargaDeServiciosDesdeNas");
		String msg = "";
		TDEXPServArchCM consulta = new TDEXPServArchCM();
		@SuppressWarnings("rawtypes")
		Vector datos = new Vector();
		try {
			datos = consulta.InodoctoDeServicios(inicio, fin);
			for (int i = 0; i < datos.size(); i++) {
				System.out.print(datos.elementAt(i) + "\t");
				// //////////////////////////////////BUSCANDO ARCHIVO EN LA
				// NAS/////////////////////////////////
				String sFichero = ""
						+ VParametros.getPropEspecifica("RutaNASServicios").toString()
						+ "" + datos.elementAt(i).toString() + ".jpg";
				File fichero = new File(sFichero);
				if (!fichero.exists()) {
					//System.out.println("no existe");
				} else {
					//System.out.println("existe");
					this.subirArchivoNAS(
							Integer.parseInt(datos.elementAt(i).toString()),
							this.obterneByteArchivos(fichero));
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}

		return msg;
	}

	public byte[] obterneByteArchivos(File fichero) throws IOException {
		FileInputStream fos = new FileInputStream(fichero);
		byte[] zipped = new byte[(int) fichero.length()];
		fos.read(zipped);
		fos.close();
		return zipped;
	}

	public byte[] obterneByteError() throws IOException{
		////Obtenemos el ByteError///
		URL u = new URL("http://aplicaciones9.sct.gob.mx/imagenes/medprev/img/medprev/lsct.jpg");
		int contentLength = u.openConnection().getContentLength();
		InputStream openStream = u.openStream();
		byte[] ficheroError = new byte[contentLength];
		//openStream.read(binaryData);
		//openStream.close();
		return ficheroError;
	}

}
