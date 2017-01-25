package au.edu.cdu.semiexact;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import au.edu.cdu.semiexact.exact.MMTest;
import au.edu.cdu.semiexact.exact.MSC1Test;
import au.edu.cdu.semiexact.exact.MSC2Test;
import au.edu.cdu.semiexact.exact.MSC3Test;
import au.edu.cdu.semiexact.exact.MSC4Test;
import au.edu.cdu.semiexact.exact.MSC4TestDataset;
import au.edu.cdu.semiexact.exact.MSC5Test;
import au.edu.cdu.semiexact.exact.MSC5TestDataset;

/**
 * a test suit for algorithms
 */
@RunWith(Suite.class)
@SuiteClasses({ MSC1Test.class, MSC2Test.class, MSC3Test.class, MMTest.class, MSC4Test.class,MSC4TestDataset.class, MSC5Test.class,
		MSC5TestDataset.class })
public class AlgoTests {

}
