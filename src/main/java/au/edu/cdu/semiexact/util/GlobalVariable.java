package au.edu.cdu.semiexact.util;

 
import java.util.Map;

public class GlobalVariable <T>{
	T[] eL;
	Map<T, Integer> eIL;
	int[][] eAL;
	int[][] eIM;
	int[] freq;

	T[] sL;
	Map<T, Integer> sIL;
	int[][] sAL;
	int[][] sIM;
	int[] card;
	
	int curEleCount;
	int curSetCount;
	
	public int getCurEleCount() {
		return curEleCount;
	}
	public void setCurEleCount(int curEleCount) {
		this.curEleCount = curEleCount;
	}
	public int getCurSetCount() {
		return curSetCount;
	}
	public void setCurSetCount(int curSetCount) {
		this.curSetCount = curSetCount;
	}
	public T[] geteL() {
		return eL;
	}
	public void seteL(T[] eL) {
		this.eL = eL;
	}
	public Map<T, Integer> geteIL() {
		return eIL;
	}
	public void seteIL(Map<T, Integer> eIL) {
		this.eIL = eIL;
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
	public int[] getFreq() {
		return freq;
	}
	public void setFreq(int[] freq) {
		this.freq = freq;
	}
	public T[] getsL() {
		return sL;
	}
	public void setsL(T[] sL) {
		this.sL = sL;
	}
	public Map<T, Integer> getsIL() {
		return sIL;
	}
	public void setsIL(Map<T, Integer> sIL) {
		this.sIL = sIL;
	}
	public int[][] getsAL() {
		return sAL;
	}
	public void setsAL(int[][] sAL) {
		this.sAL = sAL;
	}
	public int[][] getsIM() {
		return sIM;
	}
	public void setsIM(int[][] sIM) {
		this.sIM = sIM;
	}
	public int[] getCard() {
		return card;
	}
	public void setCard(int[] card) {
		this.card = card;
	}
}
