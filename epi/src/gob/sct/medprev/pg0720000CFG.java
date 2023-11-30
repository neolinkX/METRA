package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.*;

public class pg0720000CFG extends CFGListBase2 {

	public pg0720000CFG() {
		this.vParametros = new TParametro("07");
	}

	public void Guardar() {
		TDPermisos dPermisos = new TDPermisos();
		boolean lSuccess = true;
		TVUsuario vUsuario = (TVUsuario) httpReq.getSession(true).getAttribute(
				"UsrID");
		try {
			if (dPermisos.accesoUsuario("" + request.getParameter("hdUsuario"),
					"" + request.getParameter("cContrasenia"), "07") != null) {
				// lSuccess = dPermisos.cambioContrasenia("" +
				// request.getParameter("hdUsuario"), "" +
				// request.getParameter("cNvaContrasenia"), "07");
				lSuccess = dPermisos.cambioContrasenia(
						"" + request.getParameter("hdUsuario"),
						"" + request.getParameter("cNvaContrasenia"), "07",
						vUsuario.getICveusuario());
			} else {
				AccesoValido = false;
			}
		} catch (Exception e) {
			lSuccess = false;
		}
		if (lSuccess == false) {
			if (AccesoValido) {
				vErrores.acumulaError("", 14, " de datos de seguridad!");
			}
		} else {
			vErrores.acumulaError("", 0,
					"La contraseña se ha cambiado con Éxito!");
		}
	}
}
