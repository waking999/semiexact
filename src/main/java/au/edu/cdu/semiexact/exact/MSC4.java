package au.edu.cdu.semiexact.exact;

import java.util.Map;

import au.edu.cdu.semiexact.util.GlobalVariable;

/**
 * 
 * @author kwang1 1. convert Faisal's c code into java format
 */
public class MSC4 <ET, ST>{
<<<<<<< HEAD

	/**
	 * decrease element frequency because of deleting set
	 * @param gv, Global Variables
	 * @param eToDecIdx, the element index to be decreased 
	 * @param sToDelIdx, the set index to be deleted
	 */
=======
//	private T[] sL; // index to set list
//	private Map<T, Integer> sIL; // set to index list
//	private int[][] sAL; // set index adjacent list
//	private int[][] sIM; // set index incident matrix
//	
//	private T[] eL; //index to element list
//	private Map<T,Integer> eIL;// element to index list
//	private int[][] eAL; // element index adjacent list,
//	private int[][] eIM; // element index incident matrix
//
//
//
//	public int[][] getsIM() {
//		return sIM;
//	}
//
//	public void setsIM(int[][] sIM) {
//		this.sIM = sIM;
//	}
//
//	public T[] geteL() {
//		return eL;
//	}
//
//	public void seteL(T[] eL) {
//		this.eL = eL;
//	}
//
//	public Map<T, Integer> geteIL() {
//		return eIL;
//	}
//
//	public void seteIL(Map<T, Integer> eIL) {
//		this.eIL = eIL;
//	}
//
//
//
//	public T[] getsL() {
//		return sL;
//	}
//
//	public void setsL(T[] sL) {
//		this.sL = sL;
//	}
//
//	public Map<T, Integer> getsIL() {
//		return sIL;
//	}
//
//	public void setsIL(Map<T, Integer> sIL) {
//		this.sIL = sIL;
//	}
//
//	public int[][] getsAL() {
//		return sAL;
//	}
//
//	public void setsAL(int[][] sAL) {
//		this.sAL = sAL;
//	}
//
//	public int[][] geteAL() {
//		return eAL;
//	}
//
//	public void seteAL(int[][] eAL) {
//		this.eAL = eAL;
//	}
//
//	public int[][] geteIM() {
//		return eIM;
//	}
//
//	public void seteIM(int[][] eIM) {
//		this.eIM = eIM;
//	}

	
>>>>>>> origin/master
	protected void decreaseElementFrequency(GlobalVariable<ET,ST> gv, int eToDecIdx, int sToDelIdx) {
		int[] freq=gv.getFreq();
		int[][] eIM=gv.geteIM();
		int[][] eAL=gv.geteAL();
<<<<<<< HEAD
		
		int eIMSToDelCellVal=eIM[sToDelIdx][eToDecIdx];
		int eFreq=freq[eToDecIdx];
		int sToExchIdx=eAL[eToDecIdx][eFreq-1];
		int eIMSToExchCellVal=eIM[sToExchIdx][eToDecIdx];
		
		// exchange the index of set to be deleted with that of last active set in eIM
		eIM[sToExchIdx][eToDecIdx]=eIMSToDelCellVal;
		eIM[sToDelIdx][eToDecIdx]=eIMSToExchCellVal;
		
		// exchange the index of set to be deleted with that of last active set in eAL
=======
		
		int eIMSToDelCellVal=eIM[sToDelIdx][eToDecIdx];
		int eFreq=freq[eToDecIdx];
		int sToExchIdx=eAL[eToDecIdx][eFreq-1];
		int eIMSToExchCellVal=eIM[sToExchIdx][eToDecIdx];
		
		// exchange the index of set to be deleted with that of last active set in eIM
		eIM[sToExchIdx][eToDecIdx]=eIMSToDelCellVal;
		eIM[sToDelIdx][eToDecIdx]=eIMSToExchCellVal;
		
		// exchange the index of set to be deleted with that of last active set in eIM
>>>>>>> origin/master
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
	 * @param card,
	 *            the cardinality array of each set
	 * @param curSetCount,
	 *            the current active set count
	 * @param freq,
	 *            the frequency array of each element
	 * @param curEleCount,
	 *            the current active element count
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
<<<<<<< HEAD
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
=======
	
>>>>>>> origin/master
	 
}
