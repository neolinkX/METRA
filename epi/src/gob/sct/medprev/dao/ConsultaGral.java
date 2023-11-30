/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.sct.medprev.dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 
 * @author Iv
 */
public class ConsultaGral {

	public ArrayList ejecutaSelectQuery(String SQLQuery) {
		Seg seg = new Seg();
		Statement statement = seg.AbrirBD();
		ArrayList resultadoQuery = new ArrayList();
		// System.out.println("ejecuntando query general desde la clase ConsultaGral");
		try {
			ResultSet resultSet = statement.executeQuery(SQLQuery);
			ResultSetMetaData metadata = resultSet.getMetaData();
			while (resultSet.next()) {
				String[] filaInformacion = new String[metadata.getColumnCount()];
				for (int i = 0; i < metadata.getColumnCount(); i++) {
					filaInformacion[i] = resultSet.getString(i + 1);
				}
				resultadoQuery.add(filaInformacion);
			}
		} catch (SQLException a) {
			// System.out.println("\n***** Hubo un Problema al Ejectuar el comando \n"
			// + SQLQuery + "\n*****************");
			a.printStackTrace();
		}
		seg.CerrarBD();
		return resultadoQuery;
	}

	public ArrayList ejecutaSelectQueryAdmSeg(String SQLQuery) {
		Seg seg = new Seg();
		Statement statement = seg.AbrirBDAdm();
		ArrayList resultadoQuery = new ArrayList();
		// System.out.println("ejecuntando query general desde la clase ConsultaGral");
		try {
			ResultSet resultSet = statement.executeQuery(SQLQuery);
			ResultSetMetaData metadata = resultSet.getMetaData();
			while (resultSet.next()) {
				String[] filaInformacion = new String[metadata.getColumnCount()];
				for (int i = 0; i < metadata.getColumnCount(); i++) {
					filaInformacion[i] = resultSet.getString(i + 1);
				}
				resultadoQuery.add(filaInformacion);
			}
		} catch (SQLException a) {
			// System.out.println("\n***** Hubo un Problema al Ejectuar el comando \n"
			// + SQLQuery + "\n*****************");
			a.printStackTrace();
		}
		seg.CerrarBD();
		return resultadoQuery;
	}

	public int ejecutaInsert(String SQLQuery) {
		Seg seg = new Seg();
		Statement statement = seg.AbrirBDAdm();
		int resultadoQuery = -1;
		try {
			resultadoQuery = statement.executeUpdate(SQLQuery);
		} catch (SQLException a) {
			a.printStackTrace();
			// System.out.println("Error al insertar el registro");
		}
		seg.CerrarBD();
		return resultadoQuery;
	}
}
