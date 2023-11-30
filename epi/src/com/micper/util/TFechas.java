package com.micper.util;

import java.sql.*;
import java.util.*;
import com.micper.ingsw.*;
import java.text.*;
import java.io.*;

/**
 * Clase para manejo de fechas: generaciï¿½n, today, y cadenas de despliegue.
 * <p>
 * Ingenierï¿½a de Software generada en JAVA (J2EE).
 * 
 * @version 1.0
 *          <p>
 * @author Tecnologï¿½a Inred S.A. de C.V.
 * @author <dd>LSC. Rafael Miranda Blumenkron, LSC. Jaime Enrique Suï¿½rez
 *         Romero
 *         <p>
 * @see <small><a
 *      href="./../ingsample/pg9902041CFG.html">com.micper.ingsample.pg9902041CFG
 *      </a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src="TFechas.png">
 */

public class TFechas implements Serializable {
	/**
	 * Atributo que contiene las propiedades del archivo globales.properties
	 * para configuracion de meses.
	 */
	private TParametro vParametros = null;
	/**
	 * Atributo que contiene el nï¿½mero del mï¿½dulo para asignar al archivo de
	 * propiedades de consulta.
	 */
	private String NumModulo = "";
	/**
	 * Atributo que contiene la lista de nombre de meses tal como se escribiï¿½
	 * en archivo de parï¿½metros.
	 */
	private String NombreMeses = "";
	/**
	 * Atributo que contiene la lista de abreviaturas de meses tal como se
	 * escribiï¿½ en archivo de parï¿½metros.
	 */
	private String AbrevMeses = "";
	private String NombreDias = "";
	/** Atributo que contiene el vector con los nombres de meses. */
	private Vector vNombresMes = new Vector();
	/** Atributo que contiene el vector con las abreviaturas de meses. */
	private Vector vAbrevMes = new Vector();
	private Vector vcNombreDías = new Vector();

	/** Constructor por omisiï¿½n de la clase. */
	public TFechas() {
	}

	/**
	 * Constructor de la clase que permite asignar el nï¿½mero de mï¿½dulo para
	 * consulta de parï¿½metros.
	 * 
	 * @param cNumModulo
	 *            Nï¿½mero de mï¿½dulo del cual deseamos obtener los
	 *            parï¿½metros.
	 */
	public TFechas(String cNumModulo) {
		this.NumModulo = cNumModulo;
		this.vParametros = new TParametro(cNumModulo);
		this.NombreMeses = vParametros.getPropEspecifica("NombresMes");
		this.AbrevMeses = vParametros.getPropEspecifica("AbrevMes");
		this.NombreDias = vParametros.getPropEspecifica("NombreDias");
		// Genera vector de meses
		this.vNombresMes = new Vector();
		String cMeses = this.NombreMeses;
		if (cMeses == null)
			cMeses = "";
		if (cMeses.compareToIgnoreCase("") == 0)
			cMeses = "Enero,Febrero,Marzo,Abril,Mayo,Junio,Julio,Agosto,Septiembre,Octubre,Noviembre,Diciembre";
		String cTemp = "";
		for (int i = 0; i < 12; i++) {
			if (cMeses.length() != 0) {
				if (cMeses.indexOf(",") != -1) {
					cTemp = cMeses.substring(0, cMeses.indexOf(","));
					cMeses = cMeses.substring(cMeses.indexOf(",") + 1);
				}
			}
			this.vNombresMes.add(cTemp);
			if (i == 10) {
				this.vNombresMes.add(cMeses);
				break;
			}
		}
		if (this.vNombresMes.size() != 12) {
			for (int i = this.vNombresMes.size(); i <= 12; i++)
				this.vNombresMes.add("NO PROPORCIONADO");
		}
		// Genera vector de abreviaturas
		this.vAbrevMes = new Vector();
		String cAbrevs = this.AbrevMeses;
		if (cAbrevs == null)
			cAbrevs = "";
		if (cAbrevs.compareToIgnoreCase("") == 0)
			cAbrevs = "Ene,Feb,Mar,Abr,May,Jun,Jul,Ago,Sep,Oct,Nov,Dic";
		String cTemp2 = "";
		for (int i = 0; i < 12; i++) {
			if (cAbrevs.length() != 0) {
				if (cAbrevs.indexOf(",") != -1) {
					cTemp2 = cAbrevs.substring(0, cAbrevs.indexOf(","));
					cAbrevs = cAbrevs.substring(cAbrevs.indexOf(",") + 1);
				}
			}
			this.vAbrevMes.add(cTemp2);
			if (i == 10) {
				this.vAbrevMes.add(cAbrevs);
				break;
			}
		}
		if (this.vAbrevMes.size() != 12) {
			for (int i = this.vAbrevMes.size(); i <= 12; i++)
				this.vAbrevMes.add("NA");
		}
		String cNombreDias = NombreDias;
		if (NombreDias.equals("")) {
			cNombreDias = "Lunes,Martes,MiÃ©rcoles,Jueves,Viernes,SÃ¡bado,Domingo";
		}
		StringTokenizer stNDias = new StringTokenizer(cNombreDias, ",");
		while (stNDias.hasMoreTokens()) {
			vcNombreDías.add(stNDias.nextToken());
		}
	}

