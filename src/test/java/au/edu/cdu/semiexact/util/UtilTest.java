package au.edu.cdu.semiexact.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import au.edu.cdu.semiexact.TestUtil;

public class UtilTest {
	private static Logger log = LogUtil.getLogger(UtilTest.class);

	@Test
	public void testArrayToList() {
		int[] l1 = { 1, 2, 3 };
		List<Integer> list1 = Util.arrayToList(l1);
		Assert.assertEquals(new Integer(1), list1.get(0));
		Assert.assertEquals(new Integer(2), list1.get(1));
		Assert.assertEquals(new Integer(3), list1.get(2));
	}

	@Test
	public void testIs1Subset2() {
		int[] l1 = { 1, 2, 3 };
		int[] l2 = { 1, 2 };

		List<Integer> list1 = Util.arrayToList(l1);
		List<Integer> list2 = Util.arrayToList(l2);

		Assert.assertTrue(Util.is1Subset2(list2, list1));
		Assert.assertFalse(Util.is1Subset2(list1, list2));

		list1 = null;
		Assert.assertFalse(Util.is1Subset2(list2, list1));

		list2 = null;
		Assert.assertTrue(Util.is1Subset2(list2, list1));
	}

	@Test
	public void testExistSubset() {
		int[] l1 = { 1, 2, 3 };
		int[] l2 = { 1, 2 };

		List<Integer> list1 = Util.arrayToList(l1);
		List<Integer> list2 = Util.arrayToList(l2);
		List<List<Integer>> list = null;

		Assert.assertFalse(Util.existSubset(list).isExist());

		list = new ArrayList<List<Integer>>();
		Assert.assertFalse(Util.existSubset(list).isExist());
		list.add(list1);
		Assert.assertFalse(Util.existSubset(list).isExist());
		list.add(list2);
		Assert.assertTrue(Util.existSubset(list).isExist());
		Assert.assertEquals(1, Util.existSubset(list).getSetIndex());
	}

	@Test
	public void testExistUniqueSetForAElementList() {
		int[] l1 = { 1, 2, 3 };
		int[] l2 = { 1, 2 };

		List<Integer> list1 = Util.arrayToList(l1);
		List<Integer> list2 = Util.arrayToList(l2);
		List<List<Integer>> list = null;
		Assert.assertFalse(Util.existUniqueSetForAElement(2, list).isExist());
		list = new ArrayList<List<Integer>>();
		Assert.assertFalse(Util.existUniqueSetForAElement(2, list).isExist());
		Assert.assertFalse(Util.existUniqueSetForAElement(3, list).isExist());

		list.add(list1);
		list.add(list2);

		Assert.assertTrue(Util.existUniqueSetForAElement(3, list).isExist());
		Assert.assertEquals(0, Util.existUniqueSetForAElement(3, list).getSetIndex());
		Assert.assertFalse(Util.existUniqueSetForAElement(2, list).isExist());
	}

	@Test
	public void testExistUniqueSetForAElement() {
		int[] l1 = { 2, 3 };
		int[] l2 = { 1, 2 };

		int[] u1 = { 1, 2, 3 };

		List<Integer> list1 = Util.arrayToList(l1);
		List<Integer> list2 = Util.arrayToList(l2);
		List<Integer> uList = null;
		List<List<Integer>> list = null;
		Assert.assertFalse(Util.existUniqueSetForAElement(uList, list).isExist());

		uList = Util.arrayToList(u1);
		Assert.assertFalse(Util.existUniqueSetForAElement(uList, list).isExist());

		list = new ArrayList<List<Integer>>();
		Assert.assertFalse(Util.existUniqueSetForAElement(uList, list).isExist());

		list.add(list1);
		Assert.assertTrue(Util.existUniqueSetForAElement(uList, list).isExist());
		Assert.assertEquals(0, Util.existUniqueSetForAElement(uList, list).getSetIndex());

		list.add(list2);
		Assert.assertTrue(Util.existUniqueSetForAElement(uList, list).isExist());
		Assert.assertEquals(1, Util.existUniqueSetForAElement(uList, list).getSetIndex());
	}

