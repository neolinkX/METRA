package com.micper.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipCompress {

	public static final int BUFFER_SIZE = 8192;

	public void compress(List files, File zipFileName) {
		try {
			// Reference to the file we will be adding to the zipfile
			BufferedInputStream origin = null;

			// Reference to our zip file
			FileOutputStream dest = new FileOutputStream(zipFileName);

			// Wrap our destination zipfile with a ZipOutputStream
			ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(
					dest));

			// Create a byte[] buffer that we will read data from the source
			// files into and then transfer it to the zip file
			byte[] data = new byte[BUFFER_SIZE];

			// Iterate over all of the files in our list
			for (Iterator i = files.iterator(); i.hasNext();) {
				// Get a BufferedInputStream that we can use to read the source
				// file
				String filename = (String) i.next();
				System.out.println("Adding: " + filename);
				FileInputStream fi = new FileInputStream(filename);
				origin = new BufferedInputStream(fi, BUFFER_SIZE);

				// Setup the entry in the zip file
				ZipEntry entry = new ZipEntry(filename);
				out.putNextEntry(entry);

				// Read data from the source file and write it out to the zip
				// file
				int count;
				while ((count = origin.read(data, 0, BUFFER_SIZE)) != -1) {
					out.write(data, 0, count);
				}

				// Close the source file
				origin.close();
			}

			// Close the zip file
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ZipCompress zipCompress = new ZipCompress();

		// File f1 = new File("D:\\brito\\Java Zip\\Java Zip Compression.txt");
		// File f2 = new
		// File("D:\\brito\\Java Zip\\Java Zip Decompression.txt");
		// File f3 = new File("D:\\brito\\Java Zip\\Java Zip Viewing.txt");

		String f1 = "D:\\brito\\Java Zip\\Java Zip Compression.txt";
		String f2 = "D:\\brito\\Java Zip\\Java Zip Decompression.txt";
		String f3 = "D:\\brito\\Java Zip\\Java Zip Viewing.txt";

		List list = new ArrayList();
		list.add(f1);
		list.add(f2);
		list.add(f3);

		zipCompress.compress(list, new File("D:\\brito\\Java Zip\\rac.zip"));
	}
}
