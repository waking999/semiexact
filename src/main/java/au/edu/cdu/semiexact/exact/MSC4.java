package au.edu.cdu.semiexact.exact;

import java.util.Map;

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

	protected void deleteElement(GlobalVariable<ET, ST> gv, int eToDelIdx, int sFrom) {
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
	 * 
	 * @param gv
	 * @param sToAddIdx
	 */
	protected void addSetToCover(GlobalVariable<ET, ST> gv, int sToAddIdx) {
		int[] card = gv.getCard();
		int[] sL = gv.getsL();
		int sActCount = gv.getsActCount();
		int[] sIL = gv.getsIL();

		int[][] sAL = gv.geteAL();

		int d = card[sToAddIdx];
		int last = sL[sActCount - 1];
		int i = sL[sToAddIdx];
		sL[i] = last;
		sL[sActCount - 1] = sToAddIdx;
		sIL[last] = i;
		sIL[sToAddIdx] = sActCount - 1;

		for (int j = d - 1; j >= 0; j--) {
			int e = sAL[sToAddIdx][j];
			deleteElement(gv, e, sToAddIdx);

		}
		card[sToAddIdx] = 0;

		gv.setCard(card);
		gv.setsL(sL);
		gv.setsIL(sIL);
		gv.setsAL(sAL);
		gv.setsActCount(sActCount - 1);
	}

}