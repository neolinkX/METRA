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
import gob.sct.medprev.dwr.vo.ExpBitMod;
import gob.sct.medprev.vo.TVEXPExamAplica;
import gob.sct.medprev.vo.TVPERDatos;

public class AdminNAS extends DAOBase {
	private TParametro VParametros = new TParametro("7");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");
	public ZipDecompressor DES = new ZipDecompressor();
	private java.sql.Date dtUltimoBorrado = null;

	/**
	 * @autor AG SA L
	 * @fecha 3 de marzo 2015
	 */

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

	/**
	 * Metodo definido para borrar los biometricos de un expediente en la NAS
	 */
	public boolean EliminaBiometricos(String cExpediente) {
		boolean respuesta = false;
		String foto = VParametros.getPropEspecifica("RutaNAS2") + "f-"
				+ cExpediente + ".jpg";
		String firma = VParametros.getPropEspecifica("RutaNAS2") + "r-"
				+ cExpediente + ".bmp";
		String huella = VParametros.getPropEspecifica("RutaNAS2") + "h-"
				+ cExpediente + ".bmp";

		// System.out.println(foto);
		File ficherof = new File(foto);
		File ficheror = new File(firma);
		File ficheroh = new File(huella);
		int NumExamenMax = 0;
		TDEXPExamAplica dExamAplica = new TDEXPExamAplica();

		try {

			// /Elimina Foto
			if (ficherof.delete()) {
				// System.out.println("El fichero foto ha sido borrado satisfactoriamente");
			} else {
				// System.out.println("El fichero foto no puede ser borrado");
			}
			// /Elimina Firma
			if (ficheror.delete()) {
				// System.out.println("El fichero foto ha sido borrado satisfactoriamente");
			} else {
				// System.out.println("El fichero foto no puede ser borrado");
			}
			// /Elimina Huella
			if (ficheroh.delete()) {
				// System.out.println("El fichero foto ha sido borrado satisfactoriamente");
			} else {
				// System.out.println("El fichero foto no puede ser borrado");
			}

			// /////////////Borrar biometricos de examenes
			// Histiricos////////////////////
			NumExamenMax = dExamAplica.FindByMaxExamen(Integer
					.parseInt(cExpediente));

			for (int i = 0; i <= NumExamenMax; i++) {
				String fotohist = VParametros.getPropEspecifica("RutaNASH")
						+ "f-" + cExpediente + "ne"+i+".jpg";
				String firmahist = VParametros.getPropEspecifica("RutaNASH")
						+ "r-" + cExpediente +"ne"+i+ ".bmp";
				String huellahist = VParametros.getPropEspecifica("RutaNASH")
						+ "h-" + cExpediente +"ne"+i+ ".bmp";

				File ficherofhist = new File(fotohist);
				File ficherorhist = new File(firmahist);
				File ficherohhist = new File(huellahist);

				System.out.println("examen = "+i);
				
				// /Elimina Foto Historico
				if (ficherofhist.delete()) {
					// System.out.println("El fichero foto ha sido borrado satisfactoriamente");
				} else {
					// System.out.println("El fichero foto no puede ser borrado");
				}
				// /Elimina Firma Historico
				if (ficherorhist.delete()) {
					// System.out.println("El fichero foto ha sido borrado satisfactoriamente");
				} else {
					// System.out.println("El fichero foto no puede ser borrado");
				}
				// /Elimina Huella Historico
				if (ficherohhist.delete()) {
					// System.out.println("El fichero foto ha sido borrado satisfactoriamente");
				} else {
					// System.out.println("El fichero foto no puede ser borrado");
				}
			}

			respuesta = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respuesta;
	}

}