	/**
	 * Metodo para obtener un time stamp a partir de datos bï¿½sicos como aï¿½o,
	 * mes dï¿½a hora minuto y segundo.
	 * 
	 * @param iAnio
	 *            int Aï¿½o para generar time stamp.
	 * @param iMes
	 *            int Mes para generar time stamp.
	 * @param iDia
	 *            int Dï¿½a para generar time stamp.
	 * @param iHora
	 *            int Hora para generar time stamp.
	 * @param iMinuto
	 *            int Minuto para generar time stamp.
	 * @param iSegundo
	 *            int Segundo para generar time stamp.
	 * @return Timestamp TimeStamp generado con parï¿½metros proporcionados,
	 *         regresa null en caso de error de datos.
	 */
	public java.sql.Timestamp getTimeStamp(int iAnio, int iMes, int iDia,
			int iHora, int iMinuto, int iSegundo) {
		if ((iMes > 12) || (iDia > 31) || (iHora > 24) || (iMinuto > 60)
				|| (iSegundo > 60))
			return null;
		GregorianCalendar gTime = new GregorianCalendar();
		gTime.set(iAnio, iMes, iDia, iHora, iMinuto, iSegundo);
		return new java.sql.Timestamp(gTime.getTimeInMillis());
	}

	/**
	 * Metodo para obtener la fecha en formato SQLDate de un time stamp
	 * proporcionado.
	 * 
	 * @param tsFechaHora
	 *            Timestamp TimeStamp del cual se desea conocer la fecha.
	 * @return Date Fecha obtenida del time stamp proporcionado.
	 */
	public java.sql.Date getDateSQL(java.sql.Timestamp tsFechaHora) {
		GregorianCalendar gTime = new GregorianCalendar();
		gTime.setTimeInMillis(tsFechaHora.getTime());
		return this.getDateSQL(new Integer(gTime.DAY_OF_MONTH), new Integer(
				gTime.MONTH), new Integer(gTime.YEAR));
	}

	/**
	 * Metodo para obtener la hora de un timestamp proporcionado.
	 * 
	 * @param tsFechaHora
	 *            Timestamp timeStamp del cual se desea conocer la hora (formato
	 *            de 24hrs).
	 * @return int Hora obtenida del time stamp proporcionado.
	 */
	public int getHora24H(java.sql.Timestamp tsFechaHora) {
		GregorianCalendar gTime = new GregorianCalendar();
		gTime.setTimeInMillis(tsFechaHora.getTime());
		return gTime.HOUR_OF_DAY;
	}

	/**
	 * Metodo para obtener la hora de un timestamp proporcionado.
	 * 
	 * @param tsFechaHora
	 *            Timestamp timeStamp del cual se desea conocer la hora (formato
	 *            de 12hrs).
	 * @return int Hora obtenida del time stamp proporcionado.
	 */
	public int getHora12H(java.sql.Timestamp tsFechaHora) {
		GregorianCalendar gTime = new GregorianCalendar();
		gTime.setTimeInMillis(tsFechaHora.getTime());
		return gTime.HOUR;
	}

	/**
	 * Metodo para obtener AM o PM de un timestamp proporcionado.
	 * 
	 * @param tsFechaHora
	 *            Timestamp timeStamp del cual se desea conocer si es de AM o
	 *            PM.
	 * @return String AM o PM obtenido del time stamp proporcionado.
	 */
	public String getAM_PM(java.sql.Timestamp tsFechaHora) {
		GregorianCalendar gTime = new GregorianCalendar();
		gTime.setTimeInMillis(tsFechaHora.getTime());
		if (gTime.AM_PM == 0)
			return "AM";
		else
			return "PM";
	}

	/**
	 * Metodo para obtener los minutos de un timestamp proporcionado.
	 * 
	 * @param tsFechaHora
	 *            Timestamp timeStamp del cual se desea conocer los minutos.
	 * @return int Minutos obtenidos del time stamp proporcionado.
	 */
	public int getMinuto(java.sql.Timestamp tsFechaHora) {
		GregorianCalendar gTime = new GregorianCalendar();
		gTime.setTimeInMillis(tsFechaHora.getTime());
		return gTime.MINUTE;
	}

	/**
	 * Metodo para obtener los segundos de un timestamp proporcionado.
	 * 
	 * @param tsFechaHora
	 *            Timestamp timeStamp del cual se desea conocer los segundos.
	 * @return int Segundos obtenidos del time stamp proporcionado.
	 */
	public int getSegundo(java.sql.Timestamp tsFechaHora) {
		GregorianCalendar gTime = new GregorianCalendar();
		gTime.setTimeInMillis(tsFechaHora.getTime());
		return gTime.SECOND;
	}

	/**
	 * Metodo encargado de regresar la fecha actual en un objeto de tipo long.
	 * 
	 * @return Fecha actual en formato long.
	 */
	public long TodayLONG() {
		return new java.util.Date().getTime();
	}

