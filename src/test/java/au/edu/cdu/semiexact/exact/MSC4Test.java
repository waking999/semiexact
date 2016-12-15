package au.edu.cdu.semiexact.exact;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import au.edu.cdu.semiexact.util.GlobalVariable;

public class MSC4Test {
	private static final int IMPOSSIBLE_VALUE = -1;

	
	private GlobalVariable<String> getTestCase1(){
		String[] eL = { "a", "b", "c", "d", "e", "f" };
		
		Map<String, Integer> eIL = new HashMap<String, Integer>();
		eIL.put("a", 0);
		eIL.put("b", 1);
		eIL.put("c", 2);
		eIL.put("d", 3);
		eIL.put("e", 4);
		eIL.put("f", 5);
		int[][] eAL = { { 0, 1, 2 }, { 0, 1, 3 }, { 0, 2, 3 }, { 1, 2, 3, 4 }, { 3, 4, 5 }, { 4, 5 } };
		int[][] eIM = { { 0, 0, 0, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE },
				{ 1, 1, IMPOSSIBLE_VALUE, 0, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE },
				{ 2, IMPOSSIBLE_VALUE, 1, 1, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE },
				{ IMPOSSIBLE_VALUE, 2, 2, 2, 0, IMPOSSIBLE_VALUE },
				{ IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, 3, 1, 0 },
				{ IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, 2, 1 } };
		int[] freq = { 3, 3, 3, 4, 3, 2 };

		String[] sL = { "Sa", "Sb", "Sc", "Sd", "Se", "Sf" };
		Map<String, Integer> sIL = new HashMap<String, Integer>();
		sIL.put("Sa", 0);
		sIL.put("Sb", 1);
		sIL.put("Sc", 2);
		sIL.put("Sd", 3);
		sIL.put("Se", 4);
		sIL.put("Sf", 5);
		int[][] sAL = { { 0, 1, 2 }, { 0, 1, 3 }, { 0, 2, 3 }, { 1, 2, 3, 4 }, { 3, 4, 5 }, { 4, 5 } };
		int[][] sIM = { { 0, 0, 0, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE },
				{ 1, 1, IMPOSSIBLE_VALUE, 0, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE },
				{ 2, IMPOSSIBLE_VALUE, 1, 1, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE },
				{ IMPOSSIBLE_VALUE, 2, 2, 2, 0, IMPOSSIBLE_VALUE },
				{ IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, 3, 1, 0 },
				{ IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, IMPOSSIBLE_VALUE, 2, 1 } };

		int[] card = { 3, 3, 3, 4, 3, 2 };
		
		int curEleCount=6;
		int curSetCount=6;
		
		GlobalVariable<String> gv=new GlobalVariable<String>();
		gv.setCard(card);
		gv.seteAL(eAL);
		gv.seteIL(eIL);
		gv.seteIM(eIM);
		gv.seteL(eL);
		gv.setFreq(freq);
		gv.setsAL(sAL);
		gv.setsIL(sIL);
		gv.setsIM(sIM);
		gv.setsL(sL);
		gv.setCurEleCount(curEleCount);
		gv.setCurSetCount(curSetCount);
		
		return gv;
	}
	
	@Test
	public void testDecreaseElementFrequency() {

		GlobalVariable<String> gv=getTestCase1();
		String[] eL=gv.geteL();
		Map<String,Integer> eIL=gv.geteIL();
		int[][] eAL=gv.geteAL();
		int[][] eIM=gv.geteIM();
		
		String[] sL=gv.getsL();
		Map<String,Integer> sIL=gv.getsIL();
		int[][] sAL=gv.getsAL();
		int[][] sIM=gv.getsIM();
		
		int[] freq=gv.getFreq();
		int[] card=gv.getCard();
		
		int curEleCount=gv.getCurEleCount();
		
		MSC4 msc = new MSC4();
		msc.seteL(eL);
		msc.seteIL(eIL);
		msc.seteAL(eAL);
		msc.seteIM(eIM);

		msc.setsL(sL);
		msc.setsIL(sIL);
		msc.setsAL(sAL);
		msc.setsIM(sIM);

		String eToDec = "d";
		String sToDel = "Se";

		int eToDecIdx = eIL.get(eToDec);
		int sToDelIdx = sIL.get(sToDel);

		int freqB4 = freq[eToDecIdx];
		int eIMSToDelCellValB4 = eIM[sToDelIdx][eToDecIdx];
		int sToExchIdx = eAL[eToDecIdx][freqB4 - 1];

		int eIMCell2B4 = eIM[eAL[eToDecIdx][freqB4 - 1]][eToDecIdx];
		msc.decreaseElementFrequency(freq, curEleCount, eToDecIdx, sToDelIdx);

		Assert.assertEquals(eAL[eToDecIdx][eIMSToDelCellValB4], sToExchIdx);
		Assert.assertEquals(eAL[eToDecIdx][freqB4 - 1], sToDelIdx);
		Assert.assertEquals(eIM[sToExchIdx][eToDecIdx], eIMSToDelCellValB4);
		Assert.assertEquals(eIM[sToDelIdx][eToDecIdx], eIMCell2B4);
		Assert.assertEquals(freqB4 - 1, freq[eToDecIdx]);

		eToDec = "f";
		sToDel = "Se";

		eToDecIdx = eIL.get(eToDec);
		sToDelIdx = sIL.get(sToDel);

		freqB4 = freq[eToDecIdx];
		eIMSToDelCellValB4 = eIM[sToDelIdx][eToDecIdx];
		sToExchIdx = eAL[eToDecIdx][freqB4 - 1];

		eIMCell2B4 = eIM[eAL[eToDecIdx][freqB4 - 1]][eToDecIdx];
		msc.decreaseElementFrequency(freq, curEleCount, eToDecIdx, sToDelIdx);

		Assert.assertEquals(eAL[eToDecIdx][eIMSToDelCellValB4], sToExchIdx);
		Assert.assertEquals(eAL[eToDecIdx][freqB4 - 1], sToDelIdx);
		Assert.assertEquals(eIM[sToExchIdx][eToDecIdx], eIMSToDelCellValB4);
		Assert.assertEquals(eIM[sToDelIdx][eToDecIdx], eIMCell2B4);
		Assert.assertEquals(freqB4 - 1, freq[eToDecIdx]);

	}

}
