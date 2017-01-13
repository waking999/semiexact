package au.edu.cdu.semiexact.exact;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import au.edu.cdu.semiexact.TestUtil;
import au.edu.cdu.semiexact.io.FileOperation;
import au.edu.cdu.semiexact.util.GlobalVariable;
import au.edu.cdu.semiexact.util.LogUtil;
import au.edu.cdu.semiexact.util.Util;

/**
 * test class for msc4 
 *
 */
public class MSC4Test {
	private static Logger log = LogUtil.getLogger(MSC4Test.class);

	
	

	//@Ignore
	@Test
	public void testDecreaseElementFrequency() {
		log.debug(TestUtil.FUNCTION_SEP);
		GlobalVariable<String, String> gv = TestUtil.getTestCase1ForGraphRepresentation();
		TestUtil.printGlobalVariableStatus(gv);

		Map<String, Integer> eLIL = gv.geteLIL();
		int[][] eAL = gv.geteAL();
		int[][] eIM = gv.geteIM();

		Map<String, Integer> sLIL = gv.getsLIL();

		int[] freq = gv.getFreq();

		MSC4<String, String> msc = new MSC4<String, String>();

		String eToDec = "d";
		String sToDel = "Se";

		int eToDecIdx = eLIL.get(eToDec);
		int sToDelIdx = sLIL.get(sToDel);

		int freqB4 = freq[eToDecIdx];
		int eIMSToDelCellValB4 = eIM[sToDelIdx][eToDecIdx];
		int sToExchIdx = eAL[eToDecIdx][freqB4 - 1];

		int eIMCell2B4 = eIM[eAL[eToDecIdx][freqB4 - 1]][eToDecIdx];

		msc.decreaseElementFrequency(gv, eToDecIdx, sToDelIdx);
		TestUtil.printGlobalVariableStatus(gv);

		Assert.assertEquals(eAL[eToDecIdx][eIMSToDelCellValB4], sToExchIdx);
		Assert.assertEquals(eAL[eToDecIdx][freqB4 - 1], sToDelIdx);
		Assert.assertEquals(eIM[sToExchIdx][eToDecIdx], eIMSToDelCellValB4);
		Assert.assertEquals(eIM[sToDelIdx][eToDecIdx], eIMCell2B4);
		Assert.assertEquals(freqB4 - 1, freq[eToDecIdx]);

		eToDec = "f";
		sToDel = "Se";

		eToDecIdx = eLIL.get(eToDec);
		sToDelIdx = sLIL.get(sToDel);

		freqB4 = freq[eToDecIdx];
		eIMSToDelCellValB4 = eIM[sToDelIdx][eToDecIdx];
		sToExchIdx = eAL[eToDecIdx][freqB4 - 1];

		eIMCell2B4 = eIM[eAL[eToDecIdx][freqB4 - 1]][eToDecIdx];
		msc.decreaseElementFrequency(gv, eToDecIdx, sToDelIdx);
		TestUtil.printGlobalVariableStatus(gv);

		Assert.assertEquals(gv.geteAL()[eToDecIdx][eIMSToDelCellValB4], sToExchIdx);
		Assert.assertEquals(gv.geteAL()[eToDecIdx][freqB4 - 1], sToDelIdx);
		Assert.assertEquals(gv.geteIM()[sToExchIdx][eToDecIdx], eIMSToDelCellValB4);
		Assert.assertEquals(gv.geteIM()[sToDelIdx][eToDecIdx], eIMCell2B4);
		Assert.assertEquals(freqB4 - 1, gv.getFreq()[eToDecIdx]);

	}