	/**
	 * Metodo encargado de regresar la fecha actual en un objeto de tipo
	 * sql.Date.
	 * 
	 * @return Fecha actual en formato sql.Date.
	 */
	public java.sql.Date TodaySQL() {
		return new java.sql.Date(this.TodayLONG());
	}

	/**
	 * Metodo encargado de devolver el nï¿½mero de dï¿½a de una fecha
	 * proporcionada de tipo sql.Date.
	 * 
	 * @param dtFecha
	 *            Fecha de la cual se desea obtener el dï¿½a.
	 * @return Entero correspondiente al nï¿½mero de dï¿½a de la fecha
	 *         proporcionada.
	 */
	public int getIntDay(java.sql.Date dtFecha) {
		java.util.Date utilFecha = new java.util.Date(dtFecha.getTime());
		SimpleDateFormat sdfFecha = null;
		sdfFecha = new SimpleDateFormat("dd");
		return new Integer(sdfFecha.format(utilFecha)).intValue();
	}

	/**
	 * Metodo encargado de devolver el nï¿½mero de mes de una fecha
	 * proporcionada de tipo sql.Date.
	 * 
	 * @param dtFecha
	 *            Fecha de la cual se desea obtener el mes.
	 * @return Entero correspondiente al nï¿½mero de mes de la fecha
	 *         proporcionada.
	 */
	public int getIntMonth(java.sql.Date dtFecha) {
		java.util.Date utilFecha = new java.util.Date(dtFecha.getTime());
		SimpleDateFormat sdfFecha = null;
		sdfFecha = new SimpleDateFormat("MM");
		return new Integer(sdfFecha.format(utilFecha)).intValue();
	}

	/**
	 * Metodo encargado de devolver el nï¿½mero de aï¿½o de una fecha
	 * proporcionada de tipo sql.Date.
	 * 
	 * @param dtFecha
	 *            Fecha de la cual se desea obtener el aï¿½o.
	 * @return Entero correspondiente al nï¿½mero de aï¿½o de la fecha
	 *         proporcionada.
	 */
	public int getIntYear(java.sql.Date dtFecha) {
		java.util.Date utilFecha = new java.util.Date(dtFecha.getTime());
		SimpleDateFormat sdfFecha = null;
		sdfFecha = new SimpleDateFormat("yyyy");
		return new Integer(sdfFecha.format(utilFecha)).intValue();
	}

	public String getDayOfWeek(java.sql.Date dtFecha) {
		java.util.Date utilFecha = new java.util.Date(dtFecha.getTime());
		SimpleDateFormat sdfFecha = null;
		sdfFecha = new SimpleDateFormat("EEE");
		String cDia = sdfFecha.format(utilFecha).toString();
		if (cDia.equals("Mon"))
			cDia = "" + vcNombreDías.get(0);
		if (cDia.equals("Tue"))
			cDia = "" + vcNombreDías.get(1);
		if (cDia.equals("Wed"))
			cDia = "" + vcNombreDías.get(2);
		if (cDia.equals("Thu"))
			cDia = "" + vcNombreDías.get(3);
		if (cDia.equals("Fri"))
			cDia = "" + vcNombreDías.get(4);
		if (cDia.equals("Sat"))
			cDia = "" + vcNombreDías.get(5);
		if (cDia.equals("Sun"))
			cDia = "" + vcNombreDías.get(6);
		return cDia;
	}

	public int getDayOfWeekInt(java.sql.Date dtFecha) {
		java.util.Date utilFecha = new java.util.Date(dtFecha.getTime());
		SimpleDateFormat sdfFecha = null;
		sdfFecha = new SimpleDateFormat("EEE");
		String cDia = sdfFecha.format(utilFecha).toString();
		int iDia = 0;
		if (cDia.equals("Mon"))
			iDia = 1;
		if (cDia.equals("Tue"))
			iDia = 2;
		if (cDia.equals("Wed"))
			iDia = 3;
		if (cDia.equals("Thu"))
			iDia = 4;
		if (cDia.equals("Fri"))
			iDia = 5;
		if (cDia.equals("Sat"))
			iDia = 6;
		if (cDia.equals("Sun"))
			iDia = 0;
		return iDia;
	}

	public String getDateSPN(java.sql.Date dtFecha) {
		return getStringDay(dtFecha) + " de " + getMonthName(dtFecha) + " de "
				+ getStringYear(dtFecha);
	}

	public String getDateSPNWithDay(java.sql.Date dtFecha) {
		return getDayOfWeek(dtFecha) + " " + getStringDay(dtFecha) + " de "
				+ getMonthName(dtFecha) + " de " + getStringYear(dtFecha);
	}

	/**
	 * Metodo encargado de devolver el nï¿½mero de dï¿½a de una fecha
	 * proporcionada de tipo sql.Date.
	 * 
	 * @param dtFecha
	 *            Fecha de la cual se desea obtener el dï¿½a.
	 * @return Cadena correspondiente al nï¿½mero de dï¿½a de la fecha
	 *         proporcionada.
	 */
	public String getStringDay(java.sql.Date dtFecha) {
		return new TNumeros().getNumeroSinSeparador(
				new Integer(this.getIntDay(dtFecha)), 2);
	}

