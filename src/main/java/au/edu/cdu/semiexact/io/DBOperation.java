package au.edu.cdu.semiexact.io;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sqlite.Function;

import au.edu.cdu.semiexact.util.ConstantValue;

public class DBOperation {

	private Connection getConnection() throws Exception {
		String clzName = "org.sqlite.JDBC";
		String dbName = "jdbc:sqlite:result/db/semi-exact-result.db";
		return getConnection(clzName, dbName);
	}

	private Connection getConnection(String clzName, String dbName) throws Exception {

		Connection c = null;

		Class.forName(clzName);
		c = DriverManager.getConnection(dbName);

		return c;
	}

	/**
	 * execute query by sql statement
	 * 
	 * @param dbp,
	 *            db operation parameters
	 * @return query result map
	 * @throws Exception
	 */
	@SuppressWarnings("finally")
	public List<Map<String, String>> executeQuery(DBParameter dbp) {
		Connection c = null;
		Statement stmt = null;
		ResultSet rs = null;
		Map<String, String> map = null;
		List<Map<String, String>> lst = null;

		try {
			c = getConnection();
			c.setAutoCommit(false);
			stmt = c.createStatement();
			StringBuffer sqlSB = new StringBuffer();
			String[] colNames = dbp.getColNames();
			String[] colPairNames = dbp.getColPairNames();
			String[] colPairOperators = dbp.getColPairOperators();
			String[] colPairValues = dbp.getColPairValues();

			sqlSB.append("select ");
			for (String colName : colNames) {
				sqlSB.append("\"").append(colName).append("\",");
			}
			sqlSB.delete(sqlSB.length() - 1, sqlSB.length());

			sqlSB.append(" from ").append("\"").append(dbp.getTableName()).append("\"");
			;
			sqlSB.append(" where 1=1 ");

			for (int i = 0; i < colPairNames.length; i++) {
				String colPairName = colPairNames[i];
				String colPairOperator = colPairOperators[i];
				String colPairValue = colPairValues[i];
				sqlSB.append(" and \"").append(colPairName).append("\" ").append(colPairOperator).append(" \"")
						.append(colPairValue).append("\" ");
			}
			sqlSB.append(";");

			rs = stmt.executeQuery(sqlSB.toString());
			int colNamesLen = colNames.length;

			lst = new ArrayList<Map<String, String>>();

			while (rs.next()) {
				map = new HashMap<String, String>(colNamesLen);
				for (int i = 0; i < colNamesLen; i++) {
					String colName = colNames[i];
					String colVal = rs.getString(i + 1);
					map.put(colName, colVal);
				}
				lst.add(map);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				return lst;
			}

		}
	}

	/**
	 * insert one record into database table
	 * 
	 * @param dbp,
	 *            database operation parameters
	 * @return inserted record count
	 */
	@SuppressWarnings("finally")
	public int executeInsert(DBParameter dbp) {
		Connection c = null;
		Statement stmt = null;
		int rtn = ConstantValue.IMPOSSIBLE_VALUE;

		try {

			c = getConnection();

			c.setAutoCommit(false);
			stmt = c.createStatement();
			StringBuffer sqlSB = new StringBuffer();
			sqlSB.append("insert into ").append(dbp.getTableName()).append(" (");
			String[] colNames = dbp.getColPairNames();
			String[] colValues = dbp.getColPairValues();

			for (String colName : colNames) {
				sqlSB.append("\"").append(colName).append("\",");
			}

			sqlSB.delete(sqlSB.length() - 1, sqlSB.length());

			sqlSB.append(") values (");

			for (String colVal : colValues) {
				sqlSB.append("\"").append(colVal).append("\",");
			}
			sqlSB.delete(sqlSB.length() - 1, sqlSB.length());

			sqlSB.append(");");

			rtn = stmt.executeUpdate(sqlSB.toString());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				c.commit();
				c.close();
			} catch (Exception e) {

				e.printStackTrace();
			} finally {
				return rtn;
			}

		}

	}

	

}