	@Test
	public void testUnionSets() {
		int[] l1 = { 2, 3 };
		int[] l2 = { 1, 2 };

		List<Integer> list1 = Util.arrayToList(l1);
		List<Integer> list2 = Util.arrayToList(l2);

		List<List<Integer>> list = null;
		Assert.assertNull(Util.unionSets(list));

		list = new ArrayList<List<Integer>>();
		Assert.assertNull(Util.unionSets(list));

		list.add(list1);
		Assert.assertNotNull(Util.unionSets(list));
		Assert.assertEquals(new Integer(2), Util.unionSets(list).get(0));
		Assert.assertEquals(new Integer(3), Util.unionSets(list).get(1));

		list.add(list2);
		Assert.assertEquals(new Integer(1), Util.unionSets(list).get(2));
	}

	@Test
	public void testSet1Minus2() {
		int[] l1 = { 2, 3 };
		int[] l2 = { 1, 2 };

		List<Integer> list1 = null;
		List<Integer> list2 = null;

		Util.set1Minus2(list1, list2);
		Assert.assertNull(list1);

		list1 = Util.arrayToList(l1);
		list2 = Util.arrayToList(l2);

		list1 = Util.set1Minus2(list1, list2);
		Assert.assertEquals(1, list1.size());
		Assert.assertEquals(new Integer(3), list1.get(0));
		Assert.assertEquals(2, list2.size());

		int[] l3 = { 1, 2, 3 };
		List<Integer> list3 = Util.arrayToList(l3);
		list3 = Util.set1Minus2(list3, list2);
		Assert.assertEquals(1, list3.size());
		Assert.assertEquals(new Integer(3), list3.get(0));
		Assert.assertEquals(2, list2.size());

	}

	@Test
	public void testDeletSetFromList() {
		int[] l1 = { 2, 3 };
		List<Integer> list1 = Util.arrayToList(l1);
		List<List<Integer>> list = null;

		Util.deleteSet(list, list1);
		Assert.assertNull(list);

		list = new ArrayList<List<Integer>>();
		Util.deleteSet(list, null);
		Assert.assertNotNull(list);
		Assert.assertEquals(0, list.size());

		int[] l2 = { 1, 2 };
		List<Integer> list2 = Util.arrayToList(l2);
		list.add(list1);
		list.add(list2);

		list = Util.deleteSet(list, list1);
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
		Assert.assertEquals(list2, list.get(0));
	}

	@Test
	public void testDeletSetFromMap() {

		int[] l1 = { 2, 3 };
		int[] l2 = { 1, 2 };
		int[] l3 = { 1, 2, 3 };

		List<Integer> list1 = Util.arrayToList(l1);
		List<Integer> list2 = Util.arrayToList(l2);
		List<Integer> list3 = Util.arrayToList(l3);

		Map<Integer, List<Integer>> map = null;
		Util.deleteSet(map, list1);
		Assert.assertNull(map);

		map = new HashMap<Integer, List<Integer>>();
		map.put(1, list1);
		map.put(2, list2);
		map.put(3, list3);

		Util.deleteSet(map, null);
		Assert.assertNotNull(map);

		Util.deleteSet(map, list1);
		Assert.assertNotNull(map);

		Assert.assertEquals(2, map.size());
	}

	@Test
	public void testCopyList() {
		int[] l1 = { 2, 3 };
		List<Integer> list1 = null;
		List<Integer> list1Copy = Util.copyList(list1);
		Assert.assertNull(list1Copy);

		list1 = new ArrayList<Integer>();
		list1Copy = Util.copyList(list1);
		Assert.assertEquals(0, list1Copy.size());

		list1 = Util.arrayToList(l1);
		list1Copy = Util.copyList(list1);
		Assert.assertEquals(2, list1Copy.size());
		Assert.assertEquals(new Integer(2), list1Copy.get(0));
		Assert.assertEquals(new Integer(2), list1Copy.get(0));

	}

	@Test
	public void testGetMaxCardinalitySet() {
		int[] l1 = { 2, 3 };
		int[] l2 = { 1, 2 };
		int[] l3 = { 1, 2, 3 };

		List<Integer> list1 = Util.arrayToList(l1);
		List<Integer> list2 = Util.arrayToList(l2);
		List<Integer> list3 = Util.arrayToList(l3);

		List<List<Integer>> list = null;
		List<Integer> list4 = Util.getMaxCardinalitySet(list);
		Assert.assertNull(list4);

		list = new ArrayList<List<Integer>>();

		list.add(list1);
		list.add(list2);
		list.add(list3);

		list4 = Util.getMaxCardinalitySet(list);
		Assert.assertEquals(3, list4.size());

	}

