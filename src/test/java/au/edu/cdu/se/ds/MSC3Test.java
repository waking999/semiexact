package au.edu.cdu.se.ds;

import au.edu.cdu.se.TestUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * test class for msc3
 */
public class MSC3Test {

    @Test
    public void testCase1() {
        Map<Integer, List<Integer>> map = TestUtil.getTestCase1ForBasicMSCMap();
        ReturnResult<Integer> rr = new ReturnResult<>(0, new ArrayList<>());

        MSC3 msc = new MSC3();
        msc.setMap(map);
        msc.setRr(rr);

        ReturnResult<Integer> rr1 = msc.run();

        Assert.assertEquals(2, rr1.getResultSize());
        System.out.println(rr1);
    }

}
