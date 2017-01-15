package au.edu.cdu.semiexact.exact;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import au.edu.cdu.semiexact.TestUtil;
import au.edu.cdu.semiexact.io.FileOperation;
import au.edu.cdu.semiexact.util.ConstantValue;
import au.edu.cdu.semiexact.util.GlobalVariable;
import au.edu.cdu.semiexact.util.LogUtil;
import au.edu.cdu.semiexact.util.Util;

/**
 * test class for msc4
 *
 */
public class MSC4Test {
	private static Logger log = LogUtil.getLogger(MSC4Test.class);

	// //@Ignore
	// @Test
	// public void testDecreaseElementFrequency1() {
	//
	// GlobalVariable<String, String> gv = TestUtil.getTC1Rep();
	//
	// testDecreaseElementFrequency(gv);
	//
	// }
	//
	// //@Ignore
	// @Test
	// public void testDecreaseElementFrequency2() throws IOException {
	//
	// GlobalVariable<String, String> gv = TestUtil.getTC1RepFile();
	//
	// testDecreaseElementFrequency(gv);
	//
	// }
	//
	// private void testDecreaseElementFrequency(GlobalVariable<String, String>
	// gv) {
	// log.debug(TestUtil.FUNCTION_SEP);
	// TestUtil.printGlobalVariableStatus(gv);
	// // Map<String, Integer> eLIL = gv.geteLIL();
	// int[][] eAL = gv.geteAL();
	// int[][] eIM = gv.geteIM();
	//
	// // Map<String, Integer> sLIL = gv.getsLIL();
	//
	// int[] freq = gv.getFreq();
	//
	// MSC4<String, String> msc = new MSC4<String, String>();
	//
	// // String eToDec = "d";
	// // String sToDel = "Se";
	// //
	// // int eToDecIdx = eLIL.get(eToDec);
	// // int sToDelIdx = sLIL.get(sToDel);
	// int eToDecIdx = 4;
	// int sToDelIdx = 5;
	//
	// int freqB4 = freq[eToDecIdx];
	// int eIMSToDelCellValB4 = eIM[sToDelIdx][eToDecIdx];
	// int sToExchIdx = eAL[eToDecIdx][freqB4];
	//
	// int eIMCell2B4 = eIM[eAL[eToDecIdx][freqB4]][eToDecIdx];
	//
	// msc.decreaseElementFrequency(gv, eToDecIdx, sToDelIdx);
	// TestUtil.printGlobalVariableStatus(gv);
	//
	// Assert.assertEquals(eAL[eToDecIdx][eIMSToDelCellValB4], sToExchIdx);
	// Assert.assertEquals(eAL[eToDecIdx][freqB4], sToDelIdx);
	// Assert.assertEquals(eIM[sToExchIdx][eToDecIdx], eIMSToDelCellValB4);
	// Assert.assertEquals(eIM[sToDelIdx][eToDecIdx], eIMCell2B4);
	// Assert.assertEquals(freqB4 - 1, freq[eToDecIdx]);
	//
	// // eToDec = "f";
	// // sToDel = "Se";
	// //
	// // eToDecIdx = eLIL.get(eToDec);
	// // sToDelIdx = sLIL.get(sToDel);
	//
	// eToDecIdx = 6;
	// sToDelIdx = 5;
	//
	// freqB4 = freq[eToDecIdx];
	// eIMSToDelCellValB4 = eIM[sToDelIdx][eToDecIdx];
	// sToExchIdx = eAL[eToDecIdx][freqB4];
	//
	// eIMCell2B4 = eIM[eAL[eToDecIdx][freqB4]][eToDecIdx];
	// msc.decreaseElementFrequency(gv, eToDecIdx, sToDelIdx);
	// TestUtil.printGlobalVariableStatus(gv);
	//
	// Assert.assertEquals(gv.geteAL()[eToDecIdx][eIMSToDelCellValB4],
	// sToExchIdx);
	// Assert.assertEquals(gv.geteAL()[eToDecIdx][freqB4], sToDelIdx);
	// Assert.assertEquals(gv.geteIM()[sToExchIdx][eToDecIdx],
	// eIMSToDelCellValB4);
	// Assert.assertEquals(gv.geteIM()[sToDelIdx][eToDecIdx], eIMCell2B4);
	// Assert.assertEquals(freqB4 - 1, freq[eToDecIdx]);
	// }

