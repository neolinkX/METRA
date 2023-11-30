package com.micper.util;

import java.io.*;
import java.util.zip.*;

public class ZipDecompressor {

	public static final int BUFFER_SIZE = 2048;

	/**
	 * Metodo utilizado para obtener un archivo almacenado en un zip.
	 * 
	 * @param filename
	 *            byte[] Arreglo de bytes del Archivo que deber� ser analizado y
	 *            descompactado.
	 * @return byte[] Archivo enviado.
	 */
	public byte[] decompress(byte[] filename, String cTipoArchivo) {
		ByteArrayOutputStream fos = new ByteArrayOutputStream();
		try {
			ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(
					filename));
			int count;
			byte data[] = new byte[BUFFER_SIZE];
			ZipEntry entry;
			while ((entry = zis.getNextEntry()) != null) {
				if (!entry.isDirectory()) {
					String entryName = entry.getName();
					String cExtension = entryName.substring(entryName
							.indexOf("."));
					if (cTipoArchivo.equalsIgnoreCase(cExtension)) {
						// leer el archivo
						while ((count = zis.read(data, 0, BUFFER_SIZE)) != -1) {
							fos.write(data, 0, count);
						}
						fos.flush();
						fos.close();
						break;
					}
				}
			} // While entradas del zip
			zis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// enviar el archivo encontrado
		return fos.toByteArray();
	}
}
