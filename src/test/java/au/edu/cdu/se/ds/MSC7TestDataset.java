package au.edu.cdu.se.ds;

import au.edu.cdu.se.TestUtil;
import au.edu.cdu.se.util.ConstantValue;
import org.junit.Ignore;
import org.junit.Test;

/**
 * a test case to test more complex instances
 */
public class MSC7TestDataset {
    private static final String CLASS_NAME = MSC7TestDataset.class.getSimpleName();

    // private static Logger log = LogUtil.getLogger(MSC4Test.class);
    @Ignore
    @Test
    public void testIgnore() {

    }

    @Ignore
    @Test
    public void testBHOSLIB() {


        IMSC msc = new MSC7();

        TestUtil.basicTestLoop(ConstantValue.DATASET_BHOSLIB, CLASS_NAME, msc);

    }

    // @Ignore
    @Test
    public void testKONECT() {


        IMSC msc = new MSC7();

        TestUtil.basicTestLoop(ConstantValue.DATASET_KONECT, CLASS_NAME, msc);

    }

}
