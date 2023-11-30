package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.util.TNumeros;

/**
 * <p>
 * Title: Value Object TVTOXLoteCuantDetalle
 * </p>
 * <p>
 * Description: Lotes Cuantitativos
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author LSC. Itzia Ma. del C. Sánchez Méndez
 * @version 1.0
 */
public class TVTOXAnalisisCuant {

	public TVTOXAnalisis VAnalisis;
	public TVMuestra VMuestra;
	public TVTOXCuantAnalisis VCAnal;

	public TVTOXAnalisisCuant() {
		VAnalisis = new TVTOXAnalisis();
		VMuestra = new TVMuestra();
		VCAnal = new TVTOXCuantAnalisis();
	}
}
