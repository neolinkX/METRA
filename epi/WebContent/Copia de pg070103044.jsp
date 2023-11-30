<%@page import="gob.sct.medprev.pg070103043CFG"%><%@page contentType="image/jpeg"%><%@page pageEncoding="iso-8859-1"%><jsp:useBean id="imgs" type="gob.sct.medprev.ImagesFFH" scope="session" beanName="gob.sct.medprev.ImagesFFH" /><%
    response.setHeader ("Pragma", "no-cache");
    response.setHeader ("Cache-Control", "no-cache");
    response.setDateHeader ("Expires",0); 

pg070103043CFG clsConfig = new pg070103043CFG();

if(clsConfig.getAccesoValido()){
//if(imgs != null){
    int index = -1;    
    
    try{
        index = Integer.parseInt((String)request.getParameter("index"));
    }catch(Exception e){
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
        //System.out.println("Error de IndexOutOfBoundsException cachado. El arreglo de imágenes no contiene la imagen para el índice " + index + ".\nRaíz: pg070103044.jsp\n No se muestra el detalle.\nMostramos una imagen de advertencia.");
        java.io.File file = new java.io.File("c:\\nodisp.jpg");
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