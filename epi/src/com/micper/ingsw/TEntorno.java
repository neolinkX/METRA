package com.micper.ingsw;
 
import java.util.*;
import java.lang.*;
import java.text.*;
 
/**
 * Definici�n de atributos del entorno de un JSP.
 * <p>
 * Ingenier�a de Software generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de definir diferentes atributos que pueden emplearse
 * dentro de un JSP, y los conserva para su uso a lo largo del JSP. <br>
 * Estos atributos son colocados generalmente en las clases de configuraci�n,
 * pero puede cambiarse el valor de las mismas en cualquier lugar del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Tecnolog�a Inred S.A. de C.V.
 * @author <dd>LSC. Rafael Miranda Blumenkron
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-(*.jsp)');">Click para
 *      ver lista de JSP's</a></small>
 * @see <small><a href="TCabeceras.html">TCabeceras</a></small>
 * @see <small><a href="TCreaBoton.html">TCreaBoton</a></small>
 * @see <small><a href="TCreaBotonRowCol.html">TCreaBotonRowCol</a></small>
 * @see <small><a href="TShowMenu.html">TShowMenu</a></small>
 * @see <small><a href="TShowNavPanel.html">TShowNavPanel</a></small>
 * @see <small><a href="TShowPanel.html">TShowPanel</a></small>
 * @see <small><a href="TShowUpdPanel.html">TShowUpdPanel</a></small>
 * @see <small><a href="TSubTitulo.html">TSubTitulo</a></small>
 * @see <small><a href="TTipoImagen.html">TTipoImagen</a></small>
 * @see <small><a href="TTitulo.html">TTitulo</a></small>
 * @see <small><a href="TTooltips.html">TTooltips</a></small>
 * @see <small><a href="TValidaAcceso.html">TValidaAcceso</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src="TEntorno.png">
 */

public class TEntorno {
	/**
	 * Indica si los botones principales (desplegados en el encabezado) ser�n
	 * visibles o no.
	 */
	boolean lBtnPrinVisible;
	/** Contendr� a lo largo del JSP la relaci�n de imagenes a precargar. */
	Vector ListaImgs;
	/**
	 * Contiene la lista de los tipos de im�gen soportados 0 = GIF; 1 = JPG; 2 =
	 * BMP; 3 = PNG.
	 */
	Vector TiposImg;
	/**
	 * Contendr� la cadena que debe desplegarse en la barra de t�tulo del
	 * navegador.
	 */
	String TituloVentana;
	/** Contendr� la cadena que se desplegar� en la barra de subt�tulo del JSP. */
	String TituloPagina;
	/** Contendr� la cadena que se desplegar� en la barra de t�tulo del JSP. */
	int NumModulo;
	/**
	 * Contendr� el n�mero de m�dulo en el que se encuentra el JSP, sirve para
	 * obtener valores de configuraci�n.
	 */
	String cNumModulo;
	/**
	 * Contendr� el n�mero de m�dulo en formato texto de dos posiciones, se
	 * emplea para generar nombres de JSP.
	 */
	String NombrePagina;
	/**
	 * Indica el n�mero de renglones en que se desplegar�n los botones en la
	 * barra de t�tulo.
	 */
	int NumRengEncab;
	/**
	 * Indica el nombre del par�metro de archivos de funci�n de JavaScript a
	 * buscar.
	 */
	String ArchFuncs;
	/** Indica el m�todo en el cual se submitir� una forma. */
	String MetodoForm;
	/** Indica la acci�n de una forma, es decir hacia quien se submite la forma. */
	String ActionForm;
	/**
	 * Indica la funci�n de JavaScript a ejecutar cuando la p�gina termine de
	 * cargarse.
	 */
	String cOnLoad;
	/** Indica el nombre del par�metro de ruta de archivos de ayuda a buscar. */
	String RutaAyuda;
	/** Indica el nombre del archivo de tooltips a emplear. */
	String ArchTooltips;
	/** Indica el nombre del archivo de ayuda asociado a un JSP. */
	String ArchAyuda;
	/** Indica el tipo de im�genes que emplear� el JSP por omisi�n. */
	int TipoImg;
	/**
	 * Indica el URL que se asociar� a la im�gen del logotipo de la organizaci�n
	 * para mostrar el acerca de.
	 */
	String UrlLogo;
	/**
	 * Indica el texto que se mostrar� en la barra de estado al pasar el mouse
	 * sobre el logotipo de la organizaci�n.
	 */
	String BarraEdoLogo;
	/**
	 * Indica el nombre del par�metro del archivo de funciones para validaci�n
	 * de cat�logos que se vincular� al JSP.
	 */
	String ArchFCatalogo;

