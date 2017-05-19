package au.edu.cdu.semiexact;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Assert;

import au.edu.cdu.semiexact.algo.AlgorithmParameter;
import au.edu.cdu.semiexact.algo.msc.IMSC;
import au.edu.cdu.semiexact.io.DBOperation;
import au.edu.cdu.semiexact.io.DBParameter;
import au.edu.cdu.semiexact.io.FileOperation;
import au.edu.cdu.semiexact.util.ConstantValue;
import au.edu.cdu.semiexact.util.LogUtil;
import au.edu.cdu.semiexact.util.MISGlobalVariable;
import au.edu.cdu.semiexact.util.MSCGlobalVariable;
import au.edu.cdu.semiexact.util.Util;

/**
 * an util class for test purpose
 */
public class TestUtil {
	private static Logger log = LogUtil.getLogger(TestUtil.class);
	private static final int IV = ConstantValue.IMPOSSIBLE_VALUE;
	public static final String FUNCTION_SEP = "***********************************************************";

	public static String getCurrentPath() {
		return Paths.get(".").toAbsolutePath().normalize().toString();
	}


	public static List<List<Integer>> getTestCase1ForBasicMSC() {
		int[] l1 = { 1, 2, 3 };
		int[] l2 = { 1, 2, 4 };
		int[] l3 = { 1, 3, 4 };
		int[] l4 = { 2, 3, 4, 5, 6, 7 };
		int[] l5 = { 4, 5 };
		int[] l6 = { 4, 6 };
		int[] l7 = { 4, 7 };

		List<Integer> list1 = Util.arrayToList(l1);
		List<Integer> list2 = Util.arrayToList(l2);
		List<Integer> list3 = Util.arrayToList(l3);
		List<Integer> list4 = Util.arrayToList(l4);
		List<Integer> list5 = Util.arrayToList(l5);
		List<Integer> list6 = Util.arrayToList(l6);
		List<Integer> list7 = Util.arrayToList(l7);

		List<List<Integer>> list = new ArrayList<List<Integer>>();
		list.add(list1);
		list.add(list2);
		list.add(list3);
		list.add(list4);
		list.add(list5);
		list.add(list6);
		list.add(list7);
		return list;
	}

	public static Map<Integer, List<Integer>> getTestCase1ForBasicMSCMap() {

		int[] l1 = { 1, 2, 3 };
		int[] l2 = { 1, 2, 4 };
		int[] l3 = { 1, 3, 4 };
		int[] l4 = { 2, 3, 4, 5, 6, 7 };
		int[] l5 = { 4, 5 };
		int[] l6 = { 4, 6 };
		int[] l7 = { 4, 7 };

		List<Integer> list1 = Util.arrayToList(l1);
		List<Integer> list2 = Util.arrayToList(l2);
		List<Integer> list3 = Util.arrayToList(l3);
		List<Integer> list4 = Util.arrayToList(l4);
		List<Integer> list5 = Util.arrayToList(l5);
		List<Integer> list6 = Util.arrayToList(l6);
		List<Integer> list7 = Util.arrayToList(l7);

		Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
		map.put(1, list1);
		map.put(2, list2);
		map.put(3, list3);
		map.put(4, list4);
		map.put(5, list5);
		map.put(6, list6);
		map.put(7, list7);
		return map;
	}

