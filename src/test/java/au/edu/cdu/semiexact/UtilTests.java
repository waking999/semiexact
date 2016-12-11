package au.edu.cdu.semiexact;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import au.edu.cdu.semiexact.io.FileOperationTest;
import au.edu.cdu.semiexact.util.UtilTest;

@RunWith(Suite.class)
@SuiteClasses({UtilTest.class, FileOperationTest.class})
public class UtilTests {

}
