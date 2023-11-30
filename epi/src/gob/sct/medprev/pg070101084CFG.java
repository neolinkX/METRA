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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070101084CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070101084CFG.png'>
 */
public class pg070101084CFG extends CFGListBase2 {

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
	private final int SI_NO = 1;
	private final int LETRAS_NUMEROS = 2;
	private final int NUMEROS = 3;
	private final int NOTAS = 4;
	private final int RANGO = 5;

	/**
	 * Constructor default
	 */
	public pg070101084CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	private void insertar(Connection conn) {
		TDMEDPerfilSintoma dMEDPerfilSintoma = new TDMEDPerfilSintoma();
		TVMEDPerfilSintoma registro = null;
		// System.out.println("a punto de insertar: " + insertar.size() +
		// " registros");
		for (int i = 0; i < insertar.size(); i++) {

			registro = (TVMEDPerfilSintoma) insertar.get(i);
			// System.out.println("insertando: " +
			// registro.toHashMap().toString());
			try {
				// manejar en el contexto de una transacci�n
				cClave = (String) dMEDPerfilSintoma.insert(conn, registro);
			} catch (Exception ex) {
				vErrores.acumulaError("", 14, "");
				error("Error al insertar el registro", ex);
			}
		}
	}

	private void borrar(Connection conn) {
		TDMEDPerfilSintoma dMEDPerfilDiag = new TDMEDPerfilSintoma();
		TVMEDPerfilSintoma registro = null;
		// System.out.println("a punto de borrar: " + borrar.size() +
		// " registros");
		for (int i = 0; i < borrar.size(); i++) {
			registro = (TVMEDPerfilSintoma) borrar.get(i);
			// System.out.println("borrando: " +
			// registro.toHashMap().toString());
			try {
				// manejar en el contexto de una transacci�n
				cClave = (String) dMEDPerfilDiag.delete(conn, registro);
			} catch (Exception ex) {
				vErrores.acumulaError("", 14, "");
				error("Error al borrar el registro", ex);
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

		if (false) {
			return; // ****para pruebas
		}

		DbConnection dbConn = null;
		Connection conn = null;

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			if (borrar.size() != 0) { // si hay registros por insertar
				this.borrar(conn); // primero borrar todos los registros
			}
			if (insertar.size() != 0) { // si hay registros por insertar
				this.insertar(conn); // y despu�s insertar
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
					// System.out.println("--cerrando Connection...");
				}
				if (dbConn != null) {
					dbConn.closeConnection();
					// System.out.println("--cerrando DbConnection...");
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
		TDMEDPerfilSintoma dMEDPerfilSintoma = new TDMEDPerfilSintoma();
		String iCvePerfil = request.getParameter("iCvePerfil");
		String iCveServicio = request.getParameter("iCveServicio");
		String iCveRama = request.getParameter("iCveRama");
		String where = "";
		String orderBy = cOrden;
		// System.out.println("buscando sintomas para: " + iCveServicio + ", " +
		// iCveRama);

		/*
		 * // no evaluar condiciones if (cCondicion != null &&
		 * !cCondicion.equals("")) { where += " and " + cCondicion; } if
		 * (iCveServicio != null && iCveServicio.length() != 0) { where +=
		 * " and s.iCveServicio = " + iCveServicio; }
		 */

		// consultar solamente cuando hayan seleccionado una rama
		if (iCveRama != null && iCveRama.length() != 0) {
			// where += " and s.iCveRama = " + iCveRama;
			try {
				vDespliega = dMEDPerfilSintoma.findByJoin(iCvePerfil,
						iCveServicio, iCveRama); // where, orderBy
				// System.out.println("obtenidos: " + vDespliega.size());
			} catch (Exception ex) {
				error("FillVector", ex);
			}
		} else {
			// � no hacer la consulta !
			// System.out.println("no hay condiciones de busqueda");
			vDespliega = new Vector();
		}
	}

	/**
	 * Metodo que procesa los renglones obtenidos del request, y los coloca en
	 * la tabla de Hash correspondiente para insertar.
	 * 
	 * @return un int con el n�mero de renglones insertados.
	 */
	private int getTableValues() {
		TVMEDPerfilSintoma v = null;
		int i = 0;
		int accion = 0;
		int n = Integer.parseInt(request.getParameter("hdTotalRows"));
		int iCvePerfil = 0;
		int iCveServicio = 0;
		int iCveRama = 0;
		int iCveSintoma = 0;
		int lAsignar = 0;
		int tpoResp = 0;
		String llave = "";
		boolean existe = false;
		boolean asignar = false;
		boolean alarma = false;
		iCvePerfil = Integer.parseInt(request.getParameter("iCvePerfil"));
		iCveServicio = Integer.parseInt(request.getParameter("iCveServicio"));
		iCveRama = Integer.parseInt(request.getParameter("iCveRama"));
		float dValorI = 0.0f;
		float dValorF = 0.0f;
		int lLogico = 0;
		String cCaracter = "";
		String dvalori = "";
		String dvalorf = "";
		String llogico = "";
		String ccaracter = "";

		while (i < n) {
			// armar un V.O. para cada rengl�n de la pantalla de captura
			// y agregarlo a la tabla de Hash correspondiente
			v = new TVMEDPerfilSintoma();

			tpoResp = Integer
					.parseInt(request.getParameter("iCveTpoResp_" + i));
			iCveSintoma = Integer.parseInt(request.getParameter("iCveSintoma_"
					+ i));
			dvalori = request.getParameter("dValorI_" + i);
			dvalorf = request.getParameter("dValorF_" + i);
			llogico = request.getParameter("lLogico_" + i);
			ccaracter = request.getParameter("cCaracter_" + i);

			llave = iCvePerfil + "," + iCveServicio + "," + iCveRama + ","
					+ iCveSintoma;
			// System.out.println(llave + "," + dvalori + "," + dvalorf + "," +
			// llogico +"," + ccaracter);

			// llave primaria
			v.setICvePerfil(iCvePerfil);
			v.setICveServicio(iCveServicio);
			v.setICveRama(iCveRama);
			v.setICveSintoma(iCveSintoma);
			// campos con valores default
			v.setDValorI(dValorI);
			v.setDValorF(dValorF);
			v.setLLogico(lLogico);
			v.setCCaracter(cCaracter);

			// verificar que traiga valores para insertar, en cada tipo de dato,
			// y establecer el valor real
			switch (tpoResp) {
			case SI_NO:

				// //System.out.println("tipo si/no");
				if (esValido(llogico)) {
					v.setLLogico(Integer.parseInt(llogico));
					asignar = true;
				}
				break;
			case LETRAS_NUMEROS:
			case NOTAS:

				// //System.out.println("tipo letras/numeros");
				if (esValido(ccaracter) && ccaracter.length() != 0) {
					v.setCCaracter(ccaracter);
					asignar = true;
				}
				break;
			case NUMEROS:

				// //System.out.println("tipo numeros");
				if (esValido(dvalori) && !esCero(dvalori)) {
					v.setDValorI(Float.parseFloat(dvalori));
					asignar = true;
				}
				break;
			case RANGO:

				// //System.out.println("tipo rango");
				if ((esValido(dvalori) && esValido(dvalorf))
						&& (!esCero(dvalori) || !esCero(dvalorf))) {
					v.setDValorI(Float.parseFloat(dvalori));
					v.setDValorF(Float.parseFloat(dvalorf));
					asignar = true;
				}
				break;
			default:
				// System.out.println("ERROR: ninguna accion: " + llave);
			}

			if (asignar) {
				insertar.add(v);
			}
			borrar.add(v); // borrar de cualquier manera
			i++;
			existe = asignar = alarma = false; // re-set de las variables de
												// evaluaci�n
		}
		// System.out.println(i + ": insertar: " + llave + ", total="
		// +insertar.size());
		return insertar.size();
	}

	/**
	 * Metodo getInputs No implementado en este tipo de p�ginas (listado)
	 */
	public Object getInputs() throws CFGException {
		return null;
	}

	private boolean esValido(String valor) {
		if (valor == null || valor.equals("null")) {
			// es null
			return false;
		} else {
			return true;
		}
	}

	private boolean esCero(String valor) {
		if (Float.parseFloat(valor) == 0.0f) {
			return true;
		} else {
			return false;
		}
	}
}
