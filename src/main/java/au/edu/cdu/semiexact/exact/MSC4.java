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
	protected int buildMaxMatching(GlobalVariable<ET, ST> gv, int[] card) {
		Map<Integer, List<Integer>> g = Util.transferGVIntoMMParam(gv, card);
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
			mate[key] = val;
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
		// int eActCount = gv.geteActCount();
		// int[] card = gv.getCard();
		int solPtr = gv.getSolPtr();

		int k1 = -1;
		int count = 0;

		while (k1 != (bestSolCount - solCount - 1)) {
			// subset rule
			k1 = bestSolCount - solCount - 1;
			int domSet = Util.getSubset(gv, card);
			while (domSet > ConstantValue.IMPOSSIBLE_VALUE) {
				if (bestSolCount <= solCount + 1) {
					card[0] = s1;
					freq[0] = e1;
					return true;
				}
				Util.deleteSet(gv, card, freq, domSet);
				s1--;
				count++;
				domSet = Util.getSubset(gv, card);
			}
			// frequency one rule
			int freqOneSet = Util.getSetOfFrequencyOneElement(gv, freq);
			while (freqOneSet > ConstantValue.IMPOSSIBLE_VALUE) {
				if (bestSolCount <= solCount + 1) {
					return true;
				}
				sol[solPtr++] = freqOneSet;
				solCount++;
				int tmpCard = card[freqOneSet];
				Util.addSetToCover(gv, card, freq, freqOneSet);
				e1 = e1 - tmpCard;
				s1--;
				// gv.seteCount(eActCount);

				count++;
				freqOneSet = Util.getSetOfFrequencyOneElement(gv, freq);
			}
			// TODO: other rules

		}
		// gv.seteCount(eActCount);
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
		// TODO: make sure the sum of cardinalityies of the k sets of highest card is greater than number of elements
		int bestSolCount = gv.getBestSolCount();
		int solCount = gv.getSolCount();
		// int sActCount = gv.getsCount();
		int sActCount = card[0];
		int[] sL = gv.getsL();
		// int[] card = gv.getCard();

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

		// if (e1 == 0) {
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

			// if (eActCount == 0) {
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

		// if (e1 == 0) {
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

		// int set = Util.getMaxCardinalitySetIndex(gv, s1);
		int set = Util.getMaxCardinalitySetIndex(gv, card);

		if (set == ConstantValue.IMPOSSIBLE_VALUE) {
			return bestSolCount;
		}
		// int[] card = gv.getCard();
		// eActCount = gv.geteActCount();

		int kMax = kHighest(gv, card, card[set]);

		// if (kMax < e1) {
		if (kMax < freq[0]) {
			return bestSolCount;
		}

		if (card[set] == 2) {
			int size = this.buildMaxMatching(gv, card);
			solCount = gv.getSolCount();
			bestSolCount = gv.getBestSolCount();

			if (size == ConstantValue.IMPOSSIBLE_VALUE) {
				return bestSolCount;
			} else {
				// if ((size + solCount + (eActCount - 2 * size)) >=
				// bestSolCount) {
				if ((size + solCount + (freq[0] - 2 * size)) >= bestSolCount) {
					return bestSolCount;
				}else{
					// int sActCount = gv.getsActCount();
					int[] mate = gv.getMate();
					int[][] sAL = gv.getsAL();
					int[] sL = gv.getsL();
					int[] sol = gv.getSol();
					int solPtr = gv.getSolPtr();

					// for (int i = 1; i <= sActCount; i++) {
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

		int tmpSolCount = gv.getSolCount();
		int tmpSolPtr = gv.getSolPtr();

		int[] sol = gv.getSol();
		int solPtr = gv.getSolPtr();

		sol[solPtr++] = set;
		solCount++;

		// int tmpCard = copyCard[set];
		gv.setSolPtr(solPtr);
		gv.setSolCount(solCount);

		Util.addSetToCover(gv, copyCard, copyFreq, set);

		// e2 = e1 - tmpCard;
		// s2 = s1 - 1;
		// copyFreq[0] = copyFreq[0] - tmpCard;
		// copyCard[0] = copyCard[0] - 1;

		int res1 = branch(gv, copyCard, copyFreq);

		copyCard = null;
		copyFreq = null;

		solPtr = tmpSolPtr;
		solCount = tmpSolCount;

		gv.setSolPtr(solPtr);
		gv.setSolCount(solCount);

		// card[0] = s1;
		// freq[0] = e1;

		Util.deleteSet(gv, card, freq, set);
		// s1--;

		int res2 = branch(gv, card, freq);

		if (res1 < res2) {

			return res1;
		} else {
			return res2;
		}

	}
}