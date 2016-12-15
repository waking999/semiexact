package au.edu.cdu.semiexact.exact;

import java.util.Map;

import au.edu.cdu.semiexact.util.GlobalVariable;

/**
 * 
 * @author kwang1 1. convert Faisal's c code into java format
 */
public class MSC4 <ET, ST>{
 

	/**
	 * decrease element frequency because of deleting set
	 * @param gv, Global Variables
	 * @param eToDecIdx, the element index to be decreased 
	 * @param sToDelIdx, the set index to be deleted
	 */
 
	protected void decreaseElementFrequency(GlobalVariable<ET,ST> gv, int eToDecIdx, int sToDelIdx) {
		int[] freq=gv.getFreq();
		int[][] eIM=gv.geteIM();
		int[][] eAL=gv.geteAL();
 
		
		int eIMSToDelCellVal=eIM[sToDelIdx][eToDecIdx];
		int eFreq=freq[eToDecIdx];
		int sToExchIdx=eAL[eToDecIdx][eFreq-1];
		int eIMSToExchCellVal=eIM[sToExchIdx][eToDecIdx];
		
		// exchange the index of set to be deleted with that of last active set in eIM
		eIM[sToExchIdx][eToDecIdx]=eIMSToDelCellVal;
		eIM[sToDelIdx][eToDecIdx]=eIMSToExchCellVal;
		
		// exchange the index of set to be deleted with that of last active set in eAL
 
	 
		eAL[eToDecIdx][eIMSToExchCellVal]=sToDelIdx;
		eAL[eToDecIdx][eIMSToDelCellVal]=sToExchIdx;
		freq[eToDecIdx]--;

		gv.setFreq(freq);
		gv.seteIM(eIM);
		gv.seteAL(eAL);
	}
	
	
	/**
	 * delete a set in the hybrid graph representation
	 * 
	 * @param gv, Global Variable
	 * @param sToDel,
	 *            the set to be deleted
	 */
	protected void deleteSet(GlobalVariable<ET,ST> gv, ST sToDel) {
		int curSetCount=gv.getCurSetCount();
		int[] card=gv.getCard();
		ST[] sL=gv.getsL();
		Map<ST,Integer> sIL=gv.getsIL();
		int[][] sAL=gv.getsAL();
		 
		
		ST last = sL[curSetCount-1];
		int sToDelIdx = sIL.get(sToDel);
		// exchange the set to be deleted with the last active set in set list
		sL[sToDelIdx] = last;
		sL[curSetCount-1] = sToDel;
		// exchange the set to be deleted with the last active set in set index
		// list
		sIL.replace(last, sToDelIdx);
		sIL.replace(sToDel, curSetCount-1);
		// modify the elements' frequency in the set to be deleted
		for (int i = 0; i <  card[sToDelIdx]; i++) {
			int e = sAL[sToDelIdx][i];
			decreaseElementFrequency(gv, e, sToDelIdx);
		}

		card[sToDelIdx] = 0;
		
		gv.setCard(card);
		gv.setCurSetCount(curSetCount-1);
		gv.setsL(sL);
		gv.setsIL(sIL);

	}
 
	/**
	 * decrease set cardinality because of deleting an element
	 * 
	 * @param gv, Global Variables
	 * @param sToDecIdx, the set index to be decreased
	 * @param eToDelIdx, the element index to be deleted
	 */
	protected void decreaseSetCardinality(GlobalVariable<ET,ST> gv, int sToDecIdx, int eToDelIdx){
		int[] card=gv.getCard();
		int[][] sIM=gv.getsIM();
		int[][] sAL=gv.getsAL();
		
		int sIMEToDelCellVal=sIM[eToDelIdx][sToDecIdx];
		int sCard=card[sToDecIdx];
		int eToExchIdx=sAL[sToDecIdx][sCard-1];
		int sIMEToExchCellVal=sIM[eToExchIdx][sToDecIdx];
		
		// exchange the index of element to be deleted with that of last active element in sIM
		sIM[eToExchIdx][sToDecIdx]=sIMEToDelCellVal;
		sIM[eToDelIdx][sToDecIdx]=sIMEToExchCellVal;
		
		// exchange the index of element to be deleted with that of last active element in sAL
		sAL[sToDecIdx][sIMEToExchCellVal]=eToDelIdx;
 		sAL[sToDecIdx][sIMEToDelCellVal]=eToExchIdx;
 		card[sToDecIdx]--;
 		
 		gv.setCard(card);
 		gv.setsIM(sIM);
 		gv.setsAL(sAL);
	}
	
	/**
	 * 
	 * @param gv
	 * @param eToDel
	 */
	protected void deleteElement(GlobalVariable<ET,ST> gv, ET eToDel) {
		int curEleCount=gv.getCurEleCount();
		int[] freq=gv.getFreq();
		
		ET[] eL=gv.geteL();
		Map<ET,Integer> eIL=gv.geteIL();
		int[][] eAL=gv.geteAL();
		 
		
		ET last = eL[curEleCount-1];
		int eToDelIdx =eIL.get(eToDel);
		// exchange the element to be deleted with the last active element in element list
		eL[eToDelIdx] = last;
		eL[curEleCount-1] = eToDel;
		// exchange the element to be deleted with the last active element in element index
		// list
		eIL.replace(last, eToDelIdx);
		eIL.replace(eToDel, curEleCount-1);
		// modify the sets' cardinality  containing the element to be deleted
		for (int i = 0; i <  freq[eToDelIdx]; i++) {
			int s = eAL[eToDelIdx][i];
			decreaseSetCardinality(gv, s, eToDelIdx);
		}

		freq[eToDelIdx] = 0;
		
		gv.setFreq(freq);;
		gv.setCurEleCount(curEleCount-1);
		gv.seteL(eL);
		gv.seteIL(eIL);

	}
 
	 
}