	private static MSCGlobalVariable<String, String> setMSCGlobalVariable(int eCount, int[] eL, int[] eIL, int[] freq,
			int[][] eAL, int[][] eIM, int sCount, int[] sL, int[] sIL, int[] card, int[][] sAL, int[][] sIM) {
		MSCGlobalVariable<String, String> gv = new MSCGlobalVariable<String, String>();
		gv.setCard(card);
		gv.seteAL(eAL);
		gv.seteIL(eIL);
		gv.seteIM(eIM);
		gv.setFreq(freq);
		gv.setsAL(sAL);
		gv.setsIL(sIL);
		gv.setsIM(sIM);
		gv.seteCount(eCount);
		gv.setsCount(sCount);
		gv.setsL(sL);
		gv.seteL(eL);

		gv.setBestSolCount(sCount);
		gv.setSolCount(0);

		int[] mate = new int[eCount + 1];

		for (int i = 0; i <= eCount; i++) {
			mate[i] = ConstantValue.MATE_EXPOSE;
		}
		gv.setMate(mate);

		int[] sol = new int[sCount];
		int[] bestSol = new int[sCount];
		for (int i = 0; i < sCount; i++) {
			sol[i] = ConstantValue.IMPOSSIBLE_VALUE;
			bestSol[i] = ConstantValue.IMPOSSIBLE_VALUE;
		}
		gv.setSol(sol);
		gv.setBestSol(bestSol);
		gv.setSolPtr(1);

		return gv;

	}
	
	private static MISGlobalVariable<Integer> setMISGlobalVariable(int vCount, int[] vL, int[] vIL, int[] deg,
			int[][] vAL, int[][] vIM) {
		MISGlobalVariable<Integer> gv = new MISGlobalVariable<Integer>();
		gv.setDeg(deg);
		gv.setvAL(vAL);
		gv.setvIL(vIL);
		gv.setvL(vL);
		gv.setvIM(vIM);
		gv.setvCount(vCount);

		gv.setBestSolCount(vCount);
		gv.setSolCount(0);

		int[] sol = new int[vCount];
		int[] bestSol = new int[vCount];
		for (int i = 0; i < vCount; i++) {
			sol[i] = ConstantValue.IMPOSSIBLE_VALUE;
			bestSol[i] = ConstantValue.IMPOSSIBLE_VALUE;
		}
		gv.setSol(sol);
		gv.setBestSol(bestSol);
		gv.setSolPtr(1);

		return gv;

	}

	public static MSCGlobalVariable<String, String> getTC1RepFile() throws IOException {
		String filePath = TestUtil.getCurrentPath() + "/src/test/resources/sample1.txt";

		MSCGlobalVariable<String, String> gv = new FileOperation().readGraphForMSCByEdgePair(filePath);
		return gv;

	}

	
	public static MISGlobalVariable<Integer> getMISTC1Rep() {
		int vCount = 6;

		int[] vL = { IV, 1, 2, 3, 4, 5, 6 };
		int[] vIL = { IV, 1, 2, 3, 4, 5, 6 };
		int[] deg = { vCount, 2, 2, 2, 3, 2, 1 };
		int[][] vAL = { {}, { IV, 2, 3 }, { IV, 1, 4 }, { IV, 1, 4 }, { IV, 2, 3, 5 }, { IV, 4, 6 },
				{ IV, 5 } };
		int[][] vIM ={{IV, IV, IV, IV, IV, IV, IV}, {IV, IV, 1, 1, IV, IV, IV}, {IV, 1, IV, IV, 1, IV, IV}, {IV, 2, IV, IV, 2, IV, IV}, {IV, IV, 2, 2, IV, 1, IV}, {IV, IV, IV, IV, 3, IV, 1}, {IV, IV, IV, IV, IV, 2, IV}};

		 
		return setMISGlobalVariable(vCount, vL, vIL, deg, vAL, vIM);

	}
	
