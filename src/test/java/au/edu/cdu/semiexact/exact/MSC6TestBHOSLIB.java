package au.edu.cdu.semiexact.exact;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.junit.Test;

import au.edu.cdu.semiexact.TestUtil;
import au.edu.cdu.semiexact.io.DBOperation;
import au.edu.cdu.semiexact.io.DBParameter;
import au.edu.cdu.semiexact.io.FileOperation;
import au.edu.cdu.semiexact.util.ConstantValue;
import au.edu.cdu.semiexact.util.GlobalVariable;
import au.edu.cdu.semiexact.util.LogUtil;
import au.edu.cdu.semiexact.util.Util;

/**
 * a test case to test more complex instances
 */
public class MSC6TestBHOSLIB {
	private static Logger log = LogUtil.getLogger(MSC4Test.class);

	private void basicTest(String[] instanceNames, String algTableName) throws IOException {
		String baseFilePath = TestUtil.getCurrentPath() + "/src/test/resources";
		String batchNum = Util.getBatchNum();

		DBOperation dbo = new DBOperation();
		DBParameter dbpIn;
		DBParameter dbpOut;

		for (String instanceName : instanceNames) {
			// read instance information from db
			dbpIn = new DBParameter();
			dbpIn.setTableName(ConstantValue.DB_VNAME_INS);
			String[] colNamesIn = { ConstantValue.DB_COL_ID, ConstantValue.DB_COL_INS_NAME,
					ConstantValue.DB_COL_DATASET_PATH_NAME, ConstantValue.DB_COL_INS_PATH_NAME };
			String[] colPairNamesIn = { ConstantValue.DB_COL_INS_NAME };
			String[] colPairOperatorsIn = { "=" };
			String[] colPairValuesIn = { instanceName };
			dbpIn.setColNames(colNamesIn);
			dbpIn.setColPairNames(colPairNamesIn);
			dbpIn.setColPairOperators(colPairOperatorsIn);
			dbpIn.setColPairValues(colPairValuesIn);

			List<Map<String, String>> lst = dbo.executeQuery(dbpIn);
			int lstLen = lst.size();
			for (int i = 0; i < lstLen; i++) {
				Map<String, String> map = lst.get(i);
				String iPathName = map.get(ConstantValue.DB_COL_INS_PATH_NAME);
				String dPathName = map.get(ConstantValue.DB_COL_DATASET_PATH_NAME);

				String id = map.get(ConstantValue.DB_COL_ID);

				String filePath = baseFilePath + dPathName + iPathName;
				GlobalVariable<String, String> gv = new FileOperation().readGraphByEdgePair(filePath);
				MSC6<String, String> msc = new MSC6<String, String>();
				long start = System.nanoTime();
				msc.branch(gv);
				long end = System.nanoTime();

				// write result into db
				dbpOut = new DBParameter();
				dbpOut.setTableName(algTableName);
				String[] colPairNamesOut = { ConstantValue.DB_COL_INS_ID, ConstantValue.DB_COL_BATCH_NUM,
						ConstantValue.DB_COL_RESULT_COUNT, ConstantValue.DB_COL_RUNNING_TIME };
				String[] colPairValuesOut = { id, batchNum, Integer.toString(gv.getBestSolCount()),
						Long.toString((end - start)) };
				dbpOut.setColPairNames(colPairNamesOut);
				dbpOut.setColPairValues(colPairValuesOut);
				dbo.executeInsert(dbpOut);

				dbpOut = null;

				StringBuffer sb = new StringBuffer();
				sb.append(iPathName).append(":").append(gv.getBestSolCount()).append(":")
						.append((end - start) / 1000000000).append(" s.");
				log.debug(sb.toString());

			}
			dbpIn = null;

		}
	}

	// @Ignore
	@Test
	public void testBHOSLIB() throws IOException {
		String algTableName = ConstantValue.DB_TBNAME_ALG1;

		String[] instanceNames = { "frb30-15-1", "frb30-15-2",
				// "brock400_2", "brock400_4", "brock800_2",
				// "brock800_4",
		};

		basicTest(instanceNames, algTableName);

	}

}
