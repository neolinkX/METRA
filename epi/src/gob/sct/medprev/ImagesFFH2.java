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

public class ImagesFFH2 implements java.io.Serializable {
	private ArrayList data;
	private Integer numExpediente;
	private Integer idDoctoFoto;
	private String nombreCompleto;

	public ImagesFFH2() {
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
			data.clear();

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

			cSQL.append("select 0,inodocto from licffh where tscaptura = (select max(tscaptura) FROM LICFFH WHERE iCvePersonal = ? AND ICVETIPOFFH = 1 ) ");
			cSQL.append("union ");
			cSQL.append("select 1,inodocto from licffh where tscaptura = (select max(tscaptura) FROM LICFFH WHERE iCvePersonal = ? AND ICVETIPOFFH = 2 ) ");
			cSQL.append("union ");
			// cSQL.append("select 2,inodocto from (Select inodocto FROM LICFFH WHERE iCvePersonal = ? AND ICVETIPOFFH = 3 order by (SUBSTR(CHAR(tsCaptura),1,10)) DESC,  iDedo ASC FETCH FIRST 1 ROW ONLY) h ");
			cSQL.append("select 2,inodocto from (Select inodocto FROM LICFFH WHERE iCvePersonal = ? AND ICVETIPOFFH = 3 and idedo = 2 order by tsCaptura DESC,  iDedo ASC FETCH FIRST 1 ROW ONLY) h  ");

			java.sql.PreparedStatement pstmt = conn.prepareStatement(cSQL
					.toString());
			pstmt.setInt(1, numExpediente.intValue());
			pstmt.setInt(2, numExpediente.intValue());
			pstmt.setInt(3, numExpediente.intValue());
			java.sql.ResultSet rset = pstmt.executeQuery();
			int contador = 0;
			for (int i = 0; rset.next() && i < inodoctos.length; i++) {
				inodoctos[rset.getInt(1)] = rset.getInt(2);
				if (rset.getInt(1) == 0)
					contador = contador + 1;
				if (rset.getInt(1) == 1)
					contador = contador + 3;
				if (rset.getInt(1) == 2)
					contador = contador + 5;
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

			// recuperar foto, firma y huella desde el content manager de la sct
			byte[] btFoto = null;
			byte[] btArchivo = null;
			byte[] btFirma = null;
			byte[] btHuella = null;

			TParametro vParametros = new TParametro("7");
			String[] values = {
					vParametros.getPropEspecifica("CM_Usuario").toString(),
					vParametros.getPropEspecifica("CM_Password").toString(),
					vParametros.getPropEspecifica("CM_Base").toString().trim(),
					"1", "true", null };
			// Cambio de usuarios content 11 de noviembre 2010 AG SA
			// String[] values =
			// {"icmadmin","lscmv82","eLicDoc","1","true",null};
			// String[] values =
			// {"db2cmod1","mod1cmv82","eLicDoc","1","true",null};

			// recuperar la foto
			if (inodoctos[0] > 0) {
				setIdDoctoFoto(inodoctos[0]);
				values[5] = Integer.toString(inodoctos[0]);
				btFoto = cmImport.connect(keys, values, operators);
				// System.out.print("FOTYO "+btFoto.length);
				if (btFoto != null && btFoto.length > 0)
					data.add(0, btFoto);
				else
					data.add(0, null);
			}

			// recuperar la firma
			if (inodoctos[1] > 0) {
				values[5] = Integer.toString(inodoctos[1]);
				btArchivo = cmImport.connect(keys, values, operators);
				// System.out.print("huella "+btArchivo.length);
				if (btArchivo != null) {
					ZipDecompressor DES = new ZipDecompressor();
					btFirma = DES.decompress(btArchivo, ".bmp");
					/* dictamen.setFirma(btFirma); */
					if (btFirma == null || btFirma.length == 0) {
						btFirma = DES.decompress(btArchivo, ".jpg");
					}
					btArchivo = null;
				}
				if (btFirma != null && btFirma.length > 0)
					data.add(1, btFirma);
				else
					data.add(1, null);
			}

			// recuperar la huella
			if (inodoctos[2] > 0) {
				values[5] = Integer.toString(inodoctos[2]);
				btArchivo = cmImport.connect(keys, values, operators);
				if (btArchivo != null) {
					ZipDecompressor DES = new ZipDecompressor();
					btHuella = DES.decompress(btArchivo, ".bmp");
					if (btHuella == null || btHuella.length == 0) {
						btHuella = DES.decompress(btArchivo, ".jpg");
					}
				}
				if (btHuella != null && btHuella.length > 0)
					data.add(1, btHuella);
				else
					data.add(1, null);
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
}
