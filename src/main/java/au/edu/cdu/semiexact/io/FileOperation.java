package au.edu.cdu.semiexact.io;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import au.edu.cdu.semiexact.util.Util;

public class FileOperation {
	private static final String BLANK = " ";

	public static GlobalVariable retriveProblemInfoByEdgePair(String filePath) throws IOException  {
		Path path = Paths.get(filePath);
		List<String> lines = Files.readAllLines(path, Charset.defaultCharset());
		String line0 = lines.get(0);
		String[] line0Array = line0.split(BLANK);
		String numOfVerStr = line0Array[0];
		int numOfVer = Integer.parseInt(numOfVerStr);

		int mallocsize = numOfVer + 1;

		int[][] sim = new int[mallocsize][mallocsize];
		int[][] eim = new int[mallocsize][mallocsize];

		int[] oc = new int[mallocsize];
		int[] of = new int[mallocsize];

		int[] sl = new int[mallocsize];
		int[] sil = new int[mallocsize];

		int[] el = new int[mallocsize];
		int[] eil = new int[mallocsize];
		
		

		for (int i = 0; i <= numOfVer; i++) {
			oc[i] = 1;
			of[i] = 1;

			sl[i] = i;
			sil[i] = i;
			el[i] = i;
			eil[i] = i;

			for (int j = 0; j < numOfVer; j++) {
				sim[i][j] = -1;
				eim[i][j] = -1;
			}
		}
		oc[0] = 0;
		of[0] = 0;

		int linesSize = lines.size();
		for (int i = 1; i < linesSize; i++) {
			String line = lines.get(i);
			String[] lineArray = line.split(BLANK);
			int v1 = Integer.parseInt(lineArray[0]);
			int v2 = Integer.parseInt(lineArray[1]);

			sim[v1][v2] = 1;
			sim[v2][v1] = 1;
			eim[v1][v2] = 1;
			eim[v2][v1] = 1;

			oc[v1]++;
			oc[v2]++;
			of[v1]++;
			of[v2]++;

		}
		
		for(int i=1;i<=numOfVer;i++){
			sim[i][i]=1;
			eim[i][i]=1;
		}
		
		int ocMaxIdx=Util.getMaxIndex(oc);
		int[][] sal=new int[mallocsize][oc[ocMaxIdx]+2];
		
		int ofMaxIdx=Util.getMaxIndex(of);
		int[][] eal=new int[mallocsize][of[ofMaxIdx]+2];
		
		for(int i=1;i<=numOfVer;i++){
			int scurrent=1;
			int ecurrent=1;
			for(int j=1;j<=numOfVer;j++){
				if(sim[i][j]==1){
					sim[j][i]=scurrent;
					sal[i][scurrent]=j;
					scurrent++;
					
				}
				if(eim[i][j]==1){
					eim[j][i]=ecurrent;
					eal[i][ecurrent]=j;
					ecurrent++;
					
				}
			}
		}
		
		int[] mate=new int[mallocsize];
		int[] labelValue=new int[mallocsize];
		int[] labelType=new int[mallocsize];
		int[] first=new int[mallocsize];
		
		for(int i=0;i<=numOfVer;i++){
			mate[i]=0;
			labelValue[i]=-1;
			labelType[i]=-1;
			first[i]=0;
		}
		
		GlobalVariable gv=new GlobalVariable();
		gv.setEal(eal);
		gv.setEil(eil);
		gv.setEim(eim);
		gv.setEl(el);
		gv.setFirst(first);
		gv.setLabelType(labelType);
		gv.setLabelValue(labelValue);
		gv.setMate(mate);
		gv.setOc(oc);
		gv.setOf(of);
		gv.setSal(sal);
		gv.setSil(sil);
		gv.setSim(sim);
		gv.setSl(sl);
		
		gv.setE(numOfVer);
		gv.setS(numOfVer);
		gv.setBestSolCount(numOfVer);
		gv.setSolCount(0);
		return gv;

	}
}
