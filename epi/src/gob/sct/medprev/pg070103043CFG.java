package gob.sct.medprev;

import java.util.*;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.*;
import com.micper.util.*;
import com.micper.excepciones.CFGException;

import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import gob.sct.medprev.util.DictamenApto;
import gob.sct.medprev.util.DictamenNoApto;
import gob.sct.medprev.util.Dictamen;
import gob.sct.medprev.util.Dictamenes;
import gob.sct.medprev.cntmgr.*;

import java.util.Calendar;
import java.util.Vector;

public class pg070103043CFG extends CFGListBase2 {

	public pg070103043CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}
}
