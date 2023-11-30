package gob.sct.medprev.msc;

/*
 * QuerryManager.java
 *
 * Created on 31 de octubre de 2005, 06:11 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */
import com.micper.seguridad.vo.TVDinRep02;
import java.sql.*;
import java.util.*;
import com.micper.sql.*;
import com.micper.seguridad.vo.*;
import com.micper.ingsw.*;

/**
 * 
 * @author Javier Llamas Mondragón
 */

// Ver ValueObject
public class QueryManager {
	DbConnection dbconn;
	String dataSN;
	boolean debug = false;

	/** Creates a new innce of QuerryManager */

	public QueryManager(String module, String property) {
		TParametro VParametros = new TParametro(module);
		dataSN = VParametros.getPropEspecifica(property);
	}

	public QueryManager(String module, String property, boolean debug) {
		TParametro VParametros = new TParametro(module);
		dataSN = VParametros.getPropEspecifica(property);
		this.debug = debug;
	}

	/**
	 * public TVdinRep manageTransaction () { // por implementar
	 * System.out.println(" Por implementar"); }
	 */
	public ArrayList manageTransaction(ArrayList queryStructures) {
		ArrayList consultas = new ArrayList();
		ArrayList result = new ArrayList();
		Statement stmt = null;
		int[] resultados = null;
		boolean transactionComplete = true;
		Connection conn = null;
		//if (debug)
			//System.out.println("Terminando bloque inicial del metodo QueryManager.manageTransaction... ");

		try {
			dbconn = new DbConnection(dataSN);
			conn = dbconn.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			stmt.clearBatch();
			//if (debug)
				//System.out.println("\t Terminando el primer bloque del try...");

			// armar los queries y meterlos en el lote a ejecutar
			// si son consultas manejo aparte
			for (int i = 0; i < queryStructures.size(); i++) {
				QueryStructure tmp = (QueryStructure) queryStructures.get(i);
				if (tmp.getQueryType() == QueryStructure.SELECT) {
					//if (debug)
						//System.out.println("\tañadí consulta "+ generateQuery(tmp));
					String[] tmp2 = new String[2];
					tmp2[0] = (String) generateQuery(tmp);
					tmp2[1] = tmp.getKeysString();
					
					//System.out.println("-1  "+tmp2[0].toString());
					//System.out.println("-1  "+tmp2[1].toString());
					
					consultas.add(tmp2);
				} else {
					stmt.addBatch(generateQuery(tmp));
					//if (debug)
						//System.out.println("\tañadí " + generateQuery(tmp)+ "al lote");
				}
			}
			// si hay consultas
			if (consultas.size() > 0) {
				//if (debug)
					///System.out.println("\tHubieron consultas...");

				// ciclo para generar los Value Objects Dinamicos
				for (int i = 0; i < consultas.size(); i++) {
					String[] struct = (String[]) consultas.get(i);
					String query = struct[0];
					String llaves = struct[1];
					//System.out.println(query);
					ResultSet rst = stmt.executeQuery(query);
					//System.out.println(rst);
					//System.out.println(llaves);
					ArrayList tmp = generateValueObjectList(rst, llaves);
					
					result.add(tmp);
				}
				//if (debug)
					//System.out.println("\tTermine generacion de VO de selects");
			}
			// si se trata de INSERTS UPDATES y DELETES
			else {
				//if (debug)
					//System.out.println("\tHubieron modificaciones... ");
				resultados = stmt.executeBatch();
				//if (debug)
					//System.out.println(" ejecute el lote de actualizaciones");
				// validar el movimiento o echarlo para atras
			}
		} catch (BatchUpdateException buex) {
			//System.out.println("Error en la ejecucion de un query en la actualizacion en lotes en QueryManager.manageTransaction() "+ buex.getMessage());
			buex.printStackTrace();
			// transactionComplete = false;
			conn.rollback();
		} catch (SQLException sqle) {
			//System.out.println("Error de SQL en QueryManager.manageTransaction() "+ sqle.getMessage());
			sqle.printStackTrace();
			conn.rollback();
		} catch (Exception e) {
			//System.out.println("Error al traer la conexion de QueryManager.manageTransaction() "+ e.getMessage());
			e.printStackTrace();
			conn.rollback();
		} finally {
			try {
				// verificar que todos los queries hayan sido ejecutados
				// resultados aqui es nulo, validarlo
				if (debug)
					///System.out.println("verificando el arreglo de resultados (finally)... "+ resultados);

				if (consultas.size() == 0) {
					if (resultados == null)
						transactionComplete = false;
					else {
						for (int i = 0; i < resultados.length; i++) {
							if (debug)
								//System.out.println("verificando las sentencias de update, la "+ i+ " tiene un valor de "+ resultados[i]);
							if ((resultados[i] == Statement.EXECUTE_FAILED)
									|| (resultados[i] == Statement.SUCCESS_NO_INFO)) {
								if (debug)
									//System.out.println("el resultado del query "+ i	+ " fue erroneo hay q da roollback");
								transactionComplete = false;
							}
						}
					}
				}

				if (transactionComplete) {
					// System.out.println("sin errores, en commit");
					conn.commit();
				} else {
					// System.out.println("con errores, dando rollback");
					conn.rollback();
				}
				stmt.close();
				conn.close();
				dbconn.closeConnection();
			} catch (Exception ex) {
				System.out
						.println("Error finalizacion QueryManager.manageTransaction() "
								+ ex.getMessage());
			}
			return result;
		}
	}

