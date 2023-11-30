package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object GRLReportes
 * </p>
 * <p>
 * Description: VO de la entidad GRLReportes
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Jaime Enrique Suárez Romero
 * @version 1.0
 */
public class TVGRLReportes implements HashBeanInterface {
	private BeanPK pk;
	private String cNombre;
	private String cQuery;
	private String cTitulo;
	private String cCampos;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add(cNombre);
		return pk;
	}

	public TVGRLReportes() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("cNombre", cNombre);
		hmfieldsTable.put("cQuery", cQuery);
		hmfieldsTable.put("cTitulo", cTitulo);
		hmfieldsTable.put("cCampos", cCampos);
		return hmfieldsTable;
	}

	public String getCNombre() {
		return cNombre;
	}

	public void setCNombre(String cNombre) {
		this.cNombre = cNombre;
	}

	public String getCQuery() {
		return cQuery;
	}

	public void setCQuery(String cQuery) {
		this.cQuery = cQuery;
	}

	public String getCTitulo() {
		return cTitulo;
	}

	public void setCTitulo(String cTitulo) {
		this.cTitulo = cTitulo;
	}

	public String getCCampos() {
		return cCampos;
	}

	public void setCCampos(String cCampos) {
		this.cCampos = cCampos;
	}
}
