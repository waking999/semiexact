package au.edu.cdu.semiexact;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import au.edu.cdu.semiexact.io.FileOperation;
import au.edu.cdu.semiexact.util.ConstantValue;
import au.edu.cdu.semiexact.util.GlobalVariable;
import au.edu.cdu.semiexact.util.Util;

/**
 * an util class for test purpose
 */
public class TestUtil {
	// private static Logger log = LogUtil.getLogger(TestUtil.class);
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

	private static GlobalVariable<String, String> setGlobalVariable(int eCount, int[] eL, int[] eIL, int[] freq,
			int[][] eAL, int[][] eIM, int sCount, int[] sL, int[] sIL, int[] card, int[][] sAL, int[][] sIM) {
		GlobalVariable<String, String> gv = new GlobalVariable<String, String>();
		gv.setCard(card);
		gv.seteAL(eAL);
		gv.seteIL(eIL);
		gv.seteIM(eIM);
		// gv.seteLIL(eLIL);
		gv.setFreq(freq);
		gv.setsAL(sAL);
		gv.setsIL(sIL);
		gv.setsIM(sIM);
		// gv.setsLIL(sLIL);
		gv.seteCount(eCount);
		gv.setsCount(sCount);
//		gv.seteActCount(eCount);
//		gv.setsActCount(sCount);
		gv.setsL(sL);
		gv.seteL(eL);

		gv.setBestSolCount(sCount);
		gv.setSolCount(0);

		int[] mate = new int[eCount+1];

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

	public static GlobalVariable<String, String> getTC1RepFile() throws IOException {
		String filePath = TestUtil.getCurrentPath() + "/src/test/resources/sample1.txt";

		GlobalVariable<String, String> gv = new FileOperation().readGraphByEdgePair(filePath);
		return gv;

	}

	public static GlobalVariable<String, String> getTC1Rep() {
		int eCount = 6;

		// Map<String, Integer> eLIL = new HashMap<String, Integer>();
		// eLIL.put("a", 1);
		// eLIL.put("b", 2);
		// eLIL.put("c", 3);
		// eLIL.put("d", 4);
		// eLIL.put("e", 5);
		// eLIL.put("f", 6);
		int[] eL = { IV, 1, 2, 3, 4, 5, 6 };
		int[] eIL = { IV, 1, 2, 3, 4, 5, 6 };
		int[] freq = { eCount, 3, 3, 3, 4, 3, 2 };
		int[][] eAL = { {}, { IV, 1, 2, 3 }, { IV, 1, 2, 4 }, { IV, 1, 3, 4 }, { IV, 2, 3, 4, 5 }, { IV, 4, 5, 6 },
				{ IV, 5, 6 } };
		int[][] eIM = { { IV, IV, IV, IV, IV, IV, IV, }, { IV, 1, 1, 1, IV, IV, IV }, { IV, 2, 2, IV, 1, IV, IV },
				{ IV, 3, IV, 2, 2, IV, IV }, { IV, IV, 3, 3, 3, 1, IV }, { IV, IV, IV, IV, 4, 2, 1 },
				{ IV, IV, IV, IV, IV, 3, 2 } };

		int sCount = 6;
		// Map<String, Integer> sLIL = new HashMap<String, Integer>();
		// sLIL.put("Sa", 1);
		// sLIL.put("Sb", 2);
		// sLIL.put("Sc", 3);
		// sLIL.put("Sd", 4);
		// sLIL.put("Se", 5);
		// sLIL.put("Sf", 6);
		int[] sL = { IV, 1, 2, 3, 4, 5, 6 };
		int[] sIL = { IV, 1, 2, 3, 4, 5, 6 };
		int[] card = { sCount, 3, 3, 3, 4, 3, 2 };
		int[][] sAL = { {}, { IV, 1, 2, 3 }, { IV, 1, 2, 4 }, { IV, 1, 3, 4 }, { IV, 2, 3, 4, 5 }, { IV, 4, 5, 6 },
				{ IV, 5, 6 } };
		int[][] sIM = { { IV, IV, IV, IV, IV, IV, IV, }, { IV, 1, 1, 1, IV, IV, IV }, { IV, 2, 2, IV, 1, IV, IV },
				{ IV, 3, IV, 2, 2, IV, IV }, { IV, IV, 3, 3, 3, 1, IV }, { IV, IV, IV, IV, 4, 2, 1 },
				{ IV, IV, IV, IV, IV, 3, 2 } };
		return setGlobalVariable(eCount, eL, eIL, freq, eAL, eIM, sCount, sL, sIL, card, sAL, sIM);

	}

	public static GlobalVariable<String, String> getTC2Rep() {
		int eCount = 7;

//		Map<String, Integer> eLIL = new HashMap<String, Integer>();
//		eLIL.put("a", 1);
//		eLIL.put("b", 2);
//		eLIL.put("c", 3);
//		eLIL.put("d", 4);
//		eLIL.put("e", 5);
//		eLIL.put("f", 6);
//		eLIL.put("g", 7);
		int[] eL = { IV, 1, 2, 3, 4, 5, 6, 7 };
		int[] eIL = { IV, 1, 2, 3, 4, 5, 6, 7 };
		int[] freq = { eCount, 3, 3, 2, 2, 1, 2, 1 };
		int[][] eAL = { {}, { IV, 1, 2, 3 }, { IV, 1, 4, 5 }, { IV, 2, 4 }, { IV, 3, 5 }, { IV, 6 }, { IV, 6, 7 },
				{ IV, 7 } };

		int[][] eIM = { { IV, IV, IV, IV, IV, IV, IV, IV, }, { IV, 1, 1, IV, IV, IV, IV, IV },
				{ IV, 2, IV, 1, IV, IV, IV, IV }, { IV, 3, IV, IV, 2, IV, IV, IV }, { IV, IV, 2, 2, IV, IV, IV, IV },
				{ IV, IV, 3, IV, 2, IV, IV, IV }, { IV, IV, IV, IV, IV, 1, 1, IV }, { IV, IV, IV, IV, IV, IV, 2, 1 } };

		int sCount = 7;
//		Map<String, Integer> sLIL = new HashMap<String, Integer>();
//		sLIL.put("Sa", 1);
//		sLIL.put("Sb", 2);
//		sLIL.put("Sc", 3);
//		sLIL.put("Sd", 4);
//		sLIL.put("Se", 5);
//		sLIL.put("Sf", 6);
//		sLIL.put("Sg", 7);
		int[] sL = { IV, 1, 2, 3, 4, 5, 6, 7 };
		int[] sIL = { IV, 1, 2, 3, 4, 5, 6, 7 };
		int[] card = { sCount, 2, 2, 2, 2, 2, 2, 2 };
		int[][] sAL = { {}, { IV, 1, 2 }, { IV, 1, 3 }, { IV, 1, 4 }, { IV, 2, 3 }, { IV, 2, 4 }, { IV, 5, 6 },
				{ IV, 6, 7 } };

		int[][] sIM = { { IV, IV, IV, IV, IV, IV, IV, IV }, { IV, 1, 1, 1, IV, IV, IV, IV },
				{ IV, 2, IV, IV, 1, 1, IV, IV }, { IV, IV, 2, IV, 2, IV, IV, IV }, { IV, IV, IV, 2, IV, 2, IV, IV },
				{ IV, IV, IV, IV, IV, IV, 1, IV }, { IV, IV, IV, IV, IV, IV, 2, 1 },
				{ IV, IV, IV, IV, IV, IV, IV, 2 } };

		return setGlobalVariable(eCount, eL, eIL, freq, eAL, eIM, sCount, sL, sIL, card, sAL, sIM);
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
	 * print status of global variables formatly
	 * 
	 * @param gv,
	 *            global variables
	 */
	public static void printGlobalVariableStatus(GlobalVariable<String, String> gv) {
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
			if (array[i] == -1) {
				sb.append("N").append(",");
			} else {
				sb.append(array[i]).append(",");
			}
		}
		return sb.substring(0, sb.length() - 1);
	}
}
