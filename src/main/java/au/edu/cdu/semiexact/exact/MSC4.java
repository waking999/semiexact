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
	 * 
	 * @param gv
	 * @return
	 */
	protected int buildMaxMatching(GlobalVariable<ET, ST> gv, int[] card, int[] freq) {
		Map<Integer, List<Integer>> g = Util.transferGVIntoMMParam(gv, card, freq);
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
		int solPtr = gv.getSolPtr();

		int k1 = -1;
		int count = 0;

		while (k1 != (bestSolCount - solCount - 1)) {
			// subset rule
			k1 = bestSolCount - solCount - 1;
			int domSet = Util.getSubset(gv, card);
			while (domSet > ConstantValue.IMPOSSIBLE_VALUE) {
				// if (bestSolCount <= solCount + 1) {
				// card[0] = s1;
				// freq[0] = e1;
				// gv.setSol(sol);
				// gv.setSolPtr(solPtr);
				// gv.setSolCount(solCount);
				// return true;
				// }
				Util.deleteSet(gv, card, freq, s1, e1, domSet);
				s1--;
				count++;
				domSet = Util.getSubset(gv, card);
			}
			// frequency one rule
			int freqOneSet = Util.getSetOfFrequencyOneElement(gv, freq, e1);
			while (freqOneSet > ConstantValue.IMPOSSIBLE_VALUE) {
				// if (bestSolCount <= solCount + 1) {
				// return true;
				// }
				sol[solPtr++] = freqOneSet;
				solCount++;
				int tmpCard = card[freqOneSet];
				Util.addSetToCover(gv, card, freq, s1, e1, freqOneSet);
				e1 = e1 - tmpCard;
				s1--;

				count++;
				freqOneSet = Util.getSetOfFrequencyOneElement(gv, freq, e1);
			}
			// TODO: other rules

		}
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

	protected int kHighest(GlobalVariable<ET, ST> gv, int[] card, int maxCard, int sActCount) {
		// TODO: make sure the sum of cardinalities of the k sets of highest
		// card is greater than number of elements
		int bestSolCount = gv.getBestSolCount();
		int solCount = gv.getSolCount();
		// int sActCount = card[0];
		// int sActCount=gv.getsCount();
		int[] sL = gv.getsL();

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

	public int branch(GlobalVariable<ET, ST> gv, AlgorithmParameter ap) {
		int[] card = gv.getCard();
		int[] freq = gv.getFreq();

		return branch(gv, card, freq );
	}

	private int branch(GlobalVariable<ET, ST> gv, int[] card, int[] freq) {
		int bestSolCount = gv.getBestSolCount();
		int solCount = gv.getSolCount();
		int s1 = card[0];
		int e1 = freq[0];

		if (bestSolCount <= solCount) {
			return bestSolCount;
		}

		if (e1 == 0) {
			solCount = gv.getSolCount();
			bestSolCount = solCount;
			int[] sol = gv.getSol();
			int[] bestSol = Arrays.copyOf(sol, solCount + 1);
			gv.setBestSol(bestSol);
			gv.setBestSolCount(bestSolCount);
			return bestSolCount;
		}

		// if (bestSolCount <= solCount + 1) {
		// return bestSolCount;
		// }

		while (preProcess(gv, card, freq )) {
			if (bestSolCount <= solCount) {
				return bestSolCount;
			}

			if (freq[0] == 0) {
				solCount = gv.getSolCount();
				bestSolCount = solCount;
				int[] sol = gv.getSol();
				int[] bestSol = Arrays.copyOf(sol, solCount + 1);
				gv.setBestSol(bestSol);
				gv.setBestSolCount(bestSolCount);
				return bestSolCount;
			}

			// if (bestSolCount <= solCount + 1) {
			// return bestSolCount;
			// }
		}
		s1 = card[0];
		e1 = freq[0];

		if (bestSolCount <= solCount) {
			return bestSolCount;
		}

		if (e1 == 0) {
			solCount = gv.getSolCount();
			bestSolCount = solCount;
			int[] sol = gv.getSol();
			int[] bestSol = Arrays.copyOf(sol, solCount + 1);
			gv.setBestSol(bestSol);
			gv.setBestSolCount(bestSolCount);
			return bestSolCount;
		}

		// if (bestSolCount <= solCount + 1) {
		// return bestSolCount;
		// }

		int set = Util.getMaxCardinalitySetIndex(gv, card, s1);

		if (set == ConstantValue.IMPOSSIBLE_VALUE) {
			return bestSolCount;
		}

		int kMax = kHighest(gv, card, card[set], s1);

		if (kMax < e1) {
			return bestSolCount;
		}

		if (card[set] == 2) {
			int size = this.buildMaxMatching(gv, card, freq);
			solCount = gv.getSolCount();
			bestSolCount = gv.getBestSolCount();

			if (size == ConstantValue.IMPOSSIBLE_VALUE) {
				return bestSolCount;
			} else {
				if ((size + solCount + (freq[0] - 2 * size)) >= bestSolCount) {
					return bestSolCount;
				} else {
					int[] mate = gv.getMate();
					int[][] sAL = gv.getsAL();
					int[] sL = gv.getsL();
					int[] sol = gv.getSol();
					int solPtr = gv.getSolPtr();

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

					if (bestSolCount > solCount) {
						bestSolCount = solCount;
						int[] bestSol = Arrays.copyOf(sol, solCount + 1);
						gv.setBestSol(bestSol);
						gv.setBestSolCount(bestSolCount);
						gv.setSol(sol);
						gv.setSolCount(solCount);
						gv.setSolPtr(solPtr);
						gv.setMate(mate);
					}
					return bestSolCount;
				}
			}

		}

		int[] copyCard = Arrays.copyOf(card, card.length);

		int[] copyFreq = Arrays.copyOf(freq, freq.length);

		int tmpSolCount = gv.getSolCount();
		int tmpSolPtr = gv.getSolPtr();

		int[] sol = gv.getSol();
		int solPtr = gv.getSolPtr();

		sol[solPtr++] = set;
		solCount++;

		int tempCard = copyCard[set];

		gv.setSolPtr(solPtr);
		gv.setSolCount(solCount);

		Util.addSetToCover(gv, copyCard, copyFreq, s1, e1, set);
		int e2 = e1 - tempCard;
		int s2 = s1 - 1;
		copyCard[0]=s2;
		copyFreq[0]=e2;

		int res1 = branch(gv, copyCard, copyFreq);

		copyCard = null;
		copyFreq = null;

		solPtr = tmpSolPtr;
		solCount = tmpSolCount;

		gv.setSolPtr(solPtr);
		gv.setSolCount(solCount);

		Util.deleteSet(gv, card, freq, s1, e1, set);
		s1--;
		card[0]=s1;
		freq[0]=e1;
		
		int res2 = branch(gv, card, freq);
		

		if (res1 < res2) {

			return res1;
		} else {
			return res2;
		}

	}
}