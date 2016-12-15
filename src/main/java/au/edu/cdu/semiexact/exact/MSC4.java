package au.edu.cdu.semiexact.exact;

<<<<<<< HEAD
import java.util.Map;

/**
 * 
 * @author kwang1 1. convert Faisal's c code into java format
 */
public class MSC4 {
	private String[] sL; // index to set list
	private Map<String, Integer> sIL; // set to index list
	private int[][] sAL; // set index adjacent list
	private int[][] sIM; // set index incident matrix
	
	private String[] eL; //index to element list
	private Map<String,Integer> eIL;// element to index list
	private int[][] eAL; // element index adjacent list,
	private int[][] eIM; // element index incident matrix



	public int[][] getsIM() {
		return sIM;
	}

	public void setsIM(int[][] sIM) {
		this.sIM = sIM;
	}

	public String[] geteL() {
		return eL;
	}

	public void seteL(String[] eL) {
		this.eL = eL;
	}

	public Map<String, Integer> geteIL() {
		return eIL;
	}

	public void seteIL(Map<String, Integer> eIL) {
		this.eIL = eIL;
	}



	public String[] getsL() {
		return sL;
	}

	public void setsL(String[] sL) {
		this.sL = sL;
	}

	public Map<String, Integer> getsIL() {
		return sIL;
	}

	public void setsIL(Map<String, Integer> sIL) {
		this.sIL = sIL;
	}

	public int[][] getsAL() {
		return sAL;
	}

	public void setsAL(int[][] sAL) {
		this.sAL = sAL;
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

	
	protected void decreaseElementFrequency(int[] freq, int curEleCount, int eToDec, int sToDelIdx) {
		int eIMSToDelCellVal=eIM[sToDelIdx][eToDec];
		int eFreq=freq[eToDec];
		int sToExchIdx=eAL[eToDec][eFreq-1];
		int eIMSToExchCellVal=eIM[sToExchIdx][eToDec];
		
		// exchange the index of set to be deleted with that of last active set in eIM
		eIM[sToExchIdx][eToDec]=eIMSToDelCellVal;
		eIM[sToDelIdx][eToDec]=eIMSToExchCellVal;
		
		// exchange the index of set to be deleted with that of last active set in eIM
		eAL[eToDec][eIMSToExchCellVal]=sToDelIdx;
		eAL[eToDec][eIMSToDelCellVal]=sToExchIdx;
		freq[eToDec]--;

	}
	
	
	/**
	 * delete a set in the hybrid graph representation
	 * 
	 * @param card,
	 *            the cardinality array of each set
	 * @param curSetCount,
	 *            the current active set count
	 * @param freq,
	 *            the frequency array of each element
	 * @param curEleCount,
	 *            the current active element count
	 * @param sToDel,
	 *            the set to be deleted
	 */
	protected void deleteSet(int[] card, int curSetCount, int[] freq, int curEleCount, String sToDel) {
		String last = sL[curSetCount];
		int sToDelIdx = sIL.get(sToDel);
		// exchange the set to be deleted with the last active set in set list
		sL[sToDelIdx] = last;
		sL[curSetCount] = sToDel;
		// exchange the set to be deleted with the last active set in set index
		// list
		sIL.replace(last, sToDelIdx);
		sIL.replace(sToDel, curSetCount);
		// modify the elements' frequency in the set to be deleted
		for (int i = 1; i <= card[sToDelIdx]; i++) {
			int e = sAL[sToDelIdx][i];
			decreaseElementFrequency(freq, curEleCount, e, sToDelIdx);
		}

		card[sToDelIdx] = 0;

	}
	
	 
=======
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
>>>>>>> origin/master
}
