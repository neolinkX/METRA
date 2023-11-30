package com.micper.ingsw;

/**
 * Creaci�n de botones tipo imagen.
 * <p>
 * Ingenier�a de Software generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de crear el c�digo HTML necesario para desplegar un
 * bot�n de tipo imagen con funciones de roll-over o im�gen est�ndar. <br>
 * Las funciones de roll-over son generadas en el frame principal.
 * 
 * @version 1.0
 *          <p>
 * @author Tecnolog�a Inred S.A. de C.V.
 * @author <dd>LSC. Rafael Miranda Blumenkron
 *         <p>
 * @see <small><a href="TCreaBotonRowCol.html">TCreaBotonRowCol</a></small>
 * @see <small><a href="TShowMenu.html">TShowMenu</a></small>
 * @see <small><a href="TTitulo.html">TTitulo</a></small>
 * @see <small><a
 *      href="./../ingsample/pg0700003MAIN.html">com.micper.ingsample.pg0700003MAIN
 *      </a></small>
 * @see <small><a href=
 *      "JavaScript:alert('Consulte los archivos:\n-pg0700002.jsp\n-pg0702010.jsp\n-pg0702011.jsp\n-pg0703010.jsp\n-pg0703011.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src="TCreaBoton.png">
 */

public class TCreaBoton {

	/**
	 * Constructor por omisi�n uso: new TCreaBoton();
	 */
	public TCreaBoton() {
	}

	/**
	 * Este m�todo se encarga de generar el HTML necesario para desplegar una
	 * imagen con efecto roll-over o sin el, dependiendo del tipo de bot�n
	 * solicitado.
	 * <p>
	 * Los tipos de bot�n que se pueden generar son:
	 * <li>1 = Nivel 1 de Men�
	 * 
	 * @param vEntorno
	 *            Contiene los valores de configuraci�n del m�dulo y del JSP
	 * @param iTipoBoton
	 *            N�mero entero para determinar el tipo de bot�n a generar
	 * @param cNomImagen
	 *            Nombre del objeto IMG a generar
	 * @param cSrcImagen
	 *            Nombre del archivo de imagen, sin extensi�n
	 * @param iTipoImg
	 *            Tipo de archivo que se emplear� 0 = gif; 1 = jpg; 2 = bmp
	 * @param cStatus
	 *            Texto a desplegar en la barra de tareas al pasar el mouse
	 *            sobre la imagen
	 * @param cAccClickUrl
	 *            Acci�n que se utilizar� al submitir la forma del men�
	 * @param cInforDef
	 *            Imagen informativa que se desplegar� en la parte derecha del
	 *            men�
	 * @param cRef
	 *            Referencia URL que emplear� el bot�n al momento de hacer click
	 *            en �l
	 * @param vParametros
	 *            Objeto que tiene los par�metros de configuraci�n del m�dulo
	 *            <p>
	 * @return Buffer generado con el c�digo HTML para su despliegue en el JSP
	 */
	public StringBuffer clsCreaBotonMenu(TEntorno vEntorno, int iTipoBoton,
			String cNomImagen, String cSrcImagen, int iTipoImg, String cStatus,
			String cAccClickUrl, String cInforDef, String cRef,
			TParametro vParametros) {
		StringBuffer sbResultado = new StringBuffer();
		String cRutaImg = vParametros.getPropEspecifica("RutaImg");
		String cTipoImg = vEntorno.getTiposImg().elementAt(iTipoImg).toString();
		vEntorno.addListaImgs(cRutaImg + cSrcImagen + "01" + cTipoImg);
		vEntorno.addListaImgs(cRutaImg + cSrcImagen + "02" + cTipoImg);
		vEntorno.addListaImgs(cRutaImg + cSrcImagen + "03" + cTipoImg);
		switch (iTipoBoton) {
		case 1: { // Nivel 1 de Men�
			if (cRef.toUpperCase().compareTo("#") == 0)
				cRef = "JavaScript: document.forms[0].hdMenu.value='"
						+ cAccClickUrl + "';document.forms[0].submit();";
			sbResultado.append("  <a href=\"" + cRef + "\">");
			// sbResultado.append("  onMouseOut=\"if(top.fCambiaImagen)top.fCambiaImagen(document, '"
			// + cNomImagen + "','','" + cRutaImg + cSrcImagen + "01" + cTipoImg
			// +
			// "',1);if(top.fCambiaImagen)top.fCambiaImagen(document, 'bInforma','','"
			// + cRutaImg + cInforDef + cTipoImg +
			// "',1);self.status='';return true;\"\n");
			// sbResultado.append("  onMouseOver=\"if(top.fCambiaImagen)top.fCambiaImagen(document, '"
			// + cNomImagen + "','','" + cRutaImg + cSrcImagen + "02" + cTipoImg
			// +
			// "',1);if(top.fCambiaImagen)top.fCambiaImagen(document, 'bInforma','','"
			// + cRutaImg + cSrcImagen + "03" + cTipoImg + "',1);self.status='"
			// + cStatus + "';return true;\">");
			sbResultado.append("  <img name=\"" + cNomImagen
					+ "\" border=\"0\" src=\"" + cRutaImg + cSrcImagen + "01"
					+ cTipoImg + "\"></a>");
			break;
		}
		}
		return sbResultado;
	}

