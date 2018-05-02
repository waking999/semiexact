package au.edu.cdu.se.ds;

import au.edu.cdu.se.TestUtil;
import au.edu.cdu.se.util.ConstantValue;
import org.junit.Ignore;
import org.junit.Test;

/**
 * a test case to test more complex instances
 */
public class MSC6TestDataset {
    private static final String CLASS_NAME = MSC6TestDataset.class.getSimpleName();
    // private static Logger log = LogUtil.getLogger(MSC4Test.class);

    @Ignore
    @Test
    public void testBHOSLIB() {

        IMSC msc = new MSC6();

        TestUtil.basicTestLoop(ConstantValue.DATASET_KONECT, CLASS_NAME, msc);

    }

    @Ignore
    @Test
    public void testKONECT() {


        IMSC msc = new MSC6();

        TestUtil.basicTestLoop(ConstantValue.DATASET_KONECT, CLASS_NAME, msc);

    }

}
