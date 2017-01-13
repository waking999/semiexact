package au.edu.cdu.semiexact.exact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

 

public class MMTest {

	
	 @Test
	public void test1() {
 
		Map<Integer,List<Integer>> g = new HashMap<Integer,List<Integer>>();
		List<Integer> l0=new ArrayList<Integer>();
		l0.add(1);
		l0.add(2);
		l0.add(3);
		g.put(0, l0);
		
		List<Integer> l1=new ArrayList<Integer>();
		l1.add(0);
		l1.add(2);
		l1.add(3);
		g.put(1, l1);
		
		List<Integer> l2=new ArrayList<Integer>();
		l2.add(0);
		l2.add(1); 
		g.put(2, l2);
		
		List<Integer> l3=new ArrayList<Integer>();
		l3.add(0);
		l3.add(1); 
		g.put(3, l3);
		
		List<Integer> l4=new ArrayList<Integer>();
		l4.add(5); 
		g.put(4, l4);
		
		List<Integer> l5=new ArrayList<Integer>();
		l5.add(4);
		l5.add(6); 
		g.put(5, l5);
		
		List<Integer> l6=new ArrayList<Integer>();
		l6.add(5); 
		g.put(6, l6);
		
//		g[0].add(1);
//		g[0].add(2);
//		g[0].add(3);
//		
//		g[1].add(0);
//		g[1].add(2);
//		g[1].add(3);
//		
//		g[2].add(0);
//		g[2].add(1);
//		
//		g[3].add(0);
//		g[3].add(1);
//		
//		g[4].add(5);
//		
//		g[5].add(4);
//		g[5].add(6);
//		
//		g[6].add(5); 
		
		MM mm=new MM();
		MMObj o=mm.maxMatching(g);
		Assert.assertEquals(3, o.getMnum());
		

	}
	 
	 @Test
	public void test2() {

		 Map<Integer,List<Integer>> g = new HashMap<Integer,List<Integer>>();
			List<Integer> l0=new ArrayList<Integer>();
			l0.add(1);
		 
			g.put(0, l0);
			
			List<Integer> l1=new ArrayList<Integer>();
			l1.add(0);
			l1.add(2);
			l1.add(3);
			g.put(1, l1);
			
			List<Integer> l2=new ArrayList<Integer>();
			l2.add(1);
			l2.add(5); 
			g.put(2, l2);
			
			List<Integer> l3=new ArrayList<Integer>();
			l3.add(1);
			l3.add(4); 
			l3.add(5);
			g.put(3, l3);
			
			List<Integer> l4=new ArrayList<Integer>();
			l4.add(3); 
			l4.add(5); 
			g.put(4, l4);
			
			List<Integer> l5=new ArrayList<Integer>();
			l5.add(2);
			l5.add(3); 
			l5.add(4); 
			g.put(5, l5);
			
			 

//		g[0].add(1);
//
//		
//		g[1].add(2);
//
//		g[1].add(3);
//
//		g[3].add(4);
//
//		g[4].add(5);
//
//		g[5].add(3);
//
//		g[5].add(2);
//////
//		g[1].add(0);
//		g[2].add(1);
//		g[2].add(5);
//		g[3].add(1);
//		g[3].add(5);
//		g[4].add(3);
//		g[5].add(4);
//		
//		 
		
		MM mm=new MM();
		MMObj o=mm.maxMatching(g);
		Assert.assertEquals(3, o.getMnum());
		

	}
}
