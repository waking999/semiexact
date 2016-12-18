package au.edu.cdu.semiexact.exact;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import au.edu.cdu.semiexact.TestUtil;
import au.edu.cdu.semiexact.util.GlobalVariable;

/**
 * 
 * @author kwang1 1. convert Faisal's c code into java format
 */
public class MSC4Test {
	private static final int IMPOSSIBLE_VALUE = -1;

	private GlobalVariable<String, String> getTestCase1() {
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

		return gv;
	}

	@Ignore
	@Test
	public void testDecreaseElementFrequency() {
		System.out.println(
				"****************************************************************************************************");

		GlobalVariable<String, String> gv = getTestCase1();
		TestUtil.printStatus(gv);

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
		TestUtil.printStatus(gv);

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
		TestUtil.printStatus(gv);

		Assert.assertEquals(gv.geteAL()[eToDecIdx][eIMSToDelCellValB4], sToExchIdx);
		Assert.assertEquals(gv.geteAL()[eToDecIdx][freqB4 - 1], sToDelIdx);
		Assert.assertEquals(gv.geteIM()[sToExchIdx][eToDecIdx], eIMSToDelCellValB4);
		Assert.assertEquals(gv.geteIM()[sToDelIdx][eToDecIdx], eIMCell2B4);
		Assert.assertEquals(freqB4 - 1, gv.getFreq()[eToDecIdx]);

	}

	@Ignore
	@Test
	public void testDeleteSet() {
		System.out.println(
				"****************************************************************************************************");
		GlobalVariable<String, String> gv = getTestCase1();
		TestUtil.printStatus(gv);

		MSC4<String, String> msc = new MSC4<String, String>();

		String sToDel = "Sd";

		int sActCount = gv.getsActCount();
		Map<String, Integer> sLIL = gv.getsLIL();
		int[] sIL = gv.getsIL();
		int sToDelIdx = sLIL.get(sToDel);
		int sToExchIdx = sIL[sActCount - 1];

		msc.deleteSet(gv, sToDelIdx);
		TestUtil.printStatus(gv);

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
		TestUtil.printStatus(gv);

		Assert.assertEquals(sActCount - 1, gv.getsActCount());

		sIL = gv.getsIL();

		Assert.assertEquals(0, gv.getCard()[sToDelIdx]);
		Assert.assertEquals(sToDelIdx, sIL[sActCount - 1]);
		Assert.assertEquals(sToExchIdx, sIL[sToDelIdx]);
		Assert.assertEquals(sActCount - 1, sIL[sToDelIdx]);
		Assert.assertEquals(sToDelIdx, sIL[sToExchIdx]);

	}

	@Ignore
	@Test
	public void testDecreaseSetCardinality() {
		System.out.println(
				"****************************************************************************************************");
		GlobalVariable<String, String> gv = getTestCase1();
		TestUtil.printStatus(gv);

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
		TestUtil.printStatus(gv);
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
		TestUtil.printStatus(gv);
		Assert.assertEquals(gv.getsAL()[sToDecIdx][sIMEToDelCellValB4], eToExchIdx);
		Assert.assertEquals(gv.getsAL()[sToDecIdx][cardB4 - 1], eToDelIdx);
		Assert.assertEquals(gv.getsIM()[eToExchIdx][sToDecIdx], sIMEToDelCellValB4);
		Assert.assertEquals(gv.getsIM()[eToDelIdx][sToDecIdx], sIMCell2B4);
		Assert.assertEquals(cardB4 - 1, gv.getCard()[sToDecIdx]);

	}

	@Ignore
	@Test
	public void testDeleteElement() {
		System.out.println(
				"****************************************************************************************************");
		GlobalVariable<String, String> gv = getTestCase1();
		TestUtil.printStatus(gv);

		MSC4<String, String> msc = new MSC4<String, String>();

		String eToDel = "e";
		String sFrom = "Se";

		int[] eIL = gv.geteIL();
		int sFromIdx = gv.getsLIL().get(sFrom);

		int eActCount = gv.geteActCount();
		Map<String, Integer> eLIL = gv.geteLIL();

		int eToDelIdx = eLIL.get(eToDel);
		int eToExchIdx = eIL[eActCount - 1];

		msc.deleteElement(gv, eToDelIdx, sFromIdx);
		TestUtil.printStatus(gv);

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
		sFrom = "Sb";
		sFromIdx = gv.getsLIL().get(sFrom);

		eActCount = gv.geteActCount();
		eLIL = gv.geteLIL();
		eIL = gv.geteIL();
		eToDelIdx = eLIL.get(eToDel);
		eToExchIdx = eIL[eActCount - 1];

		msc.deleteElement(gv, eToDelIdx, sFromIdx);
		TestUtil.printStatus(gv);

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

	@Test
	public void testAddSetToCover() {
		System.out.println(
				"****************************************************************************************************");
		GlobalVariable<String, String> gv = getTestCase1();
		TestUtil.printStatus(gv);

		MSC4<String, String> msc = new MSC4<String, String>();

		Map<String, Integer> sLIL = gv.getsLIL();

		String sToAdd = "Se";

		int sActCount = gv.getsActCount();

		int sToAddIdx = sLIL.get(sToAdd);

		msc.addSetToCover(gv, sToAddIdx);
		TestUtil.printStatus(gv);
		Assert.assertEquals(sActCount - 1, gv.getsActCount());

	}

}