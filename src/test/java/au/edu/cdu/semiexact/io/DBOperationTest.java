package au.edu.cdu.semiexact.io;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

 

public class DBOperationTest {

	@Test
	public void testExecuteQuery() throws Exception{
		DBParameter dbp=new DBParameter();
		dbp.setTableName("instance");
		String[] colNames={"id", "i_name", "file_name", "v_count", "e_count"};
		String[] colPairNames={"i_name","file_name"};
		String[] colPairOperators={"=","="};
		String[] colPairValues={"cc", "cc.clq"};
		dbp.setColNames(colNames);
		dbp.setColPairNames(colPairNames);
		dbp.setColPairOperators(colPairOperators);
		dbp.setColPairValues(colPairValues);
		
		DBOperation dbo=new DBOperation();
		List<Map<String, String>> lst=dbo.executeQuery(dbp);
		Assert.assertNotNull(lst);
	}
	
	
	@Test
	public void testExecuteQuery2() throws Exception{
		DBParameter dbp=new DBParameter();
		dbp.setTableName("instance");
		String[] colNames={"id", "i_name", "file_name", "v_count", "e_count"};
		String[] colPairNames={"i_name"};
		String[] colPairOperators={"like"};
		String[] colPairValues={"%bro%"};
		dbp.setColNames(colNames);
		dbp.setColPairNames(colPairNames);
		dbp.setColPairOperators(colPairOperators);
		dbp.setColPairValues(colPairValues);
		
		DBOperation dbo=new DBOperation();
		List<Map<String, String>> lst=dbo.executeQuery(dbp);
		Assert.assertNotNull(lst);
	}
	
	@Test
	public void testExecuteQuery3() throws Exception{
		DBParameter dbp=new DBParameter();
		dbp.setTableName("instance");
		String[] colNames={"id", "i_name", "file_name", "v_count", "e_count"};
		String[] colPairNames={"v_count"};
		String[] colPairOperators={"<"};
		String[] colPairValues={"300"};
		dbp.setColNames(colNames);
		dbp.setColPairNames(colPairNames);
		dbp.setColPairOperators(colPairOperators);
		dbp.setColPairValues(colPairValues);
		
		DBOperation dbo=new DBOperation();
		List<Map<String, String>> lst=dbo.executeQuery(dbp);
		Assert.assertNotNull(lst);
	}
	
	
	@Ignore
	@Test
	public void testExecuteInsert() throws Exception{
		DBParameter dbp=new DBParameter();
		dbp.setTableName("instance");
		String[] colNames={"i_name","file_name"};
		
		String[] colValues={"cc", "cc.clq"};
		dbp.setColPairNames(colNames);
		dbp.setColPairValues(colValues);
		
		DBOperation dbo=new DBOperation();
		dbo.executeInsert(dbp);
		
	}
}
