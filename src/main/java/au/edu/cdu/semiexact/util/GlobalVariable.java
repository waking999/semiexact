package au.edu.cdu.semiexact.util;

/**
 * a java bean to store graph representations of Faisal's data structure
 * 
 * @param <ET>
 *            element type
 * @param <ST>
 *            set type
 */
public class GlobalVariable<ET, ST> {
	private int eCount; // element  count

<<<<<<< HEAD
//	private Map<ET, Integer> eLIL; // element label index list
	private int[] eL; // element list
=======
 	private int[] eL; // element list
>>>>>>> origin/master
	private int[] eIL;// element index list

	private int[] freq; // frequency
	private int[][] eAL;// element adjacency
	private int[][] eIM; // element incidence matrix

	private int sCount; // set count
<<<<<<< HEAD
	//private Map<ST, Integer> sLIL; // set label index list
=======
>>>>>>> origin/master
	private int[] sL; // set list
	private int[] sIL;// set index list
	private int[] card; // cardinality
	private int[][] sAL;// set adjacency
	private int[][] sIM; // set incidence matrix

	private int[] mate; // store mate of each vertex; 0: exposed

	private int solCount;
	private int[] sol;
	private int solPtr;

	private int bestSolCount;
	private int[] bestSol;
	
<<<<<<< HEAD
//	private int sActCount;
//	private int eActCount;
	

//	public Map<ET, Integer> geteLIL() {
//		return eLIL;
//	}
//
//	public void seteLIL(Map<ET, Integer> eLIL) {
//		this.eLIL = eLIL;
//	}

//	public int getsActCount() {
//		return sActCount;
//	}
//
//	public void setsActCount(int sActCount) {
//		this.sActCount = sActCount;
//	}
//
//	public int geteActCount() {
//		return eActCount;
//	}
//
//	public void seteActCount(int eActCount) {
//		this.eActCount = eActCount;
//	}
=======
 
>>>>>>> origin/master

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

<<<<<<< HEAD
//	public Map<ST, Integer> getsLIL() {
//		return sLIL;
//	}
//
//	public void setsLIL(Map<ST, Integer> sLIL) {
//		this.sLIL = sLIL;
//	}

=======
>>>>>>> origin/master
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

	public int geteCount() {
		return eCount;
	}

	public void seteCount(int eCount) {
		this.eCount = eCount;
	}

	public int getsCount() {
		return sCount;
	}

	public void setsCount(int sCount) {
		this.sCount = sCount;
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

	public int[] getSol() {
		return sol;
	}

	public void setSol(int[] sol) {
		this.sol = sol;
	}

	public int getSolPtr() {
		return solPtr;
	}

	public void setSolPtr(int solPtr) {
		this.solPtr = solPtr;
	}

	public int[] getBestSol() {
		return bestSol;
	}

	public void setBestSol(int[] bestSol) {
		this.bestSol = bestSol;
	}

}