	@Test
	public void testConvertMapToListOfSet() {
		int[] l1 = { 2, 3 };
		int[] l2 = { 1, 2 };
		int[] l3 = { 1, 2, 3 };

		List<Integer> list1 = Util.arrayToList(l1);
		List<Integer> list2 = Util.arrayToList(l2);
		List<Integer> list3 = Util.arrayToList(l3);

		Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
		map.put(1, list1);
		map.put(2, list2);
		map.put(3, list3);

		List<List<Integer>> list = Util.convertMapToListOfSet(map);
		Assert.assertEquals(3, list.size());

	}

	@Test
	public void testCopyMap() {
		int[] l1 = { 2, 3 };
		int[] l2 = { 1, 2 };
		int[] l3 = { 1, 2, 3 };

		List<Integer> list1 = Util.arrayToList(l1);
		List<Integer> list2 = Util.arrayToList(l2);
		List<Integer> list3 = Util.arrayToList(l3);

		Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
		map.put(1, list1);
		map.put(2, list2);
		map.put(3, list3);

		Map<Integer, List<Integer>> map1 = Util.copyMap(map);
		Assert.assertEquals(3, map1.size());

	}
//
//	@Test
//	public void testGetMaxIndex() {
//		int[] array = { 5, 3, 2, 23, 1, 2 };
//		int maxIdx = Util.getMaxIndex(array);
//		Assert.assertEquals(3, maxIdx);
//	}

//	@Test
//	public void testFindEdge() {
//
//		GlobalVariable<String, String> gv = TestUtil.getTestCase2();
//
//		 
//		int edge = Util.findSet(gv, 0, 2);
//		Assert.assertEquals(1, edge);
//
//		edge = Util.findSet(gv, 1, 3);
//		Assert.assertEquals(4, edge);
//	}
	
	@Test
	public void testGetMaxCardinalitySetIndex() {
		 
		GlobalVariable<String, String> gv = TestUtil.getTC1Rep();
		TestUtil.printGlobalVariableStatus(gv);

		//Map<String, Integer> sLIL = gv.getsLIL();

		 

		//int sIdx = sLIL.get("Sd");
		int sIdx=4;
		int[] card=gv.getCard();
		
		int selectSetIdx = Util.getMaxCardinalitySetIndex(gv,card);
		Assert.assertEquals(sIdx, selectSetIdx);
 

	}
	//@Ignore
	@Test
	public void testDeleteSeta1() {

		GlobalVariable<String, String> gv = TestUtil.getTC1Rep();

		testDeleteSeta(gv);

	}

	//@Ignore
	@Test
	public void testDeleteSeta2() throws IOException {

		GlobalVariable<String, String> gv = TestUtil.getTC1RepFile();

		testDeleteSeta(gv);

	}

	private void testDeleteSeta(GlobalVariable<String, String> gv) {
		log.debug(TestUtil.FUNCTION_SEP);
		TestUtil.printGlobalVariableStatus(gv);
//		MSC4<String, String> msc = new MSC4<String, String>();

		// String sToDel = "Sd";

		int[] card = gv.getCard();
		int[] freq = gv.getFreq();

		int sActCount = card[0];
		// Map<String, Integer> sLIL = gv.getsLIL();
		int[] sIL = gv.getsIL();
		// int sToDelIdx = sLIL.get(sToDel);
		int sToDelIdx = 4;
		int sToExchIdx = sIL[sActCount];

		Util.deleteSet(gv, card, freq, sToDelIdx);
		TestUtil.printGlobalVariableStatus(gv);

		Assert.assertEquals(sActCount - 1, card[0]);

		sIL = gv.getsIL();

		Assert.assertEquals(0, card[sToDelIdx]);
		Assert.assertEquals(sToDelIdx, sIL[sActCount]);
		Assert.assertEquals(sToExchIdx, sIL[sToDelIdx]);
		Assert.assertEquals(sActCount, sIL[sToDelIdx]);
		Assert.assertEquals(sToDelIdx, sIL[sToExchIdx]);

		// sToDel = "Sa";

		sActCount = card[0];
		sIL = gv.getsIL();

		// sToDelIdx = sLIL.get(sToDel);
		sToDelIdx = 1;
		sToExchIdx = sIL[sActCount];

		Util.deleteSet(gv, card, freq, sToDelIdx);
		TestUtil.printGlobalVariableStatus(gv);

		Assert.assertEquals(sActCount - 1, card[0]);

		sIL = gv.getsIL();

		Assert.assertEquals(0, card[sToDelIdx]);
		Assert.assertEquals(sToDelIdx, sIL[sActCount]);
		Assert.assertEquals(sToExchIdx, sIL[sToDelIdx]);
		Assert.assertEquals(sActCount, sIL[sToDelIdx]);
		Assert.assertEquals(sToDelIdx, sIL[sToExchIdx]);
	}

