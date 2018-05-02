package au.edu.cdu.se.ds;

import java.util.List;

/**
 * a java bean to encapsulate results.
 * 
 */
class ReturnResult<T> {
	private int resultSize;

	private List<T> results;

	int getResultSize() {
		return resultSize;
	}

	void setResultSize(int resultSize) {
		this.resultSize = resultSize;
	}

	List<T> getResults() {
		return results;
	}

//	public void setResults(List<T> results) {
//		this.results = results;
//	}

	ReturnResult(int resultSize, List<T> results) {
		super();
		this.resultSize = resultSize;
		this.results = results;
	}

//	public String toString() {
//		StringBuffer sb = new StringBuffer();
//		sb.append(resultSize).append(":");
//		for (T t : results) {
//			sb.append(t.toString()).append(",");
//		}
//		return sb.substring(0, sb.length() - 1);
//	}
}