	public ArrayList generateValueObjectList(ResultSet rst, String llaves) {

		ArrayList colTypes = new ArrayList();
		ArrayList resultados = new ArrayList();
		try {
			ResultSetMetaData rstmd = rst.getMetaData();
			// para cada registro recuperado
			while (rst.next()) {
				TVDinRep02 valueObject = new TVDinRep02();
				valueObject.setLlave(llaves);
				// para cada columna del registro
				for (int i = 1; i <= rstmd.getColumnCount(); i++) {
					int dataType = rstmd.getColumnType(i);
					switch (dataType) {
					case Types.INTEGER:
						valueObject.put(rstmd.getColumnName(i), rst.getInt(i));
						break;
					case Types.BIGINT:
						valueObject.put(rstmd.getColumnName(i), rst.getLong(i));
						break;
					case Types.REAL:
						valueObject
								.put(rstmd.getColumnName(i), rst.getFloat(i));
						break;
					case Types.FLOAT:
						valueObject.put(rstmd.getColumnName(i),
								rst.getDouble(i));
						break;
					case Types.DECIMAL:
						valueObject.put(rstmd.getColumnName(i),
								rst.getDouble(i));
						break;
					case Types.BIT:
						valueObject.put(rstmd.getColumnName(i),
								rst.getBoolean(i));
						break;
					case Types.VARCHAR:
						valueObject.put(rstmd.getColumnName(i),
								rst.getString(i));
						break;
					case Types.CHAR:
						valueObject.put(rstmd.getColumnName(i),
								rst.getString(i));
						break;
					case Types.DATE:
						valueObject.put(rstmd.getColumnName(i), rst.getDate(i));
						break;
					case Types.TIMESTAMP:
						valueObject.put(rstmd.getColumnName(i),
								rst.getString(i));
						// valueObject.put(rstmd.getColumnName(i),
						// rst.getTimestamp(i));
						// metodo modificado xq TVDinRep no tiene otros tipos
						// dados de alta
						break;
					case Types.TIME:
						valueObject.put(rstmd.getColumnName(i),
								rst.getString(i));
						// valueObject.put(rstmd.getColumnName(i),
						// rst.getTime(i));
						// metodo modificado xq TVDinRep no tiene otros tipos
						// dados de alta
						break;
					default:
						valueObject.put(rstmd.getColumnName(i),
								rst.getObject(i));
						break;
					}
				}
				resultados.add(valueObject);
			}
		} catch (SQLException sqle) {
			System.out
					.println("Error de sql en el metodo generateValueObject de QueryManager ");
			sqle.printStackTrace();
		}
		return resultados;
	}

	public String generateQuery(QueryStructure qStr) {
		StringBuffer queryTotal = new StringBuffer("");
		if (qStr.getQueryType() == QueryStructure.INSERT)
			queryTotal.append("INSERT INTO ").append(qStr.getTable())
					.append(" (").append(qStr.getAttributesString())
					.append(") VALUES (").append(qStr.getValuesString())
					.append(")");
		else if (qStr.getQueryType() == QueryStructure.DELETE) {
			queryTotal.append("DELETE FROM ").append(qStr.getTable());
			if (qStr.getClause().length() > 0)
				queryTotal.append(" WHERE ").append(qStr.getClause());
		} else if (qStr.getQueryType() == QueryStructure.UPDATE) {
			queryTotal.append("UPDATE ").append(qStr.getTable())
					.append(" SET ").append(qStr.getAttributeValueString());
			if (qStr.getClause().length() > 0)
				queryTotal.append(" WHERE ").append(qStr.getClause());
		} else if (qStr.getQueryType() == QueryStructure.SELECT) {
			queryTotal.append("SELECT ").append(qStr.getAttributesString())
					.append(" FROM ").append(qStr.getTable());
			if (qStr.getClause().length() > 0)
				queryTotal.append(" WHERE ").append(qStr.getClause());
		}
		return queryTotal.toString();
	}

	public static void main(String args[]) {
		HashMap b = new HashMap();
		b.put("d", "valor");
		b.put("e", "valor2");
		QueryStructure a = new QueryStructure(b, "dbadmin", " a=1",
				QueryStructure.INSERT);
		QueryStructure d = new QueryStructure(b, "amail", " a=1",
				QueryStructure.UPDATE);
		QueryStructure c = new QueryStructure(b, "final", " a=1",
				QueryStructure.SELECT);
		ArrayList prueba = new ArrayList();
		prueba.add(a);
		prueba.add(d);
		prueba.add(c);
		QueryManager qm = new QueryManager("10", args[0]);
	}
}