	/**
	 * Metodo encargado de devolver el nï¿½mero de mes de una fecha
	 * proporcionada de tipo sql.Date.
	 * 
	 * @param dtFecha
	 *            Fecha de la cual se desea obtener el mes.
	 * @return Cadena correspondiente al nï¿½mero de mes de la fecha
	 *         proporcionada.
	 */
	public String getStringMonth(java.sql.Date dtFecha) {
		return new TNumeros().getNumeroSinSeparador(
				new Integer(this.getIntMonth(dtFecha)), 2);
	}

	/**
	 * Metodo encargado de devolver el nï¿½mero de aï¿½o de una fecha
	 * proporcionada de tipo sql.Date.
	 * 
	 * @param dtFecha
	 *            Fecha de la cual se desea obtener el aï¿½o.
	 * @return Cadena correspondiente al nï¿½mero de aï¿½o de la fecha
	 *         proporcionada.
	 */
	public String getStringYear(java.sql.Date dtFecha) {
		return new TNumeros().getNumeroSinSeparador(
				new Integer(this.getIntYear(dtFecha)), 4);
	}

	/**
	 * Metodo encargado de devolver el nombre completo del mes correspondiente a
	 * una fecha proporcionada.
	 * 
	 * @param dtFecha
	 *            Fecha de la cual se desea obtener el nombre del mes, de tipo
	 *            sql.Date.
	 * @return Cadena correspondiente al nombre del mes, de acuerdo a los
	 *         proporcionados en el archivo de propiedades.
	 */
	public String getMonthName(java.sql.Date dtFecha) {
		return this.vNombresMes.elementAt(this.getIntMonth(dtFecha) - 1)
				.toString();
	}

	/**
	 * Metodo encargado de devolver la abreviatura del mes correspondiente a una
	 * fecha proporcionada.
	 * 
	 * @param dtFecha
	 *            Fecha de la cual se desea obtener la abreviatura del mes, de
	 *            tipo sql.Date.
	 * @return Cadena correspondiente a la abreviatura del mes, de acuerdo a los
	 *         proporcionados en el archivo de propiedades.
	 */
	public String getMonthAbrev(java.sql.Date dtFecha) {
		return this.vAbrevMes.elementAt(this.getIntMonth(dtFecha) - 1)
				.toString();
	}

	/**
	 * Metodo encargado de devolver una fecha proporcionada con un formato de
	 * dï¿½a / mes / aï¿½o con el separador proporcionado.
	 * 
	 * @param dtFecha
	 *            Fecha que deseamos obtener con separador especï¿½fico.
	 * @param cSeparador
	 *            Separador que se desea emplear en la fecha.
	 * @return Cadena correspondiente a la fecha con el separador proporcionado.
	 */
	public String getFechaDDMMYYYY(java.sql.Date dtFecha, String cSeparador) {
		java.util.Date utilFecha = new java.util.Date(dtFecha.getTime());
		SimpleDateFormat sdfFecha = null;
		String cSeparado = cSeparador;
		if (cSeparado == null)
			cSeparado = "/";
		if (cSeparado.compareToIgnoreCase("") == 0)
			cSeparado = "/";
		String cFormato = "dd" + cSeparado + "MM" + cSeparado + "yyyy";
		sdfFecha = new SimpleDateFormat(cFormato);
		return sdfFecha.format(utilFecha);
	}

	/**
	 * Metodo encargado de devolver una fecha proporcionada en formato dï¿½a,
	 * mes aï¿½o sin separador.
	 * 
	 * @param dtFecha
	 *            Fecha que deseamos obtener con separador especï¿½fico.
	 * @return Cadena correspondiente a la fecha con el separador proporcionado.
	 */
	public String getFechaDDMMYYYYSinSep(java.sql.Date dtFecha) {
		return this.getStringDay(dtFecha) + this.getStringMonth(dtFecha)
				+ this.getStringYear(dtFecha);
	}

	/**
	 * Metodo encargado de devolver una fecha proporcionada en formato mes,
	 * dï¿½a, aï¿½o sin separador.
	 * 
	 * @param dtFecha
	 *            Fecha que deseamos obtener con separador especï¿½fico.
	 * @return Cadena correspondiente a la fecha con el separador proporcionado.
	 */
	public String getFechaMMDDYYYYSinSep(java.sql.Date dtFecha) {
		return this.getStringMonth(dtFecha) + this.getStringDay(dtFecha)
				+ this.getStringYear(dtFecha);
	}

	/**
	 * Metodo encargado de devolver una fecha proporcionada en formato aï¿½o,
	 * mes, dï¿½a, sin separador.
	 * 
	 * @param dtFecha
	 *            Fecha que deseamos obtener con separador especï¿½fico.
	 * @return Cadena correspondiente a la fecha con el separador proporcionado.
	 */
	public String getFechaYYYYMMDDSinSep(java.sql.Date dtFecha) {
		return this.getStringYear(dtFecha) + this.getStringMonth(dtFecha)
				+ this.getStringDay(dtFecha);
	}

