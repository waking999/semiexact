package au.edu.cdu.semiexact.exact;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import au.edu.cdu.semiexact.TestUtil;

/**
 * test class for msc2
 *
 */
public class MSC2Test {

	@Test
	public void testCase1() {
		List<List<Integer>> list = TestUtil.getTestCase1ForBasicMSC();

		MSC2 msc = new MSC2();
		msc.setS(list);
		int result = msc.run();
		Assert.assertEquals(2, result);
		System.out.println(result);
	}

}