	public static MSCGlobalVariable<String, String> getMSCTC1Rep() {
		int eCount = 6;

		int[] eL = { IV, 1, 2, 3, 4, 5, 6 };
		int[] eIL = { IV, 1, 2, 3, 4, 5, 6 };
		int[] freq = { eCount, 3, 3, 3, 4, 3, 2 };
		int[][] eAL = { {}, { IV, 1, 2, 3 }, { IV, 1, 2, 4 }, { IV, 1, 3, 4 }, { IV, 2, 3, 4, 5 }, { IV, 4, 5, 6 },
				{ IV, 5, 6 } };
		int[][] eIM = { { IV, IV, IV, IV, IV, IV, IV, }, { IV, 1, 1, 1, IV, IV, IV }, { IV, 2, 2, IV, 1, IV, IV },
				{ IV, 3, IV, 2, 2, IV, IV }, { IV, IV, 3, 3, 3, 1, IV }, { IV, IV, IV, IV, 4, 2, 1 },
				{ IV, IV, IV, IV, IV, 3, 2 } };

		int sCount = 6;

		int[] sL = { IV, 1, 2, 3, 4, 5, 6 };
		int[] sIL = { IV, 1, 2, 3, 4, 5, 6 };
		int[] card = { sCount, 3, 3, 3, 4, 3, 2 };
		int[][] sAL = { {}, { IV, 1, 2, 3 }, { IV, 1, 2, 4 }, { IV, 1, 3, 4 }, { IV, 2, 3, 4, 5 }, { IV, 4, 5, 6 },
				{ IV, 5, 6 } };
		int[][] sIM = { { IV, IV, IV, IV, IV, IV, IV, }, { IV, 1, 1, 1, IV, IV, IV }, { IV, 2, 2, IV, 1, IV, IV },
				{ IV, 3, IV, 2, 2, IV, IV }, { IV, IV, 3, 3, 3, 1, IV }, { IV, IV, IV, IV, 4, 2, 1 },
				{ IV, IV, IV, IV, IV, 3, 2 } };
		return setMSCGlobalVariable(eCount, eL, eIL, freq, eAL, eIM, sCount, sL, sIL, card, sAL, sIM);

	}

	public static MSCGlobalVariable<String, String> getTC2Rep() {
		int eCount = 7;
		int[] eL = { IV, 1, 2, 3, 4, 5, 6, 7 };
		int[] eIL = { IV, 1, 2, 3, 4, 5, 6, 7 };
		int[] freq = { eCount, 3, 3, 2, 2, 1, 2, 1 };
		int[][] eAL = { {}, { IV, 1, 2, 3 }, { IV, 1, 4, 5 }, { IV, 2, 4 }, { IV, 3, 5 }, { IV, 6 }, { IV, 6, 7 },
				{ IV, 7 } };

		int[][] eIM = { { IV, IV, IV, IV, IV, IV, IV, IV, }, { IV, 1, 1, IV, IV, IV, IV, IV },
				{ IV, 2, IV, 1, IV, IV, IV, IV }, { IV, 3, IV, IV, 2, IV, IV, IV }, { IV, IV, 2, 2, IV, IV, IV, IV },
				{ IV, IV, 3, IV, 2, IV, IV, IV }, { IV, IV, IV, IV, IV, 1, 1, IV }, { IV, IV, IV, IV, IV, IV, 2, 1 } };

		int sCount = 7;
		int[] sL = { IV, 1, 2, 3, 4, 5, 6, 7 };
		int[] sIL = { IV, 1, 2, 3, 4, 5, 6, 7 };
		int[] card = { sCount, 2, 2, 2, 2, 2, 2, 2 };
		int[][] sAL = { {}, { IV, 1, 2 }, { IV, 1, 3 }, { IV, 1, 4 }, { IV, 2, 3 }, { IV, 2, 4 }, { IV, 5, 6 },
				{ IV, 6, 7 } };

		int[][] sIM = { { IV, IV, IV, IV, IV, IV, IV, IV }, { IV, 1, 1, 1, IV, IV, IV, IV },
				{ IV, 2, IV, IV, 1, 1, IV, IV }, { IV, IV, 2, IV, 2, IV, IV, IV }, { IV, IV, IV, 2, IV, 2, IV, IV },
				{ IV, IV, IV, IV, IV, IV, 1, IV }, { IV, IV, IV, IV, IV, IV, 2, 1 },
				{ IV, IV, IV, IV, IV, IV, IV, 2 } };

		return setMSCGlobalVariable(eCount, eL, eIL, freq, eAL, eIM, sCount, sL, sIL, card, sAL, sIM);
	}