	/**
	 * Metodo encargado de devolver una fecha proporcionada con un formato de
	 * mes / dï¿½a / aï¿½o con el separador proporcionado.
	 * 
	 * @param dtFecha
	 *            Fecha que deseamos obtener con separador especï¿½fico.
	 * @param cSeparador
	 *            Separador que se desea emplear en la fecha.
	 * @return Cadena correspondiente a la fecha con el separador proporcionado.
	 */
	public String getFechaMMDDYYYY(java.sql.Date dtFecha, String cSeparador) {
		java.util.Date utilFecha = new java.util.Date(dtFecha.getTime());
		SimpleDateFormat sdfFecha = null;
		String cSeparado = cSeparador;
		if (cSeparado == null)
			cSeparado = "/";
		if (cSeparado.compareToIgnoreCase("") == 0)
			cSeparado = "/";
		String cFormato = "MM" + cSeparado + "dd" + cSeparado + "yyyy";
		sdfFecha = new SimpleDateFormat(cFormato);
		return sdfFecha.format(utilFecha);
	}

	/**
	 * Metodo encargado de devolver una fecha proporcionada con un formato de
	 * aï¿½o / mes / dï¿½a con el separador proporcionado.
	 * 
	 * @param dtFecha
	 *            Fecha que deseamos obtener con separador especï¿½fico.
	 * @param cSeparador
	 *            Separador que se desea emplear en la fecha.
	 * @return Cadena correspondiente a la fecha con el separador proporcionado.
	 */
	public String getFechaYYYYMMDD(java.sql.Date dtFecha, String cSeparador) {
		java.util.Date utilFecha = new java.util.Date(dtFecha.getTime());
		SimpleDateFormat sdfFecha = null;
		String cSeparado = cSeparador;
		if (cSeparado == null)
			cSeparado = "/";
		if (cSeparado.compareToIgnoreCase("") == 0)
			cSeparado = "/";
		String cFormato = "yyyy" + cSeparado + "MM" + cSeparado + "dd";
		sdfFecha = new SimpleDateFormat(cFormato);
		return sdfFecha.format(utilFecha);
	}

	/**
	 * Metodo encargado de devolver una fecha proporcionada con un formato de
	 * dï¿½a / Abreviatura de mes / aï¿½o con el separador proporcionado.
	 * 
	 * @param dtFecha
	 *            Fecha que deseamos obtener con separador especï¿½fico.
	 * @param cSeparador
	 *            Separador que se desea emplear en la fecha.
	 * @return Cadena correspondiente a la fecha con el separador proporcionado.
	 */
	public String getFechaDDMMMYYYY(java.sql.Date dtFecha, String cSeparador) {
		String cFecha = "";
		String cSeparado = cSeparador;
		if (cSeparado == null)
			cSeparado = "/";
		if (cSeparado.compareToIgnoreCase("") == 0)
			cSeparado = "/";
		cFecha = cFecha
				+ new TNumeros().getNumeroSinSeparador(
						new Integer(this.getIntDay(dtFecha)), 2);
		cFecha = cFecha + cSeparado;
		cFecha = cFecha + this.getMonthAbrev(dtFecha);
		cFecha = cFecha + cSeparado;
		cFecha = cFecha
				+ new TNumeros().getNumeroSinSeparador(
						new Integer(this.getIntYear(dtFecha)), 4);
		return cFecha;
	}

	/**
	 * Metodo encargado de devolver una fecha proporcionada con un formato de
	 * Abreviatura de mes / dï¿½a / aï¿½o con el separador proporcionado.
	 * 
	 * @param dtFecha
	 *            Fecha que deseamos obtener con separador especï¿½fico.
	 * @param cSeparador
	 *            Separador que se desea emplear en la fecha.
	 * @return Cadena correspondiente a la fecha con el separador proporcionado.
	 */
	public String getFechaMMMDDYYYY(java.sql.Date dtFecha, String cSeparador) {
		String cFecha = "";
		String cSeparado = cSeparador;
		if (cSeparado == null)
			cSeparado = "/";
		if (cSeparado.compareToIgnoreCase("") == 0)
			cSeparado = "/";
		cFecha = cFecha + this.getMonthAbrev(dtFecha);
		cFecha = cFecha + cSeparado;
		cFecha = cFecha
				+ new TNumeros().getNumeroSinSeparador(
						new Integer(this.getIntDay(dtFecha)), 2);
		cFecha = cFecha + cSeparado;
		cFecha = cFecha
				+ new TNumeros().getNumeroSinSeparador(
						new Integer(this.getIntYear(dtFecha)), 4);
		return cFecha;
	}

