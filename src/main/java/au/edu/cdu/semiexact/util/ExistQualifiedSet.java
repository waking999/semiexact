package au.edu.cdu.semiexact.util;

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
	
	public String toString(){
		StringBuffer sb=new StringBuffer();
		if(exist){
			sb.append("exist:").append(this.setIndex);
		}else{
			sb.append("not exist.");
		}
		
		return sb.toString();
	}
}