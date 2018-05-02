package au.edu.cdu.se.util;

/**
 * a java bean to store if a satisfying set exist and what index of the set is
 */
public class ExistQualifiedSet {
	private boolean exist;
	private int setIndex;

	public boolean isExist() {
		return exist;
	}

	public int getSetIndex() {
		return setIndex;
	}

	ExistQualifiedSet(boolean exist, int subsetIndex) {
		this.exist = exist;
		this.setIndex = subsetIndex;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (exist) {
			sb.append("exist:").append(this.setIndex);
		} else {
			sb.append("not exist.");
		}

		return sb.toString();
	}
}