package au.edu.cdu.semiexact.util;

import java.util.Map;

public class GlobalVariable<ET, ST> {
	int eActCount; //element active count
	Map<ET, Integer> eLIL; // element label index list
	int[] eL; // element list
	int[] eIL;// element index list
	
	int[] freq; // frequency
	int[][] eAL;// element adjacency
	int[][] eIM; // element incidence matrix

	int sActCount; //set active count
	Map<ST, Integer> sLIL; // set label index list
	int[] sL; // set list
	int[] sIL;// set index list
	int[] card; // cardinality
	int[][] sAL;// set adjacency
	int[][] sIM; // set incidence matrix
	public Map<ET, Integer> geteLIL() {
		return eLIL;
	}
	public void seteLIL(Map<ET, Integer> eLIL) {
		this.eLIL = eLIL;
	}
	public int[] geteIL() {
		return eIL;
	}
	public void seteIL(int[] eIL) {
		this.eIL = eIL;
	}
	public int[] getFreq() {
		return freq;
	}
	public void setFreq(int[] freq) {
		this.freq = freq;
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
	public Map<ST, Integer> getsLIL() {
		return sLIL;
	}
	public void setsLIL(Map<ST, Integer> sLIL) {
		this.sLIL = sLIL;
	}
	public int[] getsIL() {
		return sIL;
	}
	public void setsIL(int[] sIL) {
		this.sIL = sIL;
	}
	public int[] getCard() {
		return card;
	}
	public void setCard(int[] card) {
		this.card = card;
	}
	public int[][] getsAL() {
		return sAL;
	}
	public void setsAL(int[][] sAL) {
		this.sAL = sAL;
	}
	public int[][] getsIM() {
		return sIM;
	}
	public void setsIM(int[][] sIM) {
		this.sIM = sIM;
	}
	public int geteActCount() {
		return eActCount;
	}
	public void seteActCount(int eActCount) {
		this.eActCount = eActCount;
	}
	public int getsActCount() {
		return sActCount;
	}
	public void setsActCount(int sActCount) {
		this.sActCount = sActCount;
	}
	public int[] geteL() {
		return eL;
	}
	public void seteL(int[] eL) {
		this.eL = eL;
	}
	public int[] getsL() {
		return sL;
	}
	public void setsL(int[] sL) {
		this.sL = sL;
	}

}
