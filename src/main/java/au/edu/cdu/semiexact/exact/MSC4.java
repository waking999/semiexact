package au.edu.cdu.semiexact.exact;

import au.edu.cdu.semiexact.util.ConstantValue;
import au.edu.cdu.semiexact.util.GlobalVariable;

/**
 * 
 * @author kwang1 1. convert Faisal's c code into java format
 */
public class MSC4<ET, ST> {
	

	/**
	 * delete the edge from a vertex of index u to a vertex of index v
	 * 
	 * @param deg,
	 *            degree list in u side
	 * @param al,
	 *            adjacency list in u side
	 * @param im,
	 *            incidence matrix in u side
	 * @param u,
	 *            vertex index u
	 * @param v,
	 *            vertex index v
	 */
	private void deleteEdge(int[] deg, int[][] al, int[][] im, int u, int v) {
		int i = im[v][u];
		int j = deg[u] - 1;
		int x = al[u][j];

		al[u][i] = x;
		al[u][j] = v;

		im[x][u] = i;
		im[v][u] = j;

		deg[u]--;

	}

	/**
	 * delete a vertex of index v
	 * 
	 * @param deg1,
	 *            the degree list of vertices in v side
	 * @param deg2,
	 *            the degree list of vertices in the other side
	 * @param il1,
	 *            the index list of vertices in v side
	 * @param al1,
	 *            the adjacency list of vertices in v side
	 * @param al2,
	 *            the adjacency list of vertices in the other side
	 * @param im2,
	 *            the incidence matrix of vertices in the other side
	 * @param v,
	 *            vertex index
	 * @param n,
	 *            number of active vertices in v side
	 */
	private void deleteVertex(int[] deg1, int[] l1, int[] il1, int[][] al1, int[] deg2, int[][] al2, int[][] im2, int v,
			int n) {
		int d = deg1[v];
		int last = l1[n - 1];
		int i = il1[v];
		l1[i] = last;
		l1[n - 1] = v;
		il1[last] = i;
		il1[v] = n - 1;

		for (int j = d - 1; j >= 0; j--) {
			int u = al1[v][j];
			deleteEdge(deg2, al2, im2, u, v);

		}
		deg1[v] = 0;

	}

	/**
	 * decrease element frequency
	 * 
	 * @param gv,
	 *            global variables
	 * @param eToDecIdx,
	 *            the index of the element to be decreased
	 * @param sToDelIdx,
	 *            the index of the set to be deleted
	 */
	protected void decreaseElementFrequency(GlobalVariable<ET, ST> gv, int eToDecIdx, int sToDelIdx) {
		int[] freq = gv.getFreq();
		int[][] eAL = gv.geteAL();
		int[][] eIM = gv.geteIM();

		deleteEdge(freq, eAL, eIM, eToDecIdx, sToDelIdx);

		gv.setFreq(freq);
		gv.seteAL(eAL);
		gv.seteIM(eIM);

	}

	/**
	 * delete a set
	 * 
	 * @param gv,
	 *            global variables
	 * @param sToDelIdx,
	 *            the index of the set to be deleted
	 */
	protected void deleteSet(GlobalVariable<ET, ST> gv, int sToDelIdx) {
		int[] card = gv.getCard();
		int[] freq = gv.getFreq();

		int[] sL = gv.getsL();
		int[] sIL = gv.getsIL();

		int[][] sAL = gv.getsAL();
		int[][] eAL = gv.geteAL();
		int[][] eIM = gv.geteIM();
		int sActCount = gv.getsActCount();

		deleteVertex(card, sL, sIL, sAL, freq, eAL, eIM, sToDelIdx, sActCount);

		gv.setCard(card);
		gv.setFreq(freq);
		gv.setsIL(sIL);
		gv.setsAL(sAL);
		gv.seteAL(eAL);
		gv.seteIM(eIM);
		gv.setsL(sL);
		gv.setsActCount(sActCount - 1);
	}

	/**
	 * decrease the cardinality of a set
	 * 
	 * @param gv,
	 *            global variable
	 * @param sToDecIdx,
	 *            the index of the set to be decreased
	 * @param eToDelIdx,
	 *            the index of the element to be deleted
	 */
	protected void decreaseSetCardinality(GlobalVariable<ET, ST> gv, int sToDecIdx, int eToDelIdx) {
		int[] card = gv.getCard();
		int[][] sAL = gv.getsAL();
		int[][] sIM = gv.getsIM();

		deleteEdge(card, sAL, sIM, sToDecIdx, eToDelIdx);

		gv.setCard(card);
		gv.setsAL(sAL);
		gv.setsIM(sIM);
	}

	/**
	 * delete element
	 * 
	 * @param gv,
	 *            global variable
	 * @param eToDelIdx,
	 *            the index of the element to be deleted
	 */
	protected void deleteElement(GlobalVariable<ET, ST> gv, int eToDelIdx) {
		int[] freq = gv.getFreq();
		int[] card = gv.getCard();

		int[] eL = gv.geteL();
		int[] eIL = gv.geteIL();

		int[][] eAL = gv.geteAL();
		int[][] sAL = gv.getsAL();
		int[][] sIM = gv.getsIM();
		int eActCount = gv.geteActCount();

		deleteVertex(freq, eL, eIL, eAL, card, sAL, sIM, eToDelIdx, eActCount);

		gv.setCard(card);
		gv.setFreq(freq);
		gv.seteIL(eIL);
		gv.setsAL(sAL);
		gv.seteAL(eAL);
		gv.setsIM(sIM);
		gv.seteL(eL);
		gv.seteActCount(eActCount - 1);
	}

