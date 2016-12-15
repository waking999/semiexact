package au.edu.cdu.semiexact.io;

public class GlobalVariable {
	private int[][] sim;
	private int[][] eim;

	private int[] oc;
	private int[] of;

	private int[] sl;
	private int[] sil;

	private int[] el;
	private int[] eil;

	private int[][] sal;
	private int[][] eal;

	private int[] mate;
	private int[] labelValue;
	private int[] labelType;
	private int[] first;
	private int s;
	private int e;

	private int bestSolCount;
	private int solCount;

	private int[] bestSol;
	private int[] sol;
	
	public int[] getBestSol() {
		return bestSol;
	}

	public void setBestSol(int[] bestSol) {
		this.bestSol = bestSol;
	}

	public int[] getSol() {
		return sol;
	}

	public void setSol(int[] sol) {
		this.sol = sol;
	}

	public int getSolCount() {
		return solCount;
	}

	public void setSolCount(int solCount) {
		this.solCount = solCount;
	}

	public int getBestSolCount() {
		return bestSolCount;
	}

	public void setBestSolCount(int bestSolCount) {
		this.bestSolCount = bestSolCount;
	}

	public int getS() {
		return s;
	}

	public void setS(int s) {
		this.s = s;
	}

	public int getE() {
		return e;
	}

	public void setE(int e) {
		this.e = e;
	}

	public int[][] getSal() {
		return sal;
	}

	public void setSal(int[][] sal) {
		this.sal = sal;
	}

	public int[][] getEal() {
		return eal;
	}

	public void setEal(int[][] eal) {
		this.eal = eal;
	}

	public int[] getMate() {
		return mate;
	}

	public void setMate(int[] mate) {
		this.mate = mate;
	}

	public int[] getLabelValue() {
		return labelValue;
	}

	public void setLabelValue(int[] labelValue) {
		this.labelValue = labelValue;
	}

	public int[] getLabelType() {
		return labelType;
	}

	public void setLabelType(int[] labelType) {
		this.labelType = labelType;
	}

	public int[] getFirst() {
		return first;
	}

	public void setFirst(int[] first) {
		this.first = first;
	}

	public int[][] getSim() {
		return sim;
	}

	public void setSim(int[][] sim) {
		this.sim = sim;
	}

	public int[][] getEim() {
		return eim;
	}

	public void setEim(int[][] eim) {
		this.eim = eim;
	}

	public int[] getOc() {
		return oc;
	}

	public void setOc(int[] oc) {
		this.oc = oc;
	}

	public int[] getOf() {
		return of;
	}

	public void setOf(int[] of) {
		this.of = of;
	}

	public int[] getSl() {
		return sl;
	}

	public void setSl(int[] sl) {
		this.sl = sl;
	}

	public int[] getSil() {
		return sil;
	}

	public void setSil(int[] sil) {
		this.sil = sil;
	}

	public int[] getEl() {
		return el;
	}

	public void setEl(int[] el) {
		this.el = el;
	}

	public int[] getEil() {
		return eil;
	}

	public void setEil(int[] eil) {
		this.eil = eil;
	}

}