	// ////@Ignore
	// @Test
	// public void testDecreaseSetCardinality1() {
	//
	// GlobalVariable<String, String> gv = TestUtil.getTC1Rep();
	// testDecreaseSetCardinality(gv);
	//
	// }
	//
	// ////@Ignore
	// @Test
	// public void testDecreaseSetCardinality2() throws IOException {
	//
	// GlobalVariable<String, String> gv = TestUtil.getTC1RepFile();
	// testDecreaseSetCardinality(gv);
	//
	// }
	//
	// private void testDecreaseSetCardinality(GlobalVariable<String, String>
	// gv) {
	// log.debug(TestUtil.FUNCTION_SEP);
	// TestUtil.printGlobalVariableStatus(gv);
	//
	// // Map<String, Integer> sLIL = gv.getsLIL();
	// int[][] sAL = gv.getsAL();
	// int[][] sIM = gv.getsIM();
	//
	// // Map<String, Integer> eLIL = gv.geteLIL();
	//
	// int[] card = gv.getCard();
	//
	// MSC4<String, String> msc = new MSC4<String, String>();
	//
	// // String sToDec = "Se";
	// // String eToDel = "d";
	//
	// // int sToDecIdx = sLIL.get(sToDec);
	// // int eToDelIdx = eLIL.get(eToDel);
	//
	// int sToDecIdx = 5;
	// int eToDelIdx = 4;
	//
	// int cardB4 = card[sToDecIdx];
	// int sIMEToDelCellValB4 = sIM[eToDelIdx][sToDecIdx];
	// int eToExchIdx = sAL[sToDecIdx][cardB4];
	//
	// int sIMCell2B4 = sIM[sAL[sToDecIdx][cardB4]][sToDecIdx];
	//
	// Util.decreaseSetCardinality(gv, sToDecIdx, eToDelIdx);
	// TestUtil.printGlobalVariableStatus(gv);
	// Assert.assertEquals(sAL[sToDecIdx][sIMEToDelCellValB4], eToExchIdx);
	// Assert.assertEquals(sAL[sToDecIdx][cardB4], eToDelIdx);
	// Assert.assertEquals(sIM[eToExchIdx][sToDecIdx], sIMEToDelCellValB4);
	// Assert.assertEquals(sIM[eToDelIdx][sToDecIdx], sIMCell2B4);
	// Assert.assertEquals(cardB4 - 1, card[sToDecIdx]);
	//
	// // sToDec = "Se";
	// // eToDel = "f";
	// //
	// // sToDecIdx = sLIL.get(sToDec);
	// // eToDelIdx = eLIL.get(eToDel);
	// sToDecIdx = 5;
	// eToDelIdx = 6;
	//
	// cardB4 = card[sToDecIdx];
	// sIMEToDelCellValB4 = sIM[eToDelIdx][sToDecIdx];
	// eToExchIdx = sAL[sToDecIdx][cardB4];
	//
	// sIMCell2B4 = sIM[sAL[sToDecIdx][cardB4]][sToDecIdx];
	// Util.decreaseSetCardinality(gv, sToDecIdx, eToDelIdx);
	// TestUtil.printGlobalVariableStatus(gv);
	// Assert.assertEquals(gv.getsAL()[sToDecIdx][sIMEToDelCellValB4],
	// eToExchIdx);
	// Assert.assertEquals(gv.getsAL()[sToDecIdx][cardB4], eToDelIdx);
	// Assert.assertEquals(gv.getsIM()[eToExchIdx][sToDecIdx],
	// sIMEToDelCellValB4);
	// Assert.assertEquals(gv.getsIM()[eToDelIdx][sToDecIdx], sIMCell2B4);
	// Assert.assertEquals(cardB4 - 1, gv.getCard()[sToDecIdx]);
	// }

