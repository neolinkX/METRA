<%/**
 * Title:        Administraci�n y Seguridad
 * Description:  M�dulo de Administraci�n y Seguridad
 * @author AG SA
 * @version 1.0
 * Clase:        JSP para prueba de ingenier�a
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="com.micper.seguridad.dao.*" %>
<%@ page import="com.micper.seguridad.vo.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>


<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.util.*"%>


<html>
<%  // Unica Parte del C�digo que debe modificarse
        pg0700007CFG  clsConfig = new pg0700007CFG();
%>
<script language="JavaScript">
  function fOnLoad(){
  }
  function fValidaTodo(){
    return true;
  }
  function fSubmite(objImg, cValor){
  }
  function cambiaSource(objImagen, cEstado){
  }
  function fMouseOver(objDoc, cImagen, cEstatus){
  }
  function fMouseOut(objDoc, cImagen){
  }
  function cambiaEstado(cEstado){
  }
</script>


<script language="JavaScript">

 function fGetFile2(cLocal,cURL){
	  oHTP.cArchivoLocal = cLocal;
	  oHTP.cURLArchivo = cURL;
	  oHTP.fGetFile();
	}
	function descArch(){
		var rutaLocalTemp = "<%=""+new TParametro("07").getPropEspecifica("LOCAL_FOLDER_TEMP")%>";
		var fs = new ActiveXObject("Scripting.FileSystemObject");
		var rutaDllsEspanol64 = "C:\\Archivos de Programa (x86)\\Griaule\\Fingerprint SDK 2009\\bin\\x64\\";
		var rutaDllsEspanol32 = "C:\\Archivos de Programa\\Griaule\\Fingerprint SDK 2009\\bin\\x64\\";
		var rutaDllsIngles64 = "C:\\Program Files (x86)\\Griaule\\Fingerprint SDK 2009\\bin\\x64\\";
		var rutaDllsIngles32 = "C:\\Program Files\\Griaule\\Fingerprint SDK 2009\\bin\\x64\\";
		try{
			//alert(rutaLocalTemp);
			folderBool = fs.FolderExists(rutaLocalTemp);
			if (!folderBool){
			 	fs.CreateFolder(rutaLocalTemp);
			}
			folderA = fs.FolderExists(rutaDllsEspanol64);
			if (folderA){
				fs.CopyFile(rutaDllsEspanol64 + "*.dll",rutaLocalTemp,false);
				descargaFinal();
			}
			folderB = fs.FolderExists(rutaDllsEspanol32);
			if (folderB){
				fs.CopyFile(rutaDllsEspanol32 + "*.dll",rutaLocalTemp,false);
				descargaFinal();
			}
			folderC = fs.FolderExists(rutaDllsIngles64);
			if (folderC){
				fs.CopyFile(rutaDllsIngles64 + "*.dll",rutaLocalTemp,false);
				descargaFinal();
			}
			folderD = fs.FolderExists(rutaDllsIngles32);
			if (folderD){
				fs.CopyFile(rutaDllsIngles32 + "*.dll",rutaLocalTemp,false);
				descargaFinal();
			}
			
		 } catch (err) {
			//alert("Favor de instalar FingerPrint. " + err.message);
			 descargaFinal();
		 }
	}
	function descargaFinal(){
		var fsx = new ActiveXObject("Scripting.FileSystemObject");
		var archiExist1 = fsx.FileExists("C:/Windows/Temp/TCS/Sct_Fingerprint.zip");
		var archiExist2 = fsx.FileExists("C:/Windows/Temp/TCS/fingerUtilities.jar");
		var archiExist3 = fsx.FileExists("C:/Windows/Temp/TCS/FingerDesc.jar");
		//alert(archiExist1 + " " + archiExist2);
		if(!archiExist1){
			fGetFile2("C:\\Windows\\Temp\\TCS\\Sct_Fingerprint.zip","http://servidorimagenes.sct.gob.mx/medprev/Temp/Sct_Fingerprint.zip");
		}
		if(!archiExist2){
			fGetFile2("C:\\Windows\\Temp\\TCS\\fingerUtilities.jar","http://servidorimagenes.sct.gob.mx/medprev/Temp/fingerUtilities.jar");
		}
		if(!archiExist3){
			fGetFile2("C:\\Windows\\Temp\\TCS\\FingerDesc.jar","http://servidorimagenes.sct.gob.mx/medprev/Temp/FingerDesc.jar");
		}
		var shell = new ActiveXObject("WScript.shell");
		var pathProg = "javaw -jar c:\\Windows\\Temp\\TCS\\FingerDesc.jar ";
		shell.run(pathProg);
	}
</script>

<%
        TError      vErrores      = new TError();
        StringBuffer  sbErroresAcum = new StringBuffer();
        TEntorno    vEntorno      = new TEntorno();
        TNavPanel   NavPanel      = new TNavPanel();
  Vector vBotones = new Vector();
  Vector vUrls    = new Vector();
  Vector vEstatus = new Vector();
  Vector lActivos = new Vector();
  vEntorno.setNumModulo(07);
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());
  clsConfig.runConfig(vEntorno, vBotones, vUrls, vEstatus, lActivos);
  String cRutaImg = vParametros.getPropEspecifica("RutaImg");
  String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
  String cRutaAyuda = vParametros.getPropEspecifica("html");
  String cTitulo = "" + request.getParameter("hdTitulo");
 //System.out.print(cTitulo);
  if(cTitulo.compareTo("null") == 0)
     cTitulo = "";
  else
     cTitulo = vParametros.getPropEspecifica("NomModulo") + " " + cTitulo;

  int j = 0;
  out.println("<script LANGUAGE=" + "\""  + "JavaScript"  + "\""  + ">");
  if (request.getParameter("opc") != null){
     String ObjetoDes = request.getParameter("objDes");
     switch(new Integer(request.getParameter("opc")).intValue()) {

      case 2:
           // Estado
           TVEntidadFed vEntidadFed = new TVEntidadFed();
           TDEntidadFed dEntidadFed = new TDEntidadFed();
           Vector vcEntidadFed = new Vector();
           vcEntidadFed = dEntidadFed.FindByAll(" where iCvePais = " + request.getParameter("val1").toString());
           out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
           if (vcEntidadFed.size() > 0){
           out.println("form." + ObjetoDes + ".length=1;");
           out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
           out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
           for (int i = 0; i < vcEntidadFed.size(); i++){
              j = j + 1;
              vEntidadFed = (TVEntidadFed) vcEntidadFed.get(i);
              out.println("i=form." + ObjetoDes + ".length + 1;");
              out.println("form." + ObjetoDes + ".length=i;");
              out.println("form." + ObjetoDes + "[" + j  + "].text=" + "\"" + vEntidadFed.getCNombre()  + "\"" +";");
              out.println("form." + ObjetoDes + "[" + j + "].value="+ "\"" + vEntidadFed.getICveEntidadFed()  + "\"" +";");
           }
        }else{
           out.println("form." + ObjetoDes + ".length=1;");
           out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Datos no disponibles" + "\"" + ";");
           out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
        }

        break;

      case 3:
           // Municipio
           TVMunicipio vMunicipio= new TVMunicipio();
           TDMunicipio dMunicipio = new TDMunicipio();
           Vector vcMunicipio= new Vector();
           vcMunicipio = dMunicipio.FindByAll(request.getParameter("val1").toString(),request.getParameter("val2").toString());
           out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
           if (vcMunicipio.size() > 0){
           out.println("form." + ObjetoDes + ".length=1;");
           out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
           out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
           for (int i = 0; i < vcMunicipio.size(); i++){
              j = j + 1;
              vMunicipio = (TVMunicipio) vcMunicipio.get(i);
              out.println("i=form." + ObjetoDes + ".length + 1;");
              out.println("form." + ObjetoDes + ".length=i;");
              out.println("form." + ObjetoDes + "[" + j  + "].text=" + "\"" + vMunicipio.getCNombre()  + "\"" +";");
              out.println("form." + ObjetoDes + "[" + j + "].value="+ "\"" + vMunicipio.getICveMunicipio() + "\"" +";");
           }
        }else{
           out.println("form." + ObjetoDes + ".length=1;");
           out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Datos no disponibles" + "\"" + ";");
           out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
        }

        break;

       case 4:
           // Modulos
           TVGRLModulo vModulo= new TVGRLModulo();
           TDGRLModulo dModulo = new TDGRLModulo();
           Vector vcModulo= new Vector();
           vcModulo = dModulo.FindByAll(request.getParameter("val1").toString());

           out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
           if (vcModulo.size() > 0){
           out.println("form." + ObjetoDes + ".length=1;");
           out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
           out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
           for (int i = 0; i < vcModulo.size(); i++){
              j = j + 1;
              vModulo = (TVGRLModulo) vcModulo.get(i);
              out.println("i=form." + ObjetoDes + ".length + 1;");
              out.println("form." + ObjetoDes + ".length=i;");
              out.println("form." + ObjetoDes + "[" + j  + "].text=" + "\"" + vModulo.getCDscModulo()  + "\"" +";");
              out.println("form." + ObjetoDes + "[" + j + "].value="+ "\"" + vModulo.getICveModulo() + "\"" +";");
           }
        }else{
           out.println("form." + ObjetoDes + ".length=1;");
           out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Datos no disponibles" + "\"" + ";");
           out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
        }

        break;
        
       case 5:
           // Municipio
           TVGRLLocalidad vLocalidad= new TVGRLLocalidad();
           TDGRLLocalidad dLocalidad = new TDGRLLocalidad();
           Vector vcLocalidad= new Vector();
           vcLocalidad = dLocalidad.FindByAll(request.getParameter("val1").toString(),request.getParameter("val2").toString(),request.getParameter("val3").toString());
           out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
           if (vcLocalidad.size() > 0){ 
           out.println("form." + ObjetoDes + ".length=1;");
           out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
           out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
           for (int i = 0; i < vcLocalidad.size(); i++){
              j = j + 1;
              vLocalidad = (TVGRLLocalidad) vcLocalidad.get(i);
              out.println("i=form." + ObjetoDes + ".length + 1;");
              out.println("form." + ObjetoDes + ".length=i;");
              out.println("form." + ObjetoDes + "[" + j  + "].text=" + "\"" + vLocalidad.getCNombre()  + "\"" +";");
              out.println("form." + ObjetoDes + "[" + j + "].value="+ "\"" + vLocalidad.getICveLocalidad() + "\"" +";");
           }
        }else{
           out.println("form." + ObjetoDes + ".length=1;");
           out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Datos no disponibles" + "\"" + ";");
           out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
        }

        break;
        
         case 8:
           // Discapacidad ? SUBGRUPO
           TVGRLDISCAPACIDAD vGRLDISCAPACIDAD = new TVGRLDISCAPACIDAD();
           TDGRLDISCAPACIDAD dGRLDISCAPACIDAD = new TDGRLDISCAPACIDAD();
           Vector vcGRLDISCAPACIDAD = new Vector();
           vcGRLDISCAPACIDAD = dGRLDISCAPACIDAD.FindByAllS(" where iCveGrupo = " + request.getParameter("val1").toString());
           out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
           if (vcGRLDISCAPACIDAD.size() > 0){
                   out.println("form." + ObjetoDes + ".length=1;");
                   out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
                   out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
                   for (int i = 0; i < vcGRLDISCAPACIDAD.size(); i++){
                      j = j + 1;
                      vGRLDISCAPACIDAD = (TVGRLDISCAPACIDAD) vcGRLDISCAPACIDAD.get(i);
                      out.println("i=form." + ObjetoDes + ".length + 1;");
                      out.println("form." + ObjetoDes + ".length=i;");
                      out.println("form." + ObjetoDes + "[" + j  + "].text=" + "\"" + vGRLDISCAPACIDAD.getCDscSubGrupoD()  + "\"" +";");
                      out.println("form." + ObjetoDes + "[" + j + "].value="+ "\"" + vGRLDISCAPACIDAD.getICveSubGrupoD()  + "\"" +";");
                   }
            }else{
               out.println("form." + ObjetoDes + ".length=1;");
               out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Datos no disponibles" + "\"" + ";");
               out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
            }

        break;
        
        case 9:
           // Discapacidad
           TVGRLDISCAPACIDAD vGRLDISCAPACIDAD2= new TVGRLDISCAPACIDAD();
           TDGRLDISCAPACIDAD dGRLDISCAPACIDAD2 = new TDGRLDISCAPACIDAD();
           Vector vcGRLDISCAPACIDAD2= new Vector();
           vcGRLDISCAPACIDAD2 = dGRLDISCAPACIDAD2.FindByAllD(request.getParameter("val1").toString(),request.getParameter("val2").toString());
           out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
           if (vcGRLDISCAPACIDAD2.size() > 0){
                   out.println("form." + ObjetoDes + ".length=1;");
                   out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
                   out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
                   for (int i = 0; i < vcGRLDISCAPACIDAD2.size(); i++){
                      j = j + 1;
                      vGRLDISCAPACIDAD2 = (TVGRLDISCAPACIDAD) vcGRLDISCAPACIDAD2.get(i);
                      out.println("i=form." + ObjetoDes + ".length + 1;");
                      out.println("form." + ObjetoDes + ".length=i;");
                      out.println("form." + ObjetoDes + "[" + j  + "].text=" + "\"" + vGRLDISCAPACIDAD2.getCDscDiscapacidad()  + "\"" +";");
                      out.println("form." + ObjetoDes + "[" + j + "].value="+ "\"" + vGRLDISCAPACIDAD2.getICveDiscapacidad() + "\"" +";");
                   }
            }else{
               out.println("form." + ObjetoDes + ".length=1;");
               out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Datos no disponibles" + "\"" + ";");
               out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
            }
         break;
         
         case 10:
           // Discapacidad ? SUBGRUPO
           TVGRLRELIGION vGRLRELIGION = new TVGRLRELIGION();
           TDGRLRELIGION dGRLRELIGION = new TDGRLRELIGION();
           Vector vcGRLRELIGION = new Vector();
           vcGRLRELIGION = dGRLRELIGION.FindByAllS(" where iCveGrupo = " + request.getParameter("val1").toString());
           out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
           if (vcGRLRELIGION.size() > 0){
                   out.println("form." + ObjetoDes + ".length=1;");
                   out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
                   out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
                   for (int i = 0; i < vcGRLRELIGION.size(); i++){
                      j = j + 1;
                      vGRLRELIGION = (TVGRLRELIGION) vcGRLRELIGION.get(i);
                      out.println("i=form." + ObjetoDes + ".length + 1;");
                      out.println("form." + ObjetoDes + ".length=i;");
                      out.println("form." + ObjetoDes + "[" + j  + "].text=" + "\"" + vGRLRELIGION.getCSubGrupo()  + "\"" +";");
                      out.println("form." + ObjetoDes + "[" + j + "].value="+ "\"" + vGRLRELIGION.getICveSubGrupo()  + "\"" +";");
                   }
            }else{
               out.println("form." + ObjetoDes + ".length=1;");
               out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Datos no disponibles" + "\"" + ";");
               out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
            }

        break;
        
        case 11:
           // Discapacidad
           TVGRLRELIGION vGRLRELIGION2= new TVGRLRELIGION();
           TDGRLRELIGION dGRLRELIGION2 = new TDGRLRELIGION();
           Vector vcGRLRELIGION2= new Vector();
           vcGRLRELIGION2 = dGRLRELIGION2.FindByAllD(request.getParameter("val1").toString(),request.getParameter("val2").toString());
           out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
           if (vcGRLRELIGION2.size() > 0){
                   out.println("form." + ObjetoDes + ".length=1;");
                   out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
                   out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
                   for (int i = 0; i < vcGRLRELIGION2.size(); i++){
                      j = j + 1;
                      vGRLRELIGION2 = (TVGRLRELIGION) vcGRLRELIGION2.get(i);
                      out.println("i=form." + ObjetoDes + ".length + 1;");
                      out.println("form." + ObjetoDes + ".length=i;");
                      out.println("form." + ObjetoDes + "[" + j  + "].text=" + "\"" + vGRLRELIGION2.getCCodDsc()  + "\"" +";");
                      out.println("form." + ObjetoDes + "[" + j + "].value="+ "\"" + vGRLRELIGION2.getICveReligion() + "\"" +";");
                   }
            }else{
               out.println("form." + ObjetoDes + ".length=1;");
               out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Datos no disponibles" + "\"" + ";");
               out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
            }

     }
  }
  out.print("</script>");
%>

<head>
  <title><%= vEntorno.getTituloVentana() /* Obtiene el t�tulo de la ventana de los par�metros y lo muestra */ %></title>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
