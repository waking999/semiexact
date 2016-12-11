package au.edu.cdu.semiexact;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import au.edu.cdu.semiexact.exact.MSC1Test;
import au.edu.cdu.semiexact.exact.MSC2Test;
import au.edu.cdu.semiexact.exact.MSC3Test;

@RunWith(Suite.class)
@SuiteClasses({ MSC1Test.class,MSC2Test.class,MSC3Test.class })
public class ALgoTests {

}
