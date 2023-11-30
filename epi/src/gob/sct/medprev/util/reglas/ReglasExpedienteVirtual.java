package gob.sct.medprev.util.reglas;

import java.util.Vector;

import com.micper.excepciones.DAOException;
import com.micper.ingsw.TParametro;

import gob.sct.medprev.dao.TDEXPExamAplicaExt1;
import gob.sct.medprev.vo.TVEXPExamAplica;

public class ReglasExpedienteVirtual {

	private TParametro VParametros = new TParametro("07");

	/**
	 * Metodo Find By All
	 */
	@SuppressWarnings("static-access")
	public boolean AccesoAExpVirtual(int Usuario, int iCveExpediente) throws DAOException {
		//System.out.println("Reglas Expediente Virtual");
		boolean acceso = false;
		try {

			////// Validando si el usuario es Administrador //////////////
			String AdminArray[] = VParametros.getPropEspecifica("UsuariosAdmin").split(",");
			//System.out.println(AdminArray.length);
			//System.out.println(AdminArray[0]);
			for (int i = 0; i < AdminArray.length; i++) {
				if (this.isParsable(AdminArray[i].toString())) {
					//System.out.println("i=" + i + " / " + AdminArray[i]);
					if (Usuario == Integer.parseInt(AdminArray[i].toString())) {
						acceso = true;
						//System.out.println("Administrador");
					}
				}
			}

			//////// Validar si el Usuario si en su ultmo examen
			TDEXPExamAplicaExt1 dplica = new TDEXPExamAplicaExt1();
			TVEXPExamAplica vEXPExamAplica = new TVEXPExamAplica();
			@SuppressWarnings("rawtypes")
			Vector vcEXPExamAplica = new Vector();
			if (!acceso) {
				vcEXPExamAplica = dplica.FindByAllReglaExpVirtual(iCveExpediente, Usuario);
				if (vcEXPExamAplica.size() > 0) {
					vEXPExamAplica = (TVEXPExamAplica) vcEXPExamAplica.get(0);
					/// Valida el ultimo examen este sin concluir///////
					if (vEXPExamAplica.getLDictaminado() == 0) {
						acceso = true;
					} else {
						////// La fecha del dia de hoy es igual a la fecha de
						////// dictamen ///////
						//System.out.println("getDtAplicacion "+vEXPExamAplica.getDtAplicacion());
						//System.out.println("getDtDictamen "+vEXPExamAplica.getDtDictamen());
						if (vEXPExamAplica.getDtAplicacion().compareTo(vEXPExamAplica.getDtDictamen()) == 0) {
							acceso = true;
							//System.out.println("Fechas Iguales ");
						}
					}

					//System.out.println("Dictaminado = " + vEXPExamAplica.getLDictaminado());
				}
			}

		} catch (Exception ex) {
			throw new DAOException("AccesoAExpVirtual");
		}
		return acceso;

	}

	public static boolean isParsable(String input) {
		boolean parsable = true;
		try {
			Integer.parseInt(input);
		} catch (NumberFormatException e) {
			parsable = false;
		}
		return parsable;
	}

}
