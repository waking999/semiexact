package au.edu.cdu.semiexact.exact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

	protected Map<Integer, List<Integer>> transferGVIntoMMParam(GlobalVariable<ET, ST> gv) {

		int[][] sAL = gv.getsAL();
		int sActNum = gv.getsActCount();
		int[] card = gv.getCard();

		Map<Integer, List<Integer>> g = new HashMap<Integer, List<Integer>>();

		for (int i = 0; i < sActNum; i++) {
			if (card[i] > 0) {
				int[] sEL = sAL[i];
				int sELSize = sEL.length;
				for (int j = 0; j < sELSize; j++) {
					int sELj = sEL[j];
					if (!g.containsKey(sELj)) {
						List<Integer> tmpList = new ArrayList<Integer>();
						g.put(sELj, tmpList);
					}
					List<Integer> tmpList = g.get(sELj);
					for (int k = 0; k < sELSize; k++) {
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
	 * 
	 * @param gv
	 * @return
	 */
	protected int buildMaxMatching(GlobalVariable<ET, ST> gv) {
		Map<Integer, List<Integer>> g = transferGVIntoMMParam(gv);
		MM mm = new MM();
		MMObj o = mm.maxMatching(g);

		int[] eL = gv.geteL();
		int eActCount = gv.geteActCount();
		int[] mate = gv.getMate();
		for (int i = 0; i < eActCount; i++) {
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

	// protected int preProcess(GlobalVariable<ET,ST> gv){
	//
	// int k1=-1;
	//
	// return 0;
	// }

	// /**
	// *
	// * @param gv
	// * @return
	// */
	// protected int greedyMatching(GlobalVariable<ET, ST> gv) {
	// int size = 0;
	//
	// int eActNum = gv.geteActCount();
	// int[] eL = gv.geteL();
	//
	// int[] mate = gv.getMate();
	//
	// int[] freq = gv.getFreq();
	//
	// int[][] eAL = gv.geteAL();
	// int[][] sAL = gv.getsAL();
	//
	// int solCount = gv.getSolCount();
	// int bestSolCount = gv.getBestSolCount();
	//
	// for (int i = 0; i < eActNum; i++) {
	// int v1 = eL[i];
	// if (mate[v1] != ConstantValue.IMPOSSIBLE_VALUE) {
	// continue;
	// }
	//
	// int v2;
	//
	// for (int j = 0; j < freq[v1]; j++) {
	// int edge = eAL[v1][j];
	//
	// if (sAL[edge][0] == v1) {
	// v2 = sAL[edge][1];
	// } else {
	// v2 = sAL[edge][0];
	// }
	//
	// if (mate[v2] == ConstantValue.MATE_EXPOSE) {
	// mate[v1] = v2;
	// mate[v2] = v1;
	// size++;
	// if (size + solCount >= bestSolCount) {
	// return ConstantValue.IMPOSSIBLE_VALUE;
	// }
	// break;
	// }
	// }
	//
	// }
	// return size;
	// }

	// protected void augmentMatching(GlobalVariable<ET,ST> gv ,int v, int w){
	// int[] mate=gv.getMate();
	// int[] labelType=gv.getLabelType();
	// int[] labelValue=gv.getLabelValue();
	// int[][] sAL=gv.getsAL();
	//
	// int t=mate[v];
	// mate[v]=w;
	//
	// if(mate[t]!=v){
	// return;
	// }
	//
	// if(labelType[v]==ConstantValue.LABEL_TYPE_VERTEX){
	// mate[t]=labelValue[v];
	// gv.setMate(mate);
	// augmentMatching(gv, labelValue[v],t);
	// return;
	// }else if (labelType[v]==ConstantValue.LABEL_TYPE_EDGE){
	// int x=sAL[labelValue[v]][0];
	// int y=sAL[labelValue[v]][1];
	// augmentMatching(gv,x,y);
	// augmentMatching(gv,y,x);
	// return;
	// }
	// }

	protected int findEdge(GlobalVariable<ET, ST> gv, int v, int w) {
		int[] freq = gv.getFreq();
		int[][] eAL = gv.geteAL();
		int[][] sAL = gv.getsAL();

		for (int i = 0; i < freq[v]; i++) {
			int edge = eAL[v][i];
			if (sAL[edge][0] == w || sAL[edge][1] == w) {
				return edge;
			}
		}
		return ConstantValue.IMPOSSIBLE_VALUE;
	}

	// /**
	// * @param gv
	// * @param v
	// * @param edge
	// * @param join
	// */
	// protected void subLabel(GlobalVariable<ET,ST> gv, int v, int edge, int
	// join){
	// int[] labelType=gv.getLabelType();
	// int[] labelValue=gv.getLabelValue();
	// int[] first=gv.getFirst();
	// int[] outer=gv.getOuter();
	// int[] mate=gv.getMate();
	//
	// while(v!=join){
	// labelType[v]=ConstantValue.LABEL_TYPE_EDGE;
	// labelValue[v]=edge;
	// first[v]=join;
	// int qCount=outer.length;
	// outer[qCount+1]=v;
	// v=first[labelValue[mate[v]]];
	//
	// }
	//
	//
	// gv.setLabelType(labelType);
	// gv.setLabelValue(labelValue);
	// gv.setFirst(first);
	// gv.setOuter(outer);
	// }
	//
	// /**
	// * @param gv
	// * @param x
	// * @param y
	// */
	// protected void doLabel(GlobalVariable<ET,ST> gv,int x, int y){
	// int[] first=gv.getFirst();
	// int[] labelType=gv.getLabelType();
	// int[] labelValue=gv.getLabelValue();
	// int[] mate=gv.getMate();
	// int[] outer=gv.getOuter();
	//
	// int edge=findEdge(gv,x,y);
	// int flag=-edge;
	// int r=first[x];
	// int s=first[y];
	//
	// if(r==s){
	// return;
	// }
	//
	// labelValue[r]=flag;
	// labelValue[s]=flag;
	//
	// if(s!=ConstantValue.IMPOSSIBLE_VALUE){
	// int tmp=r;
	// r=s;
	// s=tmp;
	// }
	//
	// r=first[labelValue[mate[r]]];
	// while(labelValue[r]!=flag){
	// labelValue[r]=flag;
	// if(s!=ConstantValue.IMPOSSIBLE_VALUE){
	// int tmp=r;
	// r=s;
	// s=tmp;
	// }
	// r=first[labelValue[mate[r]]];
	// }
	// int join=r;
	//
	// gv.setLabelType(labelType);
	// gv.setLabelValue(labelValue);
	//
	// subLabel(gv,first[x], edge, join);
	// subLabel(gv,first[y], edge, join);
	//
	// int qCount=outer.length;
	//
	// for(int i=0;i<qCount;i++){
	// if(labelValue[first[outer[i]]]>=0){
	// first[outer[i]]=join;
	// }
	// }
	// }
	//
	// /**
	// * @param gv
	// * @return
	// */
	// protected int buldMaxMatching(GlobalVariable<ET,ST> gv) {
	//
	//
	// int[] mate=gv.getMate();
	// int[] labelValue=gv.getLabelValue();
	// int[] labelType=gv.getLabelType();
	// int[] outer=gv.getOuter();
	// int[] first=gv.getFirst();
	//
	//
	// int eActCount=gv.geteActCount();
	// int[] eL=gv.geteL();
	// int[] freq=gv.getFreq();
	// int[][] eAL=gv.geteAL();
	// int[][] sAL=gv.getsAL();
	//
	//
	// int solCount=gv.getSolCount();
	// int bestSolCount=gv.getBestSolCount();
	//
	// int qCount=0;
	// int qPtr=-1;
	//
	//
	//
	// for(int i=0;i<eActCount;i++){
	// labelValue[eL[i]]=ConstantValue.IMPOSSIBLE_VALUE;
	// labelType[eL[i]]=ConstantValue.IMPOSSIBLE_VALUE;
	// first[eL[i]]=ConstantValue.IMPOSSIBLE_VALUE;
	// outer[eL[i]]=ConstantValue.IMPOSSIBLE_VALUE;
	// mate[eL[i]]=ConstantValue.MATE_EXPOSE;
	// }
	//
	// int size=greedyMatching(gv);
	//
	// if(size==ConstantValue.IMPOSSIBLE_VALUE){
	// return ConstantValue.IMPOSSIBLE_VALUE;
	// }
	//
	// for(int i=0;i<eActCount;i++){
	// int v1=eL[i];
	//
	// if(mate[v1]!=ConstantValue.MATE_EXPOSE){
	// continue;
	// }
	// labelValue[v1]=ConstantValue.LABEL_TYPE_START;
	// labelType[v1]=ConstantValue.LABEL_TYPE_START;
	// outer[qCount]=v1;
	// qCount++;
	// while(qCount!=qPtr){
	// qPtr++;
	// int v2=outer[qPtr];
	// for(int j=0;j<freq[v2];j++){
	// int edge=eAL[v2][j];
	// int v3;
	// if(sAL[edge][0]==v2){
	// v3=sAL[edge][1];
	// }else{
	// v3=sAL[edge][0];
	// }
	//
	// if(mate[v3]==ConstantValue.MATE_EXPOSE&&v3!=v1){
	// mate[v3]=v2;
	// gv.setMate(mate);
	// augmentMatching(gv,v2,v3);
	// size++;
	// qPtr=qCount;
	// if(size+solCount>=bestSolCount){
	// return ConstantValue.IMPOSSIBLE_VALUE;
	// }
	// break;
	// }else if(labelType[v3]>=ConstantValue.LABEL_TYPE_START){
	// doLabel(gv,v2,v3);
	// }else{
	// int v4=mate[v3];
	// if(labelType[v4]<ConstantValue.LABEL_TYPE_START){
	// labelType[v4]=ConstantValue.LABEL_TYPE_VERTEX;
	// labelValue[v4]=v2;
	// first[v4]=v3;
	//
	// outer[qCount]=v4;
	// qCount++;
	//
	// gv.setLabelType(labelType);
	// gv.setLabelValue(labelValue);
	// gv.setFirst(first);
	// gv.setOuter(outer);
	// }
	// }
	// }
	// }
	// for(int k=0;k<eActCount;k++){
	// labelType[eL[k]]=ConstantValue.IMPOSSIBLE_VALUE;
	// labelValue[eL[k]]=ConstantValue.IMPOSSIBLE_VALUE;
	// }
	// qCount=0;
	// qPtr=0;
	// }
	// return size;
	// }

}