	@Ignore
	@Test
	public void testDeleteSeta1() {

		GlobalVariable<String, String> gv = TestUtil.getTC1Rep();

		testDeleteSeta(gv);

	}

	@Ignore
	@Test
	public void testDeleteSeta2() throws IOException {

		GlobalVariable<String, String> gv = TestUtil.getTC1RepFile();

		testDeleteSeta(gv);

	}

	private void testDeleteSeta(GlobalVariable<String, String> gv) {
		log.debug(TestUtil.FUNCTION_SEP);
		TestUtil.printGlobalVariableStatus(gv);
		MSC4<String, String> msc = new MSC4<String, String>();

		// String sToDel = "Sd";

		int[] card = gv.getCard();
		int[] freq = gv.getFreq();

		int sActCount = card[0];
		// Map<String, Integer> sLIL = gv.getsLIL();
		int[] sIL = gv.getsIL();
		// int sToDelIdx = sLIL.get(sToDel);
		int sToDelIdx = 4;
		int sToExchIdx = sIL[sActCount];

		msc.deleteSet(gv, card, freq, sToDelIdx);
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

		msc.deleteSet(gv, card, freq, sToDelIdx);
		TestUtil.printGlobalVariableStatus(gv);

		Assert.assertEquals(sActCount - 1, card[0]);

		sIL = gv.getsIL();

		Assert.assertEquals(0, card[sToDelIdx]);
		Assert.assertEquals(sToDelIdx, sIL[sActCount]);
		Assert.assertEquals(sToExchIdx, sIL[sToDelIdx]);
		Assert.assertEquals(sActCount, sIL[sToDelIdx]);
		Assert.assertEquals(sToDelIdx, sIL[sToExchIdx]);
	}

	// //@Ignore
	// @Test
	// public void testDecreaseSetCardinality1() {
	//
	// GlobalVariable<String, String> gv = TestUtil.getTC1Rep();
	// testDecreaseSetCardinality(gv);
	//
	// }
	//
	// //@Ignore
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
	// msc.decreaseSetCardinality(gv, sToDecIdx, eToDelIdx);
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
	// msc.decreaseSetCardinality(gv, sToDecIdx, eToDelIdx);
	// TestUtil.printGlobalVariableStatus(gv);
	// Assert.assertEquals(gv.getsAL()[sToDecIdx][sIMEToDelCellValB4],
	// eToExchIdx);
	// Assert.assertEquals(gv.getsAL()[sToDecIdx][cardB4], eToDelIdx);
	// Assert.assertEquals(gv.getsIM()[eToExchIdx][sToDecIdx],
	// sIMEToDelCellValB4);
	// Assert.assertEquals(gv.getsIM()[eToDelIdx][sToDecIdx], sIMCell2B4);
	// Assert.assertEquals(cardB4 - 1, gv.getCard()[sToDecIdx]);
	// }

	@Ignore
	@Test
	public void testDeleteElement1() {

		GlobalVariable<String, String> gv = TestUtil.getTC1Rep();
		testDeleteElement(gv);

	}

	@Ignore
	@Test
	public void testDeleteElement2() throws IOException {

		GlobalVariable<String, String> gv = TestUtil.getTC1RepFile();
		testDeleteElement(gv);

	}

