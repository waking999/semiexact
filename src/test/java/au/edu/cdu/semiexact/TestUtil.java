package au.edu.cdu.semiexact;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import au.edu.cdu.semiexact.util.ConstantValue;
import au.edu.cdu.semiexact.util.GlobalVariable;
import au.edu.cdu.semiexact.util.Util;

/**
 * an util class for test purpose
 */
public class TestUtil {
	// private static Logger log = LogUtil.getLogger(TestUtil.class);
	private static final int IMPOSSIBLE_VALUE = ConstantValue.IMPOSSIBLE_VALUE;
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

	private static GlobalVariable<String, String> setGlobalVariable(int eActCount, Map<String, Integer> eLIL, int[] eL,
			int[] eIL, int[] freq, int[][] eAL, int[][] eIM, int sActCount, Map<String, Integer> sLIL, int[] sL,
			int[] sIL, int[] card, int[][] sAL, int[][] sIM) {
		GlobalVariable<String, String> gv = new GlobalVariable<String, String>();
		gv.setCard(card);
		gv.seteAL(eAL);
		gv.seteIL(eIL);
		gv.seteIM(eIM);
		gv.seteLIL(eLIL);
		gv.setFreq(freq);
		gv.setsAL(sAL);
		gv.setsIL(sIL);
		gv.setsIM(sIM);
		gv.setsLIL(sLIL);
		gv.seteActCount(eActCount);
		gv.setsActCount(sActCount);
		gv.setsL(sL);
		gv.seteL(eL);

		gv.setBestSolCount(sActCount);
		gv.setSolCount(0);

		int[] mate = new int[eActCount];

		for (int i = 0; i < eActCount; i++) {
			mate[i] = ConstantValue.MATE_EXPOSE;
		}
		gv.setMate(mate);

		return gv;

	}

	public static GlobalVariable<String, String> getTestCase1ForGraphRepresentation() {
		int eActCount = 6;

		Map<String, Integer> eLIL = new HashMap<String, Integer>();
		eLIL.put("a", 0);
		eLIL.put("b", 1);
		eLIL.put("c", 2);
		eLIL.put("d", 3);
		eLIL.put("e", 4);
		eLIL.put("f", 5);
		int[] eL = { 0, 1, 2, 3, 4, 5 };
		int[] eIL = { 0, 1, 2, 3, 4, 5 };
		int[] freq = { 3, 3, 3, 4, 3, 2 };
		int[][] eAL = { { 0, 1, 2 }, { 0, 1, 3 }, { 0, 2, 3 }, { 1, 2, 3, 4 }, { 3, 4, 5 }, { 4, 5 } };
		int[][] eIM = { { 0, 0, 0, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE },
				{ 1, 1, IMPOSSIBLE_VALUE, 0, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE },
				{ 2, IMPOSSIBLE_VALUE, 1, 1, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE },
				{ IMPOSSIBLE_VALUE, 2, 2, 2, 0, IMPOSSIBLE_VALUE },
				{ IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, 3, 1, 0 },
				{ IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, 2, 1 } };

		int sActCount = 6;
		Map<String, Integer> sLIL = new HashMap<String, Integer>();
		sLIL.put("Sa", 0);
		sLIL.put("Sb", 1);
		sLIL.put("Sc", 2);
		sLIL.put("Sd", 3);
		sLIL.put("Se", 4);
		sLIL.put("Sf", 5);
		int[] sL = { 0, 1, 2, 3, 4, 5 };
		int[] sIL = { 0, 1, 2, 3, 4, 5 };
		int[] card = { 3, 3, 3, 4, 3, 2 };
		int[][] sAL = { { 0, 1, 2 }, { 0, 1, 3 }, { 0, 2, 3 }, { 1, 2, 3, 4 }, { 3, 4, 5 }, { 4, 5 } };
		int[][] sIM = { { 0, 0, 0, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE },
				{ 1, 1, IMPOSSIBLE_VALUE, 0, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE },
				{ 2, IMPOSSIBLE_VALUE, 1, 1, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE },
				{ IMPOSSIBLE_VALUE, 2, 2, 2, 0, IMPOSSIBLE_VALUE },
				{ IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, 3, 1, 0 },
				{ IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, 2, 1 } };

		return setGlobalVariable(eActCount, eLIL, eL, eIL, freq, eAL, eIM, sActCount, sLIL, sL, sIL, card, sAL, sIM);
	}

