package au.edu.cdu.semiexact.exact;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import au.edu.cdu.semiexact.TestUtil;

public class MSC2Test {

	@Test
	public void test() {
		List<List<Integer>> list = TestUtil.simpleTestCase1();

		MSC2 msc = new MSC2();
		int result = msc.msc(list);
		Assert.assertEquals(2, result);
		System.out.println(result);
	}

}