	//@Ignore
	@Test
	public void testDeleteSet() {
		log.debug(TestUtil.FUNCTION_SEP);
		GlobalVariable<String, String> gv = TestUtil.getTestCase1ForGraphRepresentation();
		TestUtil.printGlobalVariableStatus(gv);

		MSC4<String, String> msc = new MSC4<String, String>();

		String sToDel = "Sd";

		int sActCount = gv.getsActCount();
		Map<String, Integer> sLIL = gv.getsLIL();
		int[] sIL = gv.getsIL();
		int sToDelIdx = sLIL.get(sToDel);
		int sToExchIdx = sIL[sActCount - 1];

		msc.deleteSet(gv, sToDelIdx);
		TestUtil.printGlobalVariableStatus(gv);

		Assert.assertEquals(sActCount - 1, gv.getsActCount());

		sIL = gv.getsIL();

		Assert.assertEquals(0, gv.getCard()[sToDelIdx]);
		Assert.assertEquals(sToDelIdx, sIL[sActCount - 1]);
		Assert.assertEquals(sToExchIdx, sIL[sToDelIdx]);
		Assert.assertEquals(sActCount - 1, sIL[sToDelIdx]);
		Assert.assertEquals(sToDelIdx, sIL[sToExchIdx]);

		sToDel = "Sa";

		sActCount = gv.getsActCount();
		sIL = gv.getsIL();

		sToDelIdx = sLIL.get(sToDel);
		sToExchIdx = sIL[sActCount - 1];

		msc.deleteSet(gv, sToDelIdx);
		TestUtil.printGlobalVariableStatus(gv);

		Assert.assertEquals(sActCount - 1, gv.getsActCount());

		sIL = gv.getsIL();

		Assert.assertEquals(0, gv.getCard()[sToDelIdx]);
		Assert.assertEquals(sToDelIdx, sIL[sActCount - 1]);
		Assert.assertEquals(sToExchIdx, sIL[sToDelIdx]);
		Assert.assertEquals(sActCount - 1, sIL[sToDelIdx]);
		Assert.assertEquals(sToDelIdx, sIL[sToExchIdx]);

	}

	//@Ignore
	@Test
	public void testDecreaseSetCardinality() {
		log.debug(TestUtil.FUNCTION_SEP);
		GlobalVariable<String, String> gv = TestUtil.getTestCase1ForGraphRepresentation();
		TestUtil.printGlobalVariableStatus(gv);

		Map<String, Integer> sLIL = gv.getsLIL();
		int[][] sAL = gv.getsAL();
		int[][] sIM = gv.getsIM();

		Map<String, Integer> eLIL = gv.geteLIL();

		int[] card = gv.getCard();

		MSC4<String, String> msc = new MSC4<String, String>();

		String sToDec = "Se";
		String eToDel = "d";

		int sToDecIdx = sLIL.get(sToDec);
		int eToDelIdx = eLIL.get(eToDel);

		int cardB4 = card[sToDecIdx];
		int sIMEToDelCellValB4 = sIM[eToDelIdx][sToDecIdx];
		int eToExchIdx = sAL[sToDecIdx][cardB4 - 1];

		int sIMCell2B4 = sIM[sAL[sToDecIdx][cardB4 - 1]][sToDecIdx];

		msc.decreaseSetCardinality(gv, sToDecIdx, eToDelIdx);
		TestUtil.printGlobalVariableStatus(gv);
		Assert.assertEquals(sAL[sToDecIdx][sIMEToDelCellValB4], eToExchIdx);
		Assert.assertEquals(sAL[sToDecIdx][cardB4 - 1], eToDelIdx);
		Assert.assertEquals(sIM[eToExchIdx][sToDecIdx], sIMEToDelCellValB4);
		Assert.assertEquals(sIM[eToDelIdx][sToDecIdx], sIMCell2B4);
		Assert.assertEquals(cardB4 - 1, card[sToDecIdx]);

		sToDec = "Se";
		eToDel = "f";

		sToDecIdx = sLIL.get(sToDec);
		eToDelIdx = eLIL.get(eToDel);

		cardB4 = card[sToDecIdx];
		sIMEToDelCellValB4 = sIM[eToDelIdx][sToDecIdx];
		eToExchIdx = sAL[sToDecIdx][cardB4 - 1];

		sIMCell2B4 = sIM[sAL[sToDecIdx][cardB4 - 1]][sToDecIdx];
		msc.decreaseSetCardinality(gv, sToDecIdx, eToDelIdx);
		TestUtil.printGlobalVariableStatus(gv);
		Assert.assertEquals(gv.getsAL()[sToDecIdx][sIMEToDelCellValB4], eToExchIdx);
		Assert.assertEquals(gv.getsAL()[sToDecIdx][cardB4 - 1], eToDelIdx);
		Assert.assertEquals(gv.getsIM()[eToExchIdx][sToDecIdx], sIMEToDelCellValB4);
		Assert.assertEquals(gv.getsIM()[eToDelIdx][sToDecIdx], sIMCell2B4);
		Assert.assertEquals(cardB4 - 1, gv.getCard()[sToDecIdx]);

	}

