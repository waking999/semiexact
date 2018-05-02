package au.edu.cdu.se.ds;

import au.edu.cdu.se.TestUtil;
import au.edu.cdu.se.util.ConstantValue;
import org.junit.Ignore;
import org.junit.Test;


/**
 * a test case to test more complex instances
 */
public class MSC5TestDataset {
    private static final String CLASS_NAME = MSC5TestDataset.class.getSimpleName();

    // private static Logger log = LogUtil.getLogger(MSC4Test.class);

    // @Ignore
    // @Test
    // public void testDIMCS() throws IOException {
    // String algTableName = ConstantValue.DB_TBNAME_ALG1;
    //
    // String[] instanceNames = { "brock200_2", "brock200_4", "brock400_2",
    // "brock400_4", "brock800_2",
    // "brock800_4", };
    //
    // IMSC msc = new MSC5();
    //
    // TestUtil.basicTest(instanceNames, algTableName, msc);
    //
    // }

    @Ignore
    @Test
    public void testBHOSLIB() {

        IMSC msc = new MSC5();

        TestUtil.basicTestLoop(ConstantValue.DATASET_BHOSLIB, CLASS_NAME, msc);

    }

    @Ignore
    @Test
    public void testKONECT() {

        IMSC msc = new MSC5();

        TestUtil.basicTestLoop(ConstantValue.DATASET_KONECT, CLASS_NAME, msc);

    }

}
