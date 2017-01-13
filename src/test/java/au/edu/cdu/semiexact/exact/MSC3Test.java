package au.edu.cdu.semiexact.exact;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import au.edu.cdu.semiexact.TestUtil;
/**
 * test class for msc3 
 *
 */
public class MSC3Test {

	@Test
	public void testCase1() {
		Map<Integer,List<Integer>> map = TestUtil.getTestCase1ForBasicMSCMap();
		ReturnResult<Integer> rr = new ReturnResult<Integer>(0, new ArrayList<Integer>());

		 
		MSC3 msc = new MSC3();
		msc.setMap(map);
		msc.setRr(rr);
		
		ReturnResult<Integer> rr1 = msc.run();

		Assert.assertEquals(2, rr1.getResultSize());
		System.out.println(rr1);
	}

}