	//@Ignore
	@Test
	public void testDeleteElement() {
		log.debug(TestUtil.FUNCTION_SEP);
		GlobalVariable<String, String> gv = TestUtil.getTestCase1ForGraphRepresentation();
		TestUtil.printGlobalVariableStatus(gv);

		MSC4<String, String> msc = new MSC4<String, String>();

		String eToDel = "e";

		int[] eIL = gv.geteIL();

		int eActCount = gv.geteActCount();
		Map<String, Integer> eLIL = gv.geteLIL();

		int eToDelIdx = eLIL.get(eToDel);
		int eToExchIdx = eIL[eActCount - 1];

		msc.deleteElement(gv, eToDelIdx);
		TestUtil.printGlobalVariableStatus(gv);

		Assert.assertEquals(eActCount - 1, gv.geteActCount());

		int[] eL = gv.geteL();
		eIL = gv.geteIL();
		eLIL = gv.geteLIL();

		Assert.assertEquals(0, gv.getFreq()[eToDelIdx]);
		Assert.assertEquals(eToDelIdx, eL[eActCount - 1]);
		Assert.assertEquals(eToExchIdx, eL[eToDelIdx]);
		Assert.assertEquals(eActCount - 1, eIL[eToDelIdx]);
		Assert.assertEquals(eToDelIdx, eIL[eToExchIdx]);

		eToDel = "a";

		eActCount = gv.geteActCount();
		eLIL = gv.geteLIL();
		eIL = gv.geteIL();
		eToDelIdx = eLIL.get(eToDel);
		eToExchIdx = eIL[eActCount - 1];

		msc.deleteElement(gv, eToDelIdx);
		TestUtil.printGlobalVariableStatus(gv);

		Assert.assertEquals(eActCount - 1, gv.geteActCount());

		eL = gv.geteL();
		eIL = gv.geteIL();
		eLIL = gv.geteLIL();

		Assert.assertEquals(0, gv.getFreq()[eToDelIdx]);
		Assert.assertEquals(eToDelIdx, eL[eActCount - 1]);
		Assert.assertEquals(eToExchIdx, eL[eToDelIdx]);
		Assert.assertEquals(eActCount - 1, eIL[eToDelIdx]);
		Assert.assertEquals(eToDelIdx, eIL[eToExchIdx]);

	}

	//@Ignore
	@Test
	public void testAddSetToCover() {
		log.debug(TestUtil.FUNCTION_SEP);
		GlobalVariable<String, String> gv = TestUtil.getTestCase1ForGraphRepresentation();
		TestUtil.printGlobalVariableStatus(gv);

		MSC4<String, String> msc = new MSC4<String, String>();

		Map<String, Integer> sLIL = gv.getsLIL();

		String sToAdd = "Se";
		int sActCount = gv.getsActCount();
		int sToAddIdx = sLIL.get(sToAdd);
		msc.addSetToCover(gv, sToAddIdx);
		TestUtil.printGlobalVariableStatus(gv);
		Assert.assertEquals(sActCount - 1, gv.getsActCount());

		sToAdd = "Sa";
		sActCount = gv.getsActCount();
		sToAddIdx = sLIL.get(sToAdd);
		msc.addSetToCover(gv, sToAddIdx);
		TestUtil.printGlobalVariableStatus(gv);
		Assert.assertEquals(sActCount - 1, gv.getsActCount());

	}

	//@Ignore
	@Test
	public void testAProcessManuallyByPersonSimulation() {
		log.debug(TestUtil.FUNCTION_SEP);
		GlobalVariable<String, String> gv = TestUtil.getTestCase1ForGraphRepresentation();
		TestUtil.printGlobalVariableStatus(gv);

		MSC4<String, String> msc = new MSC4<String, String>();

		Map<String, Integer> sLIL = gv.getsLIL();

		// 1. delete sf
		String s = "Sf";
		int sIdx = sLIL.get(s);
		msc.deleteSet(gv, sIdx);
		TestUtil.printGlobalVariableStatus(gv);
		// 2. add se
		s = "Se";
		sIdx = sLIL.get(s);
		msc.addSetToCover(gv, sIdx);
		TestUtil.printGlobalVariableStatus(gv);
		// 3. delete sb
		s = "Sb";
		sIdx = sLIL.get(s);
		msc.deleteSet(gv, sIdx);
		TestUtil.printGlobalVariableStatus(gv);
		// 4. delete sc
		s = "Sc";
		sIdx = sLIL.get(s);
		msc.deleteSet(gv, sIdx);
		TestUtil.printGlobalVariableStatus(gv);
		// 5. delete sd
		s = "Sd";
		sIdx = sLIL.get(s);
		msc.deleteSet(gv, sIdx);
		TestUtil.printGlobalVariableStatus(gv);
		// 6. add sa
		s = "Sa";
		sIdx = sLIL.get(s);
		msc.addSetToCover(gv, sIdx);
		TestUtil.printGlobalVariableStatus(gv);
	}

