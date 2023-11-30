package gob.sct.medprev.cntmgr;

import com.micper.ingsw.TParametro;
import gob.sct.documentos.*;

public class CM_GetContent {

	public CM_GetContent() {

	}

	TParametro vParametros = new TParametro("7");

	public byte[] connect(String[] keys, String[] values, String[] operators)
			throws Exception {
		byte[] attachment = null;
		boolean lError = false;
		try {
			DigitalizarDocumentoServiceLocator wsSerConSL = new DigitalizarDocumentoServiceLocator();
			DigitalizarDocumento wsSerCon = wsSerConSL
					.getDigitalizarDocumentoSoapPort(new java.net.URL(
							vParametros.getPropEspecifica("CM_WS").toString()));

			
			System.out.println(vParametros.getPropEspecifica("CM_WS").toString());
			
			attachment = wsSerCon.obtener(
					vParametros.getPropEspecifica("CM_Usuario").toString(),
					vParametros.getPropEspecifica("CM_Password").toString(),
					vParametros.getPropEspecifica("CM_Base").toString().trim(),
					keys, values, operators);

			return attachment;

		} catch (Exception ex) {
			ex.printStackTrace();
			lError = true;
		} finally {
			if (lError == true) {
				throw new Exception("Content Manager Error");
			}
		}
		return null;
	}
}