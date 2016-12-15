package au.edu.cdu.semiexact.exact;

import java.util.Map;

/**
 * 
 * @author kwang1 1. convert Faisal's c code into java format
 */
public class MSC4 {
	private String[] sL; // index to set list
	private Map<String, Integer> sIL; // set to index list
	private int[][] sAL; // set index adjacent list
	private int[][] sIM; // set index incident matrix
	
	private String[] eL; //index to element list
	private Map<String,Integer> eIL;// element to index list
	private int[][] eAL; // element index adjacent list,
	private int[][] eIM; // element index incident matrix



	public int[][] getsIM() {
		return sIM;
	}

	public void setsIM(int[][] sIM) {
		this.sIM = sIM;
	}

	public String[] geteL() {
		return eL;
	}

	public void seteL(String[] eL) {
		this.eL = eL;
	}

	public Map<String, Integer> geteIL() {
		return eIL;
	}

	public void seteIL(Map<String, Integer> eIL) {
		this.eIL = eIL;
	}



	public String[] getsL() {
		return sL;
	}

	public void setsL(String[] sL) {
		this.sL = sL;
	}

	public Map<String, Integer> getsIL() {
		return sIL;
	}

	public void setsIL(Map<String, Integer> sIL) {
		this.sIL = sIL;
	}

	public int[][] getsAL() {
		return sAL;
	}

	public void setsAL(int[][] sAL) {
		this.sAL = sAL;
	}

	public int[][] geteAL() {
		return eAL;
	}

	public void seteAL(int[][] eAL) {
		this.eAL = eAL;
	}

	public int[][] geteIM() {
		return eIM;
	}

	public void seteIM(int[][] eIM) {
		this.eIM = eIM;
	}

	
	protected void decreaseElementFrequency(int[] freq, int curEleCount, int eToDec, int sToDelIdx) {
		int eIMSToDelCellVal=eIM[sToDelIdx][eToDec];
		int eFreq=freq[eToDec];
		int sToExchIdx=eAL[eToDec][eFreq-1];
		int eIMSToExchCellVal=eIM[sToExchIdx][eToDec];
		
		// exchange the index of set to be deleted with that of last active set in eIM
		eIM[sToExchIdx][eToDec]=eIMSToDelCellVal;
		eIM[sToDelIdx][eToDec]=eIMSToExchCellVal;
		
		// exchange the index of set to be deleted with that of last active set in eIM
		eAL[eToDec][eIMSToExchCellVal]=sToDelIdx;
		eAL[eToDec][eIMSToDelCellVal]=sToExchIdx;
		freq[eToDec]--;

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
	protected void deleteSet(int[] card, int curSetCount, int[] freq, int curEleCount, String sToDel) {
		String last = sL[curSetCount];
		int sToDelIdx = sIL.get(sToDel);
		// exchange the set to be deleted with the last active set in set list
		sL[sToDelIdx] = last;
		sL[curSetCount] = sToDel;
		// exchange the set to be deleted with the last active set in set index
		// list
		sIL.replace(last, sToDelIdx);
		sIL.replace(sToDel, curSetCount);
		// modify the elements' frequency in the set to be deleted
		for (int i = 1; i <= card[sToDelIdx]; i++) {
			int e = sAL[sToDelIdx][i];
			decreaseElementFrequency(freq, curEleCount, e, sToDelIdx);
		}

		card[sToDelIdx] = 0;

	}
	
	 
}