	//@Ignore
	@Test
	public void testDeleteSet1() {
		log.debug(TestUtil.FUNCTION_SEP);
		GlobalVariable<String, String> gv = TestUtil.getTestCase1ForGraphRepresentation();
		TestUtil.printGlobalVariableStatus(gv);

		Map<String, Integer> sLIL = gv.getsLIL();

		MSC4<String, String> msc = new MSC4<String, String>();

		int sIdx = sLIL.get("Sd");
		int selectSetIdx = Util.getMaxCardinalitySetIndex(gv);
		Assert.assertEquals(sIdx, selectSetIdx);

		// delete sf
		String s = "Sf";
		sIdx = sLIL.get(s);
		msc.deleteSet(gv, sIdx);
		TestUtil.printGlobalVariableStatus(gv);
		// add se
		s = "Se";
		sIdx = sLIL.get(s);
		msc.addSetToCover(gv, sIdx);
		TestUtil.printGlobalVariableStatus(gv);

		sIdx = sLIL.get("Sa");
		selectSetIdx = Util.getMaxCardinalitySetIndex(gv);
		Assert.assertEquals(sIdx, selectSetIdx);

	}

	//@Ignore
	@Test
	public void testGetSetOfFrequencyOneElement() {
		log.debug(TestUtil.FUNCTION_SEP);
		GlobalVariable<String, String> gv = TestUtil.getTestCase1ForGraphRepresentation();
		TestUtil.printGlobalVariableStatus(gv);

		Map<String, Integer> sLIL = gv.getsLIL();

		MSC4<String, String> msc = new MSC4<String, String>();

		// delete sf
		String s = "Sf";
		int sIdx = sLIL.get(s);
		msc.deleteSet(gv, sIdx);
		TestUtil.printGlobalVariableStatus(gv);

		sIdx = sLIL.get("Se");
		int selectSetIdx = msc.getSetOfFrequencyOneElement(gv);
		Assert.assertEquals(sIdx, selectSetIdx);

		// add se
		s = "Se";
		sIdx = sLIL.get(s);
		msc.addSetToCover(gv, sIdx);
		TestUtil.printGlobalVariableStatus(gv);

		// delete sb
		s = "Sb";
		sIdx = sLIL.get(s);
		msc.deleteSet(gv, sIdx);
		TestUtil.printGlobalVariableStatus(gv);
		// delete sc
		s = "Sc";
		sIdx = sLIL.get(s);
		msc.deleteSet(gv, sIdx);
		TestUtil.printGlobalVariableStatus(gv);
		// delete sd
		s = "Sd";
		sIdx = sLIL.get(s);
		msc.deleteSet(gv, sIdx);
		TestUtil.printGlobalVariableStatus(gv);

		sIdx = sLIL.get("Sa");
		selectSetIdx = msc.getSetOfFrequencyOneElement(gv);
		Assert.assertEquals(sIdx, selectSetIdx);
	}

	//@Ignore
	@Test
	public void testIs1Subset2() {
		log.debug(TestUtil.FUNCTION_SEP);
		GlobalVariable<String, String> gv = TestUtil.getTestCase1ForGraphRepresentation();
		TestUtil.printGlobalVariableStatus(gv);
		Map<String, Integer> sLIL = gv.getsLIL();

		MSC4<String, String> msc = new MSC4<String, String>();

		String s1 = "Sf";
		String s2 = "Se";
		int s1Idx = sLIL.get(s1);
		int s2Idx = sLIL.get(s2);
		Assert.assertTrue(msc.is1Subset2(gv, s1Idx, s2Idx));
		Assert.assertFalse(msc.is1Subset2(gv, s2Idx, s1Idx));

		// delete sf
		String s = "Sf";
		int sIdx = sLIL.get(s);
		msc.deleteSet(gv, sIdx);
		TestUtil.printGlobalVariableStatus(gv);

		// add se
		s = "Se";
		sIdx = sLIL.get(s);
		msc.addSetToCover(gv, sIdx);
		TestUtil.printGlobalVariableStatus(gv);

		s1 = "Sb";
		s2 = "Sa";
		s1Idx = sLIL.get(s1);
		s2Idx = sLIL.get(s2);
		Assert.assertTrue(msc.is1Subset2(gv, s1Idx, s2Idx));
		Assert.assertFalse(msc.is1Subset2(gv, s2Idx, s1Idx));

	}