	/**
	 * Metodo encargado de devolver una fecha proporcionada con un formato de
	 * aï¿½o / Abreviatura de mes / dï¿½a con el separador proporcionado.
	 * 
	 * @param dtFecha
	 *            Fecha que deseamos obtener con separador especï¿½fico.
	 * @param cSeparador
	 *            Separador que se desea emplear en la fecha.
	 * @return Cadena correspondiente a la fecha con el separador proporcionado.
	 */
	public String getFechaYYYYMMMDD(java.sql.Date dtFecha, String cSeparador) {
		String cFecha = "";
		String cSeparado = cSeparador;
		if (cSeparado == null)
			cSeparado = "/";
		if (cSeparado.compareToIgnoreCase("") == 0)
			cSeparado = "/";
		cFecha = cFecha
				+ new TNumeros().getNumeroSinSeparador(
						new Integer(this.getIntYear(dtFecha)), 4);
		cFecha = cFecha + cSeparado;
		cFecha = cFecha + this.getMonthAbrev(dtFecha);
		cFecha = cFecha + cSeparado;
		cFecha = cFecha
				+ new TNumeros().getNumeroSinSeparador(
						new Integer(this.getIntDay(dtFecha)), 2);
		return cFecha;
	}
	
	
	/**
	 * Metodo encargado de devolver una fecha proporcionada con un formato de
	 * aï¿½o / Abreviatura de mes / dï¿½a con el separador proporcionado.
	 * 
	 * @param dtFecha
	 *            Fecha que deseamos obtener con separador especï¿½fico.
	 * @param cSeparador
	 *            Separador que se desea emplear en la fecha.
	 * @return Cadena correspondiente a la fecha con el separador proporcionado.
	 */
	public String getFechaYYYYMMMDDNom024(java.sql.Date dtFecha) {
		String cFecha = "";
		cFecha = cFecha
				+ new TNumeros().getNumeroSinSeparador(
						new Integer(this.getIntYear(dtFecha)), 4);
		cFecha = cFecha;
		//cFecha = cFecha + this.getMonthAbrev(dtFecha);
		//cFecha = cFecha;
		cFecha = cFecha
				+ new TNumeros().getNumeroSinSeparador(
						new Integer(this.getIntMonth(dtFecha)), 2);
		cFecha = cFecha
				+ new TNumeros().getNumeroSinSeparador(
						new Integer(this.getIntDay(dtFecha)), 2);
		return cFecha;
	}

	/**
	 * Metodo encargado de devolver una fecha proporcionada con un formato de
	 * dï¿½a / Nombre de mes / aï¿½o con el separador proporcionado.
	 * 
	 * @param dtFecha
	 *            Fecha que deseamos obtener con separador especï¿½fico.
	 * @param cSeparador
	 *            Separador que se desea emplear en la fecha.
	 * @return Cadena correspondiente a la fecha con el separador proporcionado.
	 */
	public String getFechaDDMMMMMYYYY(java.sql.Date dtFecha, String cSeparador) {
		String cFecha = "";
		String cSeparado = cSeparador;
		if (cSeparado == null)
			cSeparado = "/";
		if (cSeparado.compareToIgnoreCase("") == 0)
			cSeparado = "/";
		cFecha = cFecha
				+ new TNumeros().getNumeroSinSeparador(
						new Integer(this.getIntDay(dtFecha)), 2);
		cFecha = cFecha + cSeparado;
		cFecha = cFecha + this.getMonthName(dtFecha);
		cFecha = cFecha + cSeparado;
		cFecha = cFecha
				+ new TNumeros().getNumeroSinSeparador(
						new Integer(this.getIntYear(dtFecha)), 4);
		return cFecha;
	}

	/**
	 * Metodo encargado de devolver una fecha proporcionada con un formato de
	 * Nombre de mes / dï¿½a / aï¿½o con el separador proporcionado.
	 * 
	 * @param dtFecha
	 *            Fecha que deseamos obtener con separador especï¿½fico.
	 * @param cSeparador
	 *            Separador que se desea emplear en la fecha.
	 * @return Cadena correspondiente a la fecha con el separador proporcionado.
	 */
	public String getFechaMMMMMDDYYYY(java.sql.Date dtFecha, String cSeparador) {
		String cFecha = "";
		String cSeparado = cSeparador;
		if (cSeparado == null)
			cSeparado = "/";
		if (cSeparado.compareToIgnoreCase("") == 0)
			cSeparado = "/";
		cFecha = cFecha + this.getMonthName(dtFecha);
		cFecha = cFecha + cSeparado;
		cFecha = cFecha
				+ new TNumeros().getNumeroSinSeparador(
						new Integer(this.getIntDay(dtFecha)), 2);
		cFecha = cFecha + cSeparado;
		cFecha = cFecha
				+ new TNumeros().getNumeroSinSeparador(
						new Integer(this.getIntYear(dtFecha)), 4);
		return cFecha;
	}

