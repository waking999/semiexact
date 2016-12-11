package au.edu.cdu.semiexact.exact;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import au.edu.cdu.semiexact.TestUtil;

public class MSC3Test {

	@Test
	public void test() {
		Map<Integer,List<Integer>> map = TestUtil.simpleTestCase1Map();
		ReturnResult<Integer> rr = new ReturnResult<Integer>(0, new ArrayList<Integer>());

		 
		MSC3 msc = new MSC3();
		msc.setMap(map);
		msc.setRr(rr);
		
		ReturnResult<Integer> rr1 = msc.run();

		Assert.assertEquals(2, rr1.getResultSize());
		System.out.println(rr1);
	}

}
