package au.edu.cdu.semiexact.exact;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import au.edu.cdu.semiexact.util.ConstantValue;
import au.edu.cdu.semiexact.util.GlobalVariable;
import au.edu.cdu.semiexact.util.Util;

/**
 * 
 * 1. convert Faisal's c code into java format, which also uses Faisal's graph
 * representation data structure ;
 * 
 * 2. use existing Edmonds max matching algorithm
 */
public class MSC4<ET, ST> implements IMSC<ET, ST> {

	/**
<<<<<<< HEAD
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
		int j = deg[u];
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
	private void deleteVertex(int[] deg1, int[] l1, int[] il1, int[][] al1, int[] deg2, int[][] al2, int[][] im2,
			int v) {
		int n = deg1[0];
		int d = deg1[v];
		int last = l1[n];
		int i = il1[v];
		l1[i] = last;
		l1[n] = v;
		il1[last] = i;
		il1[v] = n;

		for (int j = d; j >= 1; j--) {
			int u = al1[v][j];
			deleteEdge(deg2, al2, im2, u, v);

		}
		deg1[v] = 0;
		deg1[0] = n - 1;

	}

	// /**
	// * decrease element frequency
	// *
	// * @param gv,
	// * global variables
	// * @param eToDecIdx,
	// * the index of the element to be decreased
	// * @param sToDelIdx,
	// * the index of the set to be deleted
	// */
	// protected void decreaseElementFrequency(GlobalVariable<ET, ST> gv,int[]
	// freq, int eToDecIdx, int sToDelIdx) {
	// //int[] freq = gv.getFreq();
	// int[][] eAL = gv.geteAL();
	// int[][] eIM = gv.geteIM();
	//
	// deleteEdge(freq, eAL, eIM, eToDecIdx, sToDelIdx);
	//
	// //gv.setFreq(freq);
	// gv.seteAL(eAL);
	// gv.seteIM(eIM);
	//
	// }

	/**
	 * delete a set
	 * 
	 * @param gv,
	 *            global variables
	 * @param sToDelIdx,
	 *            the index of the set to be deleted
	 */
	protected void deleteSet(GlobalVariable<ET, ST> gv, int[] card, int[] freq, int sToDelIdx) {
		// int[] card = gv.getCard();
		// int[] freq = gv.getFreq();

		int[] sL = gv.getsL();
		int[] sIL = gv.getsIL();

		int[][] sAL = gv.getsAL();
		int[][] eAL = gv.geteAL();
		int[][] eIM = gv.geteIM();
		// int sActCount = gv.getsActCount();

		deleteVertex(card, sL, sIL, sAL, freq, eAL, eIM, sToDelIdx);

		// gv.setCard(card);
		// gv.setFreq(freq);
		gv.setsIL(sIL);
		gv.setsAL(sAL);
		gv.seteAL(eAL);
		gv.seteIM(eIM);
		gv.setsL(sL);
		// gv.setsActCount(sActCount - 1);
	}

	// /**
	// // * decrease the cardinality of a set
	// // *
	// // * @param gv,
	// // * global variable
	// // * @param sToDecIdx,
	// // * the index of the set to be decreased
	// // * @param eToDelIdx,
	// // * the index of the element to be deleted
	// // */
	// protected void decreaseSetCardinality(GlobalVariable<ET, ST> gv, int[]
	// card, int sToDecIdx, int eToDelIdx) {
	// // int[] card = gv.getCard();
	// int[][] sAL = gv.getsAL();
	// int[][] sIM = gv.getsIM();
	//
	// deleteEdge(card, sAL, sIM, sToDecIdx, eToDelIdx);
	//
	// // gv.setCard(card);
	// gv.setsAL(sAL);
	// gv.setsIM(sIM);
	// }

	/**
	 * delete element
	 * 
	 * @param gv,
	 *            global variable
	 * @param eToDelIdx,
	 *            the index of the element to be deleted
	 */
	protected void deleteElement(GlobalVariable<ET, ST> gv, int[] card, int[] freq, int eToDelIdx) {
		// int[] freq = gv.getFreq();
		// int[] card = gv.getCard();

		int[] eL = gv.geteL();
		int[] eIL = gv.geteIL();

		int[][] eAL = gv.geteAL();
		int[][] sAL = gv.getsAL();
		int[][] sIM = gv.getsIM();

		deleteVertex(freq, eL, eIL, eAL, card, sAL, sIM, eToDelIdx);

		// gv.setCard(card);
		// gv.setFreq(freq);
		gv.seteIL(eIL);
		gv.setsAL(sAL);
		gv.seteAL(eAL);
		gv.setsIM(sIM);
		gv.seteL(eL);
		// gv.seteActCount(eActCount - 1);
	}

