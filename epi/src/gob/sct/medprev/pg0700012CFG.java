package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.*;

public class pg0700012CFG extends CFGListBase2 {
	protected boolean AccesoValido2 = true;

	public pg0700012CFG() {
		this.vParametros = new TParametro("07");
	}

	public void Guardar() {
		TDPermisos dPermisos = new TDPermisos();
		TVUsuario vUsuario = (TVUsuario) httpReq.getSession(true).getAttribute(
				"UsrID");
		boolean lSuccess = true;
		try {
			if (dPermisos.accesoUsuario("" + request.getParameter("hdUsuario"),
					"" + request.getParameter("cContrasenia"), "07") != null) {
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
					"La contrase�a se ha cambiado con Exito! ");
			/*
			 * if(dPermisos.accesoUsuario("xx" , "xxx", "07") != null) {
			 * lSuccess = false; } else { AccesoValido = false; }
			 */
			AccesoValido2 = false;
		}
	}

	/**
	 * Metodo encargado de enviar el valor que indica si el usuario accedi� al
	 * programa de manera correcta.
	 * 
	 * @return Valor de la propiedad.
	 */
	public boolean getAccesoValido2() {
		return AccesoValido2;
	}

}
