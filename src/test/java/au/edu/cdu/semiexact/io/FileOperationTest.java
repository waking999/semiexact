package au.edu.cdu.semiexact.io;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

import org.junit.Test;

import au.edu.cdu.semiexact.TestUtil;
import au.edu.cdu.semiexact.util.GlobalVariable;

public class FileOperationTest {
	@Test(expected = IOException.class)
	public void testRetriveProblemInfoByEdgePairIOExp() throws IOException {
		String filePath = "";
		new FileOperation().readGraphByEdgePair(filePath);
	}

	@Test(expected = NoSuchFileException.class)
	public void testRetriveProblemInfoByEdgePairFoundExp() throws IOException {
<<<<<<< Updated upstream
		String filePath = "./src/test/resources/sample.txt1";
=======
<<<<<<< HEAD
		String filePath = TestUtil.getCurrentPath()+"/src/test/resources/sample1.txt";

 
=======
		String filePath = "./src/test/resources/sample.txt1";
>>>>>>> origin/master
>>>>>>> Stashed changes
		new FileOperation().readGraphByEdgePair(filePath);
	}



	@Test
	public void testRetriveProblemInfoByEdgePairNormal() throws IOException {
<<<<<<< Updated upstream
		String filePath = "./src/test/resources/sample.txt";
=======
<<<<<<< HEAD
	 
		String filePath = TestUtil.getCurrentPath()+"/src/test/resources/sample.txt";

		
=======
		String filePath = "./src/test/resources/sample.txt";
>>>>>>> origin/master
>>>>>>> Stashed changes
		GlobalVariable<String,String> gv = new FileOperation().readGraphByEdgePair(filePath);
		TestUtil.printStatus(gv);

	}
}