	/**
	 * Metodo encargado de devolver una fecha proporcionada con un formato de
	 * aï¿½o / Nombre de mes / dï¿½a con el separador proporcionado.
	 * 
	 * @param dtFecha
	 *            Fecha que deseamos obtener con separador especï¿½fico.
	 * @param cSeparador
	 *            Separador que se desea emplear en la fecha.
	 * @return Cadena correspondiente a la fecha con el separador proporcionado.
	 */
	public String getFechaYYYYMMMMMDD(java.sql.Date dtFecha, String cSeparador) {
		String cFecha = "";
		String cSeparado = cSeparador;
		if (cSeparado == null)
			cSeparado = "/";
		if (cSeparado.compareToIgnoreCase("") == 0)
			cSeparado = "/";
		cFecha = cFecha
				+ new TNumeros().getNumeroSinSeparador(
						new Integer(this.getIntYear(dtFecha)), 4);
		cFecha = cFecha + cSeparado;
		cFecha = cFecha + this.getMonthName(dtFecha);
		cFecha = cFecha + cSeparado;
		cFecha = cFecha
				+ new TNumeros().getNumeroSinSeparador(
						new Integer(this.getIntDay(dtFecha)), 2);
		return cFecha;
	}

	/**
	 * Metodo encargado de devolver una fecha de tipo sql Date generada a partir
	 * de los parï¿½metros proporcionados.
	 * 
	 * @param iDia
	 *            Entero correspondiente al dï¿½a de la fecha a generar.
	 * @param iMes
	 *            Entero correspondiente al mes de la fecha a generar.
	 * @param iAnio
	 *            Entero correspondiente al aï¿½o de la fecha a generar.
	 * @return Fecha de tipo sql Date generada con los parï¿½metros
	 *         proporcionados.
	 */
	public java.sql.Date getDateSQL(Integer iDia, Integer iMes, Integer iAnio) {
		java.util.Calendar calendar = new java.util.GregorianCalendar(
				iAnio.intValue(), iMes.intValue() - 1, iDia.intValue());
		java.sql.Date sDate = new java.sql.Date(calendar.getTimeInMillis());
		return sDate;
	}

	/**
	 * Metodo encargado de devolver una fecha de tipo sql Date generada a partir
	 * de los parï¿½metros proporcionados.
	 * 
	 * @param cDia
	 *            Cadena correspondiente al nï¿½mero de dï¿½a para generar la
	 *            fecha.
	 * @param cMes
	 *            Cadena correspondiente al nï¿½mero de mes para generar la
	 *            fecha.
	 * @param cAnio
	 *            Cadena correspondiente al nï¿½mero de aï¿½o para generar la
	 *            fecha.
	 * @return Fecha de tipo sql Date generada.
	 */
	public java.sql.Date getDateSQL(String cDia, String cMes, String cAnio) {
		return this.getDateSQL(new Integer(cDia), new Integer(cMes),
				new Integer(cAnio));
	}

	/**
	 * Metodo encargado de devolver una fecha de tipo sql Date generada a partir
	 * de una cadena.
	 * 
	 * @param cFecha
	 *            Cadena de la cual se desea obtener una fecha, con formato
	 *            dd/mm/aaaa.
	 * @return Fecha de tipo sql Date generada.
	 */
	public java.sql.Date getDateSQL(String cFecha) {
		// return this.TodaySQL(); // codificar
		// dd/mm/aaaa
		java.util.StringTokenizer st = new java.util.StringTokenizer(cFecha,
				"/");
		return getDateSQL(st.nextToken(), st.nextToken(), st.nextToken());
	}

	/**
	 * Metodo encargado de devolver una fecha de tipo util Date generada a
	 * partir de los parï¿½metros proporcionados.
	 * 
	 * @param iDia
	 *            Entero correspondiente al dï¿½a de la fecha a generar.
	 * @param iMes
	 *            Entero correspondiente al mes de la fecha a generar.
	 * @param iAnio
	 *            Entero correspondiente al aï¿½o de la fecha a generar.
	 * @return Fecha de tipo util Date generada con los parï¿½metros
	 *         proporcionados.
	 */
	public java.util.Date getDateLONG(Integer iDia, Integer iMes, Integer iAnio) {
		java.util.Calendar calendar = new java.util.GregorianCalendar(
				iAnio.intValue(), iMes.intValue() - 1, iDia.intValue());
		java.util.Date date = new java.util.Date(calendar.getTimeInMillis());
		return date;
	}

	/**
	 * Metodo encargado de devolver una fecha de tipo util Date generada a
	 * partir de los parï¿½metros proporcionados.
	 * 
	 * @param cDia
	 *            Cadena correspondiente al nï¿½mero de dï¿½a para generar la
	 *            fecha.
	 * @param cMes
	 *            Cadena correspondiente al nï¿½mero de mes para generar la
	 *            fecha.
	 * @param cAnio
	 *            Cadena correspondiente al nï¿½mero de aï¿½o para generar la
	 *            fecha.
	 * @return Fecha de tipo util Date generada.
	 */
	public java.util.Date getDateLONG(String cDia, String cMes, String cAnio) {
		return this.getDateLONG(new Integer(cDia), new Integer(cMes),
				new Integer(cAnio));
	}