	//@Ignore
	@Test
	public void testDeleteElement1() {

		GlobalVariable<String, String> gv = TestUtil.getTC1Rep();
		testDeleteElement(gv);

	}

	//@Ignore
	@Test
	public void testDeleteElement2() throws IOException {

		GlobalVariable<String, String> gv = TestUtil.getTC1RepFile();
		testDeleteElement(gv);

	}

	private void testDeleteElement(GlobalVariable<String, String> gv) {
		log.debug(TestUtil.FUNCTION_SEP);
		TestUtil.printGlobalVariableStatus(gv);

		//MSC4<String, String> msc = new MSC4<String, String>();

		// String eToDel = "e";

		int[] eIL = gv.geteIL();
		int[] card = gv.getCard();
		int[] freq = gv.getFreq();

		int eActCount = freq[0];
		// Map<String, Integer> eLIL = gv.geteLIL();

		// int eToDelIdx = eLIL.get(eToDel);
		int eToDelIdx = 5;

		int eToExchIdx = eIL[eActCount];

		Util.deleteElement(gv, card, freq, eToDelIdx);
		TestUtil.printGlobalVariableStatus(gv);

		Assert.assertEquals(eActCount - 1, freq[0]);

		int[] eL = gv.geteL();
		eIL = gv.geteIL();
		// eLIL = gv.geteLIL();

		Assert.assertEquals(0, freq[eToDelIdx]);
		Assert.assertEquals(eToDelIdx, eL[eActCount]);
		Assert.assertEquals(eToExchIdx, eL[eToDelIdx]);
		Assert.assertEquals(eActCount, eIL[eToDelIdx]);
		Assert.assertEquals(eToDelIdx, eIL[eToExchIdx]);

		// eToDel = "a";

		eActCount = freq[0];
		// eLIL = gv.geteLIL();
		eIL = gv.geteIL();
		// eToDelIdx = eLIL.get(eToDel);
		eToDelIdx = 1;
		eToExchIdx = eIL[eActCount];

		Util.deleteElement(gv, card, freq, eToDelIdx);
		TestUtil.printGlobalVariableStatus(gv);

		Assert.assertEquals(eActCount - 1, freq[0]);

		eL = gv.geteL();
		eIL = gv.geteIL();
		// eLIL = gv.geteLIL();

		Assert.assertEquals(0, freq[eToDelIdx]);
		Assert.assertEquals(eToDelIdx, eL[eActCount]);
		Assert.assertEquals(eToExchIdx, eL[eToDelIdx]);
		Assert.assertEquals(eActCount, eIL[eToDelIdx]);
		Assert.assertEquals(eToDelIdx, eIL[eToExchIdx]);
	}

	//@Ignore
	@Test
	public void testAddSetToCover1() {

		GlobalVariable<String, String> gv = TestUtil.getTC1Rep();
		testAddSetToCover(gv);

	}

	//@Ignore
	@Test
	public void testAddSetToCover2() throws IOException {

		GlobalVariable<String, String> gv = TestUtil.getTC1RepFile();
		testAddSetToCover(gv);

	}

	private void testAddSetToCover(GlobalVariable<String, String> gv) {
		log.debug(TestUtil.FUNCTION_SEP);
		TestUtil.printGlobalVariableStatus(gv);

		//MSC4<String, String> msc = new MSC4<String, String>();

		// Map<String, Integer> sLIL = gv.getsLIL();
		int[] card = gv.getCard();
		int[] freq = gv.getFreq();

		// String sToAdd = "Se";
		int sActCount = card[0];
		// int sToAddIdx = sLIL.get(sToAdd);
		int sToAddIdx = 5;
		Util.addSetToCover(gv, card, freq, sToAddIdx);
		TestUtil.printGlobalVariableStatus(gv);
		Assert.assertEquals(sActCount - 1, card[0]);

		// sToAdd = "Sa";
		sActCount = card[0];
		// sToAddIdx = sLIL.get(sToAdd);
		sToAddIdx = 1;

		Util.addSetToCover(gv, card, freq, sToAddIdx);
		TestUtil.printGlobalVariableStatus(gv);
		Assert.assertEquals(sActCount - 1, card[0]);
	}

