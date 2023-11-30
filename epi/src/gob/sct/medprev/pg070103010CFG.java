package gob.sct.medprev;

import java.util.*;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

import gob.sct.medprev.*;
import com.micper.ingsw.*;
import java.util.*;
import com.micper.util.*;
import com.micper.util.caching.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.*;

/**
 * * Clase de configuracion para Listado de TOXCtrolCalibra
 * <p>
 * Administraci�n y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Esteban Viveros
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070103010CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070103010CFG.png'>
 */
public class pg070103010CFG extends CFGListBase2 {
	public pg070103010CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070103050.jsp"; // Marca a donde redireccionara esta
										// pagina
	}

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		Vector vcRegistros = null;
		try {
			vcRegistros = new TDGRLPuesto().FindByAll(getParameters());
			iNumReg = 200;
		} catch (Exception ex) {
			error("FillVector", ex);
		}
		vDespliega = vcRegistros;
	}

	/**
	 * Método findUser
	 */
	public TVPERDatos findUser() {
		TVPERDatos vPerDat = null;
		try {
			String cTmp = request.getParameter("hdICvePersonal");// por mientras
			int iCvePersonal = cTmp == null ? 0 : Integer.parseInt(cTmp);
			vPerDat = new TDPERDatos().findUser(iCvePersonal);
		} catch (Exception ex) {
			error("findUser", ex);
		}
		return vPerDat;
	}

	/**
	 * Método getEdad
	 */
	public int getEdad(Date dtFechaNac) {
		Calendar hoy = Calendar.getInstance();
		Calendar nac = Calendar.getInstance();
		nac.setTime(dtFechaNac);
		int edad = hoy.get(Calendar.YEAR) - nac.get(Calendar.YEAR);
		if ((hoy.get(Calendar.MONTH) << 5) + hoy.get(Calendar.DATE) < (nac
				.get(Calendar.MONTH) << 5) + nac.get(Calendar.DATE))
			edad--;
		return edad;
	}

	/**
	 * Método getParameters
	 */
	private HashMap getParameters() {

		HashMap hmTmp = new HashMap();
		String cTmp = vParametros.getPropEspecifica("EPIProceso");
		hmTmp.put("iCveProceso", cTmp != null ? cTmp : "0");
		cTmp = request.getParameter("hdICveUniMed");// por mientras
		hmTmp.put("iCveUniMed", cTmp != null ? cTmp : "0");
		cTmp = request.getParameter("hdICveModulo");// por mientras
		hmTmp.put("iCveModulo", cTmp != null ? cTmp : "0");
		cTmp = request.getParameter("hdICvePersonal");
		hmTmp.put("iCvePersonal", cTmp != null ? cTmp : "0");
		return hmTmp;
	}

	/**
	 * Validando que el Expediente no esta inhabilitado
	 */
	public int getInhabilitado(String Expediente) {
		TDMEDInhabilita dMEDInhabilita = new TDMEDInhabilita();
		Vector vcInhabilita = new Vector();
		TVMEDInhabilita vMEDInhabilita;
		TFechas dtFecha = new TFechas();
		// int Inhabilitado = 0;
		Boolean Inhabilitado = false;
		String fecha1 = "";
		String fecha2 = "";
		int Activo = 0;

		try {
			String cCondicion = " M1.iCvePersonal = " + Expediente;
			Inhabilitado = dMEDInhabilita.Inhabilitado(cCondicion);
			if (Inhabilitado) {
				Activo = 1;
			}
			/*
			 * vcInhabilita = dMEDInhabilita.FindByAll(cCondicion); fecha1 =
			 * dtFecha.getFechaYYYYMMDD(dtFecha.TodaySQL(),"-").toString();
			 * 
			 * for(int j=0;j<vcInhabilita.size();j++){ Inhabilitado = 1;
			 * vMEDInhabilita = (TVMEDInhabilita) vcInhabilita.get(j); fecha2 =
			 * vMEDInhabilita.getFinInh().toString(); }
			 * System.out.println("Inhabilitado = "+Inhabilitado);
			 * System.out.println("fecha1 = "+fecha1);
			 * System.out.println("fecha2 = "+fecha2);
			 * 
			 * 
			 * if(Inhabilitado == 1){ fecha1 = fecha1 + "-"; int dia1; int mes1;
			 * int ano1; StringTokenizer solDatos = new StringTokenizer(fecha1,
			 * "-"); dia1 =
			 * Integer.parseInt(solDatos.nextToken()+"".toString()); mes1 =
			 * Integer.parseInt(solDatos.nextToken()+"".toString()); ano1 =
			 * Integer.parseInt(solDatos.nextToken()+"".toString()); fecha2 =
			 * fecha2 + "-"; int dia2; int mes2; int ano2; StringTokenizer
			 * solDatos2 = new StringTokenizer(fecha2, "-"); dia2 =
			 * Integer.parseInt(solDatos2.nextToken()+"".toString()); mes2 =
			 * Integer.parseInt(solDatos2.nextToken()+"".toString()); ano2 =
			 * Integer.parseInt(solDatos2.nextToken()+"".toString());
			 * 
			 * System.out.println("Año = " + ano2 +" es mayor a ano1 = " +ano1);
			 * System.out.println("Mes = " + mes2 +" es mayor a mes1 = " +mes1);
			 * System.out.println("dia2 = " + dia2 +" es mayor a dia1 = "
			 * +dia1);
			 * 
			 * if(ano2 >= ano1){ System.out.println("Año ==" + ano2
			 * +" es mayor a ano1 ==" +ano1); if(mes2 >= mes1){
			 * System.out.println("Mes ==" + mes2 +" es mayor a mes1 ==" +mes1);
			 * if(dia2 >= dia1){ System.out.println("dia2 ==" + dia2
			 * +" es mayor a dia1 ==" +dia1); Activo = 1;
			 * System.out.println("Expediente Inhactivo"); } } }
			 * 
			 * }
			 */
		} catch (Exception e) {
			vcInhabilita = new Vector();
			e.printStackTrace();
		}

		return Activo;
	}

}