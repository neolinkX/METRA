<%@page import="gob.sct.medprev.pg070103043CFG, com.micper.ingsw.TEntorno, com.micper.ingsw.TParametro,java.io.ByteArrayOutputStream"%>
<%@page contentType="image/jpeg"%>
<%@page pageEncoding="iso-8859-1"%>
<jsp:useBean id="imgs" type="gob.sct.medprev.ImagesFFH2" scope="session" beanName="gob.sct.medprev.ImagesFFH2" />
<jsp:setProperty name="imgs" property="numExpediente" param="numExpediente"/>
<%
    response.setHeader ("Pragma", "no-cache");
    response.setHeader ("Cache-Control", "no-cache");
    response.setDateHeader ("Expires",0); 

pg070103043CFG clsConfig = new pg070103043CFG();
  TEntorno       vEntorno  = new TEntorno();
  vEntorno.setNumModulo(07);
  
  TParametro vParametros = new TParametro(vEntorno.getNumModuloStr());
  String cRutaImg = vParametros.getPropEspecifica("RutaImg");
  
//if(clsConfig.getAccesoValido()){
if(imgs != null){
    int index = -1;    
    
    try{
        index = Integer.parseInt((String)request.getParameter("index"));
    }catch(Exception e){
        e.printStackTrace();
        //System.out.println("índice incorrecto.\nRaíz: pg070103043.jsp\nDetalle: " + e);
    }
    
    byte[] imgbytes = null;
    javax.servlet.ServletOutputStream o = response.getOutputStream();
    java.io.InputStream is = null;
    try{
        imgbytes = imgs.getData(index);
        is = new java.io.ByteArrayInputStream(imgbytes);
        int c;
        while((c=is.read())!=-1){
            o.write(c);
        }
        is.close();
        o.flush();
        o.close();
    }catch(IndexOutOfBoundsException e){
        //response.setContentType("text/html");
        //out.println("No hay Imagen:" + cRutaImg);
        //System.out.println("Error de IndexOutOfBoundsException cachado. El arreglo de imágenes no contiene la imagen para el índice " + index + ".\nRaíz: pg070103044.jsp\n No se muestra el detalle.\nMostramos una imagen de advertencia.");
        
//       Se comento la siguiente linea debido a que se debe modificar por el ambiente local y no del servidor linux
         //java.io.File file = new java.io.File("/usr/local/bea/user_projects/domains/nodisp.jpg");
         //java.io.File file = new java.io.File("E:/bea/user_projects/domains/nodisp.jpg");
         java.io.File file = new java.io.File("C:/sct/MedprevInt/medprev/Archivos/img/medprev/nodisp.jpg");
         is = new java.io.FileInputStream(file);
         int c;
         while((c=is.read())!=-1){
             o.write(c);
         }
         is.close();
         o.flush();
         o.close(); 
        
    }   
}else{%>
    <script type="text/javascript">alert("..Lo siento, no es posible desplegar la página. Raíz: pg070103044");</script>
<%}%>