	private void testDeleteElement(GlobalVariable<String, String> gv) {
		log.debug(TestUtil.FUNCTION_SEP);
		TestUtil.printGlobalVariableStatus(gv);

		MSC4<String, String> msc = new MSC4<String, String>();

		// String eToDel = "e";

		int[] eIL = gv.geteIL();
		int[] card = gv.getCard();
		int[] freq = gv.getFreq();

		int eActCount =freq[0];
		// Map<String, Integer> eLIL = gv.geteLIL();

		// int eToDelIdx = eLIL.get(eToDel);
		int eToDelIdx = 5;

		int eToExchIdx = eIL[eActCount];

		msc.deleteElement(gv, card, freq, eToDelIdx);
		TestUtil.printGlobalVariableStatus(gv);

		Assert.assertEquals(eActCount - 1,freq[0]);

		int[] eL = gv.geteL();
		eIL = gv.geteIL();
		// eLIL = gv.geteLIL();

		Assert.assertEquals(0, freq[eToDelIdx]);
		Assert.assertEquals(eToDelIdx, eL[eActCount]);
		Assert.assertEquals(eToExchIdx, eL[eToDelIdx]);
		Assert.assertEquals(eActCount, eIL[eToDelIdx]);
		Assert.assertEquals(eToDelIdx, eIL[eToExchIdx]);

		// eToDel = "a";

		eActCount =freq[0];
		// eLIL = gv.geteLIL();
		eIL = gv.geteIL();
		// eToDelIdx = eLIL.get(eToDel);
		eToDelIdx = 1;
		eToExchIdx = eIL[eActCount];

		msc.deleteElement(gv, card, freq, eToDelIdx);
		TestUtil.printGlobalVariableStatus(gv);

		Assert.assertEquals(eActCount - 1,freq[0]);

		eL = gv.geteL();
		eIL = gv.geteIL();
		// eLIL = gv.geteLIL();

		Assert.assertEquals(0, freq[eToDelIdx]);
		Assert.assertEquals(eToDelIdx, eL[eActCount]);
		Assert.assertEquals(eToExchIdx, eL[eToDelIdx]);
		Assert.assertEquals(eActCount, eIL[eToDelIdx]);
		Assert.assertEquals(eToDelIdx, eIL[eToExchIdx]);
	}

	@Ignore
	@Test
	public void testAddSetToCover1() {

		GlobalVariable<String, String> gv = TestUtil.getTC1Rep();
		testAddSetToCover(gv);

	}

	@Ignore
	@Test
	public void testAddSetToCover2() throws IOException {

		GlobalVariable<String, String> gv = TestUtil.getTC1RepFile();
		testAddSetToCover(gv);

	}

	private void testAddSetToCover(GlobalVariable<String, String> gv) {
		log.debug(TestUtil.FUNCTION_SEP);
		TestUtil.printGlobalVariableStatus(gv);

		MSC4<String, String> msc = new MSC4<String, String>();

		// Map<String, Integer> sLIL = gv.getsLIL();
		int[] card = gv.getCard();
		int[] freq = gv.getFreq();

		// String sToAdd = "Se"; 
		int sActCount = card[0];
		// int sToAddIdx = sLIL.get(sToAdd);
		int sToAddIdx = 5;
		msc.addSetToCover(gv, card, freq, sToAddIdx);
		TestUtil.printGlobalVariableStatus(gv);
		Assert.assertEquals(sActCount - 1, card[0]);

		// sToAdd = "Sa";
		sActCount = card[0];
		// sToAddIdx = sLIL.get(sToAdd);
		sToAddIdx = 1;

		msc.addSetToCover(gv, card, freq, sToAddIdx);
		TestUtil.printGlobalVariableStatus(gv);
		Assert.assertEquals(sActCount - 1, card[0]);
	}

	@Ignore
	@Test
	public void testAProcessManuallyByPersonSimulation1() {

		GlobalVariable<String, String> gv = TestUtil.getTC1Rep();
		testAProcessManuallyByPersonSimulation(gv);
	}

	@Ignore
	@Test
	public void testAProcessManuallyByPersonSimulation2() throws IOException {

		GlobalVariable<String, String> gv = TestUtil.getTC1RepFile();
		testAProcessManuallyByPersonSimulation(gv);
	}