	//@Ignore
	@Test
	public void testAProcessManuallyByPersonSimulation1() {

		GlobalVariable<String, String> gv = TestUtil.getTC1Rep();
		testAProcessManuallyByPersonSimulation(gv);
	}

	//@Ignore
	@Test
	public void testAProcessManuallyByPersonSimulation2() throws IOException {

		GlobalVariable<String, String> gv = TestUtil.getTC1RepFile();
		testAProcessManuallyByPersonSimulation(gv);
	}

	private void testAProcessManuallyByPersonSimulation(GlobalVariable<String, String> gv) {
		log.debug(TestUtil.FUNCTION_SEP);
		TestUtil.printGlobalVariableStatus(gv);

		//MSC4<String, String> msc = new MSC4<String, String>();

		// Map<String, Integer> sLIL = gv.getsLIL();
		int[] card = gv.getCard();
		int[] freq = gv.getFreq();

		// 1. delete sf
		// String s = "Sf";
		// int sIdx = sLIL.get(s);
		int sIdx = 6;

		Util.deleteSet(gv, card, freq, sIdx);
		TestUtil.printGlobalVariableStatus(gv);
		// 2. add se
		// s = "Se";
		// sIdx = sLIL.get(s);
		sIdx = 5;

		Util.addSetToCover(gv, card, freq, sIdx);
		TestUtil.printGlobalVariableStatus(gv);
		// 3. delete sb
		// s = "Sb";
		// sIdx = sLIL.get(s);
		sIdx = 2;

		Util.deleteSet(gv, card, freq, sIdx);
		TestUtil.printGlobalVariableStatus(gv);
		// 4. delete sc
		// s = "Sc";
		// sIdx = sLIL.get(s);
		sIdx = 3;

		Util.deleteSet(gv, card, freq, sIdx);
		TestUtil.printGlobalVariableStatus(gv);
		// 5. delete sd
		// s = "Sd";
		// sIdx = sLIL.get(s);
		sIdx = 4;
		Util.deleteSet(gv, card, freq, sIdx);
		TestUtil.printGlobalVariableStatus(gv);
		// 6. add sa
		// s = "Sa";
		// sIdx = sLIL.get(s);
		sIdx = 1;
		Util.addSetToCover(gv, card, freq, sIdx);
		TestUtil.printGlobalVariableStatus(gv);
	}

	//@Ignore
	@Test
	public void testDeleteSetb1() {

		GlobalVariable<String, String> gv = TestUtil.getTC1Rep();
		testDeletsetb(gv);

	}

	//@Ignore
	@Test
	public void testDeleteSetb2() throws IOException {

		GlobalVariable<String, String> gv = TestUtil.getTC1RepFile();
		testDeletsetb(gv);

	}

	private void testDeletsetb(GlobalVariable<String, String> gv) {
		log.debug(TestUtil.FUNCTION_SEP);
		TestUtil.printGlobalVariableStatus(gv);

		// Map<String, Integer> sLIL = gv.getsLIL();
		int[] card = gv.getCard();
		int[] freq = gv.getFreq();

		//MSC4<String, String> msc = new MSC4<String, String>();

		// int sIdx = sLIL.get("Sd");
		int sIdx = 4;

		int selectSetIdx = Util.getMaxCardinalitySetIndex(gv, card);
		Assert.assertEquals(sIdx, selectSetIdx);

		// delete sf
		// String s = "Sf";
		// sIdx = sLIL.get(s);
		sIdx = 6;

		Util.deleteSet(gv, card, freq, sIdx);
		TestUtil.printGlobalVariableStatus(gv);
		// add se
		// s = "Se";
		// sIdx = sLIL.get(s);
		sIdx = 5;

		Util.addSetToCover(gv, card, freq, sIdx);
		TestUtil.printGlobalVariableStatus(gv);

		// sIdx = sLIL.get("Sa");
		sIdx = 1;

		selectSetIdx = Util.getMaxCardinalitySetIndex(gv, card);
		Assert.assertEquals(sIdx, selectSetIdx);
	}

