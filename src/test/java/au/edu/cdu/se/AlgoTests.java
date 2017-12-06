package au.edu.cdu.se;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import au.edu.cdu.se.ds.MMTest;
import au.edu.cdu.se.ds.MSC1Test;
import au.edu.cdu.se.ds.MSC2Test;
import au.edu.cdu.se.ds.MSC3Test;
import au.edu.cdu.se.ds.MSC4Test;
import au.edu.cdu.se.ds.MSC4TestDataset;
import au.edu.cdu.se.ds.MSC5Test;
import au.edu.cdu.se.ds.MSC5TestDataset;
import au.edu.cdu.se.ds.MSC6TestDataset;
import au.edu.cdu.se.ds.MSC7TestDataset;

@RunWith(Suite.class)
@SuiteClasses({ MMTest.class, MSC1Test.class, MSC2Test.class, MSC3Test.class, MSC4Test.class, MSC4TestDataset.class,
		MSC5Test.class, MSC5TestDataset.class, MSC6TestDataset.class, MSC7TestDataset.class })
public class AlgoTests {

}
