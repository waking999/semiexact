package au.edu.cdu.semiexact.util;

/**
 * a java bean to store graph representations of Faisal's data structure
 * 
 * @param <VT>
 *            vertex type
 */
public class MISGlobalVariable<VT> {
	 
	private int vCount; // vertex count
	private int[] vL; // vertex list
	private int[] vIL;// vertex index list
	private int[] deg; // degree
	private int[][] vAL;// vertex adjacency list
	private int[][] vIM; // vertex incidence matrix
 
	private int solCount;
	private int[] sol;
	private int solPtr;

	private int bestSolCount;
	private int[] bestSol;

	private String model;

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
  
 

	public int getvCount() {
		return vCount;
	}

	public void setvCount(int vCount) {
		this.vCount = vCount;
	}

	public int[] getvL() {
		return vL;
	}

	public void setvL(int[] vL) {
		this.vL = vL;
	}

	public int[] getvIL() {
		return vIL;
	}

	public void setvIL(int[] vIL) {
		this.vIL = vIL;
	}

	public int[] getDeg() {
		return deg;
	}

	public void setDeg(int[] deg) {
		this.deg = deg;
	}

	public int[][] getvAL() {
		return vAL;
	}

	public void setvAL(int[][] vAL) {
		this.vAL = vAL;
	}

	public int[][] getvIM() {
		return vIM;
	}

	public void setvIM(int[][] vIM) {
		this.vIM = vIM;
	}

	public int getBestSolCount() {
		return bestSolCount;
	}

	public void setBestSolCount(int bestSolCount) {
		this.bestSolCount = bestSolCount;
	}

 

	public int getSolCount() {
		return solCount;
	}

	public void setSolCount(int solCount) {
		this.solCount = solCount;
	}

	public int[] getSol() {
		return sol;
	}

	public void setSol(int[] sol) {
		this.sol = sol;
	}

	public int getSolPtr() {
		return solPtr;
	}

	public void setSolPtr(int solPtr) {
		this.solPtr = solPtr;
	}

	public int[] getBestSol() {
		return bestSol;
	}

	public void setBestSol(int[] bestSol) {
		this.bestSol = bestSol;
	}

}