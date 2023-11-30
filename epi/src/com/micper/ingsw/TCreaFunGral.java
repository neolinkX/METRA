package com.micper.ingsw;

/**
 * Creación de funciones de JavaScript para manejo de Submit y Precarga.
 * <p>
 * Ingeniería de Software generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de crear el código de JavaScript necesario para crear
 * las funciones de confirmación de borrado, precarga y cambio de imagenes.
 * 
 * @version 1.0
 *          <p>
 * @author Tecnología Inred S.A. de C.V.
 * @author <dd>LSC. Rafael Miranda Blumenkron
 *         <p>
 * @see <small><a href=
 *      "JavaScript:alert('Consulte los archivos:\n-pg0000000.jsp\n-pg0000004.jsp');"
 *      >Click para ver lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src="TCreaFunGral.png">
 */

public class TCreaFunGral {
	/** Buffer que contendrá el almacenamiento de HTML para el despliegue. */
	StringBuffer sbResultado = new StringBuffer();

	/**
	 * Este constructor genera las funciones de JavaScript que se emplearán en
	 * los JSP.
	 * 
	 * @see <a
	 *      href="JavaScript:alert('Consulte un manual de JavaScript');">JavaScript
	 *      : Precarga de imagenes, confirmación con mensajes</a>
	 */
	public TCreaFunGral() {
		sbResultado.append("<SCRIPT LANGUAGE=\"JavaScript\">\n");
		/* Funcion para confirmar la eliminación de un registro */
		sbResultado.append("  function fConfirmaBorrar(doc){\n");
		sbResultado.append("    var cTexto;\n");
		sbResultado
				.append("    if (doc.forms[0].hdBoton.value == \"Borrar\")\n");
		sbResultado
				.append("      cTexto = \"¿Está seguro de inactivar el registro?\";\n");
		sbResultado.append("    else\n");
		sbResultado
				.append("      cTexto = \"¿Está seguro de eliminar el registro?\";\n");
		sbResultado
				.append("    if ((doc.forms[0].hdBoton.value == \"Borrar\") ||\n");
		sbResultado
				.append("        (doc.forms[0].hdBoton.value == \"BorrarB\")){\n");
		sbResultado.append("      if (! confirm(cTexto))\n");
		sbResultado.append("        return false;\n");
		sbResultado.append("      else\n");
		sbResultado.append("        return true;\n");
		sbResultado.append("    }\n");
		sbResultado.append("    return true;\n");
		sbResultado.append("  }\n\n");
		/*
		 * Funcion para precarga de imagenes en cache, evita cargar todas al
		 * principio
		 */
		sbResultado.append("  function fPreCargarImagen(doc) {\n");
		sbResultado.append("    var d=doc;\n");
		sbResultado.append("    if(d.images){\n");
		sbResultado.append("      if(!d.MM_p)\n");
		sbResultado.append("        d.MM_p=new Array();\n");
		sbResultado
				.append("      var i,j=d.MM_p.length,a=fPreCargarImagen.arguments;\n");
		sbResultado.append("      for(i=1; i<a.length; i++)\n");
		sbResultado.append("        if (a[i].indexOf(\"#\")!=0){\n");
		sbResultado.append("          d.MM_p[j]=new Image;\n");
		sbResultado.append("          d.MM_p[j++].src=a[i];\n");
		sbResultado.append("        }\n");
		sbResultado.append("    }\n");
		sbResultado.append("  }\n\n");
		/*
		 * Función que encuentra el objeto imagen para poder realizar cambio de
		 * imagen sbResultado.append("  var vDocument;\n");
		 * sbResultado.append("  function fEncuentraObjeto(n, d) {\n");
		 * sbResultado.append("    var p,i,x;\n");
		 * sbResultado.append("    if(!d)\n");
		 * sbResultado.append("      d=vDocument;\n"); sbResultado.append(
		 * "    if((p=n.indexOf(\"?\"))>0&&parent.frames.length) {\n");
		 * sbResultado
		 * .append("      d=parent.frames[n.substring(p+1)].document;\n");
		 * sbResultado.append("      n=n.substring(0,p);\n");
		 * sbResultado.append("    }\n");
		 * sbResultado.append("    if(!(x=d[n])&&d.all)\n");
		 * sbResultado.append("      x=d.all[n];\n");
		 * sbResultado.append("    if (d.forms){");
		 * sbResultado.append("      for (i=0;!x&&i<d.forms.length;i++)\n");
		 * sbResultado.append("        x=d.forms[i][n];\n");
		 * sbResultado.append("    }");
		 * sbResultado.append("    for(i=0;!x&&d.layers&&i<d.layers.length;i++)\n"
		 * );
		 * sbResultado.append("      x=fEncuentraObjeto(n,d.layers[i].document);\n"
		 * ); sbResultado.append("    return x;\n");
		 * sbResultado.append("  }\n\n"); /* Función que realiza el cambio de
		 * imagen de un estado a otro
		 * sbResultado.append("  function fCambiaImagen() {\n");
		 * sbResultado.append("    var i,j=0,x,a=fCambiaImagen.arguments;\n");
		 * sbResultado.append("    var doc = a[0];\n");
		 * sbResultado.append("    vDocument = doc;\n");
		 * sbResultado.append("    doc.MM_sr=new Array;\n");
		 * sbResultado.append("    for(i=1;i<(a.length-2);i+=3)\n");
		 * sbResultado.append("      if ((x=fEncuentraObjeto(a[i]))!=null){\n");
		 * sbResultado.append("        doc.MM_sr[j++]=x;\n");
		 * sbResultado.append("        if(!x.oSrc)\n");
		 * sbResultado.append("          x.oSrc=x.src;\n");
		 * sbResultado.append("        x.src=a[i+2];\n");
		 * sbResultado.append("      }\n"); sbResultado.append("  }\n\n"); /*
		 * Funcion para no permitir el botón derecho en el navegador
		 * //sbResultado
		 * .append("  var cMsg = \"Lo sentimos, operación no permitida\";\n");
		 * //sbResultado.append("  function fNoRightClick(e){\n");
		 * //sbResultado.append("    if (document.all){\n");
		 * //sbResultado.append("      if (event.button == 2){\n");
		 * //sbResultado.append("        alert(cMsg);\n");
		 * //sbResultado.append("        return false;\n");
		 * //sbResultado.append("      }\n"); //sbResultado.append("    }\n");
		 * //sbResultado.append("    if (document.layers){\n");
		 * //sbResultado.append("      if (e.which == 3){\n");
		 * //sbResultado.append("        alert(cMsg);\n");
		 * //sbResultado.append("        return false;\n");
		 * //sbResultado.append("      }\n"); //sbResultado.append("    }\n");
		 * //sbResultado.append("  }\n\n");
		 * //sbResultado.append("  if (document.layers){\n");
		 * //sbResultado.append
		 * ("    document.captureEvents(Event.MOUSEDOWN);\n");
		 * //sbResultado.append("  }\n\n");
		 * //sbResultado.append("  document.onmousedown = fNoRightClick();\n\n"
		 * );
		 */
		sbResultado.append("</SCRIPT>");
	}

	/**
	 * Este método se encarga de devolver el HTML generado en el constructor.
	 * 
	 * @return Buffer con texto HTML generado en el constructor
	 */
	public StringBuffer getResultado() {
		return sbResultado;
	}
}