	/**
	 * Constructor por omisi�n uso: new TEntorno(); Inicializa el valor de los
	 * tipos de imagen y otros valores.
	 */ 
	public TEntorno() { 
		TiposImg = new Vector();
		TiposImg.addElement(".gif");
		TiposImg.addElement(".jpg");
		TiposImg.addElement(".bmp");
		TiposImg.addElement(".png");
		ListaImgs = new Vector();
		lBtnPrinVisible = true;
	}

	/**
	 * Este m�todo se encarga de asociar al atributo lBtnPrinVisible el valor
	 * enviado por el usuario.
	 * 
	 * @param lVisible
	 *            Valor que se asignar� al atributo.
	 */
	public void setBtnPrincVisible(boolean lVisible) {
		lBtnPrinVisible = lVisible;
	}

	/**
	 * Este m�todo se encarga de devolver el atributo lBtnPrinVisible,
	 * previamente asignado.
	 * 
	 * @return Valor que se asign� al atributo.
	 */
	public boolean getBtnPrincVisible() {
		return lBtnPrinVisible;
	}

	/**
	 * Este m�todo se encarga de agregar la imagen enviada a la lista de
	 * im�genes a precargar en el atributo ListaImgs.
	 * 
	 * @param cImagen
	 *            Valor que se asignar� al atributo.
	 */
	public void addListaImgs(String cImagen) {
		ListaImgs.addElement(cImagen);
	}

	/**
	 * Este m�todo se encarga de vaciar la lista de im�genes a precargar en el
	 * JSP.
	 */
	public void clearListaImgs() {
		ListaImgs.clear();
	}

	/**
	 * Este m�todo se encarga de devolver el atributo ListaImgs, previamente
	 * asignado.
	 * 
	 * @return Valor que se asign� al atributo.
	 */
	public Vector getListaImgs() {
		return ListaImgs;
	}

	/**
	 * Este m�todo se encarga de devolver el atributo TiposImg, previamente
	 * asignado.
	 * 
	 * @return Valor que se asign� al atributo.
	 */
	public Vector getTiposImg() {
		return TiposImg;
	}

	/**
	 * Este m�todo se encarga de asociar al atributo TituloVentana el valor
	 * enviado por el usuario.
	 * 
	 * @param cTituloV
	 *            Valor que se asignar� al atributo.
	 */
	public void setTituloVentana(String cTituloV) {
		TituloVentana = cTituloV;
	}

	/**
	 * Este m�todo se encarga de devolver el atributo TituloVentana, previamente
	 * asignado.
	 * 
	 * @return Valor que se asign� al atributo.
	 */
	public String getTituloVentana() {
		return TituloVentana;
	}

	/**
	 * Este m�todo se encarga de asociar al atributo TituloPagina el valor
	 * enviado por el usuario.
	 * 
	 * @param cTituloP
	 *            Valor que se asignar� al atributo.
	 */
	public void setTituloPagina(String cTituloP) {
		TituloPagina = cTituloP;
	}

	/**
	 * Este m�todo se encarga de devolver el atributo TituloPagina, previamente
	 * asignado.
	 * 
	 * @return Valor que se asign� al atributo.
	 */
	public String getTituloPagina() {
		return TituloPagina;
	}

	/**
	 * Este m�todo se encarga de asociar al atributo NumModulo el valor enviado
	 * por el usuario. <br>
	 * Adicionalmente se crear� el valor del atributo cNumModulo, mismo de
	 * NumModulo en formato caracter de dos d�gitos.
	 * 
	 * @param iNumModulo
	 *            Valor que se asignar� al atributo.
	 */
	public void setNumModulo(int iNumModulo) {
		NumModulo = iNumModulo;
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMinimumIntegerDigits(2);
		nf.setMaximumIntegerDigits(2);
		nf.setGroupingUsed(false);
		cNumModulo = nf.format(iNumModulo);
	}