	//@Ignore
	@Test
	public void testGetSetOfFrequencyOneElement1() {

		GlobalVariable<String, String> gv = TestUtil.getTC1Rep();
		testGetSetOfFrequencyOneElement(gv);
	}

	//@Ignore
	@Test
	public void testGetSetOfFrequencyOneElement2() throws IOException {

		GlobalVariable<String, String> gv = TestUtil.getTC1RepFile();
		testGetSetOfFrequencyOneElement(gv);
	}

	private void testGetSetOfFrequencyOneElement(GlobalVariable<String, String> gv) {
		log.debug(TestUtil.FUNCTION_SEP);
		TestUtil.printGlobalVariableStatus(gv);

		// Map<String, Integer> sLIL = gv.getsLIL();
		int[] card = gv.getCard();
		int[] freq = gv.getFreq();

		//MSC4<String, String> msc = new MSC4<String, String>();

		// delete sf
		// String s = "Sf";
		// int sIdx = sLIL.get(s);
		int sIdx = 6;

		Util.deleteSet(gv, card, freq, sIdx);
		TestUtil.printGlobalVariableStatus(gv);

		// sIdx = sLIL.get("Se");
		sIdx = 5;

		int selectSetIdx = Util.getSetOfFrequencyOneElement(gv, freq);
		Assert.assertEquals(sIdx, selectSetIdx);

		// add se
		// s = "Se";
		// sIdx = sLIL.get(s);
		sIdx = 5;

		Util.addSetToCover(gv, card, freq, sIdx);
		TestUtil.printGlobalVariableStatus(gv);

		// delete sb
		// s = "Sb";
		// sIdx = sLIL.get(s);
		sIdx = 2;

		Util.deleteSet(gv, card, freq, sIdx);
		TestUtil.printGlobalVariableStatus(gv);
		// delete sc
		// s = "Sc";
		// sIdx = sLIL.get(s);
		sIdx = 3;
		Util.deleteSet(gv, card, freq, sIdx);
		TestUtil.printGlobalVariableStatus(gv);
		// delete sd
		// s = "Sd";
		// sIdx = sLIL.get(s);
		sIdx = 4;
		Util.deleteSet(gv, card, freq, sIdx);
		TestUtil.printGlobalVariableStatus(gv);

		// sIdx = sLIL.get("Sa");
		sIdx = 1;
		selectSetIdx = Util.getSetOfFrequencyOneElement(gv, freq);
		Assert.assertEquals(sIdx, selectSetIdx);
	}

	//@Ignore
	@Test
	public void testIs1Subset21() {

		GlobalVariable<String, String> gv = TestUtil.getTC1Rep();
		testIs1Subset2(gv);

	}

	//@Ignore
	@Test
	public void testIs1Subset22() throws IOException {

		GlobalVariable<String, String> gv = TestUtil.getTC1RepFile();
		testIs1Subset2(gv);

	}

	private void testIs1Subset2(GlobalVariable<String, String> gv) {
		log.debug(TestUtil.FUNCTION_SEP);
		TestUtil.printGlobalVariableStatus(gv);
		// Map<String, Integer> sLIL = gv.getsLIL();
		int[] card = gv.getCard();
		int[] freq = gv.getFreq();
		//MSC4<String, String> msc = new MSC4<String, String>();

		// String s1 = "Sf";
		// String s2 = "Se";
		// int s1Idx = sLIL.get(s1);
		// int s2Idx = sLIL.get(s2);

		int s1Idx = 6;
		int s2Idx = 5;

		Assert.assertTrue(Util.is1Subset2(gv, card, s1Idx, s2Idx));
		Assert.assertFalse(Util.is1Subset2(gv, card, s2Idx, s1Idx));

		// delete sf
		// String s = "Sf";
		// int sIdx = sLIL.get(s);
		int sIdx = 6;
		Util.deleteSet(gv, card, freq, sIdx);

		TestUtil.printGlobalVariableStatus(gv);

		// add se
		// s = "Se";
		// sIdx = sLIL.get(s);
		sIdx = 5;

		Util.addSetToCover(gv, card, freq, sIdx);
		TestUtil.printGlobalVariableStatus(gv);

		// s1 = "Sb";
		// s2 = "Sa";
		// s1Idx = sLIL.get(s1);
		// s2Idx = sLIL.get(s2);
		s1Idx = 2;
		s2Idx = 1;

		Assert.assertTrue(Util.is1Subset2(gv, card, s1Idx, s2Idx));
		Assert.assertFalse(Util.is1Subset2(gv, card, s2Idx, s1Idx));
	}

