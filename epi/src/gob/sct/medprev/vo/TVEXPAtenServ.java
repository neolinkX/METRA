package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object TVEXPAtenServ
 * </p>
 * <p>
 * Description: VO para TVEXPAtenServ
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Itzia Mar�a del Carmen S�nchez M�ndez
 * @version 1.0
 */
public class TVEXPAtenServ {

	public TVEXPServicio VServ;
	public TVEXPRama VRama;
	public TVGRLUsuario VMedico;
	public TVEXPExamAplica VExamAplica;

	public TVEXPAtenServ() {
		VServ = new TVEXPServicio();
		VRama = new TVEXPRama();
		VMedico = new TVGRLUsuario();
		VExamAplica = new TVEXPExamAplica();
	}

}