	/**
	 * add a set to solution
	 * 
	 * @param gv,
	 *            global variable
	 * @param sToAddIdx,
	 *            the index of the set to be added
	 */
	protected void addSetToCover(GlobalVariable<ET, ST> gv, int[] card, int[] freq, int sToAddIdx) {
		// int[] card = gv.getCard();
		int[] sL = gv.getsL();
		int sActCount = card[0];

		int[] sIL = gv.getsIL();

		int[][] sAL = gv.getsAL();

		int d = card[sToAddIdx];
		int last = sL[sActCount];
		int i = sL[sToAddIdx];
		sL[i] = last;
		sL[sActCount] = sToAddIdx;
		sIL[last] = i;
		sIL[sToAddIdx] = sActCount;

		for (int j = d; j >= 1; j--) {
			int e = sAL[sToAddIdx][j];
			deleteElement(gv, card, freq, e);

		}
		card[sToAddIdx] = 0;

		// gv.setCard(card);
		gv.setsL(sL);
		gv.setsIL(sIL);
		gv.setsAL(sAL);
		card[0] = sActCount - 1;
		// gv.setsActCount(sActCount - 1);
	}

	/**
	 * get the set index which contains an element of frequency one
	 * 
	 * @param gv,
	 *            global variables
	 * @return set index
	 */
	protected int getSetOfFrequencyOneElement(GlobalVariable<ET, ST> gv, int[] freq) {
		// int eActCount = gv.geteCount();
		// int[] freq = gv.getFreq();
		int eActCount = freq[0];

		int[] eL = gv.geteL();

		int[][] eAL = gv.geteAL();

		for (int i = 1; i <= eActCount; i++) {
			int j = eL[i];
			if (freq[j] == 1) {
				return eAL[j][1];
			}
		}

		return ConstantValue.IMPOSSIBLE_VALUE;
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
	protected boolean is1Subset2(GlobalVariable<ET, ST> gv, int[] card, int s1Idx, int s2Idx) {
		if (s1Idx == s2Idx) {
			return false;
		}

		// int[] card = gv.getCard();
		int s1Card = card[s1Idx];
		int s2Card = card[s2Idx];

		if (s1Card == 0 || s2Card == 0 || s1Card > s2Card) {
			return false;
		}

		int[][] sAL = gv.getsAL();
		int[] s1Array = sAL[s1Idx];
		int[] s2Array = sAL[s2Idx];

		int count = 0;
		for (int i = 1; i <= s1Card; i++) {
			int tmp = s1Array[i];
			if (Util.setContiansEle(s2Array, s2Card, tmp)) {
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
	protected int getSubset(GlobalVariable<ET, ST> gv, int[] card) {
		// int sActCount = gv.geteActCount();
		int[] sL = gv.getsL();
		// int[] card = gv.getCard();
		int sActCount = card[0];

		for (int i = 1; i <= sActCount - 1; i++) {
			int isL = sL[i];
			if (card[isL] <= 0) {
				continue;
			}
			for (int j = i + 1; j <= sActCount; j++) {

				int jsL = sL[j];
				if (card[jsL] <= 0) {
					continue;
				}

				if (is1Subset2(gv, card, isL, jsL)) {
					return isL;
				} else if (is1Subset2(gv, card, jsL, isL)) {
					return jsL;
				}

			}
		}

		return ConstantValue.IMPOSSIBLE_VALUE;
	}

	protected Map<Integer, List<Integer>> transferGVIntoMMParam(GlobalVariable<ET, ST> gv, int[] card) {

		int[][] sAL = gv.getsAL();
		int sActNum = gv.getsCount();
		// int[] card = gv.getCard();

		Map<Integer, List<Integer>> g = new HashMap<Integer, List<Integer>>();

		for (int i = 1; i <= sActNum; i++) {
			if (card[i] > 0) {
				int[] sEL = sAL[i];

				for (int j = 1; j <= card[i]; j++) {
					int sELj = sEL[j];
					if (!g.containsKey(sELj)) {
						List<Integer> tmpList = new ArrayList<Integer>();
						g.put(sELj, tmpList);
					}
					List<Integer> tmpList = g.get(sELj);
					for (int k = 1; k <= card[i]; k++) {
						if (j == k) {
							continue;
						}
						int sELk = sEL[k];
						if (!tmpList.contains(sELk)) {
							tmpList.add(sELk);
						}
					}
				}
			}
		}

		return g;

	}

	/**
=======
>>>>>>> origin/master
	 * 
	 * @param gv
	 * @return
	 */
<<<<<<< HEAD
	protected int buildMaxMatching(GlobalVariable<ET, ST> gv, int[] card) {
		Map<Integer, List<Integer>> g = transferGVIntoMMParam(gv, card);
=======
	protected int buildMaxMatching(GlobalVariable<ET, ST> gv, int[] card, int[] freq) {
		Map<Integer, List<Integer>> g = Util.transferGVIntoMMParam(gv, card, freq);
>>>>>>> origin/master
		MM mm = new MM();
		MMObj o = mm.maxMatching(g);

		int[] eL = gv.geteL();
		int eActCount = gv.geteCount();
		int[] mate = gv.getMate();
		for (int i = 1; i <= eActCount; i++) {
			mate[eL[i]] = ConstantValue.MATE_EXPOSE;
		}

		int size = o.getMnum();
		Map<Integer, Integer> matching = o.getMatching();
		Set<Integer> keySet = matching.keySet();
		for (int key : keySet) {
			int val = matching.get(key);
			if (val != ConstantValue.MATE_EXPOSE) {
				mate[eL[key]] = eL[val];
			}
		}
		gv.setMate(mate);

		return size;

	}

	/**
	 * apply reduction rules;
	 * 
	 * @param gv
	 * @return
	 */
	protected boolean preProcess(GlobalVariable<ET, ST> gv, int[] card, int freq[]) {

		int bestSolCount = gv.getBestSolCount();
		int solCount = gv.getSolCount();
		int s1 = card[0];
		int e1 = freq[0];

		int[] sol = gv.getSol();
<<<<<<< HEAD
		// int eActCount = gv.geteActCount();
		// int[] card = gv.getCard();
=======
>>>>>>> origin/master
		int solPtr = gv.getSolPtr();

		int k1 = -1;
		int count = 0;

		while (k1 != (bestSolCount - solCount - 1)) {
			// subset rule
			k1 = bestSolCount - solCount - 1;
<<<<<<< HEAD
			int domSet = getSubset(gv, card);
=======
			int domSet = Util.getSubset(gv, card);
>>>>>>> origin/master
			while (domSet > ConstantValue.IMPOSSIBLE_VALUE) {
				if (bestSolCount <= solCount + 1) {
					card[0] = s1;
					freq[0] = e1;
					return true;
				}
<<<<<<< HEAD
				deleteSet(gv, card, freq, domSet);
				s1--;
				count++;
				domSet = getSubset(gv, card);
			}
			// frequency one rule
			int freqOneSet = this.getSetOfFrequencyOneElement(gv, freq);
=======
				Util.deleteSet(gv, card, freq, domSet);
				s1--;
				count++;
				domSet = Util.getSubset(gv, card);
			}
			// frequency one rule
			int freqOneSet = Util.getSetOfFrequencyOneElement(gv, freq);
>>>>>>> origin/master
			while (freqOneSet > ConstantValue.IMPOSSIBLE_VALUE) {
				if (bestSolCount <= solCount + 1) {
					return true;
				}
				sol[solPtr++] = freqOneSet;
				solCount++;
				int tmpCard = card[freqOneSet];
<<<<<<< HEAD
				this.addSetToCover(gv, card, freq, freqOneSet);
				e1 = e1 - tmpCard;
				s1--;
				// gv.seteCount(eActCount);

				count++;
				freqOneSet = this.getSetOfFrequencyOneElement(gv, freq);
=======
				Util.addSetToCover(gv, card, freq, freqOneSet);
				e1 = e1 - tmpCard;
				s1--;

				count++;
				freqOneSet = Util.getSetOfFrequencyOneElement(gv, freq);
>>>>>>> origin/master
			}
			// TODO: other rules

		}
<<<<<<< HEAD
		// gv.seteCount(eActCount);
=======
>>>>>>> origin/master
		gv.setSol(sol);
		gv.setSolPtr(solPtr);
		gv.setSolCount(solCount);

		card[0] = s1;
		freq[0] = e1;
		if (count > 0) {
			return true;
		}

		return false;
	}

	protected int kHighest(GlobalVariable<ET, ST> gv, int[] card, int maxCard) {
<<<<<<< HEAD
		// TODO: make sure the sum of cardinalityies of the k sets of highest card is greater than number of elements
		int bestSolCount = gv.getBestSolCount();
		int solCount = gv.getSolCount();
		// int sActCount = gv.getsCount();
		int sActCount = card[0];
		int[] sL = gv.getsL();
		// int[] card = gv.getCard();
=======
		// TODO: make sure the sum of cardinalityies of the k sets of highest
		// card is greater than number of elements
		int bestSolCount = gv.getBestSolCount();
		int solCount = gv.getSolCount();
		int sActCount = card[0];
		int[] sL = gv.getsL();
>>>>>>> origin/master

		int k = bestSolCount - solCount - 1;
		int count = 0;
		int kMax = 0;

		int[] cardIdx = new int[maxCard + 1];
		for (int i = 1; i <= maxCard; i++) {
			cardIdx[i] = 0;
		}

		for (int i = 1; i <= sActCount; i++) {
			int v = sL[i];
			cardIdx[card[v]]++;
		}

		for (int i = maxCard; i > 0; i--) {
			if (count >= k) {
				break;
			}

			if ((count + cardIdx[i]) > k) {
				return (kMax + (i * (k - count)));
			} else {
				kMax += (i * cardIdx[i]);
				count += cardIdx[i];
			}

		}

		return kMax;

	}

	public int branch(GlobalVariable<ET, ST> gv) {
		return branch(gv, gv.getCard(), gv.getFreq());
	}

	private int branch(GlobalVariable<ET, ST> gv, int[] card, int[] freq) {
		int bestSolCount = gv.getBestSolCount();
		int solCount = gv.getSolCount();

		// int s1 = card[0];
		// int e1 = freq[0];
		// int s2;
		// int e2;

		if (bestSolCount <= solCount) {
			return bestSolCount;
		}

<<<<<<< HEAD
		// if (e1 == 0) {
=======
>>>>>>> origin/master
		if (freq[0] == 0) {
			solCount = gv.getSolCount();
			bestSolCount = solCount;
			int[] sol = gv.getSol();
			int[] bestSol = Arrays.copyOf(sol, solCount + 1);
			gv.setBestSol(bestSol);
			gv.setBestSolCount(bestSolCount);
			return bestSolCount;
		}

		if (bestSolCount <= solCount + 1) {
			return bestSolCount;
		}

		while (preProcess(gv, card, freq)) {
			if (bestSolCount <= solCount) {
				return bestSolCount;
			}

<<<<<<< HEAD
			// if (eActCount == 0) {
=======
>>>>>>> origin/master
			if (freq[0] == 0) {
				solCount = gv.getSolCount();
				bestSolCount = solCount;
				int[] sol = gv.getSol();
				int[] bestSol = Arrays.copyOf(sol, solCount + 1);
				gv.setBestSol(bestSol);
				gv.setBestSolCount(bestSolCount);
				return bestSolCount;
			}

			if (bestSolCount <= solCount + 1) {
				return bestSolCount;
			}
		}
		// s1 = sActCount;
		// e1 = eActCount;

		if (bestSolCount <= solCount) {
			return bestSolCount;
		}

<<<<<<< HEAD
		// if (e1 == 0) {
=======
>>>>>>> origin/master
		if (freq[0] == 0) {
			solCount = gv.getSolCount();
			bestSolCount = solCount;
			int[] sol = gv.getSol();
			int[] bestSol = Arrays.copyOf(sol, solCount + 1);
			gv.setBestSol(bestSol);
			gv.setBestSolCount(bestSolCount);
			return bestSolCount;
		}

		if (bestSolCount <= solCount + 1) {
			return bestSolCount;
		}

<<<<<<< HEAD
		// int set = Util.getMaxCardinalitySetIndex(gv, s1);
		int set = Util.getMaxCardinalitySetIndex(gv, card[0]);
=======
		int set = Util.getMaxCardinalitySetIndex(gv, card);
>>>>>>> origin/master

		if (set == ConstantValue.IMPOSSIBLE_VALUE) {
			return bestSolCount;
		}
<<<<<<< HEAD
		// int[] card = gv.getCard();
		// eActCount = gv.geteActCount();

		int kMax = kHighest(gv, card, card[set]);

		// if (kMax < e1) {
=======

		int kMax = kHighest(gv, card, card[set]);

>>>>>>> origin/master
		if (kMax < freq[0]) {
			return bestSolCount;
		}

		if (card[set] == 2) {
<<<<<<< HEAD
			int size = this.buildMaxMatching(gv, card);
=======
			int size = this.buildMaxMatching(gv, card, freq);
>>>>>>> origin/master
			solCount = gv.getSolCount();
			bestSolCount = gv.getBestSolCount();

			if (size == ConstantValue.IMPOSSIBLE_VALUE) {
				return bestSolCount;
			} else {
<<<<<<< HEAD
				// if ((size + solCount + (eActCount - 2 * size)) >=
				// bestSolCount) {
				if ((size + solCount + (freq[0] - 2 * size)) >= bestSolCount) {
					return bestSolCount;
				}else{
					// int sActCount = gv.getsActCount();
=======
				if ((size + solCount + (freq[0] - 2 * size)) >= bestSolCount) {
					return bestSolCount;
				} else {
>>>>>>> origin/master
					int[] mate = gv.getMate();
					int[][] sAL = gv.getsAL();
					int[] sL = gv.getsL();
					int[] sol = gv.getSol();
					int solPtr = gv.getSolPtr();

<<<<<<< HEAD
					// for (int i = 1; i <= sActCount; i++) {
=======
>>>>>>> origin/master
					for (int i = 1; i <= card[0]; i++) {
						if ((mate[sAL[sL[i]][1]] == sAL[sL[i]][2]) && (mate[sAL[sL[i]][2]] == sAL[sL[i]][1])) {

							sol[solPtr++] = sL[i];

							solCount++;
							continue;
						}
						if (mate[sAL[sL[i]][1]] == ConstantValue.MATE_EXPOSE) {
							sol[solPtr++] = sL[i];
							solCount++;
							mate[sAL[sL[i]][1]] = sAL[sL[i]][2];
							continue;
						}
						if (mate[sAL[sL[i]][2]] == ConstantValue.MATE_EXPOSE) {
							sol[solPtr++] = sL[i];
							solCount++;
							mate[sAL[sL[i]][2]] = sAL[sL[i]][1];
							continue;
						}
					}

					bestSolCount = solCount;
					int[] bestSol = Arrays.copyOf(sol, solCount + 1);
					gv.setBestSol(bestSol);
					gv.setBestSolCount(bestSolCount);
					gv.setSol(sol);
					gv.setSolCount(solCount);
					gv.setSolPtr(solPtr);
					gv.setMate(mate);
					return bestSolCount;
				}
			}

		}

<<<<<<< HEAD
		// GlobalVariable<ET, ST> copyGv1 = Util.copyGlobalVariable(gv);
		// int[] mate = gv.getMate();
		// int[] copyMate = Arrays.copyOf(mate, mate.length);
		// copyGv.setMate(copyMate);
		// card = gv.getCard();
		int[] copyCard = Arrays.copyOf(card, card.length);
		// copyGv1.setCard(copyCard);
		// int[] freq = gv.getFreq();
		int[] copyFreq = Arrays.copyOf(freq, freq.length);
		// copyGv1.setFreq(copyFreq);
=======
		int[] copyCard = Arrays.copyOf(card, card.length);

		int[] copyFreq = Arrays.copyOf(freq, freq.length);
>>>>>>> origin/master

		int tmpSolCount = gv.getSolCount();
		int tmpSolPtr = gv.getSolPtr();

		int[] sol = gv.getSol();
		int solPtr = gv.getSolPtr();

		sol[solPtr++] = set;
		solCount++;

<<<<<<< HEAD
		// int tmpCard = copyCard[set];
		gv.setSolPtr(solPtr);
		gv.setSolCount(solCount);

		addSetToCover(gv, copyCard, copyFreq, set);

		// e2 = e1 - tmpCard;
		// s2 = s1 - 1;
		// copyFreq[0] = copyFreq[0] - tmpCard;
		// copyCard[0] = copyCard[0] - 1;
=======
		gv.setSolPtr(solPtr);
		gv.setSolCount(solCount);

		Util.addSetToCover(gv, copyCard, copyFreq, set);
>>>>>>> origin/master

		int res1 = branch(gv, copyCard, copyFreq);

		copyCard = null;
		copyFreq = null;

		solPtr = tmpSolPtr;
		solCount = tmpSolCount;

		gv.setSolPtr(solPtr);
		gv.setSolCount(solCount);

<<<<<<< HEAD
		// card[0] = s1;
		// freq[0] = e1;

		this.deleteSet(gv, card, freq, set);
		// s1--;
=======
		Util.deleteSet(gv, card, freq, set);
>>>>>>> origin/master

		int res2 = branch(gv, card, freq);

		if (res1 < res2) {

			return res1;
		} else {
			return res2;
		}

	}
}