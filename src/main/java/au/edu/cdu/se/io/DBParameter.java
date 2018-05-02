package au.edu.cdu.se.io;

public class DBParameter {
	private String tableName;
	private String[] colNames;
	private String[] colPairNames;
	private String[] colPairOperators;

	private String[] colPairValues;

	String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	String[] getColPairNames() {
		return colPairNames;
	}

	public void setColPairNames(String[] colPairNames) {
		this.colPairNames = colPairNames;
	}

	String[] getColPairOperators() {
		return colPairOperators;
	}

	void setColPairOperators(String[] colPairOperators) {
		this.colPairOperators = colPairOperators;
	}

	String[] getColPairValues() {
		return colPairValues;
	}

	public void setColPairValues(String[] colPairValues) {
		this.colPairValues = colPairValues;
	}

	String[] getColNames() {
		return colNames;
	}

	void setColNames(String[] colNames) {
		this.colNames = colNames;
	}

}
