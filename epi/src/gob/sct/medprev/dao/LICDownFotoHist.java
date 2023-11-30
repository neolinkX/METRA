/*
 * To change this template, choose Tools | Templates 
 * and open the template in the editor.
 */

package gob.sct.medprev.dao;

import java.io.*;
import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.vo.*;
import com.micper.sql.*;
import com.micper.util.*;
//import gob.sct.elic.cntmgr.*;
import gob.sct.medprev.cntmgr.*;
import gob.sct.medprev.cntmgr.placas.AdministradorContenidos;

public class LICDownFotoHist extends DAOBase {
	private TParametro VParametros = new TParametro("7");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");
	public ZipDecompressor DES = new ZipDecompressor();
	private java.sql.Date dtUltimoBorrado = null;

	// TEntorno vEntorno = new TEntorno();
	// TParametro vParametros = new TParametro(vEntorno.getNumModuloStr());

	/**
	 * Ejecuta cualquier query enviado en cWhere y regresa a las entidades
	 * encontradas.
	 * 
	 * @param cKey
	 *            String - Cadena de Campos que definen la llave de cada entidad
	 *            encontrada.
	 * @param cWhere
	 *            String - Cadena que contiene al query a ejecutar.
	 * @throws DAOException
	 *             - Excepción de tipo DAO
	 * @return Vector - Arreglo que contiene a las entidades (VOs) encontrados
	 *         por el query.
	 */
	public Vector findByCustom(String cKey, String cWhere) throws DAOException {
		Vector vcRecords = new Vector();
		cError = "";
		try {
			String cSQL = cWhere;
		} catch (Exception e) {
			cError = e.toString();
		} finally {
			if (!cError.equals(""))
				throw new DAOException(cError);
			return vcRecords;
		}
	}

