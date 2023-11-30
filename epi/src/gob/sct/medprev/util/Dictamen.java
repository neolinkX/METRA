package gob.sct.medprev.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;

import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

public class Dictamen {

	private static SimpleDateFormat FORMATO = new SimpleDateFormat("ddyyyyMM");
	private static SimpleDateFormat FORMATODay = new SimpleDateFormat("dd");
	protected Map parametros;
	protected JasperReport report;

	public static String generaCodigo(int numeroExpediente, Date fecha,
			int cveUsuario) {
		StringBuffer buffer = new StringBuffer();

		buffer.append(String.valueOf(numeroExpediente));
		buffer.append(FORMATO.format(fecha));
		buffer.append(String.valueOf(cveUsuario));
		return buffer.toString();

	}

	public static String generaCodigoNF(String HM, String MedDictaminador) {
		StringBuffer buffer = new StringBuffer();

		buffer.append(HM);
		buffer.append(MedDictaminador);
		return buffer.toString();
	}

	public byte[] getPDF() throws Exception {
		try {
			ArrayList reportList = new ArrayList();
			Map reportMap = new HashMap();
			reportMap.put("temp", "");
			reportList.add(reportMap);
			JRBeanArrayDataSource dataSourceReport = new JRBeanArrayDataSource(
					reportList.toArray());
			JasperPrint jasperPrint = JasperFillManager.fillReport(report,
					parametros, dataSourceReport);
			// JasperViewer.viewReport(jasperPrint);
			return JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (JRException e) {
			e.printStackTrace();
			throw new Exception("Error al generar el archivo", e);
		}
	}

	public JasperPrint getJasperPrint() throws Exception {
		try {
			ArrayList reportList = new ArrayList();
			Map reportMap = new HashMap();
			reportMap.put("temp", "");
			reportList.add(reportMap);
			JRBeanArrayDataSource dataSourceReport = new JRBeanArrayDataSource(
					reportList.toArray());
			return JasperFillManager.fillReport(report, parametros,
					dataSourceReport);
		} catch (JRException e) {
			e.printStackTrace();
			throw new Exception("Error al generar el archivo", e);
		}
	}

	/**
	 * @param nombre
	 *            The nombre to set.
	 */
	public void setNombre(String nombre) {
		if (nombre == null) {
			nombre = "";
		}
		parametros.put("nombre", nombre);
	}

	/**
	 * @param codigoBarras
	 *            The codigoBarras to set.
	 */
	public void setCodigo(String codigo) {
		InputStream stream = new ByteArrayInputStream(getBarCode(codigo));
		parametros.put("codigo", stream);
	}

	// Envia doble el codigo para emo
	public void setCodigo2(String codigo2) {
		InputStream stream = new ByteArrayInputStream(getBarCode(codigo2));
		parametros.put("codigo2", stream);
	}

	public void setFoto(byte[] foto) {
		InputStream is = new ByteArrayInputStream(foto);
		parametros.put("foto", is);
	}

	public void setFoto(java.io.FileInputStream foto) {
		InputStream is = foto;
		parametros.put("foto", is);
	}

	// foto para imprimirla doble en emo
	public void setFoto2(byte[] foto2) {
		InputStream is = new ByteArrayInputStream(foto2);
		parametros.put("foto2", is);
	}

	public void setFoto2(java.io.FileInputStream foto2) {
		InputStream is = foto2;
		parametros.put("foto2", is);
	}

	public void setHuella(byte[] huella) {
		InputStream is = new ByteArrayInputStream(huella);
		parametros.put("huella", is);
	}

	public void setHuella(java.io.FileInputStream huella) {
		InputStream is = huella;
		parametros.put("huella", is);
	}

	/******** secci�n para insertar el logo en el repote pdf del dictamen *********/
	public void setLogo(java.io.FileInputStream Logo) {
		InputStream is = Logo;
		parametros.put("logo", is);
	}

	public void setLogo(byte[] Logo) {
		InputStream is = new ByteArrayInputStream(Logo);
		parametros.put("logo", is);
	}

	public void setFirma(byte[] firma) {
		InputStream is = new ByteArrayInputStream(firma);
		parametros.put("firma", is);
	}

	public void setFirma(java.io.FileInputStream firma) {
		InputStream is = firma;
		parametros.put("firma", is);
	}

	/**
	 * @param dictaminador
	 *            The numCedula to set.
	 */
	public void setDictaminador(String dictaminador) {
		if (dictaminador == null) {
			dictaminador = "";
		}
		parametros.put("dictaminador", dictaminador);
	}

	protected static byte[] getBarCode(String code) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		Code39Bean bean = new Code39Bean();

		int dpi = 155;
		bean.setBarHeight(10);
		bean.setHeight(10);

		try {
			BitmapCanvasProvider canvas = new BitmapCanvasProvider(out,
					"image/jpeg", dpi, BufferedImage.TYPE_BYTE_BINARY, false);
			bean.generateBarcode(canvas, code);
			canvas.finish();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return out.toByteArray();
	}

}
