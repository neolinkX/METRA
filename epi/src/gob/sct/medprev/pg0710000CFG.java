package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

public class pg0710000CFG extends CFGListBase2 {

	public pg0710000CFG() {
		this.vParametros = new TParametro("07");
	}

	public void fillVector() {
		boolean lSuccess = false;
		if (cAccion.compareTo("Propiedad") == 0) {
			vParametros.reload(true);
			vErrores.acumulaError("", 0, "Se han actualizado las propiedades.");
		}
		if (cAccion.compareTo("Menu") == 0) {
			TDMenu.loadMenu("07");
			vErrores.acumulaError("", 0, "Se ha actualizado el menú.");
		}
		if (cAccion.compareTo("Catalogos") == 0) {
			try {
				lSuccess = (new TDSEGCatalogos()).update();
				if (lSuccess)
					vErrores.acumulaError("", 0,
							"Se han actualizado los catálogos generales del sistema.");
				else
					vErrores.acumulaError("", 0,
							"Error al actualizar los catálogos generales del sistema!");
			} catch (Exception e) {
				vErrores.acumulaError("", 0,
						"Error al actualizar los catálogos generales del sistema!");
			}
		}
		if (cAccion.compareTo("Importar") == 0) {
			try {
				lSuccess = (new TDSEGUsuario()).update("07");
				if (lSuccess)
					vErrores.acumulaError("", 0,
							"Se han actualizado los usuarios del sistema.");
				else
					vErrores.acumulaError("", 0,
							"Error al actualizar a los usuarios del sistema!");
			} catch (Exception e) {
				vErrores.acumulaError("", 0,
						"Error al actualizar a los usuarios del sistema!");
			}
		}
	}

}
