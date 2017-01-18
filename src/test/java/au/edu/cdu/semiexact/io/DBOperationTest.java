package au.edu.cdu.semiexact.io;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import au.edu.cdu.semiexact.util.ConstantValue;

public class DBOperationTest {

	@Test
	public void testExecuteQuery() throws Exception {
		DBParameter dbp = new DBParameter();
		dbp.setTableName(ConstantValue.DB_TBNAME_INS);
		String[] colNames = { ConstantValue.DB_COL_ID, ConstantValue.DB_COL_INS_NAME,
				ConstantValue.DB_COL_INS_PATH_NAME, ConstantValue.DB_COL_V_COUNT, ConstantValue.DB_COL_E_COUNT };
		String[] colPairNames = { ConstantValue.DB_COL_INS_NAME, ConstantValue.DB_COL_INS_PATH_NAME };
		String[] colPairOperators = { "=", "=" };
		String[] colPairValues = { "cc", "cc.clq" };
		dbp.setColNames(colNames);
		dbp.setColPairNames(colPairNames);
		dbp.setColPairOperators(colPairOperators);
		dbp.setColPairValues(colPairValues);

		DBOperation dbo = new DBOperation();
		List<Map<String, String>> lst = dbo.executeQuery(dbp);
		Assert.assertNotNull(lst);
	}

	@Test
	public void testExecuteQuery2() throws Exception {
		DBParameter dbp = new DBParameter();
		dbp.setTableName(ConstantValue.DB_TBNAME_INS);
		String[] colNames = { ConstantValue.DB_COL_ID, ConstantValue.DB_COL_INS_NAME,
				ConstantValue.DB_COL_INS_PATH_NAME, ConstantValue.DB_COL_V_COUNT, ConstantValue.DB_COL_E_COUNT };
		String[] colPairNames = { ConstantValue.DB_COL_INS_NAME };

		String[] colPairOperators = { "like" };
		String[] colPairValues = { "%bro%" };
		dbp.setColNames(colNames);
		dbp.setColPairNames(colPairNames);
		dbp.setColPairOperators(colPairOperators);
		dbp.setColPairValues(colPairValues);

		DBOperation dbo = new DBOperation();
		List<Map<String, String>> lst = dbo.executeQuery(dbp);
		Assert.assertNotNull(lst);
	}

	@Test
	public void testExecuteQuery3() throws Exception {
		DBParameter dbp = new DBParameter();
		dbp.setTableName(ConstantValue.DB_TBNAME_INS);
		String[] colNames = { ConstantValue.DB_COL_ID, ConstantValue.DB_COL_INS_NAME,
				ConstantValue.DB_COL_INS_PATH_NAME, ConstantValue.DB_COL_V_COUNT, ConstantValue.DB_COL_E_COUNT };
		String[] colPairNames = { ConstantValue.DB_COL_V_COUNT };

		String[] colPairOperators = { "<" };
		String[] colPairValues = { "300" };
		dbp.setColNames(colNames);
		dbp.setColPairNames(colPairNames);
		dbp.setColPairOperators(colPairOperators);
		dbp.setColPairValues(colPairValues);

		DBOperation dbo = new DBOperation();
		List<Map<String, String>> lst = dbo.executeQuery(dbp);
		Assert.assertNotNull(lst);
	}

	//@Ignore
	@Test
	public void testExecuteInsert() throws Exception {
		DBParameter dbp = new DBParameter();
		dbp.setTableName(ConstantValue.DB_TBNAME_INS);
		String[] colNames = {   ConstantValue.DB_COL_INS_NAME,
				ConstantValue.DB_COL_INS_PATH_NAME  };
		 
		String[] colValues = { "cc", "cc.clq" };
		dbp.setColPairNames(colNames);
		dbp.setColPairValues(colValues);

		DBOperation dbo = new DBOperation();
		dbo.executeInsert(dbp);

	}
}