	public static GlobalVariable<String, String> getTestCase2ForGraphRepresentation() {
		int eActCount = 7;

		Map<String, Integer> eLIL = new HashMap<String, Integer>();
		eLIL.put("a", 0);
		eLIL.put("b", 1);
		eLIL.put("c", 2);
		eLIL.put("d", 3);
		eLIL.put("e", 4);
		eLIL.put("f", 5);
		eLIL.put("g", 6);
		int[] eL = { 0, 1, 2, 3, 4, 5, 6 };
		int[] eIL = { 0, 1, 2, 3, 4, 5, 6 };
		int[] freq = { 3, 3, 2, 2, 1, 2, 1 };
		int[][] eAL = { { 0, 1, 2 }, { 0, 3, 4 }, { 1, 3 }, { 2, 4 }, { 5 }, { 5, 6 }, { 6 } };

		int[][] eIM = {
				{ 0, 0, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE },
				{ 1, IMPOSSIBLE_VALUE, 0, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE },
				{ 2, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, 1, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE },
				{ IMPOSSIBLE_VALUE, 1, 1, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE },
				{ IMPOSSIBLE_VALUE, 2, IMPOSSIBLE_VALUE, 1, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE },
				{ IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, 0, 0, IMPOSSIBLE_VALUE },
				{ IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, 1, 0 } };

		int sActCount = 7;
		Map<String, Integer> sLIL = new HashMap<String, Integer>();
		sLIL.put("Sa", 0);
		sLIL.put("Sb", 1);
		sLIL.put("Sc", 2);
		sLIL.put("Sd", 3);
		sLIL.put("Se", 4);
		sLIL.put("Sf", 5);
		sLIL.put("Sg", 5);
		int[] sL = { 0, 1, 2, 3, 4, 5, 6 };
		int[] sIL = { 0, 1, 2, 3, 4, 5, 6 };
		int[] card = { 2, 2, 2, 2, 2, 2, 2 };
		int[][] sAL = { { 0, 1 }, { 0, 2 }, { 0, 3 }, { 1, 2 }, { 1, 3 }, { 4, 5 }, { 5, 6 } };

		int[][] sIM = { { 0, 0, 0, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE },
				{ 1, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, 0, 0, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE },
				{ IMPOSSIBLE_VALUE, 1, IMPOSSIBLE_VALUE, 1, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE },
				{ IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, 1, IMPOSSIBLE_VALUE, 1, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE },
				{ IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, 0,
						IMPOSSIBLE_VALUE },
				{ IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, 1, 0 },
				{ IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE,
						IMPOSSIBLE_VALUE, 1 } };

		return setGlobalVariable(eActCount, eLIL, eL, eIL, freq, eAL, eIM, sActCount, sLIL, sL, sIL, card, sAL, sIM);
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
		int sLen = sIL.length;
		int[] card = gv.getCard();
		int[][] sAL = gv.getsAL();
		int[][] sIM = gv.getsIM();
		int sActCount = gv.getsActCount();
		int[] sL = gv.getsL();

		printStatus(styleStr, sLen, "sActCount", sActCount, "sL", sL, "sIL", sIL, "card", card, "sAL", sAL, "sIM", sIM);

		System.out.println();
		int[] eIL = gv.geteIL();
		int eLen = eIL.length;
		int[] freq = gv.getFreq();
		int[][] eAL = gv.geteAL();
		int[][] eIM = gv.geteIM();
		int eActCount = gv.geteActCount();
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
		for (int i = 0; i < len; i++) {
			sb.setLength(0);

			System.out.printf(styleStr, i + " " + l[i], i + " " + il[i], i + " " + deg[i],
					i + " " + arrayToString(al[i]), arrayToString(im[i]));
			System.out.println();
		}
	}

	private static String arrayToString(int[] array) {
		StringBuffer sb = new StringBuffer();
		int arrayLen = array.length;
		for (int i = 0; i < arrayLen; i++) {
			if (array[i] == -1) {
				sb.append("N").append(",");
			} else {
				sb.append(array[i]).append(",");
			}
		}
		return sb.substring(0, sb.length() - 1);
	}
}