	/**
	 * Este m�todo se encarga de generar el HTML necesario para desplegar una
	 * imagen con efecto roll-over o sin el, dependiendo del tipo de bot�n
	 * solicitado.
	 * <p>
	 * Los tipos de bot�n que se pueden generar son:
	 * <li>1 = Submit
	 * <li>2 = Reset
	 * <li>3 = URL
	 * <li>4 = Imagen Inactiva
	 * <li>5 = Imagen Activa
	 * <li>6 = URL para bot�n de panel
	 * 
	 * @param vEntorno
	 *            Contiene los valores de configuraci�n del m�dulo y del JSP
	 * @param iTipoBoton
	 *            N�mero entero para determinar el tipo de bot�n a generar
	 * @param cNomImagen
	 *            Nombre del objeto IMG a generar
	 * @param cSrcImagen
	 *            Nombre del archivo de imagen, sin extensi�n
	 * @param iTipoImg
	 *            Tipo de archivo que se emplear� 0 = gif; 1 = jpg; 2 = bmp
	 * @param cStatus
	 *            Texto a desplegar en la barra de tareas al pasar el mouse
	 *            sobre la imagen
	 * @param cAccClickUrl
	 *            Acci�n que se utilizar� al submitir la forma del men�
	 * @param vParametros
	 *            Objeto que tiene los par�metros de configuraci�n del m�dulo
	 *            <p>
	 * @return Buffer generado con el c�digo HTML para su despliegue en el JSP
	 */
	public StringBuffer clsCreaBoton(TEntorno vEntorno, int iTipoBoton,
			String cNomImagen, String cSrcImagen, int iTipoImg, String cStatus,
			String cAccClickUrl, TParametro vParametros) {
		StringBuffer sbResultado = new StringBuffer();
		String cRutaImg = vParametros.getPropEspecifica("RutaImg");
		String cTipoImg = vEntorno.getTiposImg().elementAt(iTipoImg).toString();
		vEntorno.addListaImgs(cRutaImg + cSrcImagen + "01" + cTipoImg);
		vEntorno.addListaImgs(cRutaImg + cSrcImagen + "02" + cTipoImg);
		if (iTipoBoton == 6) {
			vEntorno.addListaImgs(cRutaImg + cSrcImagen + "03" + cTipoImg);
			vEntorno.addListaImgs(cRutaImg + cSrcImagen + "04" + cTipoImg);
		}
		switch (iTipoBoton) {
		case 1: { // Tipo Submit
			sbResultado
					.append("  <a href=\"JavaScript:if (window.fSubmitForm) window.fSubmitForm('"
							+ cAccClickUrl + "')\"\n");
			sbResultado
					.append("  onMouseOut=\"if(top.fCambiaImagen)top.fCambiaImagen(document, '"
							+ cNomImagen
							+ "','','"
							+ cRutaImg
							+ cSrcImagen
							+ "01"
							+ cTipoImg
							+ "',1);self.status='';return true;\"\n");
			sbResultado
					.append("  onMouseOver=\"if(top.fCambiaImagen)top.fCambiaImagen(document, '"
							+ cNomImagen
							+ "','','"
							+ cRutaImg
							+ cSrcImagen
							+ "02"
							+ cTipoImg
							+ "',1);self.status='"
							+ cStatus
							+ "';return true;\">");
			sbResultado.append("  <img name=\"" + cNomImagen
					+ "\" border=\"0\" src=\"" + cRutaImg + cSrcImagen + "01"
					+ cTipoImg + "\"></a>");
			break;
		}
		case 2: { // Tipo Reset
			sbResultado
					.append("  <a href=\"JavaScript:document.forms[0].reset()\" \n");
			sbResultado
					.append("  onMouseOut=\"if(top.fCambiaImagen)top.fCambiaImagen(document, '"
							+ cNomImagen
							+ "','','"
							+ cRutaImg
							+ cSrcImagen
							+ "01"
							+ cTipoImg
							+ "',1);self.status='';return true;\"\n");
			sbResultado
					.append("  onMouseOver=\"if(top.fCambiaImagen)top.fCambiaImagen(document, '"
							+ cNomImagen
							+ "','','"
							+ cRutaImg
							+ cSrcImagen
							+ "02"
							+ cTipoImg
							+ "',1);self.status='"
							+ cStatus
							+ "';return true;\"\n");
			sbResultado.append("  onClick=\"" + cAccClickUrl + "\">");
			sbResultado.append("  <img name=\"" + cNomImagen
					+ "\" border=\"0\" src=\"" + cRutaImg + cSrcImagen + "01"
					+ cTipoImg + "\"></a>");
			break;
		}
		case 3: { // Tipo URL
			sbResultado.append("  <a href=\"" + cAccClickUrl + "\" \n");
			sbResultado
					.append("  onMouseOut=\"if(top.fCambiaImagen)top.fCambiaImagen(document, '"
							+ cNomImagen
							+ "','','"
							+ cRutaImg
							+ cSrcImagen
							+ "01"
							+ cTipoImg
							+ "',1);self.status='';return true;\"\n");
			sbResultado
					.append("  onMouseOver=\"if(top.fCambiaImagen)top.fCambiaImagen(document, '"
							+ cNomImagen
							+ "','','"
							+ cRutaImg
							+ cSrcImagen
							+ "02"
							+ cTipoImg
							+ "',1);self.status='"
							+ cStatus
							+ "';return true;\">");
			sbResultado.append("  <img name=\"" + cNomImagen
					+ "\" border=\"0\" src=\"" + cRutaImg + cSrcImagen + "01"
					+ cTipoImg + "\"></a>");
			break;
		}
		case 4: { // Tipo IMG con prefijo 03 (inactivos)
			sbResultado.append("  <img name=\"" + cNomImagen
					+ "\" border=\"0\" src=\"" + cRutaImg + cSrcImagen + "03"
					+ cTipoImg + "\">");
			break;
		}
		case 5: { // Tipo IMG con Prefijo 01 (activos)
			sbResultado.append("  <img name=\"" + cNomImagen
					+ "\" border=\"0\" src=\"" + cRutaImg + cSrcImagen + "01"
					+ cTipoImg + "\">");
			break;
		}
		case 6: { // Tipo URL para botones de paneles
			sbResultado.append("  <a href=\"" + cAccClickUrl + "\" \n");
			sbResultado
					.append("  onMouseOut=\"if(fMouseOut)fMouseOut(document, '"
							+ cNomImagen + "');self.status='';return true;\"\n");
			sbResultado
					.append("  onMouseOver=\"if(fMouseOver)fMouseOver(document, '"
							+ cNomImagen
							+ "');self.status='"
							+ cStatus
							+ "';return true;\">");
			sbResultado.append("  <img name=\"" + cNomImagen
					+ "\" border=\"0\" src=\"" + cRutaImg + cSrcImagen + "01"
					+ cTipoImg + "\"></a>");
			break;
		}
		case 7: { // Tipo especial para el nuevo template
			sbResultado.append("  <img name=\"" + cNomImagen
					+ "\" border=\"0\" src=\"" + cRutaImg + cSrcImagen + "02"
					+ cTipoImg + "\">");
			break;
		}
		}
		return sbResultado;
	}

