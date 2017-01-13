package au.edu.cdu.semiexact.util;

import java.util.Map;

public class GlobalVariable<ET, ST> {
	private int eActCount; //element active count
	
	private Map<ET, Integer> eLIL; // element label index list
	private int[] eL; // element list
	private int[] eIL;// element index list
	
	private int[] freq; // frequency
	private int[][] eAL;// element adjacency
	private int[][] eIM; // element incidence matrix

	private int sActCount; //set active count
	private Map<ST, Integer> sLIL; // set label index list
	private int[] sL; // set list
	private int[] sIL;// set index list
	private int[] card; // cardinality
	private int[][] sAL;// set adjacency
	private int[][] sIM; // set incidence matrix
	
	private int[] mate; //store mate of each vertex; 0: exposed
//	private int[] labelType; //0:start;1:vertex;2:edge;-1:non-outer
//	private int[] labelValue;
//	private int[] outer; //queue of outer vertices
//	private int[] first; // first non-outer vertex in the path back to the start of the search;


	private int solCount;
	private int bestSolCount;
	
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

	
	public int getBestSolCount() {
		return bestSolCount;
	}
	public void setBestSolCount(int bestSolCount) {
		this.bestSolCount = bestSolCount;
	}
	public int[] getMate() {
		return mate;
	}
	public void setMate(int[] mate) {
		this.mate = mate;
	} 
	public int getSolCount() {
		return solCount;
	}
	public void setSolCount(int solCount) {
		this.solCount = solCount;
	}
//	public int[] getLabelType() {
//		return labelType;
//	}
//	public void setLabelType(int[] labelType) {
//		this.labelType = labelType;
//	}
//	public int[] getLabelValue() {
//		return labelValue;
//	}
//	public void setLabelValue(int[] labelValue) {
//		this.labelValue = labelValue;
//	}
//	public int[] getOuter() {
//		return outer;
//	}
//	public void setOuter(int[] outer) {
//		this.outer = outer;
//	}
//	public int[] getFirst() {
//		return first;
//	}
//	public void setFirst(int[] first) {
//		this.first = first;
//	}
}
