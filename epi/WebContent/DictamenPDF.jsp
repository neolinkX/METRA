<%
        //System.out.println("Entrando en  jsp reportepdf");
	byte[] data = (byte[])request.getAttribute("REPORTE_PDF");
	//if(data==null)
	//	return;
	response.setContentType("application/pdf");
	response.setHeader("Content-Disposition", "inline;filename=Dictamen.pdf"); 
	response.setContentLength(data.length);
	
	javax.servlet.ServletOutputStream sos = response.getOutputStream();
	sos.flush(); 
	sos.write(data);
	sos.flush();
	sos.close();
  //      System.out.println("Finalizando en jsp reportepdf");
%>