package au.edu.cdu.se.is;

import au.edu.cdu.se.TestUtil;
import au.edu.cdu.se.util.ConstantValue;
import org.junit.Ignore;
import org.junit.Test;

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
    public void testBHOSLIB() {


        IMIS mis = new MIS3A();

        TestUtil.basicTestLoop(ConstantValue.DATASET_BHOSLIB, ConstantValue.DB_VNAME_INS_IS_OPT, CLASS_NAME, mis);

    }
}
