package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;
import com.micper.util.TNumeros;

/**
 * <p>
 * Title: Value Object TOXExamenCualita
 * </p>
 * <p>
 * Description: VO para la tabla TOXExamenCualita
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Itzia María del C. Sánchez Méndez
 * @version 1.0
 */

public class TVTOXAnalisisCtrol {
	public TVTOXCtrolCalibra VCtrol;
	public TVTOXExamenCualita VExamen;
	private int lControl;

	public TVTOXAnalisisCtrol() {
		VCtrol = new TVTOXCtrolCalibra();
		VExamen = new TVTOXExamenCualita();
	}

	public int getLControl() {
		return lControl;
	}

	public void setLControl(int lControl) {
		this.lControl = lControl;
	}
}