	private void testAProcessManuallyByPersonSimulation(GlobalVariable<String, String> gv) {
		log.debug(TestUtil.FUNCTION_SEP);
		TestUtil.printGlobalVariableStatus(gv);

		MSC4<String, String> msc = new MSC4<String, String>();

		// Map<String, Integer> sLIL = gv.getsLIL();
		int[] card = gv.getCard();
		int[] freq = gv.getFreq();

		// 1. delete sf
		// String s = "Sf";
		// int sIdx = sLIL.get(s);
		int sIdx = 6;

		msc.deleteSet(gv, card, freq, sIdx);
		TestUtil.printGlobalVariableStatus(gv);
		// 2. add se
		// s = "Se";
		// sIdx = sLIL.get(s);
		sIdx = 5;

		msc.addSetToCover(gv, card, freq, sIdx);
		TestUtil.printGlobalVariableStatus(gv);
		// 3. delete sb
		// s = "Sb";
		// sIdx = sLIL.get(s);
		sIdx = 2;

		msc.deleteSet(gv, card, freq, sIdx);
		TestUtil.printGlobalVariableStatus(gv);
		// 4. delete sc
		// s = "Sc";
		// sIdx = sLIL.get(s);
		sIdx = 3;

		msc.deleteSet(gv, card, freq, sIdx);
		TestUtil.printGlobalVariableStatus(gv);
		// 5. delete sd
		// s = "Sd";
		// sIdx = sLIL.get(s);
		sIdx = 4;
		msc.deleteSet(gv, card, freq, sIdx);
		TestUtil.printGlobalVariableStatus(gv);
		// 6. add sa
		// s = "Sa";
		// sIdx = sLIL.get(s);
		sIdx = 1;
		msc.addSetToCover(gv, card, freq, sIdx);
		TestUtil.printGlobalVariableStatus(gv);
	}

	@Ignore
	@Test
	public void testDeleteSetb1() {

		GlobalVariable<String, String> gv = TestUtil.getTC1Rep();
		testDeletsetb(gv);

	}

	@Ignore
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

		MSC4<String, String> msc = new MSC4<String, String>();

		// int sIdx = sLIL.get("Sd");
		int sIdx = 4;

		int selectSetIdx = Util.getMaxCardinalitySetIndex(gv, card[0]);
		Assert.assertEquals(sIdx, selectSetIdx);

		// delete sf
		// String s = "Sf";
		// sIdx = sLIL.get(s);
		sIdx = 6;

		msc.deleteSet(gv, card, freq, sIdx);
		TestUtil.printGlobalVariableStatus(gv);
		// add se
		// s = "Se";
		// sIdx = sLIL.get(s);
		sIdx = 5;

		msc.addSetToCover(gv, card, freq, sIdx);
		TestUtil.printGlobalVariableStatus(gv);

		// sIdx = sLIL.get("Sa");
		sIdx = 1;