</head>
<% new TCabeceras(pageContext).TCabeceras(vEntorno, vParametros); /* Agrega caracter�sticas generales de las p�ginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<script language="JavaScript">
  window.defaultStatus = "<%= vParametros.getPropEspecifica("BarraEdo") %>";
</script>
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdEstado" value="">
  <input type="hidden" name="iCvePersonal">
  <input type="hidden" name="hdTitulo" value="<%if((""+request.getParameter("hdTitulo")).compareTo("null") != 0) out.print(request.getParameter("hdTitulo"));%>">
  <table border="0" width="100%" height="100%" cellspacing="0" cellpadding="0" background=" <%= cRutaImg %>fondoSTitulo<%= cTipoImg %>">
    <tr>
      <td align="center" width="100%" valign="middle">
        <TABLE BORDER="0" CELLSPACING="0" CELLPADDING="0" ALIGN="CENTER" WIDTH="100%"  class="ETablaST">
          <TR>
            <TD class="ETituloTPag" align="left">&nbsp;&nbsp;&nbsp;<%=cTitulo.toUpperCase()%>
            </TD>
          </TR>
        </TABLE>
      </td>
    </tr>
  </table>
</form>
</body>
<script>
//setTimeout("insertaImagenes()",50);
descArch();
</script>
<%= vErrores.muestraError() /* Al final de la p�gina se despliegan errores si existen */ %>
<%=	new TDefPrecargar(vEntorno.getListaImgs()).getResultado() /* Define funciones y llamado de precarga de imagenes */ %>
<% vEntorno.clearListaImgs(); %>
</html>
