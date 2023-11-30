package gob.sct.medprev;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import com.micper.seguridad.vo.*;
import com.micper.ingsw.*;

//import com.micper.seguridad.vo.*;

public class pg0700001CFG {
	TParametro vParametros;
	TVUsuario vUsuario;

	public pg0700001CFG() {
	}

	public void runConfig(TEntorno vEntorno, Vector vBtnPrin, Vector vUrlPrin,
			Vector vEstPrin, TParametro vParams) {
		// Se asignan valores a los diferentes elementos del entorno general
		vParametros = vParams;
		vEntorno.setTituloVentana("Sistema Integral de la DGPMPT - Acceso");
		vEntorno.setArchAyuda("0700001");
		vEntorno.setArchFuncs("FValida");
		vEntorno.setArchTooltips("07_Tooltips");
		vEntorno.clearListaImgs();
		vEntorno.setMetodoForm("POST");
		vEntorno.setActionForm("pg0700001.jsp");
		vEntorno.setOnLoad("");
		vEntorno.setUrlLogo("Acerca");
		vEntorno.setBtnPrincVisible(false); // Botones principales visibles o no
	}

	public void outputHeader(TEntorno vEntorno, TError vErrores,
			PageContext pc, HttpServletRequest httpReq,
			HttpServletResponse httpResp) throws IOException, ServletException {
		JspWriter out = pc.getOut();
		ServletRequest request = pc.getRequest();

		if (httpReq.getMethod().toUpperCase().compareTo("GET") == 0) {
			vEntorno.setBtnPrincVisible(false);
		}
		if (httpReq.getMethod().toUpperCase().compareTo("POST") == 0) {
			vEntorno.setBtnPrincVisible(true);
		}
	}
}