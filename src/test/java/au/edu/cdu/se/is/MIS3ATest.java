package au.edu.cdu.se.is;

import au.edu.cdu.se.TestUtil;
import au.edu.cdu.se.io.FileOperation;
import au.edu.cdu.se.util.ConstantValue;
import au.edu.cdu.se.util.is.ISGlobalVariable;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

public class MIS3ATest {
    private static final String CLASS_NAME = MIS3ATest.class.getSimpleName();

    //    @Ignore
//    @Test
//    public void testKONECT() {
//
//
//        IMIS mis = new MIS3A();
//
//        TestUtil.basicTestLoop(ConstantValue.DAT, ConstantValue.DB_VNAME_INS_IS_OPT, CLASS_NAME, mis);
//
//    }
    @Ignore
    @Test
    public void testCase_bhoslib_frb30_15_1() throws IOException {
        String filePath = TestUtil.getBasePath() + "/src/test/resources/BHOSLIB/frb30-15-mis/frb30-15-1.mis";
        ISGlobalVariable gv = FileOperation.readGraphByEdgePairForIS(filePath);
        IMIS mis = new MIS3A();
        mis.setGv(gv);

        ISAlgoParam ap = new ISAlgoParam();
        ap.setAcceptedResultSize(27);
        ap.setUnacceptedResultSize(26);
        ap.setBestResultSize(30);
        ap.setThreshold(23);

        ap.setAllowedRunningTime(0);
        mis.setAp(ap);

        int rtn = mis.run();
        Assert.assertEquals(30, rtn);
    }

    @Ignore
    @Test
    public void testBHOSLIB() {


        IMIS mis = new MIS3A();

        TestUtil.basicTestLoop(ConstantValue.DATASET_BHOSLIB, ConstantValue.DB_VNAME_INS_IS_OPT, CLASS_NAME, mis);

    }
}
