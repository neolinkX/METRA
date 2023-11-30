package gob.sct.medprev.util;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;

public class Dictamenes {

	private List dictamenes = new ArrayList();

	public void add(Dictamen dictamen) {
		dictamenes.add(dictamen);
	}

	public byte[] getReportePDF() throws Exception {
		List jasperPrinterList = new ArrayList();
		for (Iterator iter = dictamenes.iterator(); iter.hasNext();) {
			Dictamen dictamen = (Dictamen) iter.next();
			jasperPrinterList.add(dictamen.getJasperPrint());
		}

		JRPdfExporter exporter = new JRPdfExporter();

		ByteArrayOutputStream output = new ByteArrayOutputStream();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST,
				jasperPrinterList);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, output);
		exporter.setParameter(
				JRPdfExporterParameter.IS_CREATING_BATCH_MODE_BOOKMARKS,
				Boolean.TRUE);
		// exporter.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING,
		// "UTF-8");
		exporter.exportReport();
		byte[] byteArray = output.toByteArray();
		output.close();
		return byteArray;
	}
}
