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
 * 3. add time limit
 */

public class MSC6<ET, ST> implements IMSC<ET, ST> {

	MSC4<ET, ST> msc;

	public MSC6() {
		msc = new MSC4<ET, ST>();
	}

	public int branch(GlobalVariable<ET, ST> gv) {
		long start = System.nanoTime();

		return branch(gv, gv.getCard(), gv.getFreq(), start, ConstantValue.RUNNING_TIME_LIMIT);
	}

//	 private boolean isMomentOfHurry(GlobalVariable<ET,ST> gv){
//	 return true;
//	 }

	private int branch(GlobalVariable<ET, ST> gv, int[] card, int[] freq, long start, long timeLimit) {
		int bestSolCount = gv.getBestSolCount();
		int solCount = gv.getSolCount();

		// if the algorithm runs too long (longer than our patience), stop
		long current = System.nanoTime();
		if (current - start >= timeLimit) {
			return bestSolCount;
		}

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

		if (bestSolCount <= solCount + 1) {
			return bestSolCount;
		}

		while (msc.preProcess(gv, card, freq)) {
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

			if (bestSolCount <= solCount + 1) {
				return bestSolCount;
			}
		}

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

		if (bestSolCount <= solCount + 1) {
			return bestSolCount;
		}

//		if (isMomentOfHurry(gv)) {
//			int allowCrossLvlNum = 2;
//			GreedyMSC.run(gv, card, freq, allowCrossLvlNum);
//			solCount = gv.getSolCount();
//			bestSolCount = solCount;
//			int[] sol = gv.getSol();
//			int[] bestSol = Arrays.copyOf(sol, solCount + 1);
//			gv.setBestSol(bestSol);
//			gv.setBestSolCount(bestSolCount);
//
//		}

		int set = Util.getMaxCardinalitySetIndex(gv, card);

		if (set == ConstantValue.IMPOSSIBLE_VALUE) {
			return bestSolCount;
		}

		int kMax = msc.kHighest(gv, card, card[set]);

		if (kMax < freq[0]) {
			return bestSolCount;
		}

		if (card[set] == 2) {
			int size = msc.buildMaxMatching(gv, card,freq);
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

		int[] copyCard = Arrays.copyOf(card, card.length);

		int[] copyFreq = Arrays.copyOf(freq, freq.length);

		int tmpSolCount = gv.getSolCount();
		int tmpSolPtr = gv.getSolPtr();

		int[] sol = gv.getSol();
		int solPtr = gv.getSolPtr();

		sol[solPtr++] = set;
		solCount++;

		gv.setSolPtr(solPtr);
		gv.setSolCount(solCount);

		Util.addSetToCover(gv, copyCard, copyFreq, set);

		int res1 = branch(gv, copyCard, copyFreq, start, timeLimit);

		copyCard = null;
		copyFreq = null;

		solPtr = tmpSolPtr;
		solCount = tmpSolCount;

		gv.setSolPtr(solPtr);
		gv.setSolCount(solCount);

		Util.deleteSet(gv, card, freq, set);

		int res2 = branch(gv, card, freq, start, timeLimit);

		if (res1 < res2) {

			return res1;
		} else {
			return res2;
		}

	}
}
