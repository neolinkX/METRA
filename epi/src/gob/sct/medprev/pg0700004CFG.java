package gob.sct.medprev;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

import com.micper.ingsw.*;

public class pg0700004CFG {

	public pg0700004CFG() {
	}

	public void runConfig(TEntorno vEntorno) {
		vEntorno.setNumModulo(07);
		vEntorno.clearListaImgs();
		vEntorno.setOnLoad("");
		vEntorno.setNombrePagina("pg0700004.jsp");
	}

	public void outputHeader(TEntorno vEntorno, TError vErrores,
			PageContext pc, HttpServletRequest httpReq,
			HttpServletResponse httpResp, TParametro vParametros)
			throws IOException, ServletException {
		JspWriter out = pc.getOut();
		ServletRequest request = pc.getRequest();
		if (httpReq.getMethod().toUpperCase().compareTo("GET") == 0) {
		}
		if (httpReq.getMethod().toUpperCase().compareTo("POST") == 0) {
		}
	}
}