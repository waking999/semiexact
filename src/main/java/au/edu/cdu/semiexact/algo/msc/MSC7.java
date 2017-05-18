package au.edu.cdu.semiexact.algo.msc;

import java.util.Arrays;

import au.edu.cdu.semiexact.algo.AlgorithmParameter;
import au.edu.cdu.semiexact.util.ConstantValue;
import au.edu.cdu.semiexact.util.MSCGlobalVariable;
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

	// private static Logger log = LogUtil.getLogger(MSC7.class);

	MSC4<ET, ST> msc;

	public MSC7() {
		msc = new MSC4<ET, ST>();
	}

	public int branch(MSCGlobalVariable<ET, ST> gv, AlgorithmParameter ap) {
		long start = System.nanoTime();
		// int bestResultSize = ap.getBestResultSize();
		int acceptedResultSize = ap.getAcceptedResultSize();
		int unacceptedResultSize = ap.getUnacceptedResultSize();
		int threshold = ap.getThreshold();
		// long betRunningTime = ap.getBestRunningTime();
		long allowedRunningTime = ap.getAllowedRunningTime();
		int[] card = gv.getCard();
		int[] freq = gv.getFreq();

		return branch(gv, card, freq, start, allowedRunningTime, acceptedResultSize, unacceptedResultSize, threshold,
				0);
	}

	private boolean isMomentOfHappy(long current, long start, long allowedRunningTime, int acceptedResultSize,
			int bestSolCount) {
		if ((current - start >= allowedRunningTime) && (bestSolCount <= acceptedResultSize)) {
			return true;
		}
		return false;
	}

	private boolean isMomentOfHurry(long current, long start, long allowedRunningTime, int acceptedResultSize,
			int unacceptedResultSize, int bestSolCount, int solCount, int threshold) {
		if ((current - start >= allowedRunningTime) && (bestSolCount >= unacceptedResultSize)
				&& (acceptedResultSize - solCount <= threshold)) {
			return true;
		}

		return false;

	}

	private int branch(MSCGlobalVariable<ET, ST> gv, int[] card, int[] freq, long start, long allowedRunningTime,
			int acceptedResultSize, int unacceptedResultSize, int threshold, int level) {

		int bestSolCount = gv.getBestSolCount();
		int solCount = gv.getSolCount();

		long current = System.nanoTime();
		if (isMomentOfHappy(current, start, allowedRunningTime, acceptedResultSize, bestSolCount)) {
			// if (bestSolCount==bestResultSize) {
			// current = System.nanoTime();
			// log.debug((current-start)+" ns.");
			// }
			gv.setModel("Happy");

			return bestSolCount;
		}

		if (isMomentOfHurry(current, start, allowedRunningTime, acceptedResultSize, unacceptedResultSize, bestSolCount,
				solCount, threshold)) {
			/*
			 * larger than unacceptedResultSize, our algorithm is going to stop
			 * exponential search at level min-theshold
			 */
			gv.setModel("Hurry");
			level += GreedyMSC.run(gv, card, freq);

			// bestSolCount = solCount;
			// int[] sol = gv.getSol();
			// int[] bestSol = Arrays.copyOf(sol, solCount + 1);
			// gv.setBestSol(bestSol);
			// gv.setBestSolCount(bestSolCount);
			//
			// // if (bestSolCount==bestResultSize) {
			// // current = System.nanoTime();
			// // log.debug((current-start)+" ns.");
			// // }
			// return bestSolCount;
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

		bestSolCount = gv.getBestSolCount();
		solCount = gv.getSolCount();
		if (bestSolCount <= solCount) {
			// if (bestSolCount==bestResultSize) {
			// current = System.nanoTime();
			// log.debug((current-start)+" ns.");
			// }
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
			// if (bestSolCount==bestResultSize) {
			// current = System.nanoTime();
			// log.debug((current-start)+" ns.");
			// }
			return bestSolCount;
		}

		while (msc.preProcess(gv, card, freq)) {
			if (bestSolCount <= solCount) {
				// if (bestSolCount==bestResultSize) {
				// current = System.nanoTime();
				// log.debug((current-start)+" ns.");
				// }
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
				// if (bestSolCount==bestResultSize) {
				// current = System.nanoTime();
				// log.debug((current-start)+" ns.");
				// }
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
			// if (bestSolCount==bestResultSize) {
			// current = System.nanoTime();
			// log.debug((current-start)+" ns.");
			// }
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
			// if (bestSolCount==bestResultSize) {
			// current = System.nanoTime();
			// log.debug((current-start)+" ns.");
			// }
			return bestSolCount;
		}

		int set = Util.getMaxCardinalitySetIndex(gv, card, s1);

		if (set == ConstantValue.IMPOSSIBLE_VALUE) {
			// if (bestSolCount==bestResultSize) {
			// current = System.nanoTime();
			// log.debug((current-start)+" ns.");
			// }
			return bestSolCount;
		}

		int kMax = msc.kHighest(gv, card, card[set], s1);

		if (kMax < e1) {
			// if (bestSolCount==bestResultSize) {
			// current = System.nanoTime();
			// log.debug((current-start)+" ns.");
			// }
			return bestSolCount;
		}

		if (card[set] == 2) {
			int size = msc.buildMaxMatching(gv, card, freq);
			solCount = gv.getSolCount();
			bestSolCount = gv.getBestSolCount();

			if (size == ConstantValue.IMPOSSIBLE_VALUE) {
				// if (bestSolCount==bestResultSize) {
				// current = System.nanoTime();
				// log.debug((current-start)+" ns.");
				// }
				return bestSolCount;
			} else {
				if ((size + solCount + (freq[0] - 2 * size)) >= bestSolCount) {
					// if (bestSolCount==bestResultSize) {
					// current = System.nanoTime();
					// log.debug((current-start)+" ns.");
					// }
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
					// if (bestSolCount==bestResultSize) {
					// current = System.nanoTime();
					// log.debug((current-start)+" ns.");
					// }
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

		int res1 = branch(gv, copyCard, copyFreq, start, allowedRunningTime, acceptedResultSize, unacceptedResultSize,
				threshold, level + 1);

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

		int res2 = branch(gv, card, freq, start, allowedRunningTime, acceptedResultSize, unacceptedResultSize,
				threshold, level + 1);

		if (res1 < res2) {

			return res1;
		} else {
			return res2;
		}

	}
}
