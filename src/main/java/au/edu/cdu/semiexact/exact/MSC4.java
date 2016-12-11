package au.edu.cdu.semiexact.exact;

import java.io.IOException;

import org.junit.Assert;

import au.edu.cdu.semiexact.io.FileOperation;
import au.edu.cdu.semiexact.io.GlobalVariable;

public class MSC4 {
	private String filePath;

	public MSC4(String filePath) {
		super();
		this.filePath = filePath;
	}

	public void run() throws IOException {
		GlobalVariable gv = FileOperation.retriveProblemInfoByEdgePair(filePath);
		int s = gv.getS();
		int e = gv.getE();

		int mallocsize = e + 1;

		int[] sol = new int[mallocsize];
		int[] bestSol = new int[mallocsize];

		for (int i = 0; i <= s; i++) {
			sol[i] = -1;
			bestSol[i] = -1;
		}

		gv.setBestSol(bestSol);
		gv.setSol(sol);

		int[] oc = gv.getOc();
		int[] of = gv.getOf();

		int result = branch(oc, of, gv);

	}

	private int branch(int card[], int[] freq, GlobalVariable gv) {

		if (gv.getBestSolCount() <= gv.getSolCount()) {
			return gv.getBestSolCount();
		}
		if (gv.getE() == 0) {
			int solCount = gv.getSolCount();
			gv.setBestSolCount(solCount);
			int[] bestSol = gv.getBestSol();
			int[] sol = gv.getSol();

			for (int i = 1; i <= solCount; i++) {
				bestSol[i] = sol[i];

			}
			gv.setBestSol(bestSol);
			return gv.getBestSolCount();
		}

		while (preprocess(card, freq, gv)) {
			return -1;
		}

		return -1;
	}

	private boolean preprocess(int[] card, int[] freq, int cs, int ce, GlobalVariable gv) {

		int s1 = cs;
		int e1 = ce;

		int count = 0;

		int k1 = -1;
		int bestSolCount = gv.getBestSolCount();
		int solCount = gv.getSolCount();

		int[] sol = gv.getSol();

		while (k1 != (bestSolCount - solCount - 1)) {
			k1 = bestSolCount = solCount - 1;
			int domsetSize = getMinDominatedSetSize(card, gv);
			while (domsetSize > -1) {
				if (bestSolCount <= solCount + 1) {
					cs = s1;
					ce = e1;
					return true;
				}
				deleteSet(card, s1, e1, domsetSize, gv);
				s1--;
				count++;
				domsetSize = getMinDominatedSetSize(card, gv);

			}
			int freqoneSet = getSetOfFrequencyOneElements(freq, e1, gv);

			while (freqoneSet > -1) {
				if (bestSolCount <= solCount + 1) {
					cs = s1;
					ce = e1;
					return true;
				}
				sol[solptr++] = freqoneSet;
				solCount++;
				int tmpCard = card[freqoneSet];

				gv.setSol(sol);
				gv.setSolCount(solCount);

				addSetToCover(card, s1, freq, e1, freqoneSet, gv);
				e1=e1-tmpCard;
				s1--;
				count++;
				freqoneSet= getSetOfFrequencyOneElements(freq, e1, gv);

			}
		}
		cs=s1;
		ce=e1;
		if(count>0){
			return true;
		}
		return false;
	}

	private int getMinDominatedSetSize(int[] card, GlobalVariable gv) {
		int otherset, e, count;

		int[] sl = gv.getSl();
		int mincardset;
		int[][] sal = gv.getSal();
		int[][] sim = gv.getSim();

		int s = gv.getS();

		for (int i = 1; i <= s; i++) {
			for (int j = i + 1; j <= s; j++) {
				count = 0;

				if (card[sl[i]] < card[sl[j]]) {
					mincardset = sl[i];
					otherset = sl[j];
				} else {
					mincardset = sl[j];
					otherset = sl[i];
				}

				for (int k = 1; k <= card[mincardset]; k++) {
					e = sal[mincardset][k];

					if (sim[e][otherset] <= card[otherset] && sim[e][otherset] > 0) {
						count++;
						continue;
					} else {
						break;
					}
				}

				if (count == card[mincardset]) {
					return mincardset;
				}
			}
		}

		return -1;
	}

	private void deleteSet(int[] card, int cs, int ce, int s, GlobalVariable gv) {

		int[] sl = gv.getSl();
		int last = sl[cs];
		int[] sil = gv.getSil();
		int[][] sal = gv.getSal();

		int currentIdx = sil[s];
		sl[currentIdx] = last;
		sl[cs] = s;
		sil[last] = currentIdx;
		sil[s] = cs;

		for (int i = 1; i <= card[s]; i++) {
			int e = sal[s][i];
			decreaseElementFrequency(ce, e, s, gv);
		}
		card[s] = 0;

		gv.setSl(sl);
		gv.setSil(sil);

	}

	private void decreaseElementFrequency(int ce, int e, int s, GlobalVariable gv) {
		int[][] eim = gv.getEim();
		int[][] eal = gv.getEal();
		int[] freq = gv.getOf();

		int i = eim[s][e];

		Assert.assertTrue(i <= freq[e] && i > 0);
		int j = freq[e];
		int x = eal[e][j];
		eal[e][i] = x;
		eim[x][e] = i;
		eal[e][j] = s;
		eim[s][e] = j;
		freq[e]--;

		gv.setOf(freq);
		gv.setEal(eal);
		gv.setEim(eim);

	}

	private int getSetOfFrequencyOneElements(int[] freq, int ce, GlobalVariable gv) {
		int[] el = gv.getEl();
		int[][] eal = gv.getEal();

		for (int i = 1; i <= ce; i++) {
			if (freq[el[i]] == 1) {
				return eal[(el[i])][1];
			}
		}
		return -1;
	}
	
	private void addSetToCover(int[] card, int cs, int[] freq, int ce, int s, GlobalVariable gv){
		int[] sl=gv.getSl();
		int[] sil=gv.getSil();
		
		int last=sl[cs];
		int currentIdx=sil[s];
		sl[currentIdx]=last;
		sl[cs]=s;
		sil[last]=currentIdx;
		
		
		 sil[s]=cs;
		 int[][] sal=gv.getSal();
		 
		 for(int i=1;i<=card[s];i++){
			 int e=sal[s][i];
			 deleteElement(card, cs, freq, ce-(i-1),e,s);
		 }
		 
		 card[s]=9;
	}
}