	//@Ignore
	@Test
	public void testGetSubset() {
		log.debug(TestUtil.FUNCTION_SEP);
		GlobalVariable<String, String> gv = TestUtil.getTestCase1ForGraphRepresentation();
		TestUtil.printGlobalVariableStatus(gv);

		MSC4<String, String> msc = new MSC4<String, String>();

		String s1 = "Sf";
		Map<String, Integer> sLIL = gv.getsLIL();
		int s1Idx = sLIL.get(s1);
		int subSetIdx = msc.getSubset(gv);
		Assert.assertEquals(s1Idx, subSetIdx);

		// delete sf
		String s = "Sf";
		int sIdx = sLIL.get(s);
		msc.deleteSet(gv, sIdx);
		TestUtil.printGlobalVariableStatus(gv);

		// add se
		s = "Se";
		sIdx = sLIL.get(s);
		msc.addSetToCover(gv, sIdx);
		TestUtil.printGlobalVariableStatus(gv);

		s1 = "Sb";
		s1Idx = sLIL.get(s1);
		subSetIdx = msc.getSubset(gv);
		Assert.assertEquals(s1Idx, subSetIdx);

		// delete sb
		s = "Sb";
		sIdx = sLIL.get(s);
		msc.deleteSet(gv, sIdx);
		TestUtil.printGlobalVariableStatus(gv);

		s1 = "Sd";
		s1Idx = sLIL.get(s1);
		subSetIdx = msc.getSubset(gv);
		Assert.assertEquals(s1Idx, subSetIdx);

		// delete sd
		s = "Sd";
		sIdx = sLIL.get(s);
		msc.deleteSet(gv, sIdx);
		TestUtil.printGlobalVariableStatus(gv);

		s1 = "Sc";
		s1Idx = sLIL.get(s1);
		subSetIdx = msc.getSubset(gv);
		Assert.assertEquals(s1Idx, subSetIdx);

	}

	//@Ignore
	@Test
	public void testReadGraph() throws IOException {

		String fileName = TestUtil.getCurrentPath() + "/src/test/resources/sample.txt";

		FileOperation fo = new FileOperation();

		GlobalVariable<String, String> gv = fo.readGraphByEdgePair(fileName);
		TestUtil.printGlobalVariableStatus(gv);
	}

	
	//@Ignore
	@Test
	public void testTransferGVIntoMMParam() {
		log.debug(TestUtil.FUNCTION_SEP);
		GlobalVariable<String, String> gv = TestUtil.getTestCase2ForGraphRepresentation();

		MSC4<String, String> msc = new MSC4<String, String>();
		Map<Integer, List<Integer>> g = msc.transferGVIntoMMParam(gv);
		Assert.assertTrue(g.get(0).contains(1));

	}

	//@Ignore
	@Test
	public void testBuildMaxMatching() {
		log.debug(TestUtil.FUNCTION_SEP);
		GlobalVariable<String, String> gv = TestUtil.getTestCase2ForGraphRepresentation();

		MSC4<String, String> msc = new MSC4<String, String>();
		int size = msc.buildMaxMatching(gv);
		Assert.assertEquals(3, size);

	}

	//@Ignore
	@Test
	public void testPreProcess() {
		log.debug(TestUtil.FUNCTION_SEP);
		GlobalVariable<String, String> gv = TestUtil.getTestCase1ForGraphRepresentation();

		MSC4<String, String> msc = new MSC4<String, String>();
		msc.preProcess(gv);

		Assert.assertEquals(2, gv.getSolCount());
	}

	 
	@Test
	public void testKHighest() {
		log.debug(TestUtil.FUNCTION_SEP);
		GlobalVariable<String, String> gv = TestUtil.getTestCase1ForGraphRepresentation();

		MSC4<String, String> msc = new MSC4<String, String>();
		int maxCardSet = Util.getMaxCardinalitySetIndex(gv);
		int[] card = gv.getCard();
		int maxCard = card[maxCardSet];

		int kMax = msc.kHighest(gv, maxCard);
		Assert.assertEquals(16, kMax);

	}

	@Test
	public void testBranch() {
		log.debug(TestUtil.FUNCTION_SEP);
		GlobalVariable<String, String> gv = TestUtil.getTestCase2ForGraphRepresentation();

		MSC4<String, String> msc = new MSC4<String, String>();

		msc.branch(gv);
		Assert.assertEquals(4, gv.getBestSolCount());
	}

}