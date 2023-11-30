package gob.sct.medprev;

import com.micper.ingsw.TParametro;
import gob.sct.medprev.cntmgr.*;
import com.micper.util.ZipDecompressor;
import gob.sct.medprev.vo.TVEXPDictamen;
import gob.sct.medprev.dao.TDEXPExamCat;

import java.util.ArrayList;
import java.util.Vector;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

public class ImagenesFFH implements java.io.Serializable {
	private ArrayList data;
	private Integer numExpediente;
	private Integer idDoctoFoto;
	private String FechaidDoctoFoto;
	private Integer idDoctoHuella;
	private String FechaidDoctoHuella;
	private Integer idDoctoFirma;
	private String FechaidDoctoFirma;
	private String nombreCompleto;

	public ImagenesFFH() {
		data = new ArrayList(3);
		numExpediente = new Integer(0);
		nombreCompleto = "";
	}

	public byte[] getData(int index) throws IndexOutOfBoundsException {
		return (byte[]) data.get(index);
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
				e.printStackTrace();
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
		try {
			data.clear();

			FechaidDoctoFoto = "";
			FechaidDoctoFirma = "";
			FechaidDoctoHuella ="";
			
			// recuperamos los inodocto para foto, firma y huella
			int[] inodoctos = new int[3];
			com.micper.ingsw.TParametro VParametros = new com.micper.ingsw.TParametro(
					"07");
			String dataSourceName = VParametros
					.getPropEspecifica("ConDBModulo");
			com.micper.sql.DbConnection dbConn = new com.micper.sql.DbConnection(
					dataSourceName);
			java.sql.Connection conn = dbConn.getConnection();

			StringBuffer cSQL = new StringBuffer();

			cSQL.append("select 0,inodocto, tscaptura from licffh where tscaptura = (select max(l.tscaptura) FROM LICFFH as l, perdatos as p WHERE l.iCvePersonal = p.iCvePersonal and l.iCvePersonal = ? AND l.ICVETIPOFFH = 1 ) ");
			cSQL.append("union ");
			cSQL.append("select 1,inodocto, tscaptura from licffh where tscaptura = (select max(l.tscaptura) FROM LICFFH as l, perdatos as p WHERE l.iCvePersonal = p.iCvePersonal and l.iCvePersonal = ? AND l.ICVETIPOFFH = 2 ) ");
			cSQL.append("union ");
			// cSQL.append("select 2,inodocto from (Select inodocto FROM LICFFH WHERE iCvePersonal = ? AND ICVETIPOFFH = 3 order by (SUBSTR(CHAR(tsCaptura),1,10)) DESC,  iDedo ASC FETCH FIRST 1 ROW ONLY) h ");
			cSQL.append("select 2,inodocto, tscaptura from (Select inodocto, tscaptura FROM LICFFH as l, perdatos as p WHERE l.iCvePersonal = p.iCvePersonal and l.iCvePersonal = ? AND l.ICVETIPOFFH = 3 and l.idedo = 2 order by l.tsCaptura DESC,  l.iDedo ASC FETCH FIRST 1 ROW ONLY) h    ");

			//System.out.println(cSQL);
			
			java.sql.PreparedStatement pstmt = conn.prepareStatement(cSQL
					.toString());
			pstmt.setInt(1, numExpediente.intValue());
			pstmt.setInt(2, numExpediente.intValue());
			pstmt.setInt(3, numExpediente.intValue());
			java.sql.ResultSet rset = pstmt.executeQuery();
			int contador = 0;
			for (int i = 0; rset.next() && i < inodoctos.length; i++) {
				inodoctos[rset.getInt(1)] = rset.getInt(2);
				if (rset.getInt(1) == 0){
						contador = contador + 1;
						FechaidDoctoFoto = rset.getString(3).substring(0, 19);
					}
				if (rset.getInt(1) == 1){
						contador = contador + 3;
						FechaidDoctoFirma = rset.getString(3).substring(0, 19);
					}
				if (rset.getInt(1) == 2){
						contador = contador + 5;
						FechaidDoctoHuella = rset.getString(3).substring(0, 19);
					}
				// System.out.println("inodocto = "+rset.getInt(1)+"  valor = "+rset.getInt(2));

			}

			// Identificar el inodocto vacio
			int muestimgZ = 3958400;
			int muestimgF = 3958401;
			if (contador == 0) {// no existen ningun biometrico
				inodoctos[0] = muestimgF;
				inodoctos[1] = muestimgZ;
				inodoctos[2] = muestimgZ;
			}
			if (contador == 1) {// solo existe la foto
				inodoctos[1] = muestimgZ;
				inodoctos[2] = muestimgZ;
			}
			if (contador == 3) {// solo existe la firma
				inodoctos[0] = muestimgF;
				inodoctos[2] = muestimgZ;
			}
			if (contador == 5) {// solo existe la huella
				inodoctos[0] = muestimgF;
				inodoctos[1] = muestimgZ;
			}
			if (contador == 4) {// solo existe foto y firma
				inodoctos[2] = muestimgZ;
			}
			if (contador == 6) {// solo existe foto y huella
				inodoctos[1] = muestimgZ;
			}
			if (contador == 8) {// solo existe firma y huella
				inodoctos[0] = muestimgF;
			}

			if (rset != null)
				rset.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
			dbConn.closeConnection();

			// recuperar la foto
			if (inodoctos[0] > 0) {
				setIdDoctoFoto(inodoctos[0]);
			}
			// recuperar la firma
			if (inodoctos[1] > 0) {
				setIdDoctoFirma(inodoctos[1]);
			}

			// recuperar la huella
			if (inodoctos[2] > 0) {
				setIdDoctoHuella(inodoctos[2]);
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
			
			//System.out.println(VResultado.size());
			
			if(VResultado.size() > 0){
			VDictamen = new TVEXPDictamen();
			VDictamen = (TVEXPDictamen) VResultado.lastElement();
			nombreCompleto = VDictamen.VPerDatos.getCNombre();
			}else{
				//System.out.println("El expediente no existe");
				nombreCompleto = "EL EXPEDIENTE " +numExpediente.toString()+" NO EXISTE";
			}
		} catch (com.micper.excepciones.DAOException e) {
		}
	}

	public void clearImages() {
		data.clear();
	}

	/**
	 * @return the idDoctoFoto
	 */
	public Integer getIdDoctoFoto() {
		return idDoctoFoto;
	}

	/**
	 * @param idDoctoFoto
	 *            the idDoctoFoto to set
	 */
	public void setIdDoctoFoto(Integer idDoctoFoto) {
		this.idDoctoFoto = idDoctoFoto;
	}

	/**
	 * @return the idDoctoHuella
	 */
	public Integer getIdDoctoHuella() {
		return idDoctoHuella;
	}

	/**
	 * @param idDoctoHuella
	 *            the idDoctoHuella to set
	 */
	public void setIdDoctoHuella(Integer idDoctoHuella) {
		this.idDoctoHuella = idDoctoHuella;
	}

	/**
	 * @return the idDoctoFirma
	 */
	public Integer getIdDoctoFirma() {
		return idDoctoFirma;
	}

	/**
	 * @param idDoctoFirma
	 *            the idDoctoFirma to set
	 */
	public void setIdDoctoFirma(Integer idDoctoFirma) {
		this.idDoctoFirma = idDoctoFirma;
	}
	
	/*** @return the FechaidDoctoFoto */
	public String getFechaIdDoctoFoto() {
		return FechaidDoctoFoto;
	}
	/*** @param idDoctoFoto* the idDoctoFoto to set	 */
	public void setIdDoctoFoto(String FechaidDoctoFoto) {
		this.FechaidDoctoFoto = FechaidDoctoFoto;
	}	
	
	/*** @return the FechaidDoctoHuella */
	public String getFechaIdDoctoHuella() {
		return FechaidDoctoHuella;
	}

	/*** @param idDoctoHuella* the idDoctoHuella to set	 */
	public void setIdDoctoHuella(String FechaidDoctoHuella) {
		this.FechaidDoctoHuella = FechaidDoctoHuella;
	}


	/*** @return the FechaidDoctoFirma */
	public String getFechaIdDoctoFirma() {
		return FechaidDoctoFirma;
	}
	/*** @param idDoctoFirma* the idDoctoFirma to set	 */
	public void setIdDoctoFirma(String FechaidDoctoFirma) {
		this.FechaidDoctoFirma = FechaidDoctoFirma;
	}
	
}