	/**
	 * add a set to solution
	 * 
	 * @param gv,
	 *            global variable
	 * @param sToAddIdx,
	 *            the index of the set to be added
	 */
	protected void addSetToCover(GlobalVariable<ET, ST> gv, int sToAddIdx) {
		int[] card = gv.getCard();
		int[] sL = gv.getsL();
		int sActCount = gv.getsActCount();
		int[] sIL = gv.getsIL();

		int[][] sAL = gv.getsAL();

		int d = card[sToAddIdx];
		int last = sL[sActCount - 1];
		int i = sL[sToAddIdx];
		sL[i] = last;
		sL[sActCount - 1] = sToAddIdx;
		sIL[last] = i;
		sIL[sToAddIdx] = sActCount - 1;

		for (int j = d - 1; j >= 0; j--) {
			int e = sAL[sToAddIdx][j];
			deleteElement(gv, e);

		}
		card[sToAddIdx] = 0;

		gv.setCard(card);
		gv.setsL(sL);
		gv.setsIL(sIL);
		gv.setsAL(sAL);
		gv.setsActCount(sActCount - 1);
	}

	/**
	 * select a set of the maximum cardinality
	 * 
	 * @param gv,
	 *            global variables
	 * @return the set index of the maximum cardinality
	 */
	protected int selectSet(GlobalVariable<ET, ST> gv) {

		int maxCard = ConstantValue.IMPOSSIBLE_VALUE;
		int index = ConstantValue.IMPOSSIBLE_VALUE;

		int sActCount = gv.getsActCount();

		int[] sL = gv.getsL();
		int[] card = gv.getCard();

		for (int i = 0; i < sActCount; i++) {
			int j = sL[i];
			if (card[j] > maxCard) {
				index = j;
				maxCard = card[j];
			}
			if (card[j] >= maxCard && j < index) {
				index = j;
				maxCard = card[j];
			}
		}

		return index;
	}

	/**
	 * get the set index which contains an element of frequency one
	 * 
	 * @param gv,
	 *            global variables
	 * @return set index
	 */
	protected int getSetOfFrequencyOneElement(GlobalVariable<ET, ST> gv) {
		int eActCount = gv.geteActCount();
		int[] freq = gv.getFreq();
		int[] eL = gv.geteL();

		int[][] eAL = gv.geteAL();

		for (int i = 0; i < eActCount; i++) {
			int j = eL[i];
			if (freq[j] == 1) {
				return eAL[j][0];
			}
		}

		return ConstantValue.IMPOSSIBLE_VALUE;
	}

	/**
	 * if the set (with limited setSize) contains the element
	 * 
	 * @param set,
	 *            the set
	 * @param setSize,
	 *            limit the set size
	 * @param ele,
	 *            the element
	 * @return true: the set contains the element; false: otherwise
	 */
	private boolean setContiansEle(int[] set, int setSize, int ele) {
		for (int i = 0; i < setSize; i++) {
			if (ele == set[i]) {
				return true;
			}
		}
		return false;
	}

	/**
	 * if set1 is a subset of set2
	 * 
	 * @param gv,
	 *            global variables
	 * @param s1Idx,
	 *            set1 index
	 * @param s2Idx,
	 *            set2 index
	 * @return true: set1 is a subset of set2; false: otherwise
	 */
	protected boolean is1Subset2(GlobalVariable<ET, ST> gv, int s1Idx, int s2Idx) {
		if (s1Idx == s2Idx) {
			return false;
		}

		int[] card = gv.getCard();
		int s1Card = card[s1Idx];
		int s2Card = card[s2Idx];

		if (s1Card == 0 || s2Card == 0 || s1Card > s2Card) {
			return false;
		}

		int[][] sAL = gv.getsAL();
		int[] s1Array = sAL[s1Idx];
		int[] s2Array = sAL[s2Idx];

		int count = 0;
		for (int i = 0; i < s1Card; i++) {
			int tmp = s1Array[i];
			if (setContiansEle(s2Array, s2Card, tmp)) {
				count++;
			}
		}

		if (count == s1Card) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * if a set is a subset of another set, return the former set index
	 * 
	 * @param gv,
	 *            global variable
	 * @return a subset of another set
	 */
	protected int getSubset(GlobalVariable<ET, ST> gv) {
		int sActCount = gv.geteActCount();
		int[] sL = gv.getsL();
		int[] card = gv.getCard();

		for (int i = 0; i < sActCount - 1; i++) {
			int isL = sL[i];
			if (card[isL] <= 0) {
				continue;
			}
			for (int j = i + 1; j < sActCount; j++) {

				int jsL = sL[j];
				if (card[jsL] <= 0) {
					continue;
				}

				if (is1Subset2(gv, isL, jsL)) {
					return isL;
				} else if (is1Subset2(gv, jsL, isL)) {
					return jsL;
				}

			}
		}

		return ConstantValue.IMPOSSIBLE_VALUE;
	}
 

}