	public static Map<Integer, List<Integer>> getTestCase1ForMaxMatching() {
		Map<Integer, List<Integer>> g = new HashMap<Integer, List<Integer>>();
		List<Integer> l0 = new ArrayList<Integer>();
		l0.add(1);
		l0.add(2);
		l0.add(3);
		g.put(0, l0);

		List<Integer> l1 = new ArrayList<Integer>();
		l1.add(0);
		l1.add(2);
		l1.add(3);
		g.put(1, l1);

		List<Integer> l2 = new ArrayList<Integer>();
		l2.add(0);
		l2.add(1);
		g.put(2, l2);

		List<Integer> l3 = new ArrayList<Integer>();
		l3.add(0);
		l3.add(1);
		g.put(3, l3);

		List<Integer> l4 = new ArrayList<Integer>();
		l4.add(5);
		g.put(4, l4);

		List<Integer> l5 = new ArrayList<Integer>();
		l5.add(4);
		l5.add(6);
		g.put(5, l5);

		List<Integer> l6 = new ArrayList<Integer>();
		l6.add(5);
		g.put(6, l6);
		return g;
	}

	public static Map<Integer, List<Integer>> getTestCase2ForMaxMatching() {
		Map<Integer, List<Integer>> g = new HashMap<Integer, List<Integer>>();
		List<Integer> l0 = new ArrayList<Integer>();
		l0.add(1);

		g.put(0, l0);

		List<Integer> l1 = new ArrayList<Integer>();
		l1.add(0);
		l1.add(2);
		l1.add(3);
		g.put(1, l1);

		List<Integer> l2 = new ArrayList<Integer>();
		l2.add(1);
		l2.add(5);
		g.put(2, l2);

		List<Integer> l3 = new ArrayList<Integer>();
		l3.add(1);
		l3.add(4);
		l3.add(5);
		g.put(3, l3);

		List<Integer> l4 = new ArrayList<Integer>();
		l4.add(3);
		l4.add(5);
		g.put(4, l4);

		List<Integer> l5 = new ArrayList<Integer>();
		l5.add(2);
		l5.add(3);
		l5.add(4);
		g.put(5, l5);

		return g;
	}

	/**
	 * print status of global variables in format for MSC
	 * 
	 * @param gv,
	 *            global variables
	 */
	public static void printMISGlobalVariableStatus(MISGlobalVariable<Integer> gv) {
		String styleStr = "%-6s %-6s %-6s %-20s %-20s";

		int[] vIL = gv.getvIL();
		int vLen = vIL.length - 1;
		int[] deg = gv.getDeg();
		int[][] vAL = gv.getvAL();
		int[][] vIM = gv.getvIM();
		int vActCount = gv.getvCount();
		int[] vL = gv.getvL();

		printStatus(styleStr, vLen, "vActCount", vActCount, "vL", vL, "vIL", vIL, "deg", deg, "vAL", vAL, "vIM", vIM);

		System.out.println("--------------------------------------------------------");
	}
	/**
	 * print status of global variables in format for MSC
	 * 
	 * @param gv,
	 *            global variables
	 */
	public static void printMSCGlobalVariableStatus(MSCGlobalVariable<String, String> gv) {
		String styleStr = "%-6s %-6s %-6s %-20s %-20s";

		int[] sIL = gv.getsIL();
		int sLen = sIL.length - 1;
		int[] card = gv.getCard();
		int[][] sAL = gv.getsAL();
		int[][] sIM = gv.getsIM();
		int sActCount = gv.getsCount();
		int[] sL = gv.getsL();

		printStatus(styleStr, sLen, "sActCount", sActCount, "sL", sL, "sIL", sIL, "card", card, "sAL", sAL, "sIM", sIM);

		System.out.println();
		int[] eIL = gv.geteIL();
		int eLen = eIL.length - 1;
		int[] freq = gv.getFreq();
		int[][] eAL = gv.geteAL();
		int[][] eIM = gv.geteIM();
		int eActCount = gv.geteCount();
		int[] eL = gv.geteL();

		printStatus(styleStr, eLen, "eActCount", eActCount, "eL", eL, "eIL", eIL, "freq", freq, "eAL", eAL, "eIM", eIM);
		System.out.println("--------------------------------------------------------");
	}