	/**
	 * Metodo definido para borrar los archivos de fechas anteriores
	 */
	private void borrarAnteriores(String cRuta) {
		try {
			TFechas dtFecha = new TFechas();
			if (this.dtUltimoBorrado == null
					|| !this.dtUltimoBorrado.equals(dtFecha.TodaySQL())) {
				// Realizar el borrado
				File dir = new File(cRuta.toString());
				String[] ls = dir.list();
				if (ls.length > 100) {
					for (int k = 0; k < ls.length; k++) {
						File file = new File(cRuta, ls[k]);
						if (file.isFile()
								&& new java.sql.Date(file.lastModified())
										.compareTo(dtFecha.TodaySQL()) < 0) {
							if (file.getAbsoluteFile().delete()) {
								; // info("Eliminado: " + cNomArchivo);
							}
						} // Es un archivo y su fecha es menor al día de hoy
					}// Se tienen mas de 100 archivos.
				}// Recorrer toda la lista
				this.dtUltimoBorrado = dtFecha.TodaySQL();
			} // La fecha es nula o diferente al día de hoy
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // Clase borrarAnteriores

	// /SE OBTIENE LA FOTO
	public boolean getImg(String inodoctofoto, String icveexpediente,
			Connection cnNested, int examen) throws DAOException {

		String[] keys = { "userid", "password", "entity", "maxResults",
				"queryOP", "lintiCveDocumen" };
		String[] operators = { "", "", "", "", "", "=" };
		byte[] btArchivo = null;
		String[] values = { "db2cmuca", "ucacmv82", "eLicDoc", "1", "true",
				inodoctofoto };
		CM_GetContent cmImport = new CM_GetContent();
		try {
			AdministradorContenidos content = new AdministradorContenidos();
			btArchivo = cmImport.connect(keys, values, operators);
			OutputStream bos = new FileOutputStream(
					VParametros.getPropEspecifica("RutaNASH") + "f-"
							+ icveexpediente + "ne" + examen + ".jpg");
			
			if(btArchivo.length == 0){
				byte[] bytesArchivo = null;
				bytesArchivo = content.bajarArchivoFoto(inodoctofoto);
				//System.out.println("=====================");
				//System.out.println("bytesConsultaBio = "+bytesArchivo.length);
				btArchivo = bytesArchivo;
			}
			
			bos.write(btArchivo);
			bos.close();
			bos = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	// /SE OBTIENE LA FOTO DEL ULTIMO DICTAMENE PARA SOLICITAR HUELLAS
	public boolean getImgSolHuella(String inodoctofoto, String icveexpediente) throws DAOException {
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
					VParametros.getPropEspecifica("RutaNASH") + "f-"
							+ icveexpediente + "-SolHuella.jpg");
			bos.write(btArchivo);
			bos.close();
			bos = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	// /SE OBTIENE LA FIRMA
	public boolean getFirma(String inodoctofirma, String icveexpediente,
			Connection cnNested, int examen) throws DAOException {
		String[] keys = { "userid", "password", "entity", "maxResults",
				"queryOP", "lintiCveDocumen" };
		String[] operators = { "", "", "", "", "", "=" };
		byte[] btArchivo = null;
		String[] values = { "db2cmuca", "ucacmv82", "eLicDoc", "1", "true",
				inodoctofirma };
		CM_GetContent cmImport = new CM_GetContent();
		try {
			AdministradorContenidos content = new AdministradorContenidos();
			btArchivo = DES.decompress(
					cmImport.connect(keys, values, operators), ".bmp");
			OutputStream bos = new FileOutputStream(
					VParametros.getPropEspecifica("RutaNASH") + "r-"
							+ icveexpediente + "ne" + examen + ".bmp");
			
			if(btArchivo.length == 0){
				byte[] bytesArchivo = null;
				bytesArchivo = content.bajarArchivoFirma(inodoctofirma);
				//System.out.println("=====================");
				//System.out.println("bytesConsultaBio = "+bytesArchivo.length);
				btArchivo = bytesArchivo;
			}
			
			bos.write(btArchivo);
			bos.close();
			bos = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	// /SE OBTIENE LA HUELLA
	public boolean getHuella(String inodoctohuella, String icveexpediente,
			Connection cnNested, int examen) throws DAOException {
		String[] keys = { "userid", "password", "entity", "maxResults",
				"queryOP", "lintiCveDocumen" };
		String[] operators = { "", "", "", "", "", "=" };
		byte[] btArchivo = null;
		String[] values = { "db2cmuca", "ucacmv82", "eLicDoc", "1", "true",
				inodoctohuella };
		CM_GetContent cmImport = new CM_GetContent();
		try {
			AdministradorContenidos content = new AdministradorContenidos();
			btArchivo = DES.decompress(
					cmImport.connect(keys, values, operators), ".bmp");
			if (btArchivo != null) {
				OutputStream bos = new FileOutputStream(
						VParametros.getPropEspecifica("RutaNASH") + "h-"
								+ icveexpediente + "ne" + examen + ".bmp");

				if(btArchivo.length == 0){
					byte[] bytesArchivo = null;
					bytesArchivo = content.bajarArchivoFirma(inodoctohuella);
					//System.out.println("=====================");
					//System.out.println("bytesConsultaBio = "+bytesArchivo.length);
					btArchivo = bytesArchivo;
				}
				
				bos.write(btArchivo);
				bos.close();
				bos = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean getTemplate(TVDinRep vData, Connection cnNested)
			throws DAOException {

		String cSQL = " select iCveSolicitud, "
				+ "       (select MAX(iNoDocto) " + "         from LICFFH F "
				+ "         where F.iCvePersonal = S.iCvePersonal "
				+ "          and F.iCveTipoFFH = 3 ) as iNoDocto "
				+ " from LICSolicitud S " + " where S.iCveSolicitud in ("
				+ vData.getString("cSolicitud") + ")";

		Vector vcListado = findByCustom("", cSQL);

		if (vcListado.size() > 0) {
			String[] keys = { "userid", "password", "entity", "maxResults",
					"queryOP", "lintiCveDocumen" };
			String[] operators = { "", "", "", "", "", "=" };
			String cTipoArchivo = VParametros
					.getPropEspecifica("LIC_ExtensionTemplate");
			for (int i = 0; i < vcListado.size(); i++) {
				TVDinRep vDocto = (TVDinRep) vcListado.get(i);
				byte[] btArchivo = null;
				String[] values = { "db2cmuca", "ucacmv82", "eLicDoc", "1",
						"true", vDocto.getString("INODOCTO") };
				CM_GetContent cmImport = new CM_GetContent();
				try {
					btArchivo = DES.decompress(
							cmImport.connect(keys, values, operators),
							cTipoArchivo);
					if (btArchivo != null) {
						OutputStream bos = new FileOutputStream(
								vData.getString("cRutaGuardar") + "/"
										+ cTipoArchivo);
						vData.put("cNombreHuellaArchivo", cTipoArchivo);
						bos.write(btArchivo);
						bos.close();
						bos = null;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} // Recorrer el vector
		}
		return true;
	}

}
