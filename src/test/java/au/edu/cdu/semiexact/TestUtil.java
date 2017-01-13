package au.edu.cdu.semiexact;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import au.edu.cdu.semiexact.util.GlobalVariable;
import au.edu.cdu.semiexact.util.Util;

public class TestUtil {
	//private static Logger log = LogUtil.getLogger(TestUtil.class);

	public static String getCurrentPath() {
		return Paths.get(".").toAbsolutePath().normalize().toString();
	}
	
	public static List<List<Integer>> simpleTestCase1() {
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

	public static Map<Integer, List<Integer>> simpleTestCase1Map() {

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

	public static void printStatus(GlobalVariable<String, String> gv) {
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