		selectSetIdx = Util.getMaxCardinalitySetIndex(gv, card[0]);
		Assert.assertEquals(sIdx, selectSetIdx);
	}

	@Ignore
	@Test
	public void testGetSetOfFrequencyOneElement1() {

		GlobalVariable<String, String> gv = TestUtil.getTC1Rep();
		testGetSetOfFrequencyOneElement(gv);
	}

	@Ignore
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

		MSC4<String, String> msc = new MSC4<String, String>();

		// delete sf
		// String s = "Sf";
		// int sIdx = sLIL.get(s);
		int sIdx = 6;

		msc.deleteSet(gv, card, freq, sIdx);
		TestUtil.printGlobalVariableStatus(gv);

		// sIdx = sLIL.get("Se");
		sIdx = 5;

		int selectSetIdx = msc.getSetOfFrequencyOneElement(gv, freq);
		Assert.assertEquals(sIdx, selectSetIdx);

		// add se
		// s = "Se";
		// sIdx = sLIL.get(s);
		sIdx = 5;

		msc.addSetToCover(gv, card, freq, sIdx);
		TestUtil.printGlobalVariableStatus(gv);

		// delete sb
		// s = "Sb";
		// sIdx = sLIL.get(s);
		sIdx = 2;

		msc.deleteSet(gv, card, freq, sIdx);
		TestUtil.printGlobalVariableStatus(gv);
		// delete sc
		// s = "Sc";
		// sIdx = sLIL.get(s);
		sIdx = 3;
		msc.deleteSet(gv, card, freq, sIdx);
		TestUtil.printGlobalVariableStatus(gv);
		// delete sd
		// s = "Sd";
		// sIdx = sLIL.get(s);
		sIdx = 4;
		msc.deleteSet(gv, card, freq, sIdx);
		TestUtil.printGlobalVariableStatus(gv);

		// sIdx = sLIL.get("Sa");
		sIdx = 1;
		selectSetIdx = msc.getSetOfFrequencyOneElement(gv, freq);
		Assert.assertEquals(sIdx, selectSetIdx);
	}

	@Ignore
	@Test
	public void testIs1Subset21() {

		GlobalVariable<String, String> gv = TestUtil.getTC1Rep();
		testIs1Subset2(gv);

	}

	@Ignore
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
		MSC4<String, String> msc = new MSC4<String, String>();

		// String s1 = "Sf";
		// String s2 = "Se";
		// int s1Idx = sLIL.get(s1);
		// int s2Idx = sLIL.get(s2);

		int s1Idx = 6;
		int s2Idx = 5;

		Assert.assertTrue(msc.is1Subset2(gv, card, s1Idx, s2Idx));
		Assert.assertFalse(msc.is1Subset2(gv, card, s2Idx, s1Idx));

		// delete sf
		// String s = "Sf";
		// int sIdx = sLIL.get(s);
		int sIdx = 6;
		msc.deleteSet(gv, card, freq, sIdx);

		TestUtil.printGlobalVariableStatus(gv);

		// add se
		// s = "Se";
		// sIdx = sLIL.get(s);
		sIdx = 5;

		msc.addSetToCover(gv, card, freq, sIdx);
		TestUtil.printGlobalVariableStatus(gv);

		// s1 = "Sb";
		// s2 = "Sa";
		// s1Idx = sLIL.get(s1);
		// s2Idx = sLIL.get(s2);
		s1Idx = 2;
		s2Idx = 1;

		Assert.assertTrue(msc.is1Subset2(gv, card, s1Idx, s2Idx));
		Assert.assertFalse(msc.is1Subset2(gv, card, s2Idx, s1Idx));
	}

	@Ignore
	@Test
	public void testGetSubset1() {

		GlobalVariable<String, String> gv = TestUtil.getTC1Rep();
		testGetSubset(gv);

	}

	@Ignore
	@Test
	public void testGetSubset2() throws IOException {

		GlobalVariable<String, String> gv = TestUtil.getTC1RepFile();
		testGetSubset(gv);

	}

	private void testGetSubset(GlobalVariable<String, String> gv) {
		log.debug(TestUtil.FUNCTION_SEP);
		TestUtil.printGlobalVariableStatus(gv);

		MSC4<String, String> msc = new MSC4<String, String>();

		// String s1 = "Sf";
		// Map<String, Integer> sLIL = gv.getsLIL();
		// int s1Idx = sLIL.get(s1);
		int[] card = gv.getCard();
		int[] freq = gv.getFreq();

		int s1Idx = 6;

		int subSetIdx = msc.getSubset(gv, card);
		Assert.assertEquals(s1Idx, subSetIdx);

		// delete sf
		// String s = "Sf";
		// int sIdx = sLIL.get(s);
		int sIdx = 6;

		msc.deleteSet(gv, card, freq, sIdx);
		TestUtil.printGlobalVariableStatus(gv);

		// add se
		// s = "Se";
		// sIdx = sLIL.get(s);
		sIdx = 5;

		msc.addSetToCover(gv, card, freq, sIdx);
		TestUtil.printGlobalVariableStatus(gv);

		// s1 = "Sb";
		// s1Idx = sLIL.get(s1);
		s1Idx = 2;

		subSetIdx = msc.getSubset(gv, card);
		Assert.assertEquals(s1Idx, subSetIdx);

		// delete sb
		// s = "Sb";
		// sIdx = sLIL.get(s);
		sIdx = 2;
		msc.deleteSet(gv, card, freq, sIdx);
		TestUtil.printGlobalVariableStatus(gv);

		// s1 = "Sd";
		// s1Idx = sLIL.get(s1);
		s1Idx = 4;

		subSetIdx = msc.getSubset(gv, card);
		Assert.assertEquals(s1Idx, subSetIdx);

		// delete sd
		// s = "Sd";
		// sIdx = sLIL.get(s);
		sIdx = 4;

		msc.deleteSet(gv, card, freq, sIdx);
		TestUtil.printGlobalVariableStatus(gv);

		// s1 = "Sc";
		// s1Idx = sLIL.get(s1);
		s1Idx = 3;
		subSetIdx = msc.getSubset(gv, card);
		Assert.assertEquals(s1Idx, subSetIdx);
	}

	@Ignore
	@Test
	public void testReadGraph() throws IOException {

		String fileName = TestUtil.getCurrentPath() + "/src/test/resources/sample1.txt";

		FileOperation fo = new FileOperation();

		GlobalVariable<String, String> gv = fo.readGraphByEdgePair(fileName);
		TestUtil.printGlobalVariableStatus(gv);
	}

	@Ignore
	@Test
	public void testTransferGVIntoMMParam() {
		log.debug(TestUtil.FUNCTION_SEP);
		GlobalVariable<String, String> gv = TestUtil.getTC2Rep();

		MSC4<String, String> msc = new MSC4<String, String>();
		int[] card = gv.getCard();
		Map<Integer, List<Integer>> g = msc.transferGVIntoMMParam(gv, card);
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

	@Ignore
	@Test
	public void testBuildMaxMatching1() {

		GlobalVariable<String, String> gv = TestUtil.getTC2Rep();

		testBuildMaxMatching(gv);

	}

	private void testBuildMaxMatching(GlobalVariable<String, String> gv) {
		log.debug(TestUtil.FUNCTION_SEP);
		MSC4<String, String> msc = new MSC4<String, String>();
		int[] card = gv.getCard();
		int size = msc.buildMaxMatching(gv, card);
		Assert.assertEquals(3, size);
		int[] mate = gv.getMate();
		Assert.assertEquals(3, mate[1]);
		Assert.assertEquals(1, mate[3]);
		Assert.assertEquals(4, mate[2]);
		Assert.assertEquals(2, mate[4]);
		Assert.assertEquals(6, mate[5]);
		Assert.assertEquals(5, mate[6]);
		Assert.assertEquals(ConstantValue.MATE_EXPOSE, mate[7]);
	}

	@Ignore
	@Test
	public void testPreProcess1() {

		GlobalVariable<String, String> gv = TestUtil.getTC1Rep();

		testPreProcess(gv);
	}

	@Ignore
	@Test
	public void testPreProcess2() throws IOException {

		GlobalVariable<String, String> gv = TestUtil.getTC1RepFile();

		testPreProcess(gv);
	}

	private void testPreProcess(GlobalVariable<String, String> gv) {
		log.debug(TestUtil.FUNCTION_SEP);
		MSC4<String, String> msc = new MSC4<String, String>();
		int[] card = gv.getCard();
		int[] freq = gv.getFreq();
		msc.preProcess(gv, card, freq);

		Assert.assertEquals(2, gv.getSolCount());
	}

	@Ignore
	@Test
	public void testKHighest1() {

		GlobalVariable<String, String> gv = TestUtil.getTC1Rep();

		testKHighest(gv);

	}

	@Ignore
	@Test
	public void testKHighest2() throws IOException {

		GlobalVariable<String, String> gv = TestUtil.getTC1RepFile();

		testKHighest(gv);

	}

	private void testKHighest(GlobalVariable<String, String> gv) {
		log.debug(TestUtil.FUNCTION_SEP);
		MSC4<String, String> msc = new MSC4<String, String>();
		int[] card = gv.getCard();
		int maxCardSet = Util.getMaxCardinalitySetIndex(gv, card[0]);
		
		int maxCard = card[maxCardSet];

		int kMax = msc.kHighest(gv, card, maxCard);
		Assert.assertEquals(16, kMax);
	}

	//@Ignore
	@Test
	public void testBranch() {
		log.debug(TestUtil.FUNCTION_SEP);
		GlobalVariable<String, String> gv = TestUtil.getTC2Rep();

		testBranch(gv);
	}

	private void testBranch(GlobalVariable<String, String> gv) {
		MSC4<String, String> msc = new MSC4<String, String>();

		msc.branch(gv);
		Assert.assertEquals(4, gv.getBestSolCount());
	}

}