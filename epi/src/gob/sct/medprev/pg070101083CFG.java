package gob.sct.medprev;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import com.micper.sql.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import com.micper.ingsw.*;
import com.micper.util.*;
import com.micper.excepciones.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import com.micper.util.logging.*;

/**
 * * Clase de configuracion para Clase para manejo de eventos en el JSP
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Romeo S�nchez
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070101083CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070101083CFG.png'>
 */
public class pg070101083CFG extends CFGListBase2 {

	/**
	 * Variable de instancia que almacena los value objects de los registros por
	 * actualizar
	 */
	private Vector actualizar = new Vector();
	/**
	 * Variable de instancia que almacena los value objects de los registros por
	 * actualizar
	 */
	private Vector borrar = new Vector();
	/**
	 * Variable de instancia que almacena los value objects de los registros por
	 * actualizar
	 */
	private Vector insertar = new Vector();

	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	/**
	 * Constructor default
	 */
	public pg070101083CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		NavStatus = "Hidden";
	}

	private void insertar(Connection conn) {
		TDMEDPerfilDiag dMEDPerfilDiag = new TDMEDPerfilDiag();
		TVMEDPerfilDiag registro = null;

		for (int i = 0; i < insertar.size(); i++) {

			/*
			 * �deber�a haber una validaci�n previa a la inserci�n, para
			 * asegurar que no existe el registro?
			 */

			registro = (TVMEDPerfilDiag) insertar.get(i);

			try {
				// manejar en el contexto de una transacci�n
				cClave = (String) dMEDPerfilDiag.insert(conn, registro);
			} catch (Exception ex) {
				vErrores.acumulaError("", 14, "");
				error("Error al insertar el registro", ex);
			}
		}
	}

	private void borrar(Connection conn) {
		TDMEDPerfilDiag dMEDPerfilDiag = new TDMEDPerfilDiag();
		TVMEDPerfilDiag registro = null;

		for (int i = 0; i < borrar.size(); i++) {
			registro = (TVMEDPerfilDiag) borrar.get(i);

			try {
				// manejar en el contexto de una transacci�n
				cClave = (String) dMEDPerfilDiag.delete(conn, registro);
			} catch (Exception ex) {
				vErrores.acumulaError("", 14, "");
				error("Error al borrar el registro", ex);
			}
		}
	}

	private void actualizar(Connection conn) {
		TDMEDPerfilDiag dMEDPerfilDiag = new TDMEDPerfilDiag();
		TVMEDPerfilDiag registro = null;

		for (int i = 0; i < actualizar.size(); i++) {

			/*
			 * �deber�a haber una validaci�n previa a la inserci�n, para
			 * asegurar que no existe el registro?
			 */

			registro = (TVMEDPerfilDiag) actualizar.get(i);

			try {
				// manejar en el contexto de una transacci�n
				cClave = (String) dMEDPerfilDiag.update(conn, registro);
			} catch (Exception ex) {
				vErrores.acumulaError("", 14, "");
				error("Error al actualizar el registro", ex);
			}
		}
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		/*
		 * la funcionalidad original de este Metodo se ha cambiado para tomar la
		 * acci�n pertinente, seg�n las condiciones definidas en la regla de
		 * negocios durante la ejecuci�n del Metodo getTableValues().
		 */

		// se llenan las tablas de hash con los value objects correspondientes.
		int n = this.getTableValues();
		DbConnection dbConn = null;
		Connection conn = null;

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			if (insertar.size() != 0) { // si hay registros por insertar
				this.insertar(conn);
			}
			if (borrar.size() != 0) { // si hay registros por borrar
				this.borrar(conn);
			}
			if (actualizar.size() != 0) { // si hay registros por borrar
				this.actualizar(conn);
			}

			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				vErrores.acumulaError("", 14, "");
				error("Error al crear el registro", ex);
			}
		} finally {
			try {
				if (conn != null) {
					conn.close();

				}
				if (dbConn != null) {
					dbConn.closeConnection();

				}
			} catch (SQLException ex1) {
				vErrores.acumulaError("", 14, "");
				error("Error al cerrar la conexi�n", ex1);
			}
		}

	} // Metodo Guardar

	/**
	 * Metodo GuardarA
	 */
	public void GuardarA() {
		TDMEDPerfilDiag dMEDPerfilDiag = new TDMEDPerfilDiag();
		try {
			cClave = (String) dMEDPerfilDiag.update(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al actualizar el registro", ex);
		} finally {
			super.GuardarA();
		}
	} // Metodo GuardarA

	/**
	 * Metodo Borrar
	 */
	public void Borrar() {
		TDMEDPerfilDiag dMEDPerfilDiag = new TDMEDPerfilDiag();
		try {
			cClave = (String) dMEDPerfilDiag.delete(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al eliminar/deshabilitar el registro", ex);
		} finally {
			super.Borrar();
		}
	} // Metodo Borrar

	/**
	 * Metodo fillVector
	 */
	public void fillVector() {
		TDMEDDiagnostico dMEDDiagnostico = new TDMEDDiagnostico();
		String iCvePerfil = request.getParameter("iCvePerfil");
		String iCveEspecialidad = request.getParameter("iCveEspecialidad");
		String where = "";
		String orderBy = cOrden;

		/*
		 * if (cCondicion != null && !cCondicion.equals("")) { where += " and "
		 * + cCondicion; }
		 */
		/*
		 * if (iCvePerfil!=null && iCvePerfil.length()!=0) { where +=
		 * " and p.iCvePerfil = " + iCvePerfil; }
		 */

		// consultar solamente cuando hayan seleccionado una especialidad
		if (iCveEspecialidad != null && iCveEspecialidad.length() != 0) {
			// where += " and d.iCveEspecialidad = " + iCveEspecialidad;
			try {
				vDespliega = dMEDDiagnostico.findByPerfilEspec(iCvePerfil,
						iCveEspecialidad, where, orderBy);
			} catch (Exception ex) {
				error("FillVector", ex);
			}
		} else {
			// � no hacer la consulta !
			try {
				vDespliega = new Vector(); // vacio
			} catch (Exception ex) {
				error("FillVector", ex);
			}
		}
	}

	/**
	 * Metodo que procesa los renglones obtenidos del request, y los coloca en
	 * la tabla de Hash correspondiente a la acci�n que les corresponde
	 * (actualizar, borrar e insertar).
	 * 
	 * @return un int con el n�mero de renglones recibidos en el request.
	 */
	private int getTableValues() {
		TVMEDPerfilDiag v = null;
		int i = 0;
		int accion = 0;
		final int INSERTAR = 1;
		final int ACTUALIZAR = 2;
		final int BORRAR = 3;
		int n = Integer.parseInt(request.getParameter("hdTotalRows"));
		int iCvePerfil = 0;
		int iCveEspecialidad = 0;
		int iCveDiagnostico = 0;
		int lAlarma = 0;
		int lActivo = 0;
		int lAsignar = 0;
		String llave = "";
		boolean existe = false;
		boolean asignar = false;
		boolean alarma = false;

		while (i < n) {
			// armar un V.O. para cada rengl�n de la pantalla de captura
			// y agregarlo a la tabla de Hash correspondiente
			v = new TVMEDPerfilDiag();

			existe = request.getParameter("existe_" + i).equals("true") ? true
					: false;
			iCvePerfil = Integer.parseInt(request.getParameter("iCvePerfil_"
					+ i));
			iCveEspecialidad = Integer.parseInt(request
					.getParameter("iCveEspecialidad_" + i));
			iCveDiagnostico = Integer.parseInt(request
					.getParameter("iCveDiagnostico_" + i));
			lAlarma = request.getParameter("lAlarma_" + i) != null ? Integer
					.parseInt(request.getParameter("lAlarma_" + i)) : 0;
			lAsignar = request.getParameter("lAsignar_" + i) != null ? Integer
					.parseInt(request.getParameter("lAsignar_" + i)) : 0;

			alarma = lAlarma == 1 ? true : false;
			asignar = lAsignar == 1 ? true : false;

			v.setICvePerfil(iCvePerfil);
			v.setICveEspecialidad(iCveEspecialidad);
			v.setICveDiagnostico(iCveDiagnostico);
			v.setLAlarma(lAlarma);

			llave = iCvePerfil + "," + iCveEspecialidad + "," + iCveDiagnostico;

			// System.out.println("existe: " + existe + ",\tasignar: " +
			// asignar);

			if (asignar && !existe) { // si no existe y est� marcado como
										// asignar, se debe insertar
				// System.out.println("INSERTAR, existe: " + existe +
				// ",\tasignar: " +asignar);
				accion = INSERTAR;
			} else if (!asignar && existe) { // si existe y no est� marcado
												// como asignar, se debe borrar
				// System.out.println("BORRAR, existe: " + existe +
				// ",\tasignar: " +asignar);
				accion = BORRAR;
			} else if (asignar && existe) { // si existe, se actualiza
				// System.out.println("ACTUALIZAR, existe: " + existe +
				// ",\tasignar: " +asignar);
				accion = ACTUALIZAR;
			} else {
				accion = 0;
			}

			switch (accion) {
			case INSERTAR:
				insertar.add(v);
				// System.out.println("insertar: " + llave + ", total=" +
				// insertar.size());
				break;
			case BORRAR:
				borrar.add(v);
				// System.out.println("borrar: " + llave + ", total=" +
				// borrar.size());
				break;
			case ACTUALIZAR:
				actualizar.add(v);
				// System.out.println("actualizar: " + llave + ", total="
				// +actualizar.size());
				break;
			default:
				// System.out.println("ninguna accion: " + llave);
			}
			i++;
			existe = asignar = alarma = false; // re-set de las variables de
												// evaluaci�n
		}

		return n;
	}

	/**
	 * Metodo getInputs
	 */
	public Object getInputs() throws CFGException {
		String cCampo;
		int iCampo;
		float fCampo;
		java.sql.Date dtCampo;
		TFechas tfCampo = new TFechas();
		TVMEDPerfilDiag vMEDPerfilDiag = new TVMEDPerfilDiag();
		try {
			cCampo = "" + request.getParameter("iCvePerfil");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDPerfilDiag.setICvePerfil(iCampo);

			cCampo = "" + request.getParameter("iCveEspecialidad");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDPerfilDiag.setICveEspecialidad(iCampo);

			cCampo = "" + request.getParameter("iCveDiagnostico");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDPerfilDiag.setICveDiagnostico(iCampo);

			cCampo = "" + request.getParameter("lAlarma");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDPerfilDiag.setLAlarma(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vMEDPerfilDiag;
	}
}
