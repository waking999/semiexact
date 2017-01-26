package au.edu.cdu.semiexact.exact;

import java.util.Arrays;

import au.edu.cdu.semiexact.util.ConstantValue;
import au.edu.cdu.semiexact.util.GlobalVariable;
import au.edu.cdu.semiexact.util.Util;

/**
 * 
 * 1. convert Faisal's c code into java format, which also uses Faisal's graph
 * representation data structure ;
 * 
 * 2. use existing Edmonds max matching algorithm;
 * 
 * 3. add time limit 4. add greedy sc at running time long and solution size big
 */

public class MSC7<ET, ST> implements IMSC<ET, ST> {

	MSC4<ET, ST> msc;

	public MSC7() {
		msc = new MSC4<ET, ST>();
	}

	public int branch(GlobalVariable<ET, ST> gv, AlgorithmParameter ap) {
		long start = System.nanoTime();
		int bestResultSize = ap.getBestResultSize();
		int acceptedResultSize = ap.getAcceptedResultSize();
		int unacceptedResultSize = ap.getUnacceptedResultSize();
		int theshold = ap.getTheshold();
		long betRunningTime = ap.getBestRunningTime();
		int[] card = gv.getCard();
		int[] freq = gv.getFreq();

		return branch(gv, card, freq, start, betRunningTime, bestResultSize, acceptedResultSize, unacceptedResultSize,
				theshold, 0);
	}

	private boolean isMomentOfHappy(long current, long start, long bestRunningTime, int bestSolCount,
			int acceptedResultSize) {
		if ((current - start >= bestRunningTime) && (bestSolCount < acceptedResultSize)) {
			return true;
		}
		return false;
	}

	private boolean isMomentOfHurry(long current, long start, long bestRunningTime, int bestResultSize,
			int unacceptedResultSize, int bestSolCount, int solCount, int theshold) {
		if ((current - start >= bestRunningTime) && (bestSolCount >= unacceptedResultSize)
				&& (bestSolCount - solCount == theshold)) {
			return true;
		}

		return false;

	}

	private int branch(GlobalVariable<ET, ST> gv, int[] card, int[] freq, long start, long bestRunningTime,
			int bestResultSize, int acceptedResultSize, int unacceptedResultSize, int theshold, int level) {

		int bestSolCount = gv.getBestSolCount();
		int solCount = gv.getSolCount();

		long current = System.nanoTime();
		if (isMomentOfHappy(current, start, bestRunningTime, bestSolCount, acceptedResultSize)) {
			return bestSolCount;
		}

		if (isMomentOfHurry(current, start, bestRunningTime, bestSolCount, unacceptedResultSize, bestSolCount, solCount,
				theshold)) {
			/*
			 * larger than unacceptedResultSize, our algorithm is going to
			 * stop exponential search at level min-theshold
			 */
			level += GreedyMSC.run(gv, card, freq);
		}

		// if (current - start >= bestRunningTime) {
		//
		// if (bestSolCount >= unacceptedResultSize) {
		//
		// level += GreedyMSC.run(gv, card, freq);
		//
		// } else if (bestSolCount >= acceptedResultSize) {
		// /*
		// * smaller than upper, but not closer to the lower, continue
		// * search tree
		// */
		// } else {
		// // we are happy with the current bestSolCount as a solution
		// return bestSolCount;
		// }
		// }

		int s1 = card[0];
		int e1 = freq[0];

		if (bestSolCount <= solCount) {
			return bestSolCount;
		}

		if (e1 == 0) {
			solCount = gv.getSolCount();
			if (bestSolCount > solCount) {
				bestSolCount = solCount;
				int[] sol = gv.getSol();
				int[] bestSol = Arrays.copyOf(sol, solCount + 1);
				gv.setBestSol(bestSol);
				gv.setBestSolCount(bestSolCount);
			}
			return bestSolCount;
		}

		while (msc.preProcess(gv, card, freq)) {
			if (bestSolCount <= solCount) {
				return bestSolCount;
			}

			if (freq[0] == 0) {
				solCount = gv.getSolCount();
				if (bestSolCount > solCount) {
					bestSolCount = solCount;
					int[] sol = gv.getSol();
					int[] bestSol = Arrays.copyOf(sol, solCount + 1);
					gv.setBestSol(bestSol);
					gv.setBestSolCount(bestSolCount);
				}
				return bestSolCount;
			}

			// if (bestSolCount <= solCount + 1) {
			// return bestSolCount;
			// }
		}

		solCount = gv.getSolCount();
		bestSolCount = gv.getBestSolCount();

		s1 = card[0];
		e1 = freq[0];

		if (bestSolCount <= solCount) {
			return bestSolCount;
		}

		if (e1 == 0) {
			solCount = gv.getSolCount();
			if (bestSolCount > solCount) {
				bestSolCount = solCount;
				int[] sol = gv.getSol();
				int[] bestSol = Arrays.copyOf(sol, solCount + 1);
				gv.setBestSol(bestSol);
				gv.setBestSolCount(bestSolCount);
			}
			return bestSolCount;
		}

		int set = Util.getMaxCardinalitySetIndex(gv, card, s1);

		if (set == ConstantValue.IMPOSSIBLE_VALUE) {
			return bestSolCount;
		}

		int kMax = msc.kHighest(gv, card, card[set], s1);

		if (kMax < e1) {
			return bestSolCount;
		}

		if (card[set] == 2) {
			int size = msc.buildMaxMatching(gv, card, freq);
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

		solCount = gv.getSolCount();
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

		copyCard[0] = s2;
		copyFreq[0] = e2;

		int res1 = branch(gv, copyCard, copyFreq, start, bestRunningTime, bestResultSize, acceptedResultSize,
				unacceptedResultSize, theshold, level + 1);

		copyCard = null;
		copyFreq = null;

		solPtr = tmpSolPtr;
		solCount = tmpSolCount;

		gv.setSolPtr(solPtr);
		gv.setSolCount(solCount);

		Util.deleteSet(gv, card, freq, s1, e1, set);
		s1--;
		card[0] = s1;
		freq[0] = e1;

		int res2 = branch(gv, card, freq, start, bestRunningTime, bestResultSize, acceptedResultSize,
				unacceptedResultSize, theshold, level + 1);

		if (res1 < res2) {

			return res1;
		} else {
			return res2;
		}

	}
}