	/**
	 * Este m�todo se encarga de devolver el atributo NumModulo, previamente
	 * asignado.
	 * 
	 * @return Valor que se asign� al atributo.
	 */
	public int getNumModulo() {
		return NumModulo;
	}

	/**
	 * Este m�todo se encarga de devolver el atributo cNumModulo, previamente
	 * asignado. (M�dulo en formato caracter de dos d�gitos).
	 * 
	 * @return Valor que se asign� al atributo.
	 */
	public String getNumModuloStr() {
		return cNumModulo;
	}

	/**
	 * Este m�todo se encarga de asociar al atributo NombrePagina el valor
	 * enviado por el usuario.
	 * 
	 * @param cNomPag
	 *            Valor que se asignar� al atributo.
	 */
	public void setNombrePagina(String cNomPag) {
		NombrePagina = cNomPag;
	}

	/**
	 * Este m�todo se encarga de devolver el atributo NombrePagina, previamente
	 * asignado.
	 * 
	 * @return Valor que se asign� al atributo.
	 */
	public String getNombrePagina() {
		return NombrePagina;
	}

	/**
	 * Este m�todo se encarga de asociar al atributo NumRengEncab el valor
	 * enviado por el usuario.
	 * 
	 * @param iNumRengEncab
	 *            Valor que se asignar� al atributo.
	 */
	public void setNumRengEncab(int iNumRengEncab) {
		NumRengEncab = iNumRengEncab;
	}

	/**
	 * Este m�todo se encarga de devolver el atributo NumRengEncab, previamente
	 * asignado.
	 * 
	 * @return Valor que se asign� al atributo.
	 */
	public int getNumRengEncab() {
		return NumRengEncab;
	}

	/**
	 * Este m�todo se encarga de asociar al atributo ArchFuncs el valor enviado
	 * por el usuario.
	 * 
	 * @param cArchFuncs
	 *            Valor que se asignar� al atributo.
	 */
	public void setArchFuncs(String cArchFuncs) {
		ArchFuncs = cArchFuncs;
	}

	/**
	 * Este m�todo se encarga de devolver el atributo ArchFuncs, previamente
	 * asignado.
	 * 
	 * @return Valor que se asign� al atributo.
	 */
	public String getArchFuncs() {
		return ArchFuncs;
	}

	/**
	 * Este m�todo se encarga de asociar al atributo MetodoForm el valor enviado
	 * por el usuario.
	 * 
	 * @param cMetodoForm
	 *            Valor que se asignar� al atributo.
	 */
	public void setMetodoForm(String cMetodoForm) {
		MetodoForm = cMetodoForm;
	}

	/**
	 * Este m�todo se encarga de devolver el atributo MetodoForm, previamente
	 * asignado.
	 * 
	 * @return Valor que se asign� al atributo.
	 */
	public String getMetodoForm() {
		return MetodoForm;
	}

	/**
	 * Este m�todo se encarga de asociar al atributo ActionForm el valor enviado
	 * por el usuario.
	 * 
	 * @param cActionForm
	 *            Valor que se asignar� al atributo.
	 */
	public void setActionForm(String cActionForm) {
		ActionForm = cActionForm;
	}

	/**
	 * Este m�todo se encarga de devolver el atributo ActionForm, previamente
	 * asignado.
	 * 
	 * @return Valor que se asign� al atributo.
	 */
	public String getActionForm() { 
		return ActionForm;
	}

	/**
	 * Este m�todo se encarga de asociar al atributo cOnLoad el valor enviado
	 * por el usuario.
	 * 
	 * @param cValor
	 *            Valor que se asignar� al atributo.
	 */
	public void setOnLoad(String cValor) {
		cOnLoad = cValor;
	}

	/**
	 * Este m�todo se encarga de devolver el atributo cOnLoad, previamente
	 * asignado.
	 * 
	 * @return Valor que se asign� al atributo.
	 */
	public String getOnLoad() {
		return cOnLoad;
	}

