package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object TOXEnvioXLote
 * </p>
 * <p>
 * Description: VO para TVTOXEnvioXLote
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Marco Antonio Hernández García
 * @version 1.0
 */
public class TVTOXEnvioXLote {

	private int iOrden;
	public TVTOXEnvio VEnvio;
	public TVLoteCualita VLote;

	public TVTOXEnvioXLote() {
		VEnvio = new TVTOXEnvio();
		VLote = new TVLoteCualita();
	}

	public int getIOrden() {
		return iOrden;
	}

	public void setIOrden(int iOrden) {
		this.iOrden = iOrden;
	}

}
