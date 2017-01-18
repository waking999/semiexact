package au.edu.cdu.semiexact.io;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

import org.junit.Test;

import au.edu.cdu.semiexact.TestUtil;
import au.edu.cdu.semiexact.util.GlobalVariable;

/**
 * a test class for file operation class
 */
public class FileOperationTest {
	@Test(expected = IOException.class)
	public void testRetriveProblemInfoByEdgePairIOExp() throws IOException {
		String filePath = "";
		new FileOperation().readGraphByEdgePair(filePath);
	}

	@Test(expected = NoSuchFileException.class)
	public void testRetriveProblemInfoByEdgePairFoundExp() throws IOException {
<<<<<<< HEAD
		String filePath = TestUtil.getCurrentPath()+"/src/test/resources/samplea.txt";
=======
		String filePath = TestUtil.getCurrentPath() + "/src/test/resources/samplea.txt";
>>>>>>> origin/master

		new FileOperation().readGraphByEdgePair(filePath);
	}

	@Test
	public void testRetriveProblemInfoByEdgePairNormal() throws IOException {
<<<<<<< HEAD
	 
		String filePath = TestUtil.getCurrentPath()+"/src/test/resources/sample1.txt";
=======
>>>>>>> origin/master

		String filePath = TestUtil.getCurrentPath() + "/src/test/resources/sample1.txt";

		GlobalVariable<String, String> gv = new FileOperation().readGraphByEdgePair(filePath);
		TestUtil.printGlobalVariableStatus(gv);

	}
}
