<%@ page contentType="text/html" %>
<%@ page import="com.micper.util.*" %>

<jsp:useBean id="bcrequest" class="gob.sct.medprev.msc.BarcodeRequestBean" scope="request"/>
<jsp:setProperty name="bcrequest" property="*"/>

<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>Código de Barras</title>
  </head>
  <body>
    <h1>Configurar Código de Barras</h1>
    <%
    final String genbc = bcrequest.toURL();
    if (bcrequest.isSVG()) {
    %>
    <p>El código de barras generado en formato SVG (solo es desplegado si el navegador lo soporta):</p>
    <p>
      <!--object type="image/svg+xml" data="http://localhost:8080/barcode4j/gensvg?type=code128&msg=123&ext=.svg" name="DynamicBarcode" width="400" height="150"/-->
      <embed src="<%=genbc%>&ext=.svg" pluginspage="http://www.adobe.com/svg/viewer/install/" width="100%" height="100"/>
    </p>
    <%
    } else if (bcrequest.isBitmap()) {
    %>
    <p>El código de barras generado en formato <%=bcrequest.getFormat()%> (solo es desplegado si <%=bcrequest.getFormat()%> es soportado por el servidor y navegador):</p>
    <p>
      <img src="<%=genbc%>"/>
    </p>
    <%
    } else {
        %>
        <p><i>El código de barras generado no puede ser previsualizado. El formato es <%=bcrequest.getFormat()%>.</i></p>
        <%
    }
    %>
    <p>La siguiente liga muestra el Código de Barras generado:</p>
    <table width="100%" border="1" rules="none" cellpadding="5">
      <tr>
        <td width="100%">
          <p>
            <a href="<%=genbc%>"><%=genbc%></a>
          </p>
        </td>
      </tr>
    </table>
    <p>Cambiar los parámetros:</p>
    <form method="post" action="barcode4j.jsp">
      <table border="0">
        <tr>
          <td/>
          <td>
            <p>
              <input type="submit" value="Generar Código"/>
            </p>
          </td>
        </tr>
        <tr>
          <td>
            <p>Formato de salida (requerido):</p>
          </td>
          <td>
            <p>
              <select name="format">
                <%
                final String[] FORMATS = new String[] {"svg", "eps", "jpeg", "tiff", "png", "gif"};

                for (int i = 0; i < FORMATS.length; i++) {
                  out.print("<option");

                  //if (FORMATS[i].equals(bcrequest.getFormat())) {
                    //out.print(" selected=\"selected\"");
                  //}
                  if (FORMATS[i].equals("jpeg")) {
                      out.print(" selected=\"selected\"");
                  }

                  out.print(">");
                  out.print(FORMATS[i]);
                  out.println("</option>");
                }
                %>
              </select>
            </p>
          </td>
          <td>
            <p>Algunos formatos de mapas de bits no funcionan si no existe el codificador de imagen para este formato.</p>
          </td>
        </tr>
        <tr>
          <td>
            <p>Resolución del mapa de bits (en dpi):</p>
          </td>
          <td>
            <p>
              <input type="text" name="resolution" value="<%= (bcrequest.getResolution() != null ? bcrequest.getResolution() : "500") %>"/>
            </p>
          </td>
          <td>
            <p>Aplica sólo a formatos de mapa de bits. Ejemplo: 300</p>
          </td>
        </tr>
        <tr>
          <td>
            <p>Tipo de Código de barras (requerido):</p>
          </td>
          <td>
            <p>
              <select name="type">
                <%
                final String[] BARCODES = new String[] {"code128", "code39"};

                for (int i = 0; i < BARCODES.length; i++) {
                    out.print("<option");

                    if (BARCODES[i].equals(bcrequest.getType())) {
                        out.print(" selected=\"selected\"");
                    }

                    out.print(">");
                    out.print(BARCODES[i]);
                    out.println("</option>");
                }
                %>
              </select>
            </p>
          </td>
        </tr>
        <tr>
          <td>
            <p>No. de Análisis (requerido):</p>
          </td>
          <td>
            <p>
              <%
                if (request.getParameter("numAnalisis") != null) {
                     //System.out.println("########...numAnalisis: " + request.getParameter("numAnalisis"));
               %>
                <input type="text" name="msg" value="<%= request.getParameter("numAnalisis") %>" readonly="true"/>
              <%
                }
              %>
            </p>
          </td>
        </tr>
        <tr>
          <td>
            <p>Altura:</p>
          </td>
          <td>
            <p>
              <input type="text" name="height" value="<%= (bcrequest.getHeight() != null ? bcrequest.getHeight() : "0.5cm") %>"/>
            </p>
          </td>
          <td>
            <p>Ejemplo: 2.5cm</p>
          </td>
        </tr>
        <tr>
          <td>
            <p>Ancho del Módulo:</p>
          </td>
          <td>
            <p>
              <input type="text" name="moduleWidth" value="<%= (bcrequest.getModuleWidth() != null ? bcrequest.getModuleWidth() : "0.05mm") %>" readonly="true"/>
            </p>
          </td>
          <td>
            <p>Ejemplo: 0.3mm</p>
          </td>
        </tr>
        <tr>
          <td>
            <p>Factor de Anchura:</p>
          </td>
          <td>
            <p>
              <input type="text" name="wideFactor" value="<%= (bcrequest.getWideFactor() != null ? bcrequest.getWideFactor() : "") %>" readonly="true"/>
            </p>
          </td>
          <td>
            <p>Ejemplo: 2 or 3</p>
          </td>
        </tr>
        <tr>
          <td>
            <p>Habilitar Quiet Zone:</p>
          </td>
          <td>
            <p>
              <input type="text" name="quietZone" value="<%= (bcrequest.getQuietZone() != null ? bcrequest.getQuietZone() : "disable") %>" readonly="true"/>
            </p>
          </td>
          <td>
            <p>Ejemplo: 10mw ó 1cm. Usar "disable" para deshabilitar.</p>
          </td>
        </tr>
        <tr>
          <td>
            <p>Posición de la Etiqueta:</p>
          </td>
          <td>
            <p>
              <select name="humanReadable">
                <%
                final String[] HRPOSITIONS = new String[] {"[default]", "top", "bottom", "none"};
                String hrpos = bcrequest.getHumanReadable();
                if (hrpos == null) {
                    hrpos = HRPOSITIONS[0];
                }
                for (int i = 0; i < HRPOSITIONS.length; i++) {
                    out.print("<option");
                    if (HRPOSITIONS[i].equals(hrpos)) {
                        out.print(" selected=\"selected\"");
                    }
                    out.print(">");
                    out.print(HRPOSITIONS[i]);
                    out.println("</option>");
                }
                %>
              </select>
            </p>
          </td>
        </tr>
        <tr>
          <td>
            <p>Tamaño de Letra</p>
          </td>
          <td>
            <p>
              <input type="text" name="humanReadableSize" value="<%= (bcrequest.getHumanReadableSize() != null ? bcrequest.getHumanReadableSize() : "3pt") %>"/>
            </p>
          </td>
          <td>
            <p>Ejemplo: 8pt</p>
          </td>
        </tr>
        <tr>
          <td>
            <p>Tipo de Letra</p>
          </td>
          <td>
            <p>
              <input type="text" name="humanReadableFont" value="<%= (bcrequest.getHumanReadableFont() != null ? bcrequest.getHumanReadableFont() : "Agency FB") %>"/>
            </p>
          </td>
          <td>
            <p>Ejemplo: "Helvetica"</p>
          </td>
        </tr>
      </table>
    </form>
  </body>
</html>
