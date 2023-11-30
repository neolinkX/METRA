package gob.sct.medprev.cntmgr.placas;

import java.io.*;
import java.util.*;
import com.micper.ingsw.TParametro;
import gob.sct.documentos.*;

public class CM_Import {

	TParametro vParametros = new TParametro("7");

	public CM_Import() {
	}

	public String connect(String[] keys, String[] values, byte[] bytes)
			throws Exception {

		//System.out.println("--" + values[2]);

		boolean lError = false;
		String cReturn = "";
		try {
			DigitalizarDocumentoServiceLocator wsSerConSL = new DigitalizarDocumentoServiceLocator();
			// System.out.println("URL   "+vParametros.getPropEspecifica("CM_WS").toString());
			DigitalizarDocumento wsSerCon = wsSerConSL
					.getDigitalizarDocumentoSoapPort(new java.net.URL(
							vParametros.getPropEspecifica("CM_WS").toString()));

			

			System.out.println(keys);
			System.out.println(values);
			System.out.println(bytes);
			
			cReturn = wsSerCon.asignar(
					vParametros.getPropEspecifica("CM_Usuario").toString(),
					vParametros.getPropEspecifica("CM_Password").toString(),
					vParametros.getPropEspecifica("CM_Base_Varios").toString()
							.trim(), keys, values, bytes);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			lError = true;
		} finally {
			if (lError == true) {
				throw new Exception("Content Manager Error");
			}
			return cReturn;
		}
	}
}
