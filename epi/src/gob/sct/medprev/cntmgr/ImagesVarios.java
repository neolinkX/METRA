package gob.sct.medprev.cntmgr;

import com.micper.ingsw.TParametro;
import gob.sct.medprev.cntmgr.*;
import com.micper.util.ZipDecompressor;
import gob.sct.medprev.vo.TVEXPDictamen;
import gob.sct.medprev.dao.TDEXPExamCat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

public class ImagesVarios implements java.io.Serializable {
	private ArrayList bytesArchivos;
	private Integer numExpediente;
	private Integer numExamen;
	private Integer numServicio;
	private String nombreCompleto;

	public ImagesVarios() {
		bytesArchivos = new ArrayList();
		numExpediente = new Integer(0);
		nombreCompleto = "";
	}

	public byte[] getData(int index) throws IndexOutOfBoundsException {
		return (byte[]) bytesArchivos.get(index);
	}

	public int getNumExpediente() {
		return numExpediente.intValue();
	}

	public void setNumExpediente(int numExpediente) {
		this.numExpediente = new Integer(numExpediente);
		if (this.numExpediente.intValue() > 0) {
			try {
				getImages();
				setNombreCompleto();
			} catch (Exception e) {
				// System.out.println("No fue posible recuperar las im�genes.\nRa�z: gob.sct.medprev.ImagesFFH.\nDetalle: "
				// + e);
			}
		} else {
			clearImages();
			nombreCompleto = "";
		}
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	protected void getImages() throws Exception {
		String[] keys = { "userid", "password", "entity", "maxResults",
				"queryOP", "lintiCveDocumen" };
		String[] operators = { "", "", "", "", "", "=" };
		CM_GetContent cmImport = new CM_GetContent();

		// StringBuffer cFiltro = new StringBuffer();

		try {
			bytesArchivos.clear();

			// recuperamos los inodocto para foto, firma y huella
			ArrayList idDoctos = new ArrayList();
			com.micper.ingsw.TParametro VParametros = new com.micper.ingsw.TParametro(
					"07");
			String dataSourceName = VParametros
					.getPropEspecifica("ConDBModulo");
			com.micper.sql.DbConnection dbConn = new com.micper.sql.DbConnection(
					dataSourceName);
			java.sql.Connection conn = dbConn.getConnection();

			StringBuffer cSQL = new StringBuffer();

			cSQL.append("SELECT LINTICVEDOCUMEN FROM EXPSERVARCHCM "
					+ " WHERE ICVEEXPEDIENTE = ?" + " AND INUMEXAMEN = ?"
					+ " AND ICVESERVICIO = ?" +
					// " AND ICVERAMA = 1 ";
					" ORDER BY TSGENERADO");

			java.sql.PreparedStatement pstmt = conn.prepareStatement(cSQL
					.toString());
			pstmt.setInt(1, numExpediente.intValue());
			pstmt.setInt(2, getNumExamen().intValue());
			pstmt.setInt(3, getNumServicio().intValue());
			java.sql.ResultSet rset = pstmt.executeQuery();

			while (rset.next()) {
				idDoctos.add(rset.getString("LINTICVEDOCUMEN"));
			}

			if (rset != null)
				rset.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
			dbConn.closeConnection();

			TParametro vParametros = new TParametro("7");
			String[] values = {
					vParametros.getPropEspecifica("CM_Usuario").toString(),
					vParametros.getPropEspecifica("CM_Password").toString(),
					vParametros.getPropEspecifica("CM_Base_Varios").toString()
							.trim(), "1", "true", null };

			for (Iterator it = idDoctos.iterator(); it.hasNext();) {
				Object object = it.next();
				values[5] = (String) object;
				byte[] btArchivo = null;
				btArchivo = cmImport.connect(keys, values, operators);
				if (btArchivo != null && btArchivo.length > 0)
					bytesArchivos.add(0, btArchivo);
				else
					bytesArchivos.add(0, null);
			}
		} // try
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	protected void setNombreCompleto() {
		StringBuffer cFiltro = new StringBuffer();
		Vector VResultado = new Vector();
		TVEXPDictamen VDictamen;

		nombreCompleto = "";
		try {
			cFiltro.append("  where EC.iCveExpediente  = ").append(
					numExpediente.intValue());
			VResultado = (new TDEXPExamCat())
					.InfoConstancia(cFiltro.toString());

			VDictamen = new TVEXPDictamen();
			VDictamen = (TVEXPDictamen) VResultado.lastElement();
			nombreCompleto = VDictamen.VPerDatos.getCNombre();
		} catch (com.micper.excepciones.DAOException e) {
		}
	}

	public void clearImages() {
		bytesArchivos.clear();
	}

	/**
	 * @return the numExamen
	 */
	public Integer getNumExamen() {
		return numExamen;
	}

	/**
	 * @param numExamen
	 *            the numExamen to set
	 */
	public void setNumExamen(Integer numExamen) {
		this.numExamen = numExamen;
	}

	/**
	 * @return the numServicio
	 */
	public Integer getNumServicio() {
		return numServicio;
	}

	/**
	 * @param numServicio
	 *            the numServicio to set
	 */
	public void setNumServicio(Integer numServicio) {
		this.numServicio = numServicio;
	}
}