	/**
	 * Este m�todo se encarga de generar el HTML necesario para desplegar una
	 * imagen.
	 * 
	 * @param vEntorno
	 *            Contiene los valores de configuraci�n del m�dulo y del JSP
	 * @param cNomImagen
	 *            Nombre del objeto IMG a generar
	 * @param cSrcImagen
	 *            Nombre del archivo de imagen, sin extensi�n
	 * @param iTipoImg
	 *            Tipo de archivo que se emplear� 0 = gif; 1 = jpg; 2 = bmp
	 * @param vParametros
	 *            Objeto que tiene los par�metros de configuraci�n del m�dulo
	 *            <p>
	 * @return Buffer generado con el c�digo HTML para su despliegue en el JSP
	 */
	public StringBuffer clsCreaBoton(TEntorno vEntorno, String cNomImagen,
			String cSrcImagen, int iTipoImg, TParametro vParametros) {
		StringBuffer sbResultado = new StringBuffer();
		String cRutaImg = vParametros.getPropEspecifica("RutaImg");
		String cTipoImg = vEntorno.getTiposImg().elementAt(iTipoImg).toString();
		sbResultado.append("  <img name=\"" + cNomImagen
				+ "\" border=\"0\" src=\"" + cRutaImg + cSrcImagen + "03"
				+ cTipoImg + "\">");
		return sbResultado;
	}
}