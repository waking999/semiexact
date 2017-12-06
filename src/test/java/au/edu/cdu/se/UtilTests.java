package au.edu.cdu.se;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import au.edu.cdu.se.io.DBOperationTest;
import au.edu.cdu.se.io.FileOperationTest;
import au.edu.cdu.se.util.UtilTest;

/**
 * a test suit for assistant classes
 */
@RunWith(Suite.class)
@SuiteClasses({ UtilTest.class, FileOperationTest.class, DBOperationTest.class })
public class UtilTests {

}