	private static void printStatus(String styleStr, int len, String actCountName, int actCount, String lName, int[] l,
			String ilName, int[] il, String degName, int[] deg, String alName, int[][] al, String imName, int[][] im) {
		StringBuffer sb = new StringBuffer();

		System.out.println(actCountName + ":" + actCount);
		System.out.printf(styleStr, lName, ilName, degName, alName, imName);
		System.out.println();
		for (int i = 1; i <= len; i++) {
			sb.setLength(0);

			System.out.printf(styleStr, i + " " + l[i], i + " " + il[i], i + " " + deg[i],
					i + " " + arrayToString(al[i]), arrayToString(im[i]));
			System.out.println();
		}
	}

	private static String arrayToString(int[] array) {
		StringBuffer sb = new StringBuffer();
		int arrayLen = array.length;
		for (int i = 1; i < arrayLen; i++) {
			if (array[i] == ConstantValue.IMPOSSIBLE_VALUE) {
				sb.append("N").append(",");
			} else {
				sb.append(array[i]).append(",");
			}
		}
		return sb.substring(0, sb.length() - 1);
	}

	public static void basicTestLoop(String[] instanceCodes, IMSC<String,String> msc) throws IOException {
		for(String instanceCode:instanceCodes){
			String batchNum=Util.getBatchNum();
			basicTest(instanceCode, batchNum, msc);
		}
	}
	
	private static void basicTest(String instanceCode, String batchNum, IMSC<String, String> msc) throws IOException {
		String baseFilePath = TestUtil.getCurrentPath() + "/src/test/resources";

		DBOperation.createTable(instanceCode);

		DBParameter dbpIn = null;
		DBParameter dbpOut = null;

		dbpIn = getDBParamInput(instanceCode);

		String algTableName = ConstantValue.TBL_ALG_PREFIX + instanceCode;

		List<Map<String, String>> lst = DBOperation.executeQuery(dbpIn);
		int lstLen = lst.size();
		for (int i = 0; i < lstLen; i++) {
			Map<String, String> map = lst.get(i); 

			AlgorithmParameter ap = getAP(map);

			String id = map.get(ConstantValue.DB_COL_INS_ID);

			int thresholdUpper = ap.getBestResultSize() + 1;
			for (int threshold = thresholdUpper; threshold > 0; threshold--) {
				MSCGlobalVariable<String, String> gv = getGV(baseFilePath, map);
				ap.setThreshold(threshold);

				long start = System.nanoTime();
				msc.branch(gv, ap);
				long end = System.nanoTime();

				Assert.assertTrue(Util.isValidMSCSolution(gv));

				dbpOut = getDBParamOutPut(algTableName, id, gv, start, end, batchNum, threshold);
				DBOperation.executeInsert(dbpOut);

				dbpOut = null;

				StringBuffer sb = new StringBuffer();
				sb.append(instanceCode).append(":").append(gv.getBestSolCount()).append(":")
						.append(String.format("%.3f", ((end - start) / 1000000000.0))).append(" s.");
				log.debug(sb.toString());

			}

		}
		dbpIn = null;
	}