	//@Ignore
	@Test
	public void testGetSubset1() {

		GlobalVariable<String, String> gv = TestUtil.getTC1Rep();
		testGetSubset(gv);

	}

	//@Ignore
	@Test
	public void testGetSubset2() throws IOException {

		GlobalVariable<String, String> gv = TestUtil.getTC1RepFile();
		testGetSubset(gv);

	}

	private void testGetSubset(GlobalVariable<String, String> gv) {
		log.debug(TestUtil.FUNCTION_SEP);
		TestUtil.printGlobalVariableStatus(gv);

		//MSC4<String, String> msc = new MSC4<String, String>();

		// String s1 = "Sf";
		// Map<String, Integer> sLIL = gv.getsLIL();
		// int s1Idx = sLIL.get(s1);
		int[] card = gv.getCard();
		int[] freq = gv.getFreq();

		int s1Idx = 6;

		int subSetIdx = Util.getSubset(gv, card);
		Assert.assertEquals(s1Idx, subSetIdx);

		// delete sf
		// String s = "Sf";
		// int sIdx = sLIL.get(s);
		int sIdx = 6;

		Util.deleteSet(gv, card, freq, sIdx);
		TestUtil.printGlobalVariableStatus(gv);

		// add se
		// s = "Se";
		// sIdx = sLIL.get(s);
		sIdx = 5;

		Util.addSetToCover(gv, card, freq, sIdx);
		TestUtil.printGlobalVariableStatus(gv);

		// s1 = "Sb";
		// s1Idx = sLIL.get(s1);
		s1Idx = 2;

		subSetIdx = Util.getSubset(gv, card);
		Assert.assertEquals(s1Idx, subSetIdx);

		// delete sb
		// s = "Sb";
		// sIdx = sLIL.get(s);
		sIdx = 2;
		Util.deleteSet(gv, card, freq, sIdx);
		TestUtil.printGlobalVariableStatus(gv);

		// s1 = "Sd";
		// s1Idx = sLIL.get(s1);
		s1Idx = 4;

		subSetIdx = Util.getSubset(gv, card);
		Assert.assertEquals(s1Idx, subSetIdx);

		// delete sd
		// s = "Sd";
		// sIdx = sLIL.get(s);
		sIdx = 4;

		Util.deleteSet(gv, card, freq, sIdx);
		TestUtil.printGlobalVariableStatus(gv);

		// s1 = "Sc";
		// s1Idx = sLIL.get(s1);
		s1Idx = 3;
		subSetIdx = Util.getSubset(gv, card);
		Assert.assertEquals(s1Idx, subSetIdx);
	}

	//@Ignore
	@Test
	public void testTransferGVIntoMMParam() {
		log.debug(TestUtil.FUNCTION_SEP);
		GlobalVariable<String, String> gv = TestUtil.getTC2Rep();

		//MSC4<String, String> msc = new MSC4<String, String>();
		int[] card = gv.getCard();
		Map<Integer, List<Integer>> g = Util.transferGVIntoMMParam(gv, card);
		Assert.assertTrue(g.get(1).contains(2));
		Assert.assertTrue(g.get(1).contains(3));
		Assert.assertTrue(g.get(1).contains(4));
		Assert.assertTrue(g.get(2).contains(1));
		Assert.assertTrue(g.get(2).contains(3));
		Assert.assertTrue(g.get(2).contains(4));
		Assert.assertTrue(g.get(3).contains(1));
		Assert.assertTrue(g.get(3).contains(2));
		Assert.assertTrue(g.get(4).contains(1));
		Assert.assertTrue(g.get(4).contains(2));
		Assert.assertTrue(g.get(5).contains(6));
		Assert.assertTrue(g.get(6).contains(5));
		Assert.assertTrue(g.get(6).contains(7));
		Assert.assertTrue(g.get(7).contains(6));

	}
	
	@Test
	public void testGetBatchNum(){
		String batchNum=Util.getBatchNum();
		System.out.println(batchNum);
	}

}
