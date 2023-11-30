package com.micper.ingsw;

import javax.servlet.http.*;

public class TSimpleCampo {
	StringBuffer sbRes = new StringBuffer();

	private void Etiqueta(String cEstiloE, String cEtiqueta) {
		sbRes.append("<td class=\"" + cEstiloE + "\">" + cEtiqueta + "</td>\n");
	}

	private void Campo(String cEstiloC, String cTipo, int iSize, int iMaxLen,
			String cName, String cValue, String cOnChange, String cOnBlur,
			boolean lSelectonFocus, boolean lActivo) {
		sbRes.append("<td class=\"" + cEstiloC + "\">\n");
		sbRes.append("<input type=\"" + cTipo + "\" size=\"" + iSize
				+ "\" maxlength=\"");
		sbRes.append(iMaxLen + "\" name=\"" + cName + "\"\n");
		sbRes.append("value=\"" + cValue + "\"\n");
		if (lActivo == false) {
			sbRes.append(" disabled");
		}
		if (lSelectonFocus == true) {
			sbRes.append(" onfocus=\"this.select();\"\n");
		}
		if (cOnChange.compareTo("") != 0) {
			sbRes.append(" onChange=\"" + cOnChange + "\"\n");
		}
		if (cOnBlur.compareTo("") != 0) {
			sbRes.append(" onBlur=\"" + cOnBlur + "\">");
		}
		sbRes.append("</td>\n");
	}

	public StringBuffer EtiCampo(String cEstiloE, String cEtiqueta,
			String cEstiloC, String cTipo, int iSize, int iMaxLen,
			String cName, String cValue, String cOnChange, String cOnBlur,
			boolean lSelectonFocus, boolean lActivo, boolean lCampo,
			HttpServletRequest request) {
		if (lCampo == true) {
			if (request.getParameter(cName) != null) {
				cValue = request.getParameter(cName);
			}
		}

		sbRes = new StringBuffer("");
		this.Etiqueta(cEstiloE, cEtiqueta);
		if (lCampo == true) {
			this.Campo(cEstiloC, cTipo, iSize, iMaxLen, cName, cValue,
					cOnChange, cOnBlur, lSelectonFocus, lActivo);
		} else {
			this.Etiqueta(cEstiloC, cValue);
		}
		return sbRes;
	}
}