	public static void basicTest(String[] instanceCodes, String algTableName, IMSC<String, String> msc)
			throws IOException {
		String baseFilePath = TestUtil.getCurrentPath() + "/src/test/resources";
		String batchNum = Util.getBatchNum();

		//DBOperation dbo = new DBOperation();
		DBParameter dbpIn = null;
		DBParameter dbpOut = null;

		for (String instanceCode : instanceCodes) {

			dbpIn = getDBParamInput(instanceCode);

			List<Map<String, String>> lst = DBOperation.executeQuery(dbpIn);
			int lstLen = lst.size();
			for (int i = 0; i < lstLen; i++) {
				Map<String, String> map = lst.get(i);
				MSCGlobalVariable<String, String> gv = getGV(baseFilePath, map);

				AlgorithmParameter ap = getAP(map);

				String id = map.get(ConstantValue.DB_COL_INS_ID);

				long start = System.nanoTime();
				msc.branch(gv, ap);
				long end = System.nanoTime();

				Assert.assertTrue(Util.isValidMSCSolution(gv));

				dbpOut = getDBParamOutPut(algTableName, batchNum, id, gv, start, end);
				DBOperation.executeInsert(dbpOut);

				dbpOut = null;

				StringBuffer sb = new StringBuffer();
				sb.append(instanceCode).append(":").append(gv.getBestSolCount()).append(":")
						.append(String.format("%.3f", ((end - start) / 1000000000.0))).append(" s.");
				log.debug(sb.toString());

			}
			dbpIn = null;

		}
	}

	private static AlgorithmParameter getAP(Map<String, String> map) {
		String allowedRunningTimeStr = map.get(ConstantValue.DB_COL_ALLOWED_RUNNING_TIME);
		long allowedRunningTime = Long.parseLong(allowedRunningTimeStr);
		String bestResultSizeStr = map.get(ConstantValue.DB_COL_BEST_RESULT_SIZE);
		int bestResultSize = Integer.parseInt(bestResultSizeStr);
		String acceptedResultSizeStr = map.get(ConstantValue.DB_COL_ACCEPT_RESULT_SIZE);
		int acceptedResultSize = Integer.parseInt(acceptedResultSizeStr);
		String unacceptedResultSizeStr = map.get(ConstantValue.DB_COL_UNACCEPT_RESULT_SIZE);
		int unacceptedResultSize = Integer.parseInt(unacceptedResultSizeStr);
		// String thesholdStr = map.get(ConstantValue.DB_COL_THRESHOLD);
		// int theshold = Integer.parseInt(thesholdStr);

		AlgorithmParameter ap = new AlgorithmParameter();
		ap.setAcceptedResultSize(acceptedResultSize);
		ap.setUnacceptedResultSize(unacceptedResultSize);
		ap.setBestResultSize(bestResultSize);

		ap.setAllowedRunningTime(allowedRunningTime);
		// ap.setTheshold(theshold);
		return ap;
	}

	private static MSCGlobalVariable<String, String> getGV(String baseFilePath, Map<String, String> map)
			throws IOException {
		String iPathName = map.get(ConstantValue.DB_COL_INS_PATH_NAME);
		String dPathName = map.get(ConstantValue.DB_COL_DATASET_PATH_NAME);
		String filePath = baseFilePath + dPathName + iPathName;
		MSCGlobalVariable<String, String> gv = new FileOperation().readGraphForMSCByEdgePair(filePath);
		return gv;
	}

	private static DBParameter getDBParamOutPut(String algTableName, String id, MSCGlobalVariable<String, String> gv,
			long start, long end, String batchNum, int threshold) {
		DBParameter dbpOut;
		dbpOut = new DBParameter();
		dbpOut.setTableName(algTableName);
		String[] colPairNamesOut = { ConstantValue.DB_COL_INS_ID, ConstantValue.DB_COL_RESULT_SIZE,
				ConstantValue.DB_COL_RUNNING_TIME, ConstantValue.DB_COL_BATCH_NUM, ConstantValue.DB_COL_THRESHOLD, ConstantValue.DB_COL_MODEL };

		String[] colPairValuesOut = { id, Integer.toString(gv.getBestSolCount()), Long.toString((end - start)),batchNum,
				Integer.toString(threshold), gv.getModel() };
		dbpOut.setColPairNames(colPairNamesOut);
		dbpOut.setColPairValues(colPairValuesOut);
		return dbpOut;
	}