	/**
	 * Metodo encargado de devolver una fecha de tipo util Date generada a
	 * partir de una cadena.
	 * 
	 * @param cFecha
	 *            Cadena de la cual se desea obtener una fecha, con formato
	 *            dd/mm/aaaa.
	 * @return Fecha de tipo util Date generada.
	 */
	public java.util.Date getDateLONG(String cFecha) {
		java.util.StringTokenizer st = new java.util.StringTokenizer(cFecha,
				"/");
		return getDateLONG(st.nextToken(), st.nextToken(), st.nextToken());
	}

	/**
	 * Metodo encargado de devolver el vector de abreviaturas de mes.
	 * 
	 * @return Vector con los valores de abreviaturas de mes.
	 */
	public Vector getVAbrevMes() {
		return vAbrevMes;
	}

	/**
	 * Metodo encargado de devolver el vector de nombres de mes.
	 * 
	 * @return Vector con los valores de nombres de mes.
	 */
	public Vector getVNombresMes() {
		return vNombresMes;
	}

	/**
	 * Metodo encargado de aumentar o disminuir x nï¿½mero de dï¿½as a la fecha
	 * proporcionada.
	 * 
	 * @param dtFecha
	 *            Fecha a la cual se le desean aumentar o disminuir dï¿½as.
	 * @param iNumDias
	 *            Nï¿½mero de dï¿½as a aumentar o disminuï¿½r de la fecha
	 *            original.
	 * @return Fecha en formato SQL date con la fecha resultante.
	 */
	public java.sql.Date aumentaDisminuyeDias(java.sql.Date dtFecha,
			int iNumDias) {
		Calendar calendar = new GregorianCalendar();
		java.util.Date trialTime = new java.util.Date(dtFecha.getTime());
		calendar.setTime(trialTime);
		calendar.add(Calendar.DATE, iNumDias);
		trialTime = calendar.getTime();
		return new java.sql.Date(trialTime.getTime());
	}

	/**
	 * Metodo encargado de devolver una fecha de tipo sql Date generada a partir
	 * de una cadena formato SQL.
	 * 
	 * @param cFecha
	 *            Cadena de la cual se desea obtener una fecha, con formato
	 *            aaaa-mm-dd.
	 * @return Fecha de tipo sql Date generada.
	 */
	public java.sql.Date getSQLDatefromSQLString(String cFecha) {
		String cAnio = "";
		String cMes = "";
		String cDia = "";
		java.util.StringTokenizer st = new java.util.StringTokenizer(cFecha,
				"-");
		cAnio = st.nextToken();
		cMes = st.nextToken();
		cDia = st.nextToken();
		return getDateSQL(cDia, cMes, cAnio);
	}

	private void writeObject(ObjectOutputStream oos) throws IOException {
		oos.defaultWriteObject();
	}

	private void readObject(ObjectInputStream ois)
			throws ClassNotFoundException, IOException {
		ois.defaultReadObject();
	}

	/**
	 * Agregado por Rafael Alcocer Caldera 15/Ago/2006 Obtiene un java.sqlDate a
	 * partir de un String de la forma "dd-MMM-yyyy"
	 * 
	 * Ejemplo: 10-Ago-2006 => 2006-08-10
	 * 
	 * @param cFecha
	 *            String de formato "dd-MMM-yyyy"
	 * @return Date de tipo java.sql.Date
	 */
	public java.sql.Date getSQLDateFromString(String cFecha) {
		StringTokenizer st = new StringTokenizer(cFecha, "-");
		String cDia = st.nextToken();
		String cMesMMM = st.nextToken();
		String cAnio = st.nextToken();
		String cMesMM = "";

		if (cMesMMM.equalsIgnoreCase("ENE") || cMesMMM.equalsIgnoreCase("JAN")) {
			cMesMM = "01";
		} else if (cMesMMM.equalsIgnoreCase("FEB")) {
			cMesMM = "02";
		} else if (cMesMMM.equalsIgnoreCase("MAR")) {
			cMesMM = "03";
		} else if (cMesMMM.equalsIgnoreCase("ABR")
				|| cMesMMM.equalsIgnoreCase("APR")) {
			cMesMM = "04";
		} else if (cMesMMM.equalsIgnoreCase("MAY")) {
			cMesMM = "05";
		} else if (cMesMMM.equalsIgnoreCase("JUN")) {
			cMesMM = "06";
		} else if (cMesMMM.equalsIgnoreCase("JUL")) {
			cMesMM = "07";
		} else if (cMesMMM.equalsIgnoreCase("AGO")
				|| cMesMMM.equalsIgnoreCase("AGU")) {
			cMesMM = "08";
		} else if (cMesMMM.equalsIgnoreCase("SEP")) {
			cMesMM = "09";
		} else if (cMesMMM.equalsIgnoreCase("OCT")) {
			cMesMM = "10";
		} else if (cMesMMM.equalsIgnoreCase("NOV")) {
			cMesMM = "11";
		} else if (cMesMMM.equalsIgnoreCase("DIC")
				|| cMesMMM.equalsIgnoreCase("DEC")) {
			cMesMM = "12";
		}

		// El mï¿½todo valueOf("yyyy-mm-dd") regresa java.sql.Date
		return java.sql.Date.valueOf(cAnio + "-" + cMesMM + "-" + cDia);
	}
}
