package gob.sct.medprev.vo;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */

public class TVTOXcLote {
	private Integer iCveAnalisis;
	private String cLote;

	public void setiCveAnalisis(Integer iCveAnalisis) {
		this.iCveAnalisis = iCveAnalisis;
	}

	public Integer getiCveAnalisis() {
		return iCveAnalisis;
	}

	public void setcLote(String cLote) {
		this.cLote = cLote;
	}

	public String getcLote() {
		return cLote;
	}
}
