package au.edu.cdu.semiexact.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class FileOperation {
	private static final String BLANK = " ";
	
	public static void readGraph(String filePath) throws FileNotFoundException, IOException{
		Path path = Paths.get(filePath);
		List<String> lines = Files.readAllLines(path, Charset.defaultCharset());
		String line0 = lines.get(0);
		String[] line0Array = line0.split(BLANK);
		String numOfVerStr = line0Array[0];
		//String numOfEdStr = line0Array[1];
		
		int numOfVer = Integer.parseInt(numOfVerStr);
		//int numOfEd= Integer.parseInt(numOfEdStr);
		 
		
		for(int i=0;i<=numOfVer;i++){
			
		}
		
		
		int linesSize = lines.size();
		for (int i = 1; i < linesSize; i++) {
			 
		}
	}
}