	/**
	 * Este m�todo se encarga de asociar al atributo RutaAyuda el valor enviado
	 * por el usuario.
	 * 
	 * @param cRutaAyuda
	 *            Valor que se asignar� al atributo.
	 */
	public void setParamRutaAyuda(String cRutaAyuda) {
		RutaAyuda = cRutaAyuda;
	}

	/**
	 * Este m�todo se encarga de devolver el atributo RutaAyuda, previamente
	 * asignado.
	 * 
	 * @return Valor que se asign� al atributo.
	 */
	public String getParamRutaAyuda() {
		return RutaAyuda;
	}

	/**
	 * Este m�todo se encarga de asociar al atributo ArchTooltips el valor
	 * enviado por el usuario.
	 * 
	 * @param cArchTooltips
	 *            Valor que se asignar� al atributo.
	 */
	public void setArchTooltips(String cArchTooltips) {
		ArchTooltips = cArchTooltips;
	}

	/**
	 * Este m�todo se encarga de devolver el atributo ArchTooltips, previamente
	 * asignado.
	 * 
	 * @return Valor que se asign� al atributo.
	 */
	public String getArchTooltips() {
		return ArchTooltips;
	}

	/**
	 * Este m�todo se encarga de asociar al atributo ArchAyuda el valor enviado
	 * por el usuario.
	 * 
	 * @param cArchAyuda
	 *            Valor que se asignar� al atributo.
	 */
	public void setArchAyuda(String cArchAyuda) {
		ArchAyuda = cArchAyuda;
	}

	/**
	 * Este m�todo se encarga de devolver el atributo ArchAyuda, previamente
	 * asignado.
	 * 
	 * @return Valor que se asign� al atributo.
	 */
	public String getArchAyuda() {
		return ArchAyuda;
	}

	/**
	 * Este m�todo se encarga de asociar al atributo TipoImg el valor enviado
	 * por el usuario.
	 * 
	 * @param iTipoImg
	 *            Valor que se asignar� al atributo.
	 */
	public void setTipoImg(int iTipoImg) {
		TipoImg = iTipoImg;
	}

	/**
	 * Este m�todo se encarga de devolver el atributo TipoImg, previamente
	 * asignado.
	 * 
	 * @return Valor que se asign� al atributo.
	 */
	public int getTipoImg() {
		return TipoImg;
	}

	/**
	 * Este m�todo se encarga de asociar al atributo UrlLogo el valor enviado
	 * por el usuario.
	 * 
	 * @param cUrlLogo
	 *            Valor que se asignar� al atributo.
	 */
	public void setUrlLogo(String cUrlLogo) {
		UrlLogo = cUrlLogo;
	}

	/**
	 * Este m�todo se encarga de devolver el atributo UrlLogo, previamente
	 * asignado.
	 * 
	 * @return Valor que se asign� al atributo.
	 */
	public String getUrlLogo() {
		return UrlLogo;
	}

	/**
	 * Este m�todo se encarga de asociar al atributo BarraEdoLogo el valor
	 * enviado por el usuario.
	 * 
	 * @param cBarraEdoLogo
	 *            Valor que se asignar� al atributo.
	 */
	public void setBarraEdoLogo(String cBarraEdoLogo) {
		BarraEdoLogo = cBarraEdoLogo;
	}

	/**
	 * Este m�todo se encarga de devolver el atributo BarraEdoLogo, previamente
	 * asignado.
	 * 
	 * @return Valor que se asign� al atributo.
	 */
	public String getBarraEdoLogo() {
		return BarraEdoLogo;
	}

	/**
	 * Este m�todo se encarga de asociar al atributo ArchFCatalogo el valor
	 * enviado por el usuario.
	 * 
	 * @param cArchF
	 *            Valor que se asignar� al atributo.
	 */
	public void setArchFCatalogo(String cArchF) {
		ArchFCatalogo = cArchF;
	}

	/**
	 * Este m�todo se encarga de devolver el atributo ArchFCatalogo, previamente
	 * asignado.
	 * 
	 * @return Valor que se asign� al atributo.
	 */
	public String getArchFCatalogo() {
		return ArchFCatalogo;
	}
}