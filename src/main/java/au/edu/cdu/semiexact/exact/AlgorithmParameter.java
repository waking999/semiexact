package au.edu.cdu.semiexact.exact;

public class AlgorithmParameter {
	private long bestRunningTime;
	private int unacceptedResultSize;
	private int acceptedResultSize;
	private int bestResultSize;
	private int theshold;
	
	public int getTheshold() {
		return theshold;
	}
	public void setTheshold(int theshold) {
		this.theshold = theshold;
	}
	public long getBestRunningTime() {
		return bestRunningTime;
	}
	public void setBestRunningTime(long bestRunningTime) {
		this.bestRunningTime = bestRunningTime;
	}
	public int getUnacceptedResultSize() {
		return unacceptedResultSize;
	}
	public void setUnacceptedResultSize(int unacceptedResultSize) {
		this.unacceptedResultSize = unacceptedResultSize;
	}
	public int getAcceptedResultSize() {
		return acceptedResultSize;
	}
	public void setAcceptedResultSize(int acceptedResultSize) {
		this.acceptedResultSize = acceptedResultSize;
	}
	public int getBestResultSize() {
		return bestResultSize;
	}
	public void setBestResultSize(int bestResultSize) {
		this.bestResultSize = bestResultSize;
	}
 

}