	private static DBParameter getDBParamOutPut(String algTableName, String batchNum, String id,
			MSCGlobalVariable<String, String> gv, long start, long end) {
		DBParameter dbpOut;
		dbpOut = new DBParameter();
		dbpOut.setTableName(algTableName);
		String[] colPairNamesOut = { ConstantValue.DB_COL_INS_ID, ConstantValue.DB_COL_BATCH_NUM,
				ConstantValue.DB_COL_RESULT_SIZE, ConstantValue.DB_COL_RUNNING_TIME };
		String[] colPairValuesOut = { id, batchNum, Integer.toString(gv.getBestSolCount()),
				Long.toString((end - start)) };
		dbpOut.setColPairNames(colPairNamesOut);
		dbpOut.setColPairValues(colPairValuesOut);
		return dbpOut;
	}

	private static DBParameter getDBParamInput(String instanceCode) {
		DBParameter dbpIn;
		dbpIn = new DBParameter();
		dbpIn.setTableName(ConstantValue.DB_VNAME_INS_OPT);
		String[] colNamesIn = { ConstantValue.DB_COL_INS_ID, ConstantValue.DB_COL_INS_NAME,
				ConstantValue.DB_COL_DATASET_PATH_NAME, ConstantValue.DB_COL_INS_PATH_NAME,
				ConstantValue.DB_COL_BEST_RESULT_SIZE, ConstantValue.DB_COL_ACCEPT_RESULT_SIZE,
				ConstantValue.DB_COL_UNACCEPT_RESULT_SIZE, ConstantValue.DB_COL_ALLOWED_RUNNING_TIME };
		String[] colPairNamesIn = { ConstantValue.DB_COL_INS_CODE };
		String[] colPairOperatorsIn = { "=" };
		String[] colPairValuesIn = { instanceCode };
		dbpIn.setColNames(colNamesIn);
		dbpIn.setColPairNames(colPairNamesIn);
		dbpIn.setColPairOperators(colPairOperatorsIn);
		dbpIn.setColPairValues(colPairValuesIn);
		return dbpIn;
	}

	// public static void baiscTestLoop(String[] codes, int[] thresholdUppers,
	// int[] thresholdSteps, int rounds,
	// String algTableName, IMSC<String, String> msc) throws IOException {
	//
	// int codesLen = codes.length;
	//
	// for (int round = 0; round<rounds; round++) {
	// String batchNum = Util.getBatchNum();
	// for (int j = 0; j < codesLen; j++) {
	// String code = codes[j];
	// int threshold = thresholdUppers[j] - round * thresholdSteps[j];
	// basicTest(code, threshold, batchNum, algTableName, msc);
	// }
	// }
	//
	// }
	//
	// public static void basicTest(String code, int threshold, String batchNum,
	// String algTableName,
	// IMSC<String, String> msc) throws IOException {
	// String baseFilePath = TestUtil.getCurrentPath() + "/src/test/resources";
	//
	// DBOperation dbo = new DBOperation();
	// DBParameter dbpIn;
	// DBParameter dbpOut;
	//
	// dbpIn = new DBParameter();
	// dbpIn.setTableName(ConstantValue.DB_VNAME_INS_OPT);
	// String[] colNamesIn = { ConstantValue.DB_COL_INS_ID,
	// ConstantValue.DB_COL_INS_NAME,
	// ConstantValue.DB_COL_DATASET_PATH_NAME,
	// ConstantValue.DB_COL_INS_PATH_NAME,
	// ConstantValue.DB_COL_BEST_RESULT_SIZE,
	// ConstantValue.DB_COL_ACCEPT_RESULT_SIZE,
	// ConstantValue.DB_COL_UNACCEPT_RESULT_SIZE,
	// ConstantValue.DB_COL_ALLOWED_RUNNING_TIME };
	// String[] colPairNamesIn = { ConstantValue.DB_COL_INS_CODE };
	// String[] colPairOperatorsIn = { "=" };
	// String[] colPairValuesIn = { code };
	// dbpIn.setColNames(colNamesIn);
	// dbpIn.setColPairNames(colPairNamesIn);
	// dbpIn.setColPairOperators(colPairOperatorsIn);
	// dbpIn.setColPairValues(colPairValuesIn);
	//
	// List<Map<String, String>> lst = dbo.executeQuery(dbpIn);
	// int lstLen = lst.size();
	// for (int i = 0; i < lstLen; i++) {
	// Map<String, String> map = lst.get(i);
	// String iPathName = map.get(ConstantValue.DB_COL_INS_PATH_NAME);
	// String dPathName = map.get(ConstantValue.DB_COL_DATASET_PATH_NAME);
	// String allowedRunningTimeStr =
	// map.get(ConstantValue.DB_COL_ALLOWED_RUNNING_TIME);
	// long allowedRunningTime = Long.parseLong(allowedRunningTimeStr);
	// String bestResultSizeStr =
	// map.get(ConstantValue.DB_COL_BEST_RESULT_SIZE);
	// int bestResultSize = Integer.parseInt(bestResultSizeStr);
	// String acceptedResultSizeStr =
	// map.get(ConstantValue.DB_COL_ACCEPT_RESULT_SIZE);
	// int acceptedResultSize = Integer.parseInt(acceptedResultSizeStr);
	// String unacceptedResultSizeStr =
	// map.get(ConstantValue.DB_COL_UNACCEPT_RESULT_SIZE);
	// int unacceptedResultSize = Integer.parseInt(unacceptedResultSizeStr);
	//
	// String id = map.get(ConstantValue.DB_COL_INS_ID);
	//
	// String filePath = baseFilePath + dPathName + iPathName;
	// GlobalVariable<String, String> gv = new
	// FileOperation().readGraphByEdgePair(filePath);
	// gv.setModel("Exact");
	//
	// AlgorithmParameter ap = new AlgorithmParameter();
	// ap.setAcceptedResultSize(acceptedResultSize);
	// ap.setUnacceptedResultSize(unacceptedResultSize);
	// ap.setBestResultSize(bestResultSize);
	//
	// ap.setAllowedRunningTime(allowedRunningTime);
	// ap.setTheshold(threshold);
	//
	// long start = System.nanoTime();
	// msc.branch(gv, ap);
	// long end = System.nanoTime();
	//
	// Assert.assertTrue(Util.isValidSolution(gv));
	//
	// dbpOut = new DBParameter();
	// dbpOut.setTableName(algTableName);
	// String[] colPairNamesOut = { ConstantValue.DB_COL_INS_ID,
	// ConstantValue.DB_COL_BATCH_NUM,
	// ConstantValue.DB_COL_RESULT_SIZE, ConstantValue.DB_COL_RUNNING_TIME,
	// ConstantValue.DB_COL_THRESHOLD, "model" };
	// String[] colPairValuesOut = { id, batchNum,
	// Integer.toString(gv.getBestSolCount()),
	// Long.toString((end - start)), Integer.toString(threshold), gv.getModel()
	// };
	// dbpOut.setColPairNames(colPairNamesOut);
	// dbpOut.setColPairValues(colPairValuesOut);
	// dbo.executeInsert(dbpOut);
	//
	// dbpOut = null;
	//
	// StringBuffer sb = new StringBuffer();
	// sb.append(iPathName).append(":").append(gv.getBestSolCount()).append(":")
	// .append(String.format("%.3f", ((end - start) / 1000000000.0))).append("
	// s.");
	// log.debug(sb.toString());
	//
	// dbpIn = null;
	//
	// }
	